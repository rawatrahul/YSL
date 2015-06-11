package com.rrawat.ysl_hackathon;

/**
 * Created by RRawat on 10-06-2015.
 */

public class UserContext {

    private String cobrandId;
    private String applicationId;
    private Session session;


    private Session getCobrandConversationCredentials() {
        return session;
    }


    public String getSession()
    {
        return this.getCobrandConversationCredentials().getExternalSessionID();
    }


    public String getCobrandId()
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
