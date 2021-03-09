package visualAlarm.src.dataBase;

import androidx.appcompat.app.AppCompatActivity;
import visualAlarm.src.R;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity_Test_DB extends AppCompatActivity {

    public static final String TAG = "DB_Activity";

    //和数据库模块交互的对象
    DataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__test__d_b);

        dataManager=new DataManager(this);
        test();
    }

    public long getID(){
        EditText editText=findViewById(R.id.input);
        return Long.parseLong( editText.getText().toString());
    }

    //------测试方法,主要是给控件添加事件
    public void test(){
        Button create_alarmItem=findViewById(R.id.create_table);
        create_alarmItem.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                                                                  }
                                            }
        );

        final Button add_alarmItem=findViewById(R.id.add_item);
        add_alarmItem.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    dataManager.add_alarmItem(new AlarmItem());
                                                }
                                            }
        );

        Button delete_item=findViewById(R.id.delete_item);
        delete_item.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               dataManager.delete_alarmItem(getID());
                                           }
                                       }
        );

        Button query_one=findViewById(R.id.query_one);
        query_one.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             AlarmItem alarmItem =dataManager.query_alarmItem(getID());

                                                if(alarmItem==null){
                                                    Log.d(TAG, "onClick: "+"要查询的id不存在,返回的是空对象");
                                                }
                                                else
                                                    Log.d(TAG, "onClick: "+"要查询的id存在,展示如下:");
                                                    alarmItem.show();
                                         }
                                     }
        );

        Button query_all=findViewById(R.id.query_all);
        query_all.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             ArrayList<AlarmItem> arr=dataManager.get_all_alarmItem();
                                             for(AlarmItem alarmItem:arr){
                                                 alarmItem.show();
                                             }
                                         }
                                     }
        );
    }
}