package com.SmartDiary.Utils.WebViewUtils;


//import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.text.StringEscapeUtils;
public class MyStringUtils {

    public static String JSON_2_String(String jsonString ){
        String res= StringEscapeUtils.unescapeJson(jsonString);
        return res;
    }
}
