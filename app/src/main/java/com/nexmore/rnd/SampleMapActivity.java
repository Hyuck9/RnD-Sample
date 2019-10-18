package com.nexmore.rnd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.nexmore.rnd.databinding.ActivitySampleMapBinding;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapReverseGeoCoder;
import net.daum.mf.map.api.MapView;

public class SampleMapActivity extends AppCompatActivity implements MapView.CurrentLocationEventListener, MapReverseGeoCoder.ReverseGeoCodingResultListener, MapView.MapViewEventListener, MapView.POIItemEventListener {

    ActivitySampleMapBinding binding;
    SampleMapViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MapView.setMapTilePersistentCacheEnabled(true); // 지도 타일 이미지 캐시 기능 활성화
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sample_map);

        viewModel = ViewModelProviders.of(this).get(SampleMapViewModel.class);
//        binding.setViewModel(viewModel);
        viewModel.getLonLat().observe(this, lonlat -> binding.textLonLat.setText(lonlat));


        initMap();
    }

    private void initMap() {
        binding.sMap.setMapType(MapView.MapType.Hybrid);
        binding.sMap.setMapViewEventListener(this);
//        binding.sMap.setCurrentLocationEventListener(this);
//        binding.sMap.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithHeading);
    }



    /*
     * MapView.CurrentLocationEventListener Start
     * CurrentLocationEventListener interface를 구현하는 객체를 MapView 객체에 등록하여 현위치 트래킹 이벤트를 통보받을 수 있다.
     */
    @Override
    public void onCurrentLocationUpdate(MapView mapView, MapPoint mapPoint, float v) {
        // 단말의 현위치 좌표값을 통보받을 수 있다.
        //
        // MapView.setCurrentLocationTrackingMode 메소드를 통해
        // 사용자 현위치 트래킹 기능이 켜진 경우(CurrentLocationTrackingMode.TrackingModeOnWithoutHeading, CurrentLocationTrackingMode.TrackingModeOnWithHeading)
        // 단말의 위치에 해당하는 지도 좌표와 위치 정확도가 주기적으로 delegate 객체에 통보된다.
    }
    @Override
    public void onCurrentLocationDeviceHeadingUpdate(MapView mapView, float v) {
        // 단말의 방향(Heading) 각도값을 통보받을 수 있다.
        //
        // MapView.setCurrentLocationTrackingMode 메소드를 통해
        // 사용자 현위치 트래킹과 나침반 모드가 켜진 경우(CurrentLocationTrackingMode.TrackingModeOnWithHeading)
        // 단말의 방향 각도값이 주기적으로 delegate 객체에 통보된다.
    }
    @Override
    public void onCurrentLocationUpdateFailed(MapView mapView) {
        // 현위치 갱신 작업에 실패한 경우 호출된다.
    }
    @Override
    public void onCurrentLocationUpdateCancelled(MapView mapView) {
        // 현위치 트랙킹 기능이 사용자에 의해 취소된 경우 호출된다.
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
        // 주소를 찾은 경우 호출된다.
    }
    @Override
    public void onReverseGeoCoderFailedToFindAddress(MapReverseGeoCoder mapReverseGeoCoder) {
        // Reverse Geo-Coding 서비스 호출에 실패한 경우 호출된다.
    }
    /* MapReverseGeoCoder.ReverseGeoCodingResultListener End */




    /*
     * MapView.MapViewEventListener Start
     * MapViewEventListener interface를 구현하는 객체를 MapView 객체에 등록하여
     * 지도 이동/확대/축소, 지도 화면 터치(Single Tap / Double Tap / Long Press) 이벤트를 통보받을 수 있다.
     */
    @Override
    public void onMapViewInitialized(MapView mapView) {
        // MapView가 사용가능 한 상태가 되었음을 알려준다.
        // onMapViewInitialized()가 호출된 이후에 MapView 객체가 제공하는 지도 조작 API들을 사용할 수 있다.

        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(35.87137603, 128.601806640625), 5, true);   // 대구광역시청
        MapPoint.GeoCoordinate mapPointGeo = mapView.getMapCenterPoint().getMapPointGeoCoord();
        viewModel.setLatLon(mapPointGeo.latitude, mapPointGeo.longitude);
//        binding.setViewModel(viewModel);
    }
    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {
        // 지도 중심 좌표가 이동한 경우 호출된다.
    }
    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {
        // 지도 확대/축소 레벨이 변경된 경우 호출된다.
    }
    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {
        // 사용자가 지도 위를 터치한 경우 호출된다.
    }
    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {
        // 사용자가 지도 위 한 지점을 더블 터치한 경우 호출된다.
    }
    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {
        // 사용자가 지도 위 한 지점을 길게 누른 경우(long press) 호출된다.
    }
    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {
        // 사용자가 지도 드래그를 시작한 경우 호출된다.
    }
    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {
        // 사용자가 지도 드래그를 끝낸 경우 호출된다.
    }
    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {
        // 지도의 이동이 완료된 경우 호출된다.

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
        // 단말 사용자가 POI Item을 선택한 경우 호출된다.
        // 사용자가 MapView 에 등록된 POI Item 아이콘(마커)를 터치한 경우 호출된다.
    }
    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) { /* Deprecated */ }
    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) { /* 설명 없음 */ }
    @Override
    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {
        // 단말 사용자가 길게 누른 후(long press) 끌어서(dragging) 위치 이동이 가능한 POI Item의 위치를 이동시킨 경우 호출된다.
        // 이동가능한 POI Item을 Draggable POI Item 이라 한다.
    }
    /* MapView.POIItemEventListener End */
}
