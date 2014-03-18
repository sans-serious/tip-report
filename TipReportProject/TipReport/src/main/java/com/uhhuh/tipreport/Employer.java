package com.uhhuh.tipreport;

/**
 * Created by olibrooks on 3/18/14.
 */
public class Employer {
    private int _id;
    private String employer_type;
    private String city;
    private String state;
    private String country;
    private String address;
    private String price;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getEmployer_type() {
        return employer_type;
    }

    public void setEmployer_type(String employer_type) {
        this.employer_type = employer_type;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
