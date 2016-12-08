package com.example.ivalion.preferencias;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    public static final String PREFERENCIAS = "Mis preferencias";
    EditText nombre,dni,fecha;
    RadioButton sexoHombre,sexoMujer;
    Button guardar,boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = (EditText)findViewById(R.id.et_nombre);
        dni = (EditText)findViewById(R.id.et_dni);
        fecha = (EditText)findViewById(R.id.et_fecha);
        sexoHombre = (RadioButton)findViewById(R.id.rbH);
        sexoMujer = (RadioButton)findViewById(R.id.rbM);

        guardar = (Button)findViewById(R.id.btn_guardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences misSP = getSharedPreferences(PREFERENCIAS, Activity.MODE_PRIVATE);

                SharedPreferences.Editor editor = misSP.edit();

                editor.putString("nombre",nombre.getText().toString());
                editor.putString("dni",dni.getText().toString());
                editor.putString("fecha",fecha.getText().toString());
                editor.putBoolean("sexo",sexoHombre.isChecked());

                editor.commit();
                nombre.setText("");
                dni.setText("");
                fecha.setText("");
            }
        });

        boton = (Button)findViewById(R.id.btn_boton);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences misSP = getSharedPreferences(PREFERENCIAS, Activity.MODE_PRIVATE);


                nombre.setText(misSP.getString("nombre",""));
                dni.setText(misSP.getString("dni",""));
                fecha.setText(misSP.getString("fecha",""));

                if (misSP.getBoolean("sexo",true))
                    sexoHombre.setChecked(true);
                else
                    sexoMujer.setChecked(true);


            }
        });

    //inicio la aplicacion con las preferencias que hubiera anteriormente introducidas
        SharedPreferences misSP = getSharedPreferences(PREFERENCIAS, Activity.MODE_PRIVATE);
        nombre.setText(misSP.getString("nombre",""));
        dni.setText(misSP.getString("dni",""));
        fecha.setText(misSP.getString("fecha",""));
        if (misSP.getBoolean("sexo",true))
            sexoHombre.setChecked(true);
        else
            sexoMujer.setChecked(true);
    }
}
