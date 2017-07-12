package practice.practice.Fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;
import practice.practice.Adapters.ListAdapterLottoResults;
import practice.practice.DataModel.LottoResultSql;
import practice.practice.R;

/**
 * Created by ThorneBird on 6/17/2017.
 */

public class FragmentLottoResults extends ListFragment {
    private final static String KEY_LIST = "list";
    private ArrayList<LottoResultSql> resultsList;

    public static FragmentLottoResults newInstance(ArrayList<LottoResultSql> resultsList, int position) {
        Bundle args = new Bundle();
        FragmentLottoResults fragmentLottoResults = new FragmentLottoResults();
        args.putParcelableArrayList(KEY_LIST, resultsList);
        fragmentLottoResults.setArguments(args);
        return fragmentLottoResults;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            resultsList = getArguments().getParcelableArrayList(KEY_LIST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_savedlottolist, null);
        ListView lv = (ListView) view.findViewById(android.R.id.list);
        ListAdapterLottoResults listAdapterLottoDraw = new ListAdapterLottoResults(getContext(), R.layout.fragment_lotto_result_row, resultsList);
        lv.setAdapter(listAdapterLottoDraw);

        return view;
    }
}
