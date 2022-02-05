package com.tuto.switch2.UI.list;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tuto.switch2.UI.adapter.EnfantAdapter;
import com.tuto.switch2.UI.main.MainActivity;
import com.tuto.switch2.UI.adapter.ParentAdapter;
import com.tuto.switch2.R;
import com.tuto.switch2.ViewModelFactory;


public class ListActivity extends AppCompatActivity {

    private ParentAdapter parentAdapter;
    private EnfantAdapter enfantAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        RecyclerView parRecyclerView = findViewById(R.id.listparent);
        RecyclerView enfRecyclerView = findViewById(R.id.childrenlist);
        Button returnButton = findViewById(R.id.return_button);

        final ListViewModel listViewModel = new ViewModelProvider(this, ViewModelFactory.getInstance()).get(ListViewModel.class);

        listViewModel.getParentList().observe(this, parents -> parentAdapter.submitList(listViewModel.getparentslist()));

        listViewModel.getAllEnfantsBySelectedParentName().observe(this, enfants -> enfantAdapter.submitList(listViewModel.getenfantslist()));

        parentAdapter = new ParentAdapter(listViewModel::onParentNameChanged);
        parRecyclerView.setAdapter(parentAdapter);
        parRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        enfantAdapter = new EnfantAdapter();
        enfRecyclerView.setAdapter(enfantAdapter);
        parRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        returnButton.setOnClickListener(view -> {
            Intent intent = new Intent(ListActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}