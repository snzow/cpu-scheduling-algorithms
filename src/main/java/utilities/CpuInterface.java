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
     *
     * @param process the process to be added
     * @return true if the process has been added, false if it already existed
     */
    public boolean addProcess(Process process);

    /**
     * ticks the cpu forward by one time increment
     * decrements all io processes and any cpu burst by 1
     * moves cpu process to io if the burst is complete
     * also moves any fully completed processes out of io.
     * @return true if the cpu process finished this tick, false otherwise
     */
    public boolean cpuTick();

    /**
     * puts the process on the cpu if it is available,
     * but will not preempt anyhting already on it
     * @param process the process to add to the cpu
     */
    public void sendToCpuIfEmpty(Process process);

    /**
     * moves the current cpu process back to ready, replaces it with process
     * @param process the process to put on the cpu
     */
    public void preemptOnCpu(Process process);


}
