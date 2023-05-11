package schedulers;

import java.util.List;

/**
 * Priority CPU Scheduling Algorithm
 * @author Bella Mester
 */
public class Priority implements SchedulerInterface {

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