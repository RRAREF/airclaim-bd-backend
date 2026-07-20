package com.airclaimbd.airclaimbackend.dto;

public class DashboardResponse {

    private long totalUsers;
    private long totalLostReports;
    private long totalFoundReports;
    private long matchedReports;
    private long pendingReports;

    public DashboardResponse() {
    }

    public DashboardResponse(
            long totalUsers,
            long totalLostReports,
            long totalFoundReports,
            long matchedReports,
            long pendingReports
    ) {
        this.totalUsers = totalUsers;
        this.totalLostReports = totalLostReports;
        this.totalFoundReports = totalFoundReports;
        this.matchedReports = matchedReports;
        this.pendingReports = pendingReports;
    }

    public long getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(long totalUsers) {
        this.totalUsers = totalUsers;
    }

    public long getTotalLostReports() {
        return totalLostReports;
    }

    public void setTotalLostReports(long totalLostReports) {
        this.totalLostReports = totalLostReports;
    }

    public long getTotalFoundReports() {
        return totalFoundReports;
    }

    public void setTotalFoundReports(long totalFoundReports) {
        this.totalFoundReports = totalFoundReports;
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