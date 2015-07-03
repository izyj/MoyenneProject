package esgi.ikji.mamoyenne;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

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

        ct = getActivity().getApplicationContext();
        final MatiereDAO matDAO = new MatiereDAO(getActivity());
        final NoteDAO noteDAO = new NoteDAO(getActivity());


        try {
            Matiere mat = matDAO.getMatiere(1);
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
        return v;
    }




}
