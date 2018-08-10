package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import buildit.kondeg.udacity.edu.joke.JokeActivity;
import buildit.kondeg.udacity.edu.jokeprovider.JokeConstants;
import buildit.kondeg.udacity.edu.jokeprovider.JokeProvider;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void tellJoke(View view) {
        //Toast.makeText(this, JokeProvider.getJoke(), Toast.LENGTH_SHORT).show();
        //Intent intent = new Intent(view.getContext(), JokeActivity.class);
        //intent.putExtra(JokeConstants.intentJoke, JokeProvider.getJoke());
        //String joke = JokeProvider.getJoke();
        //intent.putExtra(JokeConstants.intentJoke, joke);
        //iew.getContext().startActivity(intent);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressindicator);
        new JokeGetter(this, progressBar).execute();
    }

    class JokeGetter extends JokeAsyncTask {

        public JokeGetter(Context context, ProgressBar progressBar) {
            super(context, progressBar);
        }

        @Override
        protected void onPostExecute(@Nullable String joke) {
            if (joke==null) {
                Toast.makeText(context, "Error getting joke", Toast.LENGTH_SHORT).show();
                return;
            }
            if (mProgressBar != null) {
                mProgressBar.setVisibility(View.GONE);
            }
            Intent intent = new Intent(context, JokeActivity.class);
            intent.putExtra(JokeConstants.intentJoke, joke);
            context.startActivity(intent);
        }
    }


}
