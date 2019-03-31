package com.jmacek.multiactivity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TestActivity extends AppCompatActivity implements OnClickListener{

    EditText data;
    Button check;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        data = (EditText)findViewById(R.id.data);
        check = (Button)findViewById(R.id.checker);
        check.setOnClickListener(this);

        Intent intent = getIntent();

        Bundle mybundle = intent.getExtras();
        int index = mybundle.getInt("index");

        if (index % 2 == 0){
            result = "Even";
        }
        else{
            result = "Odd";
        }

        data.setText("index = " + index);

        mybundle.putString("result", result);
        intent.putExtras(mybundle);

        setResult(Activity.RESULT_OK, intent);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
