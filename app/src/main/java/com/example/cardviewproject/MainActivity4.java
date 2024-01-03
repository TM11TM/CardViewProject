package com.example.cardviewproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.cardviewproject.databinding.ActivityMain3Binding;
import com.example.cardviewproject.databinding.ActivityMain4Binding;

import java.util.ArrayList;

public class MainActivity4 extends AppCompatActivity {
    private ActivityMain4Binding binding;
    private ArrayList<String> listaCancion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main4);
        setContentView((binding = ActivityMain4Binding.inflate(getLayoutInflater())).getRoot());

        //Toolbar
        setSupportActionBar(binding.toolbar4);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        listaCancion = new ArrayList<>();
        listaCancion.add("HOBA HOBA SPIRIT WALKE CHAREB");
        listaCancion.add("Led Zeppelin - Whole Lotta Love");
        listaCancion.add("Michael Jackson - Smooth Criminal");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listaCancion);
        //Adaptamos la lista para que sea pulsable como los botones
        binding.listacanciones.setAdapter(adapter);
        //Hacer el onclick de los elementos de la lista
        binding.listacanciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                //Acceder al onclick de los elementos de la lista
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //Toast con la cadena introducida en el list
                    Toast.makeText(MainActivity4.this, listaCancion.get(position), Toast.LENGTH_LONG).show();
                    Intent i = new Intent(MainActivity4.this, MainActivity5.class);
                    i.putExtra("indice" , position );
                    startActivityForResult(i,0000);
                }
            });
    }
}