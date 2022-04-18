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

public class company_view extends AppCompatActivity implements AdapterView.OnItemClickListener {

    SQLiteDatabase db;
    HelperDB hlp;
    Cursor crsr;

    ListView lvrecords;
    ArrayList<String> tbl = new ArrayList<>();
    ArrayAdapter adp;

    AlertDialog.Builder adb;


    /**
     * connecting xml elements to java variables
     * @param	"launch" button
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_view);

        lvrecords = (ListView) findViewById(R.id.lvrecords_company);
        lvrecords.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lvrecords.setOnItemClickListener(this);


        hlp=new HelperDB(this);


        db = hlp.getWritableDatabase();
        tbl = new ArrayList<>();
        crsr = db.query(company_static.TABLE_COMPANIES, null, null, null, null, null, null);
        int col1 = crsr.getColumnIndex(company_static.KEY_ID);
        int col2 = crsr.getColumnIndex(company_static.TAX_NUMBER);
        int col3 = crsr.getColumnIndex(company_static.COMPANY_NAME);
        int col4 = crsr.getColumnIndex(company_static.PRIMARY_PHONE);
        int col5 = crsr.getColumnIndex(company_static.SECONDARY_PHONE);

        crsr.moveToFirst();
        while (!crsr.isAfterLast()) {
            int key = crsr.getInt(col1);
            int TAX_NUMBER = crsr.getInt(col2);
            String COMPANY_NAME = crsr.getString(col3);
            int PRIMARY_PHONE = crsr.getInt(col4);
            int SECONDARY_PHONE = crsr.getInt(col5);

            String tmp = "" + key + ", " + TAX_NUMBER + ", " + COMPANY_NAME + ", " + PRIMARY_PHONE + ", " + SECONDARY_PHONE;
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

    /**
     * activity switch
     */
    public void switch_b(View view) {
        Intent gi = new Intent(this, companies.class);
        startActivity(gi);
    }

