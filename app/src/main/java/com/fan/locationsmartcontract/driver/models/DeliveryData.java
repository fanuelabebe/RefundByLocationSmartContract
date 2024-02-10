package com.fan.locationsmartcontract.driver.models;

public class DeliveryData {
    private String name;
    private String incentive;
    private String date;
    private String compliance;

    public DeliveryData() {
    }

    public DeliveryData(String name, String incentive, String date, String compliance) {
        this.name = name;
        this.incentive = incentive;
        this.date = date;
        this.compliance = compliance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIncentive() {
        return incentive;
    }

    public void setIncentive(String incentive) {
        this.incentive = incentive;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCompliance() {
        return compliance;
    }

    public void setCompliance(String compliance) {
        this.compliance = compliance;
    }
}
