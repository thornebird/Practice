package practice.practice.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import practice.practice.DataModel.LotteyBaseInfo;
import practice.practice.DataModel.LottoResultSql;
import practice.practice.DataModel.LottocentreLottDraw;
import practice.practice.DataModel.UserLotto;
import practice.practice.R;
import practice.practice.UI.LottoBall;
import practice.practice.UI.LottoBallLayout;


/**
 * Created by ThorneBird on 3/23/2017.
 */
public class ListAdapterLottoDraw<T> extends ArrayAdapter {

    private Context context;
    private int resourceId;
    private ArrayList<T> baselotto;
    private String drawDate;
    private String wininnigNumbers;
    private String bonusNumbers;
    private String jackpot;
    private String totalWinners;
    private int lastPostion=0;


    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     */
    public ListAdapterLottoDraw(Context context, int resource, ArrayList<T> baselLottos) {
        super(context, resource);
        this.context = context;
        this.resourceId = resource;
        this.baselotto = baselLottos;
        this.drawDate = context.getResources().getString(R.string.draw_date);
        this.wininnigNumbers = context.getResources().getString(R.string.numbers);
        this.bonusNumbers = context.getResources().getString(R.string.bonus_numbers);
        this.jackpot = context.getResources().getString(R.string.jackpot);
        this.totalWinners = context.getResources().getString(R.string.winners);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LottocentreLottDraw lotto=null;
        UserLotto userLotto=null;

        ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            convertView = mInflater.inflate(resourceId, null);

            holder = new ViewHolder();
            holder.imLogo = (ImageView) convertView.findViewById(R.id.im_lotto);
            holder.tvDrawDate = (TextView) convertView.findViewById(R.id.tv_lottodate);
            holder.tvJackpot = (TextView) convertView.findViewById(R.id.tv_jackpot);
            holder.tvTotalWinners = (TextView) convertView.findViewById(R.id.tv_winners);
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

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if(position>2&&position>lastPostion) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.enter_from_right);
            convertView.startAnimation(animation);
            lastPostion=position;
        }

        if(getItem(position) instanceof LottocentreLottDraw) {
            lotto = (LottocentreLottDraw) getItem(position);

            holder.tvDrawDate.setText(drawDate+" "+lotto.getDate());
            holder.tvJackpot.setText(jackpot + " " + lotto.getJackpot());
            holder.tvTotalWinners.setText(totalWinners + " " + lotto.getWinners());
            setLogoDraw(holder.imLogo, lotto);
            if (lotto.getDrawNumbers().size() > 0 && lotto.getDrawBonusNumbers().size() > 0) {
                setNumbersLottoDraw(holder, lotto.getTitle(), lotto.getDrawNumbers(), lotto.getDrawBonusNumbers());
            }
        } if(getItem(position) instanceof UserLotto) {
            userLotto = (UserLotto) getItem(position);
            userLotto.getLottoBallsArray();
            userLotto.getBonusBallsArray();

            holder.tvDrawDate.setText(drawDate+" "+userLotto.getDrawDate());
            holder.tvJackpot.setText(userLotto.getLottoName());
            setLogoUser(holder.imLogo,userLotto);
           if (userLotto.getLottoBallsArray().size() > 0 && userLotto.getBonusBallsArray().size() > 0) {
                setNumbersUserLotto(holder, userLotto.getLottoName(), userLotto.getLottoBallsArray(), userLotto.getBonusBallsArray());
            }
        }if(getItem(position) instanceof LottoResultSql) {
            Log.i("instance","result");
        }

        return convertView;
    }


    @Override
    public int getCount() {
        return baselotto.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return baselotto.get(position);
    }


    private class ViewHolder {
        TextView tvDrawDate;
        TextView tvJackpot;
        TextView tvTotalWinners;
        ImageView imLogo;
        LottoBallLayout layoutBalls;
        LottoBall ballOne;
        LottoBall ballTwo;
        LottoBall ballThree;
        LottoBall ballFour;
        LottoBall ballFive;
        LottoBall ballSix;
        LottoBall ballSeven;
        LottoBall ballBonus;
        LottoBall ballBonusTwo;
    }




    private void setNumbersLottoDraw(ViewHolder holder, String title, List<Integer> numbers, List<Integer> bonusNumbers) {
        if (title.contains(LotteyBaseInfo.AUSOZLOTTO.getLottoTitle())) {
            setSevenTwo(holder, numbers, bonusNumbers);
        } else if (title.contains(LotteyBaseInfo.AUSPOWERBALL.getLottoTitle())) {
            setSixOne(holder, numbers, bonusNumbers);
        } else if (title.contains(LotteyBaseInfo.AUSlOTTO.getLottoTitle())) {
            setSixTwo(holder, numbers, bonusNumbers);
        } else if (title.contains(LotteyBaseInfo.BRITISHLOTTO.getLottoTitle())) {
            setSixOne(holder, numbers, bonusNumbers);
        } else if (title.contains(LotteyBaseInfo.CANADIANLOTTO.getLottoTitle())) {
            setSixOne(holder, numbers, bonusNumbers);
        } else if (title.contains(LotteyBaseInfo.EUROMILLIONS.getLottoTitle())) {
            setFiveTwo(holder, numbers, bonusNumbers);
        } else if (title.contains(LotteyBaseInfo.EUROJACKPOT.getLottoTitle())) {
            setFiveTwo(holder, numbers, bonusNumbers);
        } else if (title.contains(LotteyBaseInfo.FRENCHLOTTO.getLottoTitle())) {
          setFiveOne(holder, numbers, bonusNumbers);
        } else if (title.contains(LotteyBaseInfo.GERMANLOTTO.getLottoTitle())) {
            setSixOne(holder, numbers, bonusNumbers);
        } else if (title.contains(LotteyBaseInfo.IRISHLOTTO.getLottoTitle())) {
            setSixOne(holder, numbers, bonusNumbers);
        } else if (title.contains(LotteyBaseInfo.SPANISHLOTTO.getLottoTitle())) {
            setSixTwo(holder, numbers, bonusNumbers);
        } else if (title.contains(LotteyBaseInfo.USAMEGAMILLIONS.getLottoTitle())) {
            setFiveOne(holder, numbers, bonusNumbers);
        } else if (title.contains(LotteyBaseInfo.USAPOWERBALL.getLottoTitle())) {
            setFiveOne(holder, numbers, bonusNumbers);
        }
    }

    private void setNumbersUserLotto(ViewHolder holder, String title, List<Integer> numbers, List<Integer> bonusNumbers) {
        if (title.equals(LotteyBaseInfo.AUSOZLOTTO.getLottoTitle())) {
            setSevenTwo(holder, numbers, bonusNumbers);
        } else if (title.equals(LotteyBaseInfo.AUSPOWERBALL.getLottoTitle())) {
            setSixOne(holder, numbers, bonusNumbers);
        } else if (title.equals(LotteyBaseInfo.AUSlOTTO.getLottoTitle())) {
            setSixTwo(holder, numbers, bonusNumbers);
        } else if (title.equals(LotteyBaseInfo.BRITISHLOTTO.getLottoTitle())) {
            setSixOne(holder, numbers, bonusNumbers);
        } else if (title.equals(LotteyBaseInfo.CANADIANLOTTO.getLottoTitle())) {
            setSixOne(holder, numbers, bonusNumbers);
        } else if (title.equals(LotteyBaseInfo.EUROMILLIONS.getLottoTitle())) {
            setFiveTwo(holder, numbers, bonusNumbers);
        } else if (title.equals(LotteyBaseInfo.EUROJACKPOT.getLottoTitle())) {
            setFiveTwo(holder, numbers, bonusNumbers);
        } else if (title.equals(LotteyBaseInfo.FRENCHLOTTO.getLottoTitle())) {
            setFiveOne(holder, numbers, bonusNumbers);
        } else if (title.equals(LotteyBaseInfo.GERMANLOTTO.getLottoTitle())) {
            setSixOne(holder, numbers, bonusNumbers);
        } else if (title.equals(LotteyBaseInfo.IRISHLOTTO.getLottoTitle())) {
            setSixOne(holder, numbers, bonusNumbers);
        } else if (title.equals(LotteyBaseInfo.SPANISHLOTTO.getLottoTitle())) {
            setSixTwo(holder, numbers, bonusNumbers);
        } else if (title.equals(LotteyBaseInfo.USAMEGAMILLIONS.getLottoTitle())) {
            setFiveOne(holder, numbers, bonusNumbers);
        } else if (title.equals(LotteyBaseInfo.USAPOWERBALL.getLottoTitle())) {
            setFiveOne(holder, numbers, bonusNumbers);
        }
    }

    private void setSevenTwo(ViewHolder holder, List<Integer> numbers, List<Integer> bonusNumbers) {
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
    }



    private void setSixOne(ViewHolder holder, List<Integer> numbers, List<Integer> bonusNumbers) {
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
    }

    private void setSixTwo(ViewHolder holder, List<Integer> numbers, List<Integer> bonusNumbers) {
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
    }


    private void setFiveTwo(ViewHolder holder, List<Integer> numbers, List<Integer> bonusNumbers) {
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
        } if (holder.ballSeven != null && bonusNumbers.size() > 1) {
            holder.ballSeven.setText(bonusNumbers.get(1) + "");
            holder.ballSeven.setVisibility(View.VISIBLE);
            holder.ballSeven.setBonusColor(true);
        }
    }

    private void setFiveOne(ViewHolder holder, List<Integer> numbers, List<Integer> bonusNumbers) {
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
    }
    private void setLogoDraw(ImageView im, Object lottoDraw) {
            LottocentreLottDraw lotto=(LottocentreLottDraw)lottoDraw;
            if (lotto.getTitle().contains(LotteyBaseInfo.AUSOZLOTTO.getLottoTitle())) {
                im.setImageResource(R.drawable.austraflag);
            } else if (lotto.getTitle().contains(LotteyBaseInfo.AUSPOWERBALL.getLottoTitle())) {
                im.setImageResource(R.drawable.austraflag);
            } else if (lotto.getTitle().contains(LotteyBaseInfo.BRITISHLOTTO.getLottoTitle())) {
                im.setImageResource(R.drawable.britishflag);
            } else if (lotto.getTitle().contains(LotteyBaseInfo.CANADIANLOTTO.getLottoTitle())) {
                im.setImageResource(R.drawable.canflag);
            } else if (lotto.getTitle().contains(LotteyBaseInfo.EUROMILLIONS.getLottoTitle())) {
                im.setImageResource(R.drawable.europflag);
            } else if (lotto.getTitle().contains(LotteyBaseInfo.EUROJACKPOT.getLottoTitle())) {
                im.setImageResource(R.drawable.europflag);
            } else if (lotto.getTitle().contains(LotteyBaseInfo.FRENCHLOTTO.getLottoTitle())) {
                im.setImageResource(R.drawable.frenchflag);
            } else if (lotto.getTitle().contains(LotteyBaseInfo.GERMANLOTTO.getLottoTitle())) {
                im.setImageResource(R.drawable.germanflag);
            } else if (lotto.getTitle().contains(LotteyBaseInfo.IRISHLOTTO.getLottoTitle())) {
                im.setImageResource(R.drawable.irishflag);
            } else if (lotto.getTitle().contains(LotteyBaseInfo.SPANISHLOTTO.getLottoTitle())) {
                im.setImageResource(R.drawable.spanishflag);
            } else if (lotto.getTitle().contains(LotteyBaseInfo.USAMEGAMILLIONS.getLottoTitle())) {
                im.setImageResource(R.drawable.usaflag);
            } else if (lotto.getTitle().contains(LotteyBaseInfo.USAPOWERBALL.getLottoTitle())) {
                im.setImageResource(R.drawable.usaflag);
            }
    }


    private void setLogoUser(ImageView im, UserLotto lotto){
            if (lotto.getLottoName().equals(LotteyBaseInfo.AUSOZLOTTO.getLottoTitle())) {
                im.setImageResource(R.drawable.austraflag);
            } else if (lotto.getLottoName().equals(LotteyBaseInfo.AUSPOWERBALL.getLottoTitle())) {
                im.setImageResource(R.drawable.austraflag);
            }else if (lotto.getLottoName().equals(LotteyBaseInfo.AUSlOTTO.getLottoTitle())) {
                im.setImageResource(R.drawable.austraflag);
            } else if (lotto.getLottoName().equals(LotteyBaseInfo.BRITISHLOTTO.getLottoTitle())) {
                im.setImageResource(R.drawable.britishflag);
            } else if (lotto.getLottoName().equals(LotteyBaseInfo.CANADIANLOTTO.getLottoTitle())) {
                im.setImageResource(R.drawable.canflag);
            } else if (lotto.getLottoName().equals(LotteyBaseInfo.EUROMILLIONS.getLottoTitle())) {
                im.setImageResource(R.drawable.europflag);
            } else if (lotto.getLottoName().equals(LotteyBaseInfo.EUROJACKPOT.getLottoTitle())) {
                im.setImageResource(R.drawable.europflag);
            } else if (lotto.getLottoName().equals(LotteyBaseInfo.FRENCHLOTTO.getLottoTitle())) {
                im.setImageResource(R.drawable.frenchflag);
            } else if (lotto.getLottoName().equals(LotteyBaseInfo.GERMANLOTTO.getLottoTitle())) {
                im.setImageResource(R.drawable.germanflag);
            } else if (lotto.getLottoName().equals(LotteyBaseInfo.IRISHLOTTO.getLottoTitle())) {
                im.setImageResource(R.drawable.irishflag);
            } else if (lotto.getLottoName().equals(LotteyBaseInfo.SPANISHLOTTO.getLottoTitle())) {
                im.setImageResource(R.drawable.spanishflag);
            } else if (lotto.getLottoName().equals(LotteyBaseInfo.USAMEGAMILLIONS.getLottoTitle())) {
                im.setImageResource(R.drawable.usaflag);
            } else if (lotto.getLottoName().equals(LotteyBaseInfo.USAPOWERBALL.getLottoTitle())) {
                im.setImageResource(R.drawable.usaflag);
            }
        }
    }


