import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import schedulers.*;
import utilities.Cpu;
import utilities.CpuInterface;
import utilities.Process;

public class Main {

    public static void main(String[] args) throws Exception {
        List<Process> processes = new ArrayList<>();
        loadProcesses(processes);

        // SAMPLE
        SJF sjf = new SJF();
        sjf.loadProcesses(processes);
        sjf.executeProcesses(false);
        System.out.println(sjf.generatePerformanceMetrics().toString());
    }

    /**
     * Loads process data specific to our simulation into an
     * ArrayList of Processes
     * @param processes the collection of processes to be loaded
     *                  with the data
     */
    private static void loadProcesses(List<Process> processes) {
        Process p1 = new Process("P1", Arrays.asList(5, 27, 3, 31, 5, 43, 4, 18, 6, 22, 4, 26, 3, 24, 4));
        Process p2 = new Process("P2", Arrays.asList(4, 48, 5, 44, 7, 42, 12, 37, 9, 76, 4, 41, 9, 31, 7, 43, 8));
        Process p3 = new Process("P3", Arrays.asList(8, 33, 12, 41, 18, 65, 14, 21, 4, 61, 15, 18, 14, 26, 5, 31, 6));
        Process p4 = new Process("P4", Arrays.asList(3, 35, 4, 41, 5, 45, 3, 51, 4, 61, 5, 54, 6, 82, 5, 77, 3));
        Process p5 = new Process("P5", Arrays.asList(16, 24, 17, 21, 5, 36, 16, 26, 7, 31, 13, 28, 11, 21, 6, 13, 3, 11, 4));
        Process p6 = new Process("P6", Arrays.asList(11, 22, 4, 8, 5, 10, 6, 12, 7, 14, 9, 18, 12, 24, 15, 30, 8));
        Process p7 = new Process("P7", Arrays.asList(14, 46, 17, 41, 11, 42, 15, 21, 4, 32, 7, 19, 16, 33, 10));
        Process p8 = new Process("P8", Arrays.asList(4, 14, 5, 33, 6, 51, 14, 73, 16, 87, 6));

        p1.setPriority(3);
        p2.setPriority(6);
        p3.setPriority(5);
        p4.setPriority(4);
        p5.setPriority(1);
        p6.setPriority(2);
        p7.setPriority(8);
        p8.setPriority(7);

        p1.setArrivalTime(0);
        p2.setArrivalTime(0);
        p3.setArrivalTime(0);
        p4.setArrivalTime(0);
        p5.setArrivalTime(0);
        p6.setArrivalTime(0);
        p7.setArrivalTime(0);
        p8.setArrivalTime(0);

        processes.add(p1);
        processes.add(p2);
        processes.add(p3);
        processes.add(p4);
        processes.add(p5);
        processes.add(p6);
        processes.add(p7);
        processes.add(p8);

        /*CpuInterface testCpu = new Cpu();
        for(Process p : processes){
            testCpu.addProcess(p);
        }
        testCpu.sendToCpuIfEmpty(p1);
        while(!testCpu.cpuTick()){
        }
        testCpu.sendToCpuIfEmpty(p2);
        while(!testCpu.cpuTick()){
        }
        testCpu.cpuTick();
        testCpu.cpuTick();
        testCpu.cpuTick();
        testCpu.cpuTick();
        testCpu.sendToCpuIfEmpty(p3);
        testCpu.cpuTick();
        testCpu.cpuTick();
        testCpu.cpuTick();
        testCpu.preemptOnCpu(p4);
        while(!testCpu.cpuTick()){

        }
        while(testCpu.getIoProcesses().contains(p1)){
            testCpu.cpuTick();
        }
        testCpu.cpuTick();
        testCpu.sendToCpuIfEmpty(p6);

         */


    }

}
