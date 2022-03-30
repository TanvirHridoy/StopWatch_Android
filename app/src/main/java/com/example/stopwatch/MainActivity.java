package com.example.stopwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import java.util.Locale;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int seconds = 0;
    private boolean running;
    private boolean wasRunning;
   // private final Toast toast = Toast.makeText(this, "Text here", Toast.LENGTH_LONG);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //toaster starts
        Toast toast = Toast.makeText(this, "Activity Started", Toast.LENGTH_LONG);
        toast.show();
        //toaster ends
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState!=null)
        {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning =savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning",wasRunning);
    }

    @Override
    protected void onStop()
    {
        Toast toast = Toast.makeText(this, "Activity Stopped", Toast.LENGTH_LONG);
        toast.show();
        //toaster needed
        super.onStop();
        wasRunning = running;
        running = false;
    }
    @Override
    protected void onStart()
    {
        Toast toast = Toast.makeText(this, "Activity Started", Toast.LENGTH_LONG);
        toast.show();
        super.onStart();
        if(wasRunning){
            running = true;
        }
    }
    @Override
    protected void onResume(){
        super.onResume();
        Toast toast = Toast.makeText(this, "Activity Resumed", Toast.LENGTH_LONG);
        toast.show();
        //toaster
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        Toast toast = Toast.makeText(this, "Activity Restarted", Toast.LENGTH_LONG);
        toast.show();
        //toaster
    }
    @Override
    protected void onPause(){
        super.onPause();
        Toast toast = Toast.makeText(this, "Activity Paused", Toast.LENGTH_LONG);
        toast.show();
        //toaster
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast toast = Toast.makeText(this, "Activity Destroyed", Toast.LENGTH_LONG);
        toast.show();

    }

    public void onClickStart(View view)
    {

        Toast toast = Toast.makeText(this, "Stop Watch Started", Toast.LENGTH_LONG);
        toast.show();
        running=true;
    }
    public void onClickStop(View view)
    {
        Toast toast = Toast.makeText(this, "Stop Watch Paused", Toast.LENGTH_LONG);
        toast.show();
        running=false;
    }
    public void onClickReset(View view)
    {
        Toast toast = Toast.makeText(this, "Stop Watch has been Reset", Toast.LENGTH_LONG);
        toast.show();
        running=false;
        seconds = 0;
    }
    private void runTimer()
    {
        final TextView timeView = (TextView)findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable(){
            public void run(){
                int hours = seconds/3600;
                int minutes = (seconds % 3600)/60;
                int secs = seconds % 60;

                String time = String.format(Locale.getDefault(),"%d:%02d:%02d",hours,minutes,secs);
                timeView.setText(time);
                if(running){
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }

}