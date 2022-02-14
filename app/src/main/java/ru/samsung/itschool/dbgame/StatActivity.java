package ru.samsung.itschool.dbgame;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class StatActivity extends Activity {

    private DBManager db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);
        db = DBManager.getInstance(this);
        TextView textView = findViewById(R.id.textView);
        int a = db.selectMax();
        String str = "" + a;
        textView.setText(str);
    }
}
