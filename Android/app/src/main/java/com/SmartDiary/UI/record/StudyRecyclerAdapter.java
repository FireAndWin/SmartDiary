package com.SmartDiary.UI.record;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.SmartDiary.R;
import com.SmartDiary.pojo.DayEntry;
import com.SmartDiary.service.pojoService.DayEntryService;

import java.util.ArrayList;
import java.util.List;

public class StudyRecyclerAdapter extends RecyclerView.Adapter<com.SmartDiary.UI.record.StudyRecyclerAdapter.ViewHolder>
        implements View.OnClickListener, View.OnLongClickListener {
    private List<Item>myList=new ArrayList<>();
    private List<DayEntry> entryList;
    private DayEntryService dayEntryService;
    public Context myContext;

    public StudyRecyclerAdapter(List<Item> myList, Context myContext,DayEntryService dayEntryService) {
        this.dayEntryService=dayEntryService;
        this.myList = myList;
        this.myContext = myContext;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    //点击recyclerView的一项的事件，有待实现
    @Override
    public void onClick(View v) {

    }

    /**
     * Called when a view has been clicked and held.
     *
     * @param v The view that was clicked and held.
     * @return true if the callback consumed the long click, false otherwise.
     */
    //这个是长按方法，但是根据需求，是不需要的,但是留在这吧，万一以后需要呢
    @Override
    public boolean onLongClick(View v) {
        return false;
    }

    //实现ViewHolder类，ViewHolder就是一个视图持有者，掌管着具体的视图资源
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public LinearLayout item;
        public TextView text_recordItemTime;
        public TextView text_recordItemData;


        public ViewHolder(View view) {
            super(view);
            this.item=view.findViewById(R.id.item_ll);
            this.text_recordItemTime=view.findViewById(R.id.text_recordItemTime );
            this.text_recordItemData=view.findViewById(R.id.text_recordItemData);

        }
    }


    @NonNull
    @Override
    public com.SmartDiary.UI.record.StudyRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(myContext).inflate(R.layout.item_record_tableitem, parent, false);
        return new com.SmartDiary.UI.record.StudyRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.SmartDiary.UI.record.StudyRecyclerAdapter.ViewHolder holder, int position) {
        com.SmartDiary.UI.record.StudyRecyclerAdapter.ViewHolder mholder=(com.SmartDiary.UI.record.StudyRecyclerAdapter.ViewHolder)holder;
        mholder.text_recordItemTime.setText(myList.get(position).date);
        mholder.text_recordItemData.setText(myList.get(position).text);

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public static class Item{
        public String date;
        public String text;

        public Item(String date,String text){
            this.date=date;
            this.text=text;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}


