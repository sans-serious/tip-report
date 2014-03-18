package com.uhhuh.tipreport;

/**
 * Created by olibrooks on 3/18/14.
 */
public class Job {

    private int _id;
    private String title;
    private float pay_rate;
    private int employer_id;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPay_rate() {
        return pay_rate;
    }

    public void setPay_rate(float pay_rate) {
        this.pay_rate = pay_rate;
    }

    public int getEmployer_id() {
        return employer_id;
    }

    public void setEmployer_id(int employer_id) {
        this.employer_id = employer_id;
    }
}
