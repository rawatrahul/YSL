package com.rrawat.ysl_hackathon;

/**
 * Created by RRawat on 09-09-2015.
 */
public class LoginField2 {
    private String id;
    private String name;
    private Boolean isOptional;
    private String value;
    private String valueEditable;
    private Integer maxLength;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsOptional() {
        return isOptional;
    }

    public void setIsOptional(Boolean isOptional) {
        this.isOptional = isOptional;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValueEditable() {
        return valueEditable;
    }

    public void setValueEditable(String valueEditable) {
        this.valueEditable = valueEditable;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
