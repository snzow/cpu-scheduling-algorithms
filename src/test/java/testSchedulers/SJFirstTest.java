package testSchedulers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import schedulers.SJF;
import utilities.PerformanceMetricGenerator;
import utilities.Process;
import java.util.List;

class SJFirstTest {

    @Test
    public void sjfTest() throws Exception {
        Process p1 = new Process("P1", List.of(4));
        Process p2 = new Process("P2", List.of(2));
        Process p3 = new Process("P3", List.of(6));
        Process p4 = new Process("P4", List.of(3));
        p1.setArrivalTime(0);
        p2.setArrivalTime(2);
        p3.setArrivalTime(3);
        p4.setArrivalTime(13);
        SJF sjf = new SJF();
        sjf.loadProcesses(List.of(p1, p2, p3, p4));
        sjf.executeProcesses(false);
        PerformanceMetricGenerator pmg = sjf.generatePerformanceMetrics();

        assertEquals(93.75, pmg.getCpuUtilization());
        assertEquals(1.25, pmg.getResponseTimeAverage(), 4);
        assertEquals(1.25, pmg.getWaitingTimeAverage(), 4);
        assertEquals(5, pmg.getTurnaroundTimeAverage(), 4);
    }

    @Test
    public void srtfTest() throws Exception {
        Process p1 = new Process("P1", List.of(10));
        Process p2 = new Process("P2", List.of(3));
        Process p3 = new Process("P3", List.of(7));
        Process p4 = new Process("P4", List.of(4));
        p1.setArrivalTime(0);
        p2.setArrivalTime(1);
        p3.setArrivalTime(3);
        p4.setArrivalTime(5);
        SJF srtf = new SJF();
        srtf.loadProcesses(List.of(p1, p2, p3, p4));
        srtf.executeProcessesPreemptive(false);
        PerformanceMetricGenerator pmg = srtf.generatePerformanceMetrics();

        assertEquals(100.0, pmg.getCpuUtilization(), 5);
        assertEquals(4.25, pmg.getResponseTimeAverage(), 4);
        assertEquals(4.75, pmg.getWaitingTimeAverage(), 4);
        assertEquals(10.75, pmg.getTurnaroundTimeAverage(), 4);
    }
}