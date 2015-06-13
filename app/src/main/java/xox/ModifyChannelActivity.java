package xox;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
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


public class ModifyChannelActivity extends ActionBarActivity {
    EditText name ;
    TextView imageURL ;
    Channel channel ;

    Bitmap yourSelectedImage = null ;
    private static final int SELECT_PHOTO = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_channel);

        int position = this.getIntent().getExtras().getInt("pos") ;
        name = (EditText) findViewById(R.id.nom) ;
        imageURL = (TextView) findViewById(R.id.imageURI) ;

        channel = MainActivity.channels.get(position) ;
        name.setText(channel.getName());
        imageURL.setText(channel.getImgURL());

        Button modify = (Button) findViewById(R.id.modifyChannel) ;
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyChannel() ;
            }
        });

        Button select = (Button) findViewById(R.id.selectimg) ;
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
            }
        });

    }

    private void modifyChannel() {
        channel.setName(name.getText().toString());

        String filename = channel.getId() + ".png" ;
        channel.setImgURL(filename);


        FileOutputStream outputStream ;

        if (yourSelectedImage != null) {
            try {
                outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                yourSelectedImage.compress(Bitmap.CompressFormat.PNG, 30, outputStream) ;
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        Log.d("CHANNEL MODIFIED", channel.getName() + " , " + channel.getImgURL()) ;
        DataAccess dataAccess = new DataAccess(this) ;
        dataAccess.open();
        dataAccess.modifierChannel(channel);
        dataAccess.close();


        Context context = getApplicationContext() ;
        CharSequence text = "Channel modified !" ;

        int duration = Toast.LENGTH_LONG ;

        Toast toast = Toast.makeText(context, text, duration) ;
        toast.show() ;
        finish();

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
                        imageURL.setText("Image modified");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    yourSelectedImage = BitmapFactory.decodeStream(imageStream);
                }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_modify_channel, menu);
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
