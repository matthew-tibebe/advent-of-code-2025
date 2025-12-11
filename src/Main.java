import java.io.*;
import java.sql.Array;
import java.util.*;

import static java.lang.Math.min;

public class Main {
    public static void main(String[] args) throws IOException {
        //d1p1();
        //d1p2();
        //d2p1();
        //d2p2();
        //d3p1();
        //d3p2();
        //d4p1();
        //d4p2();
        //d5p1();
        //d5p2();
        //d6p1();
        //d6p2();
        //d7p1();
        //d7p2();
        //d8p1();
        //d9p1();
        //d10p1();
        d11p1();
    }

    public static void d1p1() throws IOException {
        int minVal = 0;
        int maxVal = 99;
        int currentVal = 50;
        int zeroCounter = 0;
        BufferedReader fileReader = new BufferedReader(new FileReader("data/day-1-input.txt"));
        ArrayList<String> rotations = new ArrayList<>();
        String line = fileReader.readLine();
        while(line != null){
            rotations.add(line);
            line = fileReader.readLine();
        }
        fileReader.close();
        for(int i = 0; i < rotations.size(); i++){
            String step = rotations.get(i);
            String direction = step.substring(0,1);
            int amount = Integer.parseInt(step.substring(1));
            System.out.printf("Current Value: %d\tStep: %s\t", currentVal, step);
            if(direction.equals("L")){
                currentVal-=amount;
            } else {
                currentVal+=amount;
            }
            System.out.printf("value after rotation: %d\t", currentVal);
            while(currentVal < minVal){ //-1 should be 99
                currentVal = maxVal+currentVal+1;
            }
            if(currentVal > maxVal){ //100 should be 0
                currentVal%=(maxVal+1);
            }
            System.out.printf("value after correction %d\t", currentVal);
            if(currentVal == 0){
                zeroCounter++;
            }
            System.out.printf("zero counter is %d\n", zeroCounter);
        }
        System.out.printf("zero counter = %d\n",zeroCounter);
    }

    ///todo figure out more elegant solution later
    public static void d1p2() throws IOException {
        int minVal = 0;
        int maxVal = 99;
        int currentVal = 50;
        int zeroCounter = 0;
        BufferedReader fileReader = new BufferedReader(new FileReader("data/day-1-input.txt"));
        ArrayList<String> rotations = new ArrayList<>();
        String line = fileReader.readLine();
        while(line != null){
            rotations.add(line);
            line = fileReader.readLine();
        }
        fileReader.close();
        for(int i = 0; i < rotations.size(); i++){
            String step = rotations.get(i);
            String direction = step.substring(0,1);
            int amount = Integer.parseInt(step.substring(1));
            System.out.printf("step: %s\t %d->", step, currentVal);
            while(amount > 0){
                if(direction.equals("L")){
                    currentVal--;
                } else {
                    currentVal++;
                }
                if(currentVal < minVal){
                    currentVal = maxVal;
                }
                if(currentVal > maxVal){
                    currentVal = minVal;
                }
                if(currentVal == 0){
                    zeroCounter++;
                }
                amount--;
            }
            System.out.printf("%d\t zc: %d\n", currentVal, zeroCounter);
        }
        System.out.printf("zero counter = %d\n", zeroCounter);
    }

