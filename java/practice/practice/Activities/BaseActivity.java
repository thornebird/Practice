package practice.practice.Activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import practice.practice.Adapters.CustomDrawerAdapter;
import practice.practice.Constants.UtilConstants;
import practice.practice.DataModel.DrawerItem;
import practice.practice.R;

public abstract class BaseActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private CustomDrawerAdapter adapter;
    private CharSequence mTitle;
    private ListView mDrawerList;
    private List<DrawerItem> dataList;
    private CharSequence mDrawerTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

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
        itemSearch.setVisible(true);
        itemSearch.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);


        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }


    protected void setNavigaitonList() {
        dataList = new ArrayList<DrawerItem>();
        dataList.add(new DrawerItem(getResources().getString(R.string.nav_item_recent), R.drawable.ic_crop_rotate));
        dataList.add(new DrawerItem(getResources().getString(R.string.nav_item_notificaiton), R.drawable.ic_account_balance_wallet));
        dataList.add(new DrawerItem(getResources().getString(R.string.nav_item_saved_tickets), R.drawable.ic_save));
        
        dataList.add(new DrawerItem(getResources().getString(R.string.nav_ticket_results), R.drawable.ic_gamepad));
        /*SqliteHelperUserTickets sqlTickets = new SqliteHelperUserTickets(this);
        if (sqlTickets.getAllItems(SqliteHelperUserTickets.TABLE_AUSOZLOTTO).size() > 0 ||
                sqlTickets.getAllItems(SqliteHelperUserTickets.TABLE_AUSPOWERBALL).size() > 0 ||
                sqlTickets.getAllItems(SqliteHelperUserTickets.TABLE_AUSLOTTO).size() > 0 ||
                sqlTickets.getAllItems(SqliteHelperUserTickets.TABLE_USPOWERBALL).size() > 0 ||
                sqlTickets.getAllItems(SqliteHelperUserTickets.TABLE_USEMEGAMILLIONS).size() > 0 ||
                sqlTickets.getAllItems(SqliteHelperUserTickets.TABLE_BRITISHLOTTO).size() > 0 ||
                sqlTickets.getAllItems(SqliteHelperUserTickets.TABLE_CANADIANLOTTO).size() > 0 ||
                sqlTickets.getAllItems(SqliteHelperUserTickets.TABLE_EUROJACKPOT).size() > 0 ||
                sqlTickets.getAllItems(SqliteHelperUserTickets.TABLE_EUROMILLIONS).size() > 0 ||
                sqlTickets.getAllItems(SqliteHelperUserTickets.TABLE_SPANISHLOTTO).size() > 0 ||
                sqlTickets.getAllItems(SqliteHelperUserTickets.TABLE_FRENCHLOTTO).size() > 0 ||
                sqlTickets.getAllItems(SqliteHelperUserTickets.TABLE_GERMANLOTTO).size() > 0 ||
                sqlTickets.getAllItems(SqliteHelperUserTickets.TABLE_IRISHLOTTO).size() > 0) {
            dataList.add(new DrawerItem(getResources().getString(R.string.nav_item_saved_tickets), R.drawable.abc_ab_share_pack_mtrl_alpha));
        }*/

        /*SqliteHelperResults sqlResults = new SqliteHelperResults(this);
        if (sqlResults.getAllLottoResults().size() > 0) {
            dataList.add(new DrawerItem(getResources().getString(R.string.nav_ticket_results), R.drawable.abc_ab_share_pack_mtrl_alpha));
        }*/
    }

    protected void setToolbar() {
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

    protected class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                long arg3) {
            // TODO Auto-generated method stub

            switch (position) {
                case 0:
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                    break;
                case 1:
                    Intent intent2 = new Intent(getApplicationContext(), LottoTicketActivity.class);
                    intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent2);
                    finish();
                    break;
                case 2:
                    Intent intent3 = new Intent(getApplicationContext(), SavedTicketsActivity.class);
                    intent3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent3.putExtra(UtilConstants.IS_FROM_NOTIFCATION, false);
                    startActivity(intent3);
                    finish();
                    break;
                case 3:
                    Intent intent4 = new Intent(getApplicationContext(), TicketResultsActivity.class);
                    intent4.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent4.putExtra(UtilConstants.IS_FROM_NOTIFCATION, false);
                    startActivity(intent4);
                    finish();
                    break;
            }
        }

    }

}
