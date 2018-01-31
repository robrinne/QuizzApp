package aston.fr.quizzapp.settings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import aston.fr.quizzapp.R;

public class SettingsActivity extends AppCompatActivity
{
    Spinner spinnerQuestionNumber, spinnerDifficulty, spinnerTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        spinnerQuestionNumber = (Spinner) findViewById(R.id.spinnerQuestionNumber);
        spinnerDifficulty = (Spinner) findViewById(R.id.spinnerDifficulty);
        spinnerTheme = (Spinner) findViewById(R.id.spinnerTheme);

        // Create an ArrayAdapter using the string array and a default spinner layout
        // Adapter pour le nombre de questions
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this,R.array.question_number_array,
                                                    R.layout.spinner);

        // Adapter pour la difficulté
        ArrayAdapter<CharSequence> adapter2 =
                ArrayAdapter.createFromResource(this,R.array.question_difficulty,
                        R.layout.spinner);

        // Adapter pour le theme
        ArrayAdapter<CharSequence> adapter3 =
                ArrayAdapter.createFromResource(this,R.array.question_theme,
                        R.layout.spinner);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinnerQuestionNumber.setAdapter(adapter);
        spinnerDifficulty.setAdapter(adapter2);
        spinnerTheme.setAdapter(adapter3);
    }
}
