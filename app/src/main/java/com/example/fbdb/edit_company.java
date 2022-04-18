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

public class edit_company extends AppCompatActivity {
    String id;
    SQLiteDatabase db;
    HelperDB hlp;
    Cursor crsr;

    EditText TAX_et;
    EditText COMPANY_NAME_et;
    EditText PRIMARY_et;
    EditText SECONDARY_et;

    int TAX_NUMBER;
    String COMPANY_NAME;
    int PRIMARY_PHONE;
    int SECONDARY_PHONE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_company);

        TAX_et = (EditText) findViewById(R.id.CARD_NUMBER);
        COMPANY_NAME_et = (EditText) findViewById(R.id.FIRST_NAME);
        PRIMARY_et = (EditText) findViewById(R.id.FINAL_NAME);
        SECONDARY_et = (EditText) findViewById(R.id.COMPANY);

        hlp=new HelperDB(this);

        Intent si = getIntent();
        id = si.getStringExtra("key_id");

        String[] columns = null;
        String selection = company_static.KEY_ID + "=?";
        String[] selectionArgs = {id};
        String groupBy = null;
        String having = null;
        String orderBy = null;
        String limit = null;
        db=hlp.getReadableDatabase();
        crsr = db.query(company_static.TABLE_COMPANIES, null, selection, selectionArgs, null, null, null);

        int col1 = crsr.getColumnIndex(company_static.TAX_NUMBER);
        int col2 = crsr.getColumnIndex(company_static.COMPANY_NAME);
        int col3 = crsr.getColumnIndex(company_static.PRIMARY_PHONE);
        int col4 = crsr.getColumnIndex(company_static.SECONDARY_PHONE);

        crsr.moveToFirst();

        TAX_NUMBER = crsr.getInt(col1);
        COMPANY_NAME = crsr.getString(col2);
        PRIMARY_PHONE = crsr.getInt(col3);
        SECONDARY_PHONE = crsr.getInt(col4);

        crsr.close();
        db.close();

        TAX_et.setText("" + TAX_NUMBER);
        COMPANY_NAME_et.setText(COMPANY_NAME);
        PRIMARY_et.setText("" +PRIMARY_PHONE);
        SECONDARY_et.setText("" +SECONDARY_PHONE);
    }

    public void update(View view) {

        String new_TAX_NUMBER = TAX_et.getText().toString();
        String new_COMPANY_NAME = COMPANY_NAME_et.getText().toString();
        String new_PRIMARY = PRIMARY_et.getText().toString();
        String new_SECONDARY = SECONDARY_et.getText().toString();

        ContentValues cv = new ContentValues();
        db=hlp.getWritableDatabase();

        cv.put(company_static.TAX_NUMBER, new_TAX_NUMBER);
        cv.put(company_static.COMPANY_NAME, new_COMPANY_NAME);
        cv.put(company_static.PRIMARY_PHONE, new_PRIMARY);
        cv.put(company_static.SECONDARY_PHONE, new_SECONDARY);

        db.update(company_static.TABLE_COMPANIES,cv,company_static.TAX_NUMBER+"=?", new String[]{String.valueOf(TAX_NUMBER)});
        db.update(company_static.TABLE_COMPANIES,cv,company_static.COMPANY_NAME+"=?", new String[]{COMPANY_NAME});
        db.update(company_static.TABLE_COMPANIES,cv,company_static.PRIMARY_PHONE+"=?", new String[]{String.valueOf(PRIMARY_PHONE)});
        db.update(company_static.TABLE_COMPANIES,cv,company_static.SECONDARY_PHONE+"=?", new String[]{String.valueOf(SECONDARY_PHONE)});

        db.close();

        Toast.makeText(this, "Data was updated", Toast.LENGTH_SHORT).show();

        TAX_et.setText("");
        COMPANY_NAME_et.setText("");
        PRIMARY_et.setText("");
        SECONDARY_et.setText("");

    }

    public void view(View view) {
        Intent si = new Intent(this, company_view.class);
        startActivity(si);
    }
}