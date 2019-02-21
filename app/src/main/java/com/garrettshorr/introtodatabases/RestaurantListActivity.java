package com.garrettshorr.introtodatabases;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.Map;
import java.util.

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.List;

public class RestaurantListActivity extends AppCompatActivity {

    private ListView listViewRestaurant;
    private FloatingActionButton restActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        wireWidgets();
        populateListView();
        restActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addRestaurant();
            }
        });

        listViewRestaurant.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                public void Backendless.Data.of( "TABLE-NAME" ).find( AsyncCallback<List<Map>> responder )
                Backendless.Persistence.save( restaurant, new AsyncCallback<Restaurant>()
                {
                    public void handleResponse( Restaurant restaurant )
                    {
                        Backendless.Persistence.of( Restaurant.class ).remove( restaurant,
                                new AsyncCallback<Long>()
                                {
                                    public void handleResponse( Long response )
                                    {
                                        // Contact has been deleted. The response is the
                                        // time in milliseconds when the object was deleted
                                    }
                                    public void handleFault( BackendlessFault fault )
                                    {
                                        // an error has occurred, the error code can be
                                        // retrieved with fault.getCode()
                                    }
                                } );
                    }
                    @Override
                    public void handleFault( BackendlessFault fault )
                    {
                        // an error has occurred, the error code can be retrieved with fault.getCode()
                    }
                });

            }
        });
    }

    private void addRestaurant() {
        Intent intent = new Intent(RestaurantListActivity.this,
                RestaurantActivity.class);
        startActivity(intent);
    }
    // refactor to only get the times that belong to the user
    // get the current user's objectiduse Backendless.UserService
    // make a dataquearyy and use the advanced object retrieval pattern
    // to find all restaurants whose ownerid matches the users objectid
    //smae WHERE clause with a string: name - 'joe'


    private void populateListView() {
        String ownerId = Backendless.UserService.CurrentUser().getObjectId();
        String whereClause = "ownerId = '" + ownerId + "'";
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();

        Backendless.Data.of( Restaurant.class).find(new AsyncCallback<List<Restaurant>>(){
            @Override
            public void handleResponse(List<Restaurant> restaurantList )
            {
                // all Restaurnt instances have been found
                RestaurantAdapter adapter = new RestaurantAdapter(
                        RestaurantListActivity.this,
                        R.layout.item_restaurantlist,
                        restaurantList);
                listViewRestaurant.setAdapter(adapter);
                //set the onItemClickListener to open the Resaurant Activity
                // tkae theclicked object and include it in the Intnt
                // in the RestaurantActivity's onCreate, check if there is a Parcelable extra
                // if there is, then get teh Testaurant object and populate the fields
            }
            @Override
            public void handleFault( BackendlessFault fault )
            {
                // an error has occurred, the error code can be retrieved with fault.getCode()
                Toast.makeText(RestaurantListActivity.this,
                        fault.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void wireWidgets() {
        listViewRestaurant = findViewById(R.id.listview_restaurantlist);
        restActivity = findViewById(R.id.floatingActionButton_ActivityRestaurantList_NewItem);
    }
}
