package aston.fr.quizzapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import models.Memory;

public class MainActivity extends AppCompatActivity {
    private TextView textViewTitleApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewTitleApp = findViewById(R.id.textViewTitleApp);
        /*Memory mem = new Memory();
        mem.Init();
        test.setText(mem.toString());*/
    }
}
