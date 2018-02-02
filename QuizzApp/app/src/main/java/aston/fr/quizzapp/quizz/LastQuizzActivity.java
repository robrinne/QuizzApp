package aston.fr.quizzapp.quizz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import aston.fr.quizzapp.R;
import aston.fr.quizzapp.ui.main.MainActivity;
import utils.Constant;

public class LastQuizzActivity extends AppCompatActivity
{
    private TextView textViewMsgScore;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_quizz);

        textViewMsgScore = (TextView) findViewById(R.id.textViewMsgScore);

        textViewMsgScore.setText("Vous avez fait un score de " + Constant.SCORE_QUIZZ + "/" + Constant.NB_QUESTION + "!");
    }

    public void goToMenu(View view)
    {
        Intent myIntent = new Intent(LastQuizzActivity.this, MainActivity.class);
        startActivity(myIntent);
        finish();
    }
}
