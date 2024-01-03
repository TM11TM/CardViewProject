package com.example.cardviewproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.SeekBar;

import com.example.cardviewproject.databinding.ActivityMain4Binding;
import com.example.cardviewproject.databinding.ActivityMain5Binding;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MainActivity5 extends AppCompatActivity {

    private ActivityMain5Binding binding;
    private ArrayList<Musica> canciones;
    private MediaPlayer mediaplayer;
    private Runnable runnable;
     Handler handler = new Handler();
    private int cont = 0,  posicion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main4);
        setContentView((binding = ActivityMain5Binding.inflate(getLayoutInflater())).getRoot());

        //Crear lista de canciones
        canciones = new ArrayList<>();
        Musica cancion1 = new Musica(R.string.cancion1,R.drawable.portada2, R.raw.mj_smooth_criminal);
        Musica cancion2 = new Musica(R.string.cancion2,  R.drawable.led_zeppelin, R.raw.led_zeppelin_whole_lotta_love);
        Musica cancion3 = new Musica(R.string.cancion3,R.drawable.mj_smoothcriminal, R.raw.hoba_hoba_spirit);
        canciones.add(cancion1);
        canciones.add(cancion2);
        canciones.add(cancion3);

        //Cogemos el intent
        Intent intent = getIntent();
         posicion = intent.getIntExtra("indice" , 0);

        //Inicializar media player
        mediaplayer = MediaPlayer.create(this,canciones.get(posicion).getSonido());
        binding.tvTituloCancion.setText(canciones.get(posicion).getNombreCancion());
        binding.ivCancion.setImageResource(canciones.get(posicion).getImg());
        binding.btnIniciar.setImageResource(R.drawable.pausa);

        runnable = new Runnable(){
            @Override
            public void run(){
                //sincronizamos la barra con la cancion
                binding.seekBar.setProgress(mediaplayer.getCurrentPosition());

                //Marcamos el delay
                handler.postDelayed(this, 500);
            }
        };

        //duracion
        int duration = mediaplayer.getDuration();
        //Convertimos a milisegundos
         String sDuracion = convertFormat(duration);
         binding.duracion.setText(sDuracion);
         mediaplayer.start();
         binding.seekBar.setMax(mediaplayer.getDuration());
         handler.postDelayed(runnable,0);


        binding.btnIniciar.setOnClickListener(view -> {
            binding.seekBar.setMax(mediaplayer.getDuration());
            handler.postDelayed(runnable,0);
            if(cont % 2 == 0){
                mediaplayer.pause();
                binding.btnIniciar.setImageResource(R.drawable.reproducir);
                cont++;
            }else{
                mediaplayer.start();
                binding.btnIniciar.setImageResource(R.drawable.pausa);
                cont++;
            }
        });

        binding.btnCancionAlante.setOnClickListener(view -> {
            mediaplayer.stop();
            if( posicion < canciones.size()-1){
                posicion++;
                changeMusicFormward();
                mediaplayer.seekTo(0);
            }
            else{
                posicion =0;
                changeMusicFormward();
                mediaplayer.seekTo(0);
            }
        });

        binding.btPausa.setOnClickListener(view -> {
            mediaplayer.pause();
            // Reiniciar la canción desde el principio
            mediaplayer.seekTo(0);
            binding.seekBar.setMax(mediaplayer.getDuration());
            binding.seekBar.setProgress(0);
            handler.postDelayed(runnable,0);

            handler.postDelayed(runnable, 0);
            binding.btnIniciar.setImageResource(R.drawable.reproducir);
            cont++;

            // Detener el handler
            handler.removeCallbacks(runnable);
        });

        binding.btnCancionAtras.setOnClickListener(view -> {
            mediaplayer.pause();
            if( posicion == 0){
                mediaplayer.seekTo(0);
                mediaplayer.start();
            }
            else{
                posicion--;
                changeMusicFormward();
                mediaplayer.seekTo(0);
            }
        });


        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b){
                    mediaplayer.seekTo(i);
                }
                binding.posicionCancion.setText(convertFormat(mediaplayer.getCurrentPosition()));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        mediaplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.pause();
                if(posicion<canciones.size()-1){
                    posicion++;
                    changeMusicFormward();
                    mediaplayer.seekTo(0);
                }else {
                    posicion = 0;
                    changeMusicFormward();
                    mediaplayer.seekTo(0);
                }
            }
        });
    }

    public void  onBackPressed(){
        if (mediaplayer.isPlaying()){
            mediaplayer.pause();
        }
        mediaplayer.release();
    }
    private void changeMusicFormward(){
        mediaplayer = MediaPlayer.create(this,canciones.get(posicion).getSonido());
        binding.tvTituloCancion.setText(canciones.get(posicion).getNombreCancion());
        binding.ivCancion.setImageResource(canciones.get(posicion).getImg());

        int duracion = mediaplayer.getDuration();
        String sDuracion = convertFormat(duracion);
        binding.posicionCancion.setText("00:00");
        binding.duracion.setText(sDuracion);
        mediaplayer.start();
        binding.seekBar.setMax(mediaplayer.getDuration());
        handler.postDelayed(runnable,0);
    }

    @SuppressLint("DefaultLocale")
    private String convertFormat(int duration){
        return  String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(duration),
                TimeUnit.MILLISECONDS.toSeconds(duration)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)));
    }
}
