package com.anudeepsamaiya.got;

import android.database.sqlite.SQLiteDatabase;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

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

}