package information;

public class Plants {
    public String plantName;
    public String cropName;
    public int plantingMonth;
    public int plantingTime;
    public String weatherConditions;
    public String uses;
    public String notes;
    public String plantImg;

    public Plants(String plantName, String cropName, int plantingMonth, int plantingTime,
            String weatherConditions, String uses, String notes, String plantImg){
                this.plantName = plantName;
                this.cropName = cropName;
                this.plantingMonth = plantingMonth;
                this.plantingTime = plantingTime;
                this.weatherConditions = weatherConditions;
                this.uses = uses;
                this.notes = notes;
                this.plantImg = plantImg;
    }
    
}
