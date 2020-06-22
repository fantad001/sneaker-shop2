package com.hfad.sneakershop.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hfad.sneakershop.R;
import com.hfad.sneakershop.model.Sneaker;
import com.hfad.sneakershop.presenter.CustomAdapter;
import com.hfad.sneakershop.model.SneakerModel;

import java.util.ArrayList;
import java.util.List;

public class SneakerFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView sneakerRecyclerView  = (RecyclerView) getLayoutInflater().inflate(
                R.layout.fragment_sneaker, container, false);
        SneakerModel model = new SneakerModel(getActivity());
        List<Sneaker> sneakerList = (ArrayList<Sneaker>) model.getAllSneaker();
        CustomAdapter adapter = new CustomAdapter(sneakerList);
        sneakerRecyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        sneakerRecyclerView.setLayoutManager(layoutManager);
        adapter.setListener(new CustomAdapter.Listener() {
            @Override
            public void onClickDetail(int position) {

            }
        });
        return sneakerRecyclerView;
    }
}