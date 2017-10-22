package thacks2.scheduler;

import java.util.Date;
import java.util.Comparator;

class Task implements Comparable<Task> {
    private int priority;
    private int duration; // in minutes
    private boolean segmentable;
    private String name;
    private Date deadline;

    // constructors
    Task() {
        this.priority = 15;
        this.duration = 10;
        this.segmentable = false;
        this.name = "Untitled Event";
        this.deadline = new Date();
        this.deadline.setTime(this.deadline.getTime() + 30 * 60 * 1000); // set default to half hour ahead
    }

    Task(Task task) {
        this.priority = task.getPriority();
        this.duration = task.getDuration();
        this.segmentable = task.getSegmentable();
        this.name = task.getName();
        this.deadline = task.getDeadline();
    }

    //Task(int priority, int duration, boolean segmentable, String name, Date deadline) { // set default values
    //    this.priority = priority;
    //    this.duration = duration;
    //    this.segmentable = segmentable;
    //    this.name = name;
    //    this.deadline = deadline;
    //}

    // accessor methods
    public int getPriority() {
        return this.priority;
    }

    public int getDuration() {
        return this.duration;
    }

    public boolean getSegmentable() {
        return this.segmentable;
    }

    public String getName() {
        return this.name;
    }

    public Date getDeadline() {
        return this.deadline;
    }

    // mutator methods
    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setSegmentable(boolean segmentable) {
        this.segmentable = segmentable;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public int compareTo(Task t) { return -(this.getPriority()- t.getPriority()); }

    public static Comparator<Task> TaskComparator
            = new Comparator<Task>() {

        public int compare(Task task1, Task task2) {

            return task1.compareTo(task2);

        }

    };
}