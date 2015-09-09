package com.rrawat.ysl_hackathon;

/**
 * Created by RRawat on 26-08-2015.
 */
public class LoginFormRow {

    private long id;
    private String label;
    private LoginField[] field;
    private String fieldRowChoice;
    private String form;
    private String help;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public LoginField[] getField() {
        return field;
    }
    public void setField(LoginField[] field) {
        this.field = field;
    }
    public String getFieldRowChoice() {
        return fieldRowChoice;
    }
    public void setFieldRowChoice(String fieldRowChoice) {
        this.fieldRowChoice = fieldRowChoice;
    }
    public String getForm() {
        return form;
    }
    public void setForm(String form) {
        this.form = form;
    }
    public String getHelp() {
        return help;
    }
    public void setHelp(String help) {
        this.help = help;
    }


}
