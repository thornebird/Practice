package practice.practice.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.net.URI;
import java.util.ArrayList;

import practice.practice.Adapters.PagerAdapterResults;
import practice.practice.Constants.UtilConstants;
import practice.practice.DAO.SqliteHelperResults;
import practice.practice.DataModel.LotteyBaseInfo;
import practice.practice.DataModel.LottoResultSql;
import practice.practice.R;


public class TicketResultsActivity extends BaseActivity {
    private PagerAdapterResults pagerAdapter;
    private ViewPager viewPager;
    private boolean isFromNotication;

    private ArrayList<LottoResultSql> ausOzLotto;
    private ArrayList<LottoResultSql> ausPowerball;
    private ArrayList<LottoResultSql> ausLotto;
    private ArrayList<LottoResultSql> britishLotto;
    private ArrayList<LottoResultSql> canadianLotot;
    private ArrayList<LottoResultSql> usaMegamillions;
    private ArrayList<LottoResultSql> usaPowerball;
    private ArrayList<LottoResultSql> euroMillions;
    private ArrayList<LottoResultSql> euroJackpot;
    private ArrayList<LottoResultSql> frenchLotto;
    private ArrayList<LottoResultSql> germanLotto;
    private ArrayList<LottoResultSql> irishLotto;
    private ArrayList<LottoResultSql> spanishLotto;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_results);
        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            isFromNotication = bundle.getBoolean(UtilConstants.IS_FROM_NOTIFCATION);
        }
        setLists();
        setNavigaitonList();
        setToolbar();
    }

    /**
     * Initialize the contents of the Activity's standard options menu.  You
     * should place your menu items in to <var>menu</var>.
     * <p>
     * <p>This is only called once, the first time the options menu is
     * displayed.  To update the menu every time it is displayed, see
     * {@link #onPrepareOptionsMenu}.
     * <p>
     * <p>The default implementation populates the menu with standard system
     * menu items.  These are placed in the {@link Menu#CATEGORY_SYSTEM} group so that
     * they will be correctly ordered with application-defined menu items.
     * Deriving classes should always call through to the base implementation.
     * <p>
     * <p>You can safely hold on to <var>menu</var> (and any items created
     * from it), making modifications to it as desired, until the next
     * time onCreateOptionsMenu() is called.
     * <p>
     * <p>When you add items to the menu, you can implement the Activity's
     * {@link #onOptionsItemSelected} method to handle them there.
     *
     * @param menu The options menu in which you place your items.
     * @return You must return true for the menu to be displayed;
     * if you return false it will not be shown.
     * @see #onPrepareOptionsMenu
     * @see #onOptionsItemSelected
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem itemSearch = menu.findItem(R.id.mi_search);
        itemSearch.setVisible(false);


        return true;
    }

    @Override
    protected void setNavigaitonList() {
        super.setNavigaitonList();
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
    }



    private void setLists(){
        SqliteHelperResults sqliteHelperResults = new SqliteHelperResults(this);

        ausOzLotto=sqliteHelperResults.getLottoResultsName(LotteyBaseInfo.AUSOZLOTTO.getLottoTitle());
        ausPowerball=sqliteHelperResults.getLottoResultsName(LotteyBaseInfo.AUSPOWERBALL.getLottoTitle());
        ausLotto=sqliteHelperResults.getLottoResultsName(LotteyBaseInfo.AUSlOTTO.getLottoTitle());
        britishLotto=sqliteHelperResults.getLottoResultsName(LotteyBaseInfo.BRITISHLOTTO.getLottoTitle());
        canadianLotot=sqliteHelperResults.getLottoResultsName(LotteyBaseInfo.CANADIANLOTTO.getLottoTitle());
        usaMegamillions=sqliteHelperResults.getLottoResultsName(LotteyBaseInfo.USAMEGAMILLIONS.getLottoTitle());
        usaPowerball=sqliteHelperResults.getLottoResultsName(LotteyBaseInfo.USAPOWERBALL.getLottoTitle());
        euroMillions=sqliteHelperResults.getLottoResultsName(LotteyBaseInfo.EUROMILLIONS.getLottoTitle());
        euroJackpot=sqliteHelperResults.getLottoResultsName(LotteyBaseInfo.EUROJACKPOT.getLottoTitle());
        frenchLotto=sqliteHelperResults.getLottoResultsName(LotteyBaseInfo.FRENCHLOTTO.getLottoTitle());
        germanLotto=sqliteHelperResults.getLottoResultsName(LotteyBaseInfo.GERMANLOTTO.getLottoTitle());
        irishLotto=sqliteHelperResults.getLottoResultsName(LotteyBaseInfo.IRISHLOTTO.getLottoTitle());
        spanishLotto=sqliteHelperResults.getLottoResultsName(LotteyBaseInfo.SPANISHLOTTO.getLottoTitle());

        String [] defaultTabs=getResources().getStringArray(R.array.pager_items);
        ArrayList<String> tabs = new ArrayList<>();
        for (int i = 0; i < defaultTabs.length; i++) {
            String currentTab = defaultTabs[i];
            if (currentTab.equals("Aus Oz Lotto") && ausOzLotto.size() > 0) {
                tabs.add(currentTab);
            } else if (currentTab.equals("Aus Powerball") && ausPowerball.size() > 0) {
                tabs.add(currentTab);
            } else if (currentTab.equals("Aus Lotto") && ausLotto.size() > 0) {
                tabs.add(currentTab);
            } else if (currentTab.equals("British Lotto") && britishLotto.size() > 0) {
                tabs.add(currentTab);
            } else if (currentTab.equals("Canadian Lotto") && canadianLotot.size() > 0) {
                tabs.add(currentTab);
            } else if (currentTab.equals("Euro Millions") && euroMillions.size() > 0) {
                tabs.add(currentTab);
            } else if (currentTab.equals("Euro Jackpot") && euroJackpot.size() > 0) {
                tabs.add(currentTab);
            } else if (currentTab.equals("French Lotto") && frenchLotto.size() > 0) {
                tabs.add(currentTab);
            } else if (currentTab.equals("German Lotto") && germanLotto.size() > 0) {
                tabs.add(currentTab);
            } else if (currentTab.equals("Irish Lotto") && irishLotto.size() > 0) {
                tabs.add(currentTab);
            } else if (currentTab.equals("Spanish Lotto") && spanishLotto.size() > 0) {
                tabs.add(currentTab);
            } else if (currentTab.equals("Usa Mega Millions") && usaMegamillions.size() > 0) {
                tabs.add(currentTab);
            } else if (currentTab.equals("Usa Powerball") && usaPowerball.size() > 0) {
                tabs.add(currentTab);
            }
        }

        String[] newTabs = new String[tabs.size()];
        for (int i = 0; i < tabs.size(); i++) {
            String currentTab = tabs.get(i);
            newTabs[i] = currentTab;
            if (i == 0) {
                listOne = getCurrentList(currentTab);
            } else if (i == 1) {
                listTwo = getCurrentList(currentTab);
            } else if (i == 2) {
                listThree = getCurrentList(currentTab);
            } else if (i == 3) {
                listFour = getCurrentList(currentTab);
            } else if (i == 4) {
                listFive = getCurrentList(currentTab);
            } else if (i == 5) {
                listSix = getCurrentList(currentTab);
            } else if (i == 6) {
                listSeven = getCurrentList(currentTab);
            } else if (i == 7) {
                listEight = getCurrentList(currentTab);
            } else if (i == 8) {
                listNine = getCurrentList(currentTab);
            } else if (i == 9) {
                listTen = getCurrentList(currentTab);
            } else if (i == 10) {
                listEleven = getCurrentList(currentTab);
            } else if (i == 11) {
                listTwelve = getCurrentList(currentTab);
            } else if (i == 12) {
                listThirteen = getCurrentList(currentTab);
            }
        }
        setViewPager(newTabs);
    }

    private ArrayList<LottoResultSql> getCurrentList(String currentTab) {
        ArrayList<LottoResultSql> currentList = new ArrayList<>();
        if (currentTab.equals(UtilConstants.AUSOZLOTTO)) {
            if (currentList != null)
                currentList.clear();
            currentList.addAll(ausOzLotto);
        } else if (currentTab.equals(UtilConstants.AUSPOWERBALL)) {
            if (currentList != null)
                currentList.clear();
            currentList.addAll(ausPowerball);
        } else if (currentTab.equals(UtilConstants.AUS_LOTTO)) {
            if (currentList != null)
                currentList.clear();
            currentList.addAll(ausLotto);
        } else if (currentTab.equals(UtilConstants.BRITISHLOTTO)) {
            if (currentList != null)
                currentList.clear();
            currentList.addAll(britishLotto);
        } else if (currentTab.equals(UtilConstants.CANADIANLOTTO)) {
            if (currentList != null)
                currentList.clear();
            currentList.addAll(canadianLotot);
        } else if (currentTab.equals(UtilConstants.EUROMILLIONS)) {
            if (currentList != null)
                currentList.clear();
            currentList.addAll(euroMillions);
        } else if (currentTab.equals(UtilConstants.EURO_JACKPOT) ) {
            if (currentList != null)
                currentList.clear();
            currentList.addAll(euroJackpot);
        } else if (currentTab.equals(UtilConstants.FRENCHLOTTO)) {
            if (currentList != null)
                currentList.clear();
            currentList.addAll(frenchLotto);
        } else if (currentTab.equals(UtilConstants.GERMANLOTTO)) {
            if (currentList != null)
                currentList.clear();
            currentList.addAll(germanLotto);
        } else if (currentTab.equals(UtilConstants.IRISHLOTTO)) {
            if (currentList != null)
                currentList.clear();
            currentList.addAll(irishLotto);
        } else if (currentTab.equals(UtilConstants.SPANISHLOTTO)) {
            if (currentList != null)
                currentList.clear();
            currentList.addAll(spanishLotto);
        } else if (currentTab.equals(UtilConstants.USMEGAMILLIONS)) {
            if (currentList != null)
                currentList.clear();
            currentList.addAll(usaMegamillions);
        } else if (currentTab.equals(UtilConstants.USPOWERBALL)) {
            if (currentList != null)
                currentList.clear();
            currentList.addAll(usaPowerball);
        }
        return currentList;
    }

    private void setViewPager(String [] tabs) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        pagerAdapter = new PagerAdapterResults(fragmentManager, this,tabs ,listOne,listTwo ,
                listThree, listFour,listFive , listSix, listSeven,listEight,listNine,listTen,listEleven,listTwelve,listThirteen);
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


}
