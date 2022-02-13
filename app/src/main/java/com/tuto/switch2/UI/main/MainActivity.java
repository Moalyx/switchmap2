package com.tuto.switch2.UI.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tuto.switch2.R;
import com.tuto.switch2.UI.list.ListActivity;
import com.tuto.switch2.ViewModelFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainViewModel viewModel = new ViewModelProvider(this, ViewModelFactory.getInstance()).get(MainViewModel.class);

        EditText parentEditText = findViewById(R.id.name_parent);
        EditText childrenEditText = findViewById(R.id.childrens);
        EditText ageEditText = findViewById(R.id.ages);
        FloatingActionButton addButton = findViewById(R.id.add);

        parentEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                viewModel.onParentNameChanged(editable.toString());
            }
        });

        childrenEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                viewModel.onNameEnfantChanged(editable.toString());

            }
        });

        ageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                viewModel.onAgeEnfantChanged(editable.toString());

            }
        });

        addButton.setOnClickListener(view -> {
            viewModel.onAddButtonClicked();
            Intent intent = new Intent(MainActivity.this, ListActivity.class);
            startActivity(intent);
        });

    }
}