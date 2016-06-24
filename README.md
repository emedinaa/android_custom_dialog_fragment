# android_custom_dialog_fragment
DialogFragment  with custom layout

En este breve tutorial vermos como crear un Dialogo personalizado

1.  Lo primero es hacer el layout del dialogo.
```
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
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
        android:id="@+id/username"
        android:inputType="textEmailAddress"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:layout_marginLeft="4dp"
        android:layout_marginRight="4dp"
        android:layout_marginBottom="4dp"
        android:hint="@string/username" />
    <EditText
        android:id="@+id/password"
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

2. Creamos una interfaz para poder comunicar los eventos del dialogo con la Actividad que invoca este elemento.

```
public interface CustomDialogListener {

    void onAction(Object object);
    void onDialogPositive(Object object);
    void onDialogNegative(Object object);
}

```

3. Creamos un DialogFragment
```
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.fragment_custom_dialog, null))
                // Add action buttons
                .setPositiveButton(R.string.signin, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                        if(mListener!=null){
                            mListener.onDialogPositive(null);
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

4. En la actividad invocamos al Dialogo
```
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
        Toast.makeText(this,"Custom Dialog "+getString(R.string.signin),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDialogNegative(Object object) {
        Toast.makeText(this,"Custom Dialog "+getString(R.string.cancel),Toast.LENGTH_SHORT).show();
    }
}

```

![screenshot01](https://github.com/emedinaa/android_custom_dialog_fragment/blob/master/screenshot01.png)

![screenshot02](https://github.com/emedinaa/android_custom_dialog_fragment/blob/master/screenshot02.png)

Referencia : [https://developer.android.com/guide/topics/ui/dialogs.html](https://developer.android.com/guide/topics/ui/dialogs.html)

