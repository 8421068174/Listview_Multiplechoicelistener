package nikam.com.listview_multiplechoicelistener;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView list;
    MovieAdapter movieAdapter;
    List<MovieData> movieDataList = new ArrayList<MovieData>();
    LayoutInflater inflater;

    String[] movie;
    int[] poster;
    String[] rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the view from listview_main.xml
        setContentView(R.layout.movie_main);

        // Generate sample data into string arrays
        movie = new String[]{"Airlift", "Barfi", "Ready", "Bajrangi Bhaijaan", "Dilwale", "Madras Cafe", "Queen", "Robot", "Talaash"};
        poster = new int[]{R.drawable.airlift, R.drawable.barfi, R.drawable.ready,
                R.drawable.bajrangi_bhaijaan, R.drawable.dilwale,
                R.drawable.madras_cafe, R.drawable.queen, R.drawable.robot,
                R.drawable.talaash};
        rate = new String[]{"*****", "****", "***", "*****", "**", "**", "*****", "*****", "***"};


        for (int i = 0; i < movie.length; i++) {
            MovieData movieData = new MovieData(movie[i], poster[i], rate[i]);
            movieDataList.add(movieData);
        }

        // Locate the ListView in listview_main.xml
        list = (ListView) findViewById(R.id.listview);

        // Pass results to ListViewAdapter Class

        movieAdapter = new MovieAdapter(this, R.layout.movie_item, movieDataList, inflater);

        // movieAdapter = new MovieAdapter(Context, R.layout.movie_item,movieDataList);

        // Binds the Adapter to the ListView
        list.setAdapter(movieAdapter);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        // Capture ListView item click
        list.setMultiChoiceModeListener(new MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

                // Capture total checked items
                final int checkedCount = list.getCheckedItemCount();
                // Set the CAB title according to total checked items
                mode.setTitle(checkedCount + " Selected");
                movieAdapter.toggleSelection(position);
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.activity_main, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.delete:
                        // Calls getSelectedIds method from ListViewAdapter Class
                        SparseBooleanArray selected =movieAdapter
                                .getSelectedIds();
                        // Captures all selected ids with a loop
                        for (int i = (selected.size() - 1); i >= 0; i--) {
                            if (selected.valueAt(i)) {
                                MovieData selecteditem = movieAdapter
                                        .getItem(selected.keyAt(i));
                                // Remove selected items following the ids
                                movieAdapter.remove(selecteditem);
                            }
                        }
                        // Close CAB
                        mode.finish();
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode mode)
            {
                movieAdapter.removeSelection();
            }
        });
    }
}
