package com.example.work1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FirstXO extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_first_xo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initUI();
    }

    private void initUI() {
        Button button = findViewById(R.id.buttonStart);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        TextView name1 = findViewById(R.id.editTextName1);
        TextView name2 = findViewById(R.id.editTextName2);
        Intent intent = new Intent(this, DemoActivity.class);
        intent.putExtra(getString(R.string.player1Name), name1.getText().toString());
        intent.putExtra(getString(R.string.player2Name), name2.getText().toString());
        startActivity(intent);
    }
}