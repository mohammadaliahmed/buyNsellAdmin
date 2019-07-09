package com.appsinventiv.buynselladmin.Activities.Locations;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.appsinventiv.buynselladmin.Activities.Categories.AddCategoryAdapter;
import com.appsinventiv.buynselladmin.R;
import com.appsinventiv.buynselladmin.Utils.CommonUtils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddLocations extends AppCompatActivity {
    DatabaseReference mDatabase;
    EditText mainCategories;
    Button update;
    RecyclerView recyclerView;
    AddLocationsAdapter adapter;
    ArrayList<String> itemList = new ArrayList<>();
    String categories = "";
    String parentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Add Locations");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true); getSupportActionBar().setElevation(0);
        }
        setContentView(R.layout.activity_location);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        parentLocation = getIntent().getStringExtra("parentLocation");

        recyclerView = findViewById(R.id.recyclerView);
        mainCategories = findViewById(R.id.mainCategories);
        update = findViewById(R.id.update);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter = new AddLocationsAdapter(this, itemList);

        recyclerView.setAdapter(adapter);

        if (parentLocation == null) {
            getDataFromDB();
        } else {
            getCategoryDataFromDB();
        }


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mainCategories.getText().length() == 0) {
                    mainCategories.setError("Enter category");
                } else {
                    List<String> container = new ArrayList<>();

                    String[] sizes = mainCategories.getText().toString().split("\n");
                    container = Arrays.asList(sizes);
                    if (parentLocation != null) {
                        mDatabase.child("Settings/Locations").child(parentLocation).setValue(container).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                mainCategories.setText("");
                                CommonUtils.showToast("Done");
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                CommonUtils.showToast(e.getMessage());
                            }
                        });
                    } else {
                        mDatabase.child("Settings/Locations").child("Province").setValue(container).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                mainCategories.setText("");
                                CommonUtils.showToast("Done");
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                CommonUtils.showToast(e.getMessage());
                            }
                        });
                    }

                }
            }
        });

    }

    private void getCategoryDataFromDB() {
        mDatabase.child("Settings").child("Locations").child(parentLocation).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String value = snapshot.getValue(String.class);
                        itemList.add(value);

                        categories = categories + value + "\n";
                        mainCategories.setText(categories);
                    }

                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getDataFromDB() {
        mDatabase.child("Settings").child("Locations").child("Province").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String value = snapshot.getValue(String.class);
                        itemList.add(value);

                        categories = categories + value + "\n";
                        mainCategories.setText(categories);
                    }

                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }


}
