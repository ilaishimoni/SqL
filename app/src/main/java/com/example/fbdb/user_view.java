package com.example.fbdb;

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
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class user_view extends AppCompatActivity implements AdapterView.OnItemClickListener {

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
        setContentView(R.layout.activity_user_view);

        lvrecords = (ListView) findViewById(R.id.lvrecords_user);
        lvrecords.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lvrecords.setOnItemClickListener(this);



        hlp=new HelperDB(this);

        db = hlp.getWritableDatabase();
        tbl = new ArrayList<>();
        crsr = db.query(user_static.TABLE_USERS, null, null, null, null, null, null);
        int col1 = crsr.getColumnIndex(user_static.KEY_ID);
        int col2 = crsr.getColumnIndex(user_static.CARD_NUMBER);
        int col3 = crsr.getColumnIndex(user_static.FIRST_NAME);
        int col4 = crsr.getColumnIndex(user_static.FINAL_NAME);
        int col5 = crsr.getColumnIndex(user_static.COMPANY_NAME);
        int col6 = crsr.getColumnIndex(user_static.ID);
        int col7 = crsr.getColumnIndex(user_static.PHONE_NUMBER);

        crsr.moveToFirst();
        while (!crsr.isAfterLast()) {
            int key = crsr.getInt(col1);
            int CARD_NUMBER = crsr.getInt(col2);
            String FIRST_NAME = crsr.getString(col3);
            String FINAL_NAME = crsr.getString(col4);
            String COMPANY_NAME = crsr.getString(col5);
            String ID = crsr.getString(col6);
            String PHONE_NUMBER = crsr.getString(col7);


            String tmp = "" + key + ", " + CARD_NUMBER + ", " + FIRST_NAME + ", " + FINAL_NAME + ", " + COMPANY_NAME + ", " + ID + ", " + PHONE_NUMBER;
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
        Intent gi = new Intent(this, users.class);
        startActivity(gi);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        // position starts from 0
        // id starts from 1

        Intent gi = new Intent(this, edit_user.class);
        String rlv = String.valueOf(position+1);
        gi.putExtra("key_id", rlv);
        startActivity(gi);



    }

    public void filter(View view) {
        adb = new AlertDialog.Builder(this);

        adb.setTitle("choose filter mode");
        adb.setPositiveButton("only nvidia workers", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tbl = new ArrayList<>();

                String[] columns = null;
                String selection = user_static.COMPANY_NAME+"=?";
                String[] selectionArgs = {"nvidia"};
                String groupBy = user_static.COMPANY_NAME+"=?";
                String having = null;
                String orderBy = null;
                String limit = null;

                db = hlp.getReadableDatabase();
                crsr = db.query(user_static.TABLE_USERS, columns, selection, selectionArgs, groupBy, having, orderBy, limit);

                int col1 = crsr.getColumnIndex(user_static.CARD_NUMBER);
                int col2 = crsr.getColumnIndex(user_static.FIRST_NAME);
                int col3 = crsr.getColumnIndex(user_static.FINAL_NAME);
                int col4 = crsr.getColumnIndex(user_static.COMPANY_NAME);
                int col5 = crsr.getColumnIndex(user_static.ID);
                int col6 = crsr.getColumnIndex(user_static.PHONE_NUMBER);



                crsr.moveToFirst();
                while (!crsr.isAfterLast()) {
                    int CARD_NUMBER = crsr.getInt(col1);
                    String FIRST_NAME = crsr.getString(col2);
                    String FINAL_NAME = crsr.getString(col3);
                    String COMPANY_NAME = crsr.getString(col4);
                    int ID = crsr.getInt(col5);
                    int PHONE_NUMBER = crsr.getInt(col6);


                    String tmp = "" + CARD_NUMBER + ", " + FIRST_NAME + ", " + FINAL_NAME + ", " + COMPANY_NAME + ", " + ID + ", " + PHONE_NUMBER;
                    tbl.add(tmp);
                    crsr.moveToNext();
                }
                crsr.close();
                db.close();
                adp = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, tbl);
                lvrecords.setAdapter(adp);



            }
        });
        adb.setNegativeButton("only asus workers", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tbl = new ArrayList<>();

                String[] columns = null;
                String selection = user_static.COMPANY_NAME+"=?";
                String[] selectionArgs = {"asus"};
                String groupBy = user_static.COMPANY_NAME+"=?";
                String having = null;
                String orderBy = null;
                String limit = null;

                db = hlp.getReadableDatabase();
                crsr = db.query(user_static.TABLE_USERS, columns, selection, selectionArgs, groupBy, having, orderBy, limit);

                int col1 = crsr.getColumnIndex(user_static.CARD_NUMBER);
                int col2 = crsr.getColumnIndex(user_static.FIRST_NAME);
                int col3 = crsr.getColumnIndex(user_static.FINAL_NAME);
                int col4 = crsr.getColumnIndex(user_static.COMPANY_NAME);
                int col5 = crsr.getColumnIndex(user_static.ID);
                int col6 = crsr.getColumnIndex(user_static.PHONE_NUMBER);



                crsr.moveToFirst();
                while (!crsr.isAfterLast()) {
                    int CARD_NUMBER = crsr.getInt(col1);
                    String FIRST_NAME = crsr.getString(col2);
                    String FINAL_NAME = crsr.getString(col3);
                    String COMPANY_NAME = crsr.getString(col4);
                    int ID = crsr.getInt(col5);
                    int PHONE_NUMBER = crsr.getInt(col6);


                    String tmp = "" + CARD_NUMBER + ", " + FIRST_NAME + ", " + FINAL_NAME + ", " + COMPANY_NAME + ", " + ID + ", " + PHONE_NUMBER;
                    tbl.add(tmp);
                    crsr.moveToNext();
                }
                crsr.close();
                db.close();
                adp = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, tbl);
                lvrecords.setAdapter(adp);

            }
        });
        adb.setNeutralButton("only intel workers", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tbl = new ArrayList<>();

                String[] columns = null;
                String selection = user_static.COMPANY_NAME+"=?";
                String[] selectionArgs = {"intel"};
                String groupBy = user_static.COMPANY_NAME+"=?";
                String having = null;
                String orderBy = null;
                String limit = null;

                db = hlp.getReadableDatabase();
                crsr = db.query(user_static.TABLE_USERS, columns, selection, selectionArgs, groupBy, having, orderBy, limit);

                int col1 = crsr.getColumnIndex(user_static.CARD_NUMBER);
                int col2 = crsr.getColumnIndex(user_static.FIRST_NAME);
                int col3 = crsr.getColumnIndex(user_static.FINAL_NAME);
                int col4 = crsr.getColumnIndex(user_static.COMPANY_NAME);
                int col5 = crsr.getColumnIndex(user_static.ID);
                int col6 = crsr.getColumnIndex(user_static.PHONE_NUMBER);



                crsr.moveToFirst();
                while (!crsr.isAfterLast()) {
                    int CARD_NUMBER = crsr.getInt(col1);
                    String FIRST_NAME = crsr.getString(col2);
                    String FINAL_NAME = crsr.getString(col3);
                    String COMPANY_NAME = crsr.getString(col4);
                    int ID = crsr.getInt(col5);
                    int PHONE_NUMBER = crsr.getInt(col6);


                    String tmp = "" + CARD_NUMBER + ", " + FIRST_NAME + ", " + FINAL_NAME + ", " + COMPANY_NAME + ", " + ID + ", " + PHONE_NUMBER;
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

    public void sort(View view) {
        adb = new AlertDialog.Builder(this);

        adb.setTitle("choose sort mode");
        adb.setPositiveButton("first name (alphabetic)", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tbl = new ArrayList<>();

                String[] columns = null;
                String selection = null;
                String[] selectionArgs = null;
                String groupBy = null;
                String having = null;
                String orderBy = user_static.FIRST_NAME;
                String limit = null;

                db = hlp.getReadableDatabase();
                crsr = db.query(user_static.TABLE_USERS, columns, selection, selectionArgs, groupBy, having, orderBy, limit);

                int col1 = crsr.getColumnIndex(user_static.CARD_NUMBER);
                int col2 = crsr.getColumnIndex(user_static.FIRST_NAME);
                int col3 = crsr.getColumnIndex(user_static.FINAL_NAME);
                int col4 = crsr.getColumnIndex(user_static.COMPANY_NAME);
                int col5 = crsr.getColumnIndex(user_static.ID);
                int col6 = crsr.getColumnIndex(user_static.PHONE_NUMBER);



                crsr.moveToFirst();
                while (!crsr.isAfterLast()) {
                    int CARD_NUMBER = crsr.getInt(col1);
                    String FIRST_NAME = crsr.getString(col2);
                    String FINAL_NAME = crsr.getString(col3);
                    String COMPANY_NAME = crsr.getString(col4);
                    int ID = crsr.getInt(col5);
                    int PHONE_NUMBER = crsr.getInt(col6);


                    String tmp = "" + CARD_NUMBER + ", " + FIRST_NAME + ", " + FINAL_NAME + ", " + COMPANY_NAME + ", " + ID + ", " + PHONE_NUMBER;
                    tbl.add(tmp);
                    crsr.moveToNext();
                }
                crsr.close();
                db.close();
                adp = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, tbl);
                lvrecords.setAdapter(adp);

            }
        });
        adb.setNegativeButton("last name (alphabetic)", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tbl = new ArrayList<>();

                String[] columns = null;
                String selection = null;
                String[] selectionArgs = null;
                String groupBy = null;
                String having = null;
                String orderBy = user_static.FINAL_NAME;
                String limit = null;

                db = hlp.getReadableDatabase();
                crsr = db.query(user_static.TABLE_USERS, columns, selection, selectionArgs, groupBy, having, orderBy, limit);

                int col1 = crsr.getColumnIndex(user_static.CARD_NUMBER);
                int col2 = crsr.getColumnIndex(user_static.FIRST_NAME);
                int col3 = crsr.getColumnIndex(user_static.FINAL_NAME);
                int col4 = crsr.getColumnIndex(user_static.COMPANY_NAME);
                int col5 = crsr.getColumnIndex(user_static.ID);
                int col6 = crsr.getColumnIndex(user_static.PHONE_NUMBER);



                crsr.moveToFirst();
                while (!crsr.isAfterLast()) {
                    int CARD_NUMBER = crsr.getInt(col1);
                    String FIRST_NAME = crsr.getString(col2);
                    String FINAL_NAME = crsr.getString(col3);
                    String COMPANY_NAME = crsr.getString(col4);
                    int ID = crsr.getInt(col5);
                    int PHONE_NUMBER = crsr.getInt(col6);


                    String tmp = "" + CARD_NUMBER + ", " + FIRST_NAME + ", " + FINAL_NAME + ", " + COMPANY_NAME + ", " + ID + ", " + PHONE_NUMBER;
                    tbl.add(tmp);
                    crsr.moveToNext();
                }
                crsr.close();
                db.close();
                adp = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, tbl);
                lvrecords.setAdapter(adp);

            }
        });
        adb.setNeutralButton("company name (alphabetic)", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tbl = new ArrayList<>();

                String[] columns = null;
                String selection = null;
                String[] selectionArgs = null;
                String groupBy = null;
                String having = null;
                String orderBy = user_static.COMPANY_NAME;
                String limit = null;

                db = hlp.getReadableDatabase();
                crsr = db.query(user_static.TABLE_USERS, columns, selection, selectionArgs, groupBy, having, orderBy, limit);

                int col1 = crsr.getColumnIndex(user_static.CARD_NUMBER);
                int col2 = crsr.getColumnIndex(user_static.FIRST_NAME);
                int col3 = crsr.getColumnIndex(user_static.FINAL_NAME);
                int col4 = crsr.getColumnIndex(user_static.COMPANY_NAME);
                int col5 = crsr.getColumnIndex(user_static.ID);
                int col6 = crsr.getColumnIndex(user_static.PHONE_NUMBER);



                crsr.moveToFirst();
                while (!crsr.isAfterLast()) {
                    int CARD_NUMBER = crsr.getInt(col1);
                    String FIRST_NAME = crsr.getString(col2);
                    String FINAL_NAME = crsr.getString(col3);
                    String COMPANY_NAME = crsr.getString(col4);
                    int ID = crsr.getInt(col5);
                    int PHONE_NUMBER = crsr.getInt(col6);


                    String tmp = "" + CARD_NUMBER + ", " + FIRST_NAME + ", " + FINAL_NAME + ", " + COMPANY_NAME + ", " + ID + ", " + PHONE_NUMBER;
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
