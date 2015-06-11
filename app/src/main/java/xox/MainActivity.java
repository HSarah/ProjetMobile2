package xox;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import xox.BDD.DataAccess;
import xox.R;
import xox.model.Channel;


public class MainActivity extends ActionBarActivity {

    public static ArrayList<Channel> channels ;
    DataAccess dataAccess ;

    ChannelListAdapter adapter ;
    ListView listView ;



    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataAccess = new DataAccess(this) ;

        dataAccess.open();
        dataAccess.supprimerChannel(1);
        channels = dataAccess.getAllChannels() ;
        dataAccess.close();

        listView = (ListView) findViewById(R.id.listView) ;
        adapter = new ChannelListAdapter(this, channels) ;
        listView.setAdapter(adapter) ;

        Button add = (Button) findViewById(R.id.button) ;

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
