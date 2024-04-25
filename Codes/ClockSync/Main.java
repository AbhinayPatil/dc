import java.time.*;
import java.util.*;

public class Main
{
  public static void main(String[] args) {
    LocalTime mainTime = LocalTime.of(10, 15);
    LocalTime[] clocks = {
        LocalTime.of(10, 14),
        LocalTime.of(10, 10),
        LocalTime.of(10, 9),
        LocalTime.of(10, 12)
    };
    LocalTime[] finalClocks = new LocalTime[4];
    int currCounter = 0;
    int[] sumCollection = new int[4];
    Arrays.sort(clocks,Collections.reverseOrder());

    for(int i=0;i<4;i++){
        System.out.println("\nIteration "+(i+1));
      //  prevCounter = currCounter;
        currCounter = (int)Duration.between(mainTime, clocks[i]).toMinutes();

        for(int j=0;j<4;j++){
            System.out.print(clocks[j].plusMinutes(Math.abs(currCounter)));
            int currDiff = (int)Duration.between(mainTime,clocks[j]).toMinutes();
            System.out.println("  (" + (currDiff + (Math.abs(currCounter))) + ") ");
            sumCollection[j] += (currDiff + (Math.abs(currCounter)));
            if(i==3){
                finalClocks[j] = clocks[j].plusMinutes(Math.abs(currCounter));
            }
        }
    }
    System.out.println("\n\n");
    for(int i=0;i<4;i++){
        System.out.println("current time: " + finalClocks[i] + "  " + "Deviation: " + ((double)sumCollection[i]/4) + " \t\t");
    }

    System.err.println("\nFinal time: " + finalClocks[0].minusMinutes(sumCollection[0]/4));
  }
}
