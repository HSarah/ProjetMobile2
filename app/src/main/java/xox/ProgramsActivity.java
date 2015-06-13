package xox;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import xox.BDD.DataAccess;
import xox.model.Channel;
import xox.model.Program;


public class ProgramsActivity extends ActionBarActivity {

    public static ArrayList<Program> programs;
    DataAccess dataAccess ;
    int idChannel ;

    ProgramsListAdapter adapter ;
    ListView listView ;



    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programs);
        dataAccess = new DataAccess(this) ;

        idChannel = getIntent().getExtras().getInt("idChannel");

        dataAccess.open();
        programs = dataAccess.getAllPrograms(idChannel) ;
        dataAccess.close();

        listView = (ListView) findViewById(R.id.listView) ;
        adapter = new ProgramsListAdapter(this, programs) ;
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
        Intent intent = new Intent(this, AddProgramActivity.class) ;
        intent.putExtra("idChannel", idChannel);
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
