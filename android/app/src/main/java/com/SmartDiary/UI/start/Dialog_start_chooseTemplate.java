package com.SmartDiary.UI.start;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.SmartDiary.R;
import com.SmartDiary.pojo.RecordEntry;
import com.SmartDiary.pojo.RecordTemplate;
import com.SmartDiary.service.pojoService.CellEntryService;
import com.SmartDiary.service.pojoService.RecordEntryService;

//选择模板界面
public class Dialog_start_chooseTemplate {

    //要创建编辑记录项对话框需要的参数,自己不怎么用;
    On_RecordEntry_Edit_Listener listener;
    Context context;

    AlertDialog dialog;
    View view;

    public Dialog_start_chooseTemplate(Context context, On_RecordEntry_Edit_Listener listener) {

        this.context=context;
        this.listener=listener;

        View view= View.inflate(context, R.layout.dialog__choose_template,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        dialog=builder.create();
        dialog.show();

        test_bindView(view);
    }


    private void test_bindView(View view) {
        view.findViewById(R.id.test_txt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecordEntry entry= RecordEntryService.newInstance().get_recordEntry_byId("txt001");
                go_create_RecordEntry(entry);
            }
        });
        view.findViewById(R.id.test_numbers).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecordEntry entry= RecordEntryService.newInstance().get_recordEntry_byId("number001");
                go_create_RecordEntry(entry);
            }
        });
        view.findViewById(R.id.test_choose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //RecordEntry entry= RecordEntryService.newInstance().get_recordEntry_byId("choice001");
                RecordEntry entry=get_chooseTemplate_entry();
                go_create_RecordEntry(entry);
            }
        });
        view.findViewById(R.id.test_singleNumber).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecordEntry entry= RecordEntryService.newInstance().get_recordEntry_byId("txt001");
                go_create_RecordEntry(entry);
            }
        });
    }

    //选择好模板后,调用此方法去创建具体的记录项
    public void go_create_RecordEntry(RecordEntry entry){
        dialog.dismiss();
        Dialog_start_editRecordEntry dialog_start_editRecordEntry=new Dialog_start_editRecordEntry(context,entry,listener);
    }

    //形成选择模板,这里还是测试方法
    public RecordEntry get_chooseTemplate_entry(){
        RecordEntry entry=new RecordEntry();
        entry.setId("choice001");
        entry.setTemplate_id("11");
        entry.setName("选择");
        entry.setInfo("可以用来记录一些和选择有关的,是个基本的记录项模板哦");
        entry.setSeparate_js("");
        entry.setFormat("");
        entry.setEdit_view("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Document</title>\n" +
                "    <style>\n" +
                "        #addBtn {\n" +
                "            padding: 5vw;\n" +
                "        }\n" +
                "    </style>\n" +
                "\n" +
                "    <!-- head 中 -->\n" +
                "    <link rel=\"stylesheet\" href=\"file:///android_asset/weui/weui.min.css\">\n" +
                "    <link rel=\"stylesheet\" href=\"file:///android_asset/weui/jquery-weui.mini.css\">\n" +
                "    <!-- body 最后 -->\n" +
                "    <script src=\"file:///android_asset/weui/jquery.min.js\"></script>\n" +
                "    <script src=\"file:///android_asset/weui/jquery-weui.min.js\"></script>\n" +
                "\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "\n" +
                "\n" +
                "    <div id=\"divChoices\" class=\"weui-cells\">\n" +
                "    </div>\n" +
                "    <div id=\"addBtn\">\n" +
                "        <a href=\"javascript:;\" class=\"weui-btn weui-btn_primary\">\n" +
                "            添加选项\n" +
                "        </a>\n" +
                "    </div>\n" +
                "\n" +
                "    <div class=\"weui-cells__title\">选择方式</div>\n" +
                "    <div class=\"weui-cells weui-cells_radio\">\n" +
                "        <label class=\"weui-cell weui-check__label\" for=\"rbtn1\">\n" +
                "            <div class=\"weui-cell__bd\">\n" +
                "                <p>单选 </p>\n" +
                "            </div>\n" +
                "            <div class=\"weui-cell__ft\">\n" +
                "                <input type=\"radio\" class=\"weui-check\" name=\"radio1\" id=\"rbtn1\">\n" +
                "                <span class=\"weui-icon-checked\"></span>\n" +
                "            </div>\n" +
                "        </label>\n" +
                "        <label class=\"weui-cell weui-check__label\" for=\"rbtn2\">\n" +
                "\n" +
                "            <div class=\"weui-cell__bd\">\n" +
                "                <p>多选</p>\n" +
                "            </div>\n" +
                "            <div class=\"weui-cell__ft\">\n" +
                "                <input type=\"radio\" name=\"radio1\" class=\"weui-check\" id=\"rbtn2\" checked=\"checked\">\n" +
                "                <span class=\"weui-icon-checked\"></span>\n" +
                "            </div>\n" +
                "        </label>\n" +
                "    </div>\n" +
                "\n" +
                "\n" +
                "    <script>\n" +
                "\n" +
                "\n" +
                "        //===============先获取到安卓的格式对象========================\n" +
                "        let jsonFormat = window.androidObject.getAndroidFormat();\n" +
                "        //let jsonFormat = \"{\\\"choiceList\\\":[{\\\"key\\\":\\\"1619096245588\\\",\\\"textValue\\\":\\\"1\\\"},{\\\"key\\\":\\\"1619096251629\\\",\\\"textValue\\\":\\\"\\\"}],\\\"type\\\":\\\"checkbox\\\",\\\"id\\\":\\\"choiceTemplate\\\"}\";\n" +
                "        //这里对获取到的格式字符串做个判断,如果格式json是空字符串,就转化为一个默认的格式字符串.\n" +
                "        if (jsonFormat === \"\") {\n" +
                "            defaultFormat = {\n" +
                "                choiceList: [\n" +
                "                    {\n" +
                "                        key: \"1111\",\n" +
                "                        textValue: \"选项1(可重命名)\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        key: \"1112\",\n" +
                "                        textValue: \"选项2\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        key: \"113\",\n" +
                "                        textValue: \"选项3(可以继续添加选项)\"\n" +
                "                    },\n" +
                "                ],\n" +
                "                type: \"checkbox\",\n" +
                "                id: \"choiceTemplate\",\n" +
                "            };\n" +
                "            jsonFormat = JSON.stringify(defaultFormat);\n" +
                "        };\n" +
                "\n" +
                "        let formatObj = JSON.parse(jsonFormat);\n" +
                "\n" +
                "\n" +
                "        //==============然后遍历choice中的选项,添加到div中================\n" +
                "        let initChoiceList = function () {\n" +
                "            let divChoices = document.getElementById(\"divChoices\");\n" +
                "            divChoices.innerHTML = \"\";\n" +
                "            for (let indexList in formatObj.choiceList) {\n" +
                "                let choice = formatObj.choiceList[indexList];\n" +
                "                let keyChoice = choice.key;\n" +
                "                let textChoice = choice.textValue;\n" +
                "                console.log(\"ded\", choice);\n" +
                "\n" +
                "                //创建列表表格div\n" +
                "                let divCell = document.createElement(\"div\");\n" +
                "                divCell.setAttribute(\"class\", \"weui-cell divCell\");\n" +
                "                divCell.setAttribute(\"choiceKey\", keyChoice);\n" +
                "                //添加内容\n" +
                "                divCell.innerHTML = `\n" +
                "\n" +
                "            <div class=\"weui-cell__hd\">\n" +
                "                <i class=\"weui-icon-download\" id=`+ \"movedown\" + keyChoice + `></i>\n" +
                "            </div>\n" +
                "            <div class=\"weui-cell__bd\">\n" +
                "                <input class=\"weui-input\" id=`+ \"input\" + keyChoice + ` type=\"text\" placeholder=\"请输入选项名称\" value=` + textChoice + `>\n" +
                "            </div>\n" +
                "            <div class=\"weui-cell__ft\">\n" +
                "                <a href=\"javascript:;\"     id=` + \"delete\" + keyChoice + ` class=\"weui-btn  weui-btn_mini  weui-btn_warn\">删除</a>\n" +
                "            </div>\n" +
                "            `;\n" +
                "                divChoices.appendChild(divCell);\n" +
                "\n" +
                "\n" +
                "                //获取到输入框和删除按钮,向下移动按钮\n" +
                "                let btnDelete = document.getElementById(\"delete\" + keyChoice);\n" +
                "                let inputName = document.getElementById(\"input\" + keyChoice);\n" +
                "                let btnMoveDown = document.getElementById(\"movedown\" + keyChoice);\n" +
                "                //添加事件\n" +
                "                //删除\n" +
                "                btnDelete.onclick = function () {\n" +
                "                    divCell.parentNode.removeChild(divCell);\n" +
                "                    formatObj.choiceList.forEach(function (item, index, arr) {\n" +
                "                        if (item.key === keyChoice) {\n" +
                "                            arr.splice(index, 1);\n" +
                "                            //console.log(formatObj.choiceList);\n" +
                "                            //initChoiceList();\n" +
                "                        };\n" +
                "                    });\n" +
                "                }\n" +
                "                //重命名\n" +
                "                inputName.onchange = function () {\n" +
                "                    formatObj.choiceList.forEach(function (item, index, arr) {\n" +
                "                        if (item.key === keyChoice) {\n" +
                "                            formatObj.choiceList[index].textValue = inputName.value;\n" +
                "                        };\n" +
                "                    });\n" +
                "                    console.log(formatObj);\n" +
                "                }\n" +
                "                //向下移动\n" +
                "                btnMoveDown.onclick = function () {\n" +
                "                    let cells = divChoices.childNodes;\n" +
                "                    cells.forEach(function (item, index, arr) {\n" +
                "                        if (cells[index] === divCell) {\n" +
                "                            let next = (index + 1) % (cells.length);\n" +
                "                            formatObj.choiceList[index] = formatObj.choiceList.splice(next, 1, formatObj.choiceList[index])[0];\n" +
                "                            console.log(formatObj.choiceList);\n" +
                "                            initChoiceList();\n" +
                "                        };\n" +
                "                    });\n" +
                "                };\n" +
                "            }\n" +
                "        };\n" +
                "        initChoiceList();\n" +
                "\n" +
                "        //==============\"添加选项\"按钮事件========\n" +
                "        let btnAdd = document.getElementById(\"addBtn\");\n" +
                "        btnAdd.onclick = function () {\n" +
                "            let millisNow = \"\" + Date.now();\n" +
                "            let newChoice = {\n" +
                "                key: \"\" + millisNow,\n" +
                "                textValue: \"\",\n" +
                "            }\n" +
                "            formatObj.choiceList.push(newChoice);\n" +
                "            initChoiceList();\n" +
                "            document.getElementById(\"input\" + millisNow).focus();\n" +
                "        };\n" +
                "        //=============\"选择方式\"相关==============\n" +
                "        let rbtn1 = document.getElementById(\"rbtn1\");\n" +
                "        let rbtn2 = document.getElementById(\"rbtn2\");\n" +
                "        if (formatObj.type === \"radio\") {\n" +
                "            rbtn1.checked = true;\n" +
                "        }\n" +
                "        else {\n" +
                "            rbtn2.checked = true;\n" +
                "        }\n" +
                "\n" +
                "        let changeType = function () {\n" +
                "            if (rbtn1.checked == true)\n" +
                "                formatObj.type = \"radio\";\n" +
                "            else\n" +
                "                formatObj.type = \"checkbox\";\n" +
                "            console.log(formatObj);\n" +
                "        }\n" +
                "        rbtn1.onclick = changeType;\n" +
                "        rbtn2.onclick = changeType;\n" +
                "        //==============拖拽改变顺序功能===========\n" +
                "\n" +
                "        //==============给安卓提供的借口============\n" +
                "        window.getJsFormat = function () {\n" +
                "            return JSON.stringify(formatObj);\n" +
                "   window.getjsonFormat = function () {\n" +
                "            return jsonFormat;\n" +
                "        };" +
                "        };\n" +
                "\n" +
                "    </script>\n" +
                "\n" +
                "</body>\n" +
                "\n" +
                "</html>");
        return entry;
    }

    //形成多数量模板,这里还是测试方法
    public RecordEntry get_multiNumberTemplate_entry(){
        return null;
    }

    //形成文本模板,这里还是测试方法
    public RecordEntry get_textTemplate_entry(){
        return null;
    }
}
