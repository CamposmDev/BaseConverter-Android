package com.campos.baseconverter.view.activity;

import android.os.Bundle;

import com.campos.baseconverter.R;
import com.campos.baseconverter.model.UserHistory;
import com.campos.baseconverter.model.HistoryViewAdapter;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Every time a user chooses a base to convert from, the HistoryActivity will
 * display what that user input and base he/she chose.  Display it as a list view or
 * whatever android provides to display it vertically.  The user can then click on that
 * base and input he/she chose and it will either display a dialog or a fragment to
 * show the bases.
 *
 * Maybe have it where when he/she clicks on the base and input, it will ask to
 * convert it to what which can make use of the BaseConverter class more.
 *
 * Credit goes to mi em oi
 */
public class HistoryActivity extends AppCompatActivity {
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        loadRecyclerView();
    }

    public void loadRecyclerView() {
        List<UserHistory.HistoryItem> list = UserHistory.getHistory().getList();
        HistoryViewAdapter adapter = new HistoryViewAdapter(this, list);
        RecyclerView.ItemDecoration itemDecor = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rv = findViewById(R.id.recycler_show_history);
        rv.addItemDecoration(itemDecor);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }
}