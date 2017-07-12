package practice.practice.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import practice.practice.Adapters.AdapterLottoMenu;
import practice.practice.DataModel.LotteyBaseInfo;
import practice.practice.DataModel.LottoSelectorManager;
import practice.practice.DataModel.UserLotto;
import practice.practice.R;

/**
 * Created by ThorneBird on 5/6/2017.
 */

public class FragmentLottoMenu extends ListFragment {

    private OnLottoSelected mLottoListener;
    private AdapterLottoMenu adapterLottoMenu;
    private final static String KEY_LOTTONAMES = "lottoNames";
    private final static String KEY_ISFIRST = "isFirst";
    private final static String KEY_MENUPOSITION = "menuPosition";
    private ArrayList<String> lottoNames;
    private int menuPosition;
    private String currentLotto;
    private int lottoLoggo;


    public static FragmentLottoMenu newInstance(ArrayList<String> lottoNames, int menuPosition) {
        Bundle args = new Bundle();
        FragmentLottoMenu fragmentListMenu = new FragmentLottoMenu();
        args.putStringArrayList(KEY_LOTTONAMES, lottoNames);
        args.putInt(KEY_MENUPOSITION, menuPosition);
        fragmentListMenu.setArguments(args);
        return fragmentListMenu;
    }

    public void setLottoListener(OnLottoSelected mLottoListener) {
        this.mLottoListener = mLottoListener;
    }

    /**
     * Called to do initial creation of a fragment.  This is called after
     * {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * <p>
     * <p>Note that this can be called while the fragment's activity is
     * still in the process of being created.  As such, you can not rely
     * on things like the activity's content view hierarchy being initialized
     * at this point.  If you want to do work once the activity itself is
     * created, see {@link #onActivityCreated(Bundle)}.
     * <p>
     * <p>Any restored child fragments will be created before the base
     * <code>Fragment.onCreate</code> method returns.</p>
     *
     * @param savedInstanceState If the fragment is being re-created from
     *                           a previous saved state, this is the state.
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            lottoNames = getArguments().getStringArrayList(KEY_LOTTONAMES);
            menuPosition = getArguments().getInt(KEY_MENUPOSITION);
            currentLotto = lottoNames.get(menuPosition);
            LottoSelectorManager manager = new LottoSelectorManager(currentLotto);
            lottoLoggo = manager.getLargeLoggo();
        }
    }

    /**
     * Called to have the fragment instantiate its user interface view.
     * This is optional, and non-graphical fragments can return null (which
     * is the default implementation).  This will be called between
     * {@link #onCreate(Bundle)} and {@link #onActivityCreated(Bundle)}.
     * <p>
     * <p>If you return a View from here, you will later be called in
     * {@link #onDestroyView} when the view is being released.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate
     *                           any views in the fragment,
     * @param container          If non-null, this is the parent view that the fragment's
     *                           UI should be attached to.  The fragment should not add the view itself,
     *                           but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lottomenu, null);
        ImageView im = (ImageView) view.findViewById(R.id.im_lotto);
        ListView lv = (ListView) view.findViewById(android.R.id.list);
        adapterLottoMenu = new AdapterLottoMenu(getContext(), R.layout.fragment_lotto_menu_item, lottoNames, menuPosition);
        lv.setAdapter(adapterLottoMenu);
        im.setImageResource(lottoLoggo);

        return view;
    }



    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */


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
        String lottoName = lottoNames.get(position);
        if (mLottoListener != null) {
            mLottoListener.onLottoSelected(lottoName, position);
        }
    }

    public interface OnLottoSelected {
        public void onLottoSelected(String lottoName, int position);
    }
}
