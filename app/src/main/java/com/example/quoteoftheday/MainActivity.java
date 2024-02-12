package com.example.quoteoftheday;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView quoteTextView;
    ImageView shareImageView;
    ImageView saveImageView;

    ImageView savedImageView;

    DatabaseReference ref;

    String[] quotes = {"Believe you can and you're halfway there. - Theodore Roosevelt",
            "The only way to achieve the impossible is to believe it is possible.    - Charles Kingsleigh",
            "Success is not final, failure is not fatal: It is the courage to continue that counts.    - Winston Churchill",
            "Don't watch the clock; do what it does. Keep going. - Sam Levenson",
            "The only limit to our realization of tomorrow will be our doubts of today.    - Franklin D. Roosevelt",
            "The future belongs to those who believe in the beauty of their dreams. - Eleanor Roosevelt",
            "You are never too old to set another goal or to dream a new dream.   - C.S. Lewis",
            "The only person you should try to be better than is the person you were yesterday. ",
            "Hardships often prepare ordinary people for an extraordinary destiny.     -C.S. Lewis",
            "Success is not the key to happiness. Happiness is the key to success. If you love what you are doing, you will be successful.  - Albert Schweitzer" ,
            "Organizing is a practice of leadership whereby we define leadership as enabling others to achieve shared purpose under conditions of uncertainty.",
            "Mobilizing others to achieve purpose under conditions of uncertainty— what leaders do—challenges the hands, the head, and the heart.",
            "How do you invest in developing leadership but not in creating dependency of that leadership upon you?",
            "Hope is the belief in the probability of the possible rather than the necessity of the probable.",
            "There’s a real sweet spot between challenge and hope – leaders make pathways that keep both firmly in view."
    };
    public static List<String> savedQuotes = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        quoteTextView = findViewById(R.id.quote);
        shareImageView = findViewById(R.id.imageView2);
        saveImageView = findViewById(R.id.imageView4);
        savedImageView = findViewById(R.id.imageView3);

        // Set up initial quote
        setRandomQuote();

        // Share button click listener

        shareImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the current quote text
                String quote = quoteTextView.getText().toString();

                // Create an Intent to share the quote

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, quote);

                // Start the Activity to share the quote
                startActivity(Intent.createChooser(shareIntent, "Share Quote"));
            }
        });




        // Save button click listener
        saveImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the current quote text
                String quote = quoteTextView.getText().toString();

                // Add the quote to the list of saved quotes
                savedQuotes.add(quote);

                // Optionally, you can provide feedback to the user that the quote has been saved
                Toast.makeText(MainActivity.this, "Quote saved", Toast.LENGTH_SHORT).show();
            }
        });


        // Saved quotes button click listener
        savedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to open the SavedQuotesActivity
                Intent intent = new Intent(MainActivity.this, myquotes.class);

                // Pass the list of saved quotes to the SavedQuotesActivity
                intent.putStringArrayListExtra("savedQuotes", (ArrayList<String>) savedQuotes);

                // Start the SavedQuotesActivity
                startActivity(intent);
            }
        });

        // Generate Next Quote button click listener
        Button nextQuoteButton = findViewById(R.id.btnNext);
        nextQuoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRandomQuote();
            }
        });
    }

    private void setRandomQuote() {
        Random random = new Random();
        int randomIndex = random.nextInt(quotes.length);
        String randomQuote = quotes[randomIndex];
        quoteTextView.setText(randomQuote);
    }
}
