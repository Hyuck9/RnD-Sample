package com.nexmore.rnd.ui.map;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nexmore.rnd.R;
import com.nexmore.rnd.data.POIItem;

import java.util.ArrayList;
import java.util.List;

public class MapViewModel extends ViewModel {


    private MutableLiveData<List<POIItem>> itemList = new MutableLiveData<>();

    public MutableLiveData<List<POIItem>> getItemList() {
        return itemList;
    }

    public void initFloodItem() {
        POIItem poiItem = new POIItem();
        poiItem.setItemName("대구역");
        poiItem.setTag(1);
        poiItem.setCustomImageResourceId(R.drawable.map_marker_water);
        poiItem.setLatitude(35.876304626464844);
        poiItem.setLongitude(128.59703063964844);
        poiItem.setRed(255);
        poiItem.setGreen(0);
        poiItem.setBlue(0);
        poiItem.setManualText("- 홍수가 발생하였으니 빨리 대피소나 높은 안전지대로 대피 합니다.\n\n" +
                "- 지정된 대피소에 도착하면 반드시 도착사실을 알리고, 통제에 따라 행동합시다.\n\n" +
                "- 홍수에 의하여 밀려온 물에는 부유물로 인한 세균감염 우려가 있으니 가까이 가지 않도록 주의합시다.\n\n" +
                "- 흐르는 물은 바닥이 보이지 않아 위험하고, 약해보이는 물살도 넘어질 수 있으니 들어가지 않습니다.\n\n" +
                "- 침수된 지역에서 자동차를 운전하지 않습니다.\n\n" +
                "- 도로에 있는 차량은 속도를 줄여서 운전합시다.\n\n" +
                "- 아파트 등 고층건물 옥상, 지하실과 하수도 맨홀에 접근하지 맙시다.\n");

        List<POIItem> poiItemList = new ArrayList<>();
        poiItemList.add(poiItem);
        itemList.setValue(poiItemList);
    }

    public void initSlopeItem() {
        POIItem poiItem = new POIItem();
        poiItem.setItemName("대구역");
        poiItem.setTag(2);
        poiItem.setCustomImageResourceId(R.drawable.marker_slope);
        poiItem.setLatitude(35.876304626464844);
        poiItem.setLongitude(128.59703063964844);
        poiItem.setRed(255);
        poiItem.setGreen(0);
        poiItem.setBlue(0);
        poiItem.setManualText("- 경사지 주변의 야외 활동을 하지 않는다.\n\n" +
                "- 산사태가 일어날 위험이 있는 곳에서 가까이 가지 않는다.\n\n" +
                "- 산사태 징후에 대해 사전에 숙지\n\n" +
                "- 화염을 통과하여 대피할 때에는 물에 적신 담요 등을 뒤집어쓰고 신속히 대피합니다.\n");

        List<POIItem> poiItemList = new ArrayList<>();
        poiItemList.add(poiItem);
        itemList.setValue(poiItemList);
    }

    public void initManholeItem() {
        POIItem poiItem = new POIItem();
        poiItem.setItemName("대구역");
        poiItem.setTag(3);
        poiItem.setCustomImageResourceId(R.drawable.marker_manhole);
        poiItem.setLatitude(35.876304626464844);
        poiItem.setLongitude(128.59703063964844);
        poiItem.setRed(255);
        poiItem.setGreen(0);
        poiItem.setBlue(0);
        poiItem.setManualText("- 도로에 있는 차량은 속도를 줄여서 운전합니다.\n\n" +
                "- 침수된 지역에서 자동차를 운전하지 않습니다.\n\n" +
                "- 밀려온 물에는 부유물로 인한 세균감염 우려가 있으니 가까이 가지 않도록 주의합니다.\n\n" +
                "- 사고가 일어난 맨홀에 접근하지 않습니다.\n");

        List<POIItem> poiItemList = new ArrayList<>();
        poiItemList.add(poiItem);
        itemList.setValue(poiItemList);
    }

    public void initHeatItem() {
        POIItem poiItem = new POIItem();
        poiItem.setItemName("대구역");
        poiItem.setTag(4);
        poiItem.setCustomImageResourceId(R.drawable.marker_heat);
        poiItem.setLatitude(35.876304626464844);
        poiItem.setLongitude(128.59703063964844);
        poiItem.setRed(255);
        poiItem.setGreen(0);
        poiItem.setBlue(0);
        poiItem.setManualText("- 야외활동을 최대한 자제하고, 외출이 꼭 필요한 경우에는 창이 넓은 모자와 가벼운 옷차림을 하고 물병을 반드시 휴대합니다.\n\n" +
                "- 거동이 불편한 노인, 신체허약자, 환자 등을 남겨두고 장시간 외출할 경우에는 친인척, 이웃 등에 보호를 부탁합니다.\n\n" +
                "- 물을 많이 마시고, 카페인이 들어간 음료나 주류는 마시지 않습니다.\n");

        List<POIItem> poiItemList = new ArrayList<>();
        poiItemList.add(poiItem);
        itemList.setValue(poiItemList);
    }

    public void initFireItem() {
        List<POIItem> poiItemList = new ArrayList<>();
        POIItem poiItem = new POIItem();
        poiItem.setItemName("대구역");
        poiItem.setTag(5);
        poiItem.setCustomImageResourceId(R.drawable.marker_fire);
        poiItem.setLatitude(35.876304626464844);
        poiItem.setLongitude(128.59703063964844);
        poiItem.setRed(255);
        poiItem.setGreen(0);
        poiItem.setBlue(0);
        poiItemList.add(poiItem);


        poiItem = new POIItem();
        poiItem.setItemName("동성로");
        poiItem.setTag(5);
        poiItem.setCustomImageResourceId(R.drawable.feap);
        poiItem.setLatitude(35.86906814575195);
        poiItem.setLongitude(128.5952911376953);
        poiItem.setRed(255);
        poiItem.setGreen(0);
        poiItem.setBlue(0);
        poiItem.setManualText("- 피난할 때는 열기나 연기의 방향을 보고 불이 난 반대쪽의 비상구, 비상계단을 찾아 1층 또는 옥상 중 가까운 쪽을 선택합니다.\n\n" +
                "- 연기 속을 통과하여 대피할 때에는 수건 등을 물에 적셔서 입과 코를 막고 숨을 짧게 쉬며 낮은 자세로 엎드려 대피합니다.\n\n" +
                "- 고층건물 화재 시 엘리베이터는 화재발생 층에서 열리거나 정전으로 멈추어 안에 갇힐 염려가 있으니 엘리베이터 통로 자체가 굴뚝 역할을 하여 질식할 우려가 있으므로 엘리베이터를 이용하지 않습니다.\n\n" +
                "- 화염을 통과하여 대피할 때에는 물에 적신 담요 등을 뒤집어쓰고 신속히 대피합니다.\n");
        poiItemList.add(poiItem);
        itemList.setValue(poiItemList);
    }

}
