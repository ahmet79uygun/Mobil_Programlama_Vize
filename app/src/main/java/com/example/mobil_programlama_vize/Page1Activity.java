package com.example.mobil_programlama_vize;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;


public class Page1Activity extends AppCompatActivity {

    private EditText decimalInput;
    private Spinner decimalSpinner;
    private TextView resultText;

    private EditText byteInput;
    private Spinner byteUnitSpinner;
    private TextView byteResultText;

    private EditText celsiusInput;
    private Spinner unitSpinnerCelsius;
    private TextView celsiusResultText;


    private static final String[] BASE_OPTIONS = {"Binary","Octal","Hexadecimal"};
    private static final String[] UNIT_OPTIONS = {"Kilo Byte","Byte","Kibi Byte", "Bit"};
    private static final String[] CELSIUS_UNIT_OPTIONS = {"Fahrenheit","Kelvin"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        decimalInput = findViewById(R.id.decimalInput);
        decimalSpinner = findViewById(R.id.decimalSpinner);
        resultText = findViewById(R.id.resultText);

        byteInput = findViewById(R.id.byteInput);
        byteUnitSpinner = findViewById(R.id.byteunitSpinner);
        byteResultText = findViewById(R.id.byteresultText);

        celsiusInput = findViewById(R.id.celciusInput);
        unitSpinnerCelsius = findViewById(R.id.unitSpinnerCelsius);
        celsiusResultText = findViewById(R.id.resultTextCelsius);

        ArrayAdapter<String> baseAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,BASE_OPTIONS);
        baseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        decimalSpinner.setAdapter(baseAdapter);

        decimalSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View selectedItemView, int position, long id) {
                convertDecimal();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        decimalInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                convertDecimal();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ArrayAdapter<String> unitAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,UNIT_OPTIONS);
        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        byteUnitSpinner.setAdapter(unitAdapter);

        byteInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                convertByte();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        byteUnitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View selectedItemView, int position, long id) {
                convertByte();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        ArrayAdapter<String> celsiusUnitAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,CELSIUS_UNIT_OPTIONS);
        celsiusUnitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitSpinnerCelsius.setAdapter(celsiusUnitAdapter);

        celsiusInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                convertCelsius();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        unitSpinnerCelsius.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View selectedItemView, int position, long id) {
                convertCelsius();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void convertDecimal(){
        try {
            String input = decimalInput.getText().toString();
            if(!input.isEmpty()){
                int decimalValue = Integer.parseInt(input);
                String selectBase = decimalSpinner.getSelectedItem().toString();
                String result = "";
                switch (selectBase){
                    case "Binary":
                        result = Integer.toBinaryString(decimalValue);
                        break;
                    case "Octal":
                        result = Integer.toOctalString(decimalValue);
                        break;
                    case "Hexadecimal":
                        result = Integer.toHexString(decimalValue);
                        break;
                }
                resultText.setText(result);
            }
            else {
                resultText.setText("");
            }
        } catch (NumberFormatException e){
            resultText.setText("Geçersiz giriş!");
        }
    }

    public void convertByte(){
        try {
            double byteValue = Double.parseDouble(byteInput.getText().toString());
            String selectUnit = byteUnitSpinner.getSelectedItem().toString();
            double result = converToSelectedUnit(byteValue, selectUnit);
            DecimalFormat df = new DecimalFormat("#.####");
            byteResultText.setText("Sonuç: " + df.format(result) + " " + selectUnit);
        } catch (NumberFormatException e){
            byteResultText.setText("Geçersiz giriş!");
        }
    }

    private double converToSelectedUnit(double value, String selectUnit){
        double result = 0;
        switch (selectUnit){
            case "Kilo Byte":
                result = value / 1024;
                break;
            case "Byte":
                result = value * 1048576;
                break;
            case "Kibi Byte":
                result = value * 976.5625;
                break;
            case "Bit":
                result = value * 8000000;
                break;
        }
        return result;
    }


    public void convertCelsius(){
        try {
            double celsiusValue = Double.parseDouble(celsiusInput.getText().toString());
            String selectUnit = unitSpinnerCelsius.getSelectedItem().toString();
            double result = convertToSelectedCelciusUnit(celsiusValue, selectUnit);
            DecimalFormat df = new DecimalFormat("#.####");
            celsiusResultText.setText("Sonuç: " + df.format(result) + " " + selectUnit);
        } catch (NumberFormatException e) {
            celsiusResultText.setText("Geçersiz giriş!");
        }
    }


    private double convertToSelectedCelciusUnit(double value, String selectUnit){
        double result = 0;
        switch (selectUnit){
            case "Fahrenheit":
                result = (value * 9 / 5) + 32;
                break;
            case "Kelvin":
                result = value + 273.15;
                break;
        }
        return result;
    }









}