    //todo there is definitely a faster way to do these
    public static void d2p1() throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader("data/day-2-input.txt"));
        String input = fileReader.readLine();
        fileReader.close();
        String[] ids = input.split(",");
        long result = 0;
        ArrayList<Long> invalidIds = new ArrayList<>();
        for(int i = 0; i < ids.length; i++){
            String idRange = ids[i];
            int dashIndex = idRange.indexOf("-");
            long startId = Long.parseLong(idRange.substring(0,dashIndex));
            long endId = Long.parseLong(idRange.substring(dashIndex+1));
            for(long j = startId; j <= endId; j++){
                String id = String.valueOf(j);
                if(id.length()%2 == 0 && id.substring(0,id.length()/2).equals(id.substring(id.length()/2))){
                    invalidIds.add(j);
                    System.out.println(j + " is an invalid id");
                }
            }
        }
        for(int i = 0; i < invalidIds.size(); i++){
            result+=invalidIds.get(i);
        }
        System.out.println("result is: " + result);
    }

    public static void d2p2() throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader("data/day-2-input.txt"));
        String input = fileReader.readLine();
        fileReader.close();
        String[] ids = input.split(",");
        long result = 0;
        ArrayList<Long> invalidIds = new ArrayList<>();
        for(int i = 0; i < ids.length; i++){
            String idRange = ids[i];
            int dashIndex = idRange.indexOf("-");
            long startId = Long.parseLong(idRange.substring(0,dashIndex));
            long endId = Long.parseLong(idRange.substring(dashIndex+1));
            for(long j = startId; j <= endId; j++){
                String id = String.valueOf(j);
                for(int k = 0; k < (id.length()/2)+1; k++){
                    String subsequence = id.substring(0,k+1);
                    int repeatCounter = 0;
                    if(id.length()%subsequence.length() == 0) {
                        for (int l = 0; l < id.length() - k; l += (k + 1)) {
                            String currentSequence = id.substring(l, l + k + 1);
                            System.out.println("id: " + id + " subseq " + subsequence + " cseq " + currentSequence);
                            if (currentSequence.equals(subsequence)) {
                                repeatCounter++;
                            } else {
                                repeatCounter = 0;
                                break;
                            }
                        }//close L
                    }
                    if(repeatCounter > 1){
                        invalidIds.add(Long.parseLong(id));
                        break;
                    }
                }//close K
            }//close J
        }//close I
        for(int i = 0; i < invalidIds.size(); i++){
            result+=invalidIds.get(i);
        }
        System.out.println("result is: " + result);
    }

    public static void d3p1() throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader("data/day-3-input.txt"));
        String line = fileReader.readLine();
        ArrayList<String> banks = new ArrayList<>();
        int totalJoltage = 0;
        while(line != null) {
            banks.add(line);
            line = fileReader.readLine();
        }
        fileReader.close();
        for(int i = 0; i < banks.size(); i++){
            String bank = banks.get(i);
            int maxJoltage = 0;
            for(int j = 0; j < bank.length(); j++){
                for(int k = j+1; k < bank.length(); k++){
                    String currentJoltageStr = "";
                    currentJoltageStr += bank.charAt(j);
                    currentJoltageStr += bank.charAt(k);
                    int currentJoltage = Integer.parseInt(currentJoltageStr);
                    if(currentJoltage > maxJoltage){
                        maxJoltage = currentJoltage;
                    }
                }
            }
            System.out.printf("Max joltage for bank %d is %d\n", i, maxJoltage);
            totalJoltage+=maxJoltage;
        }
        System.out.println("Sum of max joltages per bank = " + totalJoltage);
    }

    public static void d3p2() throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader("data/day-3-input.txt"));
        String line = fileReader.readLine();
        ArrayList<String> banks = new ArrayList<>();
        long totalJoltage = 0;
        while(line != null) {
            banks.add(line);
            line = fileReader.readLine();
        }
        fileReader.close();
        for(int i = 0; i < banks.size(); i++){
            String bank = banks.get(i);
            String voltageStr = "";
            long maxJoltage = 0;
            int startingIndex = 0;
            //scan from 0 to n-11, pick largest number (tiebreaker: earlier in seq), then scan from p to n-10 pick largest number, repeat until 12 digit number?
            //811111111111119
            //--------------
            //8111111111119
            while(voltageStr.length() < 12){
                int checkLen = 11-voltageStr.length();
                //System.out.printf("check len = %d\tstartingIndex = %d\n",checkLen,startingIndex);
                if(startingIndex + checkLen < bank.length()) {
                    int endIndex = startingIndex + (bank.substring(startingIndex).length()-checkLen);
                    int vIndex = startingIndex;
                    char cVolt = bank.charAt(startingIndex);
                    //System.out.printf("start index is %d\tend index is %d\tcVolt is %c\n", startingIndex, endIndex, cVolt);
                    for (int j = startingIndex; j < endIndex; j++) {
                        if (bank.charAt(j) > cVolt && bank.substring(j).length()+voltageStr.length() >= 12) {
                            vIndex = j;
                            cVolt = bank.charAt(j);
                        }
                    }
                    startingIndex = vIndex + 1;
                    voltageStr += String.valueOf(cVolt);
                }
                //System.out.println(voltageStr);
            }
            maxJoltage = Long.parseLong(voltageStr);
            System.out.printf("Max joltage for bank %d is %d\n", i, maxJoltage);
            totalJoltage+=maxJoltage;
        }
        System.out.println("Sum of max joltages per bank = " + totalJoltage);
    }

    public static void d4p1() throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader("data/day-4-input.txt"));
        String line = fileReader.readLine();
        ArrayList<String> rows = new ArrayList<>();
        while(line != null){
            rows.add(line);
            line = fileReader.readLine();
        }
        fileReader.close();
        int numRows = rows.size();
        int numCols = rows.get(0).length();
        int accessible = 0;
        char[][] grid = new char[numRows][numCols];
        for(int i = 0; i < rows.size(); i++){
            String row = rows.get(i);
            for(int j = 0; j < row.length(); j++){
                grid[i][j] = row.charAt(j);
            }
        }
        for(int i = 0; i < numRows; i++){
            for(int j = 0; j < numCols; j++){
                if(grid[i][j] == '@'){
                    int rollCounter = 0;
                    for(int ii = i-1; ii < i+2; ii++){
                        for(int jj = j-1; jj < j+2; jj++){
                            if(ii > -1 && ii < numRows && jj > -1 && jj < numCols){
                                if(grid[ii][jj] == '@'){
                                    rollCounter++;
                                }
                            }
                        }
                    }
                    if(rollCounter < 5){ //account for counting self
                        accessible++;
                        System.out.print('x');
                    } else {
                        System.out.print('@');
                    }
                } else {
                    System.out.print('.');
                }
            }
            System.out.print('\n');
        }
        System.out.println(accessible + " rolls of paper are accessible.");
    }

    public static void d4p2() throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader("data/day-4-input.txt"));
        String line = fileReader.readLine();
        ArrayList<String> rows = new ArrayList<>();
        while(line != null){
            rows.add(line);
            line = fileReader.readLine();
        }
        fileReader.close();
        int numRows = rows.size();
        int numCols = rows.get(0).length();
        int accessible = 0;
        char[][] grid = new char[numRows][numCols];
        for(int i = 0; i < rows.size(); i++){
            String row = rows.get(i);
            for(int j = 0; j < row.length(); j++){
                grid[i][j] = row.charAt(j);
            }
        }
        Day4Result ans = day4Helper(numRows, numCols,grid);
        while(ans.accessible > 0){
            accessible+=ans.accessible;
            ans = day4Helper(numRows, numCols,ans.grid);
        }
        System.out.println(accessible + " rolls of paper are accessible.");
    }

    public static Day4Result day4Helper(int numRows, int numCols, char[][] grid){
        int accessible = 0;
        char[][] newGrid = new char[numRows][numCols];
        for(int i = 0; i < numRows; i++){
            for(int j = 0; j < numCols; j++){
                if(grid[i][j] == '@'){
                    int rollCounter = 0;
                    for(int ii = i-1; ii < i+2; ii++){
                        for(int jj = j-1; jj < j+2; jj++){
                            if(ii > -1 && ii < numRows && jj > -1 && jj < numCols){
                                if(grid[ii][jj] == '@'){
                                    rollCounter++;
                                }
                            }
                        }
                    }
                    if(rollCounter < 5){ //account for counting self
                        accessible++;
                        newGrid[i][j] = '.';
                    } else {
                        newGrid[i][j] = '@';
                    }
                } else {
                    newGrid[i][j] = '.';
                }
            }
        }
        return new Day4Result(newGrid, accessible);
    }

    public static void d5p1() throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader("data/day-5-input.txt"));
        ArrayList<String> idRangeList = new ArrayList<>();
        ArrayList<Long> ingredientIDs = new ArrayList<>();
        boolean fillIdList = false;
        int numSpoiled = 0;
        String line = fileReader.readLine();
        while(line != null){
            if(line.isEmpty()){
                fillIdList = true;
                line = fileReader.readLine();
            }
            if(fillIdList){
                ingredientIDs.add(Long.parseLong(line));
            } else {
                idRangeList.add(line);
            }
            line = fileReader.readLine();
        }
        fileReader.close();
        for(int i = 0; i < ingredientIDs.size(); i++){
            long ingredientID = ingredientIDs.get(i);
            boolean spoiled = true;
            for(int j = 0; j < idRangeList.size(); j++){
                String range = idRangeList.get(j);
                int dashIndex = range.indexOf('-');
                long lowEnd = Long.parseLong(range.substring(0,dashIndex));
                long highEnd = Long.parseLong(range.substring(dashIndex+1));
                if(ingredientID >= lowEnd && ingredientID <= highEnd){
                    spoiled = false;
                    break;
                }
            }
            if(spoiled){
                numSpoiled++;
            }
        }
        System.out.println("There are " + (ingredientIDs.size()-numSpoiled) + " fresh ingredients");
    }

    public static void d5p2() throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader("data/day-5-input.txt"));
        ArrayList<String> idRangeList = new ArrayList<>();
        long freshIDCount = 0;
        String line = fileReader.readLine();
        while(line != null){
            if(line.isEmpty()){
                break;
            }
            idRangeList.add(line);
            line = fileReader.readLine();
        }
        fileReader.close();
        ArrayList<String> modifiedRangeList = day5Helper(idRangeList);
        while(!modifiedRangeList.equals(idRangeList)){
            idRangeList = new ArrayList<>(modifiedRangeList);
            modifiedRangeList = day5Helper(idRangeList);
        }
        for(int i = 0; i < modifiedRangeList.size(); i++){
            String idRange = modifiedRangeList.get(i);
            System.out.println(idRange);
            int dashIndex = idRange.indexOf('-');
            long idRangeLow = Long.parseLong(idRange.substring(0,dashIndex));
            long idRangeHigh = Long.parseLong(idRange.substring(dashIndex+1));
            freshIDCount += idRangeHigh-idRangeLow+1;
        }
        System.out.println("There are " + freshIDCount + " fresh ids");
    }

    public static ArrayList<String> day5Helper(ArrayList<String> idRangeList){
        ArrayList<String> modifiedRangeList = new ArrayList<>();
        //saw a meme about sorting on the AOC subreddit so
        idRangeList.sort((o1, o2) -> {
            long o1Low = Long.parseLong(o1.substring(0,o1.indexOf('-')));
            long o2Low = Long.parseLong(o2.substring(0,o2.indexOf('-')));
            System.out.printf("test %s %s %s %s %d %d\n", o1, o1, o2.substring(0,o1.indexOf('-')), o1.substring(0,o2.indexOf('-')), o1Low, o2Low);
            return Long.compare(o1Low, o2Low);
        });
        for(int i = 0; i < idRangeList.size(); i++){
            String idRange = idRangeList.get(i);
            if(modifiedRangeList.size() < 1){
                modifiedRangeList.add(idRange);
            } else {
                int dashIndex = idRange.indexOf('-');
                long idRangeLow = Long.parseLong(idRange.substring(0,dashIndex));
                long idRangeHigh = Long.parseLong(idRange.substring(dashIndex+1));
                for(int j = 0; j < modifiedRangeList.size(); j++){
                    String modIdRange = modifiedRangeList.get(j);
                    int modDashIndex = modIdRange.indexOf('-');
                    long modIDRangeLow = Long.parseLong(modIdRange.substring(0,modDashIndex));
                    long modIDRangeHigh = Long.parseLong(modIdRange.substring(modDashIndex+1));
                    if(idRangeLow >= modIDRangeLow && idRangeLow <= modIDRangeHigh){
                        idRangeLow = modIDRangeHigh+1;
                    }
                    if(idRangeHigh >= modIDRangeLow && idRangeHigh <= modIDRangeHigh){ //maybe unessecary idk
                        idRangeHigh = modIDRangeLow-1;
                    }
                }
                if(idRangeLow <= idRangeHigh) {
                    String newRange = String.valueOf(idRangeLow).concat("-").concat(String.valueOf(idRangeHigh));
                    modifiedRangeList.add(newRange);
                }
            }
        }
        modifiedRangeList.sort((o1, o2) -> {
            long o1Low = Long.parseLong(o1.substring(0,o1.indexOf('-')));
            long o2Low = Long.parseLong(o2.substring(0,o2.indexOf('-')));
            return Long.compare(o1Low, o2Low);
        });
        return modifiedRangeList;
    }

    public static void d6p1() throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader("data/day-6-input.txt"));
        String line = fileReader.readLine();
        ArrayList<String> allLines = new ArrayList<>();
        ArrayList<ArrayList<String>> problems = new ArrayList<ArrayList<String>>();
        long result = 0;
        while(line != null){
            allLines.add(line);
            line = fileReader.readLine();
        }
        fileReader.close();
        for(int i = 0; i < allLines.size(); i++){
            line = allLines.get(i);
            ArrayList<String> rows = new ArrayList<>();
            while(line.indexOf(' ') != -1){
                String input;
                if(line.indexOf(' ') != -1) {
                    input = line.substring(0, line.indexOf(' '));
                } else {
                    input = line.trim();
                }
                if(!input.isEmpty()){
                    rows.add(input);
                }
                line = line.substring(line.indexOf(' ')+1).trim();
            }
            rows.add(line.trim());
            problems.add(rows);
        }
        for(int i = 0; i < problems.get(0).size(); i++){
            String operator = problems.get(problems.size()-1).get(i);
            ArrayList<String> operands = new ArrayList<>();
            for(int j = 0; j < problems.size()-1; j++){
                operands.add(problems.get(j).get(i));
            }
            if(operator.equals("+")){
                result+=sum(operands);
            }
            if(operator.equals("*")){
                result+=mult(operands);
            }
        }
        System.out.println("The total of answers to all problems is " + result);
    }

    public static void d6p2() throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader("data/day-6-input.txt"));
        String line = fileReader.readLine();
        ArrayList<String> allLines = new ArrayList<>();
        ArrayList<ArrayList<String>> problems = new ArrayList<ArrayList<String>>();
        long result = 0;
        while(line != null){
            allLines.add(line);
            problems.add(new ArrayList<>());
            line = fileReader.readLine();
        }
        fileReader.close();
        //iterate over operands to find size of column (index of next operand -2)
        //iterate through previous lines to get numbers, replace spaces with 0 and reverse
        String operandLine = allLines.get(allLines.size()-1);
        while(operandLine.length() > 1){
            int columnIndex = 0;
            for(int i = 1; i < operandLine.length(); i++){
                if(operandLine.charAt(i) == '+' || operandLine.charAt(i) == '*'){
                    columnIndex = i-1;
                    break;
                }
            }
            problems.get(problems.size()-1).add(operandLine.substring(0,columnIndex));
            for(int i = 0; i < allLines.size()-1; i++){
                String input = allLines.get(i);
                problems.get(i).add(input.substring(0,columnIndex));
                allLines.remove(i);
                allLines.add(i,input.substring(columnIndex+1));
            }
            operandLine = operandLine.substring(columnIndex+1);
        }
        problems.get(problems.size()-1).add(operandLine);
        int padLen = 0;
        for(int i = 0; i < problems.size()-1; i++){
            padLen = Math.max(padLen, allLines.get(i).length());
        }
        for(int i = 0; i < problems.size()-1; i++){
            String input = allLines.get(i);
            while(input.length() < padLen){
                input+=" ";
            }
            problems.get(i).add(input);
        }
        for(int i = 0; i < problems.get(0).size(); i++){
            String operator = problems.get(problems.size()-1).get(i).trim();
            System.out.println("operator " + operator);
            ArrayList<String> operands = new ArrayList<>();
            ArrayList<String> modifiedOperands = new ArrayList();
            for(int j = 0; j < problems.size()-1; j++){
                operands.add(problems.get(j).get(i));
            }
            System.out.println("original: " + Arrays.toString(operands.toArray()));
            for(int j = operands.get(0).length()-1; j > -1; j--){
                String newNum = "";
                for(int k = 0; k < operands.size(); k++){
                    newNum += operands.get(k).charAt(j);
                }
                modifiedOperands.add(newNum.trim());
            }
            System.out.println("modified: " + Arrays.toString(modifiedOperands.toArray()));
            if(operator.equals("+")){
                System.out.println("sum " + sum(modifiedOperands));
                result+=sum(modifiedOperands);
            }
            if(operator.equals("*")){
                System.out.println("mult " + mult(modifiedOperands));
                result+=mult(modifiedOperands);
            }
        }
        System.out.println("The total of answers to all problems is " + result);
    }

    public static long sum(ArrayList<String> numbers){
        long res = 0;
        for(int i = 0; i < numbers.size(); i++){
            res+=Long.parseLong(numbers.get(i));
        }
        return res;
    }

    public static long mult(ArrayList<String> numbers){
        long res = 1;
        for(int i = 0; i < numbers.size(); i++){
            res *= Long.parseLong(numbers.get(i));
        }
        return res;
    }

    public static void d7p1() throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader("data/day-7-input.txt"));
        String line = fileReader.readLine();
        ArrayList<String> lines = new ArrayList<>();
        while(line != null){
            lines.add(line);
            line = fileReader.readLine();
        }
        fileReader.close();
        int rowLen = lines.get(0).length();
        boolean[] arr = new boolean[rowLen];
        int splitCounter = 0;
        for(int i = 0; i < lines.size(); i++) {
            String row = lines.get(i);
            boolean[] newArr = arr.clone();
            for(int j = 0; j < row.length(); j++){
                if(row.charAt(j) == 'S'){
                    newArr[j] = true;
                }
                if(row.charAt(j) == '^' && arr[j]){
                    newArr[j] = false;
                    if(!arr[j-1]){
                        newArr[j-1] = true;
                    }
                    if(!arr[j+1]){
                        newArr[j+1] = true;
                    }
                    if(newArr[j-1] || newArr[j+1]){
                        splitCounter++;
                    }
                }
            }
            arr = newArr;
            day7Printer(arr);
        }
        System.out.println(splitCounter);
    }

    public static void d7p2() throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader("data/day-7-input.txt"));
        String line = fileReader.readLine();
        ArrayList<String> lines = new ArrayList<>();
        long[] timelineCounter = new long[line.length()];
        long totalTimelines = 0;
        while (line != null) {
            lines.add(line);
            line = fileReader.readLine();
        }
        fileReader.close();
        String initialRow = lines.get(0);
        for(int i = 0; i < initialRow.length(); i++){
            if(initialRow.charAt(i) == 'S'){
                timelineCounter[i] = 1;
                break;
            }
        }
        for(int i = 1; i < lines.size(); i++){
            String manifoldRow = lines.get(i);
            for(int j = 0; j < manifoldRow.length(); j++){
                if(manifoldRow.charAt(j) == '^'){
                    timelineCounter[j-1] += timelineCounter[j];
                    timelineCounter[j+1] += timelineCounter[j];
                    timelineCounter[j] = 0;
                }
            }
        }
        for(int i = 0; i < timelineCounter.length; i++){
            totalTimelines+=timelineCounter[i];
        }
        System.out.println("There are " + totalTimelines + " timelines.");
    }

    public static void day7Printer(boolean[] a){
        for(int i = 0; i < a.length; i++){
            if(a[i]){
                System.out.print("1");
            } else {
                System.out.print("0");
            }
        }
        System.out.println();
    }

    //todo finish 8
    public static void d8p1() throws IOException {
        String filename = "data/day-8-sample.txt";
        int numConnections = 10;
        if (filename.contains("input")) {
            numConnections = 1000;
        }
        BufferedReader fileReader = new BufferedReader(new FileReader(filename));
        String line = fileReader.readLine();
        ArrayList<JunctionBoxCoords> boxList = new ArrayList<>();
        ArrayList<JunctionPair> distanceList = new ArrayList<>();
        ArrayList<ArrayList<JunctionBoxCoords>> circuits = new ArrayList<>();
        ArrayList<Integer> circuitLengths = new ArrayList<>();
        int nc = 0;
        long res = 1;
        while (line != null) {
            String[] coords = line.split(",");
            boxList.add(new JunctionBoxCoords(Long.parseLong(coords[0]), Long.parseLong(coords[1]), Long.parseLong(coords[2])));
            line = fileReader.readLine();
        }
        fileReader.close();
        for(int i = 0; i < boxList.size(); i++){
            JunctionBoxCoords boxA = boxList.get(i);
            for(int j = i+1; j < boxList.size(); j++){
                JunctionBoxCoords boxB = boxList.get(j);
                JunctionPair pair = new JunctionPair(boxA, boxB, boxA.distance(boxB));
                distanceList.add(pair);
            }
        }
        distanceList.sort(JunctionPair::compare);
        for(int i = 0; i < distanceList.size(); i++){
            System.out.println(distanceList.get(i));
        }
        System.out.println("--------");
        while(nc < numConnections){
            JunctionPair p = distanceList.get(0);
            distanceList.remove(p);
            System.out.println("connecting " + p);
            JunctionBoxCoords boxA = p.boxA;
            JunctionBoxCoords boxB = p.boxB;
            int circuitIndexA = findCircuit(boxA, circuits);
            int circuitIndexB = findCircuit(boxB, circuits);
            if(circuitIndexA == -1 && circuitIndexB == -1){ //if both arent in a circuit
                ArrayList<JunctionBoxCoords> newCircuit = new ArrayList<>();
                newCircuit.add(boxA);
                newCircuit.add(boxB);
                circuits.add(newCircuit);
            } else if(circuitIndexA == -1){ //if A is not in a circuit but B is
                circuits.get(circuitIndexB).add(boxA);
            } else if(circuitIndexB == -1){ //if B is not in a circuit but A is
                circuits.get(circuitIndexA).add(boxB);
            } else if(circuitIndexA != circuitIndexB){ //neither is -1 and in different circuits
                //todo something wrong here
                ArrayList<JunctionBoxCoords> secondCircuit = circuits.get(circuitIndexB);
                for(int i = 0; i < secondCircuit.size(); i++){
                    circuits.get(circuitIndexA).add(secondCircuit.get(i));
                }
                circuits.remove(circuitIndexB);
            } else if(circuitIndexA == circuitIndexB){ //if they're the same then ignore
                nc--;
            }
            nc++;
        }
        System.out.println("--------");
        for(int i = 0; i < circuits.size(); i++){
            circuitLengths.add(circuits.get(i).size());
            ArrayList<JunctionBoxCoords> circuit = circuits.get(i);
            for(int j = 0; j < circuit.size(); j++){
                System.out.print(circuit.get(j) + " ");
            }
            System.out.println();
        }
        circuitLengths.sort(Integer::compare);
        for(int i = 0; i < 3; i++){
            res*=circuitLengths.get(circuitLengths.size()-i-1);
        }
        System.out.println("The product of the 3 largest circuits is " + res);
    }

    public static int findCircuit(JunctionBoxCoords box, ArrayList<ArrayList<JunctionBoxCoords>> circuits){
        for(int i = 0; i < circuits.size(); i++){
            ArrayList<JunctionBoxCoords> circuit = circuits.get(i);
            for(int j = 0; j < circuit.size(); j++){
                if(box == circuit.get(j)){
                    return i;
                }
            }
        }
        return -1;
    }

    public static void d9p1() throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader("data/day-9-input.txt"));
        String line = fileReader.readLine();
        ArrayList<Coordinate> tileCoordinates = new ArrayList<>();
        long maxArea = Long.MIN_VALUE;
        while(line != null){
            String[] coords = line.split(",");
            Coordinate newTile = new Coordinate(Long.parseLong(coords[0]), Long.parseLong(coords[1]));
            tileCoordinates.add(newTile);
            line = fileReader.readLine();
        }
        fileReader.close();
        tileCoordinates.sort(Coordinate::compare);
        for(int i = 0; i < tileCoordinates.size(); i++){
            Coordinate one = tileCoordinates.get(i);
            for(int j = i+1; j < tileCoordinates.size(); j++) {
                Coordinate two = tileCoordinates.get(j);
                long maybeMax = (Math.abs(two.x - one.x)+1) * (Math.abs(two.y - one.y+1));
                System.out.printf("%s %s = %d\n", one, two, maybeMax);
                maxArea = Math.max(maxArea, maybeMax);
            }
        }
        System.out.println("the largest area you can make is " + maxArea);
    }

    //todo do 9-2

    public static void d10p1() throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader("data/day-10-sample.txt"));
        ArrayList<IndicatorLight> indicatorLights = new ArrayList<>();
        ArrayList<Integer> results = new ArrayList<>();
        int finalSum = 0;
        String line = fileReader.readLine();
        while(line != null){
            indicatorLights.add(new IndicatorLight(line));
            line = fileReader.readLine();
        }
        fileReader.close();
        for(int i = 0; i < indicatorLights.size(); i++){
            int r = indicatorLights.get(i).minPress(true);
            results.add(r);
            finalSum+=r;
        }
        System.out.println("The sum of minimum button presses to configure the machine correctly is " + finalSum);
    }

    public static void d11p1() throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader("data/day-11-input.txt"));
        String line = fileReader.readLine();
        HashMap<String, ArrayList<String>> paths = new HashMap<>();
        ArrayList<ArrayList<String>> testPaths = new ArrayList<>();
        ArrayList<ArrayList<String>> finalPaths = new ArrayList<>();
        while(line != null){
            String key = line.substring(0,line.indexOf(":"));
            ArrayList<String> value = new ArrayList<>(List.of(line.substring(line.indexOf(":") + 1).trim().split(" ")));
            paths.put(key,value);
            line = fileReader.readLine();
        }
        fileReader.close();
        ArrayList<String> tmp = new ArrayList<>();
        tmp.add("you");
        testPaths.add(tmp);
        while(testPaths.size() != 0){
            ArrayList<String> currentTestPath = testPaths.get(0);
            testPaths.remove(0);
            System.out.println("testing " + Arrays.toString(currentTestPath.toArray()));
            String lastServer = currentTestPath.get(currentTestPath.size()-1);
            if(lastServer.equals("out")){
                System.out.println("added to final path");
                finalPaths.add(currentTestPath);
            } else {
                ArrayList<String> nextServerList = paths.get(lastServer);
                for(int i = 0; i < nextServerList.size(); i++){
                    String nextServer = nextServerList.get(i);
                    if(!currentTestPath.contains(nextServer)){
                        System.out.println("adding " + nextServer + " to path");
                        ArrayList<String> newPath = new ArrayList<>(currentTestPath);
                        newPath.add(nextServer);
                        testPaths.add(newPath);
                    }
                }
            }
        }
        System.out.println("There are " + finalPaths.size() + " paths from you to out");
    }
}

