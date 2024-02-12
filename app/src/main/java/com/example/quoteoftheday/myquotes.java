package com.example.quoteoftheday;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class myquotes extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyQuotesAdapter adapter;

    // Define SQLite database variables
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myquotes);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<String> savedQuotes = new ArrayList<>();

        // Create the adapter with an empty list initially
        adapter = new MyQuotesAdapter(savedQuotes);
        recyclerView.setAdapter(adapter);

        // Initialize SQLite database
        DBHelper dbHelper = new DBHelper(this);
        database = dbHelper.getReadableDatabase();

        // Retrieve saved quotes from SQLite database
        retrieveSavedQuotes();

    }

    // Method to retrieve saved quotes from SQLite database
    private void retrieveSavedQuotes() {
        List<String> savedQuotes = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM myquotes", null);
        if (cursor.moveToFirst()) {
            do {
                String quote = cursor.getString(1);
                savedQuotes.add(quote);
            } while (cursor.moveToNext());
        }
        cursor.close();
        Log.d("q", savedQuotes.toString() + "******");
        // Update the adapter with the retrieved quotes
        adapter.setQuotes(savedQuotes);
        adapter.notifyDataSetChanged();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the database connection
        if (database != null) {
            database.close();
        }
    }
}
