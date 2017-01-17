package android.domain.com.testapplication.view.fragment;

import android.app.Fragment;
import android.domain.com.testapplication.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by mala on 17/1/17.
 */
public class DescriptionFragment extends Fragment {

    final public static String KEY = "description";
    private TextView mTvDescription;
    private String mDescription;

    public DescriptionFragment() {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // If the Activity is recreated, the savedInstanceStare Bundle isn't empty
        // we restore the previous version name selection set by the Bundle.
        if (savedInstanceState != null) {
            mDescription = savedInstanceState.getString(KEY);
        }


        View view = inflater.inflate(R.layout.fragment_description, container, false);

        mTvDescription = (TextView) view.findViewById(R.id.tvDescription);
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();

        Bundle args = getArguments();
        if (args != null) {
            // Set description based on argument passed in
            setDescription(args.getString(KEY));
        } else if (mDescription != null) {
            // Set description based on savedInstanceState defined during onCreateView()
            setDescription(mDescription);
        }
    }

    public void setDescription(String description) {
        mTvDescription.setText(description);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current description selection in case we need to recreate the fragment
        outState.putString(KEY, mDescription);
    }
}
