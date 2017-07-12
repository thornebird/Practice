package practice.practice.Activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import practice.practice.Adapters.CustomDrawerAdapter;
import practice.practice.Adapters.PagerAdapterMain;
import practice.practice.DAO.SqliteHelperAlarms;
import practice.practice.DAO.SqliteHelperLottoFeed;
import practice.practice.DataModel.LotteyBaseInfo;
import practice.practice.DataModel.LottocentreLottDraw;
import practice.practice.DataModel.UserAlarm;
import practice.practice.DataModel.UserLotto;
import practice.practice.DataModel.DrawerItem;
import practice.practice.Fragments.FragmentLottoMenu;
import practice.practice.LottoCheckerReceiever;
import practice.practice.MyApplication;
import practice.practice.R;

public class MainActivity extends BaseActivity implements FragmentLottoMenu.OnLottoSelected {

    private MyApplication application;
    public final int ALARM_CODE = 1111;
    private MenuItem itemSearchView;
    private DrawerLayout mDrawerLayout;
    private AutoCompleteTextView autotvSearch;
    private ListView mDrawerList;
    private Context context;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private ViewPager viewPager;
    private PagerAdapterMain pagerAdapter;
    private FrameLayout frameLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private CustomDrawerAdapter adapter;
    private ArrayList<UserLotto> defaultLotto;
    private ArrayList<LottocentreLottDraw> lottDraws;
    private List<DrawerItem> dataList;
    private SqliteHelperLottoFeed dbHelper;
    private FragmentLottoMenu fragmentLottoMenu;
    private int currentFragment = 1;
    private final static int FRAGMENT_MENU = 0;
    private final static int FRAGMENT_LOTTO_LIST = 1;
    private ArrayList<String> lottoMenuNames;
    private int menuPosition = 0;
    private final static String FRAGMETN_DIALOG = "fragmentDialog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        application = (MyApplication) getApplication();
        context = this;
        createMenuList();
        setNavigaitonList();
        setToolbar();

        //setLottoLists();
        setViewPager();
        //setSpinner();

        SqliteHelperAlarms sqliteHelperAlarms = new SqliteHelperAlarms(this);
        ArrayList<UserAlarm> list = sqliteHelperAlarms.getAllAlarms();
        //lottDraws = getIntent().getParcelableArrayListExtra("KEY");
        //setToolbar();
        // setNavigaitonList();

