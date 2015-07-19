package esgi.ikji.mamoyenne;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

import esgi.ikji.mamoyenne.DAO.NoteDAO;
import esgi.ikji.mamoyenne.Modele.Note;

public class ArrayAdapterNotes extends ArrayAdapter<Note> {

    Context mContext;
    int layoutResourceId;
    ArrayList<Note> data = new ArrayList<Note>();

    /**
     * Constructeur
     * @param mContext
     * @param layoutResourceId
     * @param data
     */
    public ArrayAdapterNotes(Context mContext, int layoutResourceId, ArrayList<Note> data) {

        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
    }

    /**
     * Renvoie la vue
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        // Instanciation du DAO note

        final NoteDAO dao = new NoteDAO(getContext());

        //Creation de la vue si elle existe pas
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }

        // Recuperation de la note
        Note note = data.get(position);

        // Remplissage des textView avec les donnees de la note
        final EditText edittextValueNote = (EditText) convertView.findViewById(R.id.et_note_value);
        edittextValueNote.setText(note.getValue());

       final EditText edittextCoefNote = (EditText) convertView.findViewById(R.id.et_note_coef);
        edittextCoefNote.setText(Integer.toString(note.getCoef()));

        ImageButton bt_modif = (ImageButton) convertView.findViewById(R.id.bt_modif_note);
        bt_modif.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try{
                    Note nt = data.get(position);
                    nt.setValue(edittextValueNote.getText().toString());
                    nt.setCoef(Integer.parseInt(edittextCoefNote.getText().toString()));
                    dao.updateNote(nt);
                    Toast.makeText(v.getContext(), "Note modifiee", Toast.LENGTH_SHORT).show();
                }
                catch(Exception e){
                    e.printStackTrace();
                }


            }
        });

        ImageButton bt_delete = (ImageButton) convertView.findViewById(R.id.bt_del_note);
        bt_delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try{
                    Note nt = data.get(position);
                    nt.setValue(edittextValueNote.getText().toString());
                    nt.setCoef(Integer.parseInt(edittextCoefNote.getText().toString()));
                    dao.deleteNote(nt);
                    Toast.makeText(v.getContext(), "Note supprimee", Toast.LENGTH_SHORT).show();
                    remove(nt);
                    notifyDataSetChanged();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        return convertView;

    }


}