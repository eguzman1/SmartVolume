package com.example.soundsmart;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private SeekBar volumeSeekBar = null;
    private SeekBar volumeSeekBar1 = null;
    private AudioManager audioManager = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initControlsForMax();
        initControlsForMin();
    }

    private void initControlsForMin(){
        volumeSeekBar1 = findViewById(R.id.seekBar2);
        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        volumeSeekBar1.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        volumeSeekBar1.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));


        volumeSeekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int curr = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                if(curr < progress){
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
                }
                TextView minVolText = findViewById(R.id.minVolText);
                minVolText.setText("Minimum Volume: "+progress+"/15");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private void initControlsForMax(){
        try{
            volumeSeekBar = findViewById(R.id.seekBar);
            audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
            volumeSeekBar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
            volumeSeekBar.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));


            volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    int cur = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                    if(cur > progress) {
                        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
                    }
                    TextView maxVolText = findViewById(R.id.maxVolText);
                    maxVolText.setText("Maximum Volume: "+progress+"/15");
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

        }catch(Exception e ){
            e.printStackTrace();
        }

    }


}
