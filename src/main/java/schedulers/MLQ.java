package schedulers;

import java.util.List;

/**
 * Multilevel Queue CPU Scheduling Algorithm
 * @author Aodhan Bower
 */
public class MLQ implements SchedulerInterface {

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