package aston.fr.quizzapp.memory;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import aston.fr.quizzapp.R;
import aston.fr.quizzapp.models.Carte;
import aston.fr.quizzapp.models.CarteAdapter;
import aston.fr.quizzapp.models.Memory;
import utils.Constant;

public class MemoryActivity extends AppCompatActivity
{
    private GridView gridMemoryMap;
    private Memory memory;
    private TextView textViewNbEssais, textViewNbFound, textViewNbCoups;
    private CarteAdapter carteAdapter;
    private boolean isRunning = true;

    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
        gridMemoryMap = findViewById(R.id.gridMemoryMap);

        gridMemoryMap.setNumColumns(Constant.MEMORY_SIZE_WIDTH);
        initMemory();
        List<Carte> cartes = memory.getMap();
        carteAdapter = new CarteAdapter(MemoryActivity.this, R.layout.item_carte, cartes);
        gridMemoryMap.setAdapter(carteAdapter);

        textViewNbEssais = (TextView) findViewById(R.id.textViewNbEssais);
        textViewNbFound = (TextView) findViewById(R.id.textViewNbFound);
        textViewNbCoups = (TextView) findViewById(R.id.textViewNbCoups);

        new Thread()
        {
            public void run()
            {
                while (isRunning)
                {
                    try
                    {
                        runOnUiThread(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                textViewNbEssais.setText("Ratées : " + String.valueOf(carteAdapter.getNbRates()));
                                textViewNbFound.setText("Trouvées : " + String.valueOf(carteAdapter.getNbTrouves()));
                                textViewNbCoups.setText("Nombre de coups : " + String.valueOf(carteAdapter.getNbCoups()));
                                if (carteAdapter.getNbTrouves() == 6)
                                {
                                    Toast.makeText(getApplicationContext(), "Félicitations ! Vous avez gagné en seulement " + String.valueOf(carteAdapter.getNbCoups()) + " coups !", Toast.LENGTH_LONG).show();
                                    isRunning = false;
                                }
                            }
                        });
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    private void initMemory()
    {
        memory = new Memory();
        memory.init();
    }

    public void restartMemory(View view)
    {
        Intent myIntent = new Intent(MemoryActivity.this, MemoryActivity.class);
        startActivity(myIntent);
        finish();
    }
}
