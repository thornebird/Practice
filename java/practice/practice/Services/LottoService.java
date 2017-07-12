package practice.practice.Services;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import practice.practice.Activities.SavedTicketsActivity;
import practice.practice.Activities.TicketResultsActivity;
import practice.practice.Constants.UtilConstants;
import practice.practice.DAO.LotteryFactoryLoaderDAO;
import practice.practice.DAO.SqliteHelperAlarms;
import practice.practice.DAO.SqliteHelperResults;
import practice.practice.DAO.SqliteHelperUserTickets;
import practice.practice.DataModel.LottoResultCalculator;
import practice.practice.DataModel.LottoResultSql;
import practice.practice.DataModel.LottocentreLottDraw;
import practice.practice.DataModel.UserAlarm;
import practice.practice.DataModel.UserLotto;
import practice.practice.R;

/**
 * Created by ThorneBird on 3/20/2017.
 */
public class LottoService extends Service implements LotteryFactoryLoaderDAO.OnLottoLoaded {

    private ArrayList<UserLotto> userLottos;
    private ArrayList<LottoResultCalculator> lottoResults;


    public LottoService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //Get todays lotto


        // new LotteryFactoryLoaderDAO(this, true).execute();
        getUserLotto();

        return Service.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        //TODO for communication return IBinder implementation
        return null;
    }


    @Override
    public void onLottoLoaded(ArrayList<LottocentreLottDraw> lottDraws) {
        int count = 0;
        if (lottDraws == null) {
            new LotteryFactoryLoaderDAO(this).execute();
        } else if (lottDraws != null) {
           /* SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE dd/MM");
            String lottoDrawDate="";*/

            lottoResults = new ArrayList<>();
            for (int i = 0; i < lottDraws.size(); i++) {
                LottocentreLottDraw lottoDraw = lottDraws.get(i);
                lottoDraw.setWinningNumberFromString(lottoDraw.getWinningNumbers());
                lottoDraw.setBonusNumberFromString(lottoDraw.getBonusNumbers());
                SqliteHelperResults dbResults = new SqliteHelperResults(this);

                for (int ii = 0; ii < userLottos.size(); ii++) {
                    if (lottoDraw.getTitle().contains(userLottos.get(ii).getLottoName()) && lottoDraw.getDate().equals(userLottos.get(ii).getDrawDate())) {
                        LottoResultCalculator lottoResult = new LottoResultCalculator(lottoDraw, userLottos.get(ii));
                        lottoResults.add(lottoResult);
                        count++;
                   if (!dbResults.isResultExist(lottoResult.getUserLotto().getLottoName(),lottoResult.getUserLotto().getDrawDate()))
                            dbResults.addLottoResult(lottoResult);
                    }
                }
            }


            for (int h = 0; h < lottoResults.size(); h++) {
                Log.i("lottoResults", lottoResults.get(h).toString());
            }

            SqliteHelperResults dbResults = new SqliteHelperResults(this);
            ArrayList<LottoResultSql> resultSqls = dbResults.getLottoResultsDate("4/28/2017");
            for (int i = 0; i < resultSqls.size(); i++) {
                Log.i("Result", resultSqls.get(i).toString());
            }

            if (count > 0) {
                //        Toast.makeText(this, lottDraws.toString(), Toast.LENGTH_LONG).show();
                try {
                    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    Ringtone r = RingtoneManager.getRingtone(this, notification);
                    r.play();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(this, TicketResultsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                        | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra(UtilConstants.IS_FROM_NOTIFCATION, true);


                PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);


                NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                        .setTicker("message")
                        .setContentTitle("Alarm lotto")
                        .setContentText("Winning nmbers")
                        .addAction(R.mipmap.ic_launcher, "Action Button", pIntent)
                        .setContentIntent(pIntent)
                        .setAutoCancel(true);

                // Create Notification Manager
                NotificationManager notificationmanager = (NotificationManager) this
                        .getSystemService(Context.NOTIFICATION_SERVICE);
                // Build Notification with Notification Manager
                notificationmanager.notify(0, builder.build());

            } else if (count == 0) {
                stopSelf();
            }
        }

    }


    private void getUserLotto() {
        if (userLottos == null)
            userLottos = new ArrayList<>();
        if (userLottos != null)
            userLottos.clear();
        ArrayList<String> queriedList = new ArrayList<>();
        Date date = new Date();
        String fDate = new SimpleDateFormat("MM/dd/yyyy").format(date);
        SqliteHelperAlarms sqliteHelperAlarms = new SqliteHelperAlarms(this);
        ArrayList<UserAlarm> alarms = sqliteHelperAlarms.getAlarmsByDate(fDate);  /// Need to run todays date
        SqliteHelperUserTickets sqliteHelperUserSaved = new SqliteHelperUserTickets(this);

        for (int i = 0; i < alarms.size(); i++) {
            if (!queriedList.contains(alarms.get(i).getTicketTableName())) {
                userLottos.addAll(sqliteHelperUserSaved.getLottosByDate(alarms.get(i).getTicketTableName(), alarms.get(i).getLottoDate()));
                queriedList.add(alarms.get(i).getTicketTableName());
            }
        }
        if (userLottos.size() == 0) {
            stopSelf();
        } else if (userLottos.size() > 0) {
            new LotteryFactoryLoaderDAO(this).execute();
        }
    }
}
