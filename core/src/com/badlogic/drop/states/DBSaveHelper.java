package com.badlogic.drop.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.sql.Database;
import com.badlogic.gdx.sql.DatabaseCursor;
import com.badlogic.gdx.sql.DatabaseFactory;
import com.badlogic.gdx.sql.SQLiteGdxException;

public class DBSaveHelper {
    private static Database dbHandler;

    private static final String HERO_TABLE = "hero";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_MONEY = "money";
    private static final String COLUMN_DAMAGE = "damage";

    private static final String DATABASE_NAME = "hero.db";
    private static final int DATABASE_VERSION = 1;

    // DATABASE CREATION SQL QUERY
    private static final String DATABASE_CREATE = "create table if not exists "
            + COLUMN_ID + " integer primary key autoincrement, "
            + HERO_TABLE + "(" + COLUMN_MONEY + " int not null, "
            + COLUMN_DAMAGE + " int not null);";

    public DBSaveHelper(){
        Gdx.app.log("DBTEST", "Database creation started");
        dbHandler = DatabaseFactory.getNewDatabase(DATABASE_NAME, DATABASE_VERSION, DATABASE_CREATE, null);

        dbHandler.setupDatabase();
        try{
            dbHandler.openOrCreateDatabase();
            dbHandler.execSQL(DATABASE_CREATE);
        }catch (SQLiteGdxException e){
            e.printStackTrace();
        }

        try{
            dbHandler.execSQL("INSERT INTO hero ('money') VALUES (20)");
        }catch (SQLiteGdxException e){
            e.printStackTrace();
        }

        Gdx.app.log("DBTEST", "Database created successfully");
    }

    public static void terminateDB(){
        try{
            dbHandler.closeDatabase();
        }catch (SQLiteGdxException e){
            e.printStackTrace();
        }
        dbHandler = null;
        Gdx.app.log("DBTEST", "DB DISPOSED");
    }

    public int getMoney(){
        DatabaseCursor cursor = null;
        int value = 0;
        try{
            cursor = dbHandler.rawQuery("SELECT money FROM hero ");
        }catch (SQLiteGdxException e){
            e.printStackTrace();
        }

        while(cursor.next()){
            value = Integer.getInteger(String.valueOf(cursor.getInt(1)));
            break;
        }
        return value;
    }

    public static Database getDb(){
        return dbHandler;
    }
}
