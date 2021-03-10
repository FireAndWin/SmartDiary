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
        template.setRecord_view(" ");
    }

    private void test_init_txt_template() {
        RecordTemplate template=new RecordTemplate();
        templateList.add(template);
        template.setId("12");
        template.setName("文本");
        template.setInfo("记录一些文本");
        template.setRecord_view(" ");
    }

    private void test_init_number_template() {
        RecordTemplate template=new RecordTemplate();
        templateList.add(template);
        template.setId("13");
        template.setName("数量");
        template.setInfo("和数量有关的记录值");
        template.setRecord_view(" ");
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
