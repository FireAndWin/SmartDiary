package com.SmartDiary.service.pojoService;

import com.SmartDiary.pojo.RecordTemplate;

import java.util.ArrayList;
import java.util.List;

public class RecordTemplateService {
    //单例模式的对象
    private static RecordTemplateService singleRecordTemplateService;
    //储存着所有模板的列表
    public List<RecordTemplate> templateList;
    //单例模式的方法
    public static  RecordTemplateService newInstance() {
        if(singleRecordTemplateService==null){
            singleRecordTemplateService=new RecordTemplateService();
        }
        return singleRecordTemplateService;
    }

    //构造函数,加入点测试数据
    public RecordTemplateService() {
        templateList=new ArrayList<>();

        //分别初始化三个模板,这里都是测试数据
        test_init_choose_template();
        test_init_txt_template();
        test_init_number_template();
    }


    private void test_init_choose_template() {
        RecordTemplate template=new RecordTemplate();
        templateList.add(template);
        template.setId("11");
        template.setName("选择");
        template.setInfo("记录选择");
        template.setSeparate_js("\n" +
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
        template.setRecord_view("<!DOCTYPE html>\n" +
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
    }

    private void test_init_txt_template() {
        RecordTemplate template=new RecordTemplate();
        templateList.add(template);
        template.setId("12");
        template.setName("文本");
        template.setInfo("记录一些文本");
        template.setRecord_view("<!DOCTYPE html>\n" +
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
                "        //获取模板json，根据模板json渲染组件\n" +
                "        // const getJSObj=window.androidObject.getAndroidFormat();\n" +
                "        // let JSObj=JSON.parse(getObj);\n" +
                "\n" +
                "        //获取安卓数据库传过来的json，用以给渲染出来的组件填充默认值\n" +
                "        const getAndroidObj=window.androidObject.getAndroidCellEntry();\n" +
                "        let AndroidObj=JSON.parse(getAndroidObj);\n" +
                "        // const AndroidObj={\n" +
                "        //     value:`人生苦短，我用JavaScript;人生苦短，我用Java；人生苦短，我用Goland`\n" +
                "        // }\n" +
                "        //要返回的json对象\n" +
                "        const returnObj={};\n" +
                "\n" +
                "        //获取root dtextarea\n" +
                "        const root=document.getElementById('story');\n" +
                "        root.value=AndroidObj['value'];\n" +
                "        returnObj['value']=root.value;\n" +
                "        console.log(returnObj);\n" +
                "        root.onchange=function(){\n" +
                "            returnObj['value']=root.value;\n" +
                "            console.log(returnObj);\n" +
                "        }\n" +
                "        window.getJSRecordView=function(){\n" +
                "            return JSON.stringify(returnObj);\n" +
                "        }\n" +
                "    </script>\n" +
                "</body>\n" +
                "</html> ");

    }

    private void test_init_number_template() {
        RecordTemplate template=new RecordTemplate();
        templateList.add(template);
        template.setId("13");
        template.setName("数量");
        template.setInfo("和数量有关的记录值");
        template.setRecord_view("<!DOCTYPE html>\n" +
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
    }

    //根据记录项id获取到该记录项的模板对象
    public RecordTemplate getObject_byID(String template_id){
//        RecordTemplate recordTemplate= new RecordTemplate();
//
//        String c=""+
//        "<!DOCTYPE html>\n" +
//                "<html>\n" +
//                "<head> \n" +
//                "<meta charset=\"utf-8\"> \n" +
//                "<title>文档标题</title>\n" +
//                "</head>\n" +
//                " \n" +
//                "<body>\n" +
//                "<form>\n" +
//                "First name: <input type=\"text\" name=\"firstname\"><br>\n" +
//                "Last name: <input type=\"text\" name=\"lastname\">\n" +
//                "</form>" +
//                "<form>\n" +
//                "<input type=\"radio\" name=\"sex\" value=\"male\">Male<br>\n" +
//                "<input type=\"radio\" name=\"sex\" value=\"female\">Female\n" +
//                "</form>" +
//                "<form>\n" +
//                "<input type=\"checkbox\" name=\"vehicle\" value=\"Bike\">I have a bike<br>\n" +
//                "<input type=\"checkbox\" name=\"vehicle\" value=\"Car\">I have a car\n" +
//                "</form>" +
//                "" +
//                "</body>\n" +
//                " \n" +
//                "</html>";
//        String b="<!DOCTYPE html>\n" +
//                "<html>\n" +
//                "<head> \n" +
//                "<meta charset=\"utf-8\"> \n" +
//                "<title>文档标题</title>\n" +
//                "</head>\n" +
//                " \n" +
//                "<body>\n" +
//                "文档内容......\n" +
//                "</body>\n" +
//                " \n" +
//                "</html>";
//        String a="" +
//                "<!DOCTYPE html>\n" +
//                "<html>\n" +
//                "  <head>\n" +
//                "    <meta charset=\"UTF-8\" />\n" +
//                "    <title></title>\n" +
//                "    <style type=\"text/css\">\n" +
//                "      #box1 {\n" +
//                "        width: 100px;\n" +
//                "        height: 100px;\n" +
//                "        background-color: red;\n" +
//                "        /*\n" +
//                "        * 开启box1的绝对定位\n" +
//                "        */\n" +
//
//                "        position: absolute;\n" +
//                "      }\n" +
//                "    </style>\n" +
//                "\n" +
//                "    <script type=\"text/javascript\">\n" +
//                "      window.onload = function() {\n" +
//                "        /*\n" +
//                "         * 使div可以跟随鼠标移动\n" +
//                "         */\n" +
//                "\n" +
//                "        //获取box1\n" +
//                "        var box1 = document.getElementById(\"box1\");\n" +
//                "\n" +
//                "        //给整个页面绑定：鼠标移动事件\n" +
//                "        document.onmousemove = function(event) {\n" +
//                "          //兼容的方式获取event对象\n" +
//                "          event = event || window.event;\n" +
//                "\n" +
//                "          // 鼠标在页面的位置 = 滚动条滚动的距离 + 可视区域的坐标。\n" +
//                "          var pagex = event.pageX || scroll().left + event.clientX;\n" +
//                "          var pagey = event.pageY || scroll().top + event.clientY;\n" +
//                "\n" +
//                "          //   设置div的偏移量（相对于整个页面）\n" +
//                "          // 注意，如果想通过 style.left 来设置属性，一定要给 box1开启绝对定位。\n" +
//                "          box1.style.left = pagex + \"px\";\n" +
//                "          box1.style.top = pagey + \"px\";\n" +
//                "        };\n" +
//                "      };\n" +
//                "\n" +
//                "      // scroll 函数封装\n" +
//                "      function scroll() {\n" +
//                "        return {\n" +
//                "          //此函数的返回值是对象\n" +
//                "          left: window.pageYOffset || document.body.scrollTop || document.documentElement.scrollTop,\n" +
//                "          right:\n" +
//                "            window.pageXOffset || document.body.scrollLeft || document.documentElement.scrollLeft\n" +
//                "        };\n" +
//                "      }\n" +
//                "    </script>\n" +
//                "  </head>\n" +
//                "  <body style=\"height: 1000px;width: 2000px;\">\n" +
//                "    <div id=\"box1\"></div>\n" +
//                "  </body>\n" +
//                "</html>\n";
//        recordTemplate.setRecord_view(c);
//        return recordTemplate;
        for(RecordTemplate template:templateList){
            if(template.getId()==template_id){
                return template;
            }
        }
        return null;
    }
}
