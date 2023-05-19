package schedulers;

import java.util.*;

import utilities.CpuInterface;
import utilities.Cpu;
import utilities.PerformanceMetricGenerator;
import utilities.Process;

/**
 * Shortest Job First CPU Scheduling Algorithm
 * @author Nick Trimmer
 */
public class SJF implements SchedulerInterface {

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
    public void executeProcesses(Boolean contextStream) throws Exception {
        this.processes.sort(Comparator.comparing(Process::getActiveProcessTimeRemaining));
        ArrayList<Process> activeList = new ArrayList<>();
        for(Process p : processes){
            activeList.add(p);
        }
        cpu = new Cpu();
        cpu.setProcessList(activeList);
        cpu.sendToCpuIfEmpty(activeList.remove(0));
        while(!cpu.checkCompletion()){
            cpu.cpuTick();
            activeList.sort(Comparator.comparing(Process::getActiveProcessTimeRemaining));
            if(cpu.idle() && activeList.size() > 0){
                cpu.sendToCpuIfEmpty(activeList.remove(0));
            }
            else if(activeList.size() > 0 && activeList.get(0).getActiveProcessTimeRemaining() < cpu.getOnCpu().getActiveProcessTimeRemaining() ){
                Process temp = cpu.getOnCpu();
                cpu.preemptOnCpu(activeList.remove(0));
                activeList.add(temp);
            }



            for(Process p : cpu.getReadyProcesses()){
                if(!activeList.contains(p)){
                    activeList.add(p);
                }
            }
        }
        processesExecuted = true;

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