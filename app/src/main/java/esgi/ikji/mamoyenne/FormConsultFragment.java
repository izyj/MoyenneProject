package esgi.ikji.mamoyenne;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import esgi.ikji.mamoyenne.DAO.MatiereDAO;
import esgi.ikji.mamoyenne.DAO.MySQLiteHelper;
import esgi.ikji.mamoyenne.Modele.Matiere;

/**
 * Created by Navi on 25/06/2015.
 */
public class FormConsultFragment extends Fragment {


    public FormConsultFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_formconsultfragment, container, false);
        // Inflate the layout for this fragment

        final MatiereDAO tbMat = new MatiereDAO(getActivity());
        try {
            List<Matiere> list = new ArrayList<Matiere>();
            list = tbMat.getAllMatieres();

            String str = "Liste Matiere : \n";
            for(Matiere m : list){
                str += m.getNomMatiere()+"\n";
            }
            Toast.makeText(getActivity().getApplicationContext(), str, Toast.LENGTH_LONG);
        }catch(Exception e){
            e.printStackTrace();
        }

        return v;
    }

}