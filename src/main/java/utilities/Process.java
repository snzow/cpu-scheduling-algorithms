package utilities;

import java.util.List;

/**
 * Simulated Process class, providing data storage, access, and calculations
 * of a simulated process, given its trace tape
 * @author Nick Trimmer
 * @author Aodhan Bower
 */
public class Process {



    /**
     * The name of the process
     */
    private final String processName;
    
    
    /**
     * Stores CPU bursts mixed with IO times.
     * Structured as:
     * CPU burst, I/O time, CPU burst, I/O time, CPU burst,
     * I/O time,…….., last CPU burst
     */
    private List<Integer> traceTape;

    /**
     * Tracks the distance through the tape the process has gotten
     */
    private int tapeCursor;
    
    /**
     * Total CPU burst time measured in the trace tape
     */
    
    private int activeProcess;
    private boolean complete;
    private int totalCPUBurstTime;
    
    /**
     * Total IO time measured in the trace tape
     */
    private int totalIOTime;
    
    /**
     * Priority level of the process, if applicable
     * Initializes to 0
     */
    private int priority;

    /**
     * Time when the process arrives at the CPU
     */
    private int arrivalTime;
    private Boolean arrivalUpdated;
    
    /**
     * Time when the CPU begins the process
     */
    private int startTime;
    private Boolean startUpdated;
    
    /**
     * The point when the CPU finishes the process
     */
    private int exitTime;
    private Boolean exitUpdated;

    /**
     * The amount of time it takes for the CPU to respond
     * to a request by the process
     */
    private int responseTime;
    
    /**
     * The total time the process spent in the ready state
     * waiting for the CPU
     */
    private int waitingTime;
    
    /**
     * The time elapsed between the arrival of the process
     * and its completion
     */
    private int turnaroundTime;

    /**
     * Process constructor
     * @param processName the name of the process
     * @param traceTape the realtime process trace tape
     *                  detailing CPU bursts and IO waits
     */
    public Process(String processName, List<Integer> traceTape) {
        this.processName = processName;
        this.traceTape = traceTape;
        this.tapeCursor = 0;
        this.activeProcess = traceTape.get(0);
        this.priority = 0;
        this.complete = false;
        this.arrivalUpdated = false;
        this.startUpdated = false;
        this.exitUpdated = false;
        calculateBurstTimes();
    }

    /**
     * Calculates CPU burst times and IO wait times
     * from the trace tape
     */
    private void calculateBurstTimes() {
        for (int i = 0; i < traceTape.size(); i++) {
            if (i % 2 == 0) {
                this.totalCPUBurstTime += traceTape.get(i);
            } else {
                this.totalIOTime += traceTape.get(i);
            }
        }
    }

    /**
     * Progresses the tape cursor to signify that the most recent item has been completed.
     * updates the activeProcess to the new item
     * @return true if there is another item, false if the process is complete.
     */
    public boolean nextTapeItem(){
        if(tapeCursor + 1 == traceTape.size()){
            complete = true;
            return false;
        }
        else{
            activeProcess = traceTape.get(++tapeCursor);
            return true;
        }
    }

    public boolean decrementActiveProcess(){
        if(--activeProcess == 0){
           nextTapeItem();
           return true;
        }
        return false;

    }

    public boolean getCompletion(){
        return complete;
    }
    
    



    /**
     * Retrieves the process name
     * @return the process name
     */
    public String getProcessName() {
        return processName;
    }

    /**
     * Retrieves the process trace tape
     * @return the process trace tape
     */
    public List<Integer> getTraceTape() {
        return traceTape;
    }

    /**
     * Retrieves the process priority level
     * @return the process priority level
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Retrieves the total CPU burst time measured in the
     * trace tape
     * @return the process total CPU burst time
     */
    public int getTotalCPUBurstTime() {
        return totalCPUBurstTime;
    }

    /**
     * Retrieves the total IO wait time measured
     * in the trace tape
     * @return the process total IO wait time
     */
    public int getTotalIOTime() {
        return totalIOTime;
    }

    /**
     * Updates the process trace tape
     * @param traceTape the new process trace tape
     */
    public void setTraceTape(List<Integer> traceTape) {
        this.traceTape = traceTape;
    }

    /**
     * Updates the process priority level
     * @param priority the new process priority level
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }



    /**
     * Retrieves the time the process arrived at the CPU
     * Prerequisite is the arrival time being set first
     * @return the time the process arrived at the CPU
     * @throws Exception if prerequisite is not met, throw
     */
    public int getArrivalTime() throws Exception {
        if (!arrivalUpdated) {
            throw new Exception("This field has not been set yet.");
        }
        return arrivalTime;
    }

    /**
     * Retrieves the time the CPU begins the process
     * Prerequisite is the start time being set first
     * @return the process start time
     * @throws Exception if the prerequisite is not met, throw
     */
    public int getStartTime() throws Exception {
        if (!startUpdated) {
            throw new Exception("This field has not been set yet.");
        }
        return startTime;
    }

    /**
     * Retrieves the time process finished executing
     * Prerequisite is the exit time being set first
     * @return the process exit time
     * @throws Exception if the prerequisite is not met, throw
     */
    public int getExitTime() throws Exception {
        if (!exitUpdated) {
            throw new Exception("This field has not been set yet.");
        }
        return exitTime;
    }

    /**
     * Sets the time the process arrives at the CPU
     * @param arrivalTime the time the process arrives at the CPU
     */
    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
        arrivalUpdated = true;
    }

    /**
     * Sets the time the CPU begins the process
     * @param startTime the process start time
     */
    public void setStartTime(int startTime) {
        this.startTime = startTime;
        startUpdated = true;
    }

    /**
     * Sets the time the process is completed by the CPU
     * @param exitTime the time the process is completed by the CPU
     */
    public void setExitTime(int exitTime) {
        this.exitTime = exitTime;
        exitUpdated = true;
    }



    /**
     * Generates process execution statistics
     * Prerequisites are arrival time being set
     * and exit time being set
     * @throws Exception If either prerequisite is not met, throw
     */
    public void generatePerformanceStatistics() throws Exception {
        if (!arrivalUpdated || !exitUpdated || !startUpdated) {
            throw new Exception("One or more fields have not been set yet.");
        }
        setResponseTime();
        setWaitingTime();
        setTurnaroundTime();
    }

    /**
     * Retrieves the time elapsed between the start of the process
     * and the arrival of the process
     * @return the CPU response time
     */
    public int getResponseTime() {
        return responseTime;
    }

    /**
     * Retrieves the time the process waited in the
     * ready state
     * @return the process waiting time
     */
    public int getWaitingTime() {
        return waitingTime;
    }

    /**
     * Retrieves the amount of time between process arrival
     * at the CPU and its completion
     * @return the process turnaround time
     */
    public int getTurnaroundTime() {
        return turnaroundTime;
    }

    /**
     * Sets the response time, the amount of time it takes for the
     * CPU to respond to a request made by the process
     */
    private void setResponseTime() {
        this.responseTime = this.startTime - this.arrivalTime;
    }

    /**
     * Sets the total time the process spent in the ready state
     * waiting for the CPU
     */
    private void setWaitingTime() {
        this.waitingTime = this.exitTime - this.arrivalTime - this.totalCPUBurstTime;
    }

    /**
     * Sets the time elapsed between the arrival of the process
     * and its completion
     */
    private void setTurnaroundTime() {
        this.turnaroundTime = this.exitTime - this.arrivalTime;
    }



    /**
     * Resets all process metrics
     */
    public void reset() {
        this.arrivalTime = 0;
        this.responseTime = 0;
        this.waitingTime = 0;
        this.turnaroundTime = 0;
        this.exitTime = 0;
        this.arrivalUpdated = false;
        this.exitUpdated = false;
    }
}