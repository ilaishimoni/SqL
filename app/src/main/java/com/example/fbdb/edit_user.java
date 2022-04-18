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

public class edit_user extends AppCompatActivity {
    String id;
    SQLiteDatabase db;
    HelperDB hlp;
    Cursor crsr;

    EditText CARD_NUMBER_et;
    EditText FIRST_NAME_et;
    EditText FINAL_NAME_et;
    EditText COMPANY_et;
    EditText ID_et;
    EditText PHONE_NUMBER_et;

    int CARD_NUMBER;
    String FIRST_NAME;
    String FINAL_NAME;
    String COMPANY_NAME;
    int ID;
    int PHONE_NUMBER;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        CARD_NUMBER_et = (EditText) findViewById(R.id.CARD_NUMBER);
        FIRST_NAME_et = (EditText) findViewById(R.id.FIRST_NAME);
        FINAL_NAME_et = (EditText) findViewById(R.id.FINAL_NAME);
        COMPANY_et = (EditText) findViewById(R.id.COMPANY);
        ID_et = (EditText) findViewById(R.id.ID);
        PHONE_NUMBER_et = (EditText) findViewById(R.id.PHONE_NUMBER);


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
        crsr = db.query(user_static.TABLE_USERS, null, selection, selectionArgs, null, null, null);

        int col1 = crsr.getColumnIndex(user_static.CARD_NUMBER);
        int col2 = crsr.getColumnIndex(user_static.FIRST_NAME);
        int col3 = crsr.getColumnIndex(user_static.FINAL_NAME);
        int col4 = crsr.getColumnIndex(user_static.COMPANY_NAME);
        int col5 = crsr.getColumnIndex(user_static.ID);
        int col6 = crsr.getColumnIndex(user_static.PHONE_NUMBER);


        crsr.moveToFirst();

        CARD_NUMBER = crsr.getInt(col1);
        FIRST_NAME = crsr.getString(col2);
        FINAL_NAME = crsr.getString(col3);
        COMPANY_NAME = crsr.getString(col4);
        ID = crsr.getInt(col5);
        PHONE_NUMBER = crsr.getInt(col6);


        crsr.close();
        db.close();

        CARD_NUMBER_et.setText("" + CARD_NUMBER);
        FIRST_NAME_et.setText(FIRST_NAME);
        FINAL_NAME_et.setText(FINAL_NAME);
        COMPANY_et.setText(COMPANY_NAME);
        ID_et.setText("" + ID);
        PHONE_NUMBER_et.setText("" + PHONE_NUMBER);

    }

    public void update(View view) {
        String new_CARD_NUMBER = CARD_NUMBER_et.getText().toString();
        String new_FIRST_NAME = FIRST_NAME_et.getText().toString();
        String new_FINAL_NAME = FINAL_NAME_et.getText().toString();
        String new_COMPANY = COMPANY_et.getText().toString();
        String new_ID = ID_et.getText().toString();
        String new_PHONE_NUMBER = PHONE_NUMBER_et.getText().toString();


        ContentValues cv = new ContentValues();
        db=hlp.getWritableDatabase();

        cv.put(user_static.CARD_NUMBER, new_CARD_NUMBER);
        cv.put(user_static.FIRST_NAME, new_FIRST_NAME);
        cv.put(user_static.FINAL_NAME, new_FINAL_NAME);
        cv.put(user_static.COMPANY_NAME, new_COMPANY);
        cv.put(user_static.ID, new_ID);
        cv.put(user_static.PHONE_NUMBER, new_PHONE_NUMBER);



        db.update(user_static.TABLE_USERS,cv,user_static.CARD_NUMBER+"=?", new String[]{String.valueOf(CARD_NUMBER)});
        db.update(user_static.TABLE_USERS,cv,user_static.FIRST_NAME+"=?", new String[]{FIRST_NAME});
        db.update(user_static.TABLE_USERS,cv,user_static.FINAL_NAME+"=?", new String[]{FINAL_NAME});
        db.update(user_static.TABLE_USERS,cv,user_static.COMPANY_NAME+"=?", new String[]{COMPANY_NAME});
        db.update(user_static.TABLE_USERS,cv,user_static.ID+"=?", new String[]{String.valueOf(ID)});
        db.update(user_static.TABLE_USERS,cv,user_static.PHONE_NUMBER+"=?", new String[]{String.valueOf(PHONE_NUMBER)});


        db.close();

        Toast.makeText(this, "Data was updated", Toast.LENGTH_SHORT).show();

        CARD_NUMBER_et.setText("");
        FIRST_NAME_et.setText("");
        FINAL_NAME_et.setText("");
        COMPANY_et.setText("");
        ID_et.setText("");
        PHONE_NUMBER_et.setText("");

    }

    public void view(View view) {
        Intent si = new Intent(this, user_view.class);
        startActivity(si);
    }
}