package com.othello.desdemona.app;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
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

public class HowActivity extends Activity {

    Button back;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how);

        context = this;

        back = (Button) findViewById(R.id.help_button1);

        OnClickListener listener = new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent goToMainActivity = new Intent(context, MainActivity.class);
                startActivity(goToMainActivity);
            }

        };

        back.setOnClickListener(listener);

    }



}
