package com.nexmore.rnd.ui.map;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.nexmore.rnd.R;
import com.nexmore.rnd.data.POIItem;
import com.nexmore.rnd.databinding.FragmentMapBinding;
import com.nexmore.rnd.widget.BottomSheetBehavior;

import net.daum.mf.map.api.MapCircle;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import org.jetbrains.annotations.NotNull;

import static com.nexmore.rnd.utils.UiUtilsKt.slideOffsetToAlpha;

@SuppressLint("LogNotTimber")
public class MapFragment extends Fragment implements MapView.CurrentLocationEventListener, MapView.MapViewEventListener, MapView.POIItemEventListener {

    private double mLatitude = 37.56640625;
    private double mLongitude = 126.97787475585938;

    private FragmentMapBinding binding;
    private MapViewModel viewModel;
    private BottomSheetBehavior bottomSheetBehavior;

//    private boolean isFabOpen = false;
//    private Animation fabOpen, fabClose, fabRClockwise, fabRAntiClockWise;

    private static final int CREATE_CHAT_REQUEST_CODE = 107;
    private static final float ALPHA_TRANSITION_START = 0.1f;
    private static final float ALPHA_TRANSITION_END = 0.5f;

    private ViewModelProvider.AndroidViewModelFactory viewModelFactory;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        MapView.setMapTilePersistentCacheEnabled(true);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_map, container, false);

        if ( viewModelFactory == null ) {
            viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication());
        }
        viewModel = new ViewModelProvider(requireActivity(), viewModelFactory).get(MapViewModel.class);
        initMap();
        initFab();
        initBottomSheet();
        initCheckBox();

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        if ( viewModelFactory == null ) {
//            viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication());
//        }
//        viewModel = new ViewModelProvider(requireActivity(), viewModelFactory).get(MapViewModel.class);
        binding.setViewModel(viewModel);
    }

    /**
     * MapView SetUp
     */
    private void initMap() {
        binding.mapView.setCurrentLocationEventListener(this);
        binding.mapView.setMapViewEventListener(this);
        binding.mapView.setPOIItemEventListener(this);
//        initEvent1();
//        initEvent2();
//        initEvent3();
        test();
    }

    /**
     * CheckBox SetUp (TrackingMode On / Off)
     */
    private void initCheckBox() {
        binding.checkboxMyLocation.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if ( isChecked ) {
                binding.mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithHeading);
            } else {
                binding.mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);
            }
        });
//        binding.checkboxMyLocation.setChecked(true);
    }

    /**
     * Floating Action Button SetUp
     */
    private void initFab() {
//        initAnimation();
//        binding.fabMain.setOnClickListener(view -> {
//            if ( isFabOpen ) {
//                fabCloseAction();
//            } else {
//                fabOpenAction();
//            }
//        });
//
//        binding.fabCreateChat.setOnClickListener(view -> {
//            Intent intent = new Intent(getContext(), CreateChatActivity.class);
//            int color = ContextCompat.getColor(requireContext(), R.color.colorFabGreen);
//            FabTransform.addExtras(intent, color, R.drawable.ic_forum_white);
//            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
//                    .makeSceneTransitionAnimation(requireActivity(),
//                            binding.fabCreateChat,
//                            getString(R.string.transition_name_create_chat));
//            startActivityForResult(intent, CREATE_CHAT_REQUEST_CODE, optionsCompat.toBundle());
//        });

        binding.fabSns.setOnClickListener(view -> {
            Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.setType("text/plain");
            String text = "재난 지도 공유 TEST";
            intent.putExtra(Intent.EXTRA_TEXT, text);
            Intent chooser = Intent.createChooser(intent, "SNS 공유하기");
            startActivity(chooser);
        });

        binding.fabManual.setOnClickListener(view -> {
            if (View.GONE == binding.cardManual.getVisibility()) {
                binding.cardManual.setVisibility(View.VISIBLE);
            } else {
                binding.cardManual.setVisibility(View.GONE);
            }
        });

        binding.cardManualBtnOk.setOnClickListener(view -> {
            binding.cardManual.setVisibility(View.GONE);
        });

    }

