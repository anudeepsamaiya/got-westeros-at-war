package com.anudeepsamaiya.got;

import android.database.sqlite.SQLiteDatabase;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;

import com.anudeepsamaiya.got.Model.KingModel;
import com.anudeepsamaiya.got.databinding.ActivityKingsBinding;

import java.util.List;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by anudeepsamaiya on 7/1/17.
 */

public class KingsListActivity extends AppCompatActivity {


    ActivityKingsBinding binding;
    SimpleDatabaseHelper databaseHelper;
    SQLiteDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_kings);

        setSupportActionBar(binding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDefaultDisplayHomeAsUpEnabled(true);
        }

        databaseHelper = new SimpleDatabaseHelper(this);
        db = databaseHelper.getReadableDatabase();

        List<KingModel> kingModelList = cupboard().withDatabase(db)
                .query(KingModel.class)
                .orderBy("currentRating desc")
                .list();

        KingsAdapter adapter = new KingsAdapter(kingModelList);

        binding.rvKings.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.rvKings.setAdapter(adapter);
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
        if (id == R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}