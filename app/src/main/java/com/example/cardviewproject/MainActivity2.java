package com.example.cardviewproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.cardviewproject.databinding.ActivityMain2Binding;
import com.example.cardviewproject.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    private ActivityMain2Binding binding;
    private ArrayList<String> listaLibros;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main2);
        setContentView((binding = ActivityMain2Binding.inflate(getLayoutInflater())).getRoot());
        //Toolbar
        setSupportActionBar(binding.toolbarAcividad2);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        listaLibros = new ArrayList<>();
        listaLibros.add("Libro 1");
        listaLibros.add("Libro 2");
        listaLibros.add("Libro 3");
        listaLibros.add("Libro 4");
        listaLibros.add("Libro 5");
        listaLibros.add("Libro 6");
        listaLibros.add("Libro 7");
        listaLibros.add("Libro 8");
        listaLibros.add("Libro 9");
        listaLibros.add("Libro 10");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listaLibros);
        //Adaptamos la lista para que sea pulsable como los botones
        binding.lista.setAdapter(adapter);
        //Hacer el onclick de los elementos de la lista
        binding.lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //Acceder al onclick de los elementos de la lista
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast con la cadena introducida en el list
                Toast.makeText(MainActivity2.this, listaLibros.get(position), Toast.LENGTH_LONG).show();
            }
        });
    }
}