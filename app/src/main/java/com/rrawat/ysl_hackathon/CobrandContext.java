package com.rrawat.ysl_hackathon;


public class CobrandContext
{

    private Long cobrandId;
    private String applicationId;
    private Session session;

    public void setCobrandId(Long cobrandId) {
        this.cobrandId = cobrandId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    private Session getCobrandConversationCredentials() {
        return session;
    }


    public String getSession()
    {
        return this.getCobrandConversationCredentials().getExternalSessionID();
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
