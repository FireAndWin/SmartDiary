package com.SmartDiary.Utils.WebViewUtils;


//import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.text.StringEscapeUtils;
public class MyStringUtils {

    public static String JSON_2_String(String jsonString ){
        String temp= StringEscapeUtils.unescapeJson(jsonString);
        String res=temp;
        if(res==null || res.equals(""))
            return "";

        if(res.charAt(0)=='"')
            res=res.substring(1);

        if(res.charAt(res.length()-1)=='"')
            res=res.substring(0,res.length()-1);

        return res;

    }
}
