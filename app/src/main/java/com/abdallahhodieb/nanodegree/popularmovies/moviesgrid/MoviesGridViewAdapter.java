package com.abdallahhodieb.nanodegree.popularmovies.moviesgrid;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.abdallahhodieb.nanodegree.popularmovies.R;
import com.abdallahhodieb.nanodegree.popularmovies.api.Movie;
import com.squareup.picasso.Picasso;

public class MoviesGridViewAdapter extends ArrayAdapter<Movie> {
    private final int layoutId;
    private final static String LOG_TAG = MoviesGridViewAdapter.class.getName();

    public MoviesGridViewAdapter(Context context, int layoutResourceId) {
        super(context, layoutResourceId);
        this.layoutId = layoutResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder = getViewHolder(convertView, parent);
        holder.fillValues(getItem(position));
        return holder.getView();
    }

    private ViewHolder getViewHolder(View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            return createNewViewHolder(parent);
        } else {
            return (ViewHolder) convertView.getTag();
        }
    }

    @NonNull
    private ViewHolder createNewViewHolder(@NonNull ViewGroup parent) {
        ViewHolder holder;
        View newItem = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);
        holder = new ViewHolder(newItem);
        newItem.setTag(holder);
        return holder;
    }

    private static class ViewHolder {
        private final ImageView imageView;
        private final View view;

        ViewHolder(View item) {
            this.view = item;
            imageView = (ImageView) item.findViewById(R.id.grid_item_image);
        }

        View getView() {
            return view;
        }

        void fillValues(Movie movie) {
            String posterImagePath = movie.getPosterImagePath(Movie.PosterSize.w185);
            Picasso.with(view.getContext())
                    .load(posterImagePath)
                    .error(R.drawable.not_available)
                    .into(imageView);

        }
    }
}