package xox;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileInputStream;
import java.util.ArrayList;

import xox.BDD.DataAccess;
import xox.model.Program;

/**
 * Created by XoX on 12/06/2015.
 */
public class ProgramsListAdapter extends BaseAdapter {
    private Activity activity ;
    private LayoutInflater inflater ;
    private ArrayList<Program> programs;

    public ProgramsListAdapter(Activity activity, ArrayList<Program> programs) {
        this.activity = activity ;
        this.programs = programs;
    }

    @Override
    public int getCount() {
        return programs.size();
    }

    @Override
    public Object getItem(int position) {
        return programs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (inflater == null) {
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.channel, null) ;
        }
        final Program program = programs.get(position) ;
        TextView title = (TextView) convertView.findViewById(R.id.channel_title) ;
        ImageView thumbnail = (ImageView) convertView.findViewById(R.id.thumbnail) ;

        String filename = program.getImgURL() ;
        FileInputStream fileInputStream ;
        Bitmap channelImg = null ;
        try {
            fileInputStream = activity.openFileInput(filename) ;
            channelImg = BitmapFactory.decodeStream(fileInputStream) ;
        } catch (Exception e) {
            e.printStackTrace();
        }


        thumbnail.setImageBitmap(channelImg);

        title.setText(String.valueOf(position + 1) + ". " + program.getName());

        Button delete = (Button) convertView.findViewById(R.id.button2) ;
        delete.setTag(new Integer(position));

        Button modify = (Button) convertView.findViewById(R.id.button3) ;
        modify.setTag(new Integer(position));

        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ModifyProgramActivity.class);
                intent.putExtra("pos", ((int) v.getTag()));
                activity.startActivity(intent);
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*MainActivity.provider.supprimerEtudiant(etudiants.get((int) v.getTag()));
                MainActivity.etudiants.remove(((int) v.getTag()));*/
                DataAccess dataAccess = new DataAccess(activity) ;
                dataAccess.open();
                dataAccess.supprimerProgram(program.getProgramID());
                dataAccess.close();
                ProgramsActivity.programs.remove(position);
                notifyDataSetChanged();
            }
        }); ;

        Log.d("PROGRAM", program.getProgramID() + " , " + program.getName() + " , " + program.getImgURL()) ;

        return convertView ;
    }
}