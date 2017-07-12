package practice.practice.Activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.Calendar;

import practice.practice.DAO.LotteryFactoryLoaderDAO;
import practice.practice.DAO.SqliteHelperLottoFeed;
import practice.practice.DataModel.LotteyBaseInfo;
import practice.practice.DataModel.LottocentreLottDraw;
import practice.practice.LottoCheckerReceiever;
import practice.practice.MyApplication;
import practice.practice.R;

public class SplashScreenActivity extends AppCompatActivity implements LotteryFactoryLoaderDAO.OnLottoLoaded {

    private SqliteHelperLottoFeed dbHelper;
    private Animation rotation;
    private ImageView imLogo;
    public final int ALARM_CODE = 1111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        createAlarm();
        LotteryFactoryLoaderDAO lotteryFactoryDAO = new LotteryFactoryLoaderDAO(this);
        lotteryFactoryDAO.execute();


      initView();
    }

    private void initView() {
        imLogo=(ImageView) findViewById(R.id.im_logo);
        ProgressBar bar = (ProgressBar) findViewById(R.id.progress);
        bar.setVisibility(View.VISIBLE);
    }

    private void createAlarm() {
        Intent intent = new Intent(SplashScreenActivity.this, LottoCheckerReceiever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), ALARM_CODE, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        /*alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                + (50), pendingIntent);*/
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 13);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);

    }

    @Override
    public void onLottoLoaded(ArrayList<LottocentreLottDraw> lottDraws) {
        if (lottDraws != null) {
            setLottoLists(lottDraws);
        } else {
            LotteryFactoryLoaderDAO lotteryFactoryDAO = new LotteryFactoryLoaderDAO(this);
            lotteryFactoryDAO.execute();
        }
    }

    private void setLottoLists(ArrayList<LottocentreLottDraw> lottDraws) {

        dbHelper = new SqliteHelperLottoFeed(this);
        for (LottocentreLottDraw item : lottDraws) {
            String title = item.getTitle();

            if (title.contains(LotteyBaseInfo.AUSOZLOTTO.getLottoTitle())) {
                if (!dbHelper.isLottoExist(SqliteHelperLottoFeed.TABLE_AUSLOTTO, title)) {
                    dbHelper.addItemLotto(item, SqliteHelperLottoFeed.TABLE_AUSOZLOTTO);
                }

            } else if (title.contains(LotteyBaseInfo.AUSPOWERBALL.getLottoTitle())) {
                if (!dbHelper.isLottoExist(SqliteHelperLottoFeed.TABLE_AUSPOWERBALL, title)) {
                    dbHelper.addItemLotto(item, SqliteHelperLottoFeed.TABLE_AUSPOWERBALL);
                }

            } else if (title.contains(LotteyBaseInfo.AUSlOTTO.getLottoTitle())) {
                if (!dbHelper.isLottoExist(SqliteHelperLottoFeed.TABLE_AUSLOTTO, title)) {
                    dbHelper.addItemLotto(item, SqliteHelperLottoFeed.TABLE_AUSLOTTO);
                }

            } else if (title.contains(LotteyBaseInfo.USAPOWERBALL.getLottoTitle())) {
                if (!dbHelper.isLottoExist(SqliteHelperLottoFeed.TABLE_USPOWERBALL, title)) {
                    dbHelper.addItemLotto(item, SqliteHelperLottoFeed.TABLE_USPOWERBALL);
                }
            } else if (title.contains(LotteyBaseInfo.BRITISHLOTTO.getLottoTitle())) {
                if (!dbHelper.isLottoExist(SqliteHelperLottoFeed.TABLE_BRITISHLOTTO, title)) {
                    dbHelper.addItemLotto(item, SqliteHelperLottoFeed.TABLE_BRITISHLOTTO);
                }
            } else if (title.contains(LotteyBaseInfo.CANADIANLOTTO.getLottoTitle())) {
                if (!dbHelper.isLottoExist(SqliteHelperLottoFeed.TABLE_CANADIANLOTTO, title)) {
                    dbHelper.addItemLotto(item, SqliteHelperLottoFeed.TABLE_CANADIANLOTTO);
                }
            } else if (title.contains(LotteyBaseInfo.USAMEGAMILLIONS.getLottoTitle())) {
                if (!dbHelper.isLottoExist(SqliteHelperLottoFeed.TABLE_USEMEGAMILLIONS, title)) {
                    dbHelper.addItemLotto(item, SqliteHelperLottoFeed.TABLE_USEMEGAMILLIONS);
                }
            } else if (title.contains(LotteyBaseInfo.EUROJACKPOT.getLottoTitle())) {
                if (!dbHelper.isLottoExist(SqliteHelperLottoFeed.TABLE_EUROJACKPOT, title)) {
                    dbHelper.addItemLotto(item, SqliteHelperLottoFeed.TABLE_EUROJACKPOT);
                }
            } else if (title.contains(LotteyBaseInfo.EUROMILLIONS.getLottoTitle())) {
                if (!dbHelper.isLottoExist(SqliteHelperLottoFeed.TABLE_EUROMILLIONS, title)) {
                    dbHelper.addItemLotto(item, SqliteHelperLottoFeed.TABLE_EUROMILLIONS);
                }
            } else if (title.contains(LotteyBaseInfo.SPANISHLOTTO.getLottoTitle())) {
                if (!dbHelper.isLottoExist(SqliteHelperLottoFeed.TABLE_SPANISHLOTTO, title)) {
                    dbHelper.addItemLotto(item, SqliteHelperLottoFeed.TABLE_SPANISHLOTTO);
                }
            } else if (title.contains(LotteyBaseInfo.FRENCHLOTTO.getLottoTitle())) {
                if (!dbHelper.isLottoExist(SqliteHelperLottoFeed.TABLE_FRENCHLOTTO, title)) {
                    dbHelper.addItemLotto(item, SqliteHelperLottoFeed.TABLE_FRENCHLOTTO);
                }
            } else if (title.contains(LotteyBaseInfo.GERMANLOTTO.getLottoTitle())) {
                if (!dbHelper.isLottoExist(SqliteHelperLottoFeed.TABLE_GERMANLOTTO, title)) {
                    dbHelper.addItemLotto(item, SqliteHelperLottoFeed.TABLE_GERMANLOTTO);
                }
            } else if (title.contains(LotteyBaseInfo.IRISHLOTTO.getLottoTitle())) {
                if (!dbHelper.isLottoExist(SqliteHelperLottoFeed.TABLE_IRISHLOTTO, title)) {
                    dbHelper.addItemLotto(item, SqliteHelperLottoFeed.TABLE_IRISHLOTTO);
                }
            }

        }
        setValues();
    }


    private void setValues() {
        MyApplication application = (MyApplication) getApplication();
        application.setAusOzLottos(dbHelper.getItemsLotto(SqliteHelperLottoFeed.TABLE_AUSOZLOTTO));
        application.setAusPowerBall(dbHelper.getItemsLotto(SqliteHelperLottoFeed.TABLE_AUSPOWERBALL));
        application.setAusLotto(dbHelper.getItemsLotto(SqliteHelperLottoFeed.TABLE_AUSLOTTO));
        application.setBritishLotto(dbHelper.getItemsLotto(SqliteHelperLottoFeed.TABLE_BRITISHLOTTO));
        application.setCanadianLotto(dbHelper.getItemsLotto(SqliteHelperLottoFeed.TABLE_CANADIANLOTTO));
        application.setEuroMillions(dbHelper.getItemsLotto(SqliteHelperLottoFeed.TABLE_EUROMILLIONS));
        application.setEuroJackpot(dbHelper.getItemsLotto(SqliteHelperLottoFeed.TABLE_EUROJACKPOT));
        application.setSpanishLotto(dbHelper.getItemsLotto(SqliteHelperLottoFeed.TABLE_SPANISHLOTTO));
        application.setFrenchLotto(dbHelper.getItemsLotto(SqliteHelperLottoFeed.TABLE_FRENCHLOTTO));
        application.setGermanLotto(dbHelper.getItemsLotto(SqliteHelperLottoFeed.TABLE_GERMANLOTTO));
        application.setUsaPowerBall(dbHelper.getItemsLotto(SqliteHelperLottoFeed.TABLE_USPOWERBALL));
        application.setUsaMegaMillions(dbHelper.getItemsLotto(SqliteHelperLottoFeed.TABLE_USEMEGAMILLIONS));
        application.setIrishLotto(dbHelper.getItemsLotto(SqliteHelperLottoFeed.TABLE_IRISHLOTTO));

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }


}
