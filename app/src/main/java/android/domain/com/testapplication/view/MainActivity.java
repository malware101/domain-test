package android.domain.com.testapplication.view;

import android.app.FragmentTransaction;
import android.domain.com.testapplication.R;
import android.domain.com.testapplication.presenter.OnItemSelectionChangeListener;
import android.domain.com.testapplication.utils.FragmentTransition;
import android.domain.com.testapplication.utils.CustomProgressDialog;
import android.domain.com.testapplication.view.fragment.DescriptionFragment;
import android.domain.com.testapplication.view.fragment.ListFragment;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by mala on 17/1/17.
 */
public class MainActivity extends AppCompatActivity implements OnItemSelectionChangeListener {

    private CustomProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

       // Set fragments for multipane
        if (findViewById(R.id.fragment_container) != null){
            if (savedInstanceState != null){
                return;
            }

            // Create an Instance of Fragment
            ListFragment listFragment = new ListFragment();
            listFragment.setArguments(getIntent().getExtras());
            getFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, listFragment)
                    .commit();
        }
    }


    @Override
    public void OnSelectionChanged(String description) {
        DescriptionFragment descriptionFragment = (DescriptionFragment) getFragmentManager()
                .findFragmentById(R.id.description_fragment);

        if (descriptionFragment != null){
            descriptionFragment.setDescription(description);
        } else {
            DescriptionFragment newDesriptionFragment = new DescriptionFragment();
            Bundle args = new Bundle();

            args.putString(DescriptionFragment.KEY,description);
            newDesriptionFragment.setArguments(args);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

            // Add custom animations for fragment transition
            FragmentTransition transition = FragmentTransition.LEFT_TO_RIGHT;
            int[] transitions = transition.getTranstion();
            fragmentTransaction.setCustomAnimations(transitions[0], transitions[1], transitions[2],
                    transitions[3]);

            fragmentTransaction.replace(R.id.fragment_container,newDesriptionFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    /**
     * Show progress dialog.
     *
     * @param canCancel can cancel this progress dialog
     */
    public void showProgressDialog(final boolean canCancel)
    {
        if(!isFinishing()) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (mProgressDialog == null) {
                            mProgressDialog = new CustomProgressDialog(MainActivity.this,
                                    R.drawable.indeterminate_progress);
                            mProgressDialog.setVolumeControlStream(AudioManager.STREAM_MUSIC);
                            mProgressDialog.setCancelable(canCancel);
                            mProgressDialog.show();
                        }
                    } catch (Exception e) {

                    }
                }
            });
        }
    }

    /**
     * Hide progress dialog.
     */
    public void hideProgressDialog()
    {
        if(!isFinishing()) {

            if (mProgressDialog != null) {
                try {
                    if (mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                    }
                } catch (Exception e) {

                }

                mProgressDialog = null;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