//    /**
//     * Animation SetUp
//     */
//    private void initAnimation() {
//        fabOpen = AnimationUtils.loadAnimation(getContext(), R.anim.fab_open);
//        fabClose = AnimationUtils.loadAnimation(getContext(), R.anim.fab_close);
//        fabRClockwise = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_clockwise);
//        fabRAntiClockWise = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_anticlockwise);
//    }

//    /**
//     * Floating Action Button Open Event
//     */
//    private void fabOpenAction() {
//        binding.fabMain.startAnimation(fabRClockwise);
//        binding.fabCreateChat.startAnimation(fabOpen);
//        binding.fabSns.startAnimation(fabOpen);
//        binding.fabCreateChat.setClickable(true);
//        binding.fabSns.setClickable(true);
//        isFabOpen = true;
//    }
//
//    /**
//     * Floating Action Button Close Event
//     */
//    private void fabCloseAction() {
//        binding.fabMain.startAnimation(fabRAntiClockWise);
//        binding.fabCreateChat.startAnimation(fabClose);
//        binding.fabSns.startAnimation(fabClose);
//        binding.fabCreateChat.setClickable(false);
//        binding.fabSns.setClickable(false);
//        isFabOpen = false;
//    }

    /**
     * BottomSheet SetUp
     */
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
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
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
        }

        @Override
        public void onSlide(@NotNull View bottomSheet, float slideOffset) {
            binding.descriptionScrollview.setAlpha(slideOffsetToAlpha(slideOffset, ALPHA_TRANSITION_START, ALPHA_TRANSITION_END));
//            if (slideOffset > 0f) {
//                binding.fabMain.hide();
//            } else {
//                binding.fabMain.show();
//                // Translate FAB to make room for the peeking sheet.
//                binding.fabMain.setTranslationY(bottomSheet.getTop() - 32 - binding.fabMain.getBottom());
//            }
        }
    };

    private void initEvent1() {
        MapPOIItem customMarker = new MapPOIItem();
//        customMarker.setItemName("뚝섬역");
        customMarker.setItemName("동성로");
        customMarker.setTag(1);
//        customMarker.setMapPoint(MapPoint.mapPointWithGeoCoord(37.5471611022949289962387, 127.0472412109375));
        customMarker.setMapPoint(MapPoint.mapPointWithGeoCoord(35.86906814575195, 128.5952911376953));
        customMarker.setMarkerType(MapPOIItem.MarkerType.CustomImage); // 마커타입을 커스텀 마커로 지정.
        customMarker.setCustomImageResourceId(R.drawable.map_marker_charging); // 마커 이미지.
        customMarker.setCustomImageAutoscale(false); // hdpi, xhdpi 등 안드로이드 플랫폼의 스케일을 사용할 경우 지도 라이브러리의 스케일 기능을 꺼줌.
        customMarker.setCustomImageAnchor(0.5f, 0.5f); // 마커 이미지중 기준이 되는 위치(앵커포인트) 지정 - 마커 이미지 좌측 상단 기준 x(0.0f ~ 1.0f), y(0.0f ~ 1.0f) 값.
        MapCircle circle = new MapCircle(
//                MapPoint.mapPointWithGeoCoord(37.54716110229492,127.0472412109375), // center
                MapPoint.mapPointWithGeoCoord(35.86906814575195, 128.5952911376953), // center
                500, // radius
                Color.argb(128, 255, 0, 0), // strokeColor
                Color.argb(64, 0, 0, 255) // fillColor
        );
        circle.setTag(1);
        binding.mapView.addCircle(circle);
        binding.mapView.addPOIItem(customMarker);
    }

    private void initEvent2() {
        MapPOIItem customMarker = new MapPOIItem();
//        customMarker.setItemName("성동세무서");
        customMarker.setItemName("대구역");
        customMarker.setTag(2);
//        customMarker.setMapPoint(MapPoint.mapPointWithGeoCoord(37.54852294921875,127.06273651123047));
        customMarker.setMapPoint(MapPoint.mapPointWithGeoCoord(35.876304626464844, 128.59703063964844));
        customMarker.setMarkerType(MapPOIItem.MarkerType.CustomImage); // 마커타입을 커스텀 마커로 지정.
        customMarker.setCustomImageResourceId(R.drawable.map_marker_water);
        customMarker.setCustomImageAutoscale(false); // hdpi, xhdpi 등 안드로이드 플랫폼의 스케일을 사용할 경우 지도 라이브러리의 스케일 기능을 꺼줌.
        customMarker.setCustomImageAnchor(0.5f, 0.5f); // 마커 이미지중 기준이 되는 위치(앵커포인트) 지정 - 마커 이미지 좌측 상단 기준 x(0.0f ~ 1.0f), y(0.0f ~ 1.0f) 값.
        MapCircle circle = new MapCircle(
//                MapPoint.mapPointWithGeoCoord(37.54855728149414,127.06277465820312), // center
                MapPoint.mapPointWithGeoCoord(35.876304626464844, 128.59703063964844), // center
                500, // radius
                Color.argb(128, 255, 0, 0), // strokeColor
                Color.argb(64, 0, 255, 0) // fillColor
        );
        circle.setTag(2);
        binding.mapView.addCircle(circle);
        binding.mapView.addPOIItem(customMarker);
    }

    private void initEvent3() {
        MapPOIItem customMarker = new MapPOIItem();
//        customMarker.setItemName("성수사거리");
        customMarker.setItemName("중구청");
        customMarker.setTag(3);
//        customMarker.setMapPoint(MapPoint.mapPointWithGeoCoord(37.542232513427734,127.06402587890625));
        customMarker.setMapPoint(MapPoint.mapPointWithGeoCoord(35.86941146850586, 128.60618591308594));
        customMarker.setMarkerType(MapPOIItem.MarkerType.CustomImage); // 마커타입을 커스텀 마커로 지정.
        customMarker.setCustomImageResourceId(R.drawable.map_marker_1);
        customMarker.setCustomImageAutoscale(false); // hdpi, xhdpi 등 안드로이드 플랫폼의 스케일을 사용할 경우 지도 라이브러리의 스케일 기능을 꺼줌.
        customMarker.setCustomImageAnchor(0.5f, 0.5f); // 마커 이미지중 기준이 되는 위치(앵커포인트) 지정 - 마커 이미지 좌측 상단 기준 x(0.0f ~ 1.0f), y(0.0f ~ 1.0f) 값.
        MapCircle circle = new MapCircle(
//                MapPoint.mapPointWithGeoCoord(37.542232513427734,127.06402587890625), // center
                MapPoint.mapPointWithGeoCoord(35.86941146850586, 128.60618591308594), // center
                500, // radius
                Color.argb(128, 255, 0, 0), // strokeColor
                Color.argb(64, 255, 0, 0) // fillColor
        );
        circle.setTag(3);
        binding.mapView.addCircle(circle);
        binding.mapView.addPOIItem(customMarker);
    }

    private void test() {
        viewModel.getItemList().observe(requireActivity(), mapPOIItems -> {
            binding.mapView.removeAllPOIItems();
            binding.mapView.removeAllCircles();
            for (POIItem mapPOIItem : mapPOIItems) {
                MapPOIItem customMarker = new MapPOIItem();
                customMarker.setItemName(mapPOIItem.getItemName());
                customMarker.setTag(mapPOIItem.getTag());
                customMarker.setMapPoint(MapPoint.mapPointWithGeoCoord(mapPOIItem.getLatitude(), mapPOIItem.getLongitude()));
                customMarker.setMarkerType(MapPOIItem.MarkerType.CustomImage); // 마커타입을 커스텀 마커로 지정.
                customMarker.setCustomImageResourceId(mapPOIItem.getCustomImageResourceId());
                customMarker.setCustomImageAutoscale(false); // hdpi, xhdpi 등 안드로이드 플랫폼의 스케일을 사용할 경우 지도 라이브러리의 스케일 기능을 꺼줌.
                customMarker.setCustomImageAnchor(0.5f, 0.5f); // 마커 이미지중 기준이 되는 위치(앵커포인트) 지정 - 마커 이미지 좌측 상단 기준 x(0.0f ~ 1.0f), y(0.0f ~ 1.0f) 값.
                MapCircle circle = new MapCircle(
                        customMarker.getMapPoint(),
                        500, // radius
                        Color.argb(128, 255, 0, 0), // strokeColor
                        Color.argb(64, mapPOIItem.getRed(), mapPOIItem.getGreen(), mapPOIItem.getBlue()) // fillColor
                );
                if ( R.drawable.feap != customMarker.getCustomImageResourceId()  ) {
                    binding.mapView.addCircle(circle);
                }
                binding.mapView.addPOIItem(customMarker);
                binding.tvManual.setText(mapPOIItem.getManualText());
            }
        });
    }


    /*
     * MapView.CurrentLocationEventListener Start
     * CurrentLocationEventListener interface를 구현하는 객체를 MapView 객체에 등록하여 현위치 트래킹 이벤트를 통보받을 수 있다.
     */
    @Override
    public void onCurrentLocationUpdate(MapView mapView, MapPoint mapPoint, float accuracyInMeters) {
        // showToastMassage("단말의 현위치 좌표값을 통보받을 수 있다.");
        //
        // MapView.setCurrentLocationTrackingMode 메소드를 통해
        // 사용자 현위치 트래킹 기능이 켜진 경우(CurrentLocationTrackingMode.TrackingModeOnWithoutHeading, CurrentLocationTrackingMode.TrackingModeOnWithHeading)
        // 단말의 위치에 해당하는 지도 좌표와 위치 정확도가 주기적으로 delegate 객체에 통보된다.

        if ( mapView.getCurrentLocationTrackingMode() == MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading ) {
            binding.mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeadingWithoutMapMoving);
        }
        MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();
        mLatitude = mapPointGeo.latitude;
        mLongitude = mapPointGeo.longitude;
        Log.i("test", String.format("MapView onCurrentLocationUpdate (%f,%f) accuracy (%f)", mapPointGeo.latitude, mapPointGeo.longitude, accuracyInMeters));
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(mLatitude, mLongitude), true);
        binding.mapView.setShowCurrentLocationMarker(true);
        binding.mapView.setCurrentLocationRadius(50);
