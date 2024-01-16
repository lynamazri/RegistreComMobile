package com.example.registrecommobile;

public class Request {
    private int id;
    private String companyName;
    private String address;
    private String phoneNumber;
    private String emailAddress;
    private String activityType;
    private String fullName;
    private String dateOfBirth;
    private String nationality;
    private String idNumber;
    private int userId;
    private String state;

    public Request() {

    }

    public Request(int id, String companyName, String address, String phoneNumber,
                   String emailAddress, String activityType, String fullName,
                   String dateOfBirth, String nationality, String idNumber, String state, int userId) {
        this.id = id;
        this.companyName = companyName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.activityType = activityType;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
        this.idNumber = idNumber;
        this.userId = userId;
        this.state = state;
    }

    public Request(String companyName, String address, String phoneNumber,
                   String emailAddress, String activityType, String fullName,
                   String dateOfBirth, String nationality, String idNumber, String state, int userId) {
        this.companyName = companyName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.activityType = activityType;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
        this.idNumber = idNumber;
        this.userId = userId;
        this.state = state;
    }

    public int getReqId() {
        return id;
    }

    public void setReqId(int id) {
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public String getNationality() {
        return nationality;
    }
    public String getIdNumber() {
        return idNumber;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
