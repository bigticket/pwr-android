package com.jmacek.responsiveui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn9, btn8, btn7, btn6, btn5, btn4, btn3, btn2, btn1, btn0;
    Button btnPlus, btnMinus, btnEqual, btnClear, btnMul, btnDiv;

    EditText memory, result;

    String r1, r2, sign, equals="=", fin_str;
    double fin;
    StringBuilder res = new StringBuilder();
    StringBuilder mem = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        memory = (EditText) findViewById(R.id.txtmemory);
        result = (EditText) findViewById(R.id.txtresult);

        btn9 = (Button) findViewById(R.id.btn9);
        btn9.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (r1 == null){
                    r1 = "9";
                    res.append(r1);
                    result.setText(res);
                }
                else if (r1 != null && r2 == null){
                    r2 = "9";
                    res.append(r2);
                    result.setText(res);
                }
                else{
                    Toast.makeText(MainActivity.this, "Beta version, click C", 10).show();
                }
            }
        });

        btn8 = (Button)findViewById(R.id.btn8);
        btn8.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (r1 == null){
                    r1 = "8";
                    res.append(r1);
                    result.setText(res);
                }
                else if (r1 != null && r2 == null){
                    r2 = "8";
                    res.append(r2);
                    result.setText(res);
                }
                else{
                    Toast.makeText(MainActivity.this, "Beta version, click C", 10).show();
                }
            }
        });

        btn7 = (Button)findViewById(R.id.btn7);
        btn7.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (r1 == null){
                    r1 = "7";
                    res.append(r1);
                    result.setText(res);
                }
                else if (r1 != null && r2 == null){
                    r2 = "7";
                    res.append(r2);
                    result.setText(res);
                }
                else{
                    Toast.makeText(MainActivity.this, "Beta version, click C", 10).show();
                }
            }
        });

        btn6 = (Button)findViewById(R.id.btn6);
        btn6.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (r1 == null){
                    r1 = "6";
                    res.append(r1);
                    result.setText(res);
                }
                else if (r1 != null && r2 == null){
                    r2 = "6";
                    res.append(r2);
                    result.setText(res);
                }
                else{
                    Toast.makeText(MainActivity.this, "Beta version, click C", 10).show();
                }
            }
        });

        btn5 = (Button)findViewById(R.id.btn5);
        btn5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (r1 == null){
                    r1 = "5";
                    res.append(r1);
                    result.setText(res);
                }
                else if (r1 != null && r2 == null){
                    r2 = "5";
                    res.append(r2);
                    result.setText(res);
                }
                else{
                    Toast.makeText(MainActivity.this, "Beta version, click C", 10).show();
                }
            }
        });

        btn4 = (Button)findViewById(R.id.btn4);
        btn4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (r1 == null){
                    r1 = "4";
                    res.append(r1);
                    result.setText(res);
                }
                else if (r1 != null && r2 == null){
                    r2 = "4";
                    res.append(r2);
                    result.setText(res);
                }
                else{
                    Toast.makeText(MainActivity.this, "Beta version, click C", 10).show();
                }
            }
        });

        btn3 = (Button)findViewById(R.id.btn3);
        btn3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (r1 == null){
                    r1 = "3";
                    res.append(r1);
                    result.setText(res);
                }
                else if (r1 != null && r2 == null){
                    r2 = "3";
                    res.append(r2);
                    result.setText(res);
                }
                else{
                    Toast.makeText(MainActivity.this, "Beta version, click C", 10).show();
                }
            }
        });

        btn2 = (Button)findViewById(R.id.btn2);
        btn2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (r1 == null){
                    r1 = "2";
                    res.append(r1);
                    result.setText(res);
                }
                else if (r1 != null && r2 == null){
                    r2 = "2";
                    res.append(r2);
                    result.setText(res);
                }
                else{
                    Toast.makeText(MainActivity.this, "Beta version, click C", 10).show();
                }
            }
        });

        btn1 = (Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (r1 == null){
                    r1 = "1";
                    res.append(r1);
                    result.setText(res);
                }
                else if (r1 != null && r2 == null){
                    r2 = "1";
                    res.append(r2);
                    result.setText(res);
                }
                else{
                    Toast.makeText(MainActivity.this, "Beta version, click C", 10).show();
                }
            }
        });

        btn0 =  (Button)findViewById(R.id.btn0);
        btn0.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (r1 == null){
                    r1 = "0";
                    res.append(r1);
                    result.setText(res);
                }
                else if (r1 != null && r2 == null){
                    r2 = "0";
                    res.append(r2);
                    result.setText(res);
                }
                else{
                    Toast.makeText(MainActivity.this, "Beta version, click C", 10).show();
                }
            }
        });

        btnClear = (Button)findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                r1 = null;
                r2 = null;
                sign = null;
                mem.append(" | ");
                result.setText(res);
                memory.setText(mem);

            }
        });

        btnPlus = (Button)findViewById(R.id.btnPlus);
        btnPlus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sign == null){
                    sign = "+";
                    res.append(sign);
                    result.setText(res);
                }
                else{
                    Toast.makeText(MainActivity.this, "Beta version, click C", 10).show();
                }
            }
        });

        btnMinus = (Button)findViewById(R.id.btnMinus);
        btnMinus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sign == null){
                    sign = "-";
                    res.append(sign);
                    result.setText(res);
                }
                else{
                    Toast.makeText(MainActivity.this, "Beta version, click C", 10).show();
                }
            }
        });

        btnMul = (Button)findViewById(R.id.btnMul);
        btnMul.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sign == null){
                    sign = "×";
                    res.append(sign);
                    result.setText(res);
                }
                else{
                    Toast.makeText(MainActivity.this, "Beta version, click C", 10).show();
                }
            }
        });

        btnDiv = (Button)findViewById(R.id.btnDiv);
        btnDiv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sign == null){
                    sign = "÷";
                    res.append(sign);
                    result.setText(res);
                }
                else{
                    Toast.makeText(MainActivity.this, "Beta version, click C", 10).show();
                }
            }
        });

        btnEqual = (Button)findViewById(R.id.btnEqual);
        btnEqual.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(res.toString().contains("+")){
                    fin = Integer.parseInt(r1) + Integer.parseInt(r2);
                    fin_str = String.valueOf(fin);
                    res.append(equals);
                    res.append(fin_str);
                    result.setText(res);
                    mem.append(res);
                    memory.setText(mem);
                    res.setLength(0);
                }
                else if(res.toString().contains("-")){
                    fin = Integer.parseInt(r1) - Integer.parseInt(r2);
                    fin_str = String.valueOf(fin);
                    res.append(equals);
                    res.append(fin_str);
                    result.setText(res);
                    mem.append(res);
                    memory.setText(mem);
                    res.setLength(0);
                }
                else if(res.toString().contains("×")){
                    fin = Integer.parseInt(r1) * Integer.parseInt(r2);
                    fin_str = String.valueOf(fin);
                    res.append(equals);
                    res.append(fin_str);
                    result.setText(res);
                    mem.append(res);
                    memory.setText(mem);
                    res.setLength(0);
                }
                else if(res.toString().contains("÷")){
                    fin = Integer.parseInt(r1)/ Integer.parseInt(r2);
                    fin_str = String.valueOf(fin);
                    res.append(equals);
                    res.append(fin_str);
                    result.setText(res);
                    mem.append(res);
                    memory.setText(mem);
                    res.setLength(0);
                }
            }
        });
    }
}
