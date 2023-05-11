package schedulers;

import java.util.List;

/**
 * Round Robin CPU Scheduling Algorithm
 * @author Bella Mester
 */
public class RR implements SchedulerInterface {

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