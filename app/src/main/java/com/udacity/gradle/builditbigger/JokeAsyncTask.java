package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.ads.InterstitialAd;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import java.io.IOException;
import edu.udacity.kondeg.buildit.backend.myApi.MyApi;

public class JokeAsyncTask extends AsyncTask<Void, Void, String> {
    private static final String LOG_TAG = JokeAsyncTask.class.getSimpleName();
    protected ProgressBar mProgressBar;
    protected static MyApi myApiService = null;
    protected Context context;
    protected InterstitialAd mInterstitialAd;

    public JokeAsyncTask(Context context, ProgressBar progressBar) {
        this.context = context;
        this.mProgressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected String doInBackground(Void... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    //.setApplicationName("backend-212722")
                    .setRootUrl("https://backend-212722.appspot.com/_ah/api/")
                    // .setRootUrl("http://backend-212722.appspot.com/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            Log.e(LOG_TAG, e.getMessage());
            return e.getMessage();
        }
    }

}
