package android.domain.com.testapplication.utils;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by mala on 17/1/17.
 */
public class AppUtil {

    public static void setImageViewAsync(ImageView imageView, String imageUrl,Context context) {
        Picasso.with(context)
                .load(imageUrl)
                .fit()
                .into(imageView);
    }
}
