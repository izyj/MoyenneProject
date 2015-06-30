package esgi.ikji.mamoyenne.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.w3c.dom.Comment;

import java.sql.SQLException;

import esgi.ikji.mamoyenne.Modele.Matiere;

//
import java.util.ArrayList;
import java.util.List;



/**
 * Created by Jonathan on 20/06/2015.
 */
public class MatiereDAO {

    // Champs de la base de données
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private Context context;

    private String[] allColumns = { MySQLiteHelper.MATIERE_ID,
            MySQLiteHelper.MATIERE_NAME, MySQLiteHelper.MATIERE_COEF };


    public MatiereDAO(Context context) {
        dbHelper = new MySQLiteHelper(context);
        this.context = context;
    }
    public void open() throws SQLException,Exception {
        database = dbHelper.getWritableDatabase();
    }

    public void close()throws SQLException,Exception  {
        dbHelper.close();
    }

    /**
     * Ajout d'une matiere dans la BDD
     * @param mat
     */
    public Matiere addMatiere(Matiere mat) throws Exception{
        this.open();
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.MATIERE_NAME, mat.getNomMatiere());
        values.put(MySQLiteHelper.MATIERE_COEF, mat.getCoeficient());
        long insertId = database.insert(MySQLiteHelper.TABLE_MATIERE, null, values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_MATIERE,
                allColumns, MySQLiteHelper.MATIERE_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Matiere matiere = cursorToMatiere(cursor);
        cursor.close();
        close();
        return matiere;
    }




    private Matiere cursorToMatiere(Cursor cursor) {
        Matiere matiere = new Matiere();
        NoteDAO ndao = new NoteDAO(context);
        matiere.setId(cursor.getInt(0));
        matiere.setNomMatiere(cursor.getString(1));
        matiere.setCoeficient(cursor.getInt(2));
        try {
            matiere.setNotes(ndao.getAllNoteByMatiere(matiere));
        }catch(SQLException e){
            e.printStackTrace();
        }
        return matiere;
    }


    public void deleteMatiere(Matiere matiere)  throws Exception {
        this.open();

        System.out.println("Comment deleted with id: " + matiere.getNomMatiere());
        database.delete(MySQLiteHelper.TABLE_MATIERE, MySQLiteHelper.MATIERE_NAME
                + " = " + matiere.getNomMatiere(), null);
        close();
    }

<<<<<<< HEAD
    public ArrayList<Matiere> getAllMatieres()  throws Exception {
        ArrayList<Matiere> matieres = new ArrayList<Matiere>();
=======


    public List<Matiere> getAllMatieres()  throws Exception {
        List<Matiere> matieres = new ArrayList<Matiere>();
>>>>>>> 2579fade9a35503fbcbf9f3482a55ecfc528b17c
        this.open();
        Cursor cursor = database.query(MySQLiteHelper.TABLE_MATIERE,allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Matiere matiere = cursorToMatiere(cursor);
            matieres.add(matiere);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        close();
        return matieres;
    }

    public Matiere getMatiereByName(String nom)  throws Exception {
        Matiere matiere = null;
        this.open();
        Cursor cursor = database.query(MySQLiteHelper.TABLE_MATIERE,
                allColumns,MySQLiteHelper.MATIERE_NAME+" = ?", // c. selections
                new String[] { nom }, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            matiere = cursorToMatiere(cursor);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        close();
        return matiere;
    }
    public Matiere getMatiere(int id)  throws Exception {
        Matiere matiere = null;
        this.open();
        Cursor cursor = database.query(MySQLiteHelper.TABLE_MATIERE,
                allColumns," id = ?", // c. selections
                new String[] { String.valueOf(id) }, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            matiere = cursorToMatiere(cursor);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        close();
        return matiere;
    }


}
