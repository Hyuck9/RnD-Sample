package com.nexmore.rnd.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.nexmore.rnd.R;
import com.nexmore.rnd.ui.map.MapFragment;
import com.nexmore.rnd.ui.map.MapViewModel;

public class HomeFragment extends Fragment {

    private MapViewModel viewModel;
    private ViewModelProvider.AndroidViewModelFactory viewModelFactory;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if ( viewModelFactory == null ) {
            viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication());
        }
        viewModel = new ViewModelProvider(requireActivity(), viewModelFactory).get(MapViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        root.findViewById(R.id.cardFlood).setOnClickListener(view -> {
            viewModel.initFloodItem();
            openMapFragment("도시 홍수");
        });
        root.findViewById(R.id.cardSlope).setOnClickListener(view -> {
            viewModel.initSlopeItem();
            openMapFragment("경사지 붕괴");
        });
        root.findViewById(R.id.cardManhole).setOnClickListener(view -> {
            viewModel.initManholeItem();
            openMapFragment("맨홀");
        });
        root.findViewById(R.id.cardHeat).setOnClickListener(view -> {
            viewModel.initHeatItem();
            openMapFragment("폭염");
        });
        root.findViewById(R.id.cardFire).setOnClickListener(view -> {
            viewModel.initFireItem();
            openMapFragment("화재");
        });

//        root.findViewById(R.id.cardSlope).setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.action_nav_home_to_nav_slope));
//
//        root.findViewById(R.id.cardManhole).setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.action_nav_home_to_nav_manhole));
//
//        root.findViewById(R.id.cardHeat).setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.action_nav_home_to_nav_heat));
//
//        root.findViewById(R.id.cardFire).setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.action_nav_home_to_nav_fire));

//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

    private void openMapFragment(String title) {
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new HomeFragment()).commit();
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new MapFragment()).commit();
        requireActivity().setTitle(title);
    }
}