package com.example.wdks.newwdksapp.DataBase;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Administrator on 2016/10/11 0011.
 * 数据库的辅助类，主要负责创建和打开数据库以及对数据库的版本进行管理
 */
public class RecordDBHelper extends SQLiteOpenHelper {

    public RecordDBHelper(Context context, int version) {
        super(context, "temp.db", null, version);
    }

    public RecordDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists records (_id integer primary key autoincrement,name text)");
        Log.e("TAG", "建表完成");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
