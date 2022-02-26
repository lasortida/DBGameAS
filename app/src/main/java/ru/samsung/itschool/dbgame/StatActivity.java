package ru.samsung.itschool.dbgame;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class StatActivity extends Activity {

    private DBManager db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);
        db = DBManager.getInstance(this);
        TextView textView = findViewById(R.id.textView);
        ArrayList<Result> data = db.getMaxUserResults();
        String result = "";
        for (Result res: data){
            result += res.name + " - " + res.score + "\n";
        }
        textView.setText(result);
    }
}
