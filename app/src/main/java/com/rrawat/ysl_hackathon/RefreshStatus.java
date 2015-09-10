/*
* Copyright (c) 2015 Yodlee, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of Yodlee, Inc.
* Use is subject to license terms.
*/
package com.rrawat.ysl_hackathon;

public class RefreshStatus {

	private Long providerAccountId;
	private RefreshInfo refreshInfo;
	LoginForm2 loginForm;

	public LoginForm2 getLoginForm() {
		return loginForm;
	}

	public void setLoginForm(LoginForm2 loginForm) {
		this.loginForm = loginForm;
	}

	public Long getProviderAccountId() {
		return providerAccountId;
	}
	public void setProviderAccountId(Long providerAccountId) {
		this.providerAccountId = providerAccountId;
	}
	public RefreshInfo getRefreshInfo() {
		return refreshInfo;
	}
	public void setRefreshInfo(RefreshInfo refreshInfo) {
		this.refreshInfo = refreshInfo;
	}
	
	public String toString()
	{
		StringBuilder refreshStatus = new StringBuilder("");
		refreshStatus.append("providerAccountId = " + providerAccountId).append(refreshInfo.toString());
		return refreshStatus.toString();
	}
	
	
	
}
