package xox;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null) {
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.channel, null) ;
        }
        Channel channel = channels.get(position) ;
        TextView title = (TextView) convertView.findViewById(R.id.channel_title) ;
        ImageView thumbnail = (ImageView) convertView.findViewById(R.id.thumbnail) ;
        thumbnail.setImageDrawable(activity.getResources().getDrawable(channel.getImgURL()));

        title.setText(String.valueOf(position+1) + ". " + channel.getName());

        return convertView ;
    }
}
