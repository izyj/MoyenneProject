package esgi.ikji.mamoyenne;

        import android.app.Fragment;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Spinner;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.List;

        import esgi.ikji.mamoyenne.DAO.MatiereDAO;
        import esgi.ikji.mamoyenne.DAO.MySQLiteHelper;
        import esgi.ikji.mamoyenne.DAO.NoteDAO;
        import esgi.ikji.mamoyenne.Modele.Matiere;
        import esgi.ikji.mamoyenne.Modele.Note;

/**
 * Created by Navi on 25/06/2015.
 */
public class FormAddNoteFragment extends Fragment {
    EditText nameNoteFromForm,coefNoteFromForm;
    Spinner listMat;
    public FormAddNoteFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_formaddnotefragment, container, false);

        // GET VALUES FROM FORM
        nameNoteFromForm = (EditText) v.findViewById(R.id.et_nameNot);
        coefNoteFromForm = (EditText) v.findViewById(R.id.et_coefNot);

        final MatiereDAO tbMat = new MatiereDAO(getActivity());

        listMat = (Spinner) v.findViewById(R.id.listmatiere);
        try {
            ArrayAdapter<String> adapterMatiere = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, getValueMat());
            listMat.setAdapter(adapterMatiere);
        }catch(Exception e){
            e.printStackTrace();
        }


        // EVENEMENT " ENREGISTRER "

        Button bt_note = (Button) v.findViewById(R.id.btSaveNote);
        bt_note.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // CONDITION COEFICIENT IS OK OR NOT
                if(verifCoef(coefNoteFromForm.getText().toString()) == false){
                    Toast.makeText(getActivity().getApplicationContext(), "Erreur Coefficient", Toast.LENGTH_SHORT).show();
                }else{
                    /*Ajout en Base de donnees */
                    final NoteDAO tbNote = new NoteDAO(getActivity());
                    final MatiereDAO handlerMatiere= new MatiereDAO(getActivity());

                    try {
                        String nameMatiere = listMat.getSelectedItem().toString();
                        Note no = new Note(nameNoteFromForm.getText().toString(),handlerMatiere.getMatiereByName(nameMatiere),Integer.parseInt(coefNoteFromForm.getText().toString()));
                        tbNote.addNote(no);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    //CharSequence str = tbMat.getMatiere(0).getNomMatiere().toString();
                    //Toast.makeText(getActivity().getApplicationContext(), str, Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }
    private boolean verifCoef(String _coef){
        try
        {
            double d = Double.parseDouble(_coef);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
    /**
     * Retourne la valeur de la matiere
     * @return
     */
    public String[] getValueMat(){
        MatiereDAO db = new MatiereDAO(getActivity());
        try {
            List<Matiere> list = db.getAllMatieres();
            String[] tmp = new String[list.size()];
            for(int i=0;i<list.size();i++){

                tmp[i] = list.get(i).getNomMatiere();
            }
            return tmp;
           // Matiere m = db.getMatiere("Maths");
          //  String[] array = {m.getNomMatiere()};
          //  return array;
        }catch(Exception e){
            e.printStackTrace();

        }
        return null;
    }

}