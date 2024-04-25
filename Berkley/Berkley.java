import java.time.*;

public class Berkley {
    public static void main(String[] args) {
        LocalTime master =  LocalTime.of(3,00);
        LocalTime[] clocks =  {
            LocalTime.of(2,50),
            LocalTime.of(3,25),
        };
        int sumOfdifferences = 0;
        int[] diffs = new int[clocks.length];
        System.out.println("master time: " + master);
        for(int i=0;i<clocks.length;i++){
            int diff = (int)Duration.between(master, clocks[i]).toMinutes();
            diffs[i] = diff;
            System.out.println("Diff between master and clock"+(i+1)+": " + diff + "\t[ "+ clocks[i] + " ]");
            sumOfdifferences+= diff;
        }
        System.out.println("sum of diff: "+sumOfdifferences);
        int avg = sumOfdifferences/(clocks.length +1);
        System.out.println("avg diff: "+ avg);

        System.out.println("\n\n Updated time:");
        System.out.println("Master: " + master.plusMinutes(avg));
        for(int i=0;i<clocks.length;i++){
            System.out.println("clock"+(i+1)+" :- " + clocks[i].plusMinutes(avg-diffs[i]));
        }
    }
}
