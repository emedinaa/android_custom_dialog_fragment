
# Como construir un Dialogo personalizado


En este breve tutorial veremos como crear un Dialogo personalizado

1 .  Lo primero es hacer el layout del dialogo.
```
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    tools:context=".fragments.CustomDialogFragment">
    <TextView
        android:layout_width="match_parent"
        android:layout_height="64dp"
        android:background="@color/yellow"
        android:text="@string/android_app"
        android:gravity="center"
        android:textSize="28sp"
        android:textStyle="bold"
        android:textColor="@color/white"
        android:contentDescription="@string/app_name" />
    <EditText
        android:id="@+id/eteUsername"
        android:inputType="textEmailAddress"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:layout_marginLeft="4dp"
        android:layout_marginRight="4dp"
        android:layout_marginBottom="4dp"
        android:hint="@string/username" />
    <EditText
        android:id="@+id/etePassword"
        android:inputType="textPassword"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="4dp"
        android:layout_marginLeft="4dp"
        android:layout_marginRight="4dp"
        android:layout_marginBottom="16dp"
        android:fontFamily="sans-serif"
        android:hint="@string/password"/>
</LinearLayout>

```

2 .  Creamos una interfaz para poder comunicar los eventos del dialogo con la Actividad que invoca este elemento.

```
public interface CustomDialogListener {

    void onAction(Object object);
    void onDialogPositive(Object object);
    void onDialogNegative(Object object);
}

```

3 .  Creamos un DialogFragment
```
@NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout

        View customView= inflater.inflate(R.layout.fragment_custom_dialog,null);
        eteUsername= (EditText) customView.findViewById(R.id.eteUsername);
        etePassword= (EditText) customView.findViewById(R.id.etePassword);

        builder.setView(customView)
                // Add action buttons
                .setPositiveButton(R.string.signin, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                        if(mListener!=null){
                            if(validateForm()) {
                                String message = String.format("username %s password %s",username,password);
                                Log.v(TAG, message);

                                mListener.onDialogPositive(message);
                            }
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                       CustomDialogFragment.this.getDialog().cancel();
                        if(mListener!=null){
                            mListener.onDialogNegative(null);
                        }
                    }
                });
        return builder.create();
    }
```

4 .  En la actividad invocamos al Dialogo
```
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
```
![](https://github.com/emedinaa/android_custom_dialog_fragment/blob/master/customdialog.gif?raw=true)


Referencia : [https://developer.android.com/guide/topics/ui/dialogs.html](https://developer.android.com/guide/topics/ui/dialogs.html)

