package com.example.mobil_programlama_vize;
import android.app.ActivityOptions;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button convertor,random, sms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        convertor = findViewById(R.id.convertor);
        random = findViewById(R.id.random);
        sms = findViewById(R.id.sms);

        convertor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openPageWithTransition(Page1Activity.class);
            }
        });
        random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openPageWithTransition(Page2Activity.class);
            }
        });
        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPageWithTransition(Page3Activity.class);
            }
        });

    }


    private void openPageWithTransition(Class<?> cls) {
        try {
            Intent intent = new Intent(this, cls);
            ActivityOptions options = ActivityOptions.makeCustomAnimation(this, R.anim.slide_in_right, R.anim.slide_in_left);
            startActivity(intent, options.toBundle());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}