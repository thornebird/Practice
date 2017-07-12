package practice.practice.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import practice.practice.Constants.UtilConstants;
import practice.practice.DataModel.LottoResultSql;
import practice.practice.DataModel.UserLotto;
import practice.practice.Fragments.FragmentLottoResults;
import practice.practice.Fragments.FragmentSavedLottos;

/**
 * Created by ThorneBird on 6/3/2017.
 */

public class PagerAdapterSaved extends FragmentStatePagerAdapter {
    private Context mContext;
    private String[] tabs;
    private ArrayList<UserLotto> listOne;
    private ArrayList<UserLotto> listTwo;
    private ArrayList<UserLotto> listThree;
    private ArrayList<UserLotto> listFour;
    private ArrayList<UserLotto> listFive;
    private ArrayList<UserLotto> listSix;
    private ArrayList<UserLotto> listSeven;
    private ArrayList<UserLotto> listEight;
    private ArrayList<UserLotto> listNine;
    private ArrayList<UserLotto> listTen;
    private ArrayList<UserLotto> listEleven;
    private ArrayList<UserLotto> listTwelve;
    private ArrayList<UserLotto> listThirteen;
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

    public PagerAdapterSaved(FragmentManager manager, Context context, String[] tabs, ArrayList<UserLotto> listOne,
                             ArrayList<UserLotto> listTwo,
                             ArrayList<UserLotto> listThree,
                             ArrayList<UserLotto> listFour,
                             ArrayList<UserLotto> listFive,
                             ArrayList<UserLotto> listSix,
                             ArrayList<UserLotto> listSeven,
                             ArrayList<UserLotto> listEight,
                             ArrayList<UserLotto> listNine,
                             ArrayList<UserLotto> listTen,
                             ArrayList<UserLotto> listEleven,
                             ArrayList<UserLotto> listTwelve,
                             ArrayList<UserLotto> listThirteen) {
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
        //setLists();
       /* this.listOne = ausOzLottos;
        this.listTwo = ausPowerball;
        this.listFour = ausLotto;
        this.listFour = usaPowerBall;
        this.listFive = usaMegaMillions;
        this.listSix = britishLotto;
        this.listSeven = canadianLotto;
        this.listNine = euroJackpot;
        this.listNine = euroMillions;
        this.listTen = spanishLotto;
        this.listEleven = frenchLotto;
        this.listTwelve = germanLotto;
        this.listThirteen = irishLotto;*/
         /*mContext.getResources().getStringArray(R.array.pager_items)*/
        ;
    }


    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new FragmentSavedLottos().newInstance(listOne, UtilConstants.AUS_OZ_LOTTO);
            case 1:
                return new FragmentSavedLottos().newInstance(listTwo, UtilConstants.AUS_POWERBALL);
            case 2:
                return new FragmentSavedLottos().newInstance(listThree, UtilConstants.AUS_LOTTO);
            case 3:
                return new FragmentSavedLottos().newInstance(listFour, UtilConstants.USA_POWERBALL);
            case 4:
                return new FragmentSavedLottos().newInstance(listFive, UtilConstants.USA_MEGA_MILLIONS);
            case 5:
                return new FragmentSavedLottos().newInstance(listSix, UtilConstants.BRITISH_LOTTO);
            case 6:
                return new FragmentSavedLottos().newInstance(listSeven, UtilConstants.CANADIAN_LOTT);
            case 7:
                return new FragmentSavedLottos().newInstance(listEight, UtilConstants.EURO_JACKPOT);
            case 8:
                return new FragmentSavedLottos().newInstance(listNine, UtilConstants.EURO_MILLIONS);
            case 9:
                return new FragmentSavedLottos().newInstance(listTen, UtilConstants.SPANISH_LOTTO);
            case 10:
                return new FragmentSavedLottos().newInstance(listEleven, UtilConstants.FRENCH_LOTTO);
            case 11:
                return new FragmentSavedLottos().newInstance(listTwelve, UtilConstants.GERMAN_LOTTO);
            case 12:
                return new FragmentSavedLottos().newInstance(listThirteen, UtilConstants.IRISH_LOTTO);
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
