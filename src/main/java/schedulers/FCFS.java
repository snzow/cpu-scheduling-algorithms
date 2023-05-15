package schedulers;

import java.util.List;

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
     /*   List<Process> processes = Utility.deepCopy(process);
        Utility.sortArrival(processes);
        int timeline = 0;
        double avg_waiting = 0;
        double avg_turnaround = 0;
        for (Row i : processes) {
            if (timeline < i.getArrivalTime()) {
                timeline = i.getArrivalTime();
            }
            i.setStartTime(timeline);
            timeline += i.getBurstTime();
            i.setFinishTime(timeline);
            i.setWaitingTime(i.getStartTime() - i.getArrivalTime());
            avg_waiting += i.getWaitingTime();
            i.setTurnaroundTime(i.getBurstTime() + i.getWaitingTime());
            avg_turnaround += i.getTurnaroundTime();

        

        this.processesExecuted = true;

      */
    }


        /**
         * @inheritDoc
         */
        @Override
        public PerformanceMetricGenerator generatePerformanceMetrics() throws Exception {
            if (!processesExecuted) {
                throw new Exception("Must complete processes before generating metrics");
            }
            return new PerformanceMetricGenerator("Multilevel Queue", processes);
        }

    }
