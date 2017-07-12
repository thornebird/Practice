package practice.practice.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import practice.practice.Adapters.PagerAdapterSaved;
import practice.practice.Constants.UtilConstants;
import practice.practice.DAO.SqliteHelperResults;
import practice.practice.DAO.SqliteHelperUserTickets;
import practice.practice.DataModel.LotteyBaseInfo;
import practice.practice.DataModel.LottoResultSql;
import practice.practice.DataModel.LottocentreLottDraw;
import practice.practice.DataModel.UserLotto;
import practice.practice.Fragments.FragmentSavedLottos;
import practice.practice.R;

public class SavedTicketsActivity extends BaseActivity implements FragmentSavedLottos.OnTicketSelected {


    private boolean isFromNotication = false;
    private String[] lottoNames = {"India", "China", "Australia"};
    private int lottoImages[] = {android.R.drawable.alert_dark_frame, android.R.drawable.bottom_bar, android.R.drawable.btn_radio};
    private LinearLayout layoutNumber;
    private PagerAdapterSaved pagerAdapter;
    private ViewPager viewPager;

    private ArrayList<UserLotto> ausOzLotto = new ArrayList<>();
    private ArrayList<UserLotto> ausPowerball = new ArrayList<>();
    private ArrayList<UserLotto> ausLotto = new ArrayList<>();
    private ArrayList<UserLotto> britishLotto = new ArrayList<>();
    private ArrayList<UserLotto> canadianLotot = new ArrayList<>();
    private ArrayList<UserLotto> usaMegamillions = new ArrayList<>();
    private ArrayList<UserLotto> usaPowerball = new ArrayList<>();
    private ArrayList<UserLotto> euroMillions = new ArrayList<>();
    private ArrayList<UserLotto> euroJackpot = new ArrayList<>();
    private ArrayList<UserLotto> frenchLotto = new ArrayList<>();
    private ArrayList<UserLotto> germanLotto = new ArrayList<>();
    private ArrayList<UserLotto> irishLotto = new ArrayList<>();
    private ArrayList<UserLotto> spanishLotto = new ArrayList<>();

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

