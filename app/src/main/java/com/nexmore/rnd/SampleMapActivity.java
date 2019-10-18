package com.nexmore.rnd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.nexmore.rnd.databinding.ActivitySampleMapBinding;

import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapReverseGeoCoder;
import net.daum.mf.map.api.MapView;

public class SampleMapActivity extends AppCompatActivity implements MapView.CurrentLocationEventListener, MapReverseGeoCoder.ReverseGeoCodingResultListener {


    ActivitySampleMapBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.id.sample_map_view);

        binding.sampleMapView.setCurrentLocationEventListener(this);
        binding.sampleMapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithHeading);
    }


    /* MapView.CurrentLocationEventListener Start */
    @Override
    public void onCurrentLocationUpdate(MapView mapView, MapPoint mapPoint, float v) {
    }
    @Override
    public void onCurrentLocationDeviceHeadingUpdate(MapView mapView, float v) {
    }
    @Override
    public void onCurrentLocationUpdateFailed(MapView mapView) {
    }
    @Override
    public void onCurrentLocationUpdateCancelled(MapView mapView) {
    }
    /* MapView.CurrentLocationEventListener End */

    /* MapReverseGeoCoder.ReverseGeoCodingResultListener Start */
    @Override
    public void onReverseGeoCoderFoundAddress(MapReverseGeoCoder mapReverseGeoCoder, String s) {
    }
    @Override
    public void onReverseGeoCoderFailedToFindAddress(MapReverseGeoCoder mapReverseGeoCoder) {
    }
    /* MapReverseGeoCoder.ReverseGeoCodingResultListener End */

}
