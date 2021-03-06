package com.SmartDiary.UI.start;

import com.SmartDiary.pojo.RecordEntry;
/*那个编辑记录项的对话框,
* 在编辑完成后就会调用该借口的方法,
* 通过这样实现返回值的效果(对话框自身没有返回值),
* 开始界面和记录界面都应该实现该借口,因为它们都有编辑(新建)记录项的操作,*/
public interface On_RecordEntry_Edit_Listener {
    public void edit_recordEntry_done(RecordEntry newEntry);
}
