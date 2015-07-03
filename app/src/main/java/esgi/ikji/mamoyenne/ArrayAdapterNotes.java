package esgi.ikji.mamoyenne;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import esgi.ikji.mamoyenne.Modele.Matiere;
import esgi.ikji.mamoyenne.Modele.Note;

public class ArrayAdapterNotes extends ArrayAdapter<Note> {

    Context mContext;
    int layoutResourceId;
    ArrayList<Note> data = new ArrayList<Note>();

    /**
     * Constructeur
     * @param mContext
     * @param layoutResourceId
     * @param data
     */
    public ArrayAdapterNotes(Context mContext, int layoutResourceId, ArrayList<Note> data) {

        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
    }

    /**
     * Renvoie la vue
     */
    public View getView(int position, View convertView, ViewGroup parent) {

        //Creation de la vue si elle existe pas
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }

        // Recuperation de la note
        Note note = data.get(position);

        // Remplissage des textView avec les donnees de la note
        EditText edittextValueNote = (EditText) convertView.findViewById(R.id.et_note_value);
        edittextValueNote.setText(note.getValue());

       EditText edittextCoefNote = (EditText) convertView.findViewById(R.id.et_note_coef);
        edittextCoefNote.setText(Integer.toString(note.getCoef()));

      //  TextView textViewMoyenne = (TextView) convertView.findViewById(R.id.moyenne);
      //  textViewMoyenne.setText(""+matiere.getMoyenne());

        return convertView;

    }


}