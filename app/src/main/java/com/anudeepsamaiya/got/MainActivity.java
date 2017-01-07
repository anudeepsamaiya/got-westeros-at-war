package com.anudeepsamaiya.got;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.anudeepsamaiya.got.Model.KingModel;
import com.anudeepsamaiya.got.Model.WarLogModel;
import com.anudeepsamaiya.got.databinding.ActivityMainBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private SimpleDatabaseHelper simpleDatabaseHelper;
    private SQLiteDatabase db;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNewActivity();
            }
        });

        binding.rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        final WarLogAdapter warLogAdapter = new WarLogAdapter(null);
        binding.rv.setAdapter(warLogAdapter);

        simpleDatabaseHelper = new SimpleDatabaseHelper(this);
        db = simpleDatabaseHelper.getWritableDatabase();

        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if (isConnected) {
            JsonService.Westeros westeros = new JsonService().getWesteros();
            westeros.getWarLog().enqueue(new Callback<List<WarLogModel>>() {
                @Override
                public void onResponse(Call<List<WarLogModel>> call, Response<List<WarLogModel>> response) {
                    try {
                        if (response.body() != null) {

                            List<WarLogModel> warLogModel = response.body();
                            warLogAdapter.updateDataset(warLogModel);
                            binding.progressBar.setVisibility(View.GONE);

                            for (WarLogModel model : warLogModel) {
                                cupboard().withDatabase(db).put(model);
                                computeBaseRatings(model);

                                computeWinningStat(model);
                            }
                            Log.d(TAG, response.body().toString());
                        } else {
                            Log.d(TAG, response.errorBody().toString());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<List<WarLogModel>> call, Throwable t) {
                    Log.d(TAG, t.toString());
                    t.printStackTrace();
                }
            });
        } else {
            Toast.makeText(this, "Please check network connection & retry", Toast.LENGTH_LONG).show();
        }
    }

    private void computeWinningStat(WarLogModel model) {

        cupboard().withDatabase(db).query(KingModel.class).list();

    }

    public void startNewActivity() {
        Intent intent = new Intent(this, KingsListActivity.class);
        startActivity(intent);
    }

    public void computeBaseRatings(WarLogModel model) {
        final int K = 32;
        final Long r1, r2;
        int defenderWinCount, defenderlostCount, attackerWinCount, attackerLostCount;

        KingModel attackerKing = cupboard().withDatabase(db)
                .query(KingModel.class)
                .withSelection("name = ?", model.getAttackerKing())
                .get();

        KingModel defenderKing = cupboard().withDatabase(db)
                .query(KingModel.class)
                .withSelection("name = ?", model.getDefenderKing())
                .get();


        if (attackerKing == null) {
            r1 = 400L;
            attackerKing = new KingModel(model.getAttackerKing(), 400L);
            attackerWinCount = attackerLostCount = 0;
        } else {
            r1 = attackerKing.getCurrentRating();
            attackerWinCount = attackerKing.getBattlesWon();
            attackerLostCount = attackerKing.getBattlesLost();
        }

        if (defenderKing == null) {
            r2 = 400L;
            defenderKing = new KingModel(model.getDefenderKing(), 400L);
            defenderWinCount = defenderlostCount = 0;
        } else {
            r2 = defenderKing.getCurrentRating();
            defenderWinCount = defenderKing.getBattlesWon();
            defenderlostCount = defenderKing.getBattlesLost();
        }

        double R1 = Math.pow(10.0, r1 / 400.0);
        double R2 = Math.pow(10.0, r2 / 400.0);

        double E1 = R1 / (R1 + R2);
        double E2 = R2 / (R1 + R2);

        double S1;
        double S2;

        if (model.getAttackerOutcome().equalsIgnoreCase("win")) {
            S1 = 1L;
            S2 = 0L;
            attackerKing.setBattlesWon(attackerWinCount + 1);
            defenderKing.setBattlesLost(defenderlostCount + 1);
        } else if (model.getAttackerOutcome().equalsIgnoreCase("loss")) {
            S1 = 0L;
            S2 = 1L;
            attackerKing.setBattlesLost(attackerLostCount + 1);
            defenderKing.setBattlesWon(defenderWinCount + 1);
        } else {
            S1 = 0.5;
            S2 = 0.5;
        }

        Long r11 = Double.valueOf(r1 + (K * (S1 - E1))).longValue();
        Long r22 = Double.valueOf(r2 + (K * (S2 - E2))).longValue();

        attackerKing.setCurrentRating(Math.abs(r11));
        cupboard().withDatabase(db).put(attackerKing);

        defenderKing.setCurrentRating(Math.abs(r22));
        cupboard().withDatabase(db).put(defenderKing);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
