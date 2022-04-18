package com.example.fbdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * @author		Ilai Shimoni ilaishimoni@gmail.com
 * @version	    2.0
 * @since		26.1.22
 * Management app using SQLite Database
 */
public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    HelperDB hlp;


    /**
     * connecting xml elements to java variables
     * @param	"launch" button
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();
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
        if(st.equals("Home")){
            Intent gi = new Intent(this, MainActivity.class);
            startActivity(gi);
        }


        return true;
    }


    /**
     * sending user to the right activity
     *
     * @param	"button" press
     * @return	activity change
     */
    public void companies(View view) {
        Intent gi = new Intent(this, companies.class);
        startActivity(gi);
    }

    /**
     * sending user to the right activity
     *
     * @param	"button" press
     * @return	activity change
     */
    public void orders(View view) {
        Intent gi = new Intent(this, orders.class);
        startActivity(gi);
    }

    /**
     * sending user to the right activity
     *
     * @param	"button" press
     * @return	activity change
     */
    public void users(View view) {
        Intent gi = new Intent(this, users.class);
        startActivity(gi);
    }

    /**
     * sending user to the right activity
     *
     * @param	"button" press
     * @return	activity change
     */
    public void meals(View view) {
        Intent gi = new Intent(this, meals.class);
        startActivity(gi);
    }
}
