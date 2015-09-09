package com.rrawat.ysl_hackathon;

/**
 * Created by RRawat on 10-06-2015.
 */

public class UserContext {


    private Session session;


    static class Session
    {
        public Session() {
        }

        private String userSession;

        public String getUserSession() {
            return userSession;
        }

        public void setUserSession(String userSession) {
            this.userSession = userSession;
        }


    }


    public Session getSession() {
        return session;
    }


    public void setSession(Session session) {
        this.session = session;
    }

}
