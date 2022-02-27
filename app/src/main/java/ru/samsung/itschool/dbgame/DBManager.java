package ru.samsung.itschool.dbgame;

import java.io.File;
import java.util.ArrayList;
import java.util.SplittableRandom;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
	/*
	 * TABLES: ------- RESULTS SCORE INTEGER USER VARCHAR
	 */
	private Context context;
	private String DB_NAME = "game.db";

	private SQLiteDatabase db;

	private static DBManager dbManager;

	public static DBManager getInstance(Context context) {
		if (dbManager == null) {
			dbManager = new DBManager(context);
		}
		return dbManager;
	}

	private DBManager(Context context) {
		this.context = context;
		db = context.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
		createTablesIfNeedBe(); 
	}

	void addResult(String username, int score) {
		//db.execSQL("INSERT INTO RESULTS VALUES ('" + username + "', " + score + ");DROP TABLE RESULTS");
		ContentValues contentValues = new ContentValues();
		contentValues.put("USERNAME", username);
		contentValues.put("SCORE", score);
		db.insert("RESULTS", null, contentValues);
	}

	/////////////////////////////////////////////////
	//user1 150                                    //
	//INSERT INTO RESULTS VALUES ('"USER 1"', 150);//
	/////////////////////////////////////////////////

	int gamesCount(){
		Cursor a = db.rawQuery("SELECT COUNT(*) FROM RESULTS", null);
		a.moveToFirst();
		return a.getInt(0);
	}

	int selectMax(){
		Cursor cursor = db.rawQuery("SELECT MAX(score) FROM RESULTS", null);
		cursor.moveToFirst();
		return cursor.getInt(0);
	}

	ArrayList<Result> getAllResults() {
		ArrayList<Result> data = new ArrayList<Result>();
		Cursor cursor = db.rawQuery("SELECT * FROM RESULTS;", null);
		boolean hasMoreData = cursor.moveToFirst();

		while (hasMoreData) {
			String name = cursor.getString(0);
			int score = cursor.getInt(1);
			data.add(new Result(name, score));
			hasMoreData = cursor.moveToNext();
		}

		return data;
	}

	private void createTablesIfNeedBe() {
			db.execSQL("CREATE TABLE IF NOT EXISTS RESULTS (USERNAME TEXT, SCORE INTEGER);");
	}

//	ArrayList<Result> getAllUserResults(String username, int minScore) {
//		ArrayList<Result> data = new ArrayList<Result>();
//
//		Cursor cursor = db.rawQuery("SELECT * FROM RESULTS WHERE USERNAME = ? AND SCORE >= ?", new String[]{username, minScore+""});
//
//		boolean hasMoreData = cursor.moveToFirst();
//
//		while (hasMoreData) {
//			String name = cursor.getString(cursor.getColumnIndex("USERNAME"));
//			int score = Integer.parseInt(cursor.getString(cursor
//					.getColumnIndex("SCORE")));
//			data.add(new Result(name, score));
//			hasMoreData = cursor.moveToNext();
//		}
//
//		return data;
//	}

	ArrayList<Result> getMaxUserResults() {
		ArrayList<Result> data = new ArrayList<Result>();
		Cursor cursor = db.query("RESULTS", new String[]{"USERNAME", "MAX(SCORE) AS MS"}, null, null, "USERNAME", null, "MS DESC");
		boolean hasMoreData = cursor.moveToFirst();

		while (hasMoreData) {
			//String name = cursor.getString(cursor.getColumnIndex("USERNAME"));
//			int score = Integer.parseInt(cursor.getString(cursor
//					.getColumnIndex("SCORE")));
			String name = cursor.getString(0);
			int score = cursor.getInt(1);
			data.add(new Result(name, score));
			hasMoreData = cursor.moveToNext();
		}

		return data;
	}

	String getTopPlayerName(){
		String name = "";
		Cursor cursor = db.query("RESULTS", new String[]{"USERNAME", "MAX(SCORE)"}, null, null, "USERNAME", null, "MAX(SCORE)");
		boolean hasMoreData = cursor.moveToFirst();
		int count = 0;

		while (hasMoreData) {
			name = cursor.getString(0);
			hasMoreData = cursor.moveToNext();
			count += 1;
		}

		if (count >= 1){
			return name;
		}
		return "None";
	}

	String getBestPlayerName(){
		String name = "";
		Cursor cursor = db.query("RESULTS", new String[]{"USERNAME", "COUNT(USERNAME)"}, null, null, "USERNAME", null, "COUNT(USERNAME)");
		boolean hasMoreData = cursor.moveToFirst();
		int count = 0;

		while (hasMoreData) {
			name = cursor.getString(0);
			hasMoreData = cursor.moveToNext();
			count += 1;
		}

		if (count >= 1){
			return name;
		}
		return "None";
	}

	String getLoserPlayerName(){
		String name = "";
		Cursor cursor = db.query("RESULTS", new String[]{"USERNAME", "MAX(SCORE)"}, null, null, "USERNAME", null, "MAX(SCORE) DESC");
		boolean hasMoreData = cursor.moveToFirst();
		int count = 0;

		while (hasMoreData) {
			name = cursor.getString(0);
			hasMoreData = cursor.moveToNext();
			count += 1;
		}
        if (count > 1){
        	return name;
		}
        return "None";
	}

	int getCountOfGame(){
		int count = 0;
		Cursor cursor = db.query("RESULTS", new String[]{"COUNT(USERNAME)"}, null, null, null, null, "");
		boolean hasMoreData = cursor.moveToFirst();

		while (hasMoreData) {
			count = cursor.getInt(0);
			hasMoreData = cursor.moveToNext();
		}

		return count;
	}

}