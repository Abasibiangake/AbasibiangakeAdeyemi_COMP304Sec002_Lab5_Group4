package com.example.abasibiangakeadeyemi_comp304sec002_lab5_group4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LaptopAdapter extends RecyclerView.Adapter<LaptopAdapter.ViewHolder> {
    //variable for laptop adapter class
    private ClickLaptopInterface clickLaptopInterface;
    private ArrayList<Laptop> laptopArrayList;
    private Context context;

    public LaptopAdapter(ClickLaptopInterface clickLaptopInterface, ArrayList<Laptop> laptopArrayList, Context context) {
        this.clickLaptopInterface = clickLaptopInterface;
        this.laptopArrayList = laptopArrayList;
        this.context = context;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // creating variable for our image view and text view on below line.
        private ImageView laptopImage;
        private TextView laptopName, laptopCost;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing all our variables on below line.
            laptopImage = itemView.findViewById(R.id.idLaptopImage);
            laptopName = itemView.findViewById(R.id.idLaptopName);
            laptopCost = itemView.findViewById(R.id.idLaptopCost);
        }
    }
    private void addAnimation(View itemView, int position) {
        int endPosition = -1;
        if (position > endPosition) {
            // on below line we are setting animation.
            Animation laptopAnimation= AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            itemView.setAnimation(laptopAnimation);
            endPosition = position;
        }
    }

    @NonNull
    @Override
    public LaptopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflating our layout file on below line.
        View myview = LayoutInflater.from(context).inflate(R.layout.display_laptop_list, parent, false);
        return new ViewHolder(myview);
    }

    @Override
    public void onBindViewHolder(@NonNull LaptopAdapter.ViewHolder holder, int location) {
        //set how items should be displayed in the recycler view
        Laptop laptop = laptopArrayList.get(location);
        holder.laptopName.setText(laptop.getLaptopname());
        holder.laptopCost.setText("$" + laptop.getLaptopcost());
        Picasso.get().load(laptop.getLaptopImage()).into(holder.laptopImage);

        //add animation
        addAnimation(holder.itemView, location);
        //handles if laptop item is selected.
        holder.laptopImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickLaptopInterface.onLaptopClick(location);
            }
        });

    }

    @Override
    public int getItemCount() {
        return laptopArrayList.size();
    }

    // creating a interface for on click
    public interface ClickLaptopInterface {
         void onLaptopClick(int position);
    }
}
