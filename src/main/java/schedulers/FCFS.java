package schedulers;

import java.util.List;
import utilities.Cpu;

import utilities.Cpu;
import utilities.CpuInterface;
import utilities.PerformanceMetricGenerator;
import utilities.Process;

/**
 * First-Come First Served CPU Scheduling Algorithm
 * @author Joette Damo
 */
public class FCFS implements SchedulerInterface {

    /**
     * Processes for scheduler simulation
     */
    private List<Process> processes;
    /**
     * Total time scheduler is active for all processes
     */
    private float totalExecutionTime;
    /**
     * True if processes have been executed, false if otherwise
     */
    private Boolean processesExecuted;

    private CpuInterface cpu;

    /**
     * @inheritDoc
     */
    @Override
    public void loadProcesses(List<Process> processes) {
        this.processes = processes;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void executeProcesses(Boolean contextStream) throws Exception {


        // YOUR ALGORITHM HERE :)
        int size = processes.size();
        int[] waitingTime = new int[size];
        int[] turnaroundTime = new int[size];
        
        //function for waiting time
        for (int i = 0; i < size; i++) {
            waitingTime [i] = processes.get(i).getWaitingTime();
        }

        //*********************************************************
        // THIS BLOCK OF CODE DOES NOT COMPILE IT IS COMMENTED OUT
        // SO THE REST OF THE PROJECT CAN STILL OPERATE
        // JO MUST FIX THIS IF SHE WISHES TO HAVE IT UNCOMMENTED:
        //calculate waiting time
//        waitingTimes[0] = 0;
//        int currentWait = 0;
//        for (int i = 1; i < waitingTimes.size(); i++) {
//            currentWait += processes.get(i - 1).getTraceTape(0);
//            waitingTimes[i] = currentWait}
        //*********************************************************

        //function for turn around time
        for (int i = 0; i < size; i++) {
            turnaroundTime [i] = processes.get(i).getTurnaroundTime();
        }
        

        this.processesExecuted = true;
    }


        /**
         * @inheritDoc
         */
        @Override
        public PerformanceMetricGenerator generatePerformanceMetrics() throws Exception {
            if (!processesExecuted) {
                throw new Exception("Must complete processes before generating metrics");
            }
            return new PerformanceMetricGenerator("Multilevel Feedback Queue", processes,cpu);
        }

    }
