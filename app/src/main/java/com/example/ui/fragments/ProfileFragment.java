package com.example.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.activities.R;
import com.example.activities.databinding.FragmentProfileBinding;
import com.example.model.User;
import com.example.services.Session;
import com.example.services.UserManager;
import com.example.ui.activities.MainActivity;
import com.example.ui.activities.RegisterActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;

public class ProfileFragment extends Fragment {

    private TextView emailTextView;
    private TextView phoneTextView;

    private TextInputEditText emailEditText;
    private TextInputEditText phoneEditText;
    private TextInputEditText passwordEditText;

    private FragmentProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        emailTextView = view.findViewById(R.id.email_text_view);
        phoneTextView = view.findViewById(R.id.phone_text_view);

        emailEditText = view.findViewById(R.id.email_edit_text);
        phoneEditText = view.findViewById(R.id.phone_edit_text);
        passwordEditText = view.findViewById(R.id.password_edit_text);

        User user = Session.getCurrentUser();

        emailEditText.setText(user.getEmail());
        phoneEditText.setText(user.getPhoneNumber());

        emailTextView.setText(user.getEmail());

        String number = user.getPhoneNumber();



        phoneTextView.setText(formatNumber(number));

        Button button = view.findViewById(R.id.button_update);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                User user = Session.getCurrentUser();

                if (!email.equals(user.getEmail())){
                    if (!email.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")){
                        Toast.makeText(getActivity(),
                                getString(R.string.registration_invalid_data_presented),
                                Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (UserManager.getInstance().existsByEmail(email)){
                        Toast.makeText(getActivity(),
                                getString(R.string.registration_identity_already_taken),
                                Toast.LENGTH_LONG).show();
                        return;
                    }

                    user.setEmail(email);
                }

                if (!phone.equals(user.getPhoneNumber())){
                    if (!phone.matches("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$")){
                        Toast.makeText(getActivity(),
                                getString(R.string.registration_invalid_data_presented),
                                Toast.LENGTH_LONG).show();
                        return;
                    }

                    user.setPhoneNumber(phone.replace("-", ""));
                }

                if (!password.equals("") && !password.equals(user.getPassword())){
                    if (password.length() < 8){
                        Toast.makeText(getActivity(),
                                getString(R.string.profile_password_length_error),
                                Toast.LENGTH_LONG).show();
                        return;
                    }

                    user.setPassword(password);
                }

                Toast.makeText(getActivity(),
                        getString(R.string.profile_data_updated),
                        Toast.LENGTH_LONG).show();

                emailTextView.setText(email);
                phoneTextView.setText(formatNumber(phone));

                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main);
                MainActivity mainActivity = (MainActivity) getActivity();
                NavigationView navView = mainActivity.binding.navView;

                View header = navView.getHeaderView(0);
                TextView headerEmailTextView = header.findViewById(R.id.email_text_view);
                TextView headerPhoneTextView = header.findViewById(R.id.phone_text_view);

                headerEmailTextView.setText(email);
                headerPhoneTextView.setText(formatNumber(phone));
            }
        });
    }

    public String formatNumber(String number){
        int shift = 0;

        if (number.substring(0,1).equals("+"))
            shift = 1;

        String formattedNumber = number.substring(0, 1 + shift);

        formattedNumber = formattedNumber + " (" + number.substring(1 + shift, 4 + shift) + ") ";
        formattedNumber = formattedNumber + number.substring(4 + shift, 7 + shift) + "-";
        formattedNumber = formattedNumber + number.substring(7 + shift, 9 + shift) + "-";
        formattedNumber = formattedNumber + number.substring(9 + shift, 11 + shift);
        return formattedNumber;
    }
}