import java.util.*;


public class Main {
  private int[][] maximum;
  private int[][] allocation;
  private int[][] need;
  private int[] available;
  private int numberOfProcesses;
  private int numberOfResources;

  public Main(int numberOfProcesses, int numberOfResources) {
    this.numberOfProcesses = numberOfProcesses;
    this.numberOfResources = numberOfResources;
    maximum = new int[numberOfProcesses][numberOfResources];
    allocation = new int[numberOfProcesses][numberOfResources];
    need = new int[numberOfProcesses][numberOfResources];
    available = new int[numberOfResources];
  }

  public void setMaximum(int process, int[] max) {
    maximum[process] = max.clone();
  }

  public void setAllocation(int process, int[] alloc) {
    allocation[process] = alloc.clone();
  }

  public void setAvailable(int[] avail) {
    available = avail.clone();
  }

  public ArrayList<Integer> getSafeSequence() {
    ArrayList<Integer> safeSequence = new ArrayList<Integer>();
    int[] work = available.clone();
    boolean[] finish = new boolean[numberOfProcesses];
    int count = 0;

    while (count < numberOfProcesses) {
      boolean found = false;
      for (int i = 0; i < numberOfProcesses; i++) {

        if (!finish[i]) {
          boolean canAllocate = true;
          for (int j = 0; j < numberOfResources; j++) {
            need[i][j] = maximum[i][j] - allocation[i][j];
            if (need[i][j] > work[j]) {
              canAllocate = false;
              break;
            }
          }
          if (canAllocate) {
            for (int j = 0; j < numberOfResources; j++) {
              work[j] += allocation[i][j];
            }
            finish[i] = true;
            count++;
            safeSequence.add(i); // Add the process to the safe sequence
            found = true;
          }
        }
      }
      if (!found) {
        return null; // No safe sequence found
      }
    }
    return safeSequence;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter number of processes: ");
    int numberOfProcesses = scanner.nextInt();
    System.out.print("Enter number of resources: ");
    int numberOfResources = scanner.nextInt();

    Main banker = new Main(numberOfProcesses, numberOfResources);

    // Input maximum resources
    for (int i = 0; i < numberOfProcesses; i++) {
      System.out.print("Enter maximum resources for process " + i + ": ");
      int[] max = new int[numberOfResources];
      for (int j = 0; j < numberOfResources; j++) {
        max[j] = scanner.nextInt();
      }
      banker.setMaximum(i, max);
    }

    // Input allocated resources
    for (int i = 0; i < numberOfProcesses; i++) {
      System.out.print("Enter allocated resources for process " + i + ": ");
      int[] alloc = new int[numberOfResources];
      for (int j = 0; j < numberOfResources; j++) {
        alloc[j] = scanner.nextInt();
      }
      banker.setAllocation(i, alloc);
    }

    // Input available resources
    System.out.print("Enter available resources: ");
    int[] avail = new int[numberOfResources];
    for (int i = 0; i < numberOfResources; i++) {
      avail[i] = scanner.nextInt();
    }
    banker.setAvailable(avail);

    ArrayList<Integer> safeSequence = banker.getSafeSequence();
    if (safeSequence != null) {
      System.out.println("Safe state: System can allocate resources to all processes.");
      System.out.println("Safe sequence: " + safeSequence);
    } else {
      System.out.println("Unsafe state: System cannot allocate resources to all processes.");
    }
  }
}