    private String[] tabsDefault;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_tickets);


        setLists();
        setNavigaitonList();
        setToolbar();




      /*  FragmentSavedLottos fragmentSavedLottos=FragmentSavedLottos.newInstance(lottos);
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame,fragmentSavedLottos).commit();*/


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem itemSearch = menu.findItem(R.id.mi_search);
        itemSearch.setVisible(false);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mi_search:
                Log.i("bar item", "Search");
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void setNavigaitonList() {
        super.setNavigaitonList();
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
    }

    private void setViewPager(String[] tabs) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        pagerAdapter = new PagerAdapterSaved(fragmentManager, this, tabs, listOne, listTwo,
                listThree, listFour, listFive, listSix, listSeven, listEight, listNine, listTen, listEleven, listTwelve, listThirteen);
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


  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        return true;
    }
    */


    private void setLists() {
        SqliteHelperUserTickets sqliteHelperUserSaved = new SqliteHelperUserTickets(this);
        ausOzLotto = sqliteHelperUserSaved.getAllItems(SqliteHelperUserTickets.TABLE_AUSOZLOTTO);
        ausPowerball = sqliteHelperUserSaved.getAllItems(SqliteHelperUserTickets.TABLE_AUSPOWERBALL);
        ausLotto = sqliteHelperUserSaved.getAllItems(SqliteHelperUserTickets.TABLE_AUSLOTTO);
        britishLotto = sqliteHelperUserSaved.getAllItems(SqliteHelperUserTickets.TABLE_BRITISHLOTTO);
        canadianLotot = sqliteHelperUserSaved.getAllItems(SqliteHelperUserTickets.TABLE_CANADIANLOTTO);
        usaMegamillions = sqliteHelperUserSaved.getAllItems(SqliteHelperUserTickets.TABLE_USEMEGAMILLIONS);
        usaPowerball = sqliteHelperUserSaved.getAllItems(SqliteHelperUserTickets.TABLE_USPOWERBALL);
        euroMillions = sqliteHelperUserSaved.getAllItems(SqliteHelperUserTickets.TABLE_EUROMILLIONS);
        euroJackpot = sqliteHelperUserSaved.getAllItems(SqliteHelperUserTickets.TABLE_EUROJACKPOT);
        frenchLotto = sqliteHelperUserSaved.getAllItems(SqliteHelperUserTickets.TABLE_FRENCHLOTTO);
        germanLotto = sqliteHelperUserSaved.getAllItems(SqliteHelperUserTickets.TABLE_GERMANLOTTO);
        irishLotto = sqliteHelperUserSaved.getAllItems(SqliteHelperUserTickets.TABLE_IRISHLOTTO);
        spanishLotto = sqliteHelperUserSaved.getAllItems(SqliteHelperUserTickets.TABLE_SPANISHLOTTO);


        /*if (lottoResults==null||lottoResults.size()==0){
            SqliteHelperResults sqliteHelperResults=new SqliteHelperResults(this);
            lottoResults=sqliteHelperResults.getAllLottoResults();
        }*/

        String[] defaultTabs = getResources().getStringArray(R.array.pager_items);
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

    private ArrayList<UserLotto> getCurrentList(String currentTab) {
        ArrayList<UserLotto> currentList = new ArrayList<>();
        if (currentTab.equals("Aus Oz Lotto")) {
            if (currentList != null)
                currentList.clear();
            currentList.addAll(ausOzLotto);
        } else if (currentTab.equals("Aus Powerball")) {
            if (currentList != null)
                currentList.clear();
            currentList.addAll(ausPowerball);
        } else if (currentTab.equals("Aus Lotto")) {
            if (currentList != null)
                currentList.clear();
            currentList.addAll(ausLotto);
        } else if (currentTab.equals("British Lotto")) {
            if (currentList != null)
                currentList.clear();
            currentList.addAll(britishLotto);
        } else if (currentTab.equals("Canadian Lotto")) {
            if (currentList != null)
                currentList.clear();
            currentList.addAll(canadianLotot);
        } else if (currentTab.equals("Euro Millions")) {
            if (currentList != null)
                currentList.clear();
            currentList.addAll(euroMillions);
        } else if (currentTab.equals("Euro Jackpot")) {
            if (currentList != null)
                currentList.clear();
            currentList.addAll(euroJackpot);
        } else if (currentTab.equals("French Lotto")) {
            if (currentList != null)
                currentList.clear();
            currentList.addAll(frenchLotto);
        } else if (currentTab.equals("German Lotto")) {
            if (currentList != null)
                currentList.clear();
            currentList.addAll(germanLotto);
        } else if (currentTab.equals("Irish Lotto")) {
            if (currentList != null)
                currentList.clear();
            currentList.addAll(irishLotto);
        } else if (currentTab.equals("Spanish Lotto")) {
            if (currentList != null)
                currentList.clear();
            currentList.addAll(spanishLotto);
        } else if (currentTab.equals("Usa Mega Millions")) {
            if (currentList != null)
                currentList.clear();
            currentList.addAll(usaMegamillions);
        } else if (currentTab.equals("Usa Powerball")) {
            if (currentList != null)
                currentList.clear();
            currentList.addAll(usaPowerball);
        }
        return currentList;
    }

    @Override
    public void onTicketSelected(UserLotto userLotto) {
       /* if(userLotto!=null) {
            Intent intent = new Intent(this, LotteryProfileActivity.class);
            intent.putExtra(FragmentSavedLottos.KEY_USERLOTTO, userLotto);
            //intent.putExtra(FragmentSavedLottos.KEY_LOTTODRAW,lottoDraw);
            ///intent.putParcelableArrayListExtra(FragmentSavedLottos.KEY_RESULTS_LIST, lottoResults);
            startActivity(intent);
        }*/
    }
}
