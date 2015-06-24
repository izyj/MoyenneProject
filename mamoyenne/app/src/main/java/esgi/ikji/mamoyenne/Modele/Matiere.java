package esgi.ikji.mamoyenne.Modele;

import java.util.ArrayList;

/**
 * Created by Jonathan on 20/06/2015.
 */
public class Matiere {

    private int id;
    private String nomMatiere;
    private int coeficient;
    private ArrayList<Note> notes;


    public Matiere() {
    }

    public Matiere(int id, String nomMatiere,int coef, ArrayList<Note> notes) {
        this.id = id;
        this.nomMatiere = nomMatiere;
        this.notes = notes;
        this.coeficient = coef;

    }

    public int getCoeficient() {
        return coeficient;
    }

    public void setCoeficient(int coeficient) {
        this.coeficient = coeficient;
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
    }

    public String getNomMatiere() {

        return nomMatiere;
    }

    public void setNomMatiere(String nomMatiere) {
        this.nomMatiere = nomMatiere;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
