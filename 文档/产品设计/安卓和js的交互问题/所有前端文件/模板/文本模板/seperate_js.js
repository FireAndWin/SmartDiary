(function () {
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
        //div_a.style.backgroundColor = "aqua";
        div_a.innerHTML = "";

        span_txt = document.createElement("span");
        span_txt.textContent = recordValueObj.remark;
        span_txt.style.fontSize = "1rem";
        span_txt.style.marginLeft = "1rem";
        //div_display.appendChild(document.createElement("br"));
        div_a.appendChild(span_txt);


        gap = document.createElement("p");
        gap.textContent = " ";
        gap.style.fontSize = "0.8rem";
        gap.style.marginLeft = "1rem";
        gap.style.marginBotton = "1rem";
        div_a.appendChild(gap);

    };
    return "seperateJs done!";
})();

