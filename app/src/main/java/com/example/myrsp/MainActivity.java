package com.example.myrsp;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button b_rock, b_scissors, b_paper; // Button declaration
    int UserScore, ComputerScore;    // Score Counter
    ImageView iv_ComputerChoice, iv_UserChoice; // Image Rock or Paper or Scissors
    TextView tv_score; // Score View

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b_paper = (Button) findViewById(R.id.b_paper);
        b_scissors = (Button) findViewById(R.id.b_scissors);
        b_rock = (Button) findViewById(R.id.b_rock);

        iv_ComputerChoice = (ImageView) findViewById(R.id.iv_ComputerChoice);
        iv_UserChoice = (ImageView) findViewById(R.id.iv_YourChoice);

        tv_score = (TextView) findViewById(R.id.Score);

        b_paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_UserChoice.setImageResource(R.drawable.paper);
                String message = play_turn("paper");
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                tv_score.setText("Score You: " + Integer.toString(UserScore) + "Computer: " + Integer.toString(ComputerScore));

            }
        });

        b_rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_UserChoice.setImageResource(R.drawable.rock);
                String message = play_turn("rock");
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                tv_score.setText("Score You: " + Integer.toString(UserScore) + "Computer: " + Integer.toString(ComputerScore));

            }
        });

        b_scissors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_UserChoice.setImageResource(R.drawable.scissors);
                String message = play_turn("scissors");
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                tv_score.setText("Score You: " + Integer.toString(UserScore) + "Computer: " + Integer.toString(ComputerScore));

            }
        });

        //set score on rotation possibility

        if (savedInstanceState != null){
            UserScore = savedInstanceState.getInt("count");
            ComputerScore = savedInstanceState.getInt ("count");
            tv_score.setText(String.valueOf(UserScore));
            tv_score.setText(String.valueOf(ComputerScore));
        }
    }



    public String play_turn(String player_choice) {

        String computer_choice = "";
        Random r = new Random();

        // choose 1 2 or 3
        int computer_choice_number = r.nextInt(3) + 1;

        if (computer_choice_number == 1) {
            computer_choice = "rock";
        } else if (computer_choice_number == 2) {
            computer_choice = "scissors";
        } else if (computer_choice_number == 3) {
            computer_choice = "paper";
        }

        // set the computer image based on his choice
        if (computer_choice == "rock") {
            iv_ComputerChoice.setImageResource(R.drawable.rock);
        } else if (computer_choice == "scissors") {
            iv_ComputerChoice.setImageResource(R.drawable.scissors);
        } else if (computer_choice == "paper") {
            iv_ComputerChoice.setImageResource(R.drawable.paper);
        }


        // computer user and choice to determine who won.
        if (computer_choice == player_choice) {
            return "Draw. Nobody won." ;
        } else if (player_choice == "rock" && computer_choice == "scissors") {
            UserScore++;
            return "Rock crushes scissors. You win!";
        } else if (player_choice == "rock" && computer_choice == "paper") {
            ComputerScore++;
            return "Paper covers Rock. Computer wins!";
        } else if (player_choice == "scissors" && computer_choice == "rock") {
            ComputerScore++;
            return "Rock crushes Scissors. Computer wins!";
        } else if (player_choice == "scissors" && computer_choice == "paper") {
            UserScore++;
            return "Scissors cut paper. You win!";
        } else if (player_choice == "paper" && computer_choice == "rock") {
            UserScore++;
            return "Paper covers Rock. You win!";
        } else if (player_choice == "paper" && computer_choice == "scissors") {
            ComputerScore++;
            return "Scissors cut paper. Computer wins!";
        }
        else return "Not sure";

    }

    // Save score on rotation
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("count",UserScore);
        outState.putInt("count",ComputerScore);
    }


}
