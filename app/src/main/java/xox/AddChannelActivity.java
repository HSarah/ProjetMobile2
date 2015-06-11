package xox;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

import xox.BDD.BDDOpenHelper;
import xox.BDD.DataAccess;
import xox.model.Channel;


public class AddChannelActivity extends ActionBarActivity {
    EditText nom ;
    EditText image;
    TextView imageURL ;
    private static final int SELECT_PHOTO = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_channel);

        nom = (EditText) findViewById(R.id.nom) ;
        imageURL = (TextView) findViewById(R.id.imageURI) ;
       // image = (EditText) findViewById(R.id.) ;

        Button select = (Button) findViewById(R.id.selectimg) ;
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
            }
        });

        Button add = (Button) findViewById(R.id.addStudent) ;
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //addChannel() ;
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode) {
            case SELECT_PHOTO:
                if(resultCode == RESULT_OK){

                    Uri selectedImage = data.getData();

                    InputStream imageStream = null;
                    try {
                        imageStream = getContentResolver().openInputStream(selectedImage);
                        imageURL.setText(selectedImage.getPath());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
                }
        }
    }

    public void addChannel() {
    DataAccess dataAccess = new DataAccess(this) ;
    dataAccess.open();
    Channel channel = dataAccess.addChannel(nom.getText().toString(), Integer.parseInt(image.getText().toString()));
    dataAccess.close();

    MainActivity.channels.add(channel) ;
    Context context = getApplicationContext() ;
    CharSequence text = "Chaine ajoutée ! " ;

    int duration = Toast.LENGTH_LONG ;

    Toast toast = Toast.makeText(context, text, duration) ;
    toast.show() ;
    finish();

 /*   Etudiant etudiant = provider.ajouterEtudiant(nom.getText().toString(), prenom.getText().toString(),
                tel.getText().toString(), date.getText().toString()) ;
    provider.close();


    new Etudiant(mat.getText().toString(), nom.getText().toString(), prenom.getText().toString(),
                tel.getText().toString(), date.getText().toString()) ;

        MainActivity.channels.add(etudiant) ;
        Context context = getApplicationContext() ;
        CharSequence text = "Etudiant ajouté ! " ;

        int duration = Toast.LENGTH_LONG ;

        Toast toast = Toast.makeText(context, text, duration) ;
        toast.show() ;
        finish();*/
}




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        /*getMenuInflater().inflate(R.menu.menu_add_student, menu);*/
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
