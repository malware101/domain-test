package android.domain.com.testapplication.utils;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ProgressBar;

/**
 * Created by mala on 17/1/17.
 */
public class CustomProgressDialog extends Dialog {

    private Activity activity;
    private int progressResourceId = 0;

    public static CustomProgressDialog show(Activity activity,
                                            int progressResourceId) {
        return show(activity, progressResourceId, false, null);
    }

    /**
     * Show.
     *
     * @param activity   the activity
     * @param cancelable the cancelable
     * @return the progress dialog
     */
    public static CustomProgressDialog show(Activity activity,
                                            int progressResourceId, boolean cancelable) {
        return show(activity, progressResourceId, cancelable, null);
    }

    /**
     * Show.
     *
     * @param activity           the activity
     * @param progressResourceId the progress resource id
     * @param cancelable         the cancelable
     * @param cancelListener     the cancel listener
     * @return the progress dialog
     */
    public static CustomProgressDialog show(Activity activity,
                                            int progressResourceId, boolean cancelable,
                                            OnCancelListener cancelListener) {
        CustomProgressDialog dialog = new CustomProgressDialog(
                activity, progressResourceId);

        dialog.setCancelable(cancelable);
        dialog.setOnCancelListener(cancelListener);

		/* The next line will add the ProgressBar to the dialog. */
        dialog.addContentView(new ProgressBar(activity), new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        dialog.show();

        return dialog;
    }

    /*
     * (non-Javadoc)
     *
     * @see android.app.Dialog#show()
     */
    @Override
    public void show() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        ProgressBar progressBar = new ProgressBar(activity, null, android.R.attr.progressBarStyleLarge);

        int dim = 150;

        addContentView(progressBar, new LayoutParams(dim, dim));

        super.show();

        getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
    }

    /**
     * Instantiates a new progress dialog.
     *
     * @param activity the activity
     */
    public CustomProgressDialog(Activity activity, int progressResourceId) {
        super(activity);

        this.activity = activity;
        this.progressResourceId = progressResourceId;
    }
}
