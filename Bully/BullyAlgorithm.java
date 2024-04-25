import java.util.*;

class Process {
    private int processId;
    private boolean isCoordinator;
    private boolean isAlive;

    public Process(int processId) {
        this.processId = processId;
        this.isCoordinator = false;
        this.isAlive = true;
    }

    public int getProcessId() {
        return processId;
    }

    public boolean isCoordinator() {
        return isCoordinator;
    }

    public void setCoordinator(boolean coordinator) {
        isCoordinator = coordinator;
    }
    public boolean isAlive() {
        return isAlive;
    }
    public void setStatus(boolean status){
        isAlive = status;
    }
}

public class BullyAlgorithm {
    private List<Process> processes = new ArrayList<>();
    
    public void addProcess(Process process) {
        processes.add(process);
    }



    public void initiateElection(int initiatingProcessId) {
        System.out.println("Election initiated by Process " + initiatingProcessId);

        for (Process process : processes) {
            if (process.getProcessId() > initiatingProcessId) {
                System.out.println("Process " + initiatingProcessId + " sends Election message to Process "
                        + process.getProcessId());
            }
        }

        int highestId = Collections.max(processes, (p1, p2) -> Integer.compare(p1.getProcessId(), p2.getProcessId()))
                .getProcessId();
        System.out.println("Process " + highestId + " acknowledges the Election and becomes the new Coordinator.");

        for (Process process : processes) {
            process.setCoordinator(process.getProcessId() == highestId);
        }
    }

    public static void main(String[] args) {
        BullyAlgorithm electionSimulator = new BullyAlgorithm();
        Scanner scanner = new Scanner(System.in);

        // Taking user input for the number of processes
        System.out.print("Enter the number of processes: ");
        int numProcesses = scanner.nextInt();

        // Adding processes with user-specified process IDs
        for (int i = 1; i <= numProcesses; i++) {
            electionSimulator.addProcess(new Process(i));
        }

        // Taking user input for the initiating process ID
        System.out.print("Enter the process ID to initiate the election: ");
        int initiatingProcessId = scanner.nextInt();

        // Initiating election based on user input
        electionSimulator.initiateElection(initiatingProcessId);

        // Closing the scanner
        scanner.close();
    }
}
