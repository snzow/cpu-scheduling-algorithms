package schedulers;

import java.util.List;

/**
 * Multilevel Feedback Queue CPU Scheduling Algorithm
 * @author Aodhan Bower
 */
public class MLFQ implements SchedulerInterface {

    private List<Process> processes;

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
    public void executeProcesses() {

    }
}