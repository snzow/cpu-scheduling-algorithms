package utilities;

import java.util.*;

public class Cpu implements CpuInterface {
    private List<Process> ioProcesses;
    private Process onCpu;
    private List<Process> readyProcesses;

    private List<Process> completedProcesses;
    private int time;

    private int useTime;

    private boolean inUse;


    public Cpu(){
        this.ioProcesses = new ArrayList<>();
        this.onCpu = null;
        this.readyProcesses = new ArrayList<>();
        this.completedProcesses = new ArrayList<>();
        this.inUse = false;
        this.useTime = 0;
        this.time = 0;

    }

    public Process getOnCpu() {
        return onCpu;
    }

    public boolean isInUse() {
        return inUse;
    }

    public List<Process> getReadyProcesses(){
        return List.copyOf(readyProcesses);
    }

    public List<Process> getIoProcesses(){ return List.copyOf(ioProcesses);}

    public boolean addProcess(Process process){
        if(!readyProcesses.contains(process)){
            readyProcesses.add(process);
            return true;
        }
        else{
            return false;
        }
    }


    public void cpuTick(){
        time++;
        if(onCpu != null){
            useTime++;
            if(onCpu.decrementActiveProcess()) {
                if(onCpu.getCompletion()){
                    completedProcesses.add(onCpu);
                }
                else{
                    ioProcesses.add(onCpu);
                }
            }
        }
        for(Process p : ioProcesses){
            if(p.decrementActiveProcess()){
                if(p.getCompletion()){
                    completedProcesses.add(p);
                }
                else{
                    readyProcesses.add(p);
                    ioProcesses.remove(p);
                }
            }
        }


    }

    public void preemptOnCpu(Process process){
        readyProcesses.add(onCpu);
        onCpu = process;
        readyProcesses.remove(process);
    }





}
