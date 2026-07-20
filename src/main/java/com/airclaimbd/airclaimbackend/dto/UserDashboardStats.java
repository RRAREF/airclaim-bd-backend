package com.airclaimbd.airclaimbackend.dto;

public class UserDashboardStats {

    private long myLostReports;
    private long myFoundReports;
    private long matchedReports;
    private long pendingReports;

    public UserDashboardStats() {
    }

    public long getMyLostReports() {
        return myLostReports;
    }

    public void setMyLostReports(long myLostReports) {
        this.myLostReports = myLostReports;
    }

    public long getMyFoundReports() {
        return myFoundReports;
    }

    public void setMyFoundReports(long myFoundReports) {
        this.myFoundReports = myFoundReports;
    }

    public long getMatchedReports() {
        return matchedReports;
    }

    public void setMatchedReports(long matchedReports) {
        this.matchedReports = matchedReports;
    }

    public long getPendingReports() {
        return pendingReports;
    }

    public void setPendingReports(long pendingReports) {
        this.pendingReports = pendingReports;
    }
}