package aston.fr.quizzapp.ui.main;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import aston.fr.quizzapp.R;
import aston.fr.quizzapp.memory.MemoryActivity;
import aston.fr.quizzapp.quizz.Results;
import aston.fr.quizzapp.settings.SettingsActivity;
import utils.Constant;
import utils.FastDialog;
import utils.Network;

public class MainActivity extends AppCompatActivity {
    private TextView textViewTitleApp;
    private Button buttonSettings, buttonMemory, buttonQuizz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Constant.SCORE_QUIZZ = 0;

        textViewTitleApp = findViewById(R.id.textViewTitleApp);

        buttonMemory = findViewById(R.id.buttonMemory);
        buttonQuizz = findViewById(R.id.buttonQuizz);
        buttonSettings = findViewById(R.id.buttonSettings);

        buttonQuizz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, QuizzActivity.class);
                startActivity(intent);
            }
        });

        buttonMemory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, MemoryActivity.class);
                startActivity(intent);
            }
        });

        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }


}