    /**
     * position pressed is saved in Intent and being used in "edit" activity
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        // position starts from 0
        // id starts from 1

        Intent gi = new Intent(this, edit_company.class);
        String rlv = String.valueOf(position+1);
        gi.putExtra("key_id", rlv);
        startActivity(gi);

    }

    /**
     * filter according to users choice
     */
    public void filter(View view) {
        adb = new AlertDialog.Builder(this);

        adb.setTitle("choose filter mode");
        adb.setPositiveButton("only aroma", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tbl = new ArrayList<>();

                String[] columns = null;
                String selection = company_static.COMPANY_NAME+"=?";
                String[] selectionArgs = {"aroma"};
                String groupBy = user_static.COMPANY_NAME+"=?";
                String having = null;
                String orderBy = null;
                String limit = null;

                db = hlp.getReadableDatabase();
                crsr = db.query(company_static.TABLE_COMPANIES, columns, selection, selectionArgs, groupBy, having, orderBy, limit);

                int col1 = crsr.getColumnIndex(company_static.TAX_NUMBER);
                int col2 = crsr.getColumnIndex(company_static.COMPANY_NAME);
                int col3 = crsr.getColumnIndex(company_static.PRIMARY_PHONE);
                int col4 = crsr.getColumnIndex(company_static.SECONDARY_PHONE);

                crsr.moveToFirst();
                while (!crsr.isAfterLast()) {
                    int TAX_NUMBER = crsr.getInt(col1);
                    String COMPANY_NAME = crsr.getString(col2);
                    String PRIMARY_PHONE = crsr.getString(col3);
                    int SECONDARY_PHONE = crsr.getInt(col4);


                    String tmp = "" + TAX_NUMBER + ", " + COMPANY_NAME + ", " + PRIMARY_PHONE + ", " + SECONDARY_PHONE;
                    tbl.add(tmp);
                    crsr.moveToNext();
                }
                crsr.close();
                db.close();
                adp = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, tbl);
                lvrecords.setAdapter(adp);

            }
        });
        adb.setNegativeButton("only SegevMoshe", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tbl = new ArrayList<>();

                String[] columns = null;
                String selection = company_static.COMPANY_NAME+"=?";
                String[] selectionArgs = {"SegevMoshe"};
                String groupBy = user_static.COMPANY_NAME+"=?";
                String having = null;
                String orderBy = null;
                String limit = null;

                db = hlp.getReadableDatabase();
                crsr = db.query(company_static.TABLE_COMPANIES, columns, selection, selectionArgs, groupBy, having, orderBy, limit);

                int col1 = crsr.getColumnIndex(company_static.TAX_NUMBER);
                int col2 = crsr.getColumnIndex(company_static.COMPANY_NAME);
                int col3 = crsr.getColumnIndex(company_static.PRIMARY_PHONE);
                int col4 = crsr.getColumnIndex(company_static.SECONDARY_PHONE);

                crsr.moveToFirst();
                while (!crsr.isAfterLast()) {
                    int TAX_NUMBER = crsr.getInt(col1);
                    String COMPANY_NAME = crsr.getString(col2);
                    String PRIMARY_PHONE = crsr.getString(col3);
                    int SECONDARY_PHONE = crsr.getInt(col4);


                    String tmp = "" + TAX_NUMBER + ", " + COMPANY_NAME + ", " + PRIMARY_PHONE + ", " + SECONDARY_PHONE;
                    tbl.add(tmp);
                    crsr.moveToNext();
                }
                crsr.close();
                db.close();
                adp = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, tbl);
                lvrecords.setAdapter(adp);

            }
        });
        adb.setNeutralButton("only CafeCafe", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tbl = new ArrayList<>();

                String[] columns = null;
                String selection = company_static.COMPANY_NAME+"=?";
                String[] selectionArgs = {"CafeCafe"};
                String groupBy = user_static.COMPANY_NAME+"=?";
                String having = null;
                String orderBy = null;
                String limit = null;

                db = hlp.getReadableDatabase();
                crsr = db.query(company_static.TABLE_COMPANIES, columns, selection, selectionArgs, groupBy, having, orderBy, limit);

                int col1 = crsr.getColumnIndex(company_static.TAX_NUMBER);
                int col2 = crsr.getColumnIndex(company_static.COMPANY_NAME);
                int col3 = crsr.getColumnIndex(company_static.PRIMARY_PHONE);
                int col4 = crsr.getColumnIndex(company_static.SECONDARY_PHONE);

                crsr.moveToFirst();
                while (!crsr.isAfterLast()) {
                    int TAX_NUMBER = crsr.getInt(col1);
                    String COMPANY_NAME = crsr.getString(col2);
                    String PRIMARY_PHONE = crsr.getString(col3);
                    int SECONDARY_PHONE = crsr.getInt(col4);


                    String tmp = "" + TAX_NUMBER + ", " + COMPANY_NAME + ", " + PRIMARY_PHONE + ", " + SECONDARY_PHONE;
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

    /**
     * sort according to users choice
     */
    public void sort(View view) {
        adb = new AlertDialog.Builder(this);

        adb.setTitle("choose sort mode");
        adb.setPositiveButton("tax number downward", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tbl = new ArrayList<>();

                String[] columns = null;
                String selection = null;
                String[] selectionArgs = null;
                String groupBy = null;
                String having = null;
                String orderBy = company_static.TAX_NUMBER+" DESC";
                String limit = null;

                db = hlp.getReadableDatabase();
                crsr = db.query(company_static.TABLE_COMPANIES, columns, selection, selectionArgs, groupBy, having, orderBy, limit);

                int col1 = crsr.getColumnIndex(company_static.TAX_NUMBER);
                int col2 = crsr.getColumnIndex(company_static.COMPANY_NAME);
                int col3 = crsr.getColumnIndex(company_static.PRIMARY_PHONE);
                int col4 = crsr.getColumnIndex(company_static.SECONDARY_PHONE);

                crsr.moveToFirst();
                while (!crsr.isAfterLast()) {
                    int TAX_NUMBER = crsr.getInt(col1);
                    String COMPANY_NAME = crsr.getString(col2);
                    String PRIMARY_PHONE = crsr.getString(col3);
                    int SECONDARY_PHONE = crsr.getInt(col4);


                    String tmp = "" + TAX_NUMBER + ", " + COMPANY_NAME + ", " + PRIMARY_PHONE + ", " + SECONDARY_PHONE;
                    tbl.add(tmp);
                    crsr.moveToNext();
                }
                crsr.close();
                db.close();
                adp = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, tbl);
                lvrecords.setAdapter(adp);

            }
        });
        adb.setNegativeButton("tax number upward", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tbl = new ArrayList<>();

                String[] columns = null;
                String selection = null;
                String[] selectionArgs = null;
                String groupBy = null;
                String having = null;
                String orderBy = company_static.TAX_NUMBER;
                String limit = null;

                db = hlp.getReadableDatabase();
                crsr = db.query(company_static.TABLE_COMPANIES, columns, selection, selectionArgs, groupBy, having, orderBy, limit);

                int col1 = crsr.getColumnIndex(company_static.TAX_NUMBER);
                int col2 = crsr.getColumnIndex(company_static.COMPANY_NAME);
                int col3 = crsr.getColumnIndex(company_static.PRIMARY_PHONE);
                int col4 = crsr.getColumnIndex(company_static.SECONDARY_PHONE);

                crsr.moveToFirst();
                while (!crsr.isAfterLast()) {
                    int TAX_NUMBER = crsr.getInt(col1);
                    String COMPANY_NAME = crsr.getString(col2);
                    String PRIMARY_PHONE = crsr.getString(col3);
                    int SECONDARY_PHONE = crsr.getInt(col4);


                    String tmp = "" + TAX_NUMBER + ", " + COMPANY_NAME + ", " + PRIMARY_PHONE + ", " + SECONDARY_PHONE;
                    tbl.add(tmp);
                    crsr.moveToNext();
                }
                crsr.close();
                db.close();
                adp = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, tbl);
                lvrecords.setAdapter(adp);

            }
        });
        adb.setNeutralButton("company name upward", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tbl = new ArrayList<>();

                String[] columns = null;
                String selection = null;
                String[] selectionArgs = null;
                String groupBy = null;
                String having = null;
                String orderBy = company_static.COMPANY_NAME;
                String limit = null;

                db = hlp.getReadableDatabase();
                crsr = db.query(company_static.TABLE_COMPANIES, columns, selection, selectionArgs, groupBy, having, orderBy, limit);

                int col1 = crsr.getColumnIndex(company_static.TAX_NUMBER);
                int col2 = crsr.getColumnIndex(company_static.COMPANY_NAME);
                int col3 = crsr.getColumnIndex(company_static.PRIMARY_PHONE);
                int col4 = crsr.getColumnIndex(company_static.SECONDARY_PHONE);

                crsr.moveToFirst();
                while (!crsr.isAfterLast()) {
                    int TAX_NUMBER = crsr.getInt(col1);
                    String COMPANY_NAME = crsr.getString(col2);
                    String PRIMARY_PHONE = crsr.getString(col3);
                    int SECONDARY_PHONE = crsr.getInt(col4);


                    String tmp = "" + TAX_NUMBER + ", " + COMPANY_NAME + ", " + PRIMARY_PHONE + ", " + SECONDARY_PHONE;
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