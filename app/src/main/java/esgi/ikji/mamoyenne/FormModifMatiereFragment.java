package esgi.ikji.mamoyenne;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import esgi.ikji.mamoyenne.DAO.MatiereDAO;
import esgi.ikji.mamoyenne.DAO.MySQLiteHelper;
import esgi.ikji.mamoyenne.DAO.NoteDAO;
import esgi.ikji.mamoyenne.Modele.Matiere;
import esgi.ikji.mamoyenne.Modele.Note;

public class FormModifMatiereFragment extends Fragment {
    EditText nameMatFromForm,coefMatFromForm;
    final MySQLiteHelper db = new MySQLiteHelper(getActivity());
    Context ct;
    FragmentManager manager;
    FragmentTransaction transaction;
    public FormModifMatiereFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // INFLATER
        View v = inflater.inflate(R.layout.fragment_formmodifmatierefragment, container, false);
        final MatiereDAO dao = new MatiereDAO(getActivity());

        int i = 0;
        ct = getActivity().getApplicationContext();
        final MatiereDAO matDAO = new MatiereDAO(getActivity());
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            i = bundle.getInt("idMatiere",0);

        }

        try {
            final Matiere mat  = matDAO.getMatiere(i);
            nameMatFromForm =(EditText)  v.findViewById(R.id.et_nameMat);
            coefMatFromForm =(EditText)  v.findViewById(R.id.et_coefMat);

            nameMatFromForm.setText(mat.getNomMatiere());
            coefMatFromForm.setText(String.valueOf(mat.getCoeficient()));
        }catch(Exception e){
            e.printStackTrace();
        }
        final int e =i;
        Button bt_valider = (Button) v.findViewById(R.id.btSaveMatiere);
        bt_valider.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try{
                    Matiere mat2 = matDAO.getMatiere(e);
                    mat2.setNomMatiere(nameMatFromForm.getText().toString());
                    mat2.setCoeficient(Integer.parseInt(coefMatFromForm.getText().toString()));
                    dao.updateMatiere(mat2);
                    Toast.makeText(getActivity().getApplicationContext(),"Matiere modifier",Toast.LENGTH_LONG).show();

                }
                catch(Exception e){
                    e.printStackTrace();
                }


            }
        });




        Button bt_supprimer = (Button) v.findViewById(R.id.btDeleteMatiere);
        bt_supprimer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try{
                    Matiere mat2 = matDAO.getMatiere(e);
                    mat2.setNomMatiere(nameMatFromForm.getText().toString());
                    mat2.setCoeficient(Integer.parseInt(coefMatFromForm.getText().toString()));
                    dao.deleteMatiere(mat2);
                    Toast.makeText(getActivity().getApplicationContext(),"Matiere modifier",Toast.LENGTH_LONG).show();
                    


                }
                catch(Exception e){
                    e.printStackTrace();
                }


            }
        });

        return v;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            nameMatFromForm.setText(savedInstanceState.getString("nomMatiere"));
            coefMatFromForm.setText(coefMatFromForm.getText().toString());
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("nomMatiere", nameMatFromForm.getText().toString());
        outState.putString("coef",coefMatFromForm.getText().toString());
    }

}