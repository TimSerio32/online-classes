package serio.tim.android.com.teatimer.Timers;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import serio.tim.android.com.teatimer.R;

public class GreenTeaTimer extends AppCompatActivity {

    TextView greenTimerText;
    SeekBar greenSeekBar;
    Button greenControllerBtn;
    boolean counterIsActive = false;
    CountDownTimer countDownTimer;

    public void resetTimer() {
        greenTimerText.setText("3:00");
        greenSeekBar.setProgress(1800);
        countDownTimer.cancel();
        greenControllerBtn.setText("Start");
        greenSeekBar.setEnabled(true);
        counterIsActive = false;
    }

    public void updateTimer(int secondsLeft) {
        int minutes = (int) secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;

        String secondsString = Integer.toString(seconds);
        if(seconds <= 9) {
            secondsString = "0" + secondsString;
        }
        greenTimerText.setText(Integer.toString(minutes) + ":" + secondsString);
    }

    public void pauseTimer() {
        countDownTimer.cancel();
        greenSeekBar.setEnabled(true);
    }

    public void controlTimer(View view) {
        if(counterIsActive == false) {
            counterIsActive = true;
            greenSeekBar.setEnabled(false);
            greenControllerBtn.setText("Stop");

            countDownTimer = new CountDownTimer(greenSeekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    resetTimer();
                    //MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    //mediaPlayer.start();
                }
            }.start();
        } else {
            //resetTimer();
            countDownTimer = new CountDownTimer(greenSeekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    resetTimer();
                    //MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    //mediaPlayer.start();
                }
            }.start();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_tea_timer);

        greenTimerText = (TextView)findViewById(R.id.greenTimerText);
        greenSeekBar = (SeekBar)findViewById(R.id.greenSeekBar);
        greenControllerBtn = (Button)findViewById(R.id.greenControllerBtn);

        greenSeekBar.setMax(180);
        greenSeekBar.setProgress(180);

        greenSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        greenControllerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(greenControllerBtn.getText().toString().equals("Start")) {
                    greenControllerBtn.setText("Stop");
                    controlTimer(view);
                } else if(greenControllerBtn.getText().toString().equals("Stop")) {
                    greenControllerBtn.setText("Start");
                    pauseTimer();
                }
            }
        });
    }
}
