package com.jmacek.currencyconverter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.DecimalFormat;

public class MainActivity extends Activity {

    //USA money format (12 digits, 2 decimals)
    DecimalFormat PLFORMAT = new DecimalFormat("###,###,###,###.##");
    // GUI widgets
    Button btnConvert;
    Button btnClear;
    EditText txtUSD;
    EditText txtEUR;
    EditText txtPLN;
    EditText txtGBP;
    EditText txtRUB;
    EditText txtCHF;
    EditText txtCAD;
    EditText txtHKD;
    EditText txtSEK;
    EditText txtNZD;

    double ex_eur, ex_usd, ex_gbp, ex_rub, ex_chf, ex_cad, ex_sek, ex_nzd, ex_hkd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        String URL = "https://api.exchangeratesapi.io/latest?base=PLN";

        JsonObjectRequest objectRequest;
        objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Rest response", response.toString());
                        try {
                            JSONObject data  = response.getJSONObject("rates");
                            String euro = data.getString("EUR");
                            ex_eur = Double.parseDouble(euro);
                            String usdollar = data.getString("USD");
                            ex_usd = Double.parseDouble(usdollar);
                            String pound = data.getString("GBP");
                            ex_gbp = Double.parseDouble(pound);
                            String rubel = data.getString("RUB");
                            ex_rub = Double.parseDouble(rubel);
                            String swissfranc = data.getString("CHF");
                            ex_chf = Double.parseDouble(swissfranc);
                            String canadiandollar = data.getString("CAD");
                            ex_cad = Double.parseDouble(canadiandollar);
                            String swedishkrona = data.getString("SEK");
                            ex_sek = Double.parseDouble(swedishkrona);
                            String nzdollar = data.getString("NZD");
                            ex_nzd = Double.parseDouble(nzdollar);
                            String hkdollar = data.getString("HKD");
                            ex_hkd = Double.parseDouble(hkdollar);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Rest response", error.toString());
                    }
                }
        );

        requestQueue.add(objectRequest);

        // bind local controls to GUI widgets
        txtPLN = (EditText)findViewById(R.id.txtPLN);
        txtUSD = (EditText)findViewById(R.id.txtUSD);
        txtGBP = (EditText)findViewById(R.id.txtGBP);
        txtRUB = (EditText)findViewById(R.id.txtRUB);
        txtCHF = (EditText)findViewById(R.id.txtCHF);
        txtCAD = (EditText)findViewById(R.id.txtCAD);
        txtSEK = (EditText)findViewById(R.id.txtSEK);
        txtNZD = (EditText)findViewById(R.id.txtNZD);
        txtEUR = (EditText)findViewById(R.id.txtEUR);
        txtHKD = (EditText)findViewById(R.id.txtHKD);

        // attach click behavior to buttons
        btnClear = (Button)findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new OnClickListener() {
            // clear the text boxes
            @Override
            public void onClick(View v) {
                txtPLN.setText("");
                txtEUR.setText("");
                txtUSD.setText("");
                txtGBP.setText("");
                txtRUB.setText("");
                txtCHF.setText("");
                txtCAD.setText("");
                txtSEK.setText("");
                txtNZD.setText("");
                txtHKD.setText("");
            }
        });
        btnConvert = (Button) findViewById(R.id.btnConvert);
        btnConvert.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    String plnStr = txtPLN.getText().toString();
                    double pln = Double.parseDouble(plnStr);

                    String eur = String.valueOf(PLFORMAT.format(pln * ex_eur));
                    String gbp = String.valueOf(PLFORMAT.format(pln * ex_gbp));
                    String usd = String.valueOf(PLFORMAT.format(pln * ex_usd));
                    String rub = String.valueOf(PLFORMAT.format(pln * ex_rub));
                    String chf = String.valueOf(PLFORMAT.format(pln * ex_chf));
                    String cad = String.valueOf(PLFORMAT.format(pln * ex_cad));
                    String sek = String.valueOf(PLFORMAT.format(pln * ex_sek));
                    String nzd = String.valueOf(PLFORMAT.format(pln * ex_nzd));
                    String hkd = String.valueOf(PLFORMAT.format(pln * ex_hkd));

                    txtUSD.setText(usd);
                    txtEUR.setText(eur);
                    txtGBP.setText(gbp);
                    txtRUB.setText(rub);
                    txtCHF.setText(chf);
                    txtCAD.setText(cad);
                    txtSEK.setText(sek);
                    txtNZD.setText(nzd);
                    txtHKD.setText(hkd);

                } catch (NumberFormatException e) {
                  // ignore errors
                }
            }
        });// setOnClick...
    }// onCreate
}// class
