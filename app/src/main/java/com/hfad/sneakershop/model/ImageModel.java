package com.hfad.sneakershop.model;

import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ImageModel {
    private SQLiteDatabase db;
    private Cursor cursor;
    private Activity activity;

    public ImageModel(Activity activity) {
        this.activity = activity;
    }

    public List<Sneaker> getAllImage() {
        List<Sneaker> imageList = null;
        SQLiteOpenHelper sqLiteOpenHelper = new sneakerDatabaseHelper(activity.getApplication());
        try {
            db = sqLiteOpenHelper.getReadableDatabase();
            cursor = db.query("SNEAKER",
                    new String[] {"IMAGE_RESOURCE_ID"},
                    null,null,null,null,null);
            imageList = new ArrayList<>();
            cursor.moveToPosition(0);
            do {
                int imageResourceId = cursor.getInt(0);
                Sneaker sneaker = new Sneaker(imageResourceId);
                imageList.add(sneaker);
            } while (cursor.moveToNext());
            cursor.close();
            db.close();
        } catch (SQLException e) {
            Toast toast = Toast.makeText(activity.getApplication(), "CAN'T LOAD IMAGE", Toast.LENGTH_SHORT);
            toast.show();
        }
        return imageList;
    }
}
