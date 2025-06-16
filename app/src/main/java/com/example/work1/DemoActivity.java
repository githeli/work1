package com.example.work1;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import static com.example.work1.AppConsts.*;

public class DemoActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private String player1Name;
    private String player2Name;

    private Model model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.demo_activity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initUI();
        model = new Model();

        player1Name = getIntent().getStringExtra(getString(R.string.player1Name));
        player2Name = getIntent().getStringExtra(getString(R.string.player2Name));
        if(player1Name.equals("")) player1Name="Player 1";
        if(player2Name.equals("")) player2Name="Player 2";
        Log.d("Android Seminar", "onCreate: " + player1Name + " " + player2Name);
        TextView textView = findViewById(R.id.textPlayers);
        textView.setText(player1Name + " is X vs " + player2Name + " is O");
    }

    private void initUI() {

        Button button1 = findViewById(R.id.button00);
        button1.setOnClickListener(this);
        Button button2 = findViewById(R.id.button01);
        button2.setOnClickListener(this);
        Button button3 = findViewById(R.id.button02);
        button3.setOnClickListener(this);
        Button button4 = findViewById(R.id.button10);
        button4.setOnClickListener(this);
        Button button5 = findViewById(R.id.button11);
        button5.setOnClickListener(this);
        Button button6 = findViewById(R.id.button12);
        button6.setOnClickListener(this);
        Button button7 = findViewById(R.id.button20);
        button7.setOnClickListener(this);
        Button button8 = findViewById(R.id.button21);
        button8.setOnClickListener(this);
        Button button9 = findViewById(R.id.button22);
        button9.setOnClickListener(this);

        Button buttonReset = findViewById(R.id.buttonReset);
        buttonReset.setOnClickListener(this);
        /*
        button.setOnClickListener(view -> {

            int viewId = view.getId();
            String idAsString = getResources().getResourceEntryName(viewId);
            Log.d("ID AS STRING", "initUI: " +  idAsString);

            String  tag = view.getTag().toString();
             Log.d("TAG", "initUI: " + tag);


            // Handle button click
            // For example, you can show a Toast or start another activity
            // Toast.makeText(DemoActivity.this, "Button clicked!", Toast.LENGTH_SHORT).show();
        });
         */
    }

    /*public void onButtonClick(View view) {
        String tag = view.getTag().toString();
        int row = tag.charAt(0)-'0';
        int column = tag.charAt(1)-'0';
        Button clickedButton = (Button) view;
        clickedButton.setText("X");
        Log.d("Android Seminar", "row: " + row + "column: " + column );

    }*/

    @Override
    public void onClick(View v) {

        String tag = v.getTag().toString();
        if(tag.equals("Reset")){
            model.resetBoard();
            enableAllGameButtons();
            Button button1 = findViewById(R.id.button00);
            button1.setText("click me");
            Button button2 = findViewById(R.id.button01);
            button2.setText("click me");
            Button button3 = findViewById(R.id.button02);
            button3.setText("click me");
            Button button4 = findViewById(R.id.button10);
            button4.setText("click me");
            Button button5 = findViewById(R.id.button11);
            button5.setText("click me");
            Button button6 = findViewById(R.id.button12);
            button6.setText("click me");
            Button button7 = findViewById(R.id.button20);
            button7.setText("click me");
            Button button8 = findViewById(R.id.button21);
            button8.setText("click me");
            Button button9 = findViewById(R.id.button22);
            button9.setText("click me");
        }
        else{
            int row = tag.charAt(0)-'0';
            int column = tag.charAt(1)-'0';
            Log.d("Android Seminar", "row: " + row + "column: " + column );
            if(!model.isValidMove(row,column)){
                Toast.makeText(DemoActivity.this, "Invalid move!", Toast.LENGTH_SHORT).show();
                return;
            }
            Button clickedButton = (Button) v;
            int turn = model.updateBoard(row,column);
            if(turn==PLAYER_X){
                clickedButton.setText("X");
            }
            else{
                clickedButton.setText("O");
            }
            int winner = model.checkWin();
            if(winner==PLAYER_X){
                Toast.makeText(DemoActivity.this, "Player "+player1Name+" (X) won!", Toast.LENGTH_SHORT).show();
            }
            else if(winner==PLAYER_0){
                Toast.makeText(DemoActivity.this, "Player "+player2Name+" (O) won!", Toast.LENGTH_SHORT).show();
            }
            if(winner==PLAYER_X || winner==PLAYER_X){
                disableAllGameButtons();
            }
            if(model.isTie()){
                Toast.makeText(DemoActivity.this, "Tie!", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void disableAllGameButtons(){
        findViewById(R.id.button00).setEnabled(false);
        findViewById(R.id.button01).setEnabled(false);
        findViewById(R.id.button02).setEnabled(false);
        findViewById(R.id.button10).setEnabled(false);
        findViewById(R.id.button11).setEnabled(false);
        findViewById(R.id.button12).setEnabled(false);
        findViewById(R.id.button20).setEnabled(false);
        findViewById(R.id.button21).setEnabled(false);
        findViewById(R.id.button22).setEnabled(false);
    }

    private void enableAllGameButtons(){
        findViewById(R.id.button00).setEnabled(true);
        findViewById(R.id.button01).setEnabled(true);
        findViewById(R.id.button02).setEnabled(true);
        findViewById(R.id.button10).setEnabled(true);
        findViewById(R.id.button11).setEnabled(true);
        findViewById(R.id.button12).setEnabled(true);
        findViewById(R.id.button20).setEnabled(true);
        findViewById(R.id.button21).setEnabled(true);
        findViewById(R.id.button22).setEnabled(true);
    }
}