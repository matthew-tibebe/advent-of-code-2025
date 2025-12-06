import java.io.*;
import java.util.ArrayList;
import java.util.Optional;

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
        d5p1();
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
}

class Day4Result{
    char[][] grid;
    int accessible;

    public Day4Result(char[][] g, int a){
        grid = g;
        accessible = a;
    }
}