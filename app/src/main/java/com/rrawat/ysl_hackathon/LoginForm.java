package com.rrawat.ysl_hackathon;

/**
 * Created by RRawat on 26-08-2015.
 */
public class LoginForm {

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private long id;
    private String help;
    private String forgetPasswordURL;
    private LoginFormRow[] row;
    public String getHelp() {
        return help;
    }
    public void setHelp(String help) {
        this.help = help;
    }
    public String getForgetPasswordURL() {
        return forgetPasswordURL;
    }
    public void setForgetPasswordURL(String forgetPasswordURL) {
        this.forgetPasswordURL = forgetPasswordURL;
    }
    public LoginFormRow[] getRow() {
        return row;
    }
    public void setRow(LoginFormRow[] row) {
        this.row = row;
    }


}