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
import java.io.FileOutputStream;
import java.io.InputStream;

import xox.BDD.DataAccess;
import xox.model.Channel;


public class AddChannelActivity extends ActionBarActivity {
    EditText nom ;
    EditText image;
    TextView imageURL ;
    Bitmap yourSelectedImage ;
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
                addChannel() ;
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
                    imageURL.setText("Image selected");
                    InputStream imageStream = null;
                    try {
                        imageStream = getContentResolver().openInputStream(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    yourSelectedImage = BitmapFactory.decodeStream(imageStream);
                }
        }
    }

public void addChannel() {
    // Save Image into local storage ;
    String filename = (MainActivity.channels.size()+1) + ".png" ;
    FileOutputStream outputStream ;

    try {
        outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
        yourSelectedImage.compress(Bitmap.CompressFormat.PNG, 30, outputStream) ;
        outputStream.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    //Save Image into data base ;


    DataAccess dataAccess = new DataAccess(this) ;
    dataAccess.open();
    Channel channel = dataAccess.addChannel(nom.getText().toString(), filename);
    dataAccess.close();

    MainActivity.channels.add(channel) ;
    Context context = getApplicationContext() ;
    CharSequence text = "Chaine ajoutée !" ;

    int duration = Toast.LENGTH_LONG ;

    Toast toast = Toast.makeText(context, text, duration) ;
    toast.show() ;
    finish();
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
