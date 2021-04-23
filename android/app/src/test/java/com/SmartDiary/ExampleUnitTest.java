package com.SmartDiary;

import com.SmartDiary.Utils.WebViewUtils.MyStringUtils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void run2(){
        run();
    }


    public String run(){
        String temp= MyStringUtils.JSON_2_String("{\"choiceList\":[{\"key\":\"1619096245588\",\"textValue\":\"1\"},{\"key\":\"1619096251629\",\"textValue\":\"\"}],\"type\":\"checkbox\",\"id\":\"choiceTemplate\"}");
        String temp2="\""+temp+"\"";
        System.out.println(temp2);

        String res=temp2;
        if(res==null || res.equals(""))
            return "";

        if(res.charAt(0)=='"')
            res=res.substring(1);

        if(res.charAt(res.length()-1)=='"')
            res=res.substring(0,res.length()-1);

        System.out.println(res);
        return res;
    }
}