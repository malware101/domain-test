package android.domain.com.testapplication.view.adapter;

import android.domain.com.testapplication.R;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by mala on 17/1/17.
 */
public class SearchListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ImageView imgProperty;
    public ImageView imgProperty2;
    public TextView tvDisplayPrice;
    public TextView tvBedBathCar;
    public TextView tvDisplayableAddress;
    public ImageView imgAgencyLogo;
    public ImageView imgFavourite;

    public RecyclerViewClickListener onClickListener;

    public SearchListViewHolder(View itemView, RecyclerViewClickListener onClickListener) {
        super(itemView);

        imgProperty = (ImageView) itemView.findViewById(R.id.imgProperty);
        imgProperty2 = (ImageView) itemView.findViewById(R.id.imgProperty2);
        tvDisplayPrice = (TextView) itemView.findViewById(R.id.tvDisplayPrice);
        tvBedBathCar = (TextView) itemView.findViewById(R.id.tvBedBathCar);
        tvDisplayableAddress = (TextView) itemView.findViewById(R.id.tvDisplayableAddress);
        imgAgencyLogo = (ImageView) itemView.findViewById(R.id.imgAgencyLogo);
        imgFavourite = (ImageView) itemView.findViewById(R.id.imgFavourite);

        this.onClickListener = onClickListener;

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onClickListener.onRecyclerViewItemClick(v, this.getLayoutPosition());
    }
}
