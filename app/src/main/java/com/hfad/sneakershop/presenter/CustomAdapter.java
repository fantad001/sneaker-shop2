package com.hfad.sneakershop.presenter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.hfad.sneakershop.R;
import com.hfad.sneakershop.model.Sneaker;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private List<Sneaker> sneakerList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        public ViewHolder(CardView v) {
            super(v);
            this.cardView = v;
        }
    }

    public interface Listener {
        void onClickDetail(int position);
    }

    private Listener listener;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public CustomAdapter(List<Sneaker> sneakerList) {
        this.sneakerList = sneakerList;
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(
                R.layout.custom_cardview_layout, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, final int position) {
        CardView cardView = (CardView) holder.cardView;

        ImageView imgView = (ImageView) cardView.findViewById(R.id.sneaker_imgview);
        TextView nameView = (TextView) cardView.findViewById(R.id.sneaker_nameview);
        TextView priceView = (TextView) cardView.findViewById(R.id.sneaker_priceview);

        Drawable drawable = ContextCompat.getDrawable(cardView.getContext(), sneakerList.get(position).getImageResourceId());
        String name = sneakerList.get(position).getName();
        imgView.setImageDrawable(drawable);
        imgView.setContentDescription(name);

        nameView.setText(name);

        int price = sneakerList.get(position).getPrice();
        priceView.setText(price + "VNĐ");

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickDetail(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sneakerList.size();
    }
}
