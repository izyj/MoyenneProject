package esgi.ikji.mamoyenne.Modele;

/**
 * Created by Jonathan on 20/06/2015.
 */
public class Note {

    private int id;
    private float value;
    private Matiere matiere;


    public Note() {
    }

    public Note(int id, float note, Matiere matiere) {
        this.id = id;
        this.value = note;
        this.matiere = matiere;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getNote() {
        return value;
    }

    public void setNote(float note) {
        this.value = note;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }
}