class Day4Result{
    char[][] grid;
    int accessible;

    public Day4Result(char[][] g, int a){
        grid = g;
        accessible = a;
    }
}

class JunctionBoxCoords implements Comparator<JunctionBoxCoords>{
    long x,y,z;

    public JunctionBoxCoords(){
        x = 0;
        y = 0;
        z = 0;
    }
    public JunctionBoxCoords(long xPos, long yPos, long zPos){
        x = xPos;
        y = yPos;
        z = zPos;
    }

    public long distance(JunctionBoxCoords other){
        return (long) Math.sqrt((Math.pow(x-other.x,2))+(Math.pow(y-other.y,2))+(Math.pow(z-other.z,2)));
    }

    @Override
    public int compare(JunctionBoxCoords o1, JunctionBoxCoords o2) {
        if(o1.x == o2.x){
            if(o1.y == o2.y){
                if(o1.z == o2.z){
                    return 0;
                }
                return (int) (o1.z-o2.z);
            }
            return (int) (o1.y-o2.y);
        }
        return (int) (o1.x-o2.x);
    }

    public String toString(){
        return "(" + x + "," + y + "," + z + ")";
    }
}

class JunctionPair{
    JunctionBoxCoords boxA, boxB;
    long distance;

    public JunctionPair(JunctionBoxCoords a, JunctionBoxCoords b, long d){
        boxA = a;
        boxB = b;
        distance = d;
    }
    public static int compare(JunctionPair o1, JunctionPair o2) {
        return Long.compare(o1.distance, o2.distance);
    }

