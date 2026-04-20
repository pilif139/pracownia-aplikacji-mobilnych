package com.filip.zadanie20_04;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ImageDialogFragment extends DialogFragment {

    private static final String ARG_IMAGE = "image";

    public static ImageDialogFragment newInstance(int imageResId) {

        ImageDialogFragment fragment = new ImageDialogFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_IMAGE, imageResId);
        fragment.setArguments(args);

        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        View view = getLayoutInflater()
                .inflate(R.layout.image_dialog, null);

        ImageView imageView = view.findViewById(R.id.dialogImage);

        assert getArguments() != null;
        int imageRes = getArguments().getInt(ARG_IMAGE);
        imageView.setImageResource(imageRes);

        Dialog dialog = new Dialog(requireContext());
        dialog.setContentView(view);

        return dialog;
    }
}