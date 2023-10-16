package com.example.myapplication;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.Hashtable;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<Item> itemList;
    private Hashtable<String, Integer> itemsBackground = new Hashtable<>();

    public ItemAdapter(List<Item> itemList) {
        itemsBackground.put("Нервы", R.drawable.nerves_background);
        itemsBackground.put("Кровь", R.drawable.blood_background);
        itemsBackground.put("Кости", R.drawable.bones_background);
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.nameTextView.setText(item.getName());
        holder.descriptionTextView.setText(item.getDescription());

        // Добавьте отображение "details"
        holder.detailsTextView.setText(item.getDetails());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                intent.putExtra("details", item.getDetails());
                v.getContext().startActivity(intent);
            }
        });

        holder.itemView.setBackgroundResource(itemsBackground.get(item.getDescription()));

        if ("Juice".equals(item.getDescription()) ||
                "Milk".equals(item.getDescription()) ||
                "Vodka".equals(item.getDescription()) ||
                "Beer".equals(item.getDescription())) {
            holder.nameTextView.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.black));
            holder.descriptionTextView.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.black));
        } else {
            holder.nameTextView.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.white));
            holder.descriptionTextView.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.white));
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setItems(List<Item> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView descriptionTextView;
        TextView detailsTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textViewName);
            descriptionTextView = itemView.findViewById(R.id.textViewDescription);
            detailsTextView = itemView.findViewById(R.id.textViewDetails);
        }
    }
}
