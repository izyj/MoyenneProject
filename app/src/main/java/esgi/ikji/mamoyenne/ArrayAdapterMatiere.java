package esgi.ikji.mamoyenne;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import esgi.ikji.mamoyenne.DAO.NoteDAO;
import esgi.ikji.mamoyenne.Modele.Matiere;
import esgi.ikji.mamoyenne.Modele.Note;

public class ArrayAdapterMatiere extends ArrayAdapter<Matiere> {

    Context mContext;
    int layoutResourceId;
    ArrayList<Matiere> data = new ArrayList<Matiere>();
    public static double moygeneValue = 0.00;
    public static int moygeneCoef = 0;
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
    public Double getMoyenneGeneValue(){
        return this.moygeneValue;
    }
    public int getMoyenneGeneCoef(){
        return this.moygeneCoef;
    }
    /**
     * Renvoie la vue
     */
    public View getView(final int position, View convertView, ViewGroup parent) {

        //Creation de la vue si elle existe pas
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }


        // Recuperation de la matiere et des notes de la matiere
        if(position == 0){
            moygeneValue = 0;
            moygeneCoef = 0;
        }
        Matiere matiere = data.get(position);

        NoteDAO ndao = new NoteDAO(mContext);


        // Remplissage des textView avec les donnees des matieres.
        TextView textViewMatiere = (TextView) convertView.findViewById(R.id.txt_matiere_name);
        textViewMatiere.setText(matiere.getNomMatiere());

        TextView textViewCoef = (TextView) convertView.findViewById(R.id.txt_matiere_coef);
        textViewCoef.setText(""+matiere.getCoeficient());

        TextView textViewNotes = (TextView) convertView.findViewById(R.id.txt_matiere_notes);





        // Rempli mon tableau


        try {
            ArrayList<Note> list_notes = ndao.getAllNoteByMatiere(matiere);
            matiere.setNotes(list_notes);
        }catch(SQLException e){
            e.printStackTrace();
        }
        String tmp = "";
        if(!matiere.getNotes().isEmpty()){
            for(Note n : matiere.getNotes()){
                tmp += n.getValue().toString() + "^" + n.getCoef() + "\n ";
            }
        }else{
            tmp = "-";
        }
        textViewNotes.setText(tmp);


        TextView textViewMoyenne = (TextView) convertView.findViewById(R.id.txt_matiere_moy);
        DecimalFormat df = new DecimalFormat("#.#");
        if(Double.isNaN(matiere.getMoyenne())){
            textViewMoyenne.setText("-");
        }else{
            textViewMoyenne.setText(df.format(matiere.getMoyenne()));
        }




      //  TextView textViewMoyenne = (TextView) convertView.findViewById(R.id.moyenne);
      //  textViewMoyenne.setText(""+matiere.getMoyenne());
        addToGeneralAverage(matiere.getMoyenne(), matiere.getCoeficient());

        return convertView;

    }

    public void sortMatiere(){
        Collections.sort(data, new Comparator<Matiere>() {
            public int compare(Matiere result1, Matiere result2) {
                return result1.getNomMatiere().compareTo(result2.getNomMatiere());
            }
        });
    }

    public void addToGeneralAverage(double moymatiere,int coefmatiere){
            this.moygeneValue += moymatiere * coefmatiere;
            this.moygeneCoef += coefmatiere;
    }

}