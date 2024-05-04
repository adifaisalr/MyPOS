package com.adifaisalr.mypos;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adifaisalr.mypos.data.Beverage;
import com.adifaisalr.mypos.data.Food;
import com.adifaisalr.mypos.data.Menu;
import com.adifaisalr.mypos.databinding.FragmentFirstBinding;

import java.util.ArrayList;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private ArrayList<Menu> allMenuList = new ArrayList<>();
    private ArrayList<Menu> filteredMenuList = new ArrayList<>();
    private CustomAdapter adapter = new CustomAdapter(filteredMenuList);

    private String searchKeyword = "";
    private String selectedCategory = "All";

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

        allMenuList.add(new Menu("Fried Rice", new Food(), 15000.0));
        allMenuList.add(new Menu("Rice", new Food(), 5000.0));
        allMenuList.add(new Menu("French Fries", new Food(), 10000.0));
        allMenuList.add(new Menu("French Fries & Sausage", new Food(), 15000.0));
        allMenuList.add(new Menu("Chicken Soup", new Food(), 15000.0));
        allMenuList.add(new Menu("Mushroom Soup", new Food(), 15000.0));
        allMenuList.add(new Menu("Chicken Steak", new Food(), 25000.0));
        allMenuList.add(new Menu("Beef Steak", new Food(), 45000.0));
        allMenuList.add(new Menu("Mineral Water", new Beverage(), 5000.0));
        allMenuList.add(new Menu("Ice Tea", new Beverage(), 6000.0));
        allMenuList.add(new Menu("Ice Sweet Tea", new Beverage(), 7000.0));
        allMenuList.add(new Menu("Ice Lemon Tea", new Beverage(), 9000.0));
        allMenuList.add(new Menu("Ice Lychee Tea", new Beverage(), 9000.0));
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
                searchKeyword = editable.toString();
                filterMenuList(searchKeyword);
            }
        });
        binding.categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedCategory = (String) adapterView.getAdapter().getItem(i);
                filterMenuList(searchKeyword);
//                Toast.makeText(getContext(), "selected : " + selectedCategory, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    /**
     * Fungsi search/filter menu berdasarkan nama menu
     *
     * @param keyword : nama menu yang ingin dicari
     */
    private void filterMenuList(String keyword) {
        filteredMenuList.clear();
        for (Menu menu : allMenuList) {
            if (menu.name().toLowerCase().contains(keyword.toLowerCase())) {
                switch (selectedCategory) {
                    case "", "All" -> filteredMenuList.add(menu);
                    case "Food" -> {
                        if (menu.category() instanceof Food) {
                            filteredMenuList.add(menu);
                        }
                    }
                    case "Beverage" -> {
                        if (menu.category() instanceof Beverage) {
                            filteredMenuList.add(menu);
                        }
                    }
                }

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