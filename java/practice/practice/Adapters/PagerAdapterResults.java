package practice.practice.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import java.util.ArrayList;
import practice.practice.Constants.UtilConstants;
import practice.practice.DataModel.LottoResultSql;
import practice.practice.Fragments.FragmentLottoResults;

/**
 * Created by ThorneBird on 6/17/2017.
 */

public class PagerAdapterResults  extends FragmentStatePagerAdapter {

    private Context mContext;
    private String[] tabs;
    private ArrayList<LottoResultSql> listOne;
    private ArrayList<LottoResultSql> listTwo;
    private ArrayList<LottoResultSql> listThree;
    private ArrayList<LottoResultSql> listFour;
    private ArrayList<LottoResultSql> listFive;
    private ArrayList<LottoResultSql> listSix;
    private ArrayList<LottoResultSql> listSeven;
    private ArrayList<LottoResultSql> listEight;
    private ArrayList<LottoResultSql> listNine;
    private ArrayList<LottoResultSql> listTen;
    private ArrayList<LottoResultSql> listEleven;
    private ArrayList<LottoResultSql> listTwelve;
    private ArrayList<LottoResultSql> listThirteen;
    private ArrayList<LottoResultSql>resultList;
/*    /////////////////////
    private ArrayList<UserLotto> ausOzLotto;
    private ArrayList<UserLotto> ausPowerball;
    private ArrayList<UserLotto> ausLotto;
    private ArrayList<UserLotto> usaPowerBall;
    private ArrayList<UserLotto> usaMegaMillions;
    private ArrayList<UserLotto> britishLotto;
    private ArrayList<UserLotto> canadianLotto;
    private ArrayList<UserLotto> euroJackpot;
    private ArrayList<UserLotto> euroMillions;
    private ArrayList<UserLotto> spanishLotto;
    private ArrayList<UserLotto> frenchLotto;
    private ArrayList<UserLotto> germanLotto;
    private ArrayList<UserLotto> irishLotto;*/

    public PagerAdapterResults(FragmentManager manager, Context context, String[] tabs, ArrayList<LottoResultSql> listOne,
                             ArrayList<LottoResultSql> listTwo,
                             ArrayList<LottoResultSql> listThree,
                             ArrayList<LottoResultSql> listFour,
                             ArrayList<LottoResultSql> listFive,
                             ArrayList<LottoResultSql> listSix,
                             ArrayList<LottoResultSql> listSeven,
                             ArrayList<LottoResultSql> listEight,
                             ArrayList<LottoResultSql> listNine,
                             ArrayList<LottoResultSql> listTen,
                             ArrayList<LottoResultSql> listEleven,
                             ArrayList<LottoResultSql> listTwelve,
                             ArrayList<LottoResultSql> listThirteen) {
        super(manager);
        this.mContext = context;
        this.tabs = tabs;
        this.listOne = listOne;
        this.listTwo = listTwo;
        this.listThree = listThree;
        this.listFour = listFour;
        this.listFive = listFive;
        this.listSix = listSix;
        this.listSeven = listSeven;
        this.listEight = listEight;
        this.listNine = listNine;
        this.listTen = listTen;
        this.listEleven = listEleven;
        this.listTwelve = listTwelve;
        this.listThirteen = listThirteen;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new FragmentLottoResults().newInstance(listOne, UtilConstants.AUS_OZ_LOTTO);
            case 1:
                return new FragmentLottoResults().newInstance(listTwo, UtilConstants.AUS_POWERBALL);
            case 2:
                return new FragmentLottoResults().newInstance(listThree, UtilConstants.AUS_LOTTO);
            case 3:
                return new FragmentLottoResults().newInstance(listFour, UtilConstants.USA_POWERBALL);
            case 4:
                return new FragmentLottoResults().newInstance(listFive, UtilConstants.USA_MEGA_MILLIONS);
            case 5:
                return new FragmentLottoResults().newInstance(listSix, UtilConstants.BRITISH_LOTTO);
            case 6:
                return new FragmentLottoResults().newInstance(listSeven, UtilConstants.CANADIAN_LOTT);
            case 7:
                return new FragmentLottoResults().newInstance(listEight, UtilConstants.EURO_JACKPOT);
            case 8:
                return new FragmentLottoResults().newInstance(listNine, UtilConstants.EURO_MILLIONS);
            case 9:
                return new FragmentLottoResults().newInstance(listTen, UtilConstants.SPANISH_LOTTO);
            case 10:
                return new FragmentLottoResults().newInstance(listEleven, UtilConstants.FRENCH_LOTTO);
            case 11:
                return new FragmentLottoResults().newInstance(listTwelve, UtilConstants.GERMAN_LOTTO);
            case 12:
                return new FragmentLottoResults().newInstance(listThirteen, UtilConstants.IRISH_LOTTO);
            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return tabs.length;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }


}
