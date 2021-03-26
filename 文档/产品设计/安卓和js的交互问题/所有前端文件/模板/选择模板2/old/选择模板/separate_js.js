
//获取所有本模板的div
let list_divDisplay=document.getElementsByClassName("separateJS11");
for(let div_display of list_divDisplay){


    // let valueJSON=div_display.getAttribute("value");

    // let formatJSON=window.androidObject.getFormat("11");

    format={
        template_id:1,//1,2,3
        choice:{
            "1":"玩手机,王者荣耀",
            "2":"看小说",
            "3":"blibli",
            "4":"看电影",
            "5":"出去玩",
            "6":"聊天",
            "7":"运动",
           },
       choiceType:"checkbox",
        };
    value={
        "1":true,
        "2":true,
        "3":true,
        "4":true,
       "5":false,
       "6":false,
       "7":false,
    };

    for(let j in value){
        let key=Object.keys(value)[j-1];
        if(value[j]==true){
            div_a=document.createElement("div");
            div_a.innerHTML=format["choice"][key];
            //div_a.innerHTML=key;
            div_display.appendChild(div_a);
        }

    }
}
