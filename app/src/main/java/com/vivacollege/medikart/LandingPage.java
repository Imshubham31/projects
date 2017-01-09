package com.vivacollege.medikart;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by indianrenters on 1/9/17.
 */

public class LandingPage extends AppCompatActivity {
    ArrayList<String> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);
        DBHandler handler = new DBHandler(this);
        SQLiteDatabase db = handler.getWritableDatabase();
        String selectQuery = "SELECT  categories FROM " + handler.TABLE_MED;

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        try {
            while (cursor.moveToNext()) {
                arrayList.add(cursor.getString(0));
            }
        } finally {
            cursor.close();
        }

        ListView listView = (ListView) findViewById(R.id.listview);
        LandingAdapter adapter = new LandingAdapter(this,arrayList);
        listView.setAdapter(adapter);
    }
}
