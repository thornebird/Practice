package practice.practice.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import practice.practice.DataModel.LottoSelectorManager;
import practice.practice.DataModel.UserAlarm;
import practice.practice.R;

/**
 * Created by ThorneBird on 6/16/2017.
 */

public class AdapterListAlarms extends ArrayAdapter<UserAlarm> {

    private Context context;
    private int resource;
    private ArrayList<UserAlarm> userAlarms;
    private LottoSelectorManager manager;
    private int currentSelection;

    public AdapterListAlarms(Context context,int resource,ArrayList<UserAlarm>userAlarms){
        super(context,resource);
        this.context=context;
        this.resource=resource;
        this.userAlarms=userAlarms;
    }

    @Override
    public int getCount() {
        return userAlarms.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder holder=null;
        UserAlarm userAlarm=userAlarms.get(position);

        if(convertView==null){
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(resource, null);
            holder = new ViewHolder();
            holder.tvAlarmName = (TextView) convertView.findViewById(R.id.tv_alarm_lottoname);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvAlarmName.setText(userAlarm.getLottoName());

        return convertView;
    }

    public class ViewHolder{
        TextView tvAlarmName;
    }
}
