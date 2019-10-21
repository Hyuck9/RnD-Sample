package com.nexmore.rnd;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nexmore.rnd.utils.Event;
import com.nexmore.rnd.widget.BottomSheetBehavior;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SampleMapViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private MutableLiveData<String> lonLat;

//    private MediatorLiveData<Event<Integer>> bottomSheetStateEvent;

    private boolean hasLoadedFeatures = true;
    private String requestedFeatureId = null;

    private double latitude =0;
    private double longitude =0;

    public SampleMapViewModel() {
        lonLat = new MutableLiveData<>();
        lonLat.setValue("This is SampleMapActivity");
    }

    @SuppressLint("DefaultLocale")
    public void setLatLon(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        lonLat.setValue("Lat : " + latitude + ", Lon : " + longitude);
//        lonLat.setValue(String.format("Lat : %.6f, Lon : %.6f", latitude, longitude));
        Log.d("test", "latitude : " + latitude + ", longitude : " + longitude);
    }

//    public void requestHighlightFeature(String featureId) {
//        if (hasLoadedFeatures) {
//            highlightFeature(featureId);
//        } else {
//            // save and re-evaluate when the map features are loaded
//            requestedFeatureId = featureId;
//        }
//    }
//
//    private void highlightFeature(String featureId) {
//        bottomSheetStateEvent.setValue(new Event(BottomSheetBehavior.STATE_COLLAPSED));
//    }
//
//    public void onMapDestroyed() {
//        hasLoadedFeatures = false;
//    }
//
//    public void dismissFeatureDetails() {
//        bottomSheetStateEvent.setValue(new Event(BottomSheetBehavior.STATE_HIDDEN));
//    }
//
//    @BindingAdapter("bottomSheetState")
//    public static void bottomSheetState(View view, Event<Integer> event) {
//        if (event == null) return;
//        int state = event.getContentIfNotHandled();
//        BottomSheetBehavior.from(view).setState(state);
//    }

}
