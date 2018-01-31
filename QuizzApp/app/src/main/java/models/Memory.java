package models;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import utils.Constant;

public class Memory
{
    private List<Carte> map;

    public void init()
    {
        this.map = new ArrayList<>();
        initMap();
    }

    private void initMap()
    {
        for (int i = 0; i < 2; i++)
        {
            for (int j = 0; j < Constant.MEMORY_URLS.length; j++)
            {
                map.add(new Carte(j, Constant.MEMORY_URLS[j]));
            }
        }
        shuffleMap();
    }

    private void shuffleMap()
    {
        for (int i=0; i<50; i++)
        {
            shuffleOnce();
        }
    }

    private void shuffleOnce()
    {
        int r = (int)((map.size()-1)*Math.random());
        Carte tmp = map.get(r);
        int r2 = (int)((map.size()-1)*Math.random());
        while (r == r2)
        {
            r2 = (int)((map.size()-1)*Math.random());
        }
        map.set(r, map.get(r2));
        map.set(r2, tmp);
    }

    public List<Carte> getMap() {
        return map;
    }
}
