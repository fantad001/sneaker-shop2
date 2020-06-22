package com.hfad.sneakershop.model;

import android.content.ContentValues;
import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.hfad.sneakershop.R;

public class sneakerDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "sneakershop";
    private static final int DB_VERSION = 1;

    public sneakerDatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, newVersion);
    }

    public void inseartSneaker (SQLiteDatabase db, String name, int size, int price, int imageResourceId) {
        ContentValues sneakerValues = new ContentValues();
        sneakerValues.put("NAME", name);
        sneakerValues.put("SIZE", size);
        sneakerValues.put("PRICE", price);
        sneakerValues.put("IMAGE_RESOURCE_ID", imageResourceId);
        db.insert("SNEAKER", null, sneakerValues);
    }

    private void updateMyDatabase (SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("CREATE TABLE SNEAKER (" +
                "_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NAME TEXT, " +
                "SIZE INTEGER, " +
                "PRICE INTEGER, " +
                "IMAGE_RESOURCE_ID INTEGER);");
        inseartSneaker(db, "Vans Old Skool", 40, 1750000, R.drawable.vans_old_skool);
        inseartSneaker(db, "Stan Smith", 43, 2300000, R.drawable.stan_smith);
        inseartSneaker(db, "Nike Jordan", 42, 3700000, R.drawable.nike_jordan);
        inseartSneaker(db, "Converse All Star", 39, 1500000, R.drawable.converse_all_star);
        inseartSneaker(db, "Nike Air Max", 44, 1600000, R.drawable.nike_air_max);
        inseartSneaker(db, "Alexander Mcqueen", 41, 3500000, R.drawable.alexander_mcqueen);
    }
}
