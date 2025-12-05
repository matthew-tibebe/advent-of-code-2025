import java.io.*;
import java.util.ArrayList;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws IOException {
        dayOne();
    }

    public static void dayOne() throws IOException {
        int minVal = 0;
        int maxVal = 99;
        int currentVal = 50;
        int zeroCounter = 0;
        BufferedReader fileReader = new BufferedReader(new FileReader(new File("data/day-1-input.txt")));
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
                currentVal%=100;
            }
            System.out.printf("value after correction %d\t", currentVal);
            if(currentVal == 0){
                zeroCounter++;
            }
            System.out.printf("zero counter is %d\n", zeroCounter);
        }
        System.out.printf("zero counter = %d\n",zeroCounter);
    }
}