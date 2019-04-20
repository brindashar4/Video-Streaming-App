package com.example.group_play;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    Server1 server;
    TextView infoip,msg,response;
    EditText editTextAddress,editTextPort;
    Button buttonConnect,buttonClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void client(View view) {

        //setContentView(R.layout.activity_client1);

        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        View serve = inflater.inflate(R.layout.activity_client1, null);

        editTextAddress =  serve.findViewById(R.id.addressEditText);
        editTextPort =  serve.findViewById(R.id.portEditText);
        buttonConnect =  serve.findViewById(R.id.connectButton);
        buttonClear =  serve.findViewById(R.id.clearButton);
        response =  serve.findViewById(R.id.responseTextView);

        buttonConnect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Client1 myClient = new Client1(editTextAddress.getText().toString(), Integer.parseInt(editTextPort.getText().toString()), response);
                myClient.execute();
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                response.setText("");
            }
        });
        Intent intent = new Intent(this, mClient1.class);
        startActivity(intent);

    }

    public void server(View view) {

        //LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.activity_server1,null);
        infoip = (TextView) v.findViewById(R.id.info);
        server = new Server1(this);
        //Toast.makeText(this,"set text ke pehle", Toast.LENGTH_SHORT).show();
        String disp = server.getIpAddress() + " : " + server.getPort();
        infoip.setText(disp);
        //Log.i("set",server.getIpAddress() + " : " + server.getPort());
        Intent intent = new Intent(this, Server1.class);
        intent.putExtra("str",disp);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        server.onDestroy();
    }
}