package com.nexmore.rnd.ui.map;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import lombok.Getter;
import lombok.Setter;

public class MapViewModel extends ViewModel {

    @Setter @Getter
    private MutableLiveData<String> title;
    // TODO: Implement the ViewModel
}
