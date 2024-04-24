package com.adifaisalr.mypos;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adifaisalr.mypos.data.Menu;
import com.adifaisalr.mypos.databinding.FragmentFirstBinding;

import java.util.ArrayList;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private ArrayList<Menu> allMenuList = new ArrayList<>();
    private ArrayList<Menu> filteredMenuList = new ArrayList<>();
    private CustomAdapter adapter = new CustomAdapter(filteredMenuList);

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        allMenuList.add(new Menu("Fried Rice", "Food", 15000));
        allMenuList.add(new Menu("Rice", "Food", 5000));
        allMenuList.add(new Menu("French Fries", "Food", 10000));
        allMenuList.add(new Menu("French Fries & Sausage", "Food", 15000));
        allMenuList.add(new Menu("Chicken Soup", "Food", 15000));
        allMenuList.add(new Menu("Mushroom Soup", "Food", 15000));
        allMenuList.add(new Menu("Chicken Steak", "Food", 25000));
        allMenuList.add(new Menu("Beef Steak", "Food", 45000));
        allMenuList.add(new Menu("Mineral Water", "Beverage", 5000));
        allMenuList.add(new Menu("Ice Tea", "Beverage", 6000));
        allMenuList.add(new Menu("Ice Sweet Tea", "Beverage", 7000));
        allMenuList.add(new Menu("Ice Lemon Tea", "Beverage", 9000));
        allMenuList.add(new Menu("Ice Lychee Tea", "Beverage", 9000));
        filteredMenuList.addAll(allMenuList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        binding.menuRv.setLayoutManager(layoutManager);
        binding.menuRv.setAdapter(adapter);

        binding.searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filterMenuList(editable.toString());
            }
        });
    }

    private void filterMenuList(String keyword) {
        filteredMenuList.clear();
        for (Menu menu : allMenuList) {
            if (menu.getName().toLowerCase().contains(keyword.toLowerCase())) {
                filteredMenuList.add(menu);
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}