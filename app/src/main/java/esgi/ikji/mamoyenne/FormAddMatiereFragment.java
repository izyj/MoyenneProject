package esgi.ikji.mamoyenne;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import esgi.ikji.mamoyenne.DAO.MatiereDAO;
import esgi.ikji.mamoyenne.DAO.MySQLiteHelper;
import esgi.ikji.mamoyenne.Modele.Matiere;

public class FormAddMatiereFragment extends Fragment {
    EditText nameMatFromForm,coefMatFromForm;
    final MySQLiteHelper db = new MySQLiteHelper(getActivity());
    public FormAddMatiereFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // INFLATER
        View v = inflater.inflate(R.layout.fragment_formaddmatierefragment, container, false);

        // GET VALUES FROM FORM
        nameMatFromForm = (EditText) v.findViewById(R.id.et_nameMat);
        coefMatFromForm = (EditText) v.findViewById(R.id.et_coefMat);


       // EVENEMENT " ENREGISTRER "

        Button bt_matiere = (Button) v.findViewById(R.id.btSaveMatiere);
        bt_matiere.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // CONDITION COEFICIENT IS OK OR NOT
                if(verifCoef(coefMatFromForm.getText().toString()) == false){
                    Toast.makeText(getActivity().getApplicationContext(), "Erreur Coefficient", Toast.LENGTH_SHORT).show();
                }else{
                    /* Ajout en Base de donnees */
                    final MatiereDAO tbMat = new MatiereDAO(getActivity());
                    Matiere ma = new Matiere(nameMatFromForm.getText().toString(),Integer.parseInt(coefMatFromForm.getText().toString()));
                    try {
                        tbMat.addMatiere(ma);
                        Toast.makeText(getActivity().getApplicationContext(),"Matiere ajoutee",Toast.LENGTH_LONG).show();
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


}