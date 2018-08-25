package com.example.wdks.newwdksapp.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/11 0011.
 * 搜索记录操作类
 */

public class RecordDAO {
    private RecordDBHelper recordDBHelper;
    private SQLiteDatabase recordDataBase;

    public RecordDAO(Context context) {
        recordDBHelper = new RecordDBHelper(context, 1);
    }

    //添加搜索记录(已经存在不添加，不存在再添加)
    public void addRecords(String record) {
        if (!isHasRecord(record)) {
            recordDataBase = recordDBHelper.getReadableDatabase();
            ContentValues values = new ContentValues();
            values.put("name", record);
            //添加
            recordDataBase.insert("records", null, values);
            //关闭
            recordDataBase.close();
        }
    }

    //判断是否有该条搜索记录
    public boolean isHasRecord(String record) {
        boolean isHasRecord = false;
        recordDataBase = recordDBHelper.getReadableDatabase();
        Cursor cursor = recordDataBase.query("records", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            if (record.equals(cursor.getString(cursor.getColumnIndexOrThrow("name")))) {
                isHasRecord = true;
            }
        }
        //关闭数据库
        cursor.close();
        recordDataBase.close();
        return isHasRecord;
    }

    //获取全部搜索记录
    public List getRecordsList() {
        List recordsList = new ArrayList();
        recordDataBase = recordDBHelper.getReadableDatabase();
        Cursor cursor = recordDataBase.query("records", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            recordsList.add(name);
        }
        cursor.close();
        recordDataBase.close();
        return recordsList;
    }

    //模糊查询
    public List querySimilarRecord(String record) {
        String queryStr = "select _id,name from records where name like '%" + record + "%' order by _id desc";
        List similarRecords = new ArrayList();
        Cursor cursor = recordDBHelper.getReadableDatabase().rawQuery(queryStr, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            similarRecords.add(name);
        }
        cursor.close();
        return similarRecords;
    }

    //清空搜索记录
    public void deleteAllRecords() {
        recordDataBase = recordDBHelper.getWritableDatabase();
        recordDataBase.execSQL("delete from records");

        recordDataBase.close();
    }

    //删除一条记录
    public void deleteOneRecord(String record) {
        recordDataBase = recordDBHelper.getWritableDatabase();
        recordDataBase.execSQL("delete from records where name=?", new String[]{record});
        recordDataBase.close();
    }
}
