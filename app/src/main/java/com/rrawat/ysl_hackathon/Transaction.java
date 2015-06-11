package com.rrawat.ysl_hackathon;

/**
 * Created by RRawat on 11-06-2015.
 */
public class Transaction {

    String CONTAINER;
    Long id;
    Double amount;
    String baseType;
    String category;
    String description;
    String date;
    Long accountId;
    public String getCONTAINER() {
        return CONTAINER;
    }
    public void setCONTAINER(String cONTAINER) {
        CONTAINER = cONTAINER;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public String getBaseType() {
        return baseType;
    }
    public void setBaseType(String baseType) {
        this.baseType = baseType;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public Long getAccountId() {
        return accountId;
    }
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }


}
