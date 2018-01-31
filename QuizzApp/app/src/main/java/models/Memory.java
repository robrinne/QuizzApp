package models;
import android.util.Log;

import utils.Constant;

public class Memory
{
    private Carte[][] map;

    public void Init()
    {
        this.map = new Carte[Constant.MEMORY_SIZE_WIDTH][Constant.MEMORY_SIZE_HEIGHT];
        Log.e("INIT","TEST");
        InitMap();
        Log.e("INIT","FINISH");
    }

    public void InitMap()
    {
        int l = 0;
        int h = 0;
        for (int i = 0; i < (Constant.MEMORY_SIZE_HEIGHT / 2) * Constant.MEMORY_SIZE_WIDTH; i++)
        {
            Log.e("TEST", ""+i);
            this.map[l][h] = new Carte(i, Constant.MEMORY_URLS[i]);
            this.map[l][h + (Constant.MEMORY_SIZE_HEIGHT / 2)] = new Carte(i, Constant.MEMORY_URLS[i]);
            if (h < Constant.MEMORY_SIZE_WIDTH/2)
            {
                h++;
            }
            else if (l < (Constant.MEMORY_SIZE_HEIGHT))
            {
                h = 0;
                l++;
            }
        }
    }

    @Override public String toString()
    {
        String s = "";
        for (int i = 0; i < Constant.MEMORY_SIZE_WIDTH; i++)
        {
            s += "| ";
            for (int j = 0; j < Constant.MEMORY_SIZE_HEIGHT; j++)
            {
                if (this.getMap()[i][j] != null)
                {
                    s += this.getMap()[i][j].getId() + " |";
                }
                else
                {
                    s += "null |";
                }
            }
            s += "\n";
        }
        return s;
    }

    public Carte[][] getMap()
    {
        return map;
    }
}
