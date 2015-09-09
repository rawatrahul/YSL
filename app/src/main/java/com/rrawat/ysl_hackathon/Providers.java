package com.rrawat.ysl_hackathon;

/**
 * Created by RRawat on 26-08-2015.
 */

public class Providers {

    Provider[] provider;

    public Provider[] getProvider() {
        return provider;
    }

    public void setProvider(Provider[] provider) {
        this.provider = provider;
    }

    public String toString()
    {
        StringBuilder providers = new StringBuilder("");
        for (int i = 0; i<provider.length; i++)
        {
            providers.append(provider[i].getId()).append("=>").append("name=").append(provider[i].getName()).append("=>" + provider[i].getLoginUrl()).append("\n");
        }
        return providers.toString();
    }
    public String dataToString()
    {
        StringBuilder providers = new StringBuilder("");
        for (int i = 0; i<provider.length; i++)
        {
            providers.append(provider[i].getId()).append(" - ").append(provider[i].getName()).append("\n");
        }
        return providers.toString();
    }
}
