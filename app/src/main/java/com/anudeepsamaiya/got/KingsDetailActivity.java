package com.anudeepsamaiya.got;

import android.database.sqlite.SQLiteDatabase;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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

        databaseHelper = new SimpleDatabaseHelper(this);
        db = databaseHelper.getReadableDatabase();

        String name;
        if (getIntent().getExtras() != null) {
            name = getIntent().getStringExtra("name");

            KingModel kingModel = cupboard().withDatabase(db)
                    .query(KingModel.class).withSelection("name = ?", name).get();

            if (kingModel != null) {
                binding.textKingName.setText(kingModel.getName());
                binding.textBattlesWon.setText(kingModel.getBattlesWon());
                binding.textBattlesLost.setText(kingModel.getBattlesLost());
            }
        }
    }
}
