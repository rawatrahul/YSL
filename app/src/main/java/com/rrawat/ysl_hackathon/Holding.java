/*
* Copyright (c) 2015 Yodlee, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of Yodlee, Inc.
* Use is subject to license terms.
*/
package com.rrawat.ysl_hackathon;

public class Holding {
	
	 private Long costBasis;
	 private Long cusipNumber;
	 private Long dailyChange;
	 private String description;
	 private String exchange;
	 private String holdingType;
	 private String isin;
	 private Long price;
	 private Long quantity;
	 private String symbol;
	 private Long value;

	public Long getCostBasis() {
		return costBasis;
	}

	public void setCostBasis(Long costBasis) {
		this.costBasis = costBasis;
	}

	public Long getCusipNumber() {
		return cusipNumber;
	}

	public void setCusipNumber(Long cusipNumber) {
		this.cusipNumber = cusipNumber;
	}

	public Long getDailyChange() {
		return dailyChange;
	}

	public void setDailyChange(Long dailyChange) {
		this.dailyChange = dailyChange;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getHoldingType() {
		return holdingType;
	}

	public void setHoldingType(String holdingType) {
		this.holdingType = holdingType;
	}

	public String getIsin() {
		return isin;
	}

	public void setIsin(String isin) {
		this.isin = isin;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}
}
