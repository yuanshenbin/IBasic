package com.yuanshenbin.basic.db;


/**
 * author : yuanshenbin
 * time   : 2023/7/20
 * desc   :
 */
public class ErrorDB {
    public static final String DATABASE_TABLE = "error";
    public static final String ID = "id";
    public static final String DESC = "desc";
    public static final String CREATE_TIME = "createTime";
    public static final String ERROR = "error";
    public static final String UPLOAD = "upload";
    public static final String CUSTOM_CRASH_DATA = "customCrashData";

    public static final String CREATE_DB = "CREATE TABLE " + DATABASE_TABLE + " ( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DESC + " TEXT, " + CREATE_TIME + " TEXT, " + ERROR + " TEXT, " + UPLOAD +" INTEGER, " + CUSTOM_CRASH_DATA + " TEXT );";


}
