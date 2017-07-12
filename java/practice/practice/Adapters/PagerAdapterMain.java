package practice.practice.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;

import practice.practice.Constants.UtilConstants;
import practice.practice.DataModel.LotteyBaseInfo;
import practice.practice.DataModel.LottocentreLottDraw;
import practice.practice.Fragments.FragmentLottoTicket;
import practice.practice.Fragments.FragmentLottoList;
import practice.practice.R;

/**
 * Created by ThorneBird on 4/1/2017.
 */

public class PagerAdapterMain extends FragmentStatePagerAdapter {
    private Context mContext;
    private FragmentManager manager;
    private String[] tabs;
    private ArrayList<LottocentreLottDraw> ausOzLottos;
    private ArrayList<LottocentreLottDraw> ausPowerball;
    private ArrayList<LottocentreLottDraw> ausLotto;
    private ArrayList<LottocentreLottDraw> usaPowerBall;
    private ArrayList<LottocentreLottDraw> usaMegaMillions;
    private ArrayList<LottocentreLottDraw> britishLotto;
    private ArrayList<LottocentreLottDraw> canadianLotto;
    private ArrayList<LottocentreLottDraw> euroJackpot;
    private ArrayList<LottocentreLottDraw> euroMillions;
    private ArrayList<LottocentreLottDraw> spanishLotto;
    private ArrayList<LottocentreLottDraw> frenchLotto;
    private ArrayList<LottocentreLottDraw> germanLotto;
    private ArrayList<LottocentreLottDraw> irishLotto;

    public PagerAdapterMain(FragmentManager manager, Context context, ArrayList<LottocentreLottDraw> ausOzLottos,
                            ArrayList<LottocentreLottDraw> ausPowerball,
                            ArrayList<LottocentreLottDraw> ausLotto,
                            ArrayList<LottocentreLottDraw> usaPowerBall,
                            ArrayList<LottocentreLottDraw> usaMegaMillions,
                            ArrayList<LottocentreLottDraw> britishLotto,
                            ArrayList<LottocentreLottDraw> canadianLotto,
                            ArrayList<LottocentreLottDraw> euroJackpot,
                            ArrayList<LottocentreLottDraw> euroMillions,
                            ArrayList<LottocentreLottDraw> spanishLotto,
                            ArrayList<LottocentreLottDraw> frenchLotto,
                            ArrayList<LottocentreLottDraw> germanLotto,
                            ArrayList<LottocentreLottDraw> irishLotto) {
        super(manager);
        this.manager=manager;
        this.mContext = context;
        this.ausOzLottos = ausOzLottos;
        this.ausPowerball = ausPowerball;
        this.ausLotto = ausLotto;
        this.usaPowerBall = usaPowerBall;
        this.usaMegaMillions = usaMegaMillions;
        this.britishLotto = britishLotto;
        this.canadianLotto = canadianLotto;
        this.euroJackpot = euroJackpot;
        this.euroMillions = euroMillions;
        this.spanishLotto = spanishLotto;
        this.frenchLotto = frenchLotto;
        this.germanLotto = germanLotto;
        this.irishLotto = irishLotto;
        tabs = mContext.getResources().getStringArray(R.array.pager_items);
    }

   /* public PagerAdapterMain(FragmentManager manager, Context context, int activity) {
        super(manager);
        this.mContext = context;
        tabs = mContext.getResources().getStringArray(R.array.pager_items);
    }
*/
    @Override
    public Fragment getItem(int position) {

        switch (position) {
                case 0:
                    return new FragmentLottoList().newInstance(ausOzLottos, UtilConstants.AUS_OZ_LOTTO);
                case 1:
                    return new FragmentLottoList().newInstance(ausPowerball, UtilConstants.AUS_POWERBALL);
                case 2:
                    return new FragmentLottoList().newInstance(ausLotto, UtilConstants.AUS_LOTTO);
                case 3:
                    return new FragmentLottoList().newInstance(britishLotto, UtilConstants.BRITISH_LOTTO);
                case 4:
                    return new FragmentLottoList().newInstance(canadianLotto, UtilConstants.CANADIAN_LOTT);
                case 5:
                    return new FragmentLottoList().newInstance(euroMillions, UtilConstants.EURO_MILLIONS);
                case 6:
                    return new FragmentLottoList().newInstance(euroJackpot, UtilConstants.EURO_JACKPOT);
                case 7:
                    return new FragmentLottoList().newInstance(frenchLotto, UtilConstants.FRENCH_LOTTO);
                case 8:
                    return new FragmentLottoList().newInstance(germanLotto, UtilConstants.GERMAN_LOTTO);
                case 9:
                    return new FragmentLottoList().newInstance(irishLotto, UtilConstants.IRISH_LOTTO);
                case 10:
                    return new FragmentLottoList().newInstance(spanishLotto, UtilConstants.SPANISH_LOTTO);
                case 11:
                    return new FragmentLottoList().newInstance(usaMegaMillions, UtilConstants.USA_MEGA_MILLIONS);
                case 12:
                    return new FragmentLottoList().newInstance(usaPowerBall, UtilConstants.USA_POWERBALL);
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