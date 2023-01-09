package com.example.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.activities.R;
import com.example.activities.databinding.FragmentSupportBinding;
import com.example.adapters.MessageAdapter;
import com.example.adapters.ServiceAdapter;
import com.example.model.Message;
import com.example.services.MessageService;
import com.example.ui.activities.MainActivity;

public class SupportFragment extends Fragment {

    private FragmentSupportBinding binding;

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private MessageAdapter adapter;


    public SupportFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentSupportBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View context, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(context, savedInstanceState);

        recyclerView = context.findViewById(R.id.support_recycle_view);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new MessageAdapter();
        recyclerView.setAdapter(adapter);

        ImageButton button = context.findViewById(R.id.support_send_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    EditText editText = context.findViewById(R.id.message_edit_text);
                    String text = editText.getText().toString();
                    if (text.equals("")) {
                        Toast.makeText(view.getContext(),
                                getString(R.string.support_empty_message),
                                Toast.LENGTH_LONG).show();
                        return;
                    }

                    MessageService messageService = MessageService.getInstance();

                    messageService.addMessage(text, true);
                    messageService.addMessage(getString(R.string.support_text_message), false);
                    adapter.notifyDataSetChanged();
                    editText.setText("");
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}