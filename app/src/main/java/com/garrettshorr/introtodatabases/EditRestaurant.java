package com.garrettshorr.introtodatabases;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.SeekBar;

import com.backendless.Backendless;

public class EditRestaurant extends AppCompatActivity {
    private EditText name;
    private EditText cuisine;
    private EditText address;
    private EditText website;
    private RatingBar rating;
    private SeekBar price;
    private Button delete;
    private Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_restaurant);
        Backendless.initApp(this,
                Credentials.APP_ID, Credentials.API_KEY);
        wireWidgets();
        fill();
    }

    private void fill() {
    String ObjectId = Backendless.UserService.CurrentUser().getObjectId();
    }

    private void wireWidgets() {
        name = findViewById(R.id.editText_Name);
        cuisine = findViewById(R.id.editText_Cuisine);
        address = findViewById(R.id.editText_Address);
        rating = findViewById(R.id.ratingBar_Rating);
        website = findViewById(R.id.editText_Website);
        price = findViewById(R.id.seekBar_Price);
        save = findViewById(R.id.button_Save);
        delete = findViewById(R.id.button_Delete);
    }
}
