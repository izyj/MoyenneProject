package esgi.ikji.mamoyenne.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jonathan on 20/06/2015.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    //Nom de la base de donnees
    private static final String DATABASE_NAME = "moyenne.db";

    //matiere
    public static  final String TABLE_MATIERE ="matiere";
    public static  final String MATIERE_ID   ="matiere_id";
    public static  final String MATIERE_NAME ="matiere_name";
    public static  final String MATIERE_COEF ="matiere_coef";

    //Note
    public static  final String TABLE_NOTE  ="note";
    public static  final String NOTE_ID     ="note_id";
    public static  final String NOTE_ID_MATIERE ="note_id_matiere";
    public static  final String NOTE_VALUE ="note_value";
    // Version
    private static final int DATABASE_VERSION = 1;

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    /**
     * Creation de la base de donnees
     */
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_MATIERE_TABLE = "CREATE TABLE "+TABLE_MATIERE+" ( " +
                MATIERE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MATIERE_NAME+" TEXT, "+
                MATIERE_COEF+" TEXT )";

        String CREATE_NOTE_TABLE = "CREATE TABLE "+TABLE_NOTE +"( " +
                NOTE_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NOTE_ID_MATIERE+" INTEGER, "+
                NOTE_VALUE+" REAL, "+
                "FOREIGN KEY(id_mat) REFERENCES Matiere(id) )";
        // create books table
        db.execSQL(CREATE_MATIERE_TABLE);
        db.execSQL(CREATE_NOTE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS Matiere");
        db.execSQL("DROP TABLE IF EXISTS Note");
        // create fresh books table
        this.onCreate(db);
    }


}
