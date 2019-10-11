package com.nexmore.rnd.ui.fire;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.chrisbanes.photoview.PhotoView;
import com.nexmore.rnd.R;

public class FireMapFragment extends Fragment {

    private FireMapViewModel mViewModel;

//    public static FireMapFragment newInstance() {
//        return new FireMapFragment();
//    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_firemap, container, false);
        PhotoView photoView = root.findViewById(R.id.photoView);
        photoView.setImageResource(R.drawable.floor_four);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FireMapViewModel.class);
        // TODO: Use the ViewModel
    }

}
