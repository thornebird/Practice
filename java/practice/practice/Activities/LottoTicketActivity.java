package practice.practice.Activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import practice.practice.Adapters.PagerAdapterMain;
import practice.practice.DAO.SqliteHelperAlarms;
import practice.practice.DAO.SqliteHelperUserTickets;
import practice.practice.DataModel.LotteyBaseInfo;
import practice.practice.DataModel.LottoSelectorManager;
import practice.practice.DataModel.UserLotto;
import practice.practice.Fragments.FragmentLottoMenu;
import practice.practice.Fragments.FragmentLottoTicket;
import practice.practice.LottoCheckerReceiever;
import practice.practice.R;

public class LottoTicketActivity extends BaseActivity implements FragmentLottoTicket.OnLottoSelected, FragmentLottoMenu.OnLottoSelected {

    private String lottoName = "";
    private int highesNumber;
    private int highestBonusNumber;
    private PagerAdapterMain pagerAdapter;
    private ViewPager viewPager;
    private LottoSelectorManager manager;
    private FragmentLottoMenu fragmentLottoMenu;
    private ArrayList<String> lottoMenuNames;
    private int currentFragment = 1;
    private final static int FRAGMENT_MENU = 0;
    private final static int FRAGMENT_TICKET = 1;
    private int menuPosition = 0;
    private boolean isNotified = false;
    private final static int SAVED_SUCCESFULLY = 0;
    private final static int SAVED_FAIL = 1;
    private final static int SAVED_NOTIFICATION = 2;
    private SharedPreferences prefs;
    private final static String KEY_PREFS = "prefs";
    private final static String KEY_NOTIFIED = "notified";
    private boolean isDefault = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lotto_alarm);
        lottoName = LotteyBaseInfo.AUSOZLOTTO.getLottoTitle();
        prefs = getSharedPreferences(KEY_PREFS, Context.MODE_PRIVATE);
        setNavigaitonList();
        setToolbar();
        createMenuList();
        setMenuFragment();
        getHighesNumbers();

    }

    /*public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
       *//* MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem itemSearch = menu.findItem(R.id.mi_search);
        itemSearch.setVisible(true);
        itemSearch.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);*//*

        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mi_search:
                if (currentFragment == FRAGMENT_MENU) {
                    isDefault = false;
                    setMenuFragment();
                } else if (currentFragment == FRAGMENT_TICKET) {
                    setTicketFragment();
                }
                setCurrentFragment();
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void setNavigaitonList() {
        super.setNavigaitonList();
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
    }


    private void setCurrentFragment() {
        if (currentFragment == FRAGMENT_MENU) {
            currentFragment = FRAGMENT_TICKET;
        } else if (currentFragment == FRAGMENT_TICKET) {
            currentFragment = FRAGMENT_MENU;
        }
    }

    private void getHighesNumbers() {
        manager = new LottoSelectorManager(lottoName);
        highesNumber = manager.getHighesNumber();
        highestBonusNumber = manager.getHighestBonusNumber();
    }

    @Override
    public void onLottoSelected(String lottoName, int menuPosition) {
        this.lottoName = lottoName;
        this.menuPosition = menuPosition;
        setCurrentFragment();
        setTicketFragment();
    }


    @Override
    public void onSave(UserLotto userLotto) {

        SqliteHelperUserTickets sqliteHelperUserSaved = new SqliteHelperUserTickets(this);

        if (!sqliteHelperUserSaved.isItemExist(userLotto.getSqliteTicketTableName(), userLotto.getLottoName(), userLotto.getDrawDate(), userLotto.getLottoNumbers(), userLotto.getBonusNumbers())) {
            if (sqliteHelperUserSaved.addLottoItem(userLotto.getSqliteTicketTableName(), userLotto)) {
                showMessage(userLotto.getLottoName(), SAVED_SUCCESFULLY);
                //// Set alarm for lottDate
                SqliteHelperAlarms sqliteHelperAlarms = new SqliteHelperAlarms(this);
                sqliteHelperAlarms.addAlarm(userLotto.getSqliteTicketTableName(), userLotto.getLottoName(), userLotto.getDrawDate());
                //createAlarm();
            }
        } else {
            showMessage(userLotto.getLottoName(), SAVED_FAIL);
        }

    }

    private void createMenuList() {
        lottoMenuNames = new ArrayList<>();
        for (LotteyBaseInfo info : LotteyBaseInfo.values()) {
            lottoMenuNames.add(info.getLottoTitle());
        }
    }


    private void setMenuFragment() {
        fragmentLottoMenu = FragmentLottoMenu.newInstance(lottoMenuNames, menuPosition);
        fragmentLottoMenu.setLottoListener(this);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!isDefault)
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        transaction.replace(R.id.content_frame, fragmentLottoMenu).commit();
    }

    private void setTicketFragment() {
        if (!prefs.getBoolean(KEY_NOTIFIED, false)) {
            showMessage("", SAVED_NOTIFICATION);
        }
        FragmentLottoTicket fragmentLottoTicket =
                FragmentLottoTicket.newInstance(lottoName, "lottoDrawDate", highesNumber, highestBonusNumber);
        fragmentLottoTicket.setListener(this);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
        transaction.replace(R.id.content_frame, fragmentLottoTicket).commit();
    }

    private void showMessage(String lottoName, int saveState) {
        View parentLayout = findViewById(R.id.content_frame);
        switch (saveState) {
            case SAVED_SUCCESFULLY:
                String messageSuccess = String.format(getResources().getString(R.string.saved_lotto), lottoName);
                final Snackbar snackbarSaved = Snackbar.make(parentLayout, messageSuccess, Snackbar.LENGTH_LONG);
                snackbarSaved.setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbarSaved.dismiss();
                    }
                });
                snackbarSaved.show();
                break;
            case SAVED_FAIL:
                String messageFail = String.format(getResources().getString(R.string.saved_failed_lotto), lottoName);
                final Snackbar snackbarFailed = Snackbar.make(parentLayout, messageFail, Snackbar.LENGTH_LONG);
                snackbarFailed.setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbarFailed.dismiss();
                    }
                });
                snackbarFailed.show();
                break;
            case SAVED_NOTIFICATION:
                String message = getResources().getString(R.string.saved_notify);
                final Snackbar snackbarNoti = Snackbar.make(parentLayout, message, Snackbar.LENGTH_INDEFINITE);
                snackbarNoti.setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbarNoti.dismiss();
                        prefs.edit().putBoolean(KEY_NOTIFIED, true).commit();
                    }
                });
                snackbarNoti.show();
                break;
        }

    }


   /* private void createAlarm() {
        Intent intent = new Intent(LottoTicketActivity.this, LottoCheckerReceiever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), ALARM_CODE, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                + (50), pendingIntent);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), AlarmManager.INTERVAL_FIFTEEN_MINUTES, pendingIntent);
    }*/
}
