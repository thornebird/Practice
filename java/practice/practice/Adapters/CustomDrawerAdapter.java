package practice.practice.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import practice.practice.DataModel.DrawerItem;
import practice.practice.R;

/**
 * Created by ThorneBird on 3/12/2017.
 */
public class CustomDrawerAdapter extends ArrayAdapter<DrawerItem> {

    private Context context;
    private List<DrawerItem> drawerItemList;
    private int layoutResID;

    public CustomDrawerAdapter(Context context, int layoutResourceID,
                               List<DrawerItem> listItems) {
        super(context, layoutResourceID, listItems);
        this.context = context;
        this.drawerItemList = listItems;
        this.layoutResID = layoutResourceID;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        DrawerItemHolder drawerHolder;
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            drawerHolder = new DrawerItemHolder();

            view = inflater.inflate(layoutResID, parent, false);
            drawerHolder.itemName = (TextView) view.findViewById(R.id.drawer_item_name);
            drawerHolder.icon = (ImageView) view.findViewById(R.id.drawer_icon);
            view.setTag(drawerHolder);

        } else {
            drawerHolder = (DrawerItemHolder) view.getTag();
        }

        DrawerItem dItem = (DrawerItem) this.drawerItemList.get(position);
        drawerHolder.icon.setImageDrawable(view.getResources().getDrawable(dItem.getImgResID()));
        drawerHolder.itemName.setText(dItem.getItemName());

        return view;
    }

    private static class DrawerItemHolder {
        TextView itemName;
        ImageView icon;
    }
}
