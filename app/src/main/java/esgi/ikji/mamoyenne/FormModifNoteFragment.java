package esgi.ikji.mamoyenne;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import esgi.ikji.mamoyenne.DAO.NoteDAO;
import esgi.ikji.mamoyenne.Modele.Matiere;
import esgi.ikji.mamoyenne.Modele.Note;


public class FormModifNoteFragment extends Fragment {

    EditText valueNoteFromForm,coefNoteFromForm;
    ListView listViewNotes;
    final MySQLiteHelper db = new MySQLiteHelper(getActivity());
    Context ct;
    FragmentManager manager;
    FragmentTransaction transaction;
    public FormModifNoteFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // INFLATER
        View v = inflater.inflate(R.layout.fragment_form_modif_note, container, false);
        //code erreur
        int i =600;
        ct = getActivity().getApplicationContext();
        final MatiereDAO matDAO = new MatiereDAO(getActivity());
        final NoteDAO noteDAO = new NoteDAO(getActivity());
        Bundle bundle = this.getArguments();
        if (bundle != null) {
        i = bundle.getInt("idMatiere",600);

        }
        //si i a 600 alors on tombe en erreur
            if(i==600){
               Toast.makeText(getActivity().getApplicationContext(),"Aucune valeur à modifier",Toast.LENGTH_SHORT).show();
               return v;
            }
        try {
            Matiere mat = matDAO.getMatiere(i);
            ArrayList<Note> list = new ArrayList<Note>();
            list = noteDAO.getAllNoteByMatiere(mat);

            // Get the ListView by Id and instantiate the adapter with
            // matieres data and then set it the ListView

            ArrayAdapterNotes adapter = new ArrayAdapterNotes(ct,R.layout.list_note, list);
            listViewNotes = (ListView) v.findViewById(R.id.lv_note);
            listViewNotes.setAdapter(adapter);
            // Set the onItemClickListener on the ListView to listen for items clicks
            registerForContextMenu(listViewNotes);

        }catch(Exception e){
            e.printStackTrace();
        }



        ((MainActivity)getActivity()).setCurrent(this);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }





}
