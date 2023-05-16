package utilities;

import java.util.List;

/**
 * Simulates the behavior of the cpu to
 * provide better snapshots and a more logical progression
 * @author Aodhan Bower
 */
public interface CpuInterface {

    /**
     *
     * @return the process that is currently on the cpu
     */
    public Process getOnCpu();

    /**
     *
     * @return true if the cpu has a process on it, false if not
     */
    public boolean isInUse();

    /**
     *
     * @return a copy of the list of ready/waiting processes
     */
    public List<Process> getReadyProcesses();

    /**
     *
     * @return a copy of the list of processes in io
     */
    public List<Process> getIoProcesses();


    /**
     * checks if the cpu is idle
     * @return true if idle, false if not
     */
    public boolean idle();

    /**
     * returns the cpu utilization
     * @return the cpu utilization
     */
    public float getCpuUtilization();

    /**
     *
     * @param process the process to be added
     * @return true if the process has been added, false if it already existed
     */
    public boolean addProcess(Process process);


    /**
     * sets the process list to the provided list of processes
     * WARNING: will replace the current process list so anything previously added
     * willl be lost when using this method
     * @param processList list of processes to put in cpu ready queue.
     */
    public void setProcessList(List<Process> processList);

    /**
     * checks to see if all processes are complete
     * @return true if all processes are complete, false if not
     */
    public boolean checkCompletion() throws Exception;

    /**
     * ticks the cpu forward by one time increment
     * decrements all io processes and any cpu burst by 1
     * moves cpu process to io if the burst is complete
     * also moves any fully completed processes out of io.
     * @return true if the cpu process finished this tick, false otherwise
     */
    public boolean cpuTick() throws Exception;

    /**
     * puts the process on the cpu if it is available,
     * but will not preempt anyhting already on it
     * @param process the process to add to the cpu
     */
    public boolean sendToCpuIfEmpty(Process process);

    /**
     * moves the current cpu process back to ready, replaces it with process
     * @param process the process to put on the cpu
     */
    public void preemptOnCpu(Process process);

    /**
     * puts the process at the front of the ready queue on the cpu
     * preempting anything that is already on it
     */
    public void nextProcessToCpuPreempt();

    /**
     * puts the process at the front of the ready queue on the cpu
     * if the cpu is idle.
     * @return true if the next process was added to the cpu, false if not
     */
    public boolean nextProcessToCpuIfIdle();

    /**
     * returns the time elapsed from the cpu
     * @return time elapsed
     */
    public int getTime();


}
