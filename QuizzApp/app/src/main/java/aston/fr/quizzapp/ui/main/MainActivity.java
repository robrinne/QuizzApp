package aston.fr.quizzapp.ui.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import aston.fr.quizzapp.R;
import aston.fr.quizzapp.memory.MemoryActivity;
import aston.fr.quizzapp.quizz.QuizzActivity;
import aston.fr.quizzapp.settings.SettingsActivity;
import models.Memory;

public class MainActivity extends AppCompatActivity {
    private TextView textViewTitleApp;
    private Button buttonSettings, buttonMemory, buttonQuizz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewTitleApp = findViewById(R.id.textViewTitleApp);
        /*Memory mem = new Memory();
        mem.Init();
        test.setText(mem.toString());*/

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
