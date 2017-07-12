package practice.practice.DAO;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import okio.BufferedSink;
import practice.practice.Constants.UtilConstants;
import practice.practice.DataModel.LottocentreLottDraw;
import practice.practice.DataModel.UserLotto;
import practice.practice.DataModel.XmlParserLotto;

/**
 * Created by ThorneBird on 3/25/2017.
 */
public class LotteryFactoryLoaderDAO extends AsyncTask<Void, Void, Void> {

    private OnLottoLoaded onLoadedListener;
   /* private final static String POST = "POST";
    private final static String GET = "GET";
    private final static String KEY_TITLE = "title";
    private final static String KEY_DESCRIPTION = "description";
    private final static String KEY_CATEGEORY = "category";
    private final static String RSS_REMOVE_ITEM = "Lottries results";
    private final static String KEY_ITEM = "";
    private String title;
    private String description;*/
    private final static String LOTTOFEED_ALL = "https://www.thelotterycentre.com/en/feed/lotto";
    private ArrayList<LottocentreLottDraw> lottDraws;
    private OkHttpClient client;
    private  ProgressDialog mProgressDialog;

    public LotteryFactoryLoaderDAO( OnLottoLoaded onLoadedListener) {
        this.onLoadedListener = onLoadedListener;
    }

    /**
     * Runs on the UI thread before {@link #doInBackground}.
     *
     * @see #onPostExecute
     * @see #doInBackground
     */


    @Override
    protected Void doInBackground(Void... params) {

        client = new OkHttpClient();
        client.setConnectTimeout(30, TimeUnit.SECONDS);
        client.setReadTimeout(30,TimeUnit.SECONDS);
        Request request = new Request.Builder()
                .url(LOTTOFEED_ALL)
                .build();

            try {
                Response response = client.newCall(request).execute();
                InputStream is = response.body().byteStream();
                XmlParserLotto parserLotto=new XmlParserLotto(is);
                lottDraws=parserLotto.parseLoadedLotto();

            } catch (SocketException ex){
                Log.e("SocketException",ex.toString());
                ex.printStackTrace();
            }catch (IOException ex){
                ex.printStackTrace();
            }

        return null;
    }

    /**
     * <p>Runs on the UI thread after {@link #doInBackground}. The
     * specified result is the value returned by {@link #doInBackground}.</p>
     * <p/>
     * <p>This method won't be invoked if the task was cancelled.</p>
     *
     * @param aVoid The result of the operation computed by {@link #doInBackground}.
     * @see #onPreExecute
     * @see #doInBackground
     * @see #onCancelled(Object)
     */
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (onLoadedListener != null)
            onLoadedListener.onLottoLoaded(lottDraws);
    }



    public interface OnLottoLoaded {
        public void onLottoLoaded(ArrayList<LottocentreLottDraw> lottDraws);

    }

}