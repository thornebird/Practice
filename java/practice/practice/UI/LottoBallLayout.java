package practice.practice.UI;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.vision.text.Line;

import java.util.ArrayList;
import java.util.List;

import practice.practice.DataModel.LottocentreLottDraw;
import practice.practice.R;

/**
 * Created by ThorneBird on 4/8/2017.
 */

public class LottoBallLayout extends LinearLayout {


    private Context context;
    private ArrayList<LottoBall> lottoBalls;
    private LottocentreLottDraw currentLayout;

    public LottoBallLayout(Context context) {
        super(context);
        this.context = context;
        lottoBalls = new ArrayList<>();
    }

    public LottoBallLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        lottoBalls = new ArrayList<>();
    }

    public LottoBallLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        lottoBalls = new ArrayList<>();
    }


    public void setData(LottocentreLottDraw  lottoNumbers) {
        for (int i = 0; i < lottoNumbers.getDrawNumbers().size(); i++) {
                if(!checkIfExists(lottoNumbers.getId())){
                    LottoBall ball = new LottoBall(getContext(),false);
                //    ball.setIsBonus(false);
                    ball.setLayoutParams(new ViewGroup.LayoutParams(110, 110));
                    ball.setText(lottoNumbers.getDrawNumbers().get(i) + "");
                    ball.setId(lottoNumbers.getDrawNumbers().get(i));
                    lottoBalls.add(ball);
                    addView(ball);
            }
        }
    }




    private LottoBall createBall(int number) {
        LottoBall ball = new LottoBall(getContext(),false);
      //  ball.setIsBonus(false);
        ball.setLayoutParams(new ViewGroup.LayoutParams(110, 110));
        ball.setText(number + "");
        ball.setId(number);
        lottoBalls.add(ball);
        return ball;
    }


    private boolean checkIfExists(int id) {
        boolean isExist = false;
        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i).getId() == id) {
                isExist = true;
                break;
            }
        }
        return isExist;
    }


}
