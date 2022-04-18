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
public class users extends AppCompatActivity {
    SQLiteDatabase db;
    HelperDB hlp;

    EditText Card_Number;
    EditText First_Name;
    EditText Final_Name;
    EditText Company;
    EditText ID;
    EditText Phone_Number;



    /**
     * connecting xml elements to java variables
     * @param	"launch" button
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();

        Card_Number = (EditText) findViewById(R.id.CARD_NUMBER);
        First_Name = (EditText) findViewById(R.id.FIRST_NAME);
        Final_Name = (EditText) findViewById(R.id.FINAL_NAME);
        Company = (EditText) findViewById(R.id.COMPANY);
        ID = (EditText) findViewById(R.id.ID);
        Phone_Number = (EditText) findViewById(R.id.PHONE_NUMBER);

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

        if(Card_Number.getText().toString().equals("") || First_Name.getText().toString().equals("") || Final_Name.getText().toString().equals("") || Company.getText().toString().equals("") || ID.getText().toString().equals("") || Company.getText().toString().equals("") || Phone_Number.getText().toString().equals("")){
            Toast.makeText(this, "Please fill all the lines", Toast.LENGTH_SHORT).show();
        }
        else{
            String Card_Number_string = Card_Number.getText().toString();
            String First_Name_string = First_Name.getText().toString();
            String Final_Name_string = Final_Name.getText().toString();
            String Company_string = Company.getText().toString();
            String ID_string = ID.getText().toString();
            String Phone_Number_string = Phone_Number.getText().toString();

            ContentValues cv = new ContentValues();

            cv.put(user_static.CARD_NUMBER, Card_Number_string);
            cv.put(user_static.FIRST_NAME, First_Name_string);
            cv.put(user_static.FINAL_NAME, Final_Name_string);
            cv.put(user_static.COMPANY_NAME, Company_string);
            cv.put(user_static.ID, ID_string);
            cv.put(user_static.PHONE_NUMBER, Phone_Number_string);


            db = hlp.getWritableDatabase();

            db.insert(user_static.TABLE_USERS, null, cv);

            db.close();

            Card_Number.setText("");
            First_Name.setText("");
            Final_Name.setText("");
            Company.setText("");
            ID.setText("");
            Phone_Number.setText("");

        }
    }

    public void switch_a(View view) {
        Intent gi = new Intent(this, user_view.class);
        startActivity(gi);
    }
}