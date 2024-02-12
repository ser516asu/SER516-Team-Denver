package org.example.JavaTaigaCode.models;

public class MilestoneDTO {
    private Integer milestoneID;
    private String milestoneName;
    private Double totalSumValue;
    private Double partialSumValue;

    public Integer getMilestoneID() {
        return milestoneID;
    }

    public void setMilestoneID(Integer milestoneID) {
        this.milestoneID = milestoneID;
    }

    public String getMilestoneName() {
        return milestoneName;
    }

    public void setMilestoneName(String milestoneName) {
        this.milestoneName = milestoneName;
    }

    public Double getTotalSumValue() {
        return totalSumValue;
    }

    public void setTotalSumValue(Double totalSumValue) {
        this.totalSumValue = totalSumValue;
    }

    public Double getPartialSumValue() {
        return partialSumValue;
    }

    public void setPartialSumValue(Double partialSumValue) {
        this.partialSumValue = partialSumValue;
    }

    public MilestoneDTO() {
    }

    public MilestoneDTO(Integer milestoneID, String milestoneName, Double totalSumValue) {
        this.milestoneID = milestoneID;
        this.milestoneName = milestoneName;
        this.totalSumValue = totalSumValue;
    }
}
