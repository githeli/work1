package com.example.work1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SharePref extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_share_pref);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        checkIfFirstRun();
        initUI();
    }

    private void initUI() {

        Button buttonGame = findViewById(R.id.buttonGame);
        buttonGame.setOnClickListener(this);
    }
    private void checkIfFirstRun() {
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.pref_file_key), MODE_PRIVATE);
        String userName = sharedPref.getString(getString(R.string.user_name),"");
        if(!userName.equals("")){
            Intent intent = new Intent(this, FirstXO.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onClick(View v) {

        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.pref_file_key), MODE_PRIVATE);

        TextView txt = findViewById(R.id.editTextUserName);
        String uNameFromScreen = txt.getText().toString();
        SharedPreferences.Editor edit = sharedPref.edit();
        edit.putString(getString(R.string.user_name),uNameFromScreen);
        edit.apply();
        Intent intent = new Intent(this, FirstXO.class);
        startActivity(intent);
        finish();
    }
}