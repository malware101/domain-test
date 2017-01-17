package android.domain.com.testapplication.view.adapter;

import android.content.Context;
import android.domain.com.testapplication.R;
import android.domain.com.testapplication.model.MapSearchModel;
import android.domain.com.testapplication.utils.AppUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by mala on 17/1/17.
 */
public class SearchListAdapter extends RecyclerView.Adapter<SearchListViewHolder> {

    private List<MapSearchModel.Listing> mItems;
    private static RecyclerViewClickListener mOnClickListener;
    private Context mContext;

    public SearchListAdapter(Context context, List<MapSearchModel.Listing> items, RecyclerViewClickListener onClickListener) {
        this.mItems = items;
        this.mOnClickListener = onClickListener;
        this.mContext = context;
    }

    public MapSearchModel.Listing getItem(int position) {
        try {
            return mItems.get(position);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public int getItemViewType(int position) {
        MapSearchModel.Listing item = this.getItem(position);
        return item.getIsElite();
    }

    @Override
    public SearchListViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        int layoutId;
        if (viewType == 0) {
            layoutId = R.layout.layout_standard;
        } else {
            layoutId = R.layout.layout_premium;
        }

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(layoutId, viewGroup, false);

        SearchListViewHolder viewHolder = new SearchListViewHolder(itemView, mOnClickListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final SearchListViewHolder viewHolder, int position) {
        final MapSearchModel.Listing item = mItems.get(position);

        // Set text values
        viewHolder.tvDisplayPrice.setText(item.getDisplayPrice());
        viewHolder.tvDisplayableAddress.setText(item.getDisplayableAddress());

        String strValue = String.format(mContext.getString(R.string.label_bed_bath_car), String.valueOf(item.getBedrooms()), String.valueOf(item.getBathrooms()), String.valueOf(item.getCarspaces()));
        viewHolder.tvBedBathCar.setText(strValue);

        // Load image from Picasso
        AppUtil.setImageViewAsync(viewHolder.imgProperty, item.getRetinaDisplayThumbUrl(), mContext);
        if (viewHolder.imgProperty2 != null) {
            AppUtil.setImageViewAsync(viewHolder.imgProperty2, item.getSecondRetinaDisplayThumbUrl(), mContext);
        }
        AppUtil.setImageViewAsync(viewHolder.imgAgencyLogo, item.getAgencyLogoUrl(), mContext);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}