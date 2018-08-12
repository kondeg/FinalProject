package buildit.kondeg.udacity.edu.joke;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import baking.kondeg.udacity.edu.jokeproducer.R;
import buildit.kondeg.udacity.edu.jokeprovider.JokeConstants;

public class JokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String joke = null;
        if (getIntent().hasExtra(JokeConstants.intentJoke)) {
            joke = getIntent().getStringExtra(JokeConstants.intentJoke);
        }
        TextView textViewJoke = (TextView) findViewById(R.id.joke_text);
        textViewJoke.setText(joke);

    }

}
