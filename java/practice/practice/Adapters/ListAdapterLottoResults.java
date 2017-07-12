package practice.practice.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;

import practice.practice.DataModel.LotteyBaseInfo;
import practice.practice.DataModel.LottoResultSql;
import practice.practice.DataModel.LottocentreLottDraw;
import practice.practice.DataModel.UserLotto;
import practice.practice.R;
import practice.practice.UI.LottoBall;
import practice.practice.UI.LottoBallLayout;

/**
 * Created by ThorneBird on 6/17/2017.
 */

public class ListAdapterLottoResults extends ArrayAdapter {

    private Context context;
    private int resourceId;
    private ArrayList<LottoResultSql> lottoResultSqls;
    private String drawDate;
    private String wininnigNumbers;
    private String bonusNumbers;
    private String jackpot;
    private String totalWinners;
    private int lastPostion = 0;
    private final static int STATE_SAVEDLOTTO = 0;
    private final static int STATE_LOTTODRAW = 1;

    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     */
    public ListAdapterLottoResults(@NonNull Context context, @LayoutRes int resource, ArrayList<LottoResultSql> lottoResultSqls) {
        super(context, resource);
        this.context = context;
        this.resourceId = resource;
        this.lottoResultSqls = lottoResultSqls;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            convertView = mInflater.inflate(resourceId, null);
            holder = new ViewHolder();
            holder.imLogo = (ImageView) convertView.findViewById(R.id.im_lotto);
            holder.tvDrawDate = (TextView) convertView.findViewById(R.id.tv_lottodate);
            holder.tvMatchingNumbers =(TextView)convertView.findViewById(R.id.tv_matching_numbers);
            holder.layoutBalls = (LottoBallLayout) convertView.findViewById(R.id.layout_balls);
            holder.ballOne = (LottoBall) convertView.findViewById(R.id.ball_one);
            holder.ballTwo = (LottoBall) convertView.findViewById(R.id.ball_two);
            holder.ballThree = (LottoBall) convertView.findViewById(R.id.ball_three);
            holder.ballFour = (LottoBall) convertView.findViewById(R.id.ball_four);
            holder.ballFive = (LottoBall) convertView.findViewById(R.id.ball_five);
            holder.ballSix = (LottoBall) convertView.findViewById(R.id.ball_six);
            holder.ballSeven = (LottoBall) convertView.findViewById(R.id.ball_seven);
            holder.ballBonus = (LottoBall) convertView.findViewById(R.id.bonus_ball_one);
            holder.ballBonusTwo = (LottoBall) convertView.findViewById(R.id.bonus_ball_two);
            ///////////////////////////
            holder.layoutResults = (LottoBallLayout) convertView.findViewById(R.id.layout_balls_results);
            holder.ballOneResuls = (LottoBall) convertView.findViewById(R.id.ball_one_results);
            holder.ballTwoResuls = (LottoBall) convertView.findViewById(R.id.ball_two_results);
            holder.ballThreeResuls = (LottoBall) convertView.findViewById(R.id.ball_three_results);
            holder.ballFourResuls = (LottoBall) convertView.findViewById(R.id.ball_four_results);
            holder.ballFiveResuls = (LottoBall) convertView.findViewById(R.id.ball_five_results);
            holder.ballSixResuls = (LottoBall) convertView.findViewById(R.id.ball_six_results);
            holder.ballSevenResuls = (LottoBall) convertView.findViewById(R.id.ball_seven_results);
            holder.ballBonusResuls = (LottoBall) convertView.findViewById(R.id.bonus_ball_one_results);
            holder.ballBonusTwoResuls = (LottoBall) convertView.findViewById(R.id.bonus_ball_two_results);
            /////////////
            holder.layoutMatching = (LottoBallLayout) convertView.findViewById(R.id.layout_matching_numbers);
            holder.ballOneMatching = (LottoBall) convertView.findViewById(R.id.ball_matchingone);
            holder.ballTwoMatching = (LottoBall) convertView.findViewById(R.id.ball_matchingtwo);
            holder.ballThreeMatching = (LottoBall) convertView.findViewById(R.id.ball_matchingthree);
            holder.ballFourMatching = (LottoBall) convertView.findViewById(R.id.ball_matchingfour);
            holder.ballFiveMatching = (LottoBall) convertView.findViewById(R.id.ball_matchingsix);
            holder.ballSixMatching = (LottoBall) convertView.findViewById(R.id.ball_matchingsix);
            holder.ballSevenMatching = (LottoBall) convertView.findViewById(R.id.ball_matchingseven);
            holder.ballBonuMatching =(LottoBall) convertView.findViewById(R.id.bonus_matching_one);
            holder.ballBonusTwoMatching = (LottoBall) convertView.findViewById(R.id.bonus_matching_two);


                    ///////////////
                    convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (position > 2 && position > lastPostion) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.enter_from_right);
            convertView.startAnimation(animation);
            lastPostion = position;
        }

        LottoResultSql lottoResult = lottoResultSqls.get(position);

        holder.tvDrawDate.setText(lottoResult.getLottoDate());
        // holder..setText(lottoResult.getLottoName());
        setNumbersUserLotto(holder, lottoResult.getLottoName(), lottoResult.getUserNumbersList(), lottoResult.getUserBonusNumberList(), STATE_SAVEDLOTTO);
        setNumbersUserLotto(holder, lottoResult.getLottoName(), lottoResult.getDrawNumbersList(), lottoResult.getDrawBonusNumbersList(), STATE_LOTTODRAW);
        setMatchingViews(holder,lottoResult.getMathchingNumbersList(),lottoResult.getMatchingBonusNumbersList());
        return convertView;
    }

    @Override
    public int getCount() {
        return lottoResultSqls.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return lottoResultSqls.get(position);
    }


    private class ViewHolder {
        TextView tvDrawDate;
        TextView tvMatchingNumbers;
        ImageView imLogo;
        LottoBallLayout layoutBalls;
        LottoBallLayout layoutResults;
        LottoBallLayout layoutMatching;
        LottoBall ballOne;
        LottoBall ballTwo;
        LottoBall ballThree;
        LottoBall ballFour;
        LottoBall ballFive;
        LottoBall ballSix;
        LottoBall ballSeven;
        LottoBall ballBonus;
        LottoBall ballBonusTwo;
        LottoBall ballOneResuls;
        LottoBall ballTwoResuls;
        LottoBall ballThreeResuls;
        LottoBall ballFourResuls;
        LottoBall ballFiveResuls;
        LottoBall ballSixResuls;
        LottoBall ballSevenResuls;
        LottoBall ballBonusResuls;
        LottoBall ballBonusTwoResuls;
        LottoBall ballOneMatching;
        LottoBall ballTwoMatching;
        LottoBall ballThreeMatching;
        LottoBall ballFourMatching;
        LottoBall ballFiveMatching;
        LottoBall ballSixMatching;
        LottoBall ballSevenMatching;
        LottoBall ballBonuMatching;
        LottoBall ballBonusTwoMatching;
    }

    private void setNumbersUserLotto(ViewHolder holder, String title, List<Integer> numbers, List<Integer> bonusNumbers, int state) {
        if (title.equals(LotteyBaseInfo.AUSOZLOTTO.getLottoTitle())) {
            setSevenTwo(holder, numbers, bonusNumbers, state);
        } else if (title.equals(LotteyBaseInfo.AUSPOWERBALL.getLottoTitle())) {
            setSixOne(holder, numbers, bonusNumbers, state);
        } else if (title.equals(LotteyBaseInfo.AUSlOTTO.getLottoTitle())) {
            setSixTwo(holder, numbers, bonusNumbers, state);
        } else if (title.equals(LotteyBaseInfo.BRITISHLOTTO.getLottoTitle())) {
            setSixOne(holder, numbers, bonusNumbers, state);
        } else if (title.equals(LotteyBaseInfo.CANADIANLOTTO.getLottoTitle())) {
            setSixOne(holder, numbers, bonusNumbers, state);
        } else if (title.equals(LotteyBaseInfo.EUROMILLIONS.getLottoTitle())) {
            setFiveTwo(holder, numbers, bonusNumbers, state);
        } else if (title.equals(LotteyBaseInfo.EUROJACKPOT.getLottoTitle())) {
            setFiveTwo(holder, numbers, bonusNumbers, state);
        } else if (title.equals(LotteyBaseInfo.FRENCHLOTTO.getLottoTitle())) {
            setFiveOne(holder, numbers, bonusNumbers, state);
        } else if (title.equals(LotteyBaseInfo.GERMANLOTTO.getLottoTitle())) {
            setSixOne(holder, numbers, bonusNumbers, state);
        } else if (title.equals(LotteyBaseInfo.IRISHLOTTO.getLottoTitle())) {
            setSixOne(holder, numbers, bonusNumbers, state);
        } else if (title.equals(LotteyBaseInfo.SPANISHLOTTO.getLottoTitle())) {
            setSixTwo(holder, numbers, bonusNumbers, state);
        } else if (title.equals(LotteyBaseInfo.USAMEGAMILLIONS.getLottoTitle())) {
            setFiveOne(holder, numbers, bonusNumbers, state);
        } else if (title.equals(LotteyBaseInfo.USAPOWERBALL.getLottoTitle())) {
            setFiveOne(holder, numbers, bonusNumbers, state);
        }
    }

    private void setMatchingViews(ViewHolder holder, List<Integer> numbers, List<Integer> bonusNumbers){
        if(numbers.size()==0&&bonusNumbers.size()==0){
            String matchingNumber=getContext().getResources().getString(R.string.no_matching_numbers);
            holder.tvMatchingNumbers.setText(matchingNumber);
            if(numbers.size()==1){
                holder.ballOneMatching.setText(numbers.get(0)+"");
                holder.ballOneMatching.setVisibility(View.VISIBLE);
            }else if(numbers.size()==2){
                holder.ballOneMatching.setText(numbers.get(0)+"");
                holder.ballOneMatching.setVisibility(View.VISIBLE);
                holder.ballTwoMatching.setText(numbers.get(1)+"");
                holder.ballTwoMatching.setVisibility(View.VISIBLE);
            }else if(numbers.size()==3){
                holder.ballOneMatching.setText(numbers.get(0)+"");
                holder.ballOneMatching.setVisibility(View.VISIBLE);
                holder.ballTwoMatching.setText(numbers.get(1)+"");
                holder.ballTwoMatching.setVisibility(View.VISIBLE);
                holder.ballThreeMatching.setText(numbers.get(2)+"");
                holder.ballThreeMatching.setVisibility(View.VISIBLE);
            }else if(numbers.size()==4){
                holder.ballOneMatching.setText(numbers.get(0)+"");
                holder.ballOneMatching.setVisibility(View.VISIBLE);
                holder.ballTwoMatching.setText(numbers.get(1)+"");
                holder.ballTwoMatching.setVisibility(View.VISIBLE);
                holder.ballThreeMatching.setText(numbers.get(2)+"");
                holder.ballThreeMatching.setVisibility(View.VISIBLE);
                holder.ballFourMatching.setText(numbers.get(3)+"");
                holder.ballFourMatching.setVisibility(View.VISIBLE);
            }else if(numbers.size()==5){
                holder.ballOneMatching.setText(numbers.get(0)+"");
                holder.ballOneMatching.setVisibility(View.VISIBLE);
                holder.ballTwoMatching.setText(numbers.get(1)+"");
                holder.ballTwoMatching.setVisibility(View.VISIBLE);
                holder.ballThreeMatching.setText(numbers.get(2)+"");
                holder.ballThreeMatching.setVisibility(View.VISIBLE);
                holder.ballFourMatching.setText(numbers.get(3)+"");
                holder.ballFourMatching.setVisibility(View.VISIBLE);
                holder.ballFiveMatching.setText(numbers.get(4)+"");
                holder.ballFiveMatching.setVisibility(View.VISIBLE);
            }else if(numbers.size()==6){
                holder.ballOneMatching.setText(numbers.get(0)+"");
                holder.ballOneMatching.setVisibility(View.VISIBLE);
                holder.ballTwoMatching.setText(numbers.get(1)+"");
                holder.ballTwoMatching.setVisibility(View.VISIBLE);
                holder.ballThreeMatching.setText(numbers.get(2)+"");
                holder.ballThreeMatching.setVisibility(View.VISIBLE);
                holder.ballFourMatching.setText(numbers.get(3)+"");
                holder.ballFourMatching.setVisibility(View.VISIBLE);
                holder.ballFiveMatching.setText(numbers.get(4)+"");
                holder.ballFiveMatching.setVisibility(View.VISIBLE);
                holder.ballSixMatching.setText(numbers.get(5)+"");
                holder.ballSixMatching.setVisibility(View.VISIBLE);
            }else if(numbers.size()==7){
                holder.ballOneMatching.setText(numbers.get(0)+"");
                holder.ballOneMatching.setVisibility(View.VISIBLE);
                holder.ballTwoMatching.setText(numbers.get(1)+"");
                holder.ballTwoMatching.setVisibility(View.VISIBLE);
                holder.ballThreeMatching.setText(numbers.get(2)+"");
                holder.ballThreeMatching.setVisibility(View.VISIBLE);
                holder.ballFourMatching.setText(numbers.get(3)+"");
                holder.ballFourMatching.setVisibility(View.VISIBLE);
                holder.ballFiveMatching.setText(numbers.get(4)+"");
                holder.ballFiveMatching.setVisibility(View.VISIBLE);
                holder.ballSixMatching.setText(numbers.get(5)+"");
                holder.ballSixMatching.setVisibility(View.VISIBLE);
            }
            if(bonusNumbers.size()==1){
                holder.ballBonuMatching.setText(bonusNumbers.get(0)+"");
                holder.ballBonuMatching.setVisibility(View.VISIBLE);
            }else if(bonusNumbers.size()==2){
                holder.ballBonuMatching.setText(bonusNumbers.get(0)+"");
                holder.ballBonuMatching.setVisibility(View.VISIBLE);
                holder.ballBonusTwoMatching.setText(bonusNumbers.get(1)+"");
                holder.ballBonusTwoMatching.setVisibility(View.VISIBLE);
            }
        }
    }

    private void setSevenTwo(ViewHolder holder, List<Integer> numbers, List<Integer> bonusNumbers, int state) {
        switch (state) {
            case STATE_SAVEDLOTTO:
                if (holder.ballOne != null && numbers.size() > 0) {
                    holder.ballOneResuls.setText(numbers.get(0) + "");
                    holder.ballOneResuls.setVisibility(View.VISIBLE);
                }
                if (holder.ballTwo != null && numbers.size() > 1) {
                    holder.ballTwo.setText(numbers.get(1) + "");
                    holder.ballTwo.setVisibility(View.VISIBLE);
                }
                if (holder.ballThree != null && numbers.size() > 2) {
                    holder.ballThree.setText(numbers.get(2) + "");
                    holder.ballThree.setVisibility(View.VISIBLE);
                }
                if (holder.ballFour != null && numbers.size() > 3) {
                    holder.ballFour.setText(numbers.get(3) + "");
                    holder.ballFour.setVisibility(View.VISIBLE);
                }
                if (holder.ballFive != null && numbers.size() > 4) {

                    holder.ballFive.setText(numbers.get(4) + "");
                    holder.ballFive.setVisibility(View.VISIBLE);

                }
                if (holder.ballSix != null && numbers.size() > 5) {
                    holder.ballSix.setText(numbers.get(5) + "");
                    holder.ballSix.setVisibility(View.VISIBLE);
                }
                if (holder.ballSeven != null && numbers.size() > 6) {
                    holder.ballSeven.setText(numbers.get(6) + "");
                    holder.ballSeven.setVisibility(View.VISIBLE);
                }
                if (holder.ballBonus != null && numbers.size() > 0) {
                    holder.ballBonus.setText(bonusNumbers.get(0) + "");
                    holder.ballBonus.setVisibility(View.VISIBLE);
                    holder.ballBonus.setBonusColor(true);
                }
                if (holder.ballBonusTwo != null && bonusNumbers.size() > 1) {
                    holder.ballBonusTwo.setText(bonusNumbers.get(1) + "");
                    holder.ballBonusTwo.setVisibility(View.VISIBLE);
                    holder.ballBonusTwo.setBonusColor(true);
                }
                break;

            case STATE_LOTTODRAW:
                if (holder.ballOneResuls != null && numbers.size() > 0) {
                    holder.ballOneResuls.setText(numbers.get(0) + "");
                    holder.ballOneResuls.setVisibility(View.VISIBLE);
                }
                if (holder.ballTwoResuls != null && numbers.size() > 1) {
                    holder.ballTwoResuls.setText(numbers.get(1) + "");
                    holder.ballTwoResuls.setVisibility(View.VISIBLE);
                }
                if (holder.ballThreeResuls != null && numbers.size() > 2) {
                    holder.ballThreeResuls.setText(numbers.get(2) + "");
                    holder.ballThreeResuls.setVisibility(View.VISIBLE);
                }
                if (holder.ballFourResuls != null && numbers.size() > 3) {
                    holder.ballFourResuls.setText(numbers.get(3) + "");
                    holder.ballFourResuls.setVisibility(View.VISIBLE);
                }
                if (holder.ballFiveResuls != null && numbers.size() > 4) {
                    holder.ballFiveResuls.setText(numbers.get(4) + "");
                    holder.ballFiveResuls.setVisibility(View.VISIBLE);
                }
                if (holder.ballSixResuls != null && numbers.size() > 5) {
                    holder.ballSixResuls.setText(numbers.get(5) + "");
                    holder.ballSixResuls.setVisibility(View.VISIBLE);
                }
                if (holder.ballSevenResuls != null && numbers.size() > 6) {
                    holder.ballSevenResuls.setText(numbers.get(6) + "");
                    holder.ballSevenResuls.setVisibility(View.VISIBLE);
                }
                if (holder.ballBonusResuls != null && numbers.size() > 0) {
                    holder.ballBonusResuls.setText(bonusNumbers.get(0) + "");
                    holder.ballBonusResuls.setVisibility(View.VISIBLE);
                    holder.ballBonusResuls.setBonusColor(true);
                }
                if (holder.ballBonusTwoResuls != null && bonusNumbers.size() > 1) {
                    holder.ballBonusTwoResuls.setText(bonusNumbers.get(1) + "");
                    holder.ballBonusTwoResuls.setVisibility(View.VISIBLE);
                    holder.ballBonusTwoResuls.setBonusColor(true);
                }
                break;
        }
    }


    private void setSixOne(ViewHolder holder, List<Integer> numbers, List<Integer> bonusNumbers, int state) {
        switch (state) {
            case STATE_SAVEDLOTTO:
                holder.layoutBalls.removeView(holder.ballBonus);
                holder.layoutBalls.removeView(holder.ballBonusTwo);
                if (holder.ballOne != null && numbers.size() > 0) {
                    holder.ballOne.setText(numbers.get(0) + "");
                    holder.ballOne.setVisibility(View.VISIBLE);
                }
                if (holder.ballTwo != null && numbers.size() > 1) {
                    holder.ballTwo.setText(numbers.get(1) + "");
                    holder.ballTwo.setVisibility(View.VISIBLE);
                }
                if (holder.ballThree != null && numbers.size() > 2) {
                    holder.ballThree.setText(numbers.get(2) + "");
                    holder.ballThree.setVisibility(View.VISIBLE);
                }
                if (holder.ballFour != null && numbers.size() > 3) {
                    holder.ballFour.setText(numbers.get(3) + "");
                    holder.ballFour.setVisibility(View.VISIBLE);
                }
                if (holder.ballFive != null && numbers.size() > 4) {
                    holder.ballFive.setText(numbers.get(4) + "");
                    holder.ballFive.setVisibility(View.VISIBLE);
                }
                if (holder.ballSix != null && numbers.size() > 5) {
                    holder.ballSix.setText(numbers.get(5) + "");
                    holder.ballSix.setVisibility(View.VISIBLE);
                }
                if (holder.ballSeven != null && bonusNumbers.size() > 0) {
                    holder.ballSeven.setText(bonusNumbers.get(0) + "");
                    holder.ballSeven.setVisibility(View.VISIBLE);
                    holder.ballSeven.setBonusColor(true);
                }
                break;

            case STATE_LOTTODRAW:

                holder.layoutResults.removeView(holder.ballBonusResuls);
                holder.layoutResults.removeView(holder.ballBonusTwoResuls);
                if (holder.ballOneResuls != null && numbers.size() > 0) {
                    holder.ballOneResuls.setText(numbers.get(0) + "");
                    holder.ballOneResuls.setVisibility(View.VISIBLE);
                }
                if (holder.ballTwoResuls != null && numbers.size() > 1) {
                    holder.ballTwoResuls.setText(numbers.get(1) + "");
                    holder.ballTwoResuls.setVisibility(View.VISIBLE);
                }
                if (holder.ballThreeResuls != null && numbers.size() > 2) {
                    holder.ballThreeResuls.setText(numbers.get(2) + "");
                    holder.ballThreeResuls.setVisibility(View.VISIBLE);
                }
                if (holder.ballFourResuls != null && numbers.size() > 3) {
                    holder.ballFourResuls.setText(numbers.get(3) + "");
                    holder.ballFourResuls.setVisibility(View.VISIBLE);
                }
                if (holder.ballFiveResuls != null && numbers.size() > 4) {
                    holder.ballFiveResuls.setText(numbers.get(4) + "");
                    holder.ballFiveResuls.setVisibility(View.VISIBLE);
                }
                if (holder.ballSixResuls != null && numbers.size() > 5) {
                    holder.ballSixResuls.setText(numbers.get(5) + "");
                    holder.ballSixResuls.setVisibility(View.VISIBLE);
                }
                if (holder.ballSevenResuls != null && bonusNumbers.size() > 0) {
                    holder.ballSevenResuls.setText(bonusNumbers.get(0) + "");
                    holder.ballSevenResuls.setVisibility(View.VISIBLE);
                    holder.ballSevenResuls.setBonusColor(true);
                }
                break;
        }
    }

    private void setSixTwo(ViewHolder holder, List<Integer> numbers, List<Integer> bonusNumbers, int state) {
        switch (state) {
            case STATE_SAVEDLOTTO:
                holder.layoutBalls.removeView(holder.ballBonusTwo);
                if (holder.ballOne != null && numbers.size() > 0) {
                    holder.ballOne.setText(numbers.get(0) + "");
                    holder.ballOne.setVisibility(View.VISIBLE);
                }
                if (holder.ballTwo != null && numbers.size() > 1) {
                    holder.ballTwo.setText(numbers.get(1) + "");
                    holder.ballTwo.setVisibility(View.VISIBLE);
                }
                if (holder.ballThree != null && numbers.size() > 2) {
                    holder.ballThree.setText(numbers.get(2) + "");
                    holder.ballThree.setVisibility(View.VISIBLE);
                }
                if (holder.ballFour != null && numbers.size() > 3) {
                    holder.ballFour.setText(numbers.get(3) + "");
                    holder.ballFour.setVisibility(View.VISIBLE);
                }
                if (holder.ballFive != null && numbers.size() > 4) {
                    holder.ballFive.setText(numbers.get(4) + "");
                    holder.ballFive.setVisibility(View.VISIBLE);
                }
                if (holder.ballSix != null && numbers.size() > 5) {
                    holder.ballSix.setText(numbers.get(5) + "");
                    holder.ballSix.setVisibility(View.VISIBLE);
                }
                if (holder.ballSeven != null && bonusNumbers.size() > 0) {
                    holder.ballSeven.setText(bonusNumbers.get(0) + "");
                    holder.ballSeven.setVisibility(View.VISIBLE);
                    holder.ballSeven.setBonusColor(true);
                }
                if (holder.ballBonus != null && bonusNumbers.size() > 1) {
                    holder.ballBonus.setText(bonusNumbers.get(1) + "");
                    holder.ballBonus.setVisibility(View.VISIBLE);
                    holder.ballBonus.setBonusColor(true);
                }
                break;

            case STATE_LOTTODRAW:
                holder.layoutResults.removeView(holder.ballBonusTwo);
                if (holder.ballOneResuls != null && numbers.size() > 0) {
                    holder.ballOneResuls.setText(numbers.get(0) + "");
                    holder.ballOneResuls.setVisibility(View.VISIBLE);
                }
                if (holder.ballTwoResuls != null && numbers.size() > 1) {
                    holder.ballTwoResuls.setText(numbers.get(1) + "");
                    holder.ballTwoResuls.setVisibility(View.VISIBLE);
                }
                if (holder.ballThreeResuls != null && numbers.size() > 2) {
                    holder.ballThreeResuls.setText(numbers.get(2) + "");
                    holder.ballThreeResuls.setVisibility(View.VISIBLE);
                }
                if (holder.ballFourResuls != null && numbers.size() > 3) {
                    holder.ballFourResuls.setText(numbers.get(3) + "");
                    holder.ballFourResuls.setVisibility(View.VISIBLE);
                }
                if (holder.ballFiveResuls != null && numbers.size() > 4) {
                    holder.ballFiveResuls.setText(numbers.get(4) + "");
                    holder.ballFiveResuls.setVisibility(View.VISIBLE);
                }
                if (holder.ballSixResuls != null && numbers.size() > 5) {
                    holder.ballSixResuls.setText(numbers.get(5) + "");
                    holder.ballSixResuls.setVisibility(View.VISIBLE);
                }
                if (holder.ballSevenResuls != null && bonusNumbers.size() > 0) {
                    holder.ballSevenResuls.setText(bonusNumbers.get(0) + "");
                    holder.ballSevenResuls.setVisibility(View.VISIBLE);
                    holder.ballSevenResuls.setBonusColor(true);
                }
                if (holder.ballBonusResuls != null && bonusNumbers.size() > 1) {
                    holder.ballBonusResuls.setText(bonusNumbers.get(1) + "");
                    holder.ballBonusResuls.setVisibility(View.VISIBLE);
                    holder.ballBonusResuls.setBonusColor(true);
                }

                break;
        }
    }


    private void setFiveTwo(ViewHolder holder, List<Integer> numbers, List<Integer> bonusNumbers, int state) {
        switch (state) {

            case STATE_SAVEDLOTTO:
                holder.layoutBalls.removeView(holder.ballBonus);
                holder.layoutBalls.removeView(holder.ballBonusTwo);
                if (holder.ballOne != null && numbers.size() > 0) {
                    holder.ballOne.setText(numbers.get(0) + "");
                    holder.ballOne.setVisibility(View.VISIBLE);
                }
                if (holder.ballTwo != null && numbers.size() > 1) {
                    holder.ballTwo.setText(numbers.get(1) + "");
                    holder.ballTwo.setVisibility(View.VISIBLE);
                }
                if (holder.ballThree != null && numbers.size() > 2) {
                    holder.ballThree.setText(numbers.get(2) + "");
                    holder.ballThree.setVisibility(View.VISIBLE);
                }
                if (holder.ballFour != null && numbers.size() > 3) {
                    holder.ballFour.setText(numbers.get(3) + "");
                    holder.ballFour.setVisibility(View.VISIBLE);
                }
                if (holder.ballFive != null && numbers.size() > 4) {
                    holder.ballFive.setText(numbers.get(4) + "");
                    holder.ballFive.setVisibility(View.VISIBLE);
                }
                if (holder.ballSix != null && bonusNumbers.size() > 0) {
                    holder.ballSix.setText(bonusNumbers.get(0) + "");
                    holder.ballSix.setVisibility(View.VISIBLE);
                    holder.ballSix.setBonusColor(true);
                }
                if (holder.ballSeven != null && bonusNumbers.size() > 1) {
                    holder.ballSeven.setText(bonusNumbers.get(1) + "");
                    holder.ballSeven.setVisibility(View.VISIBLE);
                    holder.ballSeven.setBonusColor(true);
                }

                break;

            case STATE_LOTTODRAW:
                holder.layoutResults.removeView(holder.ballBonus);
                holder.layoutResults.removeView(holder.ballBonusTwo);
                if (holder.ballOneResuls != null && numbers.size() > 0) {
                    holder.ballOneResuls.setText(numbers.get(0) + "");
                    holder.ballOneResuls.setVisibility(View.VISIBLE);
                }
                if (holder.ballTwoResuls != null && numbers.size() > 1) {
                    holder.ballTwoResuls.setText(numbers.get(1) + "");
                    holder.ballTwoResuls.setVisibility(View.VISIBLE);
                }
                if (holder.ballThreeResuls != null && numbers.size() > 2) {
                    holder.ballThreeResuls.setText(numbers.get(2) + "");
                    holder.ballThreeResuls.setVisibility(View.VISIBLE);
                }
                if (holder.ballFourResuls != null && numbers.size() > 3) {
                    holder.ballFourResuls.setText(numbers.get(3) + "");
                    holder.ballFourResuls.setVisibility(View.VISIBLE);
                }
                if (holder.ballFiveResuls != null && numbers.size() > 4) {
                    holder.ballFiveResuls.setText(numbers.get(4) + "");
                    holder.ballFiveResuls.setVisibility(View.VISIBLE);
                }
                if (holder.ballSixResuls != null && bonusNumbers.size() > 0) {
                    holder.ballSixResuls.setText(bonusNumbers.get(0) + "");
                    holder.ballSixResuls.setVisibility(View.VISIBLE);
                    holder.ballSixResuls.setBonusColor(true);
                }
                if (holder.ballSevenResuls != null && bonusNumbers.size() > 1) {
                    holder.ballSevenResuls.setText(bonusNumbers.get(1) + "");
                    holder.ballSevenResuls.setVisibility(View.VISIBLE);
                    holder.ballSevenResuls.setBonusColor(true);
                }

                break;
        }
    }

    private void setFiveOne(ViewHolder holder, List<Integer> numbers, List<Integer> bonusNumbers, int state) {
        switch (state) {

            case STATE_SAVEDLOTTO:
                holder.layoutBalls.removeView(holder.ballBonus);
                holder.layoutBalls.removeView(holder.ballBonusTwo);
                if (holder.ballOne != null && numbers.size() > 0) {
                    holder.ballOne.setText(numbers.get(0) + "");
                    holder.ballOne.setVisibility(View.VISIBLE);
                }
                if (holder.ballTwo != null && numbers.size() > 1) {
                    holder.ballTwo.setText(numbers.get(1) + "");
                    holder.ballTwo.setVisibility(View.VISIBLE);
                }
                if (holder.ballThree != null && numbers.size() > 2) {
                    holder.ballThree.setText(numbers.get(2) + "");
                    holder.ballThree.setVisibility(View.VISIBLE);
                }
                if (holder.ballFour != null && numbers.size() > 3) {
                    holder.ballFour.setText(numbers.get(3) + "");
                    holder.ballFour.setVisibility(View.VISIBLE);
                }
                if (holder.ballFive != null && numbers.size() > 4) {
                    holder.ballFive.setText(numbers.get(4) + "");
                    holder.ballFive.setVisibility(View.VISIBLE);
                }
                if (holder.ballSix != null && bonusNumbers.size() > 0) {
                    holder.ballSix.setText(bonusNumbers.get(0) + "");
                    holder.ballSix.setVisibility(View.VISIBLE);
                    holder.ballSix.setBonusColor(true);
                }

                break;


            case STATE_LOTTODRAW:

                holder.layoutResults.removeView(holder.ballBonus);
                holder.layoutResults.removeView(holder.ballBonusTwo);
                if (holder.ballOneResuls != null && numbers.size() > 0) {
                    holder.ballOneResuls.setText(numbers.get(0) + "");
                    holder.ballOneResuls.setVisibility(View.VISIBLE);
                }
                if (holder.ballTwoResuls != null && numbers.size() > 1) {
                    holder.ballTwoResuls.setText(numbers.get(1) + "");
                    holder.ballTwoResuls.setVisibility(View.VISIBLE);
                }
                if (holder.ballThreeResuls != null && numbers.size() > 2) {
                    holder.ballThreeResuls.setText(numbers.get(2) + "");
                    holder.ballThreeResuls.setVisibility(View.VISIBLE);
                }
                if (holder.ballFourResuls != null && numbers.size() > 3) {
                    holder.ballFourResuls.setText(numbers.get(3) + "");
                    holder.ballFourResuls.setVisibility(View.VISIBLE);
                }
                if (holder.ballFiveResuls != null && numbers.size() > 4) {
                    holder.ballFiveResuls.setText(numbers.get(4) + "");
                    holder.ballFiveResuls.setVisibility(View.VISIBLE);
                }
                if (holder.ballSixResuls != null && bonusNumbers.size() > 0) {
                    holder.ballSixResuls.setText(bonusNumbers.get(0) + "");
                    holder.ballSixResuls.setVisibility(View.VISIBLE);
                    holder.ballSixResuls.setBonusColor(true);
                }
                break;
        }
    }
}
