package esgi.ikji.mamoyenne.Modele;

/**
 * Created by Jonathan on 20/06/2015.
 */
public class Note {

    private int id;
    private String value;
    private Matiere matiere;
    private int coef;


    public Note() {
    }
    public Note(String _value, Matiere _m,int _coef){
        this.value = _value;
        this.matiere = _m;
        this.coef = _coef;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String note) {
        this.value = note;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public int getCoef(){
        return coef;
    }

    public void setNote(Note n) {
        this.coef = coef;
    }
}
