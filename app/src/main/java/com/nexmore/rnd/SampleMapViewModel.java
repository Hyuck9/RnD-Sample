package com.nexmore.rnd;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SampleMapViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private MutableLiveData<String> lonLat;

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

}
