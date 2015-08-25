package xox;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import xox.BDD.DataAccess;
import xox.model.Channel;


/**
 * Created by XoX on 31/03/2015.
 */
public class ChannelListAdapter extends BaseAdapter {
    private Activity activity ;
    private LayoutInflater inflater ;
    private ArrayList<Channel> channels ;

    public ChannelListAdapter(Activity activity, ArrayList<Channel> channels) {
        this.activity = activity ;
        this.channels = channels ;
    }

    @Override
    public int getCount() {
        return channels.size();
    }

    @Override
    public Object getItem(int position) {
        return channels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        if (inflater == null) {
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.channel, null) ;
        }
        final Channel channel = channels.get(position) ;
        TextView title = (TextView) convertView.findViewById(R.id.channel_title) ;
        ImageView thumbnail = (ImageView) convertView.findViewById(R.id.thumbnail) ;

        String filename = channel.getImgURL() ;
        FileInputStream fileInputStream ;
        Bitmap channelImg = null ;
        try {
            fileInputStream = activity.openFileInput(filename) ;
            channelImg = BitmapFactory.decodeStream(fileInputStream) ;
        } catch (Exception e) {
            e.printStackTrace();
        }


        thumbnail.setImageBitmap(channelImg);

        title.setText(String.valueOf(position+1) + ". " + channel.getName());

        Button delete = (Button) convertView.findViewById(R.id.button2) ;
        delete.setTag(new Integer(position)) ;

        Button modify = (Button) convertView.findViewById(R.id.button3) ;
        modify.setTag(new Integer(position));

        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ModifyChannelActivity.class) ;
                intent.putExtra("pos", ((int) v.getTag())) ;
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
                dataAccess.supprimerChannel(channel.getId());
                dataAccess.close();
                MainActivity.channels.remove(position);
                notifyDataSetChanged();
            }
        }); ;

        Log.d("CHANNEL", channel.getId() + " , " + channel.getName() + " , " + channel.getImgURL()) ;

        return convertView ;
    }

}
