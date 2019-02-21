package com.garrettshorr.introtodatabases;

import android.content.Intent;
import android.media.Rating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.SeekBar;
import com.backendless.Backendless;
import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import java.util.HashMap;
import java.util.Map;

public class RestaurantActivity extends AppCompatActivity {
    private EditText name;
    private EditText cuisine;
    private EditText address;
    private EditText website;
    private RatingBar rating;
    private SeekBar price;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        wireWidgets();
        Backendless.initApp(this,
                Credentials.APP_ID, Credentials.API_KEY);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newRestaurant();
            }
        });


    }
    public void newRestaurant()
    {
        HashMap restaurant = new HashMap();
        restaurant.put("name",name.getText().toString());
        restaurant.put("cuisine",cuisine.getText().toString() );
        restaurant.put("address",address.getText().toString());
        restaurant.put("website",website.getText().toString());
        restaurant.put("rating",( (double) rating.getRating()));
        restaurant.put("price",(int)price.getProgress());


        // save object synchronously
//        Map saveRestaurant = Backendless.Persistence.of( "Restaurant" ).save( restaurant );

        // save object asynchronously
        Backendless.Persistence.of( "Restaurant" ).save( restaurant, new AsyncCallback<Map>() {
            public void handleResponse( Map response )
            {
                // new Contact instance has been saved
                Intent intent = new Intent(RestaurantActivity.this,
                        RestaurantListActivity.class);
                startActivity(intent);
            }

            public void handleFault( BackendlessFault fault )
            {
                // an error has occurred, the error code can be retrieved with fault.getCode()
            }
        });
    }


    private void wireWidgets() {
        name = findViewById(R.id.editText_RestaurantActivity_name);
        cuisine = findViewById(R.id.editText_RestaurantActivity_cuisine);
        address = findViewById(R.id.editText_RestaurantActivity_address);
        rating = findViewById(R.id.ratingBar_RestaurantActivity_rating);
        website = findViewById(R.id.editText_RestaurantActivity_website);
        price = findViewById(R.id.seekBar_RestaurantActivity_price);
        save = findViewById(R.id.button_RestaurantActivity_save);

    }

}
