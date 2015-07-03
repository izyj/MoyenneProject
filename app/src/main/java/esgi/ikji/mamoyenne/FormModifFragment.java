package esgi.ikji.mamoyenne;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import esgi.ikji.mamoyenne.DAO.MatiereDAO;
import esgi.ikji.mamoyenne.DAO.MySQLiteHelper;
import esgi.ikji.mamoyenne.Modele.Matiere;

public class FormModifFragment extends Fragment {
    EditText nameMatFromForm,coefMatFromForm;
    final MySQLiteHelper db = new MySQLiteHelper(getActivity());
    String idMatiere;
    String idNote;
    public FormModifFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // INFLATER
        View v = inflater.inflate(R.layout.fragment_formmodifmatierefragment, container, false);

        // 1 - Recuperer la matiere
        //EditText etMat = (EditText) v.findViewById(R.id.)

        // 2 - Chercher les notes par la matiere

        // 3 - Obtenir une liste de notes

        // 4 - Afficher la liste de notes


        // Inflate the layout for this fragment
        final MatiereDAO tbMat = new MatiereDAO(getActivity());
        try {
            ArrayList<Matiere> list = new ArrayList<Matiere>();
            list = tbMat.getAllMatieres();

            // Get the ListView by Id and instantiate the adapter with
            // matieres data and then set it the ListView

            /*ArrayAdapterNotes adapter = new ArrayAdapterNotes(getActivity().getApplicationContext(),R.layout.list_note, list);
            listViewNotes = (ListView) v.findViewById(R.id.lv_note);
            listViewNotes.setAdapter(adapter);*/
        }catch(Exception e){
            e.printStackTrace();
        }
        return v;
    }

}