package com.hfad.sneakershop.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.hfad.sneakershop.R;
import com.hfad.sneakershop.model.ImageModel;
import com.hfad.sneakershop.model.Sneaker;
import com.hfad.sneakershop.model.SneakerModel;
import com.hfad.sneakershop.model.sneakerDatabaseHelper;
import com.hfad.sneakershop.presenter.ImageCustomAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(pager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                getDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Hàm xử lý dữ liệu trong dialog
    private void getDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_create_products);

        RecyclerView imgRecyclerView = (RecyclerView) dialog.findViewById(R.id.img_recyclerview);

        SneakerModel sModel = new SneakerModel(MainActivity.this);
        ImageModel model = new ImageModel(MainActivity.this);

        final List<Sneaker> sneakerList = (ArrayList<Sneaker>) sModel.getAllSneaker();
        final List<Sneaker> imageList = (ArrayList<Sneaker>) model.getAllImage();

        ImageCustomAdapter adapter = new ImageCustomAdapter(imageList);
        imgRecyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        imgRecyclerView.setLayoutManager(layoutManager);

        adapter.setListener(new ImageCustomAdapter.Listener() {
            @Override
            public void onClickImage(int position) {
                imageList.get(position).getImageResourceId();
            }
        });

        EditText addName = (EditText) dialog.findViewById(R.id.sneaker_add_name);
        Spinner addSize = (Spinner) dialog.findViewById(R.id.sneaker_add_size);
        EditText addPrice = (EditText) dialog.findViewById(R.id.sneaker_add_price);
        Button btnCreate = (Button) dialog.findViewById(R.id.btn_create);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "Save Successful", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }

    //Hàm xử lý các tablayout
    private class SectionPageAdapter extends FragmentPagerAdapter {
        public SectionPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new SneakerFragment();
                case 1:
                    return new AccessoriesFragment();
                case 2:
                    return new BlogsFragment();
            }
            return null;
        }

        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getResources().getString(R.string.sneakers_tab);
                case 1:
                    return getResources().getString(R.string.accessories_tab);
                case 2:
                    return getResources().getString(R.string.blogs_tab);
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}