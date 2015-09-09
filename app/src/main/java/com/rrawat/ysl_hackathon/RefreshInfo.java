/*
* Copyright (c) 2015 Yodlee, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of Yodlee, Inc.
* Use is subject to license terms.
*/
package com.rrawat.ysl_hackathon;

public class RefreshInfo {

       private long statusCode;
       private String statusMessage;
       private String refreshStatus;

       public String getRefreshStatus() {
              return refreshStatus;
       }

       public void setRefreshStatus(String refreshStatus) {
              this.refreshStatus = refreshStatus;
       }

       private long lastRefreshed;
       private long lastRefreshAttempt;
       private String nextRefreshScheduled;
       public String getStatusMessage() {
              return statusMessage;
       }
       public void setStatusMessage(String statusMessage) {
              this.statusMessage = statusMessage;
       }

       public long getStatusCode() {
              return statusCode;
       }

       public void setStatusCode(long statusCode) {
              this.statusCode = statusCode;
       }

       public long getLastRefreshed() {
              return lastRefreshed;
       }

       public void setLastRefreshed(long lastRefreshed) {
              this.lastRefreshed = lastRefreshed;
       }

       public long getLastRefreshAttempt() {
              return lastRefreshAttempt;
       }

       public void setLastRefreshAttempt(long lastRefreshAttempt) {
              this.lastRefreshAttempt = lastRefreshAttempt;
       }

       public String getNextRefreshScheduled() {
              return nextRefreshScheduled;
       }
       public void setNextRefreshScheduled(String nextRefreshScheduled) {
              this.nextRefreshScheduled = nextRefreshScheduled;
       }
       
       public String toString()
       {
              StringBuilder refreshInfo = new StringBuilder("");
              refreshInfo.append(" => statusCode = " + statusCode).append(" => statusMessage = " + statusMessage);
              return refreshInfo.toString();
       }
}

