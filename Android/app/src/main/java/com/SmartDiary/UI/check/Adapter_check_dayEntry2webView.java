package com.SmartDiary.UI.check;

import android.annotation.SuppressLint;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.SmartDiary.R;
import com.SmartDiary.pojo.DayEntry;

//这个类的功能就是根据dayEntry返回一个展示改天数据的webView
public class Adapter_check_dayEntry2webView {

    DayEntry dayEntry;
    /*构造函数:
    * 1.加载内部变量dayEntry;
    * 2.给webView加载最基本的html;
    * 3.给webView添加JavaScriptInterface;
    * 4.给webView执行动态js注入;*/

    public Adapter_check_dayEntry2webView(View view, DayEntry dayEntry) {
        this.dayEntry=dayEntry;

        WebView webView_check_dayEntryDisplay=view.findViewById(R.id.webView_check_dayEntryDisplay);
        webView_check_dayEntryDisplay.getSettings().setDefaultTextEncodingName("utf-8") ;
        webView_check_dayEntryDisplay.getSettings().setJavaScriptEnabled(true);
        webView_check_dayEntryDisplay.addJavascriptInterface(this,"androidObject");

        load_basicHTML(webView_check_dayEntryDisplay);
        load_separate_js(webView_check_dayEntryDisplay);
    }

    //给webView加载最基本的html;
    private void load_basicHTML(WebView webView_check_dayEntryDisplay) {
        String table_view="<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Document</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div id='div1' style=\"background-color: aqua;\">\n" +
                "    \n" +
                "    </div>\n" +
                "    <button style=\"width:100%\" id=\"btn_new\">新建选择</button>\n" +
                "    <br>\n" +
                "\n" +
                "    <input type=\"radio\" name=\"radio1\" id=\"rbtn1\">单选\n" +
                "    <br>\n" +
                "    <input type=\"radio\" name=\"radio1\" id=\"rbtn2\">多选\n" +
                "    <script>\n" +
                "        // const getObj=window.androidObject.getAndroidFormat();\n" +
                "        // let realObj=JSON.parse(getObj);\n" +
                "        const obj={\n" +
                "            template_id:1,//1,2,3\n" +
                "            choice:{\n" +
                "                \"11\":\"睡得很早\",\n" +
                "                \"22\":\"11.30左右\",\n" +
                "                \"33\":\"1点左右\",\n" +
                "                \"44\":\"没有睡觉\"\n" +
                "            },\n" +
                "            step:5,\n" +
                "            choiceType:\"1\"\n" +
                "            \n" +
                "        };\n" +
                "\n" +
                "\n" +
                "        let jsonStr=JSON.stringify(obj);\n" +
                "        let jsonObj=JSON.parse(jsonStr);//json字符串转对象\n" +
                "        console.log(obj);\n" +
                "        let div1=document.getElementById(\"div1\");\n" +
                "        for(let i in jsonObj[\"choice\"]){\n" +
                "            console.log(i);\n" +
                "           let ele= document.createElement(\"div\");\n" +
                "           let input=document.createElement(\"input\");\n" +
                "           input.setAttribute(\"type\",\"text\");\n" +
                "           input.value=jsonObj[\"choice\"][i];\n" +
                "           let button2=document.createElement(\"button\");\n" +
                "           button2.textContent=\"删除\";\n" +
                "            //先给input添加onchange事件\n" +
                "            input.onchange=function(){\n" +
                "                jsonObj[\"choice\"][i]=input.value;\n" +
                "                // console.log(jsonObj);\n" +
                "            }\n" +
                "            \n" +
                "            button2.onclick=function(){\n" +
                "                ele.parentNode.removeChild(ele);\n" +
                "                delete jsonObj[\"choice\"][i];\n" +
                "                // console.log(ele);\n" +
                "                // console.log(jsonObj);\n" +
                "            }\n" +
                "           ele.append(input);\n" +
                "           ele.append(button2);\n" +
                "           div1.appendChild(ele);\n" +
                "        }\n" +
                "        let rbtn1=document.getElementById(\"rbtn1\");\n" +
                "        let rbtn2=document.getElementById(\"rbtn2\");\n" +
                "        if(jsonObj[\"choiceType\"]===\"1\"){\n" +
                "            rbtn1.checked=true;\n" +
                "        }\n" +
                "        else{\n" +
                "            rbtn2.checked=true;\n" +
                "        }\n" +
                "        rbtn1.onclick=function(){\n" +
                "            if(rbtn1.checked==true)\n" +
                "                jsonObj[\"choiceType\"]=\"1\";\n" +
                "            else\n" +
                "                jsonObj[\"choiceType\"]=\"2\";\n" +
                "                // console.log(jsonObj);\n" +
                "        }\n" +
                "        rbtn2.onclick=function(){\n" +
                "            if(rbtn1.checked==true)\n" +
                "                jsonObj[\"choiceType\"]=\"1\";\n" +
                "            else\n" +
                "                jsonObj[\"choiceType\"]=\"2\";\n" +
                "                // console.log(jsonObj);\n" +
                "        }\n" +
                "        let btnNew=document.querySelector(\"#btn_new\");\n" +
                "        btnNew.addEventListener(\"click\",function(){\n" +
                "            let ele= document.createElement(\"div\");\n" +
                "           let input=document.createElement(\"input\");\n" +
                "           input.setAttribute(\"type\",\"text\");\n" +
                "           input.value=\"新建选择\";\n" +
                "           let button2=document.createElement(\"button\");\n" +
                "           button2.textContent=\"删除\";\n" +
                "           ele.append(input);\n" +
                "           ele.append(button2);\n" +
                "           div1.appendChild(ele);\n" +
                "           let lastIndex=Object.keys(jsonObj[\"choice\"]).length-1;\n" +
                "           let key=Object.keys(jsonObj[\"choice\"])[lastIndex];\n" +
                "           let index=parseInt(key)+1;\n" +
                "           let lastKey=index+\"\";\n" +
                "            //给两个按钮加上点击事件\n" +
                "            button2.onclick=function(){\n" +
                "                ele.parentNode.removeChild(ele);\n" +
                "                delete jsonObj[\"choice\"][lastKey];\n" +
                "                // console.log(ele);\n" +
                "                // console.log(jsonObj);\n" +
                "            }\n" +
                "            //先给input添加onchange事件\n" +
                "            input.onchange=function(){\n" +
                "                jsonObj[\"choice\"][lastKey]=input.value;\n" +
                "                // console.log(jsonObj);\n" +
                "            }\n" +
                "           jsonObj[\"choice\"][lastKey]=\"新建选择\";\n" +
                "        //    console.log(jsonObj);\n" +
                "        });\n" +
                "            window.getJsFormat=function(){\n" +
                "                return JSON.stringify(jsonObj);\n" +
                "            };\n" +
                "            // console.log(getFormat());\n" +
                "    </script>\n" +
                "</body>\n" +
                "</html>";

        webView_check_dayEntryDisplay.loadDataWithBaseURL(null, table_view, "text/html", "utf-8", null);

    }

    //给webView执行动态js注入;
    private void load_separate_js(WebView webView_check_dayEntryDisplay) {
    }

    @JavascriptInterface
    public void a(){};
}
