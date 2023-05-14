package information;

public class TaskInfo {
    public int taskId;
    public String taskName;
    public String description;
    public String priority;
    public String status;
    public String startTime;
    public String finishTime;

    public TaskInfo(int taskId, String taskName, String description, String priority, String status, String startTime,
            String finishTime) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }
}
