package com.SmartDiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.SmartDiary.UI.Test_Fragment;
import com.SmartDiary.UI.check.Fragment_Check_OutFrame;
import com.SmartDiary.UI.record.Fragment_Record_OutFrame;
import com.SmartDiary.UI.start.Fragment_start_outFrame;
import com.SmartDiary.pojo.RecordEntry;
import com.SmartDiary.service.pojoService.CellEntryService;
import com.SmartDiary.service.pojoService.DayEntryService;
import com.SmartDiary.service.pojoService.RecordEntryService;
import com.SmartDiary.service.pojoService.RecordTemplateService;
import com.SmartDiary.service.pojoService.UserDataBaseSQLHelper;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {



    //================重写方法==================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init_view();
    }

    //================service层相关对象=============
    public DayEntryService dayEntryService;
    public RecordEntryService recordEntryService;
    public RecordTemplateService recordTemplateService;

    //================UI相关成员======================
    ViewPager viewPager_main;
    TabLayout tabLayout_main;

    //=================自定义方法============================
    /*初始化UI相关成员
    * */
    public void init_view(){
        //实际用:初始化pojoService层
        init_pojoService();
        viewPager_main=findViewById(R.id.viewPager_main);
        Adapter_viewPager_main adapter_viewPager_main=new Adapter_viewPager_main(getSupportFragmentManager());
        viewPager_main.setAdapter(adapter_viewPager_main);

        //初始化标签
        init_tab();
        //测试用:初始化pojoService层的相关对象
        //test_init_pojoService();

    }

    private void init_pojoService() {
        /*
        * 1.初始化SQLOpenHelper对象,
        * 2.添加测试数据*/

        //1:
        UserDataBaseSQLHelper.init(this);

        //2:添加记录项
        RecordEntryService recordEntryService=RecordEntryService.newInstance();
        //测试方法中添加记录项
        //test_init_recordEntryService();
        test_update();
    }

    private void test_init_recordEntryService() {
        test_add_choose();
        test_add_entertain();
        test_add_text();
        test_add_number();
        test_add_process();
    }

    private void test_update() {
        RecordEntry entry=RecordEntryService.newInstance().get_recordEntry_byId("txt001");
        entry.setRecord_view("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Document</title>\n" +
                "    <style>\n" +
                "            label,textarea {\n" +
                "            font-size: .8rem;\n" +
                "            letter-spacing: 1px;\n" +
                "        }\n" +
                "        textarea {\n" +
                "            padding: 1px;\n" +
                "            line-height: 1.5;\n" +
                "            border-radius: 5px;\n" +
                "            border: 1px solid #ccc;\n" +
                "            box-shadow: 1px 1px 1px #999;\n" +
                "        }\n" +
                "\n" +
                "        label {\n" +
                "            margin-bottom: 0px;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <label for=\"story\">请在下方输入文本:</label>\n" +
                "        <br>\n" +
                "        <textarea id=\"valueTxtInput\" name=\"story\"\n" +
                "          rows=\"8\" cols=\"30\" autocomplete=\"on\" placeholder=\"请输入文本\" autofocus=\"true\">\n" +
                "        </textarea>\n" +
                "    <script>\n" +
                "\n" +
                "        /*\n" +
                "        * 说明:\n" +
                "        * 这个是最基本的文本记录模板,\n" +
                "        * 不需要模板信息(所以写起来很简单)\n" +
                "        * 返回值是个json字符串:\n" +
                "        * 对应的js对象为:\n" +
                "        {\n" +
                "            \"txtContent\":\"这个地方就是实际记录的文本,它的键是txtContent\",\n" +
                "        }\n" +
                "        */\n" +
                "\n" +
                "        //获取记录值,直接放到控件上\n" +
                "        let json_recordValue=window.androidObject.getAndroidRecordValue();\n" +
                "        //json_recordValue=\"{\\\"txtContent\\\":\\\"undefinedffffff \\\\ndede \\\"}\";\n" +
                "        if(json_recordValue===\"\"){\n" +
                "            a={\n" +
                "            \"txtData\":\"\",\n" +
                "            };\n" +
                "            json_recordValue=JSON.stringify(a);\n" +
                "        };\n" +
                "        //let recordValue=JSON.parse(json_recordValue);\n" +
                "        let recordValue=eval(\"(\"+json_recordValue+\")\");\n" +
                "        document.write(recordValue.txtData);\n" +
                "        const returnObj={\n" +
                "            \"txtData\":\"\",\n" +
                "        };\n" +
                "\n" +
                "        //获取root dtextarea\n" +
                "        let valueTxtInput=document.getElementById('valueTxtInput');\n" +
                "        //valueTxtInput.value=recordValue[\"txtData\"];\n" +
                "        valueTxtInput.value=json_recordValue;\n" +
                "        returnObj['txtData']=valueTxtInput.value;\n" +
                "\n" +
                "        valueTxtInput.onchange=function(){\n" +
                "            returnObj['txtData']=valueTxtInput.value;\n" +
                "        }\n" +
                "        window.getJSRecordView=function(){\n" +
                "            returnObj['txtData']=valueTxtInput.value;\n" +
                "            return JSON.stringify(returnObj);\n" +
                "        }\n" +
                "    </script>\n" +
                "</body>\n" +
                "</html>\n" +
                "\n" +
                "\n");

        RecordEntryService.newInstance().update_recordEntry(entry);
    }

    private void test_add_process() {

        CellEntryService.newInstance().add_recordEntry("txt002");
       RecordEntry entry=new RecordEntry();
        entry.setId("txt002");
        entry.setTemplate_id("12");
        entry.setStatus(0);
        entry.setName("非每日记录项目");
        entry.setInfo("这个记录项不是每天都要记得,不会再记录界面显示");
        entry.setRecord_view("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Document</title>\n" +
                "    <style>\n" +
                "            label,textarea {\n" +
                "            font-size: .8rem;\n" +
                "            letter-spacing: 1px;\n" +
                "        }\n" +
                "        textarea {\n" +
                "            padding: 1px;\n" +
                "            line-height: 1.5;\n" +
                "            border-radius: 5px;\n" +
                "            border: 1px solid #ccc;\n" +
                "            box-shadow: 1px 1px 1px #999;\n" +
                "        }\n" +
                "\n" +
                "        label {\n" +
                "            margin-bottom: 0px;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <label for=\"story\">请在下方输入文本:</label>\n" +
                "        <br>\n" +
                "        <textarea id=\"story\" name=\"story\"\n" +
                "          rows=\"8\" cols=\"30\" autocomplete=\"on\" placeholder=\"请输入文本\" autofocus=\"true\">\n" +
                "        </textarea>\n" +
                "    <script>\n" +
                "\n" +
                "        /*\n" +
                "        * 说明:\n" +
                "        * 这个是最基本的文本记录模板,\n" +
                "        * 不需要模板信息(所以写起来很简单)\n" +
                "        * 返回值是个json字符串:\n" +
                "        * 对应的js对象为:\n" +
                "        {\n" +
                "            \"txtContent\":\"这个地方就是实际记录的文本,它的键是txtContent\",\n" +
                "        }\n" +
                "        */\n" +
                "\n" +
                "        //获取记录值,直接放到控件上\n" +
                "        const json_recordValue=window.androidObject.getAndroidRecordValue();\n" +
                "        json_recordValue=\"\";\n" +
                "        let android_recordValue={}\n" +
                "        if(json_recordValue===\"\"){\n" +
                "            android_recordValue[\"txtContent\"]=\"什么都没有记录\";\n" +
                "        }\n" +
                "        else{\n" +
                "            android_recordValue=JSON.parse(json_recordValue);\n" +
                "        }\n" +
                "\n" +
                "        const returnObj={};\n" +
                "\n" +
                "        //获取root dtextarea\n" +
                "        let root=document.getElementById('story');\n" +
                "        root.value=android_recordValue[\"txtContent\"];\n" +
                "        returnObj['txtContent']=root.value;\n" +
                "\n" +
                "        root.onchange=function(){\n" +
                "            returnObj['txtContent']=root.value;\n" +
                "        }\n" +
                "        window.getJSRecordView=function(){\n" +
                "            return JSON.stringify(returnObj);\n" +
                "        }\n" +
                "    </script>\n" +
                "</body>\n" +
                "</html>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n");
        RecordEntryService.newInstance().add_recordEntry(entry);
    }
    private void test_add_choose() {
        CellEntryService.newInstance().add_recordEntry("choice001");
        RecordEntry entry=new RecordEntry();
        entry.setId("choice001");
        entry.setTemplate_id("11");
        entry.setName("选择");
        entry.setInfo("可以用来记录一些和选择有关的,是个基本的记录项模板哦");
        entry.setSeparate_js("\n" +
                "//获取所有本模板的div\n" +
                "let list_divDisplay=document.getElementsByClassName(\"separateJS11\");\n" +
                "for(let div_display of list_divDisplay){\n" +
                "\n" +
                "\n" +
                "    // let valueJSON=div_display.getAttribute(\"value\");\n" +
                "\n" +
                "    // let formatJSON=window.androidObject.getFormat(\"11\");\n" +
                "\n" +
                "    format={\n" +
                "        template_id:1,//1,2,3\n" +
                "        choice:{\n" +
                "            \"1\":\"玩手机,王者荣耀\",\n" +
                "            \"2\":\"看小说\",\n" +
                "            \"3\":\"blibli\",\n" +
                "            \"4\":\"看电影\",\n" +
                "            \"5\":\"出去玩\",\n" +
                "            \"6\":\"聊天\",\n" +
                "            \"7\":\"运动\",\n" +
                "           },\n" +
                "       choiceType:\"checkbox\",\n" +
                "        };\n" +
                "    value={\n" +
                "        \"1\":true,\n" +
                "        \"2\":true,\n" +
                "        \"3\":true,\n" +
                "        \"4\":true,\n" +
                "       \"5\":false,\n" +
                "       \"6\":false,\n" +
                "       \"7\":false,\n" +
                "    };\n" +
                "\n" +
                "    for(let j in value){\n" +
                "        let key=Object.keys(value)[j-1];\n" +
                "        if(value[j]==true){\n" +
                "            div_a=document.createElement(\"div\");\n" +
                "            div_a.innerHTML=format[\"choice\"][key];\n" +
                "            //div_a.innerHTML=key;\n" +
                "            div_display.appendChild(div_a);\n" +
                "        }\n" +
                "\n" +
                "    }\n" +
                "}\n");
        entry.setRecord_view("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Document</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div id='root'>\n" +
                "\n" +
                "    </div>\n" +
                "    <script>\n" +
                "\n" +
                "            \n" +
                "        \n" +
                "        \n" +
                "        //const getJSObj=window.androidObject.getAndroidFormat();\n" +
                "        //let JSObj=JSON.parse(getObj);\n" +
                "\n" +
                "\t\t        //测试用的JSObj\n" +
                "         JSObj={\n" +
                "             template_id:1,//1,2,3\n" +
                "             choice:{\n" +
                "                 \"1\":\"玩手机,王者荣耀\",\n" +
                "                 \"2\":\"看小说\",\n" +
                "                 \"3\":\"blibli\",\n" +
                "                 \"4\":\"看电影\",\n" +
                "                 \"5\":\"出去玩\",\n" +
                "                 \"6\":\"聊天\",\n" +
                "                 \"7\":\"运动\",\n" +
                "\t\t\t\t},\n" +
                "\t\t\tchoiceType:\"checkbox\",\n" +
                "             };\n" +
                "\n" +
                "        //获取模板json，根据模板json渲染组件\n" +
                "        //获取安卓数据库传过来的json，用以给渲染出来的组件填充默认值\n" +
                "        //const getAndroidObj=window.androidObject.getAndroidCellEntry();\n" +
                "        //let AndroidObj=JSON.parse(getAndroidObj);\n" +
                "\n" +
                "        //测试用例\n" +
                "         AndroidObj={\n" +
                "             \"1\":true,\n" +
                "             \"2\":true,\n" +
                "             \"3\":true,\n" +
                "             \"4\":true,\n" +
                "\t\t\t\"5\":false,\n" +
                "\t\t\t\"6\":false,\n" +
                "\t\t\t\"7\":false,\n" +
                "         }\n" +
                "\n" +
                "        //要返回的json对象\n" +
                "        const returnObj={};\n" +
                "\n" +
                "        //获取root div\n" +
                "        const root=document.getElementById('root');\n" +
                "\n" +
                "        //index用于给inout标签设置id\n" +
                "        let index=1;\n" +
                "        //渲染组件\n" +
                "        for(let i in JSObj['choice']){\n" +
                "        console.log(\"输出结果为:\"+i);\n" +
                "\n" +
                "            //第一步是获取key\n" +
                "            let key=Object.keys(JSObj.choice)[i-1];\n" +
                "            returnObj[key]=AndroidObj[key];\n" +
                "            //returnObj[JSObj['choice'][i]]=AndroidObj[JSObj['choice'][i]];\n" +
                "            let div1=document.createElement('div');\n" +
                "            let btn1=document.createElement('input');\n" +
                "            if(JSObj['choiceType']==='radio'){\n" +
                "                btn1.setAttribute('type','radio');\n" +
                "                btn1.onclick=function(){\n" +
                "                for(let j in returnObj){\n" +
                "                    returnObj[j]=false;\n" +
                "                    }\n" +
                "                //returnObj[JSObj['choice'][i]]=!returnObj[JSObj['choice'][i]];\n" +
                "                returnObj[key]=true;\n" +
                "                console.log(returnObj);\n" +
                "                }\n" +
                "            }\n" +
                "            else{\n" +
                "                btn1.setAttribute('type','checkbox');\n" +
                "                // btn1.setAttribute('name','che');\n" +
                "                btn1.onclick=function(){\n" +
                "                //returnObj[JSObj['choice'][i]]=!returnObj[JSObj['choice'][i]];\n" +
                "                returnObj[key]=!returnObj[key];\n" +
                "                console.log(returnObj);\n" +
                "            }\n" +
                "            }\n" +
                "            btn1.setAttribute('name','in');\n" +
                "            btn1.setAttribute('id','label'+index);\n" +
                "            let label1=document.createElement('label');\n" +
                "            label1.textContent=JSObj['choice'][i];\n" +
                "            label1.setAttribute('for','label'+index);\n" +
                "            //btn1.checked=AndroidObj[JSObj['choice'][i]];\n" +
                "            btn1.checked=AndroidObj[key];\n" +
                "            div1.appendChild(label1);\n" +
                "            div1.appendChild(btn1);\n" +
                "            root.appendChild(div1);\n" +
                "            ++index;\n" +
                "            // console.log(returnObj);\n" +
                "        } \n" +
                "\n" +
                "\n" +
                "        window.getJSRecordView=function(){\n" +
                "            return JSON.stringify(returnObj);\n" +
                "        }\n" +
                "    </script>\n" +
                "</body>\n" +
                "</html>");

        RecordEntryService.newInstance().add_recordEntry(entry);
    }
    private void test_add_entertain() {
        CellEntryService.newInstance().add_recordEntry("choice002");
        RecordEntry entry=new RecordEntry();
        entry.setId("choice002");
        entry.setTemplate_id("11");
        entry.setName("娱乐情况");
        entry.setInfo("记录娱乐方式,基础是一个选择模板");
        entry.setSeparate_js("\n" +
                "//获取所有本模板的div\n" +
                "let list_divDisplay=document.getElementsByClassName(\"separateJS11\");\n" +
                "for(let div_display of list_divDisplay){\n" +
                "\n" +
                "\n" +
                "    // let valueJSON=div_display.getAttribute(\"value\");\n" +
                "\n" +
                "    // let formatJSON=window.androidObject.getFormat(\"11\");\n" +
                "\n" +
                "    format={\n" +
                "        template_id:1,//1,2,3\n" +
                "        choice:{\n" +
                "            \"1\":\"玩手机,王者荣耀\",\n" +
                "            \"2\":\"看小说\",\n" +
                "            \"3\":\"blibli\",\n" +
                "            \"4\":\"看电影\",\n" +
                "            \"5\":\"出去玩\",\n" +
                "            \"6\":\"聊天\",\n" +
                "            \"7\":\"运动\",\n" +
                "           },\n" +
                "       choiceType:\"checkbox\",\n" +
                "        };\n" +
                "    value={\n" +
                "        \"1\":true,\n" +
                "        \"2\":true,\n" +
                "        \"3\":true,\n" +
                "        \"4\":true,\n" +
                "       \"5\":false,\n" +
                "       \"6\":false,\n" +
                "       \"7\":false,\n" +
                "    };\n" +
                "\n" +
                "    for(let j in value){\n" +
                "        let key=Object.keys(value)[j-1];\n" +
                "        if(value[j]==true){\n" +
                "            div_a=document.createElement(\"div\");\n" +
                "            div_a.innerHTML=format[\"choice\"][key];\n" +
                "            //div_a.innerHTML=key;\n" +
                "            div_display.appendChild(div_a);\n" +
                "        }\n" +
                "\n" +
                "    }\n" +
                "}\n");
        entry.setRecord_view("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Document</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div id='root'>\n" +
                "\n" +
                "    </div>\n" +
                "    <script>\n" +
                "\n" +
                "            \n" +
                "        \n" +
                "        \n" +
                "        //const getJSObj=window.androidObject.getAndroidFormat();\n" +
                "        //let JSObj=JSON.parse(getObj);\n" +
                "\n" +
                "\t\t        //测试用的JSObj\n" +
                "         JSObj={\n" +
                "             template_id:1,//1,2,3\n" +
                "             choice:{\n" +
                "                 \"1\":\"玩手机,王者荣耀\",\n" +
                "                 \"2\":\"看小说\",\n" +
                "                 \"3\":\"blibli\",\n" +
                "                 \"4\":\"看电影\",\n" +
                "                 \"5\":\"出去玩\",\n" +
                "                 \"6\":\"聊天\",\n" +
                "                 \"7\":\"运动\",\n" +
                "\t\t\t\t},\n" +
                "\t\t\tchoiceType:\"checkbox\",\n" +
                "             };\n" +
                "\n" +
                "        //获取模板json，根据模板json渲染组件\n" +
                "        //获取安卓数据库传过来的json，用以给渲染出来的组件填充默认值\n" +
                "        //const getAndroidObj=window.androidObject.getAndroidCellEntry();\n" +
                "        //let AndroidObj=JSON.parse(getAndroidObj);\n" +
                "\n" +
                "        //测试用例\n" +
                "         AndroidObj={\n" +
                "             \"1\":true,\n" +
                "             \"2\":true,\n" +
                "             \"3\":true,\n" +
                "             \"4\":true,\n" +
                "\t\t\t\"5\":false,\n" +
                "\t\t\t\"6\":false,\n" +
                "\t\t\t\"7\":false,\n" +
                "         }\n" +
                "\n" +
                "        //要返回的json对象\n" +
                "        const returnObj={};\n" +
                "\n" +
                "        //获取root div\n" +
                "        const root=document.getElementById('root');\n" +
                "\n" +
                "        //index用于给inout标签设置id\n" +
                "        let index=1;\n" +
                "        //渲染组件\n" +
                "        for(let i in JSObj['choice']){\n" +
                "        console.log(\"输出结果为:\"+i);\n" +
                "\n" +
                "            //第一步是获取key\n" +
                "            let key=Object.keys(JSObj.choice)[i-1];\n" +
                "            returnObj[key]=AndroidObj[key];\n" +
                "            //returnObj[JSObj['choice'][i]]=AndroidObj[JSObj['choice'][i]];\n" +
                "            let div1=document.createElement('div');\n" +
                "            let btn1=document.createElement('input');\n" +
                "            if(JSObj['choiceType']==='radio'){\n" +
                "                btn1.setAttribute('type','radio');\n" +
                "                btn1.onclick=function(){\n" +
                "                for(let j in returnObj){\n" +
                "                    returnObj[j]=false;\n" +
                "                    }\n" +
                "                //returnObj[JSObj['choice'][i]]=!returnObj[JSObj['choice'][i]];\n" +
                "                returnObj[key]=true;\n" +
                "                console.log(returnObj);\n" +
                "                }\n" +
                "            }\n" +
                "            else{\n" +
                "                btn1.setAttribute('type','checkbox');\n" +
                "                // btn1.setAttribute('name','che');\n" +
                "                btn1.onclick=function(){\n" +
                "                //returnObj[JSObj['choice'][i]]=!returnObj[JSObj['choice'][i]];\n" +
                "                returnObj[key]=!returnObj[key];\n" +
                "                console.log(returnObj);\n" +
                "            }\n" +
                "            }\n" +
                "            btn1.setAttribute('name','in');\n" +
                "            btn1.setAttribute('id','label'+index);\n" +
                "            let label1=document.createElement('label');\n" +
                "            label1.textContent=JSObj['choice'][i];\n" +
                "            label1.setAttribute('for','label'+index);\n" +
                "            //btn1.checked=AndroidObj[JSObj['choice'][i]];\n" +
                "            btn1.checked=AndroidObj[key];\n" +
                "            div1.appendChild(label1);\n" +
                "            div1.appendChild(btn1);\n" +
                "            root.appendChild(div1);\n" +
                "            ++index;\n" +
                "            // console.log(returnObj);\n" +
                "        } \n" +
                "\n" +
                "\n" +
                "        window.getJSRecordView=function(){\n" +
                "            return JSON.stringify(returnObj);\n" +
                "        }\n" +
                "    </script>\n" +
                "</body>\n" +
                "</html>");

        RecordEntryService.newInstance().add_recordEntry(entry);
    }
    private void test_add_text() {
        CellEntryService.newInstance().add_recordEntry("txt001");
        RecordEntry entry=new RecordEntry();
        entry.setId("txt001");
        entry.setTemplate_id("12");
        entry.setName("文本");
        entry.setInfo("用来记录某个方面的文字,是个基本模板哦");
        entry.setRecord_view("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Document</title>\n" +
                "    <style>\n" +
                "            label,textarea {\n" +
                "            font-size: .8rem;\n" +
                "            letter-spacing: 1px;\n" +
                "        }\n" +
                "        textarea {\n" +
                "            padding: 1px;\n" +
                "            line-height: 1.5;\n" +
                "            border-radius: 5px;\n" +
                "            border: 1px solid #ccc;\n" +
                "            box-shadow: 1px 1px 1px #999;\n" +
                "        }\n" +
                "\n" +
                "        label {\n" +
                "            margin-bottom: 0px;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <label for=\"story\">请在下方输入文本:</label>\n" +
                "        <br>\n" +
                "        <textarea id=\"valueTxtInput\" name=\"story\"\n" +
                "          rows=\"8\" cols=\"30\" autocomplete=\"on\" placeholder=\"请输入文本\" autofocus=\"true\">\n" +
                "        </textarea>\n" +
                "    <script>\n" +
                "\n" +
                "        /*\n" +
                "        * 说明:\n" +
                "        * 这个是最基本的文本记录模板,\n" +
                "        * 不需要模板信息(所以写起来很简单)\n" +
                "        * 返回值是个json字符串:\n" +
                "        * 对应的js对象为:\n" +
                "        {\n" +
                "            \"txtContent\":\"这个地方就是实际记录的文本,它的键是txtContent\",\n" +
                "        }\n" +
                "        */\n" +
                "\n" +
                "        //获取记录值,直接放到控件上\n" +
                "        //const json_recordValue=window.androidObject.getAndroidRecordValue();\n" +
                "        json_recordValue=\"\";\n" +
                "        if(json_recordValue===\"\"){\n" +
                "            a={\n" +
                "            \"txtContent\":\"\",\n" +
                "            };\n" +
                "            json_recordValue=JSON.stringify(a);\n" +
                "        };\n" +
                "        let android_recordValue={};\n" +
                "        android_recordValue=JSON.parse(json_recordValue);\n" +
                "        const returnObj={\n" +
                "            \"txtContent\":\"\",\n" +
                "        };\n" +
                "\n" +
                "        //获取root dtextarea\n" +
                "        let valueTxtInput=document.getElementById('valueTxtInput');\n" +
                "        valueTxtInput.value=android_recordValue[\"txtContent\"];\n" +
                "        returnObj['txtContent']=valueTxtInput.value;\n" +
                "\n" +
                "        valueTxtInput.onchange=function(){\n" +
                "            returnObj['txtContent']=valueTxtInput.value;\n" +
                "        }\n" +
                "        window.getJSRecordView=function(){\n" +
                "            returnObj['txtContent']=valueTxtInput.value;\n" +
                "            return JSON.stringify(returnObj);\n" +
                "        }\n" +
                "    </script>\n" +
                "</body>\n" +
                "</html>\n" +
                "\n" +
                "\n");
        RecordEntryService.newInstance().add_recordEntry(entry);
    }
    private void test_add_number() {
        CellEntryService.newInstance().add_recordEntry("number001");
        RecordEntry entry=new RecordEntry();
        entry.setId("number001");
        entry.setTemplate_id("13");
        entry.setName("数量");
        entry.setInfo("和数量有关的记录值");
        entry.setRecord_view("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Document</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div id='root'>\n" +
                "\n" +
                "    </div>\n" +
                "    <script>\n" +
                "           //测试用的JSObj\n" +
                "         const JSObj={\n" +
                "             data:{\n" +
                "             '1':'饮食支出',\n" +
                "             '2':'出行支出',\n" +
                "             '3':'娱乐支出',\n" +
                "             '4':'学习支出',\n" +
                "             '5':'衣服支出',\n" +
                "             \n" +
                "            },\n" +
                "            choiceType:'button',\n" +
                "            step:2\n" +
                "            \n" +
                "         };\n" +
                "        //测试用AndroidObj\n" +
                "         const AndroidObj={\n" +
                "             '1':'10',\n" +
                "             '2':'20',\n" +
                "             '3':'30',\n" +
                "             '4':'40',\n" +
                "             '5':'50',\n" +
                "         }\n" +
                "        \n" +
                "        \n" +
                "        \n" +
                "        //从安卓获取数据\n" +
                "        //获取模板json，根据模板json渲染组件\n" +
                "        //const getJSObj=window.androidObject.getAndroidFormat();\n" +
                "        //let JSObj=JSON.parse(getObj);\n" +
                "\n" +
                "        //获取安卓数据库传过来的json，用以给渲染出来的组件填充默认值\n" +
                "        //const getAndroidObj=window.androidObject.getAndroidCellEntry();\n" +
                "        //let AndroidObj=JSON.parse(getAndroidObj);\n" +
                "\n" +
                "        //要返回的json对象\n" +
                "        const returnObj={};\n" +
                "\n" +
                "        //获取root div\n" +
                "        const root=document.getElementById('root');\n" +
                "\n" +
                "        //index用于给inout标签设置id\n" +
                "        let index=1;\n" +
                "        for(let i in JSObj['data']){\n" +
                "            //returnObj[JSObj['data'][i]]=AndroidObj[JSObj['data'][i]];\n" +
                "\n" +
                "            //第一步是获取key\n" +
                "            let key=Object.keys(JSObj.data)[i-1];\n" +
                "\n" +
                "            //设置数据名称\n" +
                "            let div1=document.createElement('div');\n" +
                "            let label1=document.createElement('label');\n" +
                "            label1.textContent=JSObj['data'][i];\n" +
                "\n" +
                "            //添加输入框\n" +
                "            let input=document.createElement('input');\n" +
                "            input.setAttribute('type','number');\n" +
                "            input.value=AndroidObj[key];\n" +
                "            input.onchange=function(){\n" +
                "                //returnObj[JSObj['data'][i]]=input.value;\n" +
                "                returnObj[key]=input.value;\n" +
                "                console.log(returnObj);\n" +
                "            }\n" +
                "\n" +
                "            //创建底部的两个元素\n" +
                "            let nextLine=document.createElement('br');\n" +
                "            let btn1=document.createElement('button');\n" +
                "            let btn2=document.createElement('button');\n" +
                "            btn1.textContent='+';\n" +
                "            btn2.textContent='-';\n" +
                "            btn1.onclick=function(){\n" +
                "                input.value=parseInt(input.value)+parseInt(JSObj['step']);\n" +
                "                returnObj[key]=input.value;\n" +
                "                console.log(returnObj);\n" +
                "            }\n" +
                "            btn2.onclick=function(){\n" +
                "                input.value=parseInt(input.value)-parseInt(JSObj['step']);\n" +
                "                returnObj[key]=input.value;\n" +
                "                console.log(returnObj);\n" +
                "            }\n" +
                "            btn1.style.marginLeft=\"5px\";\n" +
                "            btn1.style.marginRight=\"5px\";\n" +
                "            div1.appendChild(label1);\n" +
                "            div1.appendChild(input);\n" +
                "            div1.appendChild(nextLine);\n" +
                "            div1.appendChild(btn1);\n" +
                "            div1.appendChild(btn2);\n" +
                "            root.appendChild(div1);\n" +
                "        }\n" +
                "        console.log(returnObj);\n" +
                "        window.getJSRecordView=function(){\n" +
                "            return JSON.stringify(returnObj);\n" +
                "        }\n" +
                "\n" +
                "    </script>\n" +
                "</body>\n" +
                "</html>");

        RecordEntryService.newInstance().add_recordEntry(entry);
    }

    /*初始化标签*/
    public void init_tab(){

        tabLayout_main=findViewById(R.id.tabLayout_main);
        tabLayout_main.setupWithViewPager(viewPager_main);
        TabLayout.Tab list_tab=tabLayout_main.getTabAt(0);
        list_tab.setCustomView(R.layout.item_start_tab);
        TextView textView=list_tab.getCustomView().findViewById(R.id.textView_start_tab);
        textView.setText("列表");
        ImageView imageView=list_tab.getCustomView().findViewById(R.id.imageView_start_tab);
        imageView.setBackgroundResource(R.drawable.tab_start_menuicon);

        TabLayout.Tab record_tab=tabLayout_main.getTabAt(1);
        record_tab.setCustomView(R.layout.item_start_tab);
        textView=record_tab.getCustomView().findViewById(R.id.textView_start_tab);
        textView.setText("记录");
        imageView=record_tab.getCustomView().findViewById(R.id.imageView_start_tab);
        imageView.setBackgroundResource(R.drawable.tab_start_menuicon_edit);

        TabLayout.Tab check_tab=tabLayout_main.getTabAt(2);
        check_tab.setCustomView(R.layout.item_start_tab);
        textView=check_tab.getCustomView().findViewById(R.id.textView_start_tab);
        textView.setText("查看");
        imageView=check_tab.getCustomView().findViewById(R.id.imageView_start_tab);
        imageView.setBackgroundResource(R.drawable.tab_start_menuicon_read);


        TabLayout.Tab me_tab=tabLayout_main.getTabAt(3);
        me_tab.setCustomView(R.layout.item_start_tab);
        textView=me_tab.getCustomView().findViewById(R.id.textView_start_tab);
        textView.setText("我");
        imageView=me_tab.getCustomView().findViewById(R.id.imageView_start_tab);
        imageView.setBackgroundResource(R.drawable.tab_start_menuicon_me);

    }

    public void test_init_pojoService(){
        dayEntryService=new DayEntryService();
        recordEntryService=new RecordEntryService();
        recordTemplateService=new RecordTemplateService();


    }
    //=================自定义内部类==========================
    class Adapter_viewPager_main extends FragmentPagerAdapter {
        public Adapter_viewPager_main(@NonNull FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i){
                case 0:
                    return new Fragment_start_outFrame();
                case 1:
                    return Fragment_Record_OutFrame.newInstance();
                case 2:
                    return Fragment_Check_OutFrame.newInstance("","");
            }



            return Test_Fragment.newInstance("","");
        }

        @Override
        public int getCount() {
            return 4;
        }

    }
}