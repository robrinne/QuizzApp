package models;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import aston.fr.quizzapp.R;
import aston.fr.quizzapp.memory.MemoryActivity;
import utils.Constant;

/**
 * Created by kevinp on 31/01/2018.
 */

public class CarteAdapter extends ArrayAdapter<Carte> {

    private int resId;

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

        Carte item = getItem(position);

        // mise à jour des données
        //myViewHolder.imageViewCarte = (item.getImgUrl());
        if (item != null)
        {
            Picasso.with(getContext())
                    .load(item.getImgUrl())
                    .into(myViewHolder.imageViewCarte);
            myViewHolder.buttonCarte.setText(Integer.valueOf(item.getId()).toString());

            myViewHolder.buttonCarte.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    view.setVisibility(View.INVISIBLE);
                }
            });
        }

        return convertView;
    }

    private class ViewHolder {
        ImageView imageViewCarte;
        Button buttonCarte;
    }

}
