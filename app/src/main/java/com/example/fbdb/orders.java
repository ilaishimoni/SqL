package com.example.fbdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * @author		Ilai Shimoni ilaishimoni@gmail.com
 * @version	    2.0
 * @since		26.1.22
 * Management app using SQLite Database
 */
public class orders extends AppCompatActivity {
    SQLiteDatabase db;
    HelperDB hlp;
    Cursor crsr;

    ListView lvrecords;
    ArrayList<String> tbl = new ArrayList<>();
    ArrayAdapter adp;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        lvrecords = (ListView) findViewById(R.id.lvrecords);

        hlp = new HelperDB(this);

        db = hlp.getWritableDatabase();
        tbl = new ArrayList<>();

        crsr = db.query(order_static.TABLE_ORDERS, null, null, null, null, null, null);
        int col1 = crsr.getColumnIndex(order_static.DATE_TIME);
        int col2 = crsr.getColumnIndex(order_static.WORKER);
        int col3 = crsr.getColumnIndex(order_static.MEAL_DETAILS);
        int col4 = crsr.getColumnIndex(order_static.DELIVERING_COMPANY);

        crsr.moveToFirst();
        while (!crsr.isAfterLast()) {
            String DATE_TIME = crsr.getString(col1);
            String WORKER = crsr.getString(col2);
            String MEAL_DETAILS = crsr.getString(col3);
            String DELIVERING_COMPANY = crsr.getString(col4);
            String tmp = "" + DATE_TIME + ", " + WORKER + ", " + MEAL_DETAILS + ", " + DELIVERING_COMPANY;
            tbl.add(tmp);
            crsr.moveToNext();
        }
        crsr.close();
        db.close();
        adp = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, tbl);
        lvrecords.setAdapter(adp);




    }
    /**
     * Inflate the menu;this adds items to the menu
     *
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        String st = item.getTitle().toString();

        if(st.equals("Credits")){
            Intent gi = new Intent(this, credits.class);
            startActivity(gi);
        }
        if(st.equals("Home")){
            Intent gi = new Intent(this, MainActivity.class);
            startActivity(gi);
        }
        if(st.equals("Companies")){
            Intent gi = new Intent(this, companies.class);
            startActivity(gi);
        }
        if(st.equals("Meals")){
            Intent gi = new Intent(this, meals.class);
            startActivity(gi);
        }
        if(st.equals("Orders")){
            Intent gi = new Intent(this, orders.class);
            startActivity(gi);
        }
        if(st.equals("Users")){
            Intent gi = new Intent(this, users.class);
            startActivity(gi);
        }


        return true;
    }
}