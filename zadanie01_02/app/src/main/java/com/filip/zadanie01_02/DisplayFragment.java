package com.filip.zadanie01_02;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DisplayFragment extends Fragment {

    private static final String ARG_EMAIL = "email";
    private static final String ARG_FIRST_NAME = "firstName";
    private static final String ARG_LAST_NAME = "lastName";

    private String email;
    private String firstName;
    private String lastName;

    public DisplayFragment() {
    }

    public static DisplayFragment newInstance(String email, String firstName, String lastName) {
        DisplayFragment fragment = new DisplayFragment();
        Bundle args = new Bundle();
        args.putString(ARG_EMAIL, email);
        args.putString(ARG_FIRST_NAME, firstName);
        args.putString(ARG_LAST_NAME, lastName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            email = getArguments().getString(ARG_EMAIL);
            firstName = getArguments().getString(ARG_FIRST_NAME);
            lastName = getArguments().getString(ARG_LAST_NAME);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_display, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView emailValueTextView = view.findViewById(R.id.emailValueTextView);
        TextView firstNameValueTextView = view.findViewById(R.id.firstNameValueTextView);
        TextView lastNameValueTextView = view.findViewById(R.id.lastNameValueTextView);

        emailValueTextView.setText(email);
        firstNameValueTextView.setText(firstName);
        lastNameValueTextView.setText(lastName);
    }
}
