package net.idawn.punch.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by xixi on 2014/7/30.
 */
public class MydbOpenHelper extends SQLiteOpenHelper{
    public MydbOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table passenger(id integer primary key autoincrement,name varchar(64),tel varchar(64),counts integer)";
        sqLiteDatabase.execSQL(sql); // 完成数据库的创建
        sql = "create table record(id integer primary key autoincrement,pass_id integer,time DATETIME,FOREIGN KEY(pass_id) REFERENCES passenger(id))";
        sqLiteDatabase.execSQL(sql); // 完成数据库的创建
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}
