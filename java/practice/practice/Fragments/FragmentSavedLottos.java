package practice.practice.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.TextViewCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import practice.practice.Adapters.ListAdapterLottoDraw;
import practice.practice.DAO.SqliteHelperLottoFeed;
import practice.practice.DAO.SqliteHelperResults;
import practice.practice.DataModel.LottoResultSql;
import practice.practice.DataModel.LottoSelectorManager;
import practice.practice.DataModel.LottocentreLottDraw;
import practice.practice.DataModel.UserLotto;
import practice.practice.R;

/**
 * Created by ThorneBird on 5/26/2017.
 */

public class FragmentSavedLottos extends ListFragment {

    public final static String KEY_USERLOTTO="userLotto";
    public final static String KEY_LOTTODRAW="drawLotto";
    private final static String KEY_LIST = "list";
    private ArrayList<UserLotto> savedLottoList;
    private OnTicketSelected mListener;

    public static FragmentSavedLottos newInstance(ArrayList<UserLotto> savedLottoList, int position) {
        Bundle args = new Bundle();
        FragmentSavedLottos fragmentSavedLottos = new FragmentSavedLottos();
        args.putParcelableArrayList(KEY_LIST, savedLottoList);
        fragmentSavedLottos.setArguments(args);
        return fragmentSavedLottos;
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            savedLottoList = getArguments().getParcelableArrayList(KEY_LIST);
        }
    }

    /**
     * Called when a fragment is first attached to its context.
     * {@link #onCreate(Bundle)} will be called after this.
     *
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnTicketSelected) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_savedlottolist, null);
        ListView lv = (ListView) view.findViewById(android.R.id.list);
        ListAdapterLottoDraw listAdapterLottoDraw = new ListAdapterLottoDraw(getContext(), R.layout.fragment_lotto_row, savedLottoList);
        lv.setAdapter(listAdapterLottoDraw);
        if (savedLottoList.size() == 0) {
            String nonSaved = getContext().getResources().getString(R.string.non_saved);
            TextView tv = new TextView(getContext());
            tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            tv.setGravity(Gravity.CENTER_HORIZONTAL);
            tv.setGravity(Gravity.CENTER_VERTICAL);
            tv.setText(nonSaved);
            tv.setTypeface(null, Typeface.BOLD_ITALIC);
            //  tv.setTextAppearance(android.R.style.TextAppearance_DeviceDefault_Large);
            TextViewCompat.setTextAppearance(tv, android.R.style.TextAppearance_DeviceDefault_Large);
            LinearLayout layout = (LinearLayout) view.findViewById(R.id.ll_frame);
            layout.addView(tv);
        }
        return view;

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        UserLotto lottoItem = savedLottoList.get(position);
        SqliteHelperLottoFeed sqlFeed = new SqliteHelperLottoFeed(getContext());
        LottoSelectorManager manager = new LottoSelectorManager(lottoItem.getLottoName());

        if(lottoItem!= null) {
            mListener.onTicketSelected(lottoItem);
        }
    }

    public interface OnTicketSelected{
        public void onTicketSelected(UserLotto userLotto);
    }
}
