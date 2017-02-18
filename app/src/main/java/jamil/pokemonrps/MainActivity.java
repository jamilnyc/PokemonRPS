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
        mComputerChoice = (ImageView) findViewById(R.id.computerChoice);

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
                determineResult(CHOICE_SQUIRTLE);
            }
        });
    }

    private void determineResult(int playerChoice)
    {
        int computerChoice = getComputerChoice();
        displayComputerChoice(computerChoice);

        if (playerChoice == computerChoice) {
            showTie();
            Log.d(TAG, "Result is a tie.");
        } else {
            if (playerChoice == CHOICE_BULBASAUR) {
                Log.d(TAG, "Play chose Bulbasaur");
                showResult(computerChoice == CHOICE_SQUIRTLE);
            } else if (playerChoice == CHOICE_CHARMANDER) {
                Log.d(TAG, "Play chose Charmander");
                showResult(computerChoice == CHOICE_BULBASAUR);
            } else if (playerChoice == CHOICE_SQUIRTLE) {
                Log.d(TAG, "Play chose Squirtle");
                showResult(computerChoice == CHOICE_CHARMANDER);
            } else {
                Log.d(TAG, "Error!");
            }
        }
    }

    private void showResult(boolean playerWins)
    {
        Log.d(TAG, "Play wins: " + playerWins);
        int resultText = playerWins
                ? R.string.result_win
                : R.string.result_lose;
        mResultTextView.setText(resultText);
    }

    private void showTie()
    {
        mResultTextView.setText(R.string.result_tie);
    }

    private int getComputerChoice()
    {
        int choice;
        Random rand = new Random();
        choice = rand.nextInt((CHOICE_SQUIRTLE - CHOICE_BULBASAUR) + 1) + CHOICE_BULBASAUR;
        Log.d(TAG, "Computer Choice: " + choice);
        return choice;
    }

    private void displayComputerChoice(int computerChoice)
    {
        Log.d(TAG, "Updating computer choice image");
        switch (computerChoice) {
            case CHOICE_BULBASAUR:
                mComputerChoice.setImageResource(R.drawable.bulbasaur);
                break;
            case CHOICE_CHARMANDER:
                mComputerChoice.setImageResource(R.drawable.charmander);
                break;
            case CHOICE_SQUIRTLE:
                mComputerChoice.setImageResource(R.drawable.squirtle);
                break;
        }
    }
}
