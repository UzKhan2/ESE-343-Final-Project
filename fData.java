package com.example.fitpeak;

public class fData {

    private String personName;
    private String personActivity;
    private int activityData;

    public fData(String personName, String personActivity, int activityData)
    {
        this.personName = personName;
        this.personActivity = personActivity;
        this.activityData = activityData;
    }

    public String getPersonName()
    {
        return personName;
    }

    public void getPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonActivity() {
        return personActivity;
    }

    public void getPersonActivity(String personActivity) {
        this.personActivity = personActivity;
    }

    public int getActivityData() {
        return activityData;
    }

    public void setActivityData(int activityData) {
        this.activityData = activityData;
    }
}

