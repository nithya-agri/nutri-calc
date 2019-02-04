package com.example.nutricalc;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.nutricalc.data.CropNutrientDataHelper;

import java.net.URL;

public class SplashActivity extends AppCompatActivity {

    CropNutrientDataHelper cropNutrientDataHelper = new CropNutrientDataHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        CropDataLoadingTask task = new CropDataLoadingTask();
        task.execute();
    }

    public void goToOptionsScreen() {
        Intent intent = new Intent(this, STCROptionsActivity.class);
        startActivity(intent);
    }

    private class CropDataLoadingTask extends AsyncTask<URL, Void, String> {
        @Override
        protected String doInBackground(URL... urls) {
            boolean dataAvailable = checkIfCropDataIsAvailable();
            if (!dataAvailable) {
                cropNutrientDataHelper.addCropData();
                return "Crops added successfully!";
            } else {
                return "Crops already exist!";
            }
        }

        @Override
        protected void onPostExecute(String response) {
            goToOptionsScreen();
        }
    }

    private boolean checkIfCropDataIsAvailable() {
        return cropNutrientDataHelper.getCropCount() > 0;
    }
}
