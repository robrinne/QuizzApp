package aston.fr.quizzapp.settings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import aston.fr.quizzapp.R;
import utils.Constant;

public class SettingsActivity extends AppCompatActivity
{
    Spinner spinnerQuestionNumber, spinnerDifficulty, spinnerTheme;
    RadioGroup radioGroupQuestionType;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        spinnerQuestionNumber = (Spinner) findViewById(R.id.spinnerQuestionNumber);
        spinnerDifficulty = (Spinner) findViewById(R.id.spinnerDifficulty);
        spinnerTheme = (Spinner) findViewById(R.id.spinnerTheme);
        radioGroupQuestionType = (RadioGroup) findViewById(R.id.radioGroupQuestionType);

        // Create an ArrayAdapter using the string array and a default spinner layout
        // Adapter pour le nombre de questions
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this,R.array.question_number_array,
                                                    R.layout.spinner);

        // Adapter pour la difficulté
        ArrayAdapter<CharSequence> adapter2 =
                ArrayAdapter.createFromResource(this,R.array.question_difficulty_array,
                        R.layout.spinner);

        // Adapter pour le theme
        ArrayAdapter<CharSequence> adapter3 =
                ArrayAdapter.createFromResource(this,R.array.question_theme_array,
                        R.layout.spinner);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinnerQuestionNumber.setAdapter(adapter);
        spinnerDifficulty.setAdapter(adapter2);
        spinnerTheme.setAdapter(adapter3);

        // Selection du bon item dans les spinner selon les constantes
        ArrayAdapter<CharSequence> adapterValues =
                ArrayAdapter.createFromResource(this,R.array.question_number_values,
                        R.layout.spinner);
        int pos = adapterValues.getPosition(Constant.NB_QUESTION);
        spinnerQuestionNumber.setSelection(pos);
        Log.e("TEST spinner", "position = " + pos);

        ArrayAdapter<CharSequence> adapter2Values =
                ArrayAdapter.createFromResource(this,R.array.question_difficulty_values,
                        R.layout.spinner);
        pos = adapter2Values.getPosition(Constant.DIFFICULTY_QUESTION);
        spinnerDifficulty.setSelection(pos);

        ArrayAdapter<CharSequence> adapter3Values =
                ArrayAdapter.createFromResource(this,R.array.question_theme_values,
                        R.layout.spinner);
        pos = adapter3Values.getPosition(Constant.THEME_QUESTION);
        spinnerTheme.setSelection(pos);

        // Selection du bon radioButton selon les constantes
        if(Constant.TYPE_QUESTION == "boolean")
        {
            RadioButton radioButtonBoolean = findViewById(R.id.checkboxTypeBoolean);
            radioButtonBoolean.setChecked(true);
        }
        else
        {
            RadioButton radioButtonMultiple = findViewById(R.id.checkboxTypeMultiple);
            radioButtonMultiple.setChecked(true);
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        spinnerQuestionNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String[] nbQuestionsValues = getResources().getStringArray(R.array.question_number_values);
                Constant.NB_QUESTION = nbQuestionsValues[position];
                Log.e("TEST spinner", "NB_QUESTIONS = " + Constant.NB_QUESTION);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerDifficulty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String[] difficultyQuestionValues = getResources().getStringArray(R.array.question_difficulty_values);
                Constant.DIFFICULTY_QUESTION = difficultyQuestionValues[position];
                Log.e("TEST spinner", "DIFFICULTE = " + Constant.DIFFICULTY_QUESTION);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerTheme.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String[] themeQuestionsValues = getResources().getStringArray(R.array.question_theme_values);
                Constant.THEME_QUESTION = themeQuestionsValues[position];
                Log.e("TEST spinner", "THEME = " + Constant.THEME_QUESTION);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        radioGroupQuestionType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                switch (checkedId)
                {
                    case -1:
                        Log.e("RADIOGROUP", "ERROR - Problem selection on radioButton");
                        break;
                    case R.id.checkboxTypeBoolean:
                        Constant.TYPE_QUESTION = "boolean";
                        break;
                    case R.id.checkboxTypeMultiple:
                        Constant.TYPE_QUESTION = "multiple";
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
