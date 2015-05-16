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


public class ModifyChannelActivity extends ActionBarActivity {

    EditText nom ;
    EditText prenom ;
    EditText tel ;
    EditText date ;
    EditText mat ;

    int pos ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_student);

        nom = (EditText) findViewById(R.id.nom) ;
        prenom = (EditText) findViewById(R.id.prenom) ;
        tel = (EditText) findViewById(R.id.tel) ;
        date = (EditText) findViewById(R.id.datenais) ;

        pos = this.getIntent().getExtras().getInt("pos") ;

        nom.setText(MainActivity.channels.get(pos).getNom());
        prenom.setText(MainActivity.channels.get(pos).getPrenom());
        tel.setText(MainActivity.channels.get(pos).getPhone());
        date.setText(MainActivity.channels.get(pos).getDate());

        Button modify = (Button) findViewById(R.id.modifyStudent) ;
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyStudent() ;
            }
        });
    }

    public void modifyStudent() {
        Etudiant etudiant = MainActivity.channels.get(pos) ;
        EtudiantDataProvider provider = MainActivity.provider ;

        Context context = getApplicationContext() ;
        CharSequence text = "Etudiant modifié ! " ;

        etudiant.setNom(nom.getText().toString());
        etudiant.setPrenom(prenom.getText().toString());
        etudiant.setPhone(tel.getText().toString());
        etudiant.setDate(date.getText().toString());

        provider.open();
        provider.modifierEtudiant(etudiant);
        provider.close();

        int duration = Toast.LENGTH_LONG ;

        Toast toast = Toast.makeText(context, text, duration) ;
        toast.show() ;
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_modify_student, menu);
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
