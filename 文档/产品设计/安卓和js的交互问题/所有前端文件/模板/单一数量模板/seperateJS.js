//获取所有本模板的div
let list_divDisplay=document.getElementsByClassName("separateJSTPLsn01");

for(let div_display of list_divDisplay){

    //从div总获取记录值json
    // let jsonValue=div_display.getAttribute("value");
    let jsonRecordValue="";
    if(jsonRecordValue===""){
        let defaultValueObj={
            TPLsn01:{
                number_value:4,
                txt_value:"今天玩了4个小时的手机.",
            },
        };
        jsonRecordValue=JSON.stringify(defaultValueObj);
    };
    let recordValueObj=JSON.parse(jsonRecordValue);
    console.log(recordValueObj);

    span_number=document.createElement("span");
    span_txt=document.createElement("span");

    span_number.textContent=recordValueObj.TPLsn01.number_value;
    span_number.style.fontSize="2rem";

    span_txt.textContent=recordValueObj.TPLsn01.txt_value;
    span_txt.style.fontSize="1.2rem";
    
    div_display.appendChild(span_number);
    div_display.appendChild(document.createElement("br"));
    div_display.appendChild(span_txt);
}
