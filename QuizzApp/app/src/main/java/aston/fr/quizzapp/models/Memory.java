package aston.fr.quizzapp.models;

import java.util.ArrayList;
import java.util.List;

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
        int k = 0;
        for (int i = 0; i < 2; i++)
        {
            for (int j = 0; j < Constant.MEMORY_SIZE_WIDTH * Constant.MEMORY_SIZE_HEIGHT / 2; j++)
            {
                String nb;
                switch (j)
                {
                    case 0: nb = Constant.NUMPOKEDEX_POKEMON1;
                        break;
                    case 1: nb = Constant.NUMPOKEDEX_POKEMON2;
                        break;
                    case 2: nb = Constant.NUMPOKEDEX_POKEMON3;
                        break;
                    case 3: nb = Constant.NUMPOKEDEX_POKEMON4;
                        break;
                    case 4: nb = Constant.NUMPOKEDEX_POKEMON5;
                        break;
                    case 5: nb = Constant.NUMPOKEDEX_POKEMON6;
                        break;
                    default: nb = "150";
                        break;
                }
                Carte c = new Carte(j, String.format(Constant.MEMORY_URLS, nb));
                c.setPosition(k++);
                map.add(c);
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
        Carte tmp2 = map.get(r2);
        tmp2.setPosition(r);
        tmp.setPosition(r2);
        map.set(r, tmp2);
        map.set(r2, tmp);
    }

    public List<Carte> getMap() {
        return map;
    }
}
