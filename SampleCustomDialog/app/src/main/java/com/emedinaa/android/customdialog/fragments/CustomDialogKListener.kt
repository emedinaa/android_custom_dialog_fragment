package com.emedinaa.android.customdialog.fragments

interface CustomDialogKListener {

    fun onAction(obj: Any)
    fun onDialogPositive(obj: Any?)
    fun onDialogNegative(obj: Any?)
}