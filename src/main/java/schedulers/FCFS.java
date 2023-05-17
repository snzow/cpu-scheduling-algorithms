package schedulers;

import java.util.List;
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
    public void executeProcesses(Boolean contextStream) {

        // YOUR ALGORITHM HERE :)
    int size = processes.size();
    int[] waitingTime = new int[size];
    int[] turnaroundTime = new int[size];
        
        //function for waiting time
        for (int i = 0; i < size; i++) {
            waitingTime [] = processes.get(i).getWaitingTime();
        }
        
        //function for turn around time
        for (int i = 0; i < size; i++) {
            turnaroundTime [] = processes.get(i).getTurnaroundTime();
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
