package com.bme.athasy.sightsnpubs;

import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Athasy on 2017. 11. 05..
 */

public class SightAdapter extends RecyclerView.Adapter<SightAdapter.SightViewHolder> {

    private final List<SightItem> items;

    public SightAdapter() {
        items = new ArrayList<>();
    }

    @Override
    public SightViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sight_list, parent, false);
        SightViewHolder viewHolder = new SightViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SightViewHolder holder, int position) {
        SightItem item = items.get(position);
        holder.nameTextView.setText(item.name);
        holder.typeTextView.setText(item.type);
        holder.iconImageButton.setImageResource(getImageResource(item.category));
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    private @DrawableRes int getImageResource(SightItem.Category category){
        @DrawableRes int ret;
        switch(category){
            case PUB:
                ret = R.drawable.ic_pub;
                break;
            case SIGHT:
                ret = R.drawable.ic_sight;
                break;
            default:
                ret = 0;
        }

        return ret;
    }

    public void addItem(SightItem item){
        items.add(item);
        notifyItemInserted(items.size()-1);
    }

    public void update(List<SightItem> sItems){
        items.clear();
        items.addAll(sItems);
        notifyDataSetChanged();
    }

    public class SightViewHolder extends RecyclerView.ViewHolder{

        ImageButton iconImageButton;
        TextView nameTextView;
        TextView typeTextView;
        Button removeButton;

        public SightViewHolder(View itemView) {
            super(itemView);
            iconImageButton = (ImageButton) itemView.findViewById(R.id.ItemIconImageButton);
            nameTextView = (TextView) itemView.findViewById(R.id.ItemNameTextView);
            typeTextView = (TextView) itemView.findViewById(R.id.ItemTypeTextView);
            removeButton = (Button) itemView.findViewById(R.id.DeleteButton);
        }
    }
}
