package aston.fr.quizzapp.memory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import java.util.List;

import aston.fr.quizzapp.R;
import aston.fr.quizzapp.models.Carte;
import aston.fr.quizzapp.models.CarteAdapter;
import aston.fr.quizzapp.models.Memory;
import utils.Constant;

public class MemoryActivity extends AppCompatActivity
{
    private GridView gridMemoryMap;
    private Memory memory;

    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
        gridMemoryMap = findViewById(R.id.gridMemoryMap);

        gridMemoryMap.setNumColumns(Constant.MEMORY_SIZE_WIDTH);
        initMemory();
        List<Carte> cartes = memory.getMap();
        gridMemoryMap.setAdapter(new CarteAdapter(MemoryActivity.this, R.layout.item_carte, cartes));

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
