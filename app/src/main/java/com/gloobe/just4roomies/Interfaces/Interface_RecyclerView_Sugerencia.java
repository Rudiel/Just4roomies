package com.gloobe.just4roomies.Interfaces;

import android.view.View;

import com.gloobe.just4roomies.Adaptadores.Adapter_PlacesAutoComplete;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

/**
 * Created by rudielavilaperaza on 06/10/16.
 */
public interface Interface_RecyclerView_Sugerencia {

    void clickSugerencia(View view, int position, Adapter_PlacesAutoComplete.PlaceAutocomplete placeAutocomplete);
}