    public String toString(){
        return boxA.toString() + " " + boxB.toString() + " " + distance;
    }
}

class Coordinate{
    long x, y;

    public Coordinate(long xPos, long yPos){
        x = xPos;
        y = yPos;
    }

    public static int compare(Coordinate o1, Coordinate o2) {
        if(o1.x == o2.x){
            return (int) (o1.y-o2.y);
        }
        return (int) (o1.x-o2.x);
    }

    public String toString(){
        return "(" + x + "," + y + ")";
    }
}

class IndicatorLight{
    boolean[] finalState;
    ArrayList<ArrayList<Integer>> buttonSequences; //arraylist<int> is the button
    ArrayList<Integer> joltages;

    public IndicatorLight(String input){
        String stateIndicator = input.substring(1,input.indexOf("]"));
        String[] joltageString = input.substring(input.indexOf("{")+1,input.indexOf("}")).split(",");
        input = input.substring(input.indexOf("]")+1, input.indexOf("{")).trim();
        String[] buttonArr = input.split(" ");
        finalState = new boolean[stateIndicator.length()];
        buttonSequences = new ArrayList<>();
        joltages = new ArrayList<>();
        for(int i = 0; i < stateIndicator.length(); i++){
            if(stateIndicator.charAt(i) == '.'){
                finalState[i] = false;
            } else {
                finalState[i] = true;
            }
        }
        for(int i = 0; i < buttonArr.length; i++){
            String[] buttons = buttonArr[i].substring(1,buttonArr[i].length()-1).split(",");
            ArrayList<Integer> buttonSeq = new ArrayList<>();
            for(int j = 0; j < buttons.length; j++){
                buttonSeq.add(Integer.parseInt(buttons[j]));
            }
            buttonSequences.add(buttonSeq);
        }
        for(int i = 0; i < joltageString.length; i++){
            joltages.add(Integer.parseInt(joltageString[i]));
        }
    }

