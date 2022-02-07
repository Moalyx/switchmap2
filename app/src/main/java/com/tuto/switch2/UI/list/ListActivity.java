package com.tuto.switch2.UI.list;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tuto.switch2.R;
import com.tuto.switch2.UI.list.adapter.ChildAdapter;
import com.tuto.switch2.UI.list.adapter.ParentAdapter;
import com.tuto.switch2.UI.main.MainActivity;
import com.tuto.switch2.ViewModelFactory;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        RecyclerView parRecyclerView = findViewById(R.id.listparent);
        RecyclerView enfRecyclerView = findViewById(R.id.childrenlist);
        Button returnButton = findViewById(R.id.return_button);

        final ListViewModel listViewModel = new ViewModelProvider(this, ViewModelFactory.getInstance()).get(ListViewModel.class);

        ParentAdapter parentAdapter = new ParentAdapter(listViewModel::onParentClicked);
        parRecyclerView.setAdapter(parentAdapter);

        ChildAdapter childAdapter = new ChildAdapter();
        enfRecyclerView.setAdapter(childAdapter);

        returnButton.setOnClickListener(view -> {
            Intent intent = new Intent(ListActivity.this, MainActivity.class);
            startActivity(intent);
        });

        listViewModel.getViewStateLiveData().observe(this, viewState -> {
            parentAdapter.submitList(viewState.getParentViewStates());
            childAdapter.submitList(viewState.getChildViewStates());
        });
    }
}