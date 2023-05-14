package information;

public class ProjectInfo {
    public String projectName;
    public String description;
    public String img;
    public String priority;
    public String status;
    public String startTime;
    public String finishTime;
    public int projectId;

    public ProjectInfo(String projectName, String description, String img, String priority, String status, String startTime,
            String finishTime, int projectId) {
        this.projectName = projectName;
        this.description = description;
        this.img = img;
        this.priority = priority;
        this.status = status;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.projectId = projectId;
    }
}
