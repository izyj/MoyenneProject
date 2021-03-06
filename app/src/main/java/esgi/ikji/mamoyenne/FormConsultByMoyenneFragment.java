package esgi.ikji.mamoyenne;


import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import esgi.ikji.mamoyenne.DAO.MatiereDAO;
import esgi.ikji.mamoyenne.DAO.NoteDAO;
import esgi.ikji.mamoyenne.Modele.Matiere;
import esgi.ikji.mamoyenne.Modele.Note;

/**
 * Created by Navi on 25/06/2015.
 */
public class FormConsultByMoyenneFragment extends Fragment {
    // variables declaration
    ListView listViewMatieres;
    Context ct;
    FragmentManager manager;
    FragmentTransaction transaction;
    ArrayAdapterMatiere adapter;
    public FormConsultByMoyenneFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_list_item, container, false);
        ct = getActivity().getApplicationContext();

        ActionBar ab = getActivity().getActionBar();


        // Inflate the layout for this fragment
        final MatiereDAO tbMat = new MatiereDAO(getActivity());
        try {
            ArrayList<Matiere> list = new ArrayList<Matiere>();
            list = tbMat.getAllMatieres();
            NoteDAO ndao = new NoteDAO(getActivity().getApplicationContext());
            for(Matiere m : list){
                try {
                    ArrayList<Note> list_notes = ndao.getAllNoteByMatiere(m);
                    m.setNotes(list_notes);
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            Collections.sort(list, new Comparator<Matiere>() {
                @Override
                public int compare(Matiere lhs, Matiere rhs) {
                    if (lhs.getMoyenne() > rhs.getMoyenne()) return -1;

                    return 1;
                }
            });
            adapter = new ArrayAdapterMatiere(ct, R.layout.list_matiere, list);

            listViewMatieres = (ListView) v.findViewById(R.id.lv_matiere);
            listViewMatieres.setAdapter(adapter);

            // Set the onItemClickListener on the ListView to listen for items clicks
            registerForContextMenu(listViewMatieres);

            Button bt_calculate = (Button) v.findViewById(R.id.bt_calculate);
            bt_calculate.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    LayoutInflater inflater = LayoutInflater.from(FormConsultByMoyenneFragment.this.getActivity().getApplicationContext());
                    View root = inflater.inflate(R.layout.layout_list_item,null);
                    TextView txt_moy_gene = (TextView) root.findViewById(R.id.txt_matiere_moy_general);
                    txt_moy_gene.setText(String.valueOf(getAverageGeneral(adapter.getMoyenneGeneValue(), adapter.getMoyenneGeneCoef())));
                    Toast.makeText(ct,String.valueOf(getAverageGeneral(adapter.getMoyenneGeneValue(), adapter.getMoyenneGeneCoef())), Toast.LENGTH_LONG).show();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return v;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        ListView lv = (ListView) v;
        AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) menuInfo;
        Matiere obj = (Matiere) lv.getItemAtPosition(acmi.position);

      //  menu.add(obj.getNomMatiere());
        menu.setHeaderTitle("Selectionner une action");
        menu.add(0,obj.getId(), 0, "Modifier note");//groupId, itemId, order, title
        menu.add(0, obj.getId(), 0, "Modifier matiere");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){

        Fragment fragmentModifMatiere = new FormModifMatiereFragment();
        Fragment fragment = new FormModifNoteFragment();
        if(item.getTitle()=="Modifier note"){

            Bundle bundle = new Bundle();
            bundle.putInt("idMatiere", item.getItemId());
            fragment.setArguments(bundle);

            manager = getFragmentManager();
            transaction = manager.beginTransaction();
            transaction.replace(R.id.container,fragment);
            transaction.commit();

            //Toast.makeText(getActivity().getApplicationContext(),Integer.toString(item.getItemId()),Toast.LENGTH_LONG).show();
        }
        else if(item.getTitle()=="Modifier matiere"){
            Bundle bundle = new Bundle();
            bundle.putInt("idMatiere", item.getItemId());
            fragmentModifMatiere.setArguments(bundle);

            manager = getFragmentManager();
            transaction = manager.beginTransaction();
            transaction.replace(R.id.container,fragmentModifMatiere);
            transaction.commit();
        }else{
            return false;
        }
        return true;
    }
    public double getAverageGeneral(double res,int coef){
        return res/coef;
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