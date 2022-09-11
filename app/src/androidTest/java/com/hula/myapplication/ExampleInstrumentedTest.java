package com.hula.myapplication;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
//        // Context of the app under test.
//        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
//        assertEquals("com.p47_70.myapplication", appContext.getPackageName());
        String ss = "http://ec2-3-143-124-172.us-east-2.compute.amazonaws.com/events/event_search?category=0&date=4%2C5&limit=10&neighborhood=0&offset=0&user_id=6597";
        String ss1= "http://ec2-3-143-124-172.us-east-2.compute.amazonaws.com/events/event_search?date=1%252C2&offset=0&user_id=6597&limit=10&neighborhood=0&category=1";
        Uri parse = Uri.parse(ss1);
        Log.i("TAG", "useAppContext: "+parse.getQueryParameter("date"));
        System.out.println(parse.getQueryParameter("date"));
    }
}