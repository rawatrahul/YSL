package com.rrawat.ysl_hackathon;

/**
 * Created by RRawat on 26-08-2015.
 */
public class LoginField {
    private Long id;
    private String name;
    private String type;
    private String size;
    private String maxLength;
    private String required;
    private Option[] option;
    private Validation[] validation;
    private String value;
    private Boolean valueEditable;
    private String prefix;
    private String suffix;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public String getMaxLength() {
        return maxLength;
    }
    public void setMaxLength(String maxLength) {
        this.maxLength = maxLength;
    }
    public String getRequired() {
        return required;
    }
    public void setRequired(String required) {
        this.required = required;
    }
    public Option[] getOption() {
        return option;
    }
    public void setOption(Option[] option) {
        this.option = option;
    }
    public Validation[] getValidation() {
        return validation;
    }
    public void setValidation(Validation[] validation) {
        this.validation = validation;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public Boolean getValueEditable() {
        return valueEditable;
    }
    public void setValueEditable(Boolean valueEditable) {
        this.valueEditable = valueEditable;
    }
    public String getPrefix() {
        return prefix;
    }
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
    public String getSuffix() {
        return suffix;
    }
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }


}
