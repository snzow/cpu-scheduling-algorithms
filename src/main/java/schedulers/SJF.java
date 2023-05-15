package schedulers;

import java.util.*;

<<<<<<< HEAD
import utilities.CpuInterface;
=======
import utilities.Cpu;
>>>>>>> 3bb2eceed33d29e84c4caa323ca3c67f49da2f24
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
        PriorityQueue<Process> readyQueue = new PriorityQueue<>(Comparator.comparing(o -> o.getTraceTape().get(o.getTapeCursor())));
        Queue<Process> ioQueue = new LinkedList<>();
        readyQueue.addAll(processes);
        int time = 0;

        while (!readyQueue.isEmpty() || !ioQueue.isEmpty()) {
            if (!readyQueue.isEmpty()) {
                Process current = readyQueue.poll();
                if (current.getTapeCursor() == 0) {
                    current.setStartTime(time);
                }
                time += current.getTraceTape().get(current.getTapeCursor());
                current.nextTapeItem();
                if (!current.getCompletion()) {
                    ioQueue.add(current);
                    current.setIoLogTime(time);
                } else {
                    current.setExitTime(time);
                }
            }
            int timeChange = checkIOQ(time, ioQueue, readyQueue);
            if (timeChange > 0) {
                time += timeChange;
            }
        }
        this.processesExecuted = true;
    }

    private int checkIOQ(int currentTime, Queue<Process> ioQueue, Queue<Process> readyQueue) {
        int timeDelta = -1;
        if (!ioQueue.isEmpty()) {
            Process current = ioQueue.peek();
            if (currentTime - current.getIoLogTime() >= current.getTraceTape().get(current.getTapeCursor())) {
                current.nextTapeItem();
                readyQueue.add(ioQueue.poll());
            } else if (readyQueue.isEmpty()) {
                timeDelta = current.getTraceTape().get(current.getTapeCursor()) - currentTime;
                current.nextTapeItem();
                readyQueue.add(ioQueue.poll());
            }
        }
        return timeDelta;
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