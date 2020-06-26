package com.nexmore.rnd.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.nexmore.rnd.R;
import com.nexmore.rnd.ui.map.MapFragment;

import java.util.Objects;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        root.findViewById(R.id.cardFlood).setOnClickListener(view -> test());
        root.findViewById(R.id.cardSlope).setOnClickListener(view -> test());
        root.findViewById(R.id.cardManhole).setOnClickListener(view -> test());
        root.findViewById(R.id.cardHeat).setOnClickListener(view -> test());
        root.findViewById(R.id.cardFire).setOnClickListener(view -> test());

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

    private void test() {
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new HomeFragment()).commit();
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new MapFragment()).commit();
    }
}