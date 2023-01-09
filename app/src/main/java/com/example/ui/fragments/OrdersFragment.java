package com.example.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.activities.R;
import com.example.activities.databinding.FragmentOrdersBinding;
import com.example.activities.databinding.FragmentServicesBinding;
import com.example.activities.databinding.FragmentSupportBinding;
import com.example.adapters.OrdersAdapter;
import com.example.adapters.ServiceAdapter;
import com.example.model.Order;

public class OrdersFragment extends Fragment implements View.OnClickListener {


    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private OrdersAdapter adapter;

    private FragmentOrdersBinding binding;

    public OrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentOrdersBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.orders_recycler_view);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new OrdersAdapter();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {

    }
}