package models;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import pe.com.neodark.reciclercardview.ItemActivity;
import pe.com.neodark.reciclercardview.R;

/**
 * Created by root on 10/05/16.
 */
public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.ViewHolder> {
    private ArrayList<Person> people;

    public PeopleAdapter(ArrayList<Person> people) {
        this.people = people;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        ImageView pictureImageView;
        CardView personCard;

        public ViewHolder(View itemView) {
            super(itemView);
            personCard = (CardView) itemView.findViewById(R.id.person_card);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            pictureImageView = (ImageView) itemView.findViewById(R.id.pictureImageView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.nameTextView.setText(people.get(position).firstName + " " +
                people.get(position).lastName);
        holder.pictureImageView.setImageResource(Integer.parseInt(people.get(position).pictureUrl));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.printf("Selected position: %d%n", position);
                Intent itemIntent = new Intent(view.getContext(), ItemActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("firstName", people.get(position).firstName);
                bundle.putString("lastName", people.get(position).lastName);
                bundle.putString("pictureUrl", people.get(position).pictureUrl);
                itemIntent.putExtras(bundle);
                view.getContext().startActivity(itemIntent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return people.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