        //setLottoLists();
        //setDefaultLotto();
        //  setLottoAlarm();

    }


    @Override
    protected void setNavigaitonList() {
        super.setNavigaitonList();
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mi_search:
                Log.i("bar item", "Search");
                if (currentFragment != FRAGMENT_MENU) {
                    setMenuFragment();
                    setCurrentFragment();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /*private void setAlarmsFragment() {
     //   fragmentLottoMenu.setLottoListener(this);

            SqliteHelperAlarms sqliteHelperAlarms = new SqliteHelperAlarms(this);
            ArrayList<UserAlarm>alarmList = sqliteHelperAlarms.getAllAlarms();

        if(getSupportFragmentManager().findFragmentByTag(FRAGMETN_DIALOG)==null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            fragmentAlarms = FragmentAlarms.newInstance();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
            transaction.add(R.id.content_frame, fragmentAlarms,FRAGMETN_DIALOG).commit();
        }
    }*/


    private void setViewPager() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        pagerAdapter = new PagerAdapterMain(fragmentManager, this, /*PagerAdapterMain.ACTIVITY_MAIN,*/ application.getAusOzLottos(), application.getAusPowerBall(),
                application.getAusLotto(), application.getUsaPowerBall(), application.getUsaMegaMillions(), application.getBritishLotto(), application.getCanadianLotto()
                , application.getEuroJackpot(), application.getEuroMillions(), application.getSpanishLotto(), application.getFrenchLotto(), application.getGermanLotto(), application.getIrishLotto());
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


   /* private void setLottoLists() {
        dbHelper = new SqliteHelperLottoFeed(this);
        ausOzLottos = dbHelper.getItemsLotto(SqliteHelperLottoFeed.TABLE_AUSOZLOTTO);
        ausPowerBall = dbHelper.getItemsLotto(SqliteHelperLottoFeed.TABLE_AUSPOWERBALL);
        ausLotto = dbHelper.getItemsLotto(SqliteHelperLottoFeed.TABLE_AUSLOTTO);
        britishLotto = dbHelper.getItemsLotto(SqliteHelperLottoFeed.TABLE_BRITISHLOTTO);
        canadianLotto = dbHelper.getItemsLotto(SqliteHelperLottoFeed.TABLE_CANADIANLOTTO);
        usaMegaMillions = dbHelper.getItemsLotto(SqliteHelperLottoFeed.TABLE_USEMEGAMILLIONS);
        euroMillions = dbHelper.getItemsLotto(SqliteHelperLottoFeed.TABLE_EUROMILLIONS);
        euroJackpot = dbHelper.getItemsLotto(SqliteHelperLottoFeed.TABLE_EUROJACKPOT);
        spanishLotto = dbHelper.getItemsLotto(SqliteHelperLottoFeed.TABLE_SPANISHLOTTO);
        frenchLotto = dbHelper.getItemsLotto(SqliteHelperLottoFeed.TABLE_FRENCHLOTTO);
        germanLotto = dbHelper.getItemsLotto(SqliteHelperLottoFeed.TABLE_GERMANLOTTO);
        irishLotto = dbHelper.getItemsLotto(SqliteHelperLottoFeed.TABLE_IRISHLOTTO);
        usaPowerBall = dbHelper.getItemsLotto(SqliteHelperLottoFeed.TABLE_USPOWERBALL);
    }*/
    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

   /* @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setScrimColor(getResources().getColor(android.R.color.transparent));
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        adapter = new CustomDrawerAdapter(this, R.layout.custom_drawer, dataList);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(actionBarDrawerToggle);
    }

    private void setNavigaitonList() {
        dataList.add(new DrawerItem(getResources().getString(R.string.nav_item_recent), R.drawable.abc_ab_share_pack_mtrl_alpha));
        dataList.add(new DrawerItem(getResources().getString(R.string.nav_item_notificaiton), R.drawable.abc_ab_share_pack_mtrl_alpha));
        dataList.add(new DrawerItem(getResources().getString(R.string.nav_item_saved_tickets), R.drawable.abc_ab_share_pack_mtrl_alpha));
    }*/

    /*private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                long arg3) {
            // TODO Auto-generated method stub
            //  Toast.makeText(MainActivity.this," clicked on item position "+position,Toast.LENGTH_SHORT).show();
            Snackbar.make(findViewById(android.R.id.content), "Had a snack at Snackbar", Snackbar.LENGTH_LONG)
                    .setAction("Undo", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    })
                    .setActionTextColor(Color.RED)
                    .show();

            switch (position) {
                case 0:
                    Intent intent = new Intent(MainActivity.this, ScanTicketActivity.class);
                    startActivity(intent);
                    break;
                case 1:
                    Intent intent2 = new Intent(MainActivity.this, ScannerActivity.class);
                    startActivity(intent2);
                    break;
                case 2:
                    Intent intent3 = new Intent(MainActivity.this, SavedTicketsActivity.class);
                    startActivity(intent3);
                    break;
            }
        }

    }*/

    private void setLottoAlarm() {
        Intent intent = new Intent(MainActivity.this, LottoCheckerReceiever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), ALARM_CODE, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                + (50), pendingIntent);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), AlarmManager.INTERVAL_FIFTEEN_MINUTES, pendingIntent);
    }


    private void setCurrentFragment() {
        if (currentFragment == FRAGMENT_MENU) {
            currentFragment = FRAGMENT_LOTTO_LIST;
        } else if (currentFragment == FRAGMENT_LOTTO_LIST) {
            currentFragment = FRAGMENT_MENU;
        }
    }

    private void createMenuList() {
        lottoMenuNames = new ArrayList<>();
        for (LotteyBaseInfo info : LotteyBaseInfo.values()) {
            lottoMenuNames.add(info.getLottoTitle());
        }
    }

    private void setMenuFragment() {
        menuPosition = viewPager.getCurrentItem();
        fragmentLottoMenu = FragmentLottoMenu.newInstance(lottoMenuNames, menuPosition);
        fragmentLottoMenu.setLottoListener(this);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        // if(getSupportFragmentManager().findFragmentByTag(FRAGMETN_DIALOG)!=null&& fragmentAlarms !=null)
        //  transaction.remove(fragmentAlarms);
        transaction.replace(R.id.content_frame, fragmentLottoMenu).commit();

    }

    /* private void removeSearchFragment(){
         FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
         transaction.
     }
 */
    @Override
    public void onLottoSelected(String lottoName, int position) {
        if (fragmentLottoMenu != null && fragmentLottoMenu.isVisible()) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            //  transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
            transaction.remove(fragmentLottoMenu).commit();
        }
        setViewPager();
        setCurrentFragment();
        viewPager.setCurrentItem(position);

    }



    /*private void setLottoLists() {

        ausOzLottos = new ArrayList<>();
        usaPowerBall = new ArrayList<>();
        ausPowerBall = new ArrayList<>();
        ausLotto = new ArrayList<>();
        britishLotto = new ArrayList<>();
        canadianLotto = new ArrayList<>();
        usaMegaMillions = new ArrayList<>();
        euroMillions = new ArrayList<>();
        euroJackpot = new ArrayList<>();
        spanishLotto = new ArrayList<>();
        frenchLotto = new ArrayList<>();
        germanLotto = new ArrayList<>();
        irishLotto = new ArrayList<>();

        for (LottocentreLottDraw item : lottDraws) {
            String title = item.getTitle();

            if (title.contains("AUSTRALIAN OZ LOTTO")) {
                if(!dbHelper.isLottoExist(SqliteHelperLottoFeed.TABLE_AUSLOTTO,title)) {
                    dbHelper.addItemLotto(item, SqliteHelperLottoFeed.TABLE_AUSOZLOTTO);
                }
                ausOzLottos.add(item);
                Log.i("oz lotto", title);
            } else if (title.contains("AUSTRALIAN POWERBALL")) {
                if(!dbHelper.isLottoExist(SqliteHelperLottoFeed.TABLE_AUSPOWERBALL,title)){
                    dbHelper.addItemLotto(item,SqliteHelperLottoFeed.TABLE_AUSPOWERBALL);
                }

                ausPowerBall.add(item);
                Log.i("aus powerball", title);
            } else if (title.contains("AUSTRALIAN LOTTO")) {
                if(!dbHelper.isLottoExist(SqliteHelperLottoFeed.TABLE_AUSLOTTO,title)){
                    dbHelper.addItemLotto(item,SqliteHelperLottoFeed.TABLE_AUSLOTTO);
                }

                ausLotto.add(item);
                Log.i("Aus lotto", title);
            } else if (title.contains("US POWERBALL")) {
               if(!dbHelper.isLottoExist(SqliteHelperLottoFeed.TABLE_USPOWERBALL,title)){
                   dbHelper.addItemLotto(item,SqliteHelperLottoFeed.TABLE_USPOWERBALL);
                }


                Log.i("us powerball", title);
                usaPowerBall.add(item);
            } else if (title.contains("British Lotto")) {
                if (!dbHelper.isLottoExist(SqliteHelperLottoFeed.TABLE_BRITISHLOTTO,title)) {
                    dbHelper.addItemLotto(item, SqliteHelperLottoFeed.TABLE_BRITISHLOTTO);
                }
                Log.i("British Lotto", title);
                britishLotto.add(item);
            } else if (title.contains("CANADIAN LOTTO")) {
                if(!dbHelper.isLottoExist(SqliteHelperLottoFeed.TABLE_CANADIANLOTTO,title)) {
                    dbHelper.addItemLotto(item, SqliteHelperLottoFeed.TABLE_CANADIANLOTTO);
                }
                Log.i("CANADIAN LOTTO", title);
                canadianLotto.add(item);
            } else if (title.contains("US MEGA MILLIONS")) {
               if(!dbHelper.isLottoExist(SqliteHelperLottoFeed.TABLE_USEMEGAMILLIONS,title)) {
                   dbHelper.addItemLotto(item, SqliteHelperLottoFeed.TABLE_USEMEGAMILLIONS);
               }
                Log.i("US MEGA MILLIONS", title);
                usaMegaMillions.add(item);
            } else if (title.contains("Eurojackpot")) {
                if(!dbHelper.isLottoExist(SqliteHelperLottoFeed.TABLE_EUROJACKPOT,title)) {
                    dbHelper.addItemLotto(item, SqliteHelperLottoFeed.TABLE_EUROJACKPOT);
                }
                Log.i("Eurojackpot", title);
                euroJackpot.add(item);
            } else if (title.contains("EuroMillions")) {
                if(!dbHelper.isLottoExist(SqliteHelperLottoFeed.TABLE_EUROMILLIONS,title)) {
                    dbHelper.addItemLotto(item, SqliteHelperLottoFeed.TABLE_EUROMILLIONS);
                }
                Log.i("EuroMillions", title);
                euroMillions.add(item);
            } else if (title.contains("Spanish Lotto")) {
                if(!dbHelper.isLottoExist(SqliteHelperLottoFeed.TABLE_SPANISHLOTTO,title)) {
                    dbHelper.addItemLotto(item, SqliteHelperLottoFeed.TABLE_SPANISHLOTTO);
                }
                Log.i("Spanish Lotto", title);
                spanishLotto.add(item);
            } else if (title.contains("French Lotto")) {
                if(!dbHelper.isLottoExist(SqliteHelperLottoFeed.TABLE_FRENCHLOTTO,title)) {
                    dbHelper.addItemLotto(item, SqliteHelperLottoFeed.TABLE_FRENCHLOTTO);
                }
                Log.i("French Lotto", title);
                frenchLotto.add(item);
            } else if (title.contains("German Lotto")) {
                if(!dbHelper.isLottoExist(SqliteHelperLottoFeed.TABLE_GERMANLOTTO,title)) {
                    dbHelper.addItemLotto(item, SqliteHelperLottoFeed.TABLE_GERMANLOTTO);
                }
                Log.i("German Lotto", title);
                germanLotto.add(item);
            } else if (title.contains("Irish Lotto")) {
                if(!dbHelper.isLottoExist(SqliteHelperLottoFeed.TABLE_IRISHLOTTO,title)) {
                    dbHelper.addItemLotto(item, SqliteHelperLottoFeed.TABLE_IRISHLOTTO);
                }
                Log.i("Irish Lotto", title);
                irishLotto.add(item);
            }

        }
        ArrayList<LottocentreLottDraw> list=dbHelper.getItemsLotto(SqliteHelperLottoFeed.TABLE_AUSLOTTO);
        for(int i=0;i<list.size();i++){
            Log.i("SQL",list.get(i).getTitle()+":"+list.get(i).getDescription());
        }
    }*/

}
