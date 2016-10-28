package com.emedinaa.android.customdialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.emedinaa.android.customdialog.fragments.CustomDialogFragment;
import com.emedinaa.android.customdialog.fragments.CustomDialogListener;

public class MainActivity extends AppCompatActivity  implements CustomDialogListener{

    private Button btnDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ui();

    }

    private void ui() {

        btnDialog= (Button) findViewById(R.id.btnDialog);
        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
            }
        });
    }

    private void showCustomDialog() {
        CustomDialogFragment dialog = new CustomDialogFragment();
        dialog.show(getSupportFragmentManager(), "CustomDialogFragment");

    }

    /**
     * COMUNICACION ENTRE EL DIALOGO CON LA ACTIVIDAD
     * @param object
     */
    @Override
    public void onAction(Object object) {

    }

    @Override
    public void onDialogPositive(Object object) {
        Toast.makeText(this,"Custom Dialog "+  getString(R.string.signin)+" "+object.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDialogNegative(Object object) {
        Toast.makeText(this,"Custom Dialog "+getString(R.string.cancel),Toast.LENGTH_SHORT).show();
    }
}
