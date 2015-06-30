package esgi.ikji.mamoyenne;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import esgi.ikji.mamoyenne.Modele.Matiere;
import esgi.ikji.mamoyenne.Modele.Note;

public class ArrayAdapterMatiere extends ArrayAdapter<Matiere> {

    Context mContext;
    int layoutResourceId;
    ArrayList<Matiere> data = new ArrayList<Matiere>();

    /**
     * Constructeur
     * @param mContext
     * @param layoutResourceId
     * @param data
     */
    public ArrayAdapterMatiere(Context mContext, int layoutResourceId, ArrayList<Matiere> data) {

        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
    }

    /**
     * Renvoie la vue
     */
    public View getView(int position, View convertView, ViewGroup parent) {

        //Création de la vue si elle existe pas
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }

        // Récuparation de la matiere
        Matiere matiere = data.get(position);

        // Remplissage des textView avec les données des matières.
        TextView textViewMatiere = (TextView) convertView.findViewById(R.id.txt_matiere_name);
        textViewMatiere.setText(matiere.getNomMatiere());

        TextView textViewCoef = (TextView) convertView.findViewById(R.id.txt_matiere_coef);
        textViewCoef.setText(""+matiere.getCoeficient());

        TextView textViewNotes = (TextView) convertView.findViewById(R.id.txt_matiere_notes);
        String tmp = "";
        for(Note n : matiere.getNotes()){
            tmp = n.getValue() + "^" + n.getCoef();
        }
        textViewNotes.setText(tmp);

      //  TextView textViewMoyenne = (TextView) convertView.findViewById(R.id.moyenne);
      //  textViewMoyenne.setText(""+matiere.getMoyenne());

        return convertView;

    }


}