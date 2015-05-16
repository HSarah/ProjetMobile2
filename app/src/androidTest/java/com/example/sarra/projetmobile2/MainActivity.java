package com.example.sarra.projetmobile2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.channels.Channel;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    public static ArrayList<Channel> channels ;

    ChannelsAdapter adapter ;
    ListView listView ;






    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        listView = (ListView) findViewById(R.id.listView) ;
        adapter = new ChannelsAdapter(this, channels);
        listView.setAdapter(adapter);

        Button add = (Button) findViewById(R.id.button) ;
        final Button save = (Button) findViewById(R.id.save);
        final Button load = (Button) findViewById(R.id.load);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity() ;
            }
        });




    }

    public void launchActivity() {
        Intent intent = new Intent(this, AddChannelActivity.class) ;
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onPause() {
       super.onPause();
    }
}
