package com.example.fbdb;

import static com.example.fbdb.company_static.PRIMARY_PHONE;
import static com.example.fbdb.company_static.SECONDARY_PHONE;
import static com.example.fbdb.company_static.TABLE_COMPANIES;
import static com.example.fbdb.company_static.TAX_NUMBER;
import static com.example.fbdb.meal_static.ADD_ON;
import static com.example.fbdb.meal_static.APERITIF;
import static com.example.fbdb.meal_static.DESSERT;
import static com.example.fbdb.meal_static.DRINK;
import static com.example.fbdb.meal_static.KEY_ID;
import static com.example.fbdb.meal_static.MAIN_MEAL;
import static com.example.fbdb.meal_static.TABLE_MEALS;
import static com.example.fbdb.order_static.DATE_TIME;
import static com.example.fbdb.order_static.DELIVERING_COMPANY;
import static com.example.fbdb.order_static.MEAL_DETAILS;
import static com.example.fbdb.order_static.TABLE_ORDERS;
import static com.example.fbdb.order_static.WORKER;
import static com.example.fbdb.user_static.CARD_NUMBER;
import static com.example.fbdb.user_static.COMPANY_NAME;
import static com.example.fbdb.user_static.FINAL_NAME;
import static com.example.fbdb.user_static.FIRST_NAME;
import static com.example.fbdb.user_static.ID;
import static com.example.fbdb.user_static.PHONE_NUMBER;
import static com.example.fbdb.user_static.TABLE_USERS;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author		Ilai Shimoni ilaishimoni@gmail.com
 * @version	    2.0
 * @since		26.1.22
 * Management app using SQLite Database
 */
public class HelperDB extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "GavYam.db";
    private static final int DATABASE_VERSION = 1;
    String strCreate, strDelete;

    public HelperDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        strCreate="CREATE TABLE "+TABLE_ORDERS;
        strCreate+=" ("+KEY_ID+" INTEGER PRIMARY KEY,";

        strCreate+=" "+DATE_TIME+" TEXT,";
        strCreate+=" "+ WORKER+" TEXT,";
        strCreate+=" "+ MEAL_DETAILS+" TEXT,";
        strCreate+=" "+ DELIVERING_COMPANY+" TEXT";
        strCreate+=");";
        db.execSQL(strCreate);


        strCreate="CREATE TABLE "+TABLE_MEALS;
        strCreate+=" ("+KEY_ID+" INTEGER PRIMARY KEY,";

        strCreate+=" "+APERITIF+" TEXT,";
        strCreate+=" "+MAIN_MEAL+" TEXT,";
        strCreate+=" "+ADD_ON+" TEXT,";
        strCreate+=" "+DESSERT+" TEXT,";
        strCreate+=" "+DRINK+" TEXT";
        strCreate+=");";
        db.execSQL(strCreate);

        strCreate="CREATE TABLE "+TABLE_USERS;
        strCreate+=" ("+KEY_ID+" INTEGER PRIMARY KEY,";

        strCreate+=" "+CARD_NUMBER+" INTEGER,";
        strCreate+=" "+FIRST_NAME+" TEXT,";
        strCreate+=" "+FINAL_NAME+" TEXT,";
        strCreate+=" "+COMPANY_NAME+" TEXT,";
        strCreate+=" "+ID+" TEXT,";
        strCreate+=" "+PHONE_NUMBER+" INTEGER";
        strCreate+=");";
        db.execSQL(strCreate);


        strCreate="CREATE TABLE "+TABLE_COMPANIES;
        strCreate+=" ("+KEY_ID+" INTEGER PRIMARY KEY,";

        strCreate+=" "+TAX_NUMBER+" TEXT,";
        strCreate+=" "+COMPANY_NAME+" TEXT,";
        strCreate+=" "+PRIMARY_PHONE+" TEXT,";
        strCreate+=" "+SECONDARY_PHONE+" TEXT";
        strCreate+=");";
        db.execSQL(strCreate);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        strDelete="DROP TABLE IF EXISTS "+TABLE_ORDERS;
        db.execSQL(strDelete);
        strDelete="DROP TABLE IF EXISTS "+TABLE_USERS;
        db.execSQL(strDelete);
        strDelete="DROP TABLE IF EXISTS "+TABLE_MEALS;
        db.execSQL(strDelete);
        strDelete="DROP TABLE IF EXISTS "+TABLE_COMPANIES;
        db.execSQL(strDelete);

        onCreate(db);
    }
}
