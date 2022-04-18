package com.example.fbdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author		Ilai Shimoni ilaishimoni@gmail.com
 * @version	    2.0
 * @since		26.1.22
 * Management app using SQLite Database
 */


public class companies extends AppCompatActivity {

    SQLiteDatabase db;
    HelperDB hlp;

    EditText tax;
    EditText company;
    EditText primary;
    EditText secondary;


    /**
     * connecting xml elements to java variables
     * @param	"launch" button
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companies);

        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();

        tax = (EditText) findViewById(R.id.CARD_NUMBER);
        company = (EditText) findViewById(R.id.FIRST_NAME);
        primary = (EditText) findViewById(R.id.FINAL_NAME);
        secondary = (EditText) findViewById(R.id.COMPANY);
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
     * EditText is sent to "String" variable and data is sent to database
     *
     * @param	"submit" button press
     * @return	data sent to database
     *
     */

    public void check_insert(View view) {
        if(tax.getText().toString().equals("") || company.getText().toString().equals("") || primary.getText().toString().equals("") || secondary.getText().toString().equals("")){
            Toast.makeText(this, "Please fill all the lines", Toast.LENGTH_SHORT).show();
        }
        else{
            String tax_string = tax.getText().toString();
            String company_string = company.getText().toString();
            String primary_string = primary.getText().toString();
            String secondary_string = secondary.getText().toString();

            ContentValues cv = new ContentValues();

            cv.put(company_static.TAX_NUMBER, tax_string);
            cv.put(company_static.COMPANY_NAME, company_string);
            cv.put(company_static.PRIMARY_PHONE, primary_string);
            cv.put(company_static.SECONDARY_PHONE, secondary_string);

            db = hlp.getWritableDatabase();

            db.insert(company_static.TABLE_COMPANIES, null, cv);

            db.close();

            tax.setText("");
            company.setText("");
            primary.setText("");
            secondary.setText("");

        }



    }

    public void switch_a(View view) {
        Intent gi = new Intent(this, company_view.class);
        startActivity(gi);

    }
}