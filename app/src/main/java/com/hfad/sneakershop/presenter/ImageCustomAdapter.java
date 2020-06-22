package com.hfad.sneakershop.presenter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.hfad.sneakershop.R;
import com.hfad.sneakershop.model.Sneaker;

import java.util.List;

public class ImageCustomAdapter extends RecyclerView.Adapter<ImageCustomAdapter.ViewHolder> {
    private List<Sneaker> imageList;

    public ImageCustomAdapter(List<Sneaker> imageList) {
        this.imageList = imageList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        public ViewHolder(CardView view) {
            super(view);
            this.cardView = view;
        }
    }

    public interface Listener {
        void onClickImage(int position);
    }

    private Listener listener;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ImageCustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(
                R.layout.sneaker_add_image_layout, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageCustomAdapter.ViewHolder holder, final int position) {
        CardView cardView = (CardView) holder.cardView;
        ImageView imageView = (ImageView) cardView.findViewById(R.id.sneaker_add_image);

        Drawable drawable = ContextCompat.getDrawable(cardView.getContext(), imageList.get(position).getImageResourceId());
        String name = imageList.get(position).getName();

        imageView.setImageDrawable(drawable);
        imageView.setContentDescription(name);

        final int[] imgResourceId = new int[imageList.size()];

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickImage(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }
}
