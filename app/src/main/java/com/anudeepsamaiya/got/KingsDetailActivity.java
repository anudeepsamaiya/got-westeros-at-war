package com.anudeepsamaiya.got;

import android.database.sqlite.SQLiteDatabase;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.anudeepsamaiya.got.Model.KingModel;
import com.anudeepsamaiya.got.databinding.ActivityKingsDetailBinding;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by anudeepsamaiya on 7/1/17.
 */

public class KingsDetailActivity extends AppCompatActivity {

    ActivityKingsDetailBinding binding;
    SimpleDatabaseHelper databaseHelper;
    SQLiteDatabase db;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_kings_detail);
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

        String name;
        if (getIntent().getExtras() != null) {
            name = getIntent().getStringExtra("king");

            KingModel kingModel = cupboard().withDatabase(db)
                    .query(KingModel.class).withSelection("name = ?", name).get();

            if (kingModel != null) {
                binding.textKingName.setText(kingModel.getName());
                binding.textBattlesWon.setText(String.valueOf(kingModel.getBattlesWon()));
                binding.textBattlesLost.setText(String.valueOf(kingModel.getBattlesLost()));

                TextDrawable drawable = TextDrawable.builder()
                        .buildRound(kingModel.getName().substring(0, 1), ColorGenerator.MATERIAL.getRandomColor());

                binding.imageView.setImageDrawable(drawable);
            }
        }
    }
}
