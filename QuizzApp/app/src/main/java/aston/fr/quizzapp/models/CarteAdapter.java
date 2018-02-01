package aston.fr.quizzapp.models;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import aston.fr.quizzapp.R;
import aston.fr.quizzapp.memory.MemoryActivity;
import utils.Constant;

public class CarteAdapter extends ArrayAdapter<Carte> {
    public static int nbReturned = 0;
    private int resId;
    private View carte1 = null;
    private View carte2 = null;
    private String carte1ID = "";
    private String carte2ID = "";

    public CarteAdapter(@NonNull Context context, int resource, @NonNull List<Carte> objects)
    {
        super(context, resource, objects);
        resId = resource; // R.layout.item_restaurant
    }

    @NonNull @Override public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        ViewHolder myViewHolder = null; // déclaration = null

        if(convertView == null)
        {
            nbReturned = 0;
            convertView = LayoutInflater.from(getContext()).inflate(resId, null);

            // instance de classe
            myViewHolder = new ViewHolder();

            // récupération de l'id
            myViewHolder.buttonCarte = convertView.findViewById(R.id.buttonCarte);
            myViewHolder.imageViewCarte = convertView.findViewById(R.id.imageViewCarte);

            convertView.setTag(myViewHolder); // enregistrement de l'objet myViewHolder
        } else {
            myViewHolder = (ViewHolder) convertView.getTag(); // récupération de l'objet
        }

        final Carte item = getItem(position);

        // mise à jour des données
        if (item != null)
        {
            Picasso.with(getContext())
                    .load(item.getImgUrl())
                    .into(myViewHolder.imageViewCarte);

            myViewHolder.buttonCarte.setOnClickListener(new View.OnClickListener()
            {
                @Override public void onClick(View view)
                {
                    if (nbReturned < 2)
                    {
                        view.setVisibility(View.INVISIBLE);
                        item.setVisible(true);
                        nbReturned++;
                        if (nbReturned == 1)
                        {
                            carte1 = view;
                            carte1ID = item.getId();
                        }
                        else {
                            carte2 = view;
                            carte2ID = item.getId();
                            new Handler().postDelayed(new TimerTask()
                            {
                                @Override
                                public void run() {
                                    if (!carte1ID.equals(carte2ID)) {
                                        carte1.setVisibility(View.VISIBLE);
                                        carte2.setVisibility(View.VISIBLE);
                                    }
                                    carte1 = null;
                                    carte2 = null;
                                    carte1ID = "";
                                    carte2ID = "";
                                    nbReturned = 0;
                                }
                            }, 1000);
                        }
                    }
                }
            });
        }

        return convertView;
    }

    public void resetAll()
    {
        for (int i=0; i < Constant.MEMORY_SIZE_HEIGHT * Constant.MEMORY_SIZE_WIDTH; i++)
        {

        }
    }

    private class ViewHolder {
        ImageView imageViewCarte;
        Button buttonCarte;
    }

}
