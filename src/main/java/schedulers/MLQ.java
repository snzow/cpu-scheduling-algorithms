package schedulers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

import utilities.Cpu;
import utilities.CpuInterface;
import utilities.PerformanceMetricGenerator;
import utilities.Process;

/**
 * Multilevel Queue CPU Scheduling Algorithm
 * @author Aodhan Bower
 */
public class MLQ implements SchedulerInterface {

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

    private List<Process> foreground;
    private List<Process> background;
    private int quantum;

    private CpuInterface cpu;

    public MLQ(ArrayList<Process> foregroundList, ArrayList<Process> backgroundList, int quantum){
        this.foreground = foregroundList;
        this.background = backgroundList;
        this.processes = new ArrayList<>();
        for(Process p : foreground){
            processes.add(p);
        }
        for(Process p : background){
            processes.add(p);
        }
        this.quantum = quantum;

    }
    @Override
    public void loadProcesses(List<Process> processes) {
        this.processes = processes;

    }

    /**
     * @inheritDoc
     */
    @Override
    public void executeProcesses(Boolean contextStream) throws Exception {

        // YOUR ALGORITHM HERE :)


        this.cpu = new Cpu(true);
        HashMap<Process, List<Process>> processListMap = new HashMap<>();
        for(Process p : foreground){
            cpu.addProcess(p);
            processListMap.put(p,foreground);
        }
        for(Process p : background){
            cpu.addProcess(p);
            processListMap.put(p,background);
        }
        cpu.sendToCpuIfEmpty(foreground.remove(0));
        int quantumCount = 0;
        while(!cpu.checkCompletion()){
            for(int i = 0; i < 50; i++){
                if(i < 40){
                    cpu.cpuTick();
                    if (foreground.size() == 0){
                    }
                    else if(cpu.idle()){
                        cpu.sendToCpuIfEmpty(foreground.remove(0));
                    }
                    else if(quantum == quantumCount){
                        Process temp = cpu.getOnCpu();
                        cpu.preemptOnCpu(foreground.remove(0));

                        processListMap.get(temp).add(temp);
                    }
                    if(quantumCount >= quantum){
                        quantumCount = -1;
                    }
                    quantumCount++;

                }
                else{
                    if(background.size() == 0){
                        cpu.cpuTick();
                    }
                    else{
                        if(i == 40){
                            if(!cpu.idle()){
                                Process temp = cpu.getOnCpu();
                                processListMap.get(temp).add(temp);
                            }
                            cpu.preemptOnCpu(background.remove(0));
                            cpu.cpuTick();
                        }
                        else{
                            if(cpu.sendToCpuIfEmpty(background.remove(0))){
                            }
                            cpu.cpuTick();
                        }
                    }

                }
                for(Process p : cpu.getReadyProcesses()){
                    if(!processListMap.get(p).contains(p)){
                        processListMap.get(p).add(p);
                    }

                }
                if(cpu.checkCompletion()){
                    break;
                }
            }
        }
        this.processesExecuted = true;
    }

    /**
     * @inheritDoc
     */
    @Override
    public PerformanceMetricGenerator generatePerformanceMetrics() throws Exception {
        if (!processesExecuted) {
            throw new Exception("Must complete processes before generating metrics");
        }
        return new PerformanceMetricGenerator("Multilevel Queue", processes,cpu);
    }

}