let display_divs = document.getElementsByClassName("separateJS11");
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
            remark: "Null"
        };
        jsonRecordValue = JSON.stringify(defaultValueObj);
    };

    let recordValueObj = JSON.parse(jsonRecordValue);
    div_a.style.backgroundColor = "aqua";
    div_a.innerHTML = "";

    span_txt = document.createElement("span");
    span_txt.textContent = recordValueObj.remark;
    span_txt.style.fontSize = "1rem";
    //div_display.appendChild(document.createElement("br"));
    div_a.appendChild(span_txt);
};

