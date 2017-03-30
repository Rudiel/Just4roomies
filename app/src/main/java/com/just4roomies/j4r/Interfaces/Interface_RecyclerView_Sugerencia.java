package com.just4roomies.j4r.Interfaces;

import android.view.View;

import com.just4roomies.j4r.Adaptadores.Adapter_PlacesAutoComplete;

/**
 * Created by rudielavilaperaza on 06/10/16.
 */
public interface Interface_RecyclerView_Sugerencia {

    void clickSugerencia(View view, int position, Adapter_PlacesAutoComplete.PlaceAutocomplete placeAutocomplete);
}
