package com.vivacollege.medikart;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by indianrenters on 1/9/17.
 */

public class DBHandler extends SQLiteOpenHelper {

    //Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "MEDIKART";

    // Contacts table name
    public static final String TABLE_MED = "medicine";

    public DBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    String arrData[] = {"Allergies",
            "Blood / Nutrition Disorders",
            "Breathing Problems",
            "Cancer",
            "Deficiencies",
            "Ear / Nose / Throat / Mouth",
            "Endocrine Disorders",
            "Eye Conditions",
            "Gastrointestinal Conditions",
            "The Genito-urinary System",
            "Heart Problems",
            "HRT",
            "The Immune System",
            "Immunisation",
            "Infections",
            "Inflammation",
            "Kidney Conditions",
            "Metabolic Disorders",
            "Muscles / Joints",
            "Nausea / Vomiting",
            "The Nervous System",
            "Pain",
            "The Reproductive System",
            "Skin Conditions"
    };

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_MED + "( _id INTEGER PRIMARY KEY AUTOINCREMENT, categories TEXT, medicine_name TEXT, medicine_price TEXT, medicine_description TEXT, medicine_image TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
        insertData(db);
    }

    public void insertData(SQLiteDatabase db){
        for (int i = 0; i<arrData.length;i++) {
            String query = "INSERT INTO medicine (categories, medicine_name, medicine_price, medicine_description, medicine_image) VALUES('" + arrData[i] + "', 'ABCD', '200', 'wqeartyu ƒdsfdfgh vcxvbhcv', 'dfds');";
            db.execSQL(query);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
