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
                RecordEntry entry= get_textTemplate_entry();
                go_create_RecordEntry(entry);
            }
        });
        view.findViewById(R.id.test_numbers).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecordEntry entry= get_multiNumberTemplate_entry();
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
                "<div id=\"divChoices\" class=\"weui-cells\">\n" +
                "</div>\n" +
                "<div id=\"addBtn\">\n" +
                "    <a href=\"javascript:;\" class=\"weui-btn weui-btn_primary\">\n" +
                "        添加选项\n" +
                "    </a>\n" +
                "</div>\n" +
                "\n" +
                "<div class=\"weui-cells__title\">选择方式</div>\n" +
                "<div class=\"weui-cells weui-cells_radio\">\n" +
                "    <label class=\"weui-cell weui-check__label\" for=\"rbtn1\">\n" +
                "        <div class=\"weui-cell__bd\">\n" +
                "            <p>单选 </p>\n" +
                "        </div>\n" +
                "        <div class=\"weui-cell__ft\">\n" +
                "            <input type=\"radio\" class=\"weui-check\" name=\"radio1\" id=\"rbtn1\">\n" +
                "            <span class=\"weui-icon-checked\"></span>\n" +
                "        </div>\n" +
                "    </label>\n" +
                "    <label class=\"weui-cell weui-check__label\" for=\"rbtn2\">\n" +
                "\n" +
                "        <div class=\"weui-cell__bd\">\n" +
                "            <p>多选</p>\n" +
                "        </div>\n" +
                "        <div class=\"weui-cell__ft\">\n" +
                "            <input type=\"radio\" name=\"radio1\" class=\"weui-check\" id=\"rbtn2\" checked=\"checked\">\n" +
                "            <span class=\"weui-icon-checked\"></span>\n" +
                "        </div>\n" +
                "    </label>\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "<script>\n" +
                "\n" +
                "\n" +
                "        //===============先获取到安卓的格式对象========================\n" +
                "        let jsonFormat = window.androidObject.getAndroidFormat();\n" +
                "        console.log(\"初始收到的jsonFormat: '\"+jsonFormat);\n" +
                "        console.log(\"初始收到的jsonFormat类型:\", typeof jsonFormat);\n" +
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
                "            console.log(\"因为jsonFormat原本为空,使用默认格式对象\", jsonFormat);\n" +
                "        };\n" +
                "\n" +
                "        // let formatObj = JSON.parse(\"'\" + jsonFormat + \"'\");\n" +
                "        let formatObj = JSON.parse(jsonFormat);\n" +
                "        //console.log(\"formatObj解析结果\", formatObj);\n" +
                "        console.log(\"formatObj是否为空\", formatObj === null);\n" +
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
                "                console.log(\"ded\", textChoice);\n" +
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
                "                inputName.oninput = function () {\n" +
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
                "        };\n" +
                "\n" +
                "        window.getjsonFormat = function () {\n" +
                "            return jsonFormat;\n" +
                "        };\n" +
                "\n" +
                "    </script>\n" +
                "\n" +
                "</body>\n" +
                "\n" +
                "</html>");
        entry.setRecord_view("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Document</title>\n" +
                "\n" +
                "\n" +
                "    <!-- head 中 -->\n" +
                "    <link rel=\"stylesheet\" href=\"file:///android_asset/weui/weui.min.css\">\n" +
                "    <link rel=\"stylesheet\" href=\"file:///android_asset/weui/jquery-weui.mini.css\">\n" +
                "    <!-- body 最后 -->\n" +
                "    <script src=\"file:///android_asset/weui/jquery.min.js\"></script>\n" +
                "    <script src=\"file:///android_asset/weui/jquery-weui.min.js\"></script>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "\n" +
                "<div id=\"divChoices\"> </div>\n" +
                "<div class=\"weui-cells__title\">备注信息</div>\n" +
                "<div class=\"weui-cells weui-cells_form\">\n" +
                "    <div class=\"weui-cell\">\n" +
                "        <div class=\"weui-cell__bd\">\n" +
                "            <textarea class=\"weui-textarea\" id=\"remark\" placeholder=\"请输入备注内容\" rows=\"3\"></textarea>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "<script>\n" +
                "        let JsonFormat = window.androidObject.getAndroidFormat();\n" +
                "        let formatObj = JSON.parse(JsonFormat);\n" +
                "\n" +
                "        // let recordValueObj = {\n" +
                "        //     numberValueMap: [\n" +
                "        //         {\n" +
                "        //             key: \"玩了挺长时间\",\n" +
                "        //             value: 0,\n" +
                "        //         },\n" +
                "\n" +
                "        //         {\n" +
                "        //             key: \"一直玩\",\n" +
                "        //             value: 0,\n" +
                "        //         },\n" +
                "        //     ],\n" +
                "        //     remark: \"今天玩得真开心,哈哈哈\"\n" +
                "        // };\n" +
                "\n" +
                "        let JsonRecordValue = window.androidObject.getAndroidRecordValue();\n" +
                "        if (JsonRecordValue === \"\") {\n" +
                "            let defaultValueObj = {\n" +
                "                numberValueMap: [\n" +
                "                    {\n" +
                "                        key: \"impossible key\",\n" +
                "                        value: 0,\n" +
                "                    },\n" +
                "                ],\n" +
                "                remark: \"\"\n" +
                "            };\n" +
                "            JsonRecordValue = JSON.stringify(defaultValueObj);\n" +
                "        };\n" +
                "        let recordValueObj = JSON.parse(JsonRecordValue);\n" +
                "\n" +
                "\n" +
                "\n" +
                "        //===================获取放所有选择的div======================\n" +
                "        let divChoices = document.getElementById(\"divChoices\");\n" +
                "\n" +
                "        //==================根据记录值和格式添加元素=====================\n" +
                "        for (let indexList in formatObj.choiceList) {\n" +
                "            let choice = formatObj.choiceList[indexList];\n" +
                "            let choiceKey = choice.key;\n" +
                "            let choiceText = choice.textValue;\n" +
                "\n" +
                "\n" +
                "            //-----创建列表的列表项label\n" +
                "            let labelChoice = document.createElement(\"label\");\n" +
                "\n" +
                "            //-----向label中添加内容,这里要判断是否为单选\n" +
                "            //----1.如果是单选:\n" +
                "            if (formatObj.type === \"radio\") {\n" +
                "                divChoices.setAttribute(\"class\", \"weui-cells weui-cells_radio\");\n" +
                "\n" +
                "                labelChoice.setAttribute(\"class\", \"weui-cell weui-check__label\");\n" +
                "                labelChoice.setAttribute(\"for\", choiceKey);\n" +
                "                //把单选框放到右边\n" +
                "                labelChoice.innerHTML = `<div class=\"weui-cell__bd\">\n" +
                "                                <p>`+ choiceText + `</p>\n" +
                "                                </div>\n" +
                "                                <div class=\"weui-cell__ft\">\n" +
                "                                <input type=\"radio\" class=\"weui-check\" name=\"radio1\" id=`+ choiceKey + `>\n" +
                "                                <span class=\"weui-icon-checked\"></span>\n" +
                "                                </div>`;\n" +
                "\n" +
                "            }\n" +
                "            //----2.如果不是单选:\n" +
                "            else {\n" +
                "                divChoices.setAttribute(\"class\", \"weui-cells weui-cells_checkbox\");\n" +
                "\n" +
                "                labelChoice.setAttribute(\"class\", \"weui-cell weui-check__label\");\n" +
                "                labelChoice.setAttribute(\"for\", choiceKey);\n" +
                "                labelChoice.innerHTML = `\n" +
                "                <div class=\"weui-cell__hd\">\n" +
                "                    <input type=\"checkbox\" class=\"weui-check\" name=\"checkbox1\" id=`+ choiceKey + ` checked= \"false\">\n" +
                "                    <i class=\"weui-icon-checked\"></i>\n" +
                "                    </div>\n" +
                "                    <div class=\"weui-cell__bd\">\n" +
                "                    <p>`+ choiceText + `</p>\n" +
                "                    </div>`;\n" +
                "\n" +
                "            };\n" +
                "\n" +
                "\n" +
                "            //添加到父元素\n" +
                "            divChoices.appendChild(labelChoice);\n" +
                "            //获取到input元素\n" +
                "            let inputChoice = document.getElementById(choiceKey);\n" +
                "            //获取该元素是否被选中,如果没有选中,就用代码点击一下(设置checked不能用),取消选中状态\n" +
                "            let checked = 0;\n" +
                "            recordValueObj.numberValueMap.forEach(function (item, index, arr) {\n" +
                "                if (item.key === choiceKey) {\n" +
                "                    if (item.value > 0) {\n" +
                "                        checked = 1;\n" +
                "                        arr.splice(index, 1);\n" +
                "                    };\n" +
                "                };\n" +
                "            });\n" +
                "            recordValueObj.numberValueMap.push({ key: choiceKey, value: checked });\n" +
                "            inputChoice.click();\n" +
                "            if (checked === 1) { inputChoice.click(); }\n" +
                "            //添加事件,当状态改变时修改recordValueObj\n" +
                "            inputChoice.onchange = function () {\n" +
                "                if (formatObj.type === \"radio\") {\n" +
                "                    recordValueObj.numberValueMap.forEach(function (item, index, arr) {\n" +
                "                        recordValueObj.numberValueMap[index].value = 0;\n" +
                "                        if (item.key === choiceKey) {\n" +
                "                            recordValueObj.numberValueMap[index].value = 1;\n" +
                "                        };\n" +
                "                    });\n" +
                "                }\n" +
                "                else {\n" +
                "                    if (inputChoice.checked === true) {\n" +
                "                        recordValueObj.numberValueMap.forEach(function (item, index, arr) {\n" +
                "                            if (item.key === choiceKey) {\n" +
                "                                recordValueObj.numberValueMap[index].value = 1;\n" +
                "                            };\n" +
                "                        });\n" +
                "                    }\n" +
                "                    else {\n" +
                "                        recordValueObj.numberValueMap.forEach(function (item, index, arr) {\n" +
                "                            if (item.key === choiceKey) {\n" +
                "                                recordValueObj.numberValueMap[index].value = 0;\n" +
                "                            };\n" +
                "                        });\n" +
                "                    };\n" +
                "                };\n" +
                "                console.log(recordValueObj.numberValueMap);\n" +
                "            };\n" +
                "\n" +
                "        };\n" +
                "\n" +
                "        //==================备注信息相关==============================\n" +
                "        let inputRemark = document.getElementById(\"remark\");\n" +
                "        inputRemark.value = recordValueObj.remark;\n" +
                "        inputRemark.oninput = function () {\n" +
                "            recordValueObj.remark = inputRemark.value;\n" +
                "            console.log(recordValueObj);\n" +
                "        };\n" +
                "        //==================提供给安卓的借口:=============================\n" +
                "        window.getJSRecordView = function () {\n" +
                "            return JSON.stringify(recordValueObj);\n" +
                "        };\n" +
                "\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "</body>\n" +
                "\n" +
                "</html>");
        return entry;
    }

    //形成多数量模板,这里还是测试方法
    public RecordEntry get_multiNumberTemplate_entry(){

        RecordEntry entry=new RecordEntry();
        entry.setId("number001");
        entry.setTemplate_id("11");
        entry.setName("数值");
        entry.setInfo("记录数值");
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
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "\n" +
                "\n" +
                "<div id=\"divChoices\" class=\"weui-cells\">\n" +
                "</div>\n" +
                "<div id=\"addBtn\">\n" +
                "    <a href=\"javascript:;\" class=\"weui-btn weui-btn_primary\">\n" +
                "        添加选项\n" +
                "    </a>\n" +
                "</div>\n" +
                "\n" +
                "<div class=\"weui-cell\">\n" +
                "    <div class=\"weui-cell__bd\">\n" +
                "        <p>按钮step</p>\n" +
                "    </div>\n" +
                "    <div class=\"weui-cell__ft\">\n" +
                "        <div class=\"weui-count\">\n" +
                "            <a class=\"weui-count__btn weui-count__decrease\" id=\"btnMinusStep\"></a>\n" +
                "            <input class=\"weui-count__number\" id=\"inputBtnStep\" type=\"number\" width=\"5vw\" value=\"1\">\n" +
                "            <a class=\"weui-count__btn weui-count__increase\" id=\"btnAddStep\"></a>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "\n" +
                "<div class=\"weui-cell\">\n" +
                "    <div class=\"weui-cell__bd\">\n" +
                "        <p>默认值</p>\n" +
                "    </div>\n" +
                "    <div class=\"weui-cell__ft\">\n" +
                "        <div class=\"weui-count\">\n" +
                "            <a class=\"weui-count__btn weui-count__decrease\" id=\"btnMinusdefaultNumber\"></a>\n" +
                "            <input class=\"weui-count__number\" id=\"inputdefaultNumber\" type=\"number\" width=\"5vw\" value=\"1\">\n" +
                "            <a class=\"weui-count__btn weui-count__increase\" id=\"btnAdddefaultNumber\"></a>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "\n" +
                "<script>\n" +
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
                "                        textValue: \"数值1\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        key: \"1112\",\n" +
                "                        textValue: \"数值2\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        key: \"113\",\n" +
                "                        textValue: \"数值3\"\n" +
                "                    },\n" +
                "                ],\n" +
                "                btnStep: 1,\n" +
                "                defaultNumber: 0,\n" +
                "                type: \"checkbox\",\n" +
                "                id: \"choiceTemplate\",\n" +
                "            };\n" +
                "            jsonFormat = JSON.stringify(defaultFormat);\n" +
                "            console.log(\"因为jsonFormat原本为空,使用默认格式对象\", jsonFormat);\n" +
                "        };\n" +
                "\n" +
                "        // let formatObj = JSON.parse(\"'\" + jsonFormat + \"'\");\n" +
                "        let formatObj = JSON.parse(jsonFormat);\n" +
                "        //console.log(\"formatObj解析结果\", formatObj);\n" +
                "        console.log(\"formatObj是否为空\", formatObj === null);\n" +
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
                "                inputName.oninput = function () {\n" +
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
                "\n" +
                "        //==============按钮step相关===========\n" +
                "        let inputBtnStep = document.getElementById(\"inputBtnStep\");\n" +
                "        inputBtnStep.value = formatObj.btnStep;\n" +
                "        inputBtnStep.style.width = \"3rem\";\n" +
                "        let btnMinusStep = document.getElementById(\"btnMinusStep\");\n" +
                "        btnMinusStep.onclick = function (e) {\n" +
                "            let number = parseInt(inputBtnStep.value) - 1;\n" +
                "            if (number < 0) number = 0;\n" +
                "            inputBtnStep.value = number;\n" +
                "            formatObj.btnStep = number;\n" +
                "        };\n" +
                "        let btnAddStep = document.getElementById(\"btnAddStep\");\n" +
                "        btnAddStep.onclick = function (e) {\n" +
                "            let number = parseInt(inputBtnStep.value) + 1;\n" +
                "            if (number < 0) number = 0;\n" +
                "            inputBtnStep.value = number;\n" +
                "            formatObj.btnStep = number;\n" +
                "        };\n" +
                "\n" +
                "        //=============默认值相关===========\n" +
                "        let inputdefaultNumber = document.getElementById(\"inputdefaultNumber\");\n" +
                "        inputdefaultNumber.value = formatObj.defaultNumber;\n" +
                "        inputdefaultNumber.style.width = \"3rem\";\n" +
                "        let btnMinusdefaultNumber = document.getElementById(\"btnMinusdefaultNumber\");\n" +
                "        btnMinusdefaultNumber.onclick = function (e) {\n" +
                "            let number = parseInt(inputdefaultNumber.value) - 1;\n" +
                "            inputdefaultNumber.value = number;\n" +
                "            formatObj.defaultNumber = number;\n" +
                "        };\n" +
                "        let btnAdddefaultNumber = document.getElementById(\"btnAdddefaultNumber\");\n" +
                "        btnAdddefaultNumber.onclick = function (e) {\n" +
                "            let number = parseInt(inputdefaultNumber.value) + 1;\n" +
                "            if (number < 0) number = 0;\n" +
                "            inputdefaultNumber.value = number;\n" +
                "            formatObj.defaultNumber = number;\n" +
                "        };\n" +
                "\n" +
                "        //==============给安卓提供的借口============\n" +
                "        window.getJsFormat = function () {\n" +
                "            return JSON.stringify(formatObj);\n" +
                "        };\n" +
                "\n" +
                "    </script>\n" +
                "</body>\n" +
                "\n" +
                "</html>");
        entry.setRecord_view("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Document</title>\n" +
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
                "<div id=\"divNumbers\" class=\"weui-cells\">\n" +
                "\n" +
                "\n" +
                "</div>\n" +
                "<div class=\"weui-cells__title\">备注信息</div>\n" +
                "<div class=\"weui-cells weui-cells_form\">\n" +
                "    <div class=\"weui-cell\">\n" +
                "        <div class=\"weui-cell__bd\">\n" +
                "            <textarea class=\"weui-textarea\" id=\"remark\" placeholder=\"请输入备注内容\" rows=\"3\"></textarea>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "<script>\n" +
                "        let JsonFormat = window.androidObject.getAndroidFormat();\n" +
                "        let formatObj = JSON.parse(JsonFormat);\n" +
                "\n" +
                "\n" +
                "        let JsonRecordValue = window.androidObject.getAndroidRecordValue();\n" +
                "        if (JsonRecordValue === \"\") {\n" +
                "            let defaultValueObj = {\n" +
                "                numberValueMap: [\n" +
                "                    {\n" +
                "                        key: \"impossible key\",\n" +
                "                        value: 0,\n" +
                "                    },\n" +
                "                ],\n" +
                "                remark: \"\"\n" +
                "            };\n" +
                "            JsonRecordValue = JSON.stringify(defaultValueObj);\n" +
                "        };\n" +
                "        let recordValueObj = JSON.parse(JsonRecordValue);\n" +
                "\n" +
                "\n" +
                "\n" +
                "        //===================获取放所有选择的div======================\n" +
                "        let divNumbers = document.getElementById(\"divNumbers\");\n" +
                "        //divNumbers.innerHTML = \"\";\n" +
                "\n" +
                "        //==================根据记录值和格式添加元素=====================\n" +
                "        for (let indexList in formatObj.choiceList) {\n" +
                "            //获取该数值项的id,名称,值\n" +
                "            let choice = formatObj.choiceList[indexList];\n" +
                "            let numberKey = choice.key;\n" +
                "            let numberText = choice.textValue;\n" +
                "            let numberValue = formatObj.defaultNumber;\n" +
                "            recordValueObj.numberValueMap.forEach(function (item, index, arr) {\n" +
                "                if (item.key === numberKey) {\n" +
                "                    numberValue = item.value;\n" +
                "                };\n" +
                "            });\n" +
                "\n" +
                "            //-----创建列表的列表项\n" +
                "            //创建列表表格div\n" +
                "            let divCell = document.createElement(\"div\");\n" +
                "            divCell.setAttribute(\"class\", \"weui-cell\");\n" +
                "            //添加内容\n" +
                "            divCell.innerHTML =\n" +
                "                `<div class=\"weui-cell__bd\">\n" +
                "                    <p>`+ numberText + `</p>\n" +
                "                </div>\n" +
                "                <div class=\"weui-cell__ft\">\n" +
                "                    <div class=\"weui-count\">\n" +
                "                        <a      id=`+ \"btnMinus\" + numberKey + `    class=\"weui-count__btn weui-count__decrease\"></a>\n" +
                "                        <input  id=`+ \"input\" + numberKey + `   class=\"weui-count__number\" type=\"number\">\n" +
                "                        <a      id=`+ \"btnAdd\" + numberKey + `   class=\"weui-count__btn weui-count__increase\"></a>\n" +
                "                    </div>\n" +
                "                </div>`;\n" +
                "            divNumbers.appendChild(divCell);\n" +
                "\n" +
                "            //获取到输入框,+按钮,-按钮\n" +
                "            //修改本数值项记录值的函数\n" +
                "            let changRecordNumberValue = function (newNumberValue) {\n" +
                "                let valueObj = {\n" +
                "                    key: numberKey,\n" +
                "                    value: newNumberValue,\n" +
                "                };\n" +
                "                recordValueObj.numberValueMap.forEach(function (item, index, arr) {\n" +
                "                    if (item.key === numberKey) {\n" +
                "                        arr.splice(index, 1);\n" +
                "                    };\n" +
                "                });\n" +
                "                recordValueObj.numberValueMap.push(valueObj);\n" +
                "                console.log(recordValueObj.numberValueMap)\n" +
                "            };\n" +
                "            //输入框\n" +
                "            let inputNumber = document.getElementById(\"input\" + numberKey);\n" +
                "            inputNumber.style.width = \"12vw\";\n" +
                "            inputNumber.value = numberValue;\n" +
                "            inputNumber.onblur = function () { changRecordNumberValue(inputNumber.value); };\n" +
                "            //+按钮\n" +
                "            let btnAdd = document.getElementById(\"btnAdd\" + numberKey);\n" +
                "            console.log(btnAdd);\n" +
                "            btnAdd.onclick = function () {\n" +
                "                let newNumber = parseInt(inputNumber.value) + formatObj.btnStep;\n" +
                "                inputNumber.value = newNumber;\n" +
                "                changRecordNumberValue(newNumber);\n" +
                "            };\n" +
                "            //-按钮\n" +
                "            let btnMinus = document.getElementById(\"btnMinus\" + numberKey);\n" +
                "            console.log(btnAdd);\n" +
                "            btnMinus.onclick = function () {\n" +
                "                let newNumber = parseInt(inputNumber.value) - formatObj.btnStep;\n" +
                "                inputNumber.value = newNumber;\n" +
                "                changRecordNumberValue(newNumber);\n" +
                "            };\n" +
                "\n" +
                "        };\n" +
                "\n" +
                "        //==================备注信息相关==============================\n" +
                "        let inputRemark = document.getElementById(\"remark\");\n" +
                "        inputRemark.value = recordValueObj.remark;\n" +
                "        inputRemark.oninput = function () {\n" +
                "            recordValueObj.remark = inputRemark.value;\n" +
                "            console.log(recordValueObj);\n" +
                "        };\n" +
                "        //==================提供给安卓的借口:=============================\n" +
                "        window.getJSRecordView = function () {\n" +
                "            return JSON.stringify(recordValueObj);\n" +
                "        };\n" +
                "\n" +
                "    </script>\n" +
                "\n" +
                "</body>\n" +
                "\n" +
                "</html>");
        return entry;
    }

    //形成文本模板,这里还是测试方法
    public RecordEntry get_textTemplate_entry(){

        RecordEntry entry=new RecordEntry();
        entry.setId("text001");
        entry.setTemplate_id("11");
        entry.setName("文本");
        entry.setInfo("记录文本");
        entry.setSeparate_js("");
        entry.setFormat("");
        entry.setEdit_view("");
        entry.setRecord_view("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Document</title>\n" +
                "\n" +
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
                "    <div class=\"weui-cells__title\">文本内容:</div>\n" +
                "    <div class=\"weui-cells weui-cells_form\">\n" +
                "        <div class=\"weui-cell\">\n" +
                "            <div class=\"weui-cell__bd\">\n" +
                "                <textarea class=\"weui-textarea\" id=\"remark\" placeholder=\"请输入备注内容\" rows=\"6\"></textarea>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <script>\n" +
                "        let JsonRecordValue = window.androidObject.getAndroidRecordValue();\n" +
                "        if (JsonRecordValue === \"\") {\n" +
                "            let defaultValueObj = {\n" +
                "                numberValueMap: [\n" +
                "                    {\n" +
                "                        key: \"impossible key\",\n" +
                "                        value: 0,\n" +
                "                    },\n" +
                "                ],\n" +
                "                remark: \"\"\n" +
                "            };\n" +
                "            JsonRecordValue = JSON.stringify(defaultValueObj);\n" +
                "        };\n" +
                "        let recordValueObj = JSON.parse(JsonRecordValue);\n" +
                "\n" +
                "        //==================备注信息相关==============================\n" +
                "        let inputRemark = document.getElementById(\"remark\");\n" +
                "        inputRemark.value = recordValueObj.remark;\n" +
                "        inputRemark.oninput = function () {\n" +
                "            recordValueObj.remark = inputRemark.value;\n" +
                "            console.log(recordValueObj);\n" +
                "        };\n" +
                "        //==================提供给安卓的借口:=============================\n" +
                "        window.getJSRecordView = function () {\n" +
                "            return JSON.stringify(recordValueObj);\n" +
                "        };\n" +
                "\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "</body>\n" +
                "\n" +
                "</html>");
        return entry;
    }
}
