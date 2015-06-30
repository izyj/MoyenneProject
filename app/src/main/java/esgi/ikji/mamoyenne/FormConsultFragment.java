package esgi.ikji.mamoyenne;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import esgi.ikji.mamoyenne.DAO.MatiereDAO;
import esgi.ikji.mamoyenne.Modele.Matiere;

/**
 * Created by Navi on 25/06/2015.
 */
public class FormConsultFragment extends Fragment {
    // variables declaration
    ListView listViewMatieres;

    public FormConsultFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_list_item, container, false);
        // Inflate the layout for this fragment
        final MatiereDAO tbMat = new MatiereDAO(getActivity());
        try {
            ArrayList<Matiere> list = new ArrayList<Matiere>();
            list = tbMat.getAllMatieres();

            // Get the ListView by Id and instantiate the adapter with
            // matieres data and then set it the ListView

            ArrayAdapterMatiere adapter = new ArrayAdapterMatiere(getActivity().getApplicationContext(),R.layout.list_matiere, list);
            listViewMatieres = (ListView) v.findViewById(R.id.lv_matiere);
            listViewMatieres.setAdapter(adapter);
            // Set the onItemClickListener on the ListView to listen for items clicks

        }catch(Exception e){
            e.printStackTrace();
        }

        return v;
    }

}