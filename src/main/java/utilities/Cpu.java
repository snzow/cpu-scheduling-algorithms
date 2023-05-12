package utilities;

import java.util.*;

public class Cpu {
    private Map<Process, Integer> processMap;
    private List<Process> ioProcesses;
    private Process onCpu;
    private Queue<Process> processQueue;


    public Cpu(){
        processMap = new HashMap<>();
        ioProcesses = new ArrayList<>();
        onCpu = null;
        processQueue = new LinkedList<>();
    }



}
