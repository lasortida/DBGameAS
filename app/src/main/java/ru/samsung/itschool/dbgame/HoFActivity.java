package ru.samsung.itschool.dbgame;

import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HoFActivity extends Activity {

	private RecyclerView recyclerView;
	private Adapter adapter;
	private DBManager dbManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ho_f);

		dbManager = DBManager.getInstance(this);
		ArrayList<Result> data = dbManager.getAllResults();

		recyclerView = findViewById(R.id.recycler);
		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setHasFixedSize(true);
		adapter = new Adapter(data.size());
		recyclerView.setAdapter(adapter);
	}

}
