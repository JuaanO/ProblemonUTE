package com.problemonute.problemonute.views;

import android.databinding.DataBindingUtil;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.content.Intent;
import com.problemonute.problemonute.R;
import com.problemonute.problemonute.databinding.ActivityProblemBinding;
import com.problemonute.problemonute.viewmodels.ProblemViewModel;
import io.github.kexanie.library.*;



public class ProblemActivity extends AppCompatActivity {
    TextView textViewTime;
    final CounterClass timer = new CounterClass(60000,1000);
    MathView answer1View;
    MathView answer2View;
    MathView answer3View;
    MathView answer4View;
    ProblemViewModel pvm;
    String message;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityProblemBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_problem);
        
        Intent intent = getIntent();
        message = intent.getStringExtra("KEY_MARKER");

        textViewTime = (TextView) findViewById(R.id.textViewTime);
        answer1View = (MathView) findViewById(R.id.answer1);
        answer2View = (MathView) findViewById(R.id.answer2);
        answer3View = (MathView) findViewById(R.id.answer3);
        answer4View = (MathView) findViewById(R.id.answer4);

        if(pvm == null) pvm = new ProblemViewModel();
        pvm.loadQuestion();
        pvm.loadUser();
        binding.setProblemVM(pvm);

        textViewTime.setText("01:00:00");
        timer.start();

        answer1View.setOnTouchListener(
                (v, event) -> {
                    pvm.onAnswerClick(getApplicationContext(),1);
                    returnMarker();
                    timer.cancel();
                    return true;
                }

        );

        answer2View.setOnTouchListener(
                (v, event) -> {
                    pvm.onAnswerClick(getApplicationContext(),2);
                    returnMarker();
                    timer.cancel();
                    return true;
                }
        );

        answer3View.setOnTouchListener(
                (v, event) -> {
                    pvm.onAnswerClick(getApplicationContext(),3);
                    returnMarker();
                    timer.cancel();
                    return true;
                }
        );

        answer4View.setOnTouchListener(
                (v, event) -> {
                    pvm.onAnswerClick(getApplicationContext(),4);
                    returnMarker();
                    timer.cancel();
                    return true;
                }
        );



    }

    @Override
    public void onBackPressed() {
        returnMarker();
        pvm.onAnswerClick(getApplicationContext(),6);
        timer.cancel();
        super.onBackPressed();
    }

    public void returnMarker() {
        Intent intent = new Intent();
        intent.putExtra("KEY_MARKER_RETURN", message);
        setResult(RESULT_OK, intent);
        finish();
    }

    public class CounterClass extends CountDownTimer{

        private CounterClass(long millisInFuture, long countDownInterval){
            super(millisInFuture,countDownInterval);
        }
        @Override
        public void onTick(long millisUntilFinished){
            long millis = millisUntilFinished;
            String hms = String.format("%02d:%02d:%02d", java.util.concurrent.TimeUnit.MILLISECONDS.toHours(millis),
                    java.util.concurrent.TimeUnit.MILLISECONDS.toMinutes(millis) - java.util.concurrent.TimeUnit.HOURS.toMinutes(java.util.concurrent.TimeUnit.MILLISECONDS.toHours(millis)),
                    java.util.concurrent.TimeUnit.MILLISECONDS.toSeconds(millis) - java.util.concurrent.TimeUnit.MINUTES.toSeconds(java.util.concurrent.TimeUnit.MILLISECONDS.toMinutes(millis)));
            textViewTime.setText(hms);
        }
        @Override
        public void onFinish(){
            pvm.onAnswerClick(getApplicationContext(),5);
            returnMarker();
            finish();
        }


}

}
