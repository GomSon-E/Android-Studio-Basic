package org.techtown.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "person.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "person";
    public static final String PERSON_ID = "_id";
    public static final String PERSON_NAME = "name";
    public static final String PERSON_AGE = "age";
    public static final String PERSON_MOBILE = "mobile";

    public static final String[] ALL_COLUMNS = {PERSON_ID, PERSON_NAME, PERSON_AGE, PERSON_MOBILE};

    private static final String CREATE_TABLE =
            "create table " + TABLE_NAME + " (" +
                    PERSON_ID + " integer primary key autoincrement, " +
                    PERSON_NAME + " text, " +
                    PERSON_AGE + " integer, " +
                    PERSON_MOBILE + " text" + ")";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    // 이전에 데이터베이스가 생성된 기록이 없을 때, 새로운 DB 생성
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    // 이전에 데이터베이스가 생성된 기록이 있을 때, 해당 DB를 업그레이드
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }
}
