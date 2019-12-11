package com.emedinaa.android.customdialog.fragments

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

import com.emedinaa.android.customdialog.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val TAG = "CustomDialogF"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [TransparentDialogKFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [TransparentDialogKFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TransparentDialogKFragment : DialogFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: CustomDialogKListener? = null

    private var username: String? = null
    private var password: String? = null

    private var eteUsername: EditText? = null
    private var etePassword:EditText? = null
    private var tviCancel: View? = null
    private var tviOk:View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transparent_dialog_k, container, false)
    }



    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is CustomDialogKListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement CustomDialogKListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        activity?.let {
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = it.layoutInflater

            val customView = inflater.inflate(R.layout.fragment_transparent_dialog, null)
            eteUsername = customView.findViewById(R.id.eteUsername) as EditText
            etePassword = customView.findViewById(R.id.etePassword) as EditText
            tviCancel = customView.findViewById(R.id.tviCancel)
            tviOk = customView.findViewById(R.id.tviOk)

            builder.setView(customView)

            // Add action buttons
            tviCancel?.setOnClickListener {
                dismiss()
                listener?.onDialogNegative(null)

            }

            tviOk?.setOnClickListener{
                // sign in the user ...
                    if (validateForm()) {
                        dismiss()
                        val message = "username $username password $password"
                        Log.v(TAG, message)

                        listener?.onDialogPositive(message)
                    }

            }

            return builder.create()
        }

        return super.onCreateDialog(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    private fun validateForm(): Boolean {
        username = eteUsername?.text.toString().trim()
        password = etePassword?.text.toString().trim()

        if (username.isNullOrEmpty()) return false

        if (password.isNullOrEmpty()) return false

        return true
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TransparentDialogKFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                TransparentDialogKFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
