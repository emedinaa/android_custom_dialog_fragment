package com.emedinaa.android.customdialog.fragments;

/**
 * Created by em on 24/06/16.
 */
public interface CustomDialogListener {

    void onAction(Object object);
    void onDialogPositive(Object object);
    void onDialogNegative(Object object);
}
