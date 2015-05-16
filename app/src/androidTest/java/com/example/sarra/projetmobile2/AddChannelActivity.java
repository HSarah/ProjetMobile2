package com.example.sarra.projetmobile2;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddChannelActivity extends ActionBarActivity {
    EditText nom ;
    EditText prenom ;
    EditText tel ;
    EditText date ;
    EditText mat ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        nom = (EditText) findViewById(R.id.nom) ;
        prenom = (EditText) findViewById(R.id.prenom) ;
        tel = (EditText) findViewById(R.id.tel) ;
        date = (EditText) findViewById(R.id.datenais) ;

        Button add = (Button) findViewById(R.id.addStudent) ;
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStudent() ;
            }
        });
    }

    public void addStudent() {
        EtudiantDataProvider provider = MainActivity.provider ;
        provider.open();

        Etudiant etudiant = provider.ajouterEtudiant(nom.getText().toString(), prenom.getText().toString(),
                tel.getText().toString(), date.getText().toString()) ;
        provider.close();
        /*new Etudiant(mat.getText().toString(), nom.getText().toString(), prenom.getText().toString(),
                tel.getText().toString(), date.getText().toString()) ;*/
        MainActivity.channels.add(etudiant) ;
        Context context = getApplicationContext() ;
        CharSequence text = "Etudiant ajouté ! " ;

        int duration = Toast.LENGTH_LONG ;

        Toast toast = Toast.makeText(context, text, duration) ;
        toast.show() ;
        finish();
      }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_student, menu);
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
}
