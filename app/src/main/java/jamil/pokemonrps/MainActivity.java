package jamil.pokemonrps;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

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
    private TextView mPlayerPointsTextView;
    private TextView mComputerPointsTextView;

    private int mPlayerPoints = 0;
    private int mComputerPoints = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBulbasaurButton = (ImageButton) findViewById(R.id.playerBulbasaur);
        mCharmanderButton = (ImageButton) findViewById(R.id.playerCharmander);
        mSquirtleButton = (ImageButton) findViewById(R.id.playerSquirtle);
        mComputerChoice = (ImageView) findViewById(R.id.computerChoice);

        mResultTextView = (TextView) findViewById(R.id.resultTextView);
        mPlayerPointsTextView = (TextView) findViewById(R.id.playerPoints);
        mComputerPointsTextView = (TextView) findViewById(R.id.computerPoints);

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

        // Set button transparencies
        float opaque = 1f;
        float transparent = 0.35f;
        if (playerChoice == CHOICE_BULBASAUR) {
            mBulbasaurButton.setAlpha(opaque);
            mCharmanderButton.setAlpha(transparent);
            mSquirtleButton.setAlpha(transparent);
        } else if (playerChoice == CHOICE_CHARMANDER) {
            mBulbasaurButton.setAlpha(transparent);
            mCharmanderButton.setAlpha(opaque);
            mSquirtleButton.setAlpha(transparent);
        } else if (playerChoice == CHOICE_SQUIRTLE) {
            mBulbasaurButton.setAlpha(transparent);
            mCharmanderButton.setAlpha(transparent);
            mSquirtleButton.setAlpha(opaque);
        }

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

        if (playerWins) {
            ++mPlayerPoints;
            mPlayerPointsTextView.setText("" + mPlayerPoints);
        } else {
            ++mComputerPoints;
            mComputerPointsTextView.setText("" + mComputerPoints);
        }

        mResultTextView.setText(resultText);

        String backgroundColor = playerWins ? "#47d147" : "#ff4d4d";
        mResultTextView.setBackgroundColor(Color.parseColor(backgroundColor));
    }

    private void showTie()
    {
        mResultTextView.setText(R.string.result_tie);
        mResultTextView.setBackgroundColor(Color.parseColor("#666666"));
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
