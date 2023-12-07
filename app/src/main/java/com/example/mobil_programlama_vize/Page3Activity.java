package com.example.mobil_programlama_vize;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


public class Page3Activity extends AppCompatActivity {


    private static final int SMS_PERMISSION_REQUEST_CODE = 123;
    private EditText phoneNumberEditText, messageEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,new String[] {android.Manifest.permission.SEND_SMS}, SMS_PERMISSION_REQUEST_CODE);
            }
        }
    }

    public void sendMessage(View view){
        String phoneNumber = phoneNumberEditText.getText().toString();
        String message = messageEditText.getText().toString();
        if(!phoneNumber.isEmpty() && !message.isEmpty()){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,new String[] {android.Manifest.permission.SEND_SMS}, SMS_PERMISSION_REQUEST_CODE);
                } else {
                    sendSMS(phoneNumber, message);
                }
            }
        } else {
            Toast.makeText(this, "Lütfen geçerli bir telefon numarası giriniz.", Toast.LENGTH_SHORT).show();
        }
    }


    private void sendSMS(String phoneNumber, String message){
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);

            Toast.makeText(this, "Mesaj gönderildi!", Toast.LENGTH_SHORT).show();
        } catch (Exception e){
            Toast.makeText(this, "Mesaj gönderilirken bir hata oluştu " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }



}
