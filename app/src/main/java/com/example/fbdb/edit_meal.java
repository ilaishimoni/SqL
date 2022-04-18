package com.example.fbdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class edit_meal extends AppCompatActivity {
    String id;
    SQLiteDatabase db;
    HelperDB hlp;
    Cursor crsr;

    EditText APERITIF_et;
    EditText MAIN_MEAL_et;
    EditText ADD_ON_et;
    EditText DESSERT_et;
    EditText DRINK_et;

    String APERITIF;
    String MAIN_MEAL;
    String ADD_ON;
    String DESSERT;
    String DRINK;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_meal);

        APERITIF_et = (EditText) findViewById(R.id.CARD_NUMBER);
        MAIN_MEAL_et = (EditText) findViewById(R.id.FIRST_NAME);
        ADD_ON_et = (EditText) findViewById(R.id.FINAL_NAME);
        DESSERT_et = (EditText) findViewById(R.id.COMPANY);
        DRINK_et = (EditText) findViewById(R.id.DRINK);


        hlp=new HelperDB(this);

        Intent si = getIntent();
        id = si.getStringExtra("key_id");

        String[] columns = null;
        String selection = meal_static.KEY_ID + "=?";
        String[] selectionArgs = {id};
        String groupBy = null;
        String having = null;
        String orderBy = null;
        String limit = null;
        db=hlp.getReadableDatabase();
        crsr = db.query(meal_static.TABLE_MEALS, null, selection, selectionArgs, null, null, null);

        int col1 = crsr.getColumnIndex(meal_static.APERITIF);
        int col2 = crsr.getColumnIndex(meal_static.MAIN_MEAL);
        int col3 = crsr.getColumnIndex(meal_static.ADD_ON);
        int col4 = crsr.getColumnIndex(meal_static.DESSERT);
        int col5 = crsr.getColumnIndex(meal_static.DRINK);

        crsr.moveToFirst();

        APERITIF = crsr.getString(col1);
        MAIN_MEAL = crsr.getString(col2);
        ADD_ON = crsr.getString(col3);
        DESSERT = crsr.getString(col4);
        DRINK = crsr.getString(col5);

        crsr.close();
        db.close();

        APERITIF_et.setText(APERITIF);
        MAIN_MEAL_et.setText(MAIN_MEAL);
        ADD_ON_et.setText(ADD_ON);
        DESSERT_et.setText(DESSERT);
        DRINK_et.setText(DRINK);


    }

    public void update(View view) {
        String new_APERITIF = APERITIF_et.getText().toString();
        String new_MAIN_MEAL = MAIN_MEAL_et.getText().toString();
        String new_ADD_ON = ADD_ON_et.getText().toString();
        String new_DESSERT = DESSERT_et.getText().toString();
        String new_DRINK = DRINK_et.getText().toString();


        ContentValues cv = new ContentValues();
        db=hlp.getWritableDatabase();

        cv.put(meal_static.APERITIF, new_APERITIF);
        cv.put(meal_static.MAIN_MEAL, new_MAIN_MEAL);
        cv.put(meal_static.ADD_ON, new_ADD_ON);
        cv.put(meal_static.DESSERT, new_DESSERT);
        cv.put(meal_static.DRINK, new_DRINK);



        db.update(meal_static.TABLE_MEALS,cv,meal_static.APERITIF+"=?", new String[]{APERITIF});
        db.update(meal_static.TABLE_MEALS,cv,meal_static.MAIN_MEAL+"=?", new String[]{MAIN_MEAL});
        db.update(meal_static.TABLE_MEALS,cv,meal_static.ADD_ON+"=?", new String[]{ADD_ON});
        db.update(meal_static.TABLE_MEALS,cv,meal_static.DESSERT+"=?", new String[]{DESSERT});
        db.update(meal_static.TABLE_MEALS,cv,meal_static.DRINK+"=?", new String[]{DRINK});


        db.close();

        Toast.makeText(this, "Data was updated", Toast.LENGTH_SHORT).show();

        APERITIF_et.setText("");
        MAIN_MEAL_et.setText("");
        ADD_ON_et.setText("");
        DESSERT_et.setText("");
        DRINK_et.setText("");

    }

    public void view(View view) {
        Intent si = new Intent(this, view_meals.class);
        startActivity(si);
    }
}