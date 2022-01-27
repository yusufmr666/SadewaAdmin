package com.example.adminsadewa.kuliner;




import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminsadewa.Adaper.AdapterKuliner;
import com.example.adminsadewa.R;
import com.example.adminsadewa.model.User;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class KelolaKulinerActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    FloatingActionButton fb;
    AdapterKuliner adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelola_sadewa);

        recyclerView = findViewById(R.id.listcag);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<User> options =
                new FirebaseRecyclerOptions.Builder<User>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("kuliner"), User.class)
                        .build();

        adapter = new AdapterKuliner(options);
        recyclerView.setAdapter(adapter);

        fb=(FloatingActionButton)findViewById(R.id.fadd);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddDataKuliner.class));
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }







    public void onResume() {
        super.onResume();
        ActionBar supportActionBar = ((AppCompatActivity)this).getSupportActionBar();
        if (supportActionBar != null)
            supportActionBar.hide();
    }

}