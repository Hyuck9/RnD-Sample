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

        List<POIItem> poiItemList = new ArrayList<>();
        poiItemList.add(poiItem);
        itemList.setValue(poiItemList);
    }

    public void initSlopeItem() {
        POIItem poiItem = new POIItem();
        poiItem.setItemName("대구역");
        poiItem.setTag(2);
        poiItem.setCustomImageResourceId(R.drawable.map_marker_water);
        poiItem.setLatitude(35.876304626464844);
        poiItem.setLongitude(128.59703063964844);
        poiItem.setRed(255);
        poiItem.setGreen(0);
        poiItem.setBlue(0);

        List<POIItem> poiItemList = new ArrayList<>();
        poiItemList.add(poiItem);
        itemList.setValue(poiItemList);
    }

    public void initManholeItem() {
        POIItem poiItem = new POIItem();
        poiItem.setItemName("대구역");
        poiItem.setTag(3);
        poiItem.setCustomImageResourceId(R.drawable.map_marker_water);
        poiItem.setLatitude(35.876304626464844);
        poiItem.setLongitude(128.59703063964844);
        poiItem.setRed(255);
        poiItem.setGreen(0);
        poiItem.setBlue(0);

        List<POIItem> poiItemList = new ArrayList<>();
        poiItemList.add(poiItem);
        itemList.setValue(poiItemList);
    }

    public void initHeatItem() {
        POIItem poiItem = new POIItem();
        poiItem.setItemName("대구역");
        poiItem.setTag(4);
        poiItem.setCustomImageResourceId(R.drawable.map_marker_water);
        poiItem.setLatitude(35.876304626464844);
        poiItem.setLongitude(128.59703063964844);
        poiItem.setRed(255);
        poiItem.setGreen(0);
        poiItem.setBlue(0);

        List<POIItem> poiItemList = new ArrayList<>();
        poiItemList.add(poiItem);
        itemList.setValue(poiItemList);
    }

    public void initFireItem() {
        List<POIItem> poiItemList = new ArrayList<>();
        POIItem poiItem = new POIItem();
        poiItem.setItemName("대구역");
        poiItem.setTag(5);
        poiItem.setCustomImageResourceId(R.drawable.map_marker_charging);
        poiItem.setLatitude(35.876304626464844);
        poiItem.setLongitude(128.59703063964844);
        poiItem.setRed(255);
        poiItem.setGreen(0);
        poiItem.setBlue(0);
        poiItemList.add(poiItem);

        poiItem = new POIItem();
        poiItem.setItemName("동성로");
        poiItem.setTag(5);
        poiItem.setCustomImageResourceId(R.drawable.map_marker_charging);
        poiItem.setLatitude(35.86906814575195);
        poiItem.setLongitude(128.5952911376953);
        poiItem.setRed(255);
        poiItem.setGreen(0);
        poiItem.setBlue(0);
        poiItemList.add(poiItem);
        itemList.setValue(poiItemList);
    }

}
