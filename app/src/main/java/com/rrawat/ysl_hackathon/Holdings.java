/*
* Copyright (c) 2015 Yodlee, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of Yodlee, Inc.
* Use is subject to license terms.
*/
package com.rrawat.ysl_hackathon;

public class Holdings {

	Holding[] holding;

	public Holding[] getHolding() {
		return holding;
	}

	public void setHolding(Holding[] holding) {
		this.holding = holding;
	}
	
	public String toString()
	{
		StringBuilder holdings = new StringBuilder("");
		for (int i = 0; i<holding.length; i++)
		{
			holdings.append(holding[i].getHoldingType()).append("=>").append(holding[i].getDescription()).append("\n");
		}
		return holdings.toString();
	}

	public String dataToString() {
		StringBuilder holdings = new StringBuilder("");
		for (int i = 0; i<holding.length; i++)
		{
			holdings.append(holding[i].getHoldingType()).append(" - ").append(holding[i].getDescription()).append("\n");
		}
		return holdings.toString();
	}
}
