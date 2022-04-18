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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author		Ilai Shimoni ilaishimoni@gmail.com
 * @version	    2.0
 * @since		26.1.22
 * Management app using SQLite Database
 */
public class meals extends AppCompatActivity {
    SQLiteDatabase db;
    HelperDB hlp;

    EditText Aperitif;
    EditText Main_Meal;
    EditText Add_On;
    EditText Dessert;
    EditText Drink;

    EditText name;
    EditText delivering;


    /**
     * connecting xml elements to java variables
     * @param	"launch" button
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);

        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();

        Aperitif = (EditText) findViewById(R.id.CARD_NUMBER);
        Main_Meal = (EditText) findViewById(R.id.FIRST_NAME);
        Add_On = (EditText) findViewById(R.id.FINAL_NAME);
        Dessert = (EditText) findViewById(R.id.COMPANY);
        Drink = (EditText) findViewById(R.id.DRINK);

        name = (EditText) findViewById(R.id.name);
        delivering = (EditText) findViewById(R.id.delivering);



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

        if(Aperitif.getText().toString().equals("") || Main_Meal.getText().toString().equals("") || Add_On.getText().toString().equals("") || Dessert.getText().toString().equals("") || Drink.getText().toString().equals("")){
            Toast.makeText(this, "Please fill all the lines", Toast.LENGTH_SHORT).show();
        }
        else{
            String Aperitif_string = Aperitif.getText().toString();
            String Main_Meal_string = Main_Meal.getText().toString();
            String Add_On_string = Add_On.getText().toString();
            String Dessert_string = Dessert.getText().toString();
            String Drink_string = Drink.getText().toString();

            ContentValues cv = new ContentValues();

            cv.put(meal_static.APERITIF, Aperitif_string);
            cv.put(meal_static.MAIN_MEAL, Main_Meal_string);
            cv.put(meal_static.ADD_ON, Add_On_string);
            cv.put(meal_static.DESSERT, Dessert_string);
            cv.put(meal_static.DRINK, Drink_string);

            db = hlp.getWritableDatabase();

            db.insert(meal_static.TABLE_MEALS, null, cv);

            db.close();

            Aperitif.setText("");
            Main_Meal.setText("");
            Add_On.setText("");
            Dessert.setText("");
            Drink.setText("");

            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

            String delivering_string = delivering.getText().toString();
            String name_string = name.getText().toString();
            String strDate_hour = dateFormat.format(date);
            String meal = Aperitif_string + " " + Main_Meal_string + " " +  Add_On_string + " " +  Dessert_string + " " + Drink_string;

            ContentValues ncv = new ContentValues();

            ncv.put(order_static.DELIVERING_COMPANY, delivering_string);
            ncv.put(order_static.WORKER, name_string);
            ncv.put(order_static.DATE_TIME, strDate_hour);
            ncv.put(order_static.MEAL_DETAILS, meal);

            db = hlp.getWritableDatabase();

            db.insert(order_static.TABLE_ORDERS, null, ncv);

            db.close();

            delivering.setText("");
            name.setText("");
















        }



    }

    public void switch_a(View view) {
        Intent gi = new Intent(this, view_meals.class);
        startActivity(gi);
    }
}