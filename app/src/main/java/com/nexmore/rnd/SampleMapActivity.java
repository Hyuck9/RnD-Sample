package com.nexmore.rnd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.nexmore.rnd.data.model.MarkerInfo;
import com.nexmore.rnd.databinding.ActivitySampleMapBinding;
import com.nexmore.rnd.widget.BottomSheetBehavior;

import net.daum.mf.map.api.MapCircle;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapReverseGeoCoder;
import net.daum.mf.map.api.MapView;

import org.jetbrains.annotations.NotNull;

import static com.nexmore.rnd.utils.UiUtilsKt.slideOffsetToAlpha;

public class SampleMapActivity extends AppCompatActivity implements MapView.CurrentLocationEventListener, MapReverseGeoCoder.ReverseGeoCodingResultListener, MapView.MapViewEventListener, MapView.POIItemEventListener {

    ActivitySampleMapBinding binding;
    SampleMapViewModel viewModel;
    BottomSheetBehavior bottomSheetBehavior;

    @SuppressWarnings("FieldCanBeLocal") private static float ALPHA_TRANSITION_START = 0.1f;
    @SuppressWarnings("FieldCanBeLocal") private static float ALPHA_TRANSITION_END = 0.5f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MapView.setMapTilePersistentCacheEnabled(true); // 지도 타일 이미지 캐시 기능 활성화
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sample_map);

        viewModel = ViewModelProviders.of(this).get(SampleMapViewModel.class);
        binding.setViewModel(viewModel);
        viewModel.getLonLat().observe(this, lonlat -> binding.textLonLat.setText(lonlat));

        initMap();
        initBottomSheet();
    }

    private void initMap() {
//        binding.sMap.setMapType(MapView.MapType.Hybrid);
        binding.sMap.setMapType(MapView.MapType.Standard);
        binding.sMap.setMapViewEventListener(this);
        binding.sMap.setPOIItemEventListener(this);
        initEvent1();
        initEvent2();
        initEvent3();
//        binding.sMap.setCurrentLocationEventListener(this);
//        binding.sMap.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithHeading);
    }


    private void initEvent1() {
        MapPOIItem customMarker = new MapPOIItem();
        customMarker.setItemName("연암산");
        customMarker.setTag(1);
        customMarker.setMapPoint(MapPoint.mapPointWithGeoCoord(35.89962387, 128.60006713867188));
        customMarker.setMarkerType(MapPOIItem.MarkerType.CustomImage); // 마커타입을 커스텀 마커로 지정.
        customMarker.setCustomImageResourceId(R.drawable.map_marker_charging); // 마커 이미지.
        customMarker.setCustomImageAutoscale(false); // hdpi, xhdpi 등 안드로이드 플랫폼의 스케일을 사용할 경우 지도 라이브러리의 스케일 기능을 꺼줌.
        customMarker.setCustomImageAnchor(0.5f, 0.5f); // 마커 이미지중 기준이 되는 위치(앵커포인트) 지정 - 마커 이미지 좌측 상단 기준 x(0.0f ~ 1.0f), y(0.0f ~ 1.0f) 값.
        MapCircle circle = new MapCircle(
                MapPoint.mapPointWithGeoCoord(35.89962387,128.60006713867188), // center
                2000, // radius
                Color.argb(128, 255, 0, 0), // strokeColor
                Color.argb(64, 0, 0, 255) // fillColor
        );
        circle.setTag(1);
        binding.sMap.addCircle(circle);
        binding.sMap.addPOIItem(customMarker);
        binding.sMap.addPOIItem(customMarker);
    }

    private void initEvent2() {
        MapPOIItem customMarker = new MapPOIItem();
        customMarker.setItemName("계성중학교");
        customMarker.setTag(2);
        customMarker.setMapPoint(MapPoint.mapPointWithGeoCoord(35.86679077,128.58074951171875));
        customMarker.setMarkerType(MapPOIItem.MarkerType.CustomImage); // 마커타입을 커스텀 마커로 지정.
        customMarker.setCustomImageResourceId(R.drawable.map_marker_water);
        customMarker.setCustomImageAutoscale(false); // hdpi, xhdpi 등 안드로이드 플랫폼의 스케일을 사용할 경우 지도 라이브러리의 스케일 기능을 꺼줌.
        customMarker.setCustomImageAnchor(0.5f, 0.5f); // 마커 이미지중 기준이 되는 위치(앵커포인트) 지정 - 마커 이미지 좌측 상단 기준 x(0.0f ~ 1.0f), y(0.0f ~ 1.0f) 값.
        MapCircle circle = new MapCircle(
                MapPoint.mapPointWithGeoCoord(35.86679077,128.58074951171875), // center
                2000, // radius
                Color.argb(128, 255, 0, 0), // strokeColor
                Color.argb(64, 0, 255, 0) // fillColor
        );
        circle.setTag(2);
        binding.sMap.addCircle(circle);
        binding.sMap.addPOIItem(customMarker);
    }

    private void initEvent3() {
        MapPOIItem customMarker = new MapPOIItem();
        customMarker.setItemName("e편한세상");
        customMarker.setTag(3);
        customMarker.setMapPoint(MapPoint.mapPointWithGeoCoord(35.86746597,128.6170196533203));
        customMarker.setMarkerType(MapPOIItem.MarkerType.CustomImage); // 마커타입을 커스텀 마커로 지정.
        customMarker.setCustomImageResourceId(R.drawable.map_marker_1);
        customMarker.setCustomImageAutoscale(false); // hdpi, xhdpi 등 안드로이드 플랫폼의 스케일을 사용할 경우 지도 라이브러리의 스케일 기능을 꺼줌.
        customMarker.setCustomImageAnchor(0.5f, 0.5f); // 마커 이미지중 기준이 되는 위치(앵커포인트) 지정 - 마커 이미지 좌측 상단 기준 x(0.0f ~ 1.0f), y(0.0f ~ 1.0f) 값.
        MapCircle circle = new MapCircle(
                MapPoint.mapPointWithGeoCoord(35.86746597,128.6170196533203), // center
                2000, // radius
                Color.argb(128, 255, 0, 0), // strokeColor
                Color.argb(64, 255, 0, 0) // fillColor
        );
        circle.setTag(3);
        binding.sMap.addCircle(circle);
        binding.sMap.addPOIItem(customMarker);
    }






    private void initBottomSheet() {
        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet);
        bottomSheetBehavior.addBottomSheetCallback(bottomSheetCallback);
        binding.bottomSheet.post(() -> {
            int state = bottomSheetBehavior.getState();
            float slideOffset = -1f;

            if ( state == BottomSheetBehavior.STATE_EXPANDED ) {
                slideOffset = 1f;
            } else if ( state == BottomSheetBehavior.STATE_COLLAPSED ) {
                slideOffset = 0f;
            }
            bottomSheetCallback.onStateChanged(binding.bottomSheet, state);
            bottomSheetCallback.onSlide(binding.bottomSheet, slideOffset);
        });

        binding.clickable.setOnClickListener(v -> {
            if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            } else {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        binding.descriptionScrollview
                .setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) ->
                        binding.sheetHeaderShadow.setActivated(v.canScrollVertically(-1)));
//        binding.mapModeFab.setOnClickListener();
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

    }

    private void updateInfoSheet(MarkerInfo markerInfo) {
        // TODO: markerInfo로 이미지 및 텍스트 셋팅
    }

    private BottomSheetBehavior.BottomSheetCallback bottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override
        public void onStateChanged(@NotNull View bottomSheet, int newState) {
            float rotation = 180f;
            switch ( newState ) {
                case BottomSheetBehavior.STATE_EXPANDED:
                    rotation = 0f;
                    break;
                case BottomSheetBehavior.STATE_COLLAPSED:
                case BottomSheetBehavior.STATE_HIDDEN:
                    rotation = 180f;
                    break;
            }
            binding.expandIcon.animate().rotationX(rotation).start();

//            if (newState == BottomSheetBehavior.STATE_EXPANDED) {
//                viewModel.logViewedMarkerDetails();
//            }
        }

        @Override
        public void onSlide(@NotNull View bottomSheet, float slideOffset) {
            binding.descriptionScrollview.setAlpha(slideOffsetToAlpha(slideOffset, ALPHA_TRANSITION_START, ALPHA_TRANSITION_END));
            if (slideOffset > 0f) {
                binding.mapModeFab.hide();
            } else {
                binding.mapModeFab.show();
                // Translate FAB to make room for the peeking sheet.
                binding.mapModeFab.setTranslationY(bottomSheet.getTop() - 32 - binding.mapModeFab.getBottom());
            }
        }
    };


    /*
     * MapView.CurrentLocationEventListener Start
     * CurrentLocationEventListener interface를 구현하는 객체를 MapView 객체에 등록하여 현위치 트래킹 이벤트를 통보받을 수 있다.
     */
    @Override
    public void onCurrentLocationUpdate(MapView mapView, MapPoint mapPoint, float v) {
        Toast.makeText(this, "단말의 현위치 좌표값을 통보받을 수 있다.", Toast.LENGTH_SHORT).show();
        //
        // MapView.setCurrentLocationTrackingMode 메소드를 통해
        // 사용자 현위치 트래킹 기능이 켜진 경우(CurrentLocationTrackingMode.TrackingModeOnWithoutHeading, CurrentLocationTrackingMode.TrackingModeOnWithHeading)
        // 단말의 위치에 해당하는 지도 좌표와 위치 정확도가 주기적으로 delegate 객체에 통보된다.
    }
    @Override
    public void onCurrentLocationDeviceHeadingUpdate(MapView mapView, float v) {
        Toast.makeText(this, "단말의 방향(Heading) 각도값을 통보받을 수 있다.", Toast.LENGTH_SHORT).show();
        //
        // MapView.setCurrentLocationTrackingMode 메소드를 통해
        // 사용자 현위치 트래킹과 나침반 모드가 켜진 경우(CurrentLocationTrackingMode.TrackingModeOnWithHeading)
        // 단말의 방향 각도값이 주기적으로 delegate 객체에 통보된다.
    }
    @Override
    public void onCurrentLocationUpdateFailed(MapView mapView) {
        Toast.makeText(this, "현위치 갱신 작업에 실패한 경우 호출된다.", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onCurrentLocationUpdateCancelled(MapView mapView) {
        Toast.makeText(this, "현위치 트랙킹 기능이 사용자에 의해 취소된 경우 호출된다.", Toast.LENGTH_SHORT).show();
        // 처음 현위치를 찾는 동안에 현위치를 찾는 중이라는 Alert Dialog 인터페이스가 사용자에게 노출된다.
        // 첫 현위치를 찾기전에 사용자가 취소 버튼을 누른 경우 호출 된다.
    }
    /* MapView.CurrentLocationEventListener End */





    /*
     * MapReverseGeoCoder.ReverseGeoCodingResultListener Start
     * Reverse Geo-Coding 결과를 비동기적으로 통보받는 listener 객체가 구현해야하는 interface
     */
    @Override
    public void onReverseGeoCoderFoundAddress(MapReverseGeoCoder mapReverseGeoCoder, String s) {
        Toast.makeText(this, "주소를 찾은 경우 호출된다.", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onReverseGeoCoderFailedToFindAddress(MapReverseGeoCoder mapReverseGeoCoder) {
        Toast.makeText(this, "Reverse Geo-Coding 서비스 호출에 실패한 경우 호출된다.", Toast.LENGTH_SHORT).show();
    }
    /* MapReverseGeoCoder.ReverseGeoCodingResultListener End */




    /*
     * MapView.MapViewEventListener Start
     * MapViewEventListener interface를 구현하는 객체를 MapView 객체에 등록하여
     * 지도 이동/확대/축소, 지도 화면 터치(Single Tap / Double Tap / Long Press) 이벤트를 통보받을 수 있다.
     */
    @Override
    public void onMapViewInitialized(MapView mapView) {
        Toast.makeText(this, "MapView가 사용가능 한 상태가 되었음을 알려준다.", Toast.LENGTH_SHORT).show();
        // onMapViewInitialized()가 호출된 이후에 MapView 객체가 제공하는 지도 조작 API들을 사용할 수 있다.

        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(35.87137603, 128.601806640625), 5, true);   // 대구광역시청
        MapPoint.GeoCoordinate mapPointGeo = mapView.getMapCenterPoint().getMapPointGeoCoord();
        viewModel.setLatLon(mapPointGeo.latitude, mapPointGeo.longitude);
//        binding.setViewModel(viewModel);
    }
    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {
        Toast.makeText(this, "지도 중심 좌표가 이동한 경우 호출된다.", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {
        Toast.makeText(this, "지도 확대/축소 레벨이 변경된 경우 호출된다.", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {
        Toast.makeText(this, "사용자가 지도 위를 터치한 경우 호출된다.", Toast.LENGTH_SHORT).show();
//        viewModel.dismissFeatureDetails();
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }
    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {
        Toast.makeText(this, "사용자가 지도 위 한 지점을 더블 터치한 경우 호출된다.", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {
        Toast.makeText(this, "사용자가 지도 위 한 지점을 길게 누른 경우(long press) 호출된다.", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {
        Toast.makeText(this, "사용자가 지도 드래그를 시작한 경우 호출된다.", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {
        Toast.makeText(this, "사용자가 지도 드래그를 끝낸 경우 호출된다.", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {
        Toast.makeText(this, "지도의 이동이 완료된 경우 호출된다.", Toast.LENGTH_SHORT).show();

        MapPoint.GeoCoordinate mapPointGeo = mapView.getMapCenterPoint().getMapPointGeoCoord();
        viewModel.setLatLon(mapPointGeo.latitude, mapPointGeo.longitude);
//        binding.setViewModel(viewModel);
    }
    /* MapView.MapViewEventListener End */





    /*
     * MapView.POIItemEventListener Start
     * POIItemEventListener interface를 구현하는 객체를 MapView 객체에 등록하여 POI 관련 이벤트를 통보받을 수 있다.
     */
    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {
        Toast.makeText(this, "단말 사용자가 POI Item을 선택한 경우 호출된다.", Toast.LENGTH_SHORT).show();
        // 사용자가 MapView 에 등록된 POI Item 아이콘(마커)를 터치한 경우 호출된다.

        binding.markerIcon.setImageResource(mapPOIItem.getCustomImageResourceId());
        binding.markerTitle.setText(mapPOIItem.getItemName());
        binding.markerSubtitle.setText("재난 지역");
        binding.markerDescription.setText("지진 / 홍수 / 화재 / 교통 사고 등 재난 발생 지역입니다.  지진 / 홍수 / 화재 / 교통 사고 등 재난 발생 지역입니다.  지진 / 홍수 / 화재 / 교통 사고 등 재난 발생 지역입니다.  지진 / 홍수 / 화재 / 교통 사고 등 재난 발생 지역입니다.  지진 / 홍수 / 화재 / 교통 사고 등 재난 발생 지역입니다.  지진 / 홍수 / 화재 / 교통 사고 등 재난 발생 지역입니다.  지진 / 홍수 / 화재 / 교통 사고 등 재난 발생 지역입니다.  지진 / 홍수 / 화재 / 교통 사고 등 재난 발생 지역입니다.");
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }
    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) { /* Deprecated */ }
    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {
        Toast.makeText(this, "onCalloutBalloonOfPOIItemTouched 호출", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {
        Toast.makeText(this, "단말 사용자가 길게 누른 후(long press) 끌어서(dragging) 위치 이동이 가능한 POI Item의 위치를 이동시킨 경우 호출된다.", Toast.LENGTH_SHORT).show();
        // 이동가능한 POI Item을 Draggable POI Item 이라 한다.
    }
    /* MapView.POIItemEventListener End */
}
