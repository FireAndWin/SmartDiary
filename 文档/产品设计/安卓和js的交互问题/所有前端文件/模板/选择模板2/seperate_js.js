let display_divs = document.getElementsByClassName("separateJS13");
for (let j in display_divs) {
    let div_a = display_divs[j];

    let jsonRecordValue = div_a.getAttribute("value");
    if (jsonRecordValue === "") {
        let defaultValueObj = {
            numberValueMap: [
                {
                    key: "impossible key",
                    value: 0,
                },
            ],
            remark: "  "
        };
        jsonRecordValue = JSON.stringify(defaultValueObj);
    };

    let recordValueObj = JSON.parse(jsonRecordValue);
    //div_a.style.backgroundColor = "aqua";
    div_a.innerHTML = "";

    let jsonFormat = window.getFormat("13");
    let formatObj = JSON.parse(jsonFormat);
    //span_txt.textContent = jsonFormat;

    //==================根据记录值和格式添加元素=====================
    for (let indexList in formatObj.choiceList) {
        //获取该数值项的id,名称,值
        let choice = formatObj.choiceList[indexList];
        let numberKey = choice.key;
        let numberText = choice.textValue;
        let numberValue = formatObj.defaultNumber;
        recordValueObj.numberValueMap.forEach(function (item, index, arr) {
            if (item.key === numberKey) {
                numberValue = item.value;
            };
        });
        if (numberValue === 1) {

            let choice_item = document.createElement("p");
            choice_item.textContent = numberText;
            choice_item.style.fontSize = "1rem";
            choice_item.style.marginLeft = "1rem";
            //div_display.appendChild(document.createElement("br"));
            div_a.appendChild(choice_item);
        };        // let br = document.createElement("div");
        // br.innerHTML = "<br/>";
        // div_a.appendChild(br);
        //div_a.appendChild(document.createElement("br"));
    };

    span_txt = document.createElement("span");
    span_txt.textContent = recordValueObj.remark;
    span_txt.style.fontSize = "0.8rem";
    span_txt.style.marginLeft = "1rem";
    div_a.appendChild(span_txt);
};

