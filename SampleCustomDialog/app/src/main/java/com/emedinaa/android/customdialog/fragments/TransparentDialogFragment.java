package com.emedinaa.android.customdialog.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.emedinaa.android.customdialog.R;

/**
 * Created by eduardomedina on 24/01/17.
 */
public class TransparentDialogFragment extends DialogFragment {

    private static final String TAG = "CustomDialogF";
    private CustomDialogListener mListener;
    private String username;
    private String password;
    private EditText eteUsername,etePassword;
    private View tviCancel, tviOk;

    public TransparentDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CustomDialogListener) {
            mListener = (CustomDialogListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement CustomDialogListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout

        View customView= inflater.inflate(R.layout.fragment_transparent_dialog,null);
        eteUsername= (EditText) customView.findViewById(R.id.eteUsername);
        etePassword= (EditText) customView.findViewById(R.id.etePassword);
        tviCancel=  customView.findViewById(R.id.tviCancel);
        tviOk=  customView.findViewById(R.id.tviOk);

        builder.setView(customView);

        // Add action buttons
        tviCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();

                if(mListener!=null){
                    mListener.onDialogNegative(null);
                }
            }
        });

        tviOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // sign in the user ...
                if(mListener!=null){
                    if(validateForm()) {
                        dismiss();
                        String message = String.format("username %s password %s",username,password);
                        Log.v(TAG, message);

                        mListener.onDialogPositive(message);
                    }
                }
            }
        });

        return builder.create();
    }

    private boolean validateForm() {
        username= eteUsername.getText().toString().trim();
        password= etePassword.getText().toString().trim();

        if(username.isEmpty())return false;
        if(password.isEmpty())return false;
        return true;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    }
}
