package com.jmacek.fitnessapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class DataDisplay extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    DatabaseHandler db;
    PointsGraphSeries<DataPoint> series;
    Date x;
    Double y;
    SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_display);
        final GraphView graph;
        graph =(GraphView)findViewById(R.id.graph);
        db = new DatabaseHandler(this);
        final Spinner spinnerSport, spinnerStat;
        Button btnShow;
        spinnerSport = (Spinner)findViewById(R.id.spinnerSport);
        spinnerStat = (Spinner)findViewById(R.id.spinnerStat);
        graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if(isValueX){
                    return formatter.format(new Date((long) value));
                }
                else{
                    return super.formatLabel(value, isValueX);
                }
            }
        });

        final List<String> Sports = Arrays.asList("running", "cycling");
        final List<String> Stats = Arrays.asList("DISTANCE", "MAX_SPEED", "HR", "CALORIES");

        ArrayAdapter<String> sportAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, Sports);
        ArrayAdapter<String> statAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, Stats);
        sportAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSport.setAdapter(sportAdapter);
        spinnerStat.setAdapter(statAdapter);

        btnShow =(Button)findViewById(R.id.btnShow);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stat = spinnerStat.getSelectedItem().toString();
                String sport = spinnerSport.getSelectedItem().toString();
                HashMap<Date, Double> data = null;
                try {
                    data = db.getStats(sport,stat);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                List<Date> keys = new ArrayList<>(data.keySet());
                List<Double> values = new ArrayList<>(data.values());
                graph.removeAllSeries();
                series = new PointsGraphSeries<DataPoint>();
                for (int i=0; i<data.size(); i++){
                    x = keys.get(i);
                    y = values.get(i);
                    System.out.println(x + " + " + y);
                    series.appendData(new DataPoint(x,y), true, 500);
                }
                graph.addSeries(series);
                graph.getGridLabelRenderer().setNumHorizontalLabels(data.size()+1);
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
