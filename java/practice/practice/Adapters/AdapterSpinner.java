package practice.practice.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import practice.practice.Constants.UtilConstants;
import practice.practice.R;

/**
 * Created by ThorneBird on 3/19/2017.
 */
public class AdapterSpinner extends BaseAdapter {


    private Context context;
    private String[] lottoNames;
    private LayoutInflater inflater;


    public AdapterSpinner(Context applicationContext, String[] lottoNames) {
        this.context = applicationContext;
        this.lottoNames = lottoNames;
        this.inflater = (LayoutInflater.from(applicationContext));
    }
    @Override
    public int getCount() {
        return lottoNames.length;
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return lottoNames[position];
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=inflater.inflate(R.layout.spinner_row,null);
        ImageView im = (ImageView) view.findViewById(R.id.imageView);
        TextView names = (TextView) view.findViewById(R.id.textView);
     //   icon.setImageResource(lottoImages[position]);
        names.setText(lottoNames[position]);
        setImage(im,position);
        return view;
    }

    private void setImage(ImageView im,int position) {
        if(lottoNames[position].equals(UtilConstants.AUSOZLOTTO)||
                lottoNames[position].equals(UtilConstants.AUSLOTT)||
                lottoNames[position].equals(UtilConstants.AUSPOWERBALL)){
            im.setImageResource(R.drawable.austraflag);
        }else if(lottoNames[position].equals(UtilConstants.USPOWERBALL)||
                 lottoNames[position].equals(UtilConstants.USMEGAMILLIONS)){
            im.setImageResource(R.drawable.usaflag);
        } else if(lottoNames[position].equals(UtilConstants.BRITISHLOTTO)){
            im.setImageResource(R.drawable.britishflag);
        }else if(lottoNames[position].equals(UtilConstants.CANADIANLOTTO)){
            im.setImageResource(R.drawable.canflag);
        }else if(lottoNames[position].equals(UtilConstants.EUROJACKPOT)||lottoNames[position].equals(UtilConstants.EUROMILLIONS)){
            im.setImageResource(R.drawable.europflag);
        }else if(lottoNames[position].equals(UtilConstants.SPANISHLOTTO)){
            im.setImageResource(R.drawable.spanishflag);
        }else if(lottoNames[position].equals(UtilConstants.FRENCHLOTTO)){
            im.setImageResource(R.drawable.frenchflag);
        }else if(lottoNames[position].equals(UtilConstants.GERMANLOTTO)){
            im.setImageResource(R.drawable.germanflag);
        }else if(lottoNames[position].equals(UtilConstants.IRISHLOTTO)){
            im.setImageResource(R.drawable.irishflag);
        }



        /*if (currentPosition == UtilConstants.AUS_LOTTO ||
                currentPosition == UtilConstants.AUS_OZ_LOTTO ||
                currentPosition == UtilConstants.AUS_POWERBALL) {
            im.setImageResource(R.drawable.austraflag);
        } else if (currentPosition == UtilConstants.BRITISH_LOTTO) {
            im.setImageResource(R.drawable.britishflag);
        } else if (currentPosition == UtilConstants.CANADIAN_LOTT) {
            im.setImageResource(R.drawable.canflag);
        } else if (currentPosition == UtilConstants.EURO_JACKPOT || currentPosition == UtilConstants.EURO_MILLIONS) {
            im.setImageResource(R.drawable.europflag);
        } else if (currentPosition == UtilConstants.FRENCH_LOTTO) {
            im.setImageResource(R.drawable.frenchflag);

        } else if (currentPosition == UtilConstants.GERMAN_LOTTO) {
            im.setImageResource(R.drawable.germanflag);

        } else if (currentPosition == UtilConstants.IRISH_LOTTO) {
            im.setImageResource(R.drawable.irishflag);

        } else if (currentPosition == UtilConstants.SPANISH_LOTTO) {
            im.setImageResource(R.drawable.spanishflag);

        } else if (currentPosition == UtilConstants.USA_MEGA_MILLIONS || currentPosition == UtilConstants.USA_POWERBALL) {
            im.setImageResource(R.drawable.usaflag);

        }*/
    }
}
