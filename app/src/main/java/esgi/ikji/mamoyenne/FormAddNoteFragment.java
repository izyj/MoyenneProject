package esgi.ikji.mamoyenne;

        import android.app.Fragment;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;

/**
 * Created by Navi on 25/06/2015.
 */
public class FormAddNoteFragment extends Fragment {


    public FormAddNoteFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_formaddnotefragment, container, false);
        // Inflate the layout for this fragment
        return v;
    }

}