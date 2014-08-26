package com.othello.desdemona.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class OptionsActivity extends Activity {

    Button back, red, green, blue, bw, gs;
    EditText player1, player2;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        context = this;

        back = (Button) findViewById(R.id.button6);
        red = (Button) findViewById(R.id.button1);
        green = (Button) findViewById(R.id.button3);
        blue = (Button) findViewById(R.id.button2);
        bw = (Button) findViewById(R.id.button4);
        gs = (Button) findViewById(R.id.button5);

        back.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent goToPlay = new Intent(context, MainActivity.class);
                startActivity(goToPlay);
            }

        });

        /*red.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //make board red
            }

        });

        green.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //make board green
            }
        });

        blue.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //make board blue
            }
        });

        bw.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //make pieces black and white
            }
        });

        gs.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //make pieces gold and silver
            }
        });

        player1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //enter player 1 name
            }
        });

        player2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //enter player 2 name
            }
        });
*/
    }


}