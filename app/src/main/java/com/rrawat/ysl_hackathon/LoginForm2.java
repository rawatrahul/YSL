package com.rrawat.ysl_hackathon;

/**
 * Created by RRawat on 09-09-2015.
 */
public class LoginForm2 {
    private Integer mfaTimeout;
    private String formType;
    private LoginFormRow2[] row;

    public Integer getMfaTimeout() {
        return mfaTimeout;
    }

    public void setMfaTimeout(Integer mfaTimeout) {
        this.mfaTimeout = mfaTimeout;
    }

    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    public LoginFormRow2[] getRow() {
        return row;
    }

    public void setRow(LoginFormRow2[] row) {
        this.row = row;
    }
}
