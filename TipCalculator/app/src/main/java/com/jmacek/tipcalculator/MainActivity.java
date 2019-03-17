package com.jmacek.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    //money format (12 digits, 2 decimals)
    DecimalFormat priceformat = new DecimalFormat("###,###,###,###.##");

    // GUI widgets
    Button btnConvert;
    Button btnClear;
    EditText txtPrice;
    EditText txtTip;
    EditText txtFinal;
    RadioGroup radioGroup;
    RadioButton btnTasty;
    RadioButton btnGood;
    RadioButton btnBad;
    RadioButton btnDisplay;

    int tipIncreaser = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // bind local controls to GUI widgets
        txtPrice = (EditText)findViewById(R.id.txtPrice);
        txtTip = (EditText)findViewById(R.id.txtTip);
        txtFinal = (EditText)findViewById(R.id.txtFinal);

        radioGroup = (RadioGroup)findViewById(R.id.radiogroup);

        btnTasty = (RadioButton)findViewById(R.id.btnTasty);
        btnTasty.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                tipIncreaser = 10;
            }
        });

        btnGood = (RadioButton)findViewById(R.id.btnGood);
        btnGood.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                tipIncreaser = 5;
            }
        });

        btnBad = (RadioButton)findViewById(R.id.btnBad);
        btnBad.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                tipIncreaser = 0;
            }
        });

        btnClear = (Button)findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new OnClickListener() {
            // clear the text boxes
            @Override
            public void onClick(View v) {
                txtPrice.setText("");
                txtTip.setText("");
                txtFinal.setText("");
            }
        });
        btnConvert = (Button) findViewById(R.id.btnConvert);
        btnConvert.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String priceStr = txtPrice.getText().toString();
                    double price = Double.parseDouble(priceStr);
                    String tipStr = txtTip.getText().toString();
                    double tip = Double.parseDouble(tipStr) * 0.01;
                    String fin =
                            String.valueOf(priceformat.format
                                    (tipIncreaser +price + (price * tip)));
                    txtFinal.setText(fin);
                } catch (NumberFormatException e) {
                  // ignore errors
                }
            }
        });// setOnClick...
    }// onCreate
}// class
