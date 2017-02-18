package jamil.pokemonrps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    private static final int CHOICE_BULBASAUR = 1;
    private static final int CHOICE_CHARMANDER = 2;
    private static final int CHOICE_SQUIRTLE = 3;

    // Define views
    private ImageButton mBulbasaurButton;
    private ImageButton mCharmanderButton;
    private ImageButton mSquirtleButton;

    private TextView mResultTextView;

    private ImageView mComputerChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBulbasaurButton = (ImageButton) findViewById(R.id.playerBulbasaur);
        mCharmanderButton = (ImageButton) findViewById(R.id.playerCharmander);
        mSquirtleButton = (ImageButton) findViewById(R.id.playerSquirtle);

        mResultTextView = (TextView) findViewById(R.id.resultTextView);

        setUpButtonListeners();
    }

    private void setUpButtonListeners()
    {
        mBulbasaurButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                determineResult(CHOICE_BULBASAUR);
            }
        });

        mCharmanderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                determineResult(CHOICE_CHARMANDER);
            }
        });

        mSquirtleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                determineResult(CHOICE_CHARMANDER);
            }
        });
    }

    private void determineResult(int playerChoice)
    {
        int computerChoice = getComputerChoice();

        if (playerChoice == computerChoice) {
            // Tie
            showTie();
            Log.d(TAG, "Result is a tie.");
        } else {
            if (playerChoice == CHOICE_BULBASAUR) {
                showResult(computerChoice == CHOICE_SQUIRTLE);
            } else if (playerChoice == CHOICE_CHARMANDER) {
                showResult(computerChoice == CHOICE_BULBASAUR);
            } else if (playerChoice == CHOICE_SQUIRTLE) {
                showResult(computerChoice == CHOICE_CHARMANDER);
            } else {
                Log.d(TAG, "Error!");
            }
        }
    }

    private void showResult(boolean playerWins)
    {
        String resultText = playerWins
                ? "You win!"
                : "Sorry, you lose!";

        mResultTextView.setText(resultText);
    }

    private void showTie()
    {
        mResultTextView.setText("It was a TIE!");
    }


    private int getComputerChoice()
    {
        int choice;
        Random rand = new Random();
        choice = rand.nextInt((CHOICE_SQUIRTLE - CHOICE_BULBASAUR) + 1) + CHOICE_BULBASAUR;
        Log.d(TAG, "Computer Choice: " + choice);
        return choice;
    }
}
