package com.yuanshenbin.basic.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.yuanshenbin.basic.model.ErrorModel;

import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;

/**
 * author : yuanshenbin
 * time   : 2023/7/20
 * desc   :
 */
public class DBManager {
    private DatabaseHelper databaseHelper;
    private Context context;
    private SQLiteDatabase database;
    static  int SIZE=40;
    public DBManager(Context ctx) {
        context = ctx;
    }

    public DBManager open() throws SQLDataException {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        try {
            databaseHelper.close();
        }catch (Exception e){

        }
    }

    public void insertError(ErrorModel model) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ErrorDB.DESC, model.getDesc());
        contentValues.put(ErrorDB.ERROR, model.getError());
        contentValues.put(ErrorDB.CREATE_TIME, model.getCreateTime());
        contentValues.put(ErrorDB.CUSTOM_CRASH_DATA, model.getCustomCrashData());
        contentValues.put(ErrorDB.UPLOAD, model.getUpload());
        database.insert(ErrorDB.DATABASE_TABLE, null, contentValues);
    }

    public void deleteError(long _id) {
        database.execSQL("delete  from " + ErrorDB.DATABASE_TABLE + " where id = " + _id);
    }

    public void deleteAllError() {
        database.execSQL("delete  from " + ErrorDB.DATABASE_TABLE);
    }


    /**
     * 获取所有数据
     * @return
     */
    public List<ErrorModel> ListByAll() {

        List<ErrorModel> list = new ArrayList<>();


        String query = "DELETE FROM " + ErrorDB.DATABASE_TABLE + " WHERE " + ErrorDB.ID + " NOT IN (SELECT " + ErrorDB.ID + " FROM " + ErrorDB.DATABASE_TABLE + " ORDER BY " + ErrorDB.CREATE_TIME + " DESC LIMIT 20);";
        database.execSQL(query);

        String orderBy = " order by id desc";
        try {
            Cursor cursor = database.rawQuery("select * from " + ErrorDB.DATABASE_TABLE + orderBy, null);
            if (cursor != null && cursor.getCount() > 0) {
                //移动到首位
                cursor.moveToFirst();
                for (int i = 0; i < cursor.getCount(); i++) {
                    String desc = cursor.getString(cursor.getColumnIndex(ErrorDB.DESC));
                    long id = cursor.getLong(cursor.getColumnIndex(ErrorDB.ID));
                    String error = cursor.getString(cursor.getColumnIndex(ErrorDB.ERROR));
                    String create_time = cursor.getString(cursor.getColumnIndex(ErrorDB.CREATE_TIME));
                    String custom_crash_data = cursor.getString(cursor.getColumnIndex(ErrorDB.CUSTOM_CRASH_DATA));
                    int upload = cursor.getInt(cursor.getColumnIndex(ErrorDB.UPLOAD));
                    ErrorModel model = new ErrorModel();
                    model.setDesc(desc);
                    model.setId(id);
                    model.setError(error);
                    model.setCreateTime(create_time);
                    model.setCustomCrashData(custom_crash_data);
                    model.setUpload(upload);
                    list.add(model);
                    //移动到下一位
                    cursor.moveToNext();
                }
            }
            if (cursor != null) {
                cursor.close();
            }
        } catch (Exception e) {

        }

        return list;
    }

    /**
     * 获取最新数据未上传的
     * @return
     */
    public List<ErrorModel> ListByNotUpload() {

        List<ErrorModel> list = new ArrayList<>();


        String query = "DELETE FROM " + ErrorDB.DATABASE_TABLE + " WHERE " + ErrorDB.ID + " NOT IN (SELECT " + ErrorDB.ID + " FROM " + ErrorDB.DATABASE_TABLE + " ORDER BY " + ErrorDB.CREATE_TIME + " DESC LIMIT 20);";
        database.execSQL(query);

        String sql = "SELECT * FROM " +ErrorDB.DATABASE_TABLE +" WHERE " +ErrorDB.UPLOAD+ " = 0" +" ORDER BY " +ErrorDB.CREATE_TIME;
        try {
            Cursor cursor = database.rawQuery(sql, null);
            if (cursor != null && cursor.getCount() > 0) {
                //移动到首位
                cursor.moveToFirst();
                for (int i = 0; i < cursor.getCount(); i++) {
                    String desc = cursor.getString(cursor.getColumnIndex(ErrorDB.DESC));
                    long id = cursor.getLong(cursor.getColumnIndex(ErrorDB.ID));
                    String error = cursor.getString(cursor.getColumnIndex(ErrorDB.ERROR));
                    String create_time = cursor.getString(cursor.getColumnIndex(ErrorDB.CREATE_TIME));
                    String custom_crash_data = cursor.getString(cursor.getColumnIndex(ErrorDB.CUSTOM_CRASH_DATA));
                    int upload = cursor.getInt(cursor.getColumnIndex(ErrorDB.UPLOAD));

                    ErrorModel model = new ErrorModel();
                    model.setDesc(desc);
                    model.setId(id);
                    model.setUpload(upload);
                    model.setError(error);
                    model.setCreateTime(create_time);
                    model.setCustomCrashData(custom_crash_data);
                    list.add(model);
                    //移动到下一位
                    cursor.moveToNext();
                }
            }
            if (cursor != null) {
                cursor.close();
            }
        } catch (Exception e) {

        }

        return list;
    }

    /**
     * 获取最新的1条数据
     * @return
     */
    public ErrorModel loadLast() {

        ErrorModel model =null;

        try {
            Cursor cursor = database.rawQuery("SELECT * FROM " + ErrorDB.DATABASE_TABLE + " ORDER BY " + ErrorDB.CREATE_TIME + " DESC LIMIT 1", null);
            // 检查是否有结果
            if (cursor != null && cursor.moveToFirst()) {
                // 获取查询结果
                String desc = cursor.getString(cursor.getColumnIndex(ErrorDB.DESC));
                long id = cursor.getLong(cursor.getColumnIndex(ErrorDB.ID));
                String error = cursor.getString(cursor.getColumnIndex(ErrorDB.ERROR));
                String create_time = cursor.getString(cursor.getColumnIndex(ErrorDB.CREATE_TIME));
                String custom_crash_data = cursor.getString(cursor.getColumnIndex(ErrorDB.CUSTOM_CRASH_DATA));
                int upload = cursor.getInt(cursor.getColumnIndex(ErrorDB.UPLOAD));
                model = new ErrorModel();
                model.setDesc(desc);
                model.setId(id);
                model.setError(error);
                model.setUpload(upload);
                model.setCreateTime(create_time);
                model.setCustomCrashData(custom_crash_data);
            }
            if (cursor != null) {
                cursor.close();
            }
        } catch (Exception e) {

        }
        return model;
    }


    /**
     * 更新上传
     * @param errorList
     */
    public void updateUploadStatus(List<ErrorModel> errorList) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ErrorDB.UPLOAD, 1);
        for (ErrorModel error : errorList) {
            String selection = ErrorDB.ID + " = ?";
            String[] selectionArgs = {String.valueOf(error.getId())};
            db.update(ErrorDB.DATABASE_TABLE, values, selection, selectionArgs);
        }
    }

}
