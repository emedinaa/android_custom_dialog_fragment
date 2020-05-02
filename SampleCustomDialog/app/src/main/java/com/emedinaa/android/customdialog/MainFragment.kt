package com.emedinaa.android.customdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.emedinaa.android.customdialog.fragments.CustomDialogKFragment
import com.emedinaa.android.customdialog.fragments.CustomDialogKListener
import com.emedinaa.android.customdialog.fragments.TransparentDialogKFragment
import kotlinx.android.synthetic.main.fragment_main.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment(), CustomDialogKListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    private fun showCustomDialog() {
        val dialog = CustomDialogKFragment()
        dialog.setTargetFragment(this,0)
        dialog.show(requireFragmentManager(), "CustomDialogFragment")
    }

    private fun showTransparentDialog() {
        val dialog = TransparentDialogKFragment()
        dialog.setTargetFragment(this,0)
        dialog.show(requireFragmentManager(), "TransparentDialogFragment")
    }

    override fun onDialogNegative(obj: Any?) {
        Toast.makeText(requireContext(), "Custom Dialog ${getString(R.string.cancel)}", Toast.LENGTH_SHORT).show()
    }

    override fun onDialogPositive(obj: Any?) {
        Toast.makeText(requireContext(), "Custom Dialog ${getString(R.string.signin)}  $obj", Toast.LENGTH_SHORT).show()
    }

    override fun onAction(obj: Any) {}

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btnDialog.setOnClickListener {
            //showCustomDialog()
            showTransparentDialog()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                MainFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
