/*
* Copyright (c) 2015 Yodlee, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of Yodlee, Inc.
* Use is subject to license terms.
*/
package com.rrawat.ysl_hackathon;

public class CobrandContext
{

    private Long cobrandId;
    private String applicationId;
    private Session session;


    static class Session
    {
        public Session() {
        }

        private String cobSession;

        public String getCobSession() {
            return cobSession;
        }

        public void setCobSession(String cobSession) {
            this.cobSession = cobSession;
        }


    }

    public Session getSession() {
        return session;
    }



    public void setSession(Session session) {
        this.session = session;
    }



    public void setCobrandId(Long cobrandId) {
        this.cobrandId = cobrandId;
    }



    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }



    public Long getCobrandId()
    {
        return cobrandId;
    }



    public String getApplicationId() {
        return applicationId;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder(this.cobrandId+this.applicationId);
        System.out.println("CoBrand : toString() = " + sb);
        return new String(sb);
    }

}