//        mMapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(mLatitude, mLongitude), 6, true);
    }
    @Override
    public void onCurrentLocationDeviceHeadingUpdate(MapView mapView, float v) {
        // showToastMassage("단말의 방향(Heading) 각도값을 통보받을 수 있다.");
        //
        // MapView.setCurrentLocationTrackingMode 메소드를 통해
        // 사용자 현위치 트래킹과 나침반 모드가 켜진 경우(CurrentLocationTrackingMode.TrackingModeOnWithHeading)
        // 단말의 방향 각도값이 주기적으로 delegate 객체에 통보된다.
    }
    @Override
    public void onCurrentLocationUpdateFailed(MapView mapView) {
        // showToastMassage("현위치 갱신 작업에 실패한 경우 호출된다.");
    }
    @Override
    public void onCurrentLocationUpdateCancelled(MapView mapView) {
        // showToastMassage("현위치 트랙킹 기능이 사용자에 의해 취소된 경우 호출된다.");
        // 처음 현위치를 찾는 동안에 현위치를 찾는 중이라는 Alert Dialog 인터페이스가 사용자에게 노출된다.
        // 첫 현위치를 찾기전에 사용자가 취소 버튼을 누른 경우 호출 된다.
    }
    /* MapView.CurrentLocationEventListener End */




    /*
     * MapView.MapViewEventListener Start
     * MapViewEventListener interface를 구현하는 객체를 MapView 객체에 등록하여
     * 지도 이동/확대/축소, 지도 화면 터치(Single Tap / Double Tap / Long Press) 이벤트를 통보받을 수 있다.
     */
    @Override
    public void onMapViewInitialized(MapView mapView) {
        showToastMassage("MapView가 사용가능 한 상태가 되었음을 알려준다.");
        // onMapViewInitialized()가 호출된 이후에 MapView 객체가 제공하는 지도 조작 API들을 사용할 수 있다.

//        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
//        Log.d("test", "onCreate - 현재 위치 : " + mLatitude + ", " + mLongitude);
//        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(mLatitude, mLongitude), 2, true);
        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(35.87130355834961, 128.60165405273438), 2, true);

    }
    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {
        showToastMassage("지도 중심 좌표가 이동한 경우 호출된다.");
    }
    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {
        showToastMassage("지도 확대/축소 레벨이 변경된 경우 호출된다.");
    }
    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {
        showToastMassage("사용자가 지도 위를 터치한 경우 호출된다.");

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
//        binding.fabMain.show();
        MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();
        Log.d("test", "onMapViewSingleTapped - 현재 위치 : " + mapPointGeo.latitude + ", " + mapPointGeo.longitude);
    }
    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {
        showToastMassage("사용자가 지도 위 한 지점을 더블 터치한 경우 호출된다.");
    }
    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {
        showToastMassage("사용자가 지도 위 한 지점을 길게 누른 경우(long press) 호출된다.");
    }
    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {
        showToastMassage("사용자가 지도 드래그를 시작한 경우 호출된다.");
    }
    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {
        showToastMassage("사용자가 지도 드래그를 끝낸 경우 호출된다.");
    }
    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {
        showToastMassage("지도의 이동이 완료된 경우 호출된다.");
        MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();
        Log.d("test", "onMapViewMoveFinished - 현재 위치 : " + mapPointGeo.latitude + ", " + mapPointGeo.longitude);

    }
    /* MapView.MapViewEventListener End */






    /*
     * MapView.POIItemEventListener Start
     * POIItemEventListener interface를 구현하는 객체를 MapView 객체에 등록하여 POI 관련 이벤트를 통보받을 수 있다.
     */
    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {
        showToastMassage("단말 사용자가 POI Item을 선택한 경우 호출된다.");
        // 사용자가 MapView 에 등록된 POI Item 아이콘(마커)를 터치한 경우 호출된다.

        binding.markerIcon.setImageResource(mapPOIItem.getCustomImageResourceId());
        binding.markerTitle.setText(mapPOIItem.getItemName());
        String subTitle;
        String description;
        switch (mapPOIItem.getTag()) {
            case 1:
                subTitle = "도시 홍수 발생";
                description = "홍수로 인한 피해가 속출하고 있습니다. 주의 하시기 바랍니다.";
                break;
            case 2:
                subTitle = "경사지 붕괴 발생";
                description = "홍수로 인한 피해가 속출하고 있습니다. 주의 하시기 바랍니다.";
                break;
            case 3:
                subTitle = "맨홀 사고 발생";
                description = "맨홀 역류로 인한 피해가 속출하고 있습니다. 주의 하시기 바랍니다.";
                break;
            case 4:
                subTitle = "폭염 주의보 발생";
                description = "폭염으로 인한 피해가 속출하고 있습니다. 주의 하시기 바랍니다.";
                break;
            case 5:
                subTitle = "화재 발생";
                description = "화재로 인한 피해가 속출하고 있습니다. 주의 하시기 바랍니다.";
                break;
            default:
                subTitle = "재난 지역";
                description = "지진 / 홍수 / 화재 / 교통 사고 등 재난 발생 지역입니다.  지진 / 홍수 / 화재 / 교통 사고 등 재난 발생 지역입니다.  지진 / 홍수 / 화재 / 교통 사고 등 재난 발생 지역입니다.  지진 / 홍수 / 화재 / 교통 사고 등 재난 발생 지역입니다.  지진 / 홍수 / 화재 / 교통 사고 등 재난 발생 지역입니다.  지진 / 홍수 / 화재 / 교통 사고 등 재난 발생 지역입니다.  지진 / 홍수 / 화재 / 교통 사고 등 재난 발생 지역입니다.  지진 / 홍수 / 화재 / 교통 사고 등 재난 발생 지역입니다.";
                break;
        }
        binding.markerSubtitle.setText(subTitle);
        binding.markerDescription.setText(description);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

//        if ( isFabOpen ) {
//            fabCloseAction();
//        }
//        binding.fabMain.hide();
    }
    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) { /* Deprecated */ }
    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {
        showToastMassage("onCalloutBalloonOfPOIItemTouched 호출");
    }
    @Override
    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {
        showToastMassage("단말 사용자가 길게 누른 후(long press) 끌어서(dragging) 위치 이동이 가능한 POI Item의 위치를 이동시킨 경우 호출된다.");
        // 이동가능한 POI Item을 Draggable POI Item 이라 한다.
    }
    /* MapView.POIItemEventListener End */




    private void showToastMassage(String msg) {
//        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
