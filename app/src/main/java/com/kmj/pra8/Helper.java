package com.kmj.pra8;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Helper extends SQLiteOpenHelper {
    public Helper(Context context){
        super(context,"song_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSQL="CREATE TABLE songs(kind text ," +
                "name text primary key,lyrics text)";

        try{
            db.execSQL(createSQL);
        }catch (Exception e){
            Log.e("onCreate","테이블 생성 오류");
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
