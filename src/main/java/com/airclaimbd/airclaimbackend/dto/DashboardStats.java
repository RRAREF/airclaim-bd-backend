package com.airclaimbd.airclaimbackend.dto;

public class DashboardStats {

    private long lostReports;
    private long foundReports;
    private long matchedReports;
    private long pendingReports;

    public DashboardStats() {
    }

    public DashboardStats(long lostReports, long foundReports,
                          long matchedReports, long pendingReports) {
        this.lostReports = lostReports;
        this.foundReports = foundReports;
        this.matchedReports = matchedReports;
        this.pendingReports = pendingReports;
    }

    public long getLostReports() {
        return lostReports;
    }

    public void setLostReports(long lostReports) {
        this.lostReports = lostReports;
    }

    public long getFoundReports() {
        return foundReports;
    }

    public void setFoundReports(long foundReports) {
        this.foundReports = foundReports;
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