package ru.kev163rus.countandearn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.os.Handler;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {

    ProgressBar pbRun;
    Handler h;
    final int pbRun_Max = 100;
    int pbRun_Count = 0;
    int timeOut = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        h = new Handler();

        pbRun = findViewById(R.id.progressBar);
        pbRun.setProgress(0);

        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    for (pbRun_Count = 1; pbRun_Count < pbRun_Max; pbRun_Count++) {
                        TimeUnit.MILLISECONDS.sleep(timeOut);
                        // обновляем ProgressBar
                        h.post(setProgress);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();

    }

    Runnable setProgress = new Runnable() {
        public void run() {
            pbRun.setProgress(pbRun_Count);
        }
    };
}
