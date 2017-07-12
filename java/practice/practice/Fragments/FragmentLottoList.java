package practice.practice.Fragments;


import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import practice.practice.Adapters.ListAdapterLottoDraw;
import practice.practice.Constants.UtilConstants;
import practice.practice.DataModel.CalendarLotto;
import practice.practice.DataModel.LottocentreLottDraw;
import practice.practice.R;

/**
 * Created by ThorneBird on 3/24/2017.
 */
public class FragmentLottoList extends ListFragment {

    private ArrayList<LottocentreLottDraw> userLottoList;
    private int currentPosition;
    public final static String KEY_USERlIST = "KEY_USERLIST";
    public final static String KEY_POSITION = "KEY_POSITION";


    public static FragmentLottoList newInstance(ArrayList<LottocentreLottDraw> userlottList, int position) {
        Bundle args = new Bundle();
        FragmentLottoList fragment = new FragmentLottoList();
        args.putParcelableArrayList(KEY_USERlIST, userlottList);
        args.putInt(KEY_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userLottoList = getArguments().getParcelableArrayList(KEY_USERlIST);
            currentPosition = getArguments().getInt(KEY_POSITION);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lottolist, null);
        ImageView imLotto = (ImageView) view.findViewById(R.id.im_lotto);
        TextView tvNextDate = (TextView) view.findViewById(R.id.tv_nextdate);
        setImageLotto(imLotto, currentPosition);
        setCalendar(tvNextDate, currentPosition);
        ListView lv = (ListView) view.findViewById(android.R.id.list);
        ListAdapterLottoDraw adapter = new ListAdapterLottoDraw(getActivity(), R.layout.fragment_lotto_row, userLottoList);
        lv.setAdapter(adapter);

        return view;

    }

    /**
     * This method will be called when an item in the list is selected.
     * Subclasses should override. Subclasses can call
     * getListView().getItemAtPosition(position) if they need to access the
     * data associated with the selected item.
     *
     * @param l        The ListView where the click happened
     * @param v        The view that was clicked within the ListView
     * @param position The position of the view in the list
     * @param id       The row id of the item that was clicked
     */
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Log.i("Clicked",position+"");
    }

    private void setImageLotto(ImageView im, int currentPosition) {
        if (currentPosition == UtilConstants.AUS_OZ_LOTTO) {
            im.setImageResource(R.drawable.ozlotto);
        } else if (currentPosition == UtilConstants.AUS_POWERBALL) {
            im.setImageResource(R.drawable.auspowerball);
        } else if (currentPosition == UtilConstants.USA_POWERBALL) {
            im.setImageResource(R.drawable.usapowerball);
        } else if (currentPosition == UtilConstants.USA_MEGA_MILLIONS) {
            im.setImageResource(R.drawable.usamegamillions);
        } else if (currentPosition == UtilConstants.BRITISH_LOTTO) {
            im.setImageResource(R.drawable.britishlotto);
        } else if (currentPosition == UtilConstants.CANADIAN_LOTT) {
            im.setImageResource(R.drawable.canadianlotto);
        } else if (currentPosition == UtilConstants.EURO_JACKPOT) {
            im.setImageResource(R.drawable.eurojackpot);
        } else if (currentPosition == UtilConstants.EURO_MILLIONS) {
            im.setImageResource(R.drawable.euromillions);
        } else if (currentPosition == UtilConstants.SPANISH_LOTTO) {
            im.setImageResource(R.drawable.spanishlotto);
        } else if (currentPosition == UtilConstants.FRENCH_LOTTO) {
            im.setImageResource(R.drawable.frenchlotto);
        } else if (currentPosition == UtilConstants.GERMAN_LOTTO) {
            im.setImageResource(R.drawable.germanlotto);
        } else if (currentPosition == UtilConstants.IRISH_LOTTO) {
            im.setImageResource(R.drawable.irishlotto);
        } else if (currentPosition == UtilConstants.AUS_LOTTO) {
            im.setImageResource(R.drawable.auslotto);
        }
    }


    private void setCalendar(TextView tvDrawDay, int position) {
        CalendarLotto lotto = new CalendarLotto(position);
        String nextDrawDay = lotto.getNextDrawDay();
        tvDrawDay.setText(getActivity().getResources().getString(R.string.next_draw)+" "+nextDrawDay);
    }
}





