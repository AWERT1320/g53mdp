package com.example.martinservice3;

import android.util.Log;

public class Counter extends Thread implements Runnable
{
    public boolean direction = true;
    public int count = 0;
    public boolean running = true;

    public Counter()
    {
        this.start();
    }

    public void run()
    {
        while(this.running)
        {
            try {Thread.sleep(2000);} catch(Exception e) {return;}

            if(direction)
                count++;
            else
                count--;

            Log.d("g53mdp", "counter " + count);
        }

        Log.d("g53mdp", "counter thread exiting");
    }
}
