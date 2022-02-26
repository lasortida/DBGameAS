package ru.samsung.itschool.dbgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainState extends AppCompatActivity {

    private TextView topPlayer;
    private TextView loserPlayer;
    private TextView countGames;
    private TextView bestPlayer;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_state);
        topPlayer = findViewById(R.id.textViewTopPlayer);
        loserPlayer = findViewById(R.id.textViewLoser);
        countGames = findViewById(R.id.textViewCount);
        bestPlayer = findViewById(R.id.textViewBestPlayer);
        dbManager = DBManager.getInstance(this);
        topPlayer.setText("Топ-игрок: " + dbManager.getTopPlayerName());
        loserPlayer.setText("Аутсайдер: " + dbManager.getLoserPlayerName());
        countGames.setText("Количество игр: " + dbManager.getCountOfGame());
        bestPlayer.setText("Постоянный пользователь: " + dbManager.getBestPlayerName());
    }
}