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

    public Process getOnCpu(){
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


    public boolean cpuTick(){
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
                onCpu = null;
                printSnapshot("Process on cpu complete!");
                return true;
            }
        }
        for(int i = 0; i < ioProcesses.size(); i++){
            if(ioProcesses.get(i).decrementActiveProcess()){
                if(ioProcesses.get(i).getCompletion()){
                    completedProcesses.add(ioProcesses.get(i));
                }
                else{
                    readyProcesses.add(ioProcesses.get(i));
                    ioProcesses.remove(ioProcesses.get(i));
                }
            }
        }
        return false;
    }

    public void sendToCpuIfEmpty(Process process){
        if(onCpu != null){
        }
        else{
            onCpu = process;
            readyProcesses.remove(process);
            printSnapshot("Process added to cpu!");
        }
    }

    public void preemptOnCpu(Process process){
        readyProcesses.add(onCpu);
        onCpu = process;
        readyProcesses.remove(process);
        printSnapshot("Process Preempted from cpu!");
    }

    private void printSnapshot(String title){
        System.out.println("---------------");
        System.out.println(title);
        System.out.println("Time Elapsed: " + time);
        System.out.println("CPU Utilization: " + useTime + "/" + time);
        System.out.print("Process On Cpu: ");
        if (onCpu != null) {
            System.out.println(getOnCpu().getProcessName());
        }
        else{
            System.out.println("None");
        }
        System.out.println("---------------");
        System.out.println("IO Processes (Time Remaining)");
        for(Process p : getIoProcesses()){
            System.out.println(p.getProcessName() + " (" + p.getActiveProcessTimeRemaining() + ")");
        }
        System.out.println("---------------");
        System.out.println("Waiting Processes");
        for(Process p : getReadyProcesses()){
            System.out.println(p.getProcessName());
        }
        System.out.println("---------------");
    }

}
