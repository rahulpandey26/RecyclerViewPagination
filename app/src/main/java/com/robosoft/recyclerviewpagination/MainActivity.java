package com.robosoft.recyclerviewpagination;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> mItemList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setAdapter();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.item_recycler_view);

        for (int pos = 0; pos < 10; pos++) {
            mItemList.add(getResources().getString(R.string.item_name)  + pos);
        }
    }

    private void setAdapter() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        final RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(mItemList);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(recyclerViewAdapter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) //check for scroll down
                {
                    int visibleItemCount = linearLayoutManager.getChildCount();
                    int totalItemCount = linearLayoutManager.getItemCount();
                    int firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();

                    if ((visibleItemCount + firstVisibleItem) >= totalItemCount) {
                        //Do pagination.. i.e. fetch new data
                        //Add the values in your Array here.
                        for (int pos = totalItemCount; pos < totalItemCount + 10; pos++) {
                            mItemList.add(getResources().getString(R.string.item_name) + pos);
                        }
                        //Notify the adapter about the data set change.
                        recyclerViewAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }
}
