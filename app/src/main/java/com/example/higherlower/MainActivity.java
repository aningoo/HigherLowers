package com.example.higherlower;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private final static int UPPER_LIMIT = 10; //not including this number.
    private static int SCORE = 0; //

    private final Random numberGenerator = new Random();
    private int currentValue;
    private int nextValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public int generateRandomNumber(){
        return numberGenerator.nextInt(UPPER_LIMIT);
    }

    public void initializeNewGame(View v){
        SCORE = 0;
        currentValue = numberGenerator.nextInt(UPPER_LIMIT);
        nextValue = numberGenerator.nextInt(UPPER_LIMIT);

        TextView currentNumberView = ((TextView) findViewById(R.id.currentNumberView));
        TextView scoreView = ((TextView) findViewById(R.id.scoreView));
        Button higher = findViewById(R.id.higherButton);
        Button lower = findViewById(R.id.lowerButton);

        higher.setEnabled(true);
        lower.setEnabled(true);
        currentNumberView.setText(String.valueOf("Current number: " + currentValue));
        scoreView.setText(String.valueOf("Score: " + SCORE));
    }


    public void castGuess(View button){
        if (button.getId() == R.id.higherButton){
            if (nextValue>currentValue){
                correctAnswerFlow();
            } else {
                gameOverFlow();
            }
        } else {
            if (nextValue<currentValue){
                correctAnswerFlow();
            }
            else {
                gameOverFlow();
            }
        }
    }

    private void plusOneScore(){
        TextView scoreView = ((TextView) findViewById(R.id.scoreView));
        scoreView.setText("Score: " + ++SCORE);
    }

    private void setNextValue(){
        currentValue = nextValue;
        int randomInteger = generateRandomNumber();
        while (randomInteger==currentValue){
            Log.e("ERROR","Identical number error, generate new number.");
            randomInteger = generateRandomNumber();
        }
        nextValue = randomInteger;
    }

    private void correctAnswerFlow(){
        plusOneScore();
        TextView currentNumberView = ((TextView) findViewById(R.id.currentNumberView));
        currentNumberView.setText("Current number: " + nextValue);
        setNextValue();
    }

    private void gameOverFlow(){
        TextView currentNumberView = ((TextView) findViewById(R.id.currentNumberView));
        currentNumberView.setText("GAME OVER! current: " + currentValue + " next: " + nextValue);

        Button higher = findViewById(R.id.higherButton);
        Button lower = findViewById(R.id.lowerButton);

        higher.setEnabled(false);
        lower.setEnabled(false);

    }
}