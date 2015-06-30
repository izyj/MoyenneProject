package esgi.ikji.mamoyenne.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import esgi.ikji.mamoyenne.Modele.Matiere;
import esgi.ikji.mamoyenne.Modele.Note;

/**
 * Created by Jonathan on 21/06/2015.
 */
public class NoteDAO {

    // Champs de la base de données
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.NOTE_ID,
            MySQLiteHelper.NOTE_VALUE, MySQLiteHelper.NOTE_ID_MATIERE };


    public NoteDAO(Context context) {
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
     * @param note
     */
    public Note addNote(Note note) throws SQLException{
        this.open();
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.NOTE_VALUE, note.getValue());
        values.put(MySQLiteHelper.NOTE_ID_MATIERE, note.getMatiere().getId());

        long insertId = database.insert(MySQLiteHelper.TABLE_NOTE, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_NOTE,
                allColumns, MySQLiteHelper.NOTE_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Note rep = cursorToNote(cursor);
        cursor.close();
        close();
        return rep;
    }

    private Note cursorToNote(Cursor cursor) {
        Note note = new Note();
        note.setId(cursor.getInt(0));
        note.setMatiere(new Matiere(cursor.getInt(1)));
        note.setValue(cursor.getString(2));
        return note;
    }


    public void deleteNote(Note note) throws SQLException{
        this.open();
        long id = note.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_NOTE, MySQLiteHelper.NOTE_ID
                + " = " + id, null);
        close();
    }

    public List<Note> getAllNote() throws SQLException{
        List<Note> notes = new ArrayList<Note>();
        this.open();
        Cursor cursor = database.query(MySQLiteHelper.TABLE_NOTE,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Note note1 = cursorToNote(cursor);
            notes.add(note1);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        close();
        return notes;
    }

    public ArrayList<Note> getAllNoteByMatiere(Matiere matiere) throws SQLException{

        ArrayList<Note> notes = new ArrayList<Note>();
        this.open();
        Cursor cursor = database.query(MySQLiteHelper.TABLE_NOTE,
        allColumns, MySQLiteHelper.NOTE_ID_MATIERE, new String[]{matiere.getNomMatiere()}, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Note note1 = cursorToNote(cursor);
            notes.add(note1);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        close();
        return notes;
    }
    public void updateNote(Note note)  throws Exception {
        this.open();
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.NOTE_ID_MATIERE, note.getValue());
        String[] selectionArgs = { Integer.toString(note.getId()) };
        System.out.println("Comment updated with id: " + note.getId());
        database.update(MySQLiteHelper.TABLE_NOTE, values, MySQLiteHelper.NOTE_ID
                + " = ?", selectionArgs);
        close();
    }
}
