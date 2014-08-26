package com.othello.desdemona.app;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;

public class MainActivity extends Activity {

    Button play, how, options;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        options = (Button) findViewById(R.id.button1);
        play = (Button) findViewById(R.id.button2);
        how = (Button) findViewById(R.id.button3);

        options.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent goToPlay = new Intent(context, OptionsActivity.class);
                startActivity(goToPlay);
            }

        });

        play.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent goToPlay = new Intent(context, PlayActivity.class);
                startActivity(goToPlay);
            }

        });

        how.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent goToHow = new Intent(context, HowActivity.class);
                startActivity(goToHow);
            }
        });

    }


}
