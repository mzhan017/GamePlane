package com.ispring.gameplane.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.ispring.gameplane.MainActivity;
import com.ispring.gameplane.R;

import java.util.List;

/**
 * Created by red on 2018/6/11.
 */

public class UserAdapter extends ArrayAdapter<User> {
    private int resoureId;

    public UserAdapter(Context context, int textViewResourceId, List<User> objects)
    {
        super(context,textViewResourceId,objects);
        resoureId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        User user = getItem(position);
        if(user==null) {
            return null;
        }
        View view;
        if(convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resoureId, parent, false);
        } else
        {
            view = convertView;
        }
        TextView name = (TextView)view.findViewById(R.id.name_list);
        TextView score = (TextView)view.findViewById(R.id.score_list);
        TextView id = (TextView)view.findViewById(R.id.id_list);
        if (name!=null) {
            name.setText(user.getUserName() + "("+user.getDate()+"): Score is ");
        }
        if(score!=null) {
            score.setText(user.getScore()+";");
        }
        if(id!=null) {
            id.setText("id = "+user.getId()+";");
        }
        return view;
    }
}
