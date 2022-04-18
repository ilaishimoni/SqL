package com.example.fbdb;

import static com.example.fbdb.meal_static.TABLE_MEALS;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class view_meals extends AppCompatActivity implements AdapterView.OnItemClickListener {

    SQLiteDatabase db;
    HelperDB hlp;
    Cursor crsr;

    ListView lvrecords;
    ArrayList<String> tbl = new ArrayList<>();
    ArrayAdapter adp;

    AlertDialog.Builder adb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_meals);

        lvrecords = (ListView) findViewById(R.id.lvrecords_meals);
        lvrecords.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lvrecords.setOnItemClickListener(this);

        hlp=new HelperDB(this);

        db = hlp.getWritableDatabase();
        tbl = new ArrayList<>();
        crsr = db.query(TABLE_MEALS, null, null, null, null, null, null);
        int col1 = crsr.getColumnIndex(meal_static.KEY_ID);
        int col2 = crsr.getColumnIndex(meal_static.APERITIF);
        int col3 = crsr.getColumnIndex(meal_static.MAIN_MEAL);
        int col4 = crsr.getColumnIndex(meal_static.ADD_ON);
        int col5 = crsr.getColumnIndex(meal_static.DESSERT);
        int col6 = crsr.getColumnIndex(meal_static.DRINK);

        crsr.moveToFirst();
        while (!crsr.isAfterLast()) {
            int key = crsr.getInt(col1);
            String APERITIF = crsr.getString(col2);
            String MAIN_MEAL = crsr.getString(col3);
            String ADD_ON = crsr.getString(col4);
            String DESSERT = crsr.getString(col5);
            String DRINK = crsr.getString(col6);

            String tmp = "" + key + ", " + APERITIF + ", " + MAIN_MEAL + ", " + ADD_ON + ", " + DESSERT + ", " + DRINK;
            tbl.add(tmp);
            crsr.moveToNext();
        }
        crsr.close();
        db.close();
        adp = new ArrayAdapter<String>(
                this, R.layout.support_simple_spinner_dropdown_item, tbl);
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

    /**
     * sending user to the right activity
     *
     * @param	item selection
     * @return	activity change
     */
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

    public void switch_b(View view) {
        Intent gi = new Intent(this, meals.class);
        startActivity(gi);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        // position starts from 0
        // id starts from 1

        Intent gi = new Intent(this, edit_meal.class);
        String rlv = String.valueOf(position+1);
        gi.putExtra("key_id", rlv);
        startActivity(gi);

    }

    public void sort(View view) {
        adb = new AlertDialog.Builder(this);

        adb.setTitle("choose sort mode");
        adb.setPositiveButton("Drink name (alphabetic)", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                tbl = new ArrayList<>();

                String[] columns = null;
                String selection = null;
                String[] selectionArgs = null;
                String groupBy = null;
                String having = null;
                String orderBy = meal_static.DRINK;
                String limit = null;

                db=hlp.getReadableDatabase();
                crsr = db.query(TABLE_MEALS, columns, selection, selectionArgs, groupBy, having, orderBy, limit);

                int col1 = crsr.getColumnIndex(meal_static.KEY_ID);
                int col2 = crsr.getColumnIndex(meal_static.APERITIF);
                int col3 = crsr.getColumnIndex(meal_static.MAIN_MEAL);
                int col4 = crsr.getColumnIndex(meal_static.ADD_ON);
                int col5 = crsr.getColumnIndex(meal_static.DESSERT);
                int col6 = crsr.getColumnIndex(meal_static.DRINK);

                crsr.moveToFirst();
                while (!crsr.isAfterLast()) {
                    int key = crsr.getInt(col1);
                    String APERITIF = crsr.getString(col2);
                    String MAIN_MEAL = crsr.getString(col3);
                    String ADD_ON = crsr.getString(col4);
                    String DESSERT = crsr.getString(col5);
                    String DRINK = crsr.getString(col6);

                    String tmp = "" + key + ", " + APERITIF + ", " + MAIN_MEAL + ", " + ADD_ON + ", " + DESSERT + ", " + DRINK;
                    tbl.add(tmp);
                    crsr.moveToNext();
                }
                crsr.close();
                db.close();
                adp = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, tbl);
                lvrecords.setAdapter(adp);


            }
        });
        adb.setNegativeButton("add_on (alphabetic)", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                tbl = new ArrayList<>();

                String[] columns = null;
                String selection = null;
                String[] selectionArgs = null;
                String groupBy = null;
                String having = null;
                String orderBy = meal_static.ADD_ON;
                String limit = null;

                db=hlp.getReadableDatabase();
                crsr = db.query(TABLE_MEALS, columns, selection, selectionArgs, groupBy, having, orderBy, limit);

                int col1 = crsr.getColumnIndex(meal_static.KEY_ID);
                int col2 = crsr.getColumnIndex(meal_static.APERITIF);
                int col3 = crsr.getColumnIndex(meal_static.MAIN_MEAL);
                int col4 = crsr.getColumnIndex(meal_static.ADD_ON);
                int col5 = crsr.getColumnIndex(meal_static.DESSERT);
                int col6 = crsr.getColumnIndex(meal_static.DRINK);

                crsr.moveToFirst();
                while (!crsr.isAfterLast()) {
                    int key = crsr.getInt(col1);
                    String APERITIF = crsr.getString(col2);
                    String MAIN_MEAL = crsr.getString(col3);
                    String ADD_ON = crsr.getString(col4);
                    String DESSERT = crsr.getString(col5);
                    String DRINK = crsr.getString(col6);

                    String tmp = "" + key + ", " + APERITIF + ", " + MAIN_MEAL + ", " + ADD_ON + ", " + DESSERT + ", " + DRINK;
                    tbl.add(tmp);
                    crsr.moveToNext();
                }
                crsr.close();
                db.close();
                adp = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, tbl);
                lvrecords.setAdapter(adp);


            }
        });
        adb.setNeutralButton("dessert (alphabetic downward)", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                tbl = new ArrayList<>();

                String[] columns = null;
                String selection = null;
                String[] selectionArgs = null;
                String groupBy = null;
                String having = null;
                String orderBy = meal_static.DESSERT+" DESC";
                String limit = null;

                db=hlp.getReadableDatabase();
                crsr = db.query(TABLE_MEALS, columns, selection, selectionArgs, groupBy, having, orderBy, limit);

                int col1 = crsr.getColumnIndex(meal_static.KEY_ID);
                int col2 = crsr.getColumnIndex(meal_static.APERITIF);
                int col3 = crsr.getColumnIndex(meal_static.MAIN_MEAL);
                int col4 = crsr.getColumnIndex(meal_static.ADD_ON);
                int col5 = crsr.getColumnIndex(meal_static.DESSERT);
                int col6 = crsr.getColumnIndex(meal_static.DRINK);

                crsr.moveToFirst();
                while (!crsr.isAfterLast()) {
                    int key = crsr.getInt(col1);
                    String APERITIF = crsr.getString(col2);
                    String MAIN_MEAL = crsr.getString(col3);
                    String ADD_ON = crsr.getString(col4);
                    String DESSERT = crsr.getString(col5);
                    String DRINK = crsr.getString(col6);

                    String tmp = "" + key + ", " + APERITIF + ", " + MAIN_MEAL + ", " + ADD_ON + ", " + DESSERT + ", " + DRINK;
                    tbl.add(tmp);
                    crsr.moveToNext();
                }
                crsr.close();
                db.close();
                adp = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, tbl);
                lvrecords.setAdapter(adp);


            }
        });
        AlertDialog ad = adb.create();
        ad.show();
    }

    public void filter(View view) {
        adb = new AlertDialog.Builder(this);

        adb.setTitle("choose filter mode");
        adb.setPositiveButton("only hamburger (Main meal)", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                tbl = new ArrayList<>();

                String[] columns = null;
                String selection = meal_static.MAIN_MEAL+"=?";
                String[] selectionArgs = {"hamburger"};
                String groupBy = null;
                String having = null;
                String orderBy = null;
                String limit = null;

                db=hlp.getReadableDatabase();
                crsr = db.query(TABLE_MEALS, columns, selection, selectionArgs, groupBy, having, orderBy, limit);

                int col1 = crsr.getColumnIndex(meal_static.KEY_ID);
                int col2 = crsr.getColumnIndex(meal_static.APERITIF);
                int col3 = crsr.getColumnIndex(meal_static.MAIN_MEAL);
                int col4 = crsr.getColumnIndex(meal_static.ADD_ON);
                int col5 = crsr.getColumnIndex(meal_static.DESSERT);
                int col6 = crsr.getColumnIndex(meal_static.DRINK);

                crsr.moveToFirst();
                while (!crsr.isAfterLast()) {
                    int key = crsr.getInt(col1);
                    String APERITIF = crsr.getString(col2);
                    String MAIN_MEAL = crsr.getString(col3);
                    String ADD_ON = crsr.getString(col4);
                    String DESSERT = crsr.getString(col5);
                    String DRINK = crsr.getString(col6);

                    String tmp = "" + key + ", " + APERITIF + ", " + MAIN_MEAL + ", " + ADD_ON + ", " + DESSERT + ", " + DRINK;
                    tbl.add(tmp);
                    crsr.moveToNext();
                }
                crsr.close();
                db.close();
                adp = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, tbl);
                lvrecords.setAdapter(adp);
            }
        });
        adb.setNegativeButton("only coke (Drink)", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                tbl = new ArrayList<>();

                String[] columns = null;
                String selection = meal_static.DRINK+"=?";
                String[] selectionArgs = {"coke"};
                String groupBy = null;
                String having = null;
                String orderBy = null;
                String limit = null;

                db=hlp.getReadableDatabase();
                crsr = db.query(TABLE_MEALS, columns, selection, selectionArgs, groupBy, having, orderBy, limit);

                int col1 = crsr.getColumnIndex(meal_static.KEY_ID);
                int col2 = crsr.getColumnIndex(meal_static.APERITIF);
                int col3 = crsr.getColumnIndex(meal_static.MAIN_MEAL);
                int col4 = crsr.getColumnIndex(meal_static.ADD_ON);
                int col5 = crsr.getColumnIndex(meal_static.DESSERT);
                int col6 = crsr.getColumnIndex(meal_static.DRINK);

                crsr.moveToFirst();
                while (!crsr.isAfterLast()) {
                    int key = crsr.getInt(col1);
                    String APERITIF = crsr.getString(col2);
                    String MAIN_MEAL = crsr.getString(col3);
                    String ADD_ON = crsr.getString(col4);
                    String DESSERT = crsr.getString(col5);
                    String DRINK = crsr.getString(col6);

                    String tmp = "" + key + ", " + APERITIF + ", " + MAIN_MEAL + ", " + ADD_ON + ", " + DESSERT + ", " + DRINK;
                    tbl.add(tmp);
                    crsr.moveToNext();
                }
                crsr.close();
                db.close();
                adp = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, tbl);
                lvrecords.setAdapter(adp);



            }
        });
        adb.setNeutralButton("only cake (Dessert)", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tbl = new ArrayList<>();

                String[] columns = null;
                String selection = meal_static.DESSERT+"=?";
                String[] selectionArgs = {"cake"};
                String groupBy = null;
                String having = null;
                String orderBy = null;
                String limit = null;

                db=hlp.getReadableDatabase();
                crsr = db.query(TABLE_MEALS, columns, selection, selectionArgs, groupBy, having, orderBy, limit);

                int col1 = crsr.getColumnIndex(meal_static.KEY_ID);
                int col2 = crsr.getColumnIndex(meal_static.APERITIF);
                int col3 = crsr.getColumnIndex(meal_static.MAIN_MEAL);
                int col4 = crsr.getColumnIndex(meal_static.ADD_ON);
                int col5 = crsr.getColumnIndex(meal_static.DESSERT);
                int col6 = crsr.getColumnIndex(meal_static.DRINK);

                crsr.moveToFirst();
                while (!crsr.isAfterLast()) {
                    int key = crsr.getInt(col1);
                    String APERITIF = crsr.getString(col2);
                    String MAIN_MEAL = crsr.getString(col3);
                    String ADD_ON = crsr.getString(col4);
                    String DESSERT = crsr.getString(col5);
                    String DRINK = crsr.getString(col6);

                    String tmp = "" + key + ", " + APERITIF + ", " + MAIN_MEAL + ", " + ADD_ON + ", " + DESSERT + ", " + DRINK;
                    tbl.add(tmp);
                    crsr.moveToNext();
                }
                crsr.close();
                db.close();
                adp = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, tbl);
                lvrecords.setAdapter(adp);


            }
        });
        AlertDialog ad = adb.create();
        ad.show();
    }

}