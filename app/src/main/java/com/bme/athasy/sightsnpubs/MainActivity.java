package com.bme.athasy.sightsnpubs;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NewItemDialogFragment.INewItemDialogListener{
    private RecyclerView recyclerView;
    private SightAdapter adapter;


    private void initRecyclerView(){
        recyclerView = (RecyclerView) findViewById(R.id.ListRecyclerView);
        adapter = new SightAdapter();
        loadItemsInBackground();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.AddNewSight);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new NewItemDialogFragment().show(getSupportFragmentManager(), NewItemDialogFragment.TAG);
            }
        });
        initRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadItemsInBackground() {
        new AsyncTask<Void, Void, List<SightItem>>() {

            @Override
            protected List<SightItem> doInBackground(Void... voids) {
                return SightItem.listAll(SightItem.class);
            }

            @Override
            protected void onPostExecute(List<SightItem> sightItems) {
                super.onPostExecute(sightItems);
                adapter.update(sightItems);
            }
        }.execute();
    }

    @Override
    public void onItemCreated(SightItem newItem) {
        adapter.addItem(newItem);
    }
}
