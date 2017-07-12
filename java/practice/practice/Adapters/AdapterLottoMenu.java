package practice.practice.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import practice.practice.DataModel.LottoSelectorManager;
import practice.practice.DataModel.UserLotto;
import practice.practice.R;

/**
 * Created by ThorneBird on 5/12/2017.
 */

public class AdapterLottoMenu extends ArrayAdapter<UserLotto> {

    private Context context;
    private int resourse;
    private ArrayList<String> lottoNames;
    private LottoSelectorManager manager;
    private int currentSelection;

    /**
     * Constructor
     *d
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     */
    public AdapterLottoMenu(@NonNull Context context, @LayoutRes int resource, ArrayList<String> lottoNames,int currentSelection) {
        super(context, resource);
        this.context = context;
        this.lottoNames = lottoNames;
        this.resourse=resource;
        this.currentSelection=currentSelection;
    }

    @Override
    public int getCount() {
        return lottoNames.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder = null;
        String currentLotto = lottoNames.get(position);
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(resourse, null);
            holder = new ViewHolder();
            holder.tvLottoName = (TextView) convertView.findViewById(R.id.tv_lottoname);
            holder.imLotto = (ImageView) convertView.findViewById(R.id.im_lotto);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if(currentSelection==position) {
            convertView.setBackgroundColor(Color.parseColor("#00BFFF"));
            holder.tvLottoName.setTextColor(Color.WHITE);
        }
        setData(holder, currentLotto);

        return convertView;
    }

    private class ViewHolder {
        TextView tvLottoName;
        ImageView imLotto;
    }

    private void setData(ViewHolder holder, String currentLotto) {
        if (manager == null) {
            manager = new LottoSelectorManager(currentLotto);
        }
        else {
            manager.reset(currentLotto);
        }

        holder.imLotto.setImageResource(manager.getSmallLogo());
        holder.tvLottoName.setText(currentLotto);

    }
}
