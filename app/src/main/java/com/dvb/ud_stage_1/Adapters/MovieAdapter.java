package com.dvb.ud_stage_1.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dvb.ud_stage_1.Activities.MovieReviewActivity;
import com.dvb.ud_stage_1.Model.Movie;
import com.dvb.ud_stage_1.R;
import com.dvb.ud_stage_1.RoundedTransformation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dmitrybondarenko on 14.11.17.
 */

public class MovieAdapter extends RecyclerView.Adapter
        <MovieAdapter.RecyclerViewHolder> {

    private Context context;
    private ArrayList<Movie> mPopMovies = new ArrayList<Movie>();


    public MovieAdapter(ArrayList<Movie> arrayList) {
        this.mPopMovies = arrayList;
    }



    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);

        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {

        final Movie popMovie = mPopMovies.get(position);

        holder.title.setText(popMovie.getTitle());
        Picasso.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w185" + popMovie.getPoster_path())
                .transform(new RoundedTransformation(5, 0))
                .error(R.mipmap.ic_launcher)
                .into(holder.poster_path);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Toast.makeText(view.getContext(), "You've clicked position: "
                        + position, Toast.LENGTH_SHORT).show();

                // Start The MovieReview Activity
                Intent intent = new Intent(view.getContext(), MovieReviewActivity.class);

                // Pass the data (movie id) to the new activity
                intent.putExtra("MOVIE_ID", popMovie.getId());

                view.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        if (mPopMovies == null) {
            return 0;
        }
        return mPopMovies.size();
    }


    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        int id;
        ImageView poster_path;
        TextView title;

        public RecyclerViewHolder(View view) {
            super(view);

            poster_path = (ImageView) view.findViewById(R.id.img);
            title = (TextView) view.findViewById(R.id.f_name);

        }
    }

}