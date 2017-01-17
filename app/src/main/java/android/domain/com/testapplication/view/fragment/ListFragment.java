package android.domain.com.testapplication.view.fragment;

import android.app.Fragment;
import android.domain.com.testapplication.R;
import android.domain.com.testapplication.model.MapSearchModel;
import android.domain.com.testapplication.presenter.OnItemSelectionChangeListener;
import android.domain.com.testapplication.presenter.ServiceClient;
import android.domain.com.testapplication.view.MainActivity;
import android.domain.com.testapplication.view.adapter.RecyclerViewClickListener;
import android.domain.com.testapplication.view.adapter.SearchListAdapter;
import android.domain.com.testapplication.view.adapter.SimpleDividerItemDecoration;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mala on 17/1/17.
 */
public class ListFragment extends Fragment {

    protected RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        setRecyclerViewLayoutManager();

        loadAdapter();
        return rootView;
    }

    /**
     * Set RecyclerView's LayoutManager
     *
     */
    public void setRecyclerViewLayoutManager() {
        int scrollPosition = 0;
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
        // If a layout manager has already been set, get current scroll position.
        if (mRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(scrollPosition);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(20);
        mRecyclerView.setDrawingCacheEnabled(true);
        mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
    }

    /**
     * Load Json data through retrofit and populate adapter
     */
    private void loadAdapter() {
        ((MainActivity)getActivity()).showProgressDialog(true);

        ServiceClient.getInstance().getSearchListings(new Callback<MapSearchModel>() {
            @Override
            public void onResponse(Call<MapSearchModel> call, Response<MapSearchModel> response) {
                if (response.body() != null) {
                    SearchListAdapter adapter = new SearchListAdapter(getActivity(), response.body().getListingResults().getListings(), new RecyclerViewClickListener() {
                        @Override
                        public void onRecyclerViewItemClick(View v, int position) {
                            SearchListAdapter searchListAdapter = (SearchListAdapter) mRecyclerView.getAdapter();
                            MapSearchModel.Listing item = searchListAdapter.getItem(position);
                            OnItemSelectionChangeListener listener = (OnItemSelectionChangeListener) getActivity();
                            listener.OnSelectionChanged(item.getDescription());
                        }
                    });
                    mRecyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(getActivity(), getString(R.string.label_error), Toast.LENGTH_SHORT).show();
                }
                ((MainActivity)getActivity()).hideProgressDialog();
            }

            @Override
            public void onFailure(Call<MapSearchModel> call, Throwable t) {
                Toast.makeText(getActivity(), getString(R.string.label_error), Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).hideProgressDialog();
            }
        });
    }
}
