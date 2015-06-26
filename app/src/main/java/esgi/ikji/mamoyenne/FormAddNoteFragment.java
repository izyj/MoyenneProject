package esgi.ikji.mamoyenne;

        import android.app.Fragment;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

/**
 * Created by Navi on 25/06/2015.
 */
public class FormAddNoteFragment extends Fragment {
    EditText nameNoteFromForm,coefNoteFromForm;

    public FormAddNoteFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_formaddnotefragment, container, false);

        // GET VALUES FROM FORM
        nameNoteFromForm = (EditText) v.findViewById(R.id.et_nameNot);
        coefNoteFromForm = (EditText) v.findViewById(R.id.et_coefNot);


        // EVENEMENT " ENREGISTRER "

        Button bt_matiere = (Button) v.findViewById(R.id.btSaveNote);
        bt_matiere.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // CONDITION COEFICIENT IS OK OR NOT
                if(verifCoef(coefNoteFromForm.getText().toString()) == false){
                    Toast.makeText(getActivity().getApplicationContext(), "Erreur Coefficient", Toast.LENGTH_SHORT).show();
                }else{
                    /* Ajout en Base de donnees */
                    CharSequence str = "Ajout de "+ nameNoteFromForm.getText() + " Coef "+coefNoteFromForm.getText();
                    Toast.makeText(getActivity().getApplicationContext(), str, Toast.LENGTH_SHORT).show();
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

}