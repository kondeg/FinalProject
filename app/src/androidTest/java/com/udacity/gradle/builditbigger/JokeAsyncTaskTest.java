package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;
import android.util.Log;
import android.widget.ProgressBar;

import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import static org.mockito.Mockito.mock;

@RunWith(AndroidJUnit4.class)
public class JokeAsyncTaskTest extends AndroidTestCase {

    private static final String LOG_TAG = JokeAsyncTaskTest.class.getSimpleName();

    @Test
    public void testJokeAsyncTask() {
        Context context = mock(Context.class);
        ProgressBar progressBar = mock(ProgressBar.class);
        JokeAsyncTask task = new JokeAsyncTask(context, progressBar);
        task.execute();
        String response = null;
        try {
            response = task.get(5, TimeUnit.SECONDS);
            Log.i(LOG_TAG, response);
        } catch (InterruptedException e) {
            Log.e(LOG_TAG, e.getMessage());
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
            Log.e(LOG_TAG, e.getMessage());
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        assertNotNull(response);
        assertFalse(response.isEmpty());
    }


}