package practice.practice.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import java.util.ArrayList;

import practice.practice.DataModel.LottoSelectorManager;
import practice.practice.DataModel.UserLotto;
import practice.practice.R;
import practice.practice.UI.LottoBall;

import static practice.practice.R.color.ball;

/**
 * Created by ThorneBird on 4/23/2017.
 */

public class ListAdapterLottoAlarm extends ArrayAdapter<UserLotto> {

    private Context context;
    private int resource;
    private int numbersCount;
    private ArrayList<UserLotto> userLottos;
    private String name;
    //   private itn listSize=10;

    public ListAdapterLottoAlarm(Context context, int resourse, ArrayList<UserLotto> userLotto) {
        super(context, resourse);
        name=userLotto.get(0).getLottoName();
        this.userLottos = userLotto;
        this.context = context;
        this.resource = resourse;
    }


    public void upDateList(ArrayList<UserLotto> newList){
        this.notifyDataSetChanged();
    }



    @Override
    public int getCount() {
        return userLottos.size();
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        UserLotto userLotto = userLottos.get(position);
        ViewHolder holder;
        LayoutInflater inflater;
        if (convertView == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resource, null);
            LinearLayout layoutBalls = (LinearLayout) convertView.findViewById(R.id.layout_balls);
            holder = new ViewHolder();
            //addView(layoutBalls, userLotto, position);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    private class ViewHolder {

    }

   /* private void addView(LinearLayout layout, UserLotto userLotto, int position) {
       // numbersCount = userLotto.getTotalNumbersCount();
        LottoSelectorManager manager=new LottoSelectorManager(name);
        numbersCount=manager.getTotalNumbersCount();
        boolean isBonus = false;
        for (int i = 0; i < numbersCount; i++) {
            if (i == numbersCount - userLotto.getBonusNumbersCount())
                isBonus = true;
            LottoBall ball = new LottoBall(context, isBonus);
            ball.setLayoutParams(new ViewGroup.LayoutParams(110, 110));
            ball.setText(userLotto.getLottoBallsArray()[position] + "");
            layout.addView(ball);
        }
    }*/
}