    //todo i know this is really inefficient but - make more efficient for 10-2
    public int minPress(boolean careAboutJoltage){
        ArrayList<ArrayList<ArrayList<Integer>>> testSequences = new ArrayList<>();
        for(int i = 0; i < buttonSequences.size(); i++){
            ArrayList<ArrayList<Integer>> ts = new ArrayList<>();
            ts.add(buttonSequences.get(i));
            testSequences.add(ts);
        }
        while(true){
            for(int i = 0; i < testSequences.size(); i++) {
                ArrayList<ArrayList<Integer>> testSequence = testSequences.get(i);
                //System.out.println(Arrays.toString(testSequence.toArray()));
                boolean[] testLights = new boolean[finalState.length];
                int[] testJoltages = new int[joltages.size()];
                for(int j = 0; j < testSequence.size(); j++){
                    ArrayList<Integer> lights = testSequence.get(j);
                    for(int k = 0; k < lights.size(); k++){
                        testLights[lights.get(k)] = !testLights[lights.get(k)];
                        testJoltages[lights.get(k)]++;
                    }
                }
                if(Arrays.toString(testLights).equals(Arrays.toString(finalState)) && (careAboutJoltage && Arrays.toString(testJoltages).equals(Arrays.toString(joltages.toArray())))){
                    return testSequence.size();
                }
            } //if we're out of this then it means nothing worked
            ArrayList<ArrayList<ArrayList<Integer>>> newSequences = new ArrayList<>();
            for(int i = 0; i < testSequences.size(); i++){
                for(int j = 0; j < buttonSequences.size(); j++){
                    ArrayList<ArrayList<Integer>> oldSequence = new ArrayList<>(testSequences.get(i));
                    oldSequence.add(buttonSequences.get(j));
                    newSequences.add(oldSequence);
                }
            }
            testSequences = newSequences;
        }
    }

    public String toString(){
        String buttonString = "";
        for(int i = 0; i < buttonSequences.size(); i++){
            buttonString += Arrays.toString(buttonSequences.get(i).toArray());
        }
        return "final state: " + Arrays.toString(finalState) + " buttons: " + buttonString + " joltages: " + Arrays.toString(joltages.toArray());
    }
}