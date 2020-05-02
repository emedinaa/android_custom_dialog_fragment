package com.emedinaa.android.customdialog

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.emedinaa.android.customdialog.fragments.*
import kotlinx.android.synthetic.main.activity_main_k.*

class MainKActivity : AppCompatActivity(),CustomDialogKListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_k)

        btnDialog.setOnClickListener {
            //showCustomDialog()
            showTransparentDialog()
        }
    }


    private fun showCustomDialog() {
        val dialog = CustomDialogKFragment()
        dialog.show(supportFragmentManager, "CustomDialogFragment")
    }

    private fun showTransparentDialog() {
        val dialog = TransparentDialogKFragment()
        dialog.show(supportFragmentManager, "TransparentDialogFragment")
    }

    /**
     * COMUNICACION ENTRE EL DIALOGO CON LA ACTIVIDAD
     * @param object
     */

    override fun onAction(obj: Any) {

    }

    override fun onDialogNegative(obj: Any?) {
        Toast.makeText(this, "Custom Dialog ${getString(R.string.cancel)}", Toast.LENGTH_SHORT).show()
    }

    override fun onDialogPositive(obj: Any?) {
        Toast.makeText(this, "Custom Dialog ${getString(R.string.signin)}  $obj", Toast.LENGTH_SHORT).show()
    }
}
