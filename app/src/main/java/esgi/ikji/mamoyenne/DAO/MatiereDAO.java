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
    private String[] allColumns = { MySQLiteHelper.MATIERE_ID,
            MySQLiteHelper.MATIERE_NAME, MySQLiteHelper.MATIERE_COEF };


    public MatiereDAO(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    /**
     * Ajout d'une matiere dans la BDD
     * @param mat
     */
    public Matiere CreateMatiere(Matiere mat){
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.MATIERE_NAME, mat.getNomMatiere());
        values.put(MySQLiteHelper.MATIERE_COEF, mat.getCoeficient());
        long insertId = database.insert(MySQLiteHelper.TABLE_MATIERE, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_MATIERE,
                allColumns, MySQLiteHelper.MATIERE_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Matiere matiere = cursorToMatiere(cursor);
        cursor.close();
        return matiere;
    }




    private Matiere cursorToMatiere(Cursor cursor) {
        Matiere matiere = new Matiere();
        matiere.setId(cursor.getInt(0));
        matiere.setNomMatiere(cursor.getString(1));
        matiere.setCoeficient(cursor.getInt(2));
        return matiere;
    }


    public void deleteMatiere(Matiere matiere) {
        long id = matiere.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_MATIERE, MySQLiteHelper.MATIERE_ID
                + " = " + id, null);
    }

    public List<Matiere> getAllMatieres() {
        List<Matiere> matieres = new ArrayList<Matiere>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_MATIERE,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Matiere matiere = cursorToMatiere(cursor);
            matieres.add(matiere);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return matieres;
    }

    public Matiere getMatiere(int  id) {
        Matiere matiere = null;
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
        return matiere;
    }


}
