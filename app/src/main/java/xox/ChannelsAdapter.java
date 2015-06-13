package xox;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import xox.model.Channel;

/**
 * Created by XoX on 26/04/2015.
 */
public class ChannelsAdapter extends BaseAdapter {

    public ArrayList<Channel> channels;
    Activity activity ;
    private LayoutInflater inflater ;

    public ChannelsAdapter(Activity activity, ArrayList<Channel> channels) {
        this.channels = channels ;
        this.activity = activity ;
    }

    @Override
    public int getCount() {
        return 0 ;
    }

    @Override
    public Object getItem(int position) {
        return channels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null) {
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.channel, null) ;
        }

        final Channel channel = channels.get(position) ;
        TextView temp = (TextView) convertView.findViewById(R.id.nom) ;
        temp.setText(channel.getName());



        Button delete = (Button) convertView.findViewById(R.id.button2) ;
        delete.setTag(new Integer(position));

        Button modify = (Button) convertView.findViewById(R.id.button3) ;
        modify.setTag(new Integer(position));

        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent(activity, ModifyChannelActivity.class) ;
                intent.putExtra("pos", ((int) v.getTag())) ;
                activity.startActivity(intent);*/
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO remove from database;
                MainActivity.channels.remove(((int) v.getTag()));
                notifyDataSetChanged();
            }
        }); ;

        return convertView;
    }
}
