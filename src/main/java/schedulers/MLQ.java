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

    public MLQ(int quantum){
        this.quantum = quantum;

    }
    @Override
    public void loadProcesses(List<Process> processes) {
        this.processes = new ArrayList<>();
        this.foreground = new ArrayList<>();
        this.background = new ArrayList<>();
        for(Process p : processes){
            this.processes.add(p);
            if(processes.indexOf(p) < 4){
                foreground.add(p);
            }
            else{
                background.add(p);
            }
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public void executeProcesses(Boolean contextStream) throws Exception {
        //if contextstream we will print snapshots every time onCpu changes
        if(contextStream){
            this.cpu = new Cpu(true);
        }
        else{
            this.cpu = new Cpu();
        }

        //initialize a map that will store which queue each process belongs in.
        HashMap<Process, List<Process>> processListMap = new HashMap<>();
        for(Process p : foreground){
            cpu.addProcess(p);
            processListMap.put(p,foreground);
        }
        for(Process p : background){
            cpu.addProcess(p);
            processListMap.put(p,background);
        }

        //put the first item on the cpu and initialize quantum counter and time counter to 0
        cpu.sendToCpuIfEmpty(foreground.remove(0));
        int quantumCount = 0;
        int timeCounter = 0;

        //while all processes are not complete
        while(!cpu.checkCompletion()){
            //tick the cpu and increase the quantum
            cpu.cpuTick();
            quantumCount++;

            /* if the timecounter is below 40 we want to do a foreground process
            in line with our 80/20 split. but if foreground queue is empty we will
            skip over it for efficiency's sake */
            if(timeCounter < 40 && foreground.size() > 0){
                if(cpu.idle()){
                    cpu.sendToCpuIfEmpty(foreground.remove(0));
                }
                else if(quantumCount >= quantum){
                    Process temp = cpu.getOnCpu();
                    cpu.preemptOnCpu(foreground.remove(0));
                    quantumCount = 0;
                    processListMap.get(temp).add(temp);
                }
            }
            /*
            we are here if either it is the 10 ticks alloted to background
            OR foreground queue is empty. if there are items in the background queue
            we will use fcfs process on them
             */
            else if(background.size() > 0){
                if(cpu.idle()){
                    cpu.sendToCpuIfEmpty(background.remove(0));
                }
            }
            /* this if only activates if it is the background's turn but background queue
            is empty. in that case so long as foreground queue isnt empty we will go back to drawing from it
             */
            else if(foreground.size() > 0){
                if(cpu.idle()){
                    cpu.sendToCpuIfEmpty(foreground.remove(0));
                }
            }

            //increment time and reset it back to 0 if it is at 50
            timeCounter++;
            if(timeCounter == 50){
                timeCounter = 0;
            }
            //check the cpu ready processes and make sure that anything that returned from io is also
            //returned to our external queues.
            for(Process p : cpu.getReadyProcesses()){
                if(!processListMap.get(p).contains(p)){
                    processListMap.get(p).add(p);
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
        return new PerformanceMetricGenerator("Multilevel Queue", processes,cpu);
    }

}