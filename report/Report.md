# Part 4: Report 

## Table of Contents

## Introduction

### Attribution Requirement:

Part 1: Designing the Experiment - A.Bower

Part 2: Java Implementation - A.Bower and N.Trimmer

Part 3. Output and Testing - A.Bower and N.Trimmer

Part 4. Report and Version Control for Project Board - J.Damo

### Algorithm Multilevel Queue - A.Bower

A multi-level queue scheduling algorithm partitions the ready queue into several separate queues. The processes are permanently
assigned to one queue, generally based on some property of the process, such as memory size, process priority or process type.
The advantages of multi-level queue cpu scheduling are the following: low scheduling overhead, efficient allocation of CPU time, 
fairness as to fair allocation of CPU time, customizable, priorization, and preemption. On the other hand the disadvantages are 
that some processes may starve for CPU if some higher priority queues are never becoming empty or else it is inflexible in nature.

### Algorithm Multilevel Feedback Queue - A.Bower

Cpu scheduling as like multi-level queue (MLQ) scheduling but in this process can move between the queues. And much more
efficient than multi-level queue scheduling. The advantage of multi-level feedback queue scheduling are the following: flexible,
allows different processes to move between different queues, and prevents starvation by moving a process that waits too long for 
the lower priority queue to the higher priority queue. On the other hand disadvantages of multi-level feedback queue scheduling
are the it produces more CPU overheads and the most complex algorithm.

### Algorithm Round Robin - B.Mester

Round Robin is a CPU scheduling algorithm where each process is assigned a fixed time slot in a cyclic way. It is basically the preemptive
version of First Come First Serve CPU scheduling algorithm and thus focuses on Time Sharing technique. While performing a round robin scheduling
a particular time quantun is allotted to different jobs. Each process get a chance to reschedule after a particular quantum time in this
scheduling; usually with larger waiting time and response time. The disadvantages of using round robin if slicing time of operating system
is low, the processor output will be reduced. Round robin performance heavily depends on time quantum. Priorities cannot be set for the
processes.

### Algorithm Priority Scheduling - B.Mester

Lower the number, higher is the priority in which if a newer process arrive, that is having a higher priority than the currently running
process, then the currently running process is preempted. Processes with the same priority are executed on a first come first served
basis. Therefore, priority can be based on memory requirements, time requirements, or any other resource requirement. Also priority can
be decided on the ratio of average I/O to average CPU burst time. Advantage is that priority based scheduling ensures high-priority processes
are executed first which leads to faster completion of critical tasks. The two main disadvantages of priority scheduling are priority inversion
in which occurs when a low-priority process holds a resource that a high-priority process requires that may cause delays of execution and the
second disadvantage is starvation in which if the system is heavily loaded with high-priority processes, low-priority processes may never
get a change to execute.

### Algorithm Shortest Job First (SJF) nonpreemptive - N.Trimmer

Basically when a the CPU finish the current job it will select the shortest job in the queue to execute next. Therefore, it selects the waiting
process with the smallest execution time to execute next. Shortest job first has the advantage of having a minimum average waiting time among
all scheduling algorithms. It is a Greedy algorithm which may cause starvation if shorter processes keep coming; this problem can be solved
using the concept of ageing. Advantage of SJF is reduces the average waiting time and it is generally used for long term scheduling. On the
other hand the disadvantages are that SJF may cause very long turn around times or starvation; and other disadvantage it is complicated to 
predict the length of the upcoming CPU request.

### Algorithm Shortest Remaining Time First (SRTF) - N.Trimmer

The shortest remaining time first algorithm the process having the smallest amount of time remaining until completion is selected first to
execute. So, basically in SRTF the processes are scheduled according to the shortest remaining time. Processes will always run until they 
complete or a new process is added that requires a smaller amount of time. SRTF a dynamic algorithm meaning it can adapt to changes in the 
arrival time and burst time of processes. Where it can re-evaluate the remaining burst time of each process and schedules the process with
the shortest remaining time. Advantages if SRTF are the short processes are handled very quickly. On the other hand, the disadvantage is
that like shortest job first it has the potential for process starvation with long processes can be held off indefinitely if short processes
are continually added.

### Algorithm First Come First Serve (FCFS) - J.Damo

The first come first serve scheduling algorithm in the process which arrive first gets executed first. It is implemented by using the FIFO 
queue which is the simplest scheduling algorithm in that the FIFO simply queues processes in the order they arrive in the ready queue.
Advantage for FCFS is that it is simple and easy to understand with a process with less execution time suffers ie waiting time is often
quite long. Disadvantages is that important jobs with higher priority have to wait along with not guaranting good response time. Other
disadvantages of FCFS are the average waiting time and turn around time is oten quite lone with lower CPU and device utilization.

### Report Preemptive FCFS in report section - J.Damo

(2 to 3 paragraphs)

## UML Diagram - A.Bower

![UML Diagram](img/uml.png)

## Final Results - tables, plots

![UML Diagram](img/uml.png)
![CPU Utilization Bar Graph](img/cpuBar.png)
![Waiting Time Bar Graph](img/waitingBar.png)
![Response Time Bar Graphn](img/responseBar.png)
![Averages Bar Graph](img/averagesBar.png)

## Discussion and Tables for:

<ul>
  <li>U (CPU utilization</li>
  <li>Tw (waiting times)</li>
  <li>Ttr (turnaround times)</li>
  <li>Rt (response time)</li>
  <li>All processes and averages for each algorithm</li>
</ul>

## Plots

<ul>
  <li>CPU utilization data for algorithms</li>
  <li>Average Waiting time: data for all algorithms</li>
  <li>Average Turnaround time: data for all algorithms</li>
  <li>Average Response Time: data for all algorithms</li>
</ul>

## Discussion on what is best solution to implement.

Discussion starts here.

