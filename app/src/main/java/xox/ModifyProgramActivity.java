package xox;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import xox.BDD.DataAccess;
import xox.model.Program;
import xox.model.Theme;


public class ModifyProgramActivity extends ActionBarActivity {


    EditText name ;
    EditText details ;
    EditText category ;
    TextView imageURL ;
    TimePicker time ;

    int idChannel ;

    Bitmap yourSelectedImage ;

    Program program ;
    private static final int SELECT_PHOTO = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_program);

        Button b  = (Button) findViewById(R.id.addProgram);

        yourSelectedImage = null ;

        idChannel = getIntent().getExtras().getInt("idProgram") ;

        name = (EditText) findViewById(R.id.nom);
        details = (EditText) findViewById(R.id.details);
        category = (EditText) findViewById(R.id.category);
        time = (TimePicker) findViewById(R.id.timePicker);
        imageURL = (TextView) findViewById(R.id.imageURI) ;




        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyProgram();
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


        int position = this.getIntent().getExtras().getInt("pos") ;


        program = ProgramsActivity.programs.get(position) ;
        name.setText(program.getName());
        imageURL.setText(program.getImgURL());
        details.setText(program.getDetails());
        category.setText(program.getTheme().getDescription());

       /* Time t1 = new Time() ;
        t1.setToNow();
        t1.set(0, 4, 5, 12, 06, 2015);
        long mil = t1.toMillis(true) ;
        Time t2 = new Time() ;
        t2.set(mil);
*/


        Log.d("CHANNEL : ", "" + program.getTime().hour+ ":" + program.getTime().minute) ;
        time.setCurrentHour(program.getTime().hour);
        time.setCurrentMinute(program.getTime().minute);


        Button modify = (Button) findViewById(R.id.addProgram) ;
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyProgram() ;
            }
        });




    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_modify_program, menu);
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


    private void modifyProgram() {
        program.setName(name.getText().toString());

        String filename = program.getChannelID() + "." + program.getProgramID() + ".png" ;




        // La suite des attributs
        Time t = new Time();
        t.set(0, time.getCurrentMinute(), time.getCurrentHour(), 12, 06, 2015);

        program.setTime(t);
        program.setDetails(details.getText().toString());
        program.setTheme(new Theme(category.getText().toString()));


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

        DataAccess dataAccess = new DataAccess(this) ;
        dataAccess.open();
        dataAccess.modifierProgram(program);
        dataAccess.close();


        Context context = getApplicationContext() ;
        CharSequence text = "Program modified !" ;

        int duration = Toast.LENGTH_LONG ;

        Toast toast = Toast.makeText(context, text, duration) ;
        toast.show() ;
        finish();

    }


}
