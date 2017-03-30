package com.just4roomies.j4r.Adaptadores;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.just4roomies.j4r.Interfaces.Interface_RecyclerView_Sugerencia;
import com.just4roomies.j4r.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.AutocompletePredictionBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * Created by rudielavilaperaza on 04/10/16.
 */
public class Adapter_PlacesAutoComplete extends RecyclerView.Adapter<Adapter_PlacesAutoComplete.PredictionHolder> implements Filterable {

    public static ArrayList<PlaceAutocomplete> mResultList;
    private GoogleApiClient mGoogleApiClient;
    private LatLngBounds mBounds;
    private AutocompleteFilter mPlaceFilter;

    private Context mContext;
    private int layout;
    private Interface_RecyclerView_Sugerencia listener;
    private Typeface typeface;

    private final int VIEW_ITEM = 0;
    private final int VIEW_FOOTER = 1;

    public Adapter_PlacesAutoComplete(Context context, int resource, GoogleApiClient googleApiClient,
                                      LatLngBounds bounds, AutocompleteFilter filter, Interface_RecyclerView_Sugerencia listener, Typeface typeface) {
        mContext = context;
        layout = resource;
        mGoogleApiClient = googleApiClient;
        mBounds = bounds;
        mPlaceFilter = filter;
        this.listener = listener;
        this.typeface = typeface;
    }

    /**
     * Sets the bounds for all subsequent queries.
     */
    public void setBounds(LatLngBounds bounds) {
        mBounds = bounds;
    }

    /**
     * Returns the filter for the current set of autocomplete results.
     */
    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                // Skip the autocomplete query if no constraints are given.
                if (constraint != null) {
                    // Query the autocomplete API for the (constraint) search string.
                    mResultList = getAutocomplete(constraint);
                    if (mResultList != null) {
                        // The API successfully returned results.
                        results.values = mResultList;
                        results.count = mResultList.size();
                    }
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    // The API returned at least one result, update the data.
                    notifyDataSetChanged();
                } else {
                    // The API did not return any results, invalidate the data set.
                    //notifyDataSetInvalidated();
                }
            }
        };
        return filter;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPosition(position))
            return VIEW_ITEM;
        return VIEW_FOOTER;
    }

    private boolean isPosition(int position) {
        return position != getItemCount() - 1;
    }

    private ArrayList<PlaceAutocomplete> getAutocomplete(CharSequence constraint) {
        if (mGoogleApiClient.isConnected()) {
            // Submit the query to the autocomplete API and retrieve a PendingResult that will
            // contain the results when the query completes.
            PendingResult<AutocompletePredictionBuffer> results =
                    Places.GeoDataApi
                            .getAutocompletePredictions(mGoogleApiClient, constraint.toString(),
                                    mBounds, mPlaceFilter);

            // This method should have been called off the main UI thread. Block and wait for at most 60s
            // for a result from the API.
            AutocompletePredictionBuffer autocompletePredictions = results
                    .await(60, TimeUnit.SECONDS);

            // Confirm that the query completed successfully, otherwise return null
            final Status status = autocompletePredictions.getStatus();
            if (!status.isSuccess()) {
                autocompletePredictions.release();
                return null;
            }
            // Copy the results into our own data structure, because we can't hold onto the buffer.
            // AutocompletePrediction objects encapsulate the API response (place ID and description).

            Iterator<AutocompletePrediction> iterator = autocompletePredictions.iterator();
            ArrayList resultList = new ArrayList<>(autocompletePredictions.getCount());
            while (iterator.hasNext()) {
                AutocompletePrediction prediction = iterator.next();
                // Get the details of this prediction and copy it into a new PlaceAutocomplete object.
                resultList.add(new PlaceAutocomplete(prediction.getPlaceId(),
                        prediction.getFullText(null)));
            }

            // Release the buffer now that all data has been copied.
            autocompletePredictions.release();

            return resultList;
        }
        return null;
    }


    @Override
    public PredictionHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (i == VIEW_ITEM) {
            View convertView = layoutInflater.inflate(layout, viewGroup, false);
            PredictionHolder mPredictionHolder = new PredictionHolder(convertView);
            return mPredictionHolder;
        } else {
            View convertView = layoutInflater.inflate(R.layout.layout_item_sugerencia_google, viewGroup, false);
            PredictionHolder mPredictionHolder = new PredictionHolder(convertView);
            return mPredictionHolder;
        }

    }

    @Override
    public void onBindViewHolder(PredictionHolder mPredictionHolder, final int i) {

        switch (getItemViewType(i)) {
            case VIEW_ITEM:
                mPredictionHolder.tvSugerencia.setText(mResultList.get(i).description);
                mPredictionHolder.tvSugerencia.setTypeface(typeface);

                mPredictionHolder.mRow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            listener.clickSugerencia(view, i, mResultList.get(i));
                        } catch (Exception e) {
                        }
                    }
                });
                break;
            case VIEW_FOOTER:
                break;
        }

    }

    @Override
    public int getItemCount() {
        if (mResultList == null)
            return 0;

        if (mResultList.size() == 0)
            return 1;

        return mResultList.size() + 1;
    }

    public static PlaceAutocomplete getItem(int position) {
        return mResultList.get(position);
    }

    public class PredictionHolder extends RecyclerView.ViewHolder {
        private TextView tvSugerencia;
        private RelativeLayout mRow;

        public PredictionHolder(View itemView) {

            super(itemView);
            // mPrediction = (TextView) itemView.findViewById(R.id.address);
            mRow = (RelativeLayout) itemView.findViewById(R.id.predictedRow);

            tvSugerencia = (TextView) itemView.findViewById(R.id.tvSugerencia);

        }

    }

    /**
     * Holder for Places Geo Data Autocomplete API results.
     */
    public class PlaceAutocomplete {

        public CharSequence placeId;
        public CharSequence description;

        PlaceAutocomplete(CharSequence placeId, CharSequence description) {
            this.placeId = placeId;
            this.description = description;
        }

        @Override
        public String toString() {
            return description.toString();
        }
    }


}