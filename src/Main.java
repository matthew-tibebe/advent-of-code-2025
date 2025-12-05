import java.io.*;
import java.util.ArrayList;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws IOException {
        //d1p1();
        //d1p2();
        d2p1();
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

    //todo there is definitely a faster way to do this
    public static void d2p1() throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader("data/day-2-input.txt"));
        String input = fileReader.readLine();
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

}