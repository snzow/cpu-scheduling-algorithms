package testSchedulers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import schedulers.Priority;
import utilities.PerformanceMetricGenerator;
import utilities.Process;

import java.util.Collections;
import java.util.List;

class PriorityTest {

    @Test
    public void priorityTest() throws Exception {
        Process p1 = new Process("P1", List.of(29, 50, 23, 48, 20, 42, 34, 37));
        Process p2 = new Process("P2", List.of(32, 45, 15, 29, 44, 24, 20, 47));
        Process p3 = new Process("P3", List.of(29, 31, 21, 45, 20, 33, 42, 26));
        Process p4 = new Process("P4", List.of(37, 26, 29, 40, 48, 29, 37, 23));
        Process p5 = new Process("P5", List.of(30, 23, 34, 43, 28, 27, 39, 22));
        Process p6 = new Process("P6", List.of(42, 19, 22, 25, 34, 30, 26, 45));
        Process p7 = new Process("P7", List.of(35, 30, 41, 37, 43, 24, 38, 21));
        Process p8 = new Process("P8", List.of(47, 21, 26, 42, 38, 31, 39, 29));
        List<Process> processes = List.of(p1, p2, p3, p4, p5, p6, p7, p8);

        Priority priority = new Priority();
        priority.loadProcesses(processes);
        priority.executeProcesses(false);
        PerformanceMetricGenerator pmg = priority.generatePerformanceMetrics();

        assertEquals(83.03, pmg.getCpuUtilization(), 0.01);

        assertIterableEquals(List.of(171, 177, 174, 182, 213, 227, 190, 190), Collections.singleton(pmg.getWaitingTimeAverage()));
        assertEquals(190.5, pmg.getWaitingTimeAverage(), 0.01);

        assertIterableEquals(List.of(396, 604, 566, 666, 522, 442, 518, 499), Collections.singleton(pmg.getTurnaroundTimeAverage()));
        assertEquals(526.625, pmg.getTurnaroundTimeAverage(), 0.01);

        assertIterableEquals(List.of(1, 6, 10, 18, 21, 37, 48, 62), Collections.singleton(pmg.getResponseTimeAverage()));
        assertEquals(25.375, pmg.getResponseTimeAverage(), 0.01);
    }
}