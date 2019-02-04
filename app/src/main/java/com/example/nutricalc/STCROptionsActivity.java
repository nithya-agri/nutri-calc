package com.example.nutricalc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.nutricalc.data.CropNutrientDataHelper;

public class STCROptionsActivity extends AppCompatActivity {

    CropNutrientDataHelper cropNutrientDataHelper = new CropNutrientDataHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stcroptions);

        TextView textView = findViewById(R.id.message_text_view);
        String message = "no of crops: " + cropNutrientDataHelper.getCropCount();
        textView.setText(message);

    }
}
