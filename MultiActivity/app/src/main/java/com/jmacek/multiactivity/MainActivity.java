package com.jmacek.multiactivity;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText contact, gps_longitude, gps_latitude, sms_recipient, sms_content, std_index;
    TextView ind_result;
    Button btnContact, btnGPS, btnSMS, btn_newapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contact = (EditText)findViewById(R.id.contact);
        gps_longitude = (EditText)findViewById(R.id.gps_longitude);
        gps_latitude = (EditText)findViewById(R.id.gps_latitude);
        sms_recipient = (EditText)findViewById(R.id.sms_recipient);
        sms_content = (EditText)findViewById(R.id.sms_content);
        std_index = (EditText)findViewById(R.id.student_ind);
        ind_result = (TextView)findViewById(R.id.index_result);

        btnContact = (Button)findViewById(R.id.btnContact);
        btnContact.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String myData = ContactsContract.Contacts
                        .CONTENT_URI + "/" + "2";
                Intent myActivity2 = new Intent(Intent.ACTION_EDIT,
                        Uri.parse(myData));
                startActivity(myActivity2);
            }
        });
        btnGPS = (Button)findViewById(R.id.btnGPS);
        btnGPS.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String lng = gps_longitude.getText().toString();
                String lat = gps_latitude.getText().toString();
                String geoCode = "geo:" + lng + "," + lat +"&z=16";
                Intent intent = new Intent(Intent.ACTION_VIEW,
                         Uri.parse(geoCode));
                startActivity(intent);
            }
        });
        btnSMS = (Button)findViewById(R.id.btnSMS);
        btnSMS.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                String sms_body = sms_content.getText().toString();
                String recipient = "smsto:" + sms_recipient.getText().toString();
                Intent intent = new Intent(
                        Intent.ACTION_SENDTO,
                        Uri.parse(recipient));
                intent.putExtra("sms_body", sms_body);
                startActivity(intent);

            }
        });
        btn_newapp = (Button)findViewById(R.id.new_app);
        btn_newapp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = Integer.parseInt(std_index.getText().toString());
                Intent checker = new Intent(MainActivity.this, TestActivity.class);
                Bundle databundle = new Bundle();

                databundle.putInt("index", index);

                checker.putExtras(databundle);

                startActivityForResult(checker, 101);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if ((requestCode == 101 ) && (resultCode == AppCompatActivity.RESULT_OK)){
                Bundle myResultBundle = data.getExtras();
                String result = myResultBundle.getString("result");
                ind_result.setText("Index is " + result);
            }
        }
        catch (Exception e) {
            ind_result.setText("Problems - " + requestCode + " " + resultCode);
        }
    }
}
