package com.oop.iotapp;

public class LightData extends Device{
    private Long status, lightIntensity, autoMode, timerMode;
    private String timerStart, timerStop, name;

    public LightData(){
        this.setAddress("0.0.0.0");
        this.setPort(0L);
        this.name = "#";
        this.status = 0L;
        this.lightIntensity = 0L;
        this.autoMode = 0L;
        this.timerMode = 0L;
        this.timerStart = "#";
        this.timerStop = "#";
    }

    public LightData(String name, Long status, Long lightIntensity, Long autoMode, Long timerMode, String timerStart, String timerStop) {
        this.name = name;
        this.status = status;
        this.lightIntensity = lightIntensity;
        this.autoMode = autoMode;
        this.timerMode = timerMode;
        this.timerStart = timerStart;
        this.timerStop = timerStop;
    }

    public LightData(String address, Long port, Long status, Long lightIntensity, Long autoMode, Long timerMode, String timerStart, String timerStop, String name) {
        super(address, port);
        this.status = status;
        this.lightIntensity = lightIntensity;
        this.autoMode = autoMode;
        this.timerMode = timerMode;
        this.timerStart = timerStart;
        this.timerStop = timerStop;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getLightIntensity() {
        return lightIntensity;
    }

    public void setLightIntensity(Long lightIntensity) {
        this.lightIntensity = lightIntensity;
    }

    public Long getAutoMode() {
        return autoMode;
    }

    public void setAutoMode(Long autoMode) {
        this.autoMode = autoMode;
    }

    public Long getTimerMode() {
        return timerMode;
    }

    public void setTimerMode(Long timerMode) {
        this.timerMode = timerMode;
    }

    public String getTimerStart() {
        return timerStart;
    }

    public void setTimerStart(String timerStart) {
        this.timerStart = timerStart;
    }

    public String getTimerStop() {
        return timerStop;
    }

    public void setTimerStop(String timerStop) {
        this.timerStop = timerStop;
    }
}
