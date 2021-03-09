package visualAlarm.src.dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class AlarmItemSQL extends SQLiteOpenHelper {


    //context对象
    private Context context;

    //建表语句
    public static final String CREATE_ALARMITEM="create table AlarmItem(" +
            "_id                integer primary key autoincrement," +
            "name               text," +
            "begin_history      text," +
            "counting           integer," +
            "tag_list           text," +
            "deleted            integer," +
            "" +
            "mode               integer," +
            "counting_millis     integer," +
            "monitor_app        text," +
            "fixed_minute       integer," +
            "weekly_repeat      text," +
            "" +
            "content            text," +
            "" +
            "vibrate            integer," +
            "alert              text," +
            "" +
            "together_list      text)";

    public AlarmItemSQL(@Nullable Context context, @Nullable String name,  int version) {
        super(context, name, null, version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ALARMITEM);
        Toast.makeText(context,"建表成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
