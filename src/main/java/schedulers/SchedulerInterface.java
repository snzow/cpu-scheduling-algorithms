package schedulers;

import java.util.List;

/**
 * Interface for simulated CPU schedulers
 * @author Nick Trimmer
 */
public interface SchedulerInterface {

    /**
     * Loads all process objects for simulated scheduling
     * @param processes the collection of processes to schedule
     */
    void loadProcesses(List<Process> processes);

    /**
     * Begins the execution of the scheduler
     */
    void executeProcesses();
}
