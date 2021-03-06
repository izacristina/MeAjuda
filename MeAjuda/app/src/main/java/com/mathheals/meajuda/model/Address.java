package com.mathheals.meajuda.model;

public class Address {
    private String CEP;
    private String description;
    private String district;
    private String county;
    private String state;

    public Address(String state, String county) {
        setState(state);
        setCounty(county);
    }

    public Address(String CEP, String description, String district, String county, String state) {
        setCEP(CEP);
        setDescription(description);
        setDistrict(district);
        setCounty(county);
        setState(state);
    }

    public String getCEP() {
        return CEP;
    }

    public String getDescription() {
        return description;
    }

    public String getDistrict() {
        return district;
    }

    public String getCounty() {
        return county;
    }

    public String getState() {
        return state;
    }

    private void setCEP(String CEP) {
        this.CEP = CEP;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    private void setDistrict(String district) {
        this.district = district;
    }

    private void setCounty(String county) {
        this.county = county;
    }

    private void setState(String state) {
        this.state = state;
    }


}
