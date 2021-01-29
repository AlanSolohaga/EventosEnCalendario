package com.example.pruebacalendario;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    Button btnCalendario;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.edit);
        btnCalendario = findViewById(R.id.btnCalendario);
        Date hoy = new Date();
        Date mañana = new Date(hoy.getTime()+ TimeUnit.DAYS.toMillis(1));
        final FechaSalon fecha1 = new FechaSalon(mañana,EnumEstadoFecha.LIBRE);



        btnCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fecha1.getEstado().equals(EnumEstadoFecha.LIBRE)){

                    Calendar calendar = Calendar.getInstance();

                    calendar.set(Calendar.DAY_OF_MONTH,fecha1.getFecha().getDate());
                    calendar.set(Calendar.MONTH,fecha1.getFecha().getMonth());
                    calendar.set(Calendar.YEAR,fecha1.getFecha().getYear()+1900);

                    calendar.set(Calendar.HOUR_OF_DAY,fecha1.getFecha().getHours());
                    calendar.set(Calendar.MINUTE,fecha1.getFecha().getMinutes());


                    Intent intent = new Intent(Intent.ACTION_EDIT);
                    intent.setType("vnd.android.cursor.item/event");

                    intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,calendar.getTimeInMillis());
                    intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,calendar.getTimeInMillis()+60*60*1000);

                    startActivity(intent);

                }else{
                    alert(fecha1);
                }
            }
        });

    }

    private void alert(FechaSalon fecha) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String cadena = format.format(fecha.getFecha());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(cadena+" "+fecha.getEstado().name()).setTitle("ERROR").setNeutralButton("BOTON", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

}
