package com.example.registrecommobile;

public class Request {
    private int id;
    private String companyName;
    private String address;
    private String activityType;
    private String personalInfo;
    private int userId;

    public Request() {
        // Empty constructor required by SQLite
    }

    public Request(String companyName, String address, String activityType, String personalInfo, int userId) {
        this.companyName = companyName;
        this.address = address;
        this.activityType = activityType;
        this.personalInfo = personalInfo;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(String personalInfo) {
        this.personalInfo = personalInfo;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
