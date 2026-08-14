package com.example.placementmanagement.dto.response;

public class DashboardSummaryDto {

    private long totalStudents;
    private long activeCompanies;
    private long openJobs;
    private long totalApplications;
    private long shortlistedStudents;
    private long selectedStudents;
    private long placedStudents;
    private double placementRatePercentage;
    private double averagePackage;
    private double highestPackage;

    public DashboardSummaryDto() {
    }

    public long getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(long totalStudents) {
        this.totalStudents = totalStudents;
    }

    public long getActiveCompanies() {
        return activeCompanies;
    }

    public void setActiveCompanies(long activeCompanies) {
        this.activeCompanies = activeCompanies;
    }

    public long getOpenJobs() {
        return openJobs;
    }

    public void setOpenJobs(long openJobs) {
        this.openJobs = openJobs;
    }

    public long getTotalApplications() {
        return totalApplications;
    }

    public void setTotalApplications(long totalApplications) {
        this.totalApplications = totalApplications;
    }

    public long getShortlistedStudents() {
        return shortlistedStudents;
    }

    public void setShortlistedStudents(long shortlistedStudents) {
        this.shortlistedStudents = shortlistedStudents;
    }

    public long getSelectedStudents() {
        return selectedStudents;
    }

    public void setSelectedStudents(long selectedStudents) {
        this.selectedStudents = selectedStudents;
    }

    public long getPlacedStudents() {
        return placedStudents;
    }

    public void setPlacedStudents(long placedStudents) {
        this.placedStudents = placedStudents;
    }

    public double getPlacementRatePercentage() {
        return placementRatePercentage;
    }

    public void setPlacementRatePercentage(double placementRatePercentage) {
        this.placementRatePercentage = placementRatePercentage;
    }

    public double getAveragePackage() {
        return averagePackage;
    }

    public void setAveragePackage(double averagePackage) {
        this.averagePackage = averagePackage;
    }

    public double getHighestPackage() {
        return highestPackage;
    }

    public void setHighestPackage(double highestPackage) {
        this.highestPackage = highestPackage;
    }
}
