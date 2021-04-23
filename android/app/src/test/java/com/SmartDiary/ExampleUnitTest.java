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
    public void run(){
        System.out.println(MyStringUtils.JSON_2_String("{\"choiceList\":[{\"key\":\"1619096245588\",\"textValue\":\"1\"},{\"key\":\"1619096251629\",\"textValue\":\"\"}],\"type\":\"checkbox\",\"id\":\"choiceTemplate\"}"));
    }
}