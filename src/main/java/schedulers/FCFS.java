package schedulers;

import java.util.List;

/**
 * First-Come First Served CPU Scheduling Algorithm
 * @author Joette Damo
 */
public class FCFS implements SchedulerInterface {

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