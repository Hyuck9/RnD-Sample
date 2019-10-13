package com.nexmore.rnd.ui.map;

import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nexmore.rnd.R;
import com.nexmore.rnd.transitions.FabTransform;
import com.nexmore.rnd.ui.chat.CreateChatActivity;

import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.util.Objects;

public class MapFragment extends Fragment implements MapView.CurrentLocationEventListener, MapView.MapViewEventListener {

    private MapViewModel mViewModel;

    private MapView mMapView;

//    private LocationManager mLocationManager;
    private double mLatitude = 37.56640625;
    private double mLongitude = 126.97787475585938;

    private FloatingActionButton mFabMain;
    private FloatingActionButton mFabChat;
    private FloatingActionButton mFabSNS;
    private boolean isFabOpen = false;
    private Animation fabOpen, fabClose, fabRClockwise, fabRAntiClockWise;

    public static final int CREATE_CHAT_REQUEST_CODE = 107;

//    public static MapFragment newInstance() {
//        return new MapFragment();
//    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_map, container, false);
        mMapView = root.findViewById(R.id.map_view);
        mFabMain = root.findViewById(R.id.fab_main);
        mFabChat = root.findViewById(R.id.fab_create_chat);
        mFabSNS = root.findViewById(R.id.fab_sns);

        initalizeAnimation();
        setupFab();

        CheckBox checkbox = root.findViewById(R.id.checkbox_my_location);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if ( isChecked ) {
                    mMapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithHeading);
                } else {
                    mMapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);
                    mMapView.setShowCurrentLocationMarker(false);
                }
            }
        });

        checkbox.setChecked(true);


        mMapView.setCurrentLocationEventListener(this);
        return root;
    }


    private void setupFab() {
        mFabMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( isFabOpen ) {
                    fabCloseAction();
                } else {
                    fabOpenAction();
                }
            }
        });

        mFabChat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CreateChatActivity.class);
                int color = ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.colorFabGreen);
                FabTransform.addExtras(intent, color, R.drawable.ic_forum_white);
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(Objects.requireNonNull(getActivity()),
                                mFabChat,
                                getString(R.string.transition_name_create_chat));
                startActivityForResult(intent, CREATE_CHAT_REQUEST_CODE, optionsCompat.toBundle());
            }
        });
    }

    private void fabOpenAction() {
        mFabMain.startAnimation(fabRClockwise);
        mFabChat.startAnimation(fabOpen);
        mFabSNS.startAnimation(fabOpen);
        mFabChat.setClickable(true);
        mFabSNS.setClickable(true);
        isFabOpen = true;
    }

    private void fabCloseAction() {
        mFabMain.startAnimation(fabRAntiClockWise);
        mFabChat.startAnimation(fabClose);
        mFabSNS.startAnimation(fabClose);
        mFabChat.setClickable(false);
        mFabSNS.setClickable(false);
        isFabOpen = false;
    }

    private void initalizeAnimation() {
        fabOpen = AnimationUtils.loadAnimation(getContext(), R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(getContext(), R.anim.fab_close);
        fabRClockwise = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_clockwise);
        fabRAntiClockWise = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_anticlockwise);
    }




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MapViewModel.class);
        // TODO: Use the ViewModel
    }



    @Override
    public void onCurrentLocationUpdate(MapView mapView, MapPoint mapPoint, float accuracyInMeters) {
        if ( mapView.getCurrentLocationTrackingMode() == MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading ) {
            mMapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeadingWithoutMapMoving);
        }
        MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();
        mLatitude = mapPointGeo.latitude;
        mLongitude = mapPointGeo.longitude;
        Log.i("MapFragment", String.format("MapView onCurrentLocationUpdate (%f,%f) accuracy (%f)", mapPointGeo.latitude, mapPointGeo.longitude, accuracyInMeters));
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(mLatitude, mLongitude), true);
//        mMapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(mLatitude, mLongitude), 6, true);
    }
    @Override
    public void onCurrentLocationDeviceHeadingUpdate(MapView mapView, float v) {}
    @Override
    public void onCurrentLocationUpdateFailed(MapView mapView) {}
    @Override
    public void onCurrentLocationUpdateCancelled(MapView mapView) {}





    @Override
    public void onMapViewInitialized(MapView mapView) {
        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
//        MapPoint.GeoCoordinate mapPointGeo = mapView.getMapCenterPoint().getMapPointGeoCoord();
//        mLatitude = mapPointGeo.latitude;
//        mLongitude = mapPointGeo.longitude;
        Log.d("test", "onCreate - 현재 위치 : "+mLatitude+", "+mLongitude);
        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(mLatitude, mLongitude), 6, true);

    }
    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {
    }
    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {
    }
    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {
    }
    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {
    }
    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {
    }
    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {
    }
    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {
    }
    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {
    }


    @Override
    public void onResume() {
        super.onResume();
//        requestMyLocation();
    }

//    private void requestMyLocation() {
//        long minTime = 10000;   // 위치 갱신 최소 시간 (milliSecond)
//        float minDistance = 0;  // 위치 갱신 최소 거리 (meter)
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
//            if (ContextCompat.checkSelfPermission(Objects.requireNonNull(getContext()), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(Objects.requireNonNull(getContext()), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                return;
//            }
//        }
//        mLocationManager.requestLocationUpdates(
//                LocationManager.GPS_PROVIDER,
//                minTime,
//                minDistance,
//                mLocationListener
//        );
//        mLocationManager.requestLocationUpdates(
//                LocationManager.NETWORK_PROVIDER,
//                minTime,
//                minDistance,
//                mLocationListener
//        );
//    }
//
//    private LocationListener mLocationListener = new LocationListener() {
//        @Override
//        public void onLocationChanged(Location location) {
//            double longitude = location.getLongitude(); //경도
//            double latitude = location.getLatitude();   //위도
//            double altitude = location.getAltitude();   //고도
//            float accuracy = location.getAccuracy();    //정확도
//            String provider = location.getProvider();   //위치제공자
//
//            String test = "위치정보 : " + provider + "\n위도 : " + longitude + "\n경도 : " + latitude + "\n고도 : " + altitude + "\n정확도 : "  + accuracy;
//            Log.d("MyLocationService", test);
//
//        }
//
//        @Override
//        public void onStatusChanged(String s, int i, Bundle bundle) {
//        }
//        @Override
//        public void onProviderEnabled(String s) {
//        }
//        @Override
//        public void onProviderDisabled(String s) {
//        }
//    };

}
