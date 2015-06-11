package com.rrawat.ysl_hackathon;

/**
 * Created by RRawat on 10-06-2015.
 */

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;


public class GSONParser
{
    private static final String fqcn = GSONParser.class.getName();
    private static Gson gson = new Gson();

    public static <T> Object handleJson(String json, Class<?> T) throws IOException
    {
        String mn = "handleJson(" + json + ", " + T.getCanonicalName()+" )";
        Log.d("rahul",fqcn + " :: " + mn );
        return gson.fromJson(json,T);
    }


    public static void main(String[] args)
    {
        // TODO Auto-generated method stub

    }

}
