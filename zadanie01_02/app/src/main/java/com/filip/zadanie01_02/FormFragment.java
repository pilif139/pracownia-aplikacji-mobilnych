package com.filip.zadanie01_02;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class FormFragment extends Fragment {

    private TextInputLayout emailInputLayout;
    private TextInputLayout firstNameInputLayout;
    private TextInputLayout lastNameInputLayout;
    private TextInputEditText emailEditText;
    private TextInputEditText firstNameEditText;
    private TextInputEditText lastNameEditText;
    private Button submitButton;

    public interface OnFormSubmitListener {
        void onFormSubmit(String email, String firstName, String lastName);
    }

    private OnFormSubmitListener listener;

    public FormFragment() {
    }

    public static FormFragment newInstance() {
        return new FormFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_form, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emailInputLayout = view.findViewById(R.id.emailInputLayout);
        firstNameInputLayout = view.findViewById(R.id.firstNameInputLayout);
        lastNameInputLayout = view.findViewById(R.id.lastNameInputLayout);
        emailEditText = view.findViewById(R.id.emailEditText);
        firstNameEditText = view.findViewById(R.id.firstNameEditText);
        lastNameEditText = view.findViewById(R.id.lastNameEditText);
        submitButton = view.findViewById(R.id.submitButton);

        if (getActivity() instanceof OnFormSubmitListener) {
            listener = (OnFormSubmitListener) getActivity();
        }

        submitButton.setOnClickListener(v -> validateAndSubmit());
    }

    private void validateAndSubmit() {
        emailInputLayout.setError(null);
        firstNameInputLayout.setError(null);
        lastNameInputLayout.setError(null);

        String email = emailEditText.getText() != null ? emailEditText.getText().toString().trim() : "";
        String firstName = firstNameEditText.getText() != null ? firstNameEditText.getText().toString().trim() : "";
        String lastName = lastNameEditText.getText() != null ? lastNameEditText.getText().toString().trim() : "";

        boolean isValid = true;

        if (TextUtils.isEmpty(email)) {
            emailInputLayout.setError("Pole e-mail nie może być puste");
            isValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailInputLayout.setError("Niepoprawny adres e-mail");
            isValid = false;
        }

        if (TextUtils.isEmpty(firstName)) {
            firstNameInputLayout.setError("Pole imię nie może być puste");
            isValid = false;
        }

        if (TextUtils.isEmpty(lastName)) {
            lastNameInputLayout.setError("Pole nazwisko nie może być puste");
            isValid = false;
        }

        if (isValid && listener != null) {
            listener.onFormSubmit(email, firstName, lastName);
        }
    }
}
