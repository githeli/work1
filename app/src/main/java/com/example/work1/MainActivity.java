package com.example.work1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        initUI();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void initUI() {

        Button btnShowMain = findViewById(R.id.btnShowMain);
        Button btnPlusMain = findViewById(R.id.btnPlusMain);

        btnPlusMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txt = findViewById(R.id.txtNumMain);
                txt.setText(String.valueOf(Integer.parseInt(txt.getText().toString()) + 1));
            }
        });

        btnShowMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txt = findViewById(R.id.txtNumMain);
                Toast.makeText(MainActivity.this, "You have clicked "+txt.getText().toString()+" times!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}