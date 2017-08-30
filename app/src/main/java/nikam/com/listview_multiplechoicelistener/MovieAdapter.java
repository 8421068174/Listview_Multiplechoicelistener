package nikam.com.listview_multiplechoicelistener;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Nikam on 24/08/2017.
 */
public class MovieAdapter extends ArrayAdapter<MovieData> {

    // Declare Variables
    Context context;
    LayoutInflater inflater;
    List<MovieData> movieDataList;
    private SparseBooleanArray mSelectedItemsIds;

    public MovieAdapter(Context context, int resource, List<MovieData> movieDataList,
                        LayoutInflater inflater) {
        super(context, resource,movieDataList);

        this.context=context;
        this.movieDataList = movieDataList;


        mSelectedItemsIds = new SparseBooleanArray();
       this.inflater = LayoutInflater.from(context);
    }

    private class ViewHolder {
        ImageView poster;
        TextView movie;
        TextView rate;
    }


    public View getView(int position, View view, ViewGroup viewGroup) {
        final ViewHolder holder;


            if (view == null) {
                holder = new ViewHolder();

                view = inflater.inflate(R.layout.movie_item,null);
                // Locate the TextViews in listview_item.xml
                holder.movie = (TextView) view.findViewById(R.id.movieName);
                holder.rate = (TextView) view.findViewById(R.id.rate);

                // Locate the ImageView in listview_item.xml
                holder.poster = (ImageView) view.findViewById(R.id.poster);

                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }


            // Capture position and set to the TextViews
            holder.movie.setText(movieDataList.get(position).getMovie());
            holder.rate.setText(movieDataList.get(position).getRate());

            // Capture position and set to the ImageView
            holder.poster.setImageResource(movieDataList.get(position).getPoster());

        return view;
       }


    @Override
    public void remove(MovieData object) {
        movieDataList.remove(object);
        notifyDataSetChanged();
    }

    public List<MovieData> getMovieDataList() {
        return movieDataList;
    }

    public void toggleSelection(int position) {
        selectView(position, !mSelectedItemsIds.get(position));
    }

    public void removeSelection() {
        mSelectedItemsIds = new SparseBooleanArray();
        notifyDataSetChanged();
    }

    public void selectView(int position, boolean value) {
        if (value)
            mSelectedItemsIds.put(position, value);
        else
            mSelectedItemsIds.delete(position);
        notifyDataSetChanged();
    }

    public int getSelectedCount() {
        return mSelectedItemsIds.size();
    }

    public SparseBooleanArray getSelectedIds() {
        return mSelectedItemsIds;
    }
}
