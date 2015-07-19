package esgi.ikji.mamoyenne;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;
import java.lang.reflect.Method;
import esgi.ikji.mamoyenne.DAO.MySQLiteHelper;

public class MainActivity extends ActionBarActivity {
	FragmentManager manager;
	FragmentTransaction transaction;
    public Fragment current;

    static final String STATE_TRANSACTION = "transaction";
	// INITIALISE DATABASE
	final MySQLiteHelper db = new MySQLiteHelper(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            // Restore value of members from saved state
            current = getSupportFragmentManager().getFragment(savedInstanceState, "mContent");
            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction();
            transaction.replace(R.id.container, current);
            transaction.commit();
        } else {

            // INSTANCIATE FRAGMENT MANAGER
            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction();
            current = new PresentationFragment();
            transaction.replace(R.id.container, current);
            transaction.commit();
        }

            // CONFIGURE ACTION BAR
            getSupportActionBar().setIcon(R.drawable.ic_launcher);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        /* RIGHT MENU --- BUTTONS CONTROLS */
        /* Traitement Ajout de matiere */

            Button bt_matiere = (Button) findViewById(R.id.btNewMatiere);
            bt_matiere.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    manager = getSupportFragmentManager();
                    transaction = manager.beginTransaction();
                    current = new FormAddMatiereFragment();
                    transaction.replace(R.id.container, current);
                    transaction.commit();
                     /* Ajout en Base de donnees */
                    CharSequence str = "Add new matiere";
                    Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
                }
            });

            /**
             * Traitement Ajout de note
             */
            Button bt_note = (Button) findViewById(R.id.btNewNote);
            bt_note.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    manager = getSupportFragmentManager();
                    transaction = manager.beginTransaction();
                    current = new FormAddNoteFragment();
                    transaction.replace(R.id.container, current);
                    transaction.addToBackStack("nouvellenote");
                    transaction.commit();

                    /**
                     * Ajout en Base de donnees
                     */
                    CharSequence str = "Add new note";
                    Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
                }
            });

            /**
             * Traitement Suppression donnees
             */
            Button bt_del = (Button) findViewById(R.id.btDeleteAll);
            bt_del.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {

                    getApplicationContext().deleteDatabase(MySQLiteHelper.DATABASE_NAME);
                    /** Suppression en Base de donnees */
                    CharSequence str = "Delete all data";
                    ArrayAdapterMatiere.moygeneCoef = 0;
                    ArrayAdapterMatiere.moygeneValue = 0.00;
                    Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
                }
            });




	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_main, menu);
		return true;
	}

	/* ACTION BAR ------ CONTROLES DES BOUTONS */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
            case R.id.logo:
                manager = getSupportFragmentManager();
                transaction = manager.beginTransaction();
                current = new PresentationFragment();
                transaction.replace(R.id.container, current);
                transaction.commit();
                break;
			case R.id.consult:
                manager = getSupportFragmentManager();
				transaction = manager.beginTransaction();
                current = new FormConsultFragment();
				transaction.replace(R.id.container,current);
				transaction.commit();
				Toast.makeText(this, "Consultation", Toast.LENGTH_SHORT)
						.show();
				break;
			case R.id.bymatiere:
				manager = getSupportFragmentManager();
				transaction = manager.beginTransaction();
                current = new FormConsultByMatiereFragment();
                transaction.replace(R.id.container,current);
				transaction.commit();
				Toast.makeText(this, "Consultation par matiere", Toast.LENGTH_SHORT)
						.show();
				break;
			case R.id.bynote:
				manager = getSupportFragmentManager();
				transaction = manager.beginTransaction();
                current = new FormConsultByMoyenneFragment();
                transaction.replace(R.id.container,current);
				transaction.commit();
				Toast.makeText(this, "Consultation par moyenne", Toast.LENGTH_SHORT)
						.show();
				break;
			default:
				break;
		}

		return true;

	}
	@Override
	public boolean onMenuOpened(int featureId, android.view.Menu menu) {
		if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
			if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
				try {
					Method m = menu.getClass().getDeclaredMethod(
							"setOptionalIconsVisible", Boolean.TYPE);
					m.setAccessible(true);
					m.invoke(menu, true);
				} catch (NoSuchMethodException e) {

				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
		return super.onMenuOpened(featureId, menu);
	}
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
        //Save the fragment's instance
        getSupportFragmentManager().putFragment(savedInstanceState, "mContent", current);

    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //current = getSupportFragmentManager().getFragment(savedInstanceState,"mContent");
    }

    public void setCurrent(Fragment f){

            this.current = f;

    }
}
