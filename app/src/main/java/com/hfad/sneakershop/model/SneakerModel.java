package com.hfad.sneakershop.model;

import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.hfad.sneakershop.model.Sneaker;
import com.hfad.sneakershop.model.sneakerDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class SneakerModel {
    private SQLiteDatabase db;
    private Cursor cursor;
    private Activity activity;

    public SneakerModel(Activity activity) {
        this.activity = activity;
    }

    public List<Sneaker> getAllSneaker() {
        SQLiteOpenHelper sneakerDatabaseHelper = new sneakerDatabaseHelper(activity.getApplication());
        List<Sneaker> sneakerList = null;
        try {
            db = sneakerDatabaseHelper.getReadableDatabase();
            cursor = db.query("SNEAKER",
                    new String[] {"NAME", "SIZE", "PRICE", "IMAGE_RESOURCE_ID"},
                    null, null, null, null, null);
            sneakerList = new ArrayList<>();
            cursor.moveToPosition(0);
            do {
                String name = cursor.getString(0);
                int size = cursor.getInt(1);
                int price = cursor.getInt(2);
                int imageResourceId = cursor.getInt(3);
                Sneaker sneaker = new Sneaker(name, size, price, imageResourceId);
                sneakerList.add(sneaker);
            } while (cursor.moveToNext());
            cursor.close();
            db.close();
        } catch (SQLException e) {
            Toast toast = Toast.makeText(activity.getApplication(), "DATA UNAVAILABLE", Toast.LENGTH_SHORT);
            toast.show();
        }
        return sneakerList;
    }
}
