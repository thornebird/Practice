package practice.practice.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import practice.practice.DAO.SqliteHelperLottoFeed;
import practice.practice.DAO.SqliteHelperResults;
import practice.practice.DataModel.LottoResultCalculator;
import practice.practice.DataModel.LottoResultSql;
import practice.practice.DataModel.LottoSelectorManager;
import practice.practice.DataModel.LottocentreLottDraw;
import practice.practice.DataModel.UserLotto;
import practice.practice.Fragments.FragmentSavedLottos;
import practice.practice.R;
import practice.practice.UI.LottoBall;

public class LotteryProfileActivity extends AppCompatActivity {

    private LottocentreLottDraw lottoDraw;
    private UserLotto userLotto;
    private LottoResultSql lottoResults;
    private LinearLayout layoutDraw;
    private LinearLayout layoutBonusDraw;
    private LinearLayout layoutMatchingNumbers;
    private LinearLayout layoutMatchingBonus;
    private int logoRes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottery_profile);


        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            userLotto = bundle.getParcelable(FragmentSavedLottos.KEY_USERLOTTO);
            if(userLotto!=null) {
                LottoSelectorManager manager = new LottoSelectorManager(userLotto.getLottoName());
                logoRes=manager.getLargeLoggo();
            }
            //   lottoDraw = bundle.getParcelable(FragmentSavedLottos.KEY_LOTTODRAW);

        }
        calculatResults();
        init();
        if (lottoResults != null) {
            setBalls(layoutDraw, lottoResults.getDrawNumbersList(), false);
            setBalls(layoutBonusDraw, lottoResults.getDrawBonusNumbersList(), true);
            if(lottoResults.getMathchingNumbersList().size()>0)
                setBalls(layoutMatchingNumbers, lottoResults.getMathchingNumbersList(), false);
            if(lottoResults.getMatchingBonusNumbersList().size()>0)
                setBalls(layoutMatchingBonus,lottoResults.getMatchingBonusNumbersList(),true);
        }
    }

    /**
     * This hook is called whenever an item in your options menu is selected.
     * The default implementation simply returns false to have the normal
     * processing happen (calling the item's Runnable or sending a message to
     * its Handler as appropriate).  You can use this method for any items
     * for which you would like to do processing without those other
     * facilities.
     * <p>
     * <p>Derived classes should call through to the base class for it to
     * perform the default menu handling.</p>
     *
     * @param item The menu item that was selected.
     * @return boolean Return false to allow normal menu processing to
     * proceed, true to consume it here.
     * @see #onCreateOptionsMenu
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intentBack = new Intent(this, SavedTicketsActivity.class);
                intentBack.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentBack);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        ImageView imLogo=(ImageView)findViewById(R.id.im_loggo);
        imLogo.setImageResource(logoRes);

        TextView tvName=(TextView)findViewById(R.id.tv_lotto_name);
        tvName.setText(userLotto.getLottoName());
        TextView tvDrawDate=(TextView)findViewById(R.id.tv_draw_date);
        tvDrawDate.setText(userLotto.getDrawDate());
        TextView tvResults=(TextView)findViewById(R.id.tv_lotto_results);
        if(lottoResults.getMatchingBonusNumbersList().size()==0
                &&lottoResults.getMathchingNumbersList().size()==0){
            String noMatching=getResources().getString(R.string.no_macthing_numbers);
            tvResults.setText(noMatching);
        }else {
          tvResults.setVisibility(View.INVISIBLE);
        }
        //// Drawdate
        LinearLayout layoutUserBalls = (LinearLayout) findViewById(R.id.layout_userballs);
        LinearLayout layoutUserBonusBalls = (LinearLayout) findViewById(R.id.layout_userbonusballs);
        layoutDraw = (LinearLayout) findViewById(R.id.layout_drawballs);
        layoutBonusDraw = (LinearLayout) findViewById(R.id.layout_drawbonusballs);
        layoutMatchingNumbers = (LinearLayout) findViewById(R.id.layout_matching_number);
        layoutMatchingBonus = (LinearLayout) findViewById(R.id.layout_matching_number_bonus);
        setBalls(layoutUserBalls, userLotto.getLottoBallsArray(), false);
        setBalls(layoutUserBonusBalls, userLotto.getBonusBallsArray(), true);

        // setBalls(layoutDraw,lottoDraw.getDrawNumbers());
        //  setBalls(layoutBonusDraw,lottoDraw.getDrawBonusNumbers());
    }


    private void calculatResults() {
        if (userLotto == null)
            return;
        SqliteHelperResults sqlResults = new SqliteHelperResults(this);
        lottoResults = sqlResults.getResults(userLotto.getLottoName(), "4/28/2017");
        if (lottoResults != null) {
            Log.i("lottonull", lottoResults.toString());
            Log.i("lotto", lottoResults.getUserNumbersList() + "" + lottoResults.getUserBonusNumberList());
        } else {
            Log.i("lottonull", "==null");
        }
    }

    private void setBalls(LinearLayout layout, ArrayList<Integer> list, boolean isBonus) {
        for (int i = 0; i < list.size(); i++) {
            layout.addView(createBall(isBonus, list.get(i) + ""));
        }
    }


    private LottoBall createBall(boolean isBonus, String number) {
        LottoBall ball = new LottoBall(this, isBonus);
        ball.setLayoutParams(new ViewGroup.LayoutParams(110, 110));
        ball.setText(number);
        return ball;
    }


}
