package practice.practice.Fragments;

import practice.practice.Constants.UtilConstants;
import practice.practice.DataModel.CalendarLotto;
import practice.practice.DataModel.LottoCardNumber;
import practice.practice.DataModel.NumberLottoCard;
import practice.practice.UI.LottoBall;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import practice.practice.Adapters.AdapterGridViewNumbers;
import practice.practice.DataModel.LotteyBaseInfo;
import practice.practice.DataModel.LottoSelectorManager;
import practice.practice.DataModel.UserLotto;
import practice.practice.R;

/**
 * Created by ThorneBird on 4/21/2017.
 */

public class FragmentLottoTicket extends Fragment implements AdapterGridViewNumbers.OnNumerAdded, View.OnClickListener {

    private final static String KEY_LOTTONAME = "lottoName";
    private final static String KEY_DRAWDATE = "drawDate";
    private final static String KEY_HIGHNUMBER = "keyHighNumber";
    private final static String KEY_HIGHBONUS = "keyHighBonusNumber";
    private final static int ERROR_BONUS = 0;
    private final static int ERROR_SAVE = 1;
    private OnLottoSelected mListener;
    private ListView lv;
    private GridView glNumbers;
    private String lottoName;
    private String drawDate;
    private int highNumber;
    private int highestBonusNumber;
    private AdapterGridViewNumbers adapterGridView;
    private ArrayList<LottoBall> viewsLottoBalls;
    private ArrayList<NumberLottoCard> currentSelection;
    private int totalLottoBalls;
    private int amountLottoBalls;
    private int amountBonusLottoBalls;
    private LinearLayout layoutBalls;
    private String[] lottoNames;
    private LottoSelectorManager manager;
    private int imgLottoLarge;
    private int imgLottoSmall;
    private ImageView imSmall;
    private ImageView imLarge;
    private ArrayList<LottoCardNumber> displayNumbers;
    private TextView tvName;
    private TextView tvInfo;
    private TextView tvNextDate;
    private TextView tvHighestBonus;
    private View parentView;
    private String nextDrawDate = "";
    private boolean isDeafault = true;
    private float xClick;
    private float yClick;


    public static FragmentLottoTicket newInstance(String lottoName, String drawDate, int highNumber, int highBonus) {
        Bundle args = new Bundle();
        FragmentLottoTicket fragmentLottoAlarm = new FragmentLottoTicket();
        args.putString(KEY_LOTTONAME, lottoName);
        args.putString(KEY_DRAWDATE, drawDate);
        args.putInt(KEY_HIGHNUMBER, highNumber);
        args.putInt(KEY_HIGHBONUS, highBonus);
        fragmentLottoAlarm.setArguments(args);
        return fragmentLottoAlarm;
    }


    /**
     * Called to do initial creation of a fragment.  This is called after
     * and before
     * {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * <p>
     * <p>Note that this can be called while the fragment's activity is
     * still in the process of being created.  As such, you can not rely
     * on things like the activity's content view hierarchy being initialized
     * at this point.  If you want to do work once the activity itself is
     * created, see {@link #onActivityCreated(Bundle)}.
     * <p>
     * <p>Any restored child fragments will be created before the base
     * <code>Fragment.onCreate</code> method returns.</p>
     *
     * @param savedInstanceState If the fragment is being re-created from
     *                           a previous saved state, this is the state.
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            lottoNames = getContext().getResources().getStringArray(R.array.pager_items);
            lottoName = getArguments().getString(KEY_LOTTONAME);
            drawDate = getArguments().getString(KEY_DRAWDATE);
            manager = new LottoSelectorManager(lottoName);
            highNumber = manager.getHighesNumber();
            highestBonusNumber = manager.getHighestBonusNumber();
            totalLottoBalls = manager.getTotalNumbersCount();
            amountLottoBalls = manager.getNumbersCount();
            amountBonusLottoBalls = manager.getBonusNumbersCount();
            imgLottoLarge = manager.getLargeLoggo();
            imgLottoSmall = manager.getSmallLogo();
            setDisplayNumbers();
            setNextDate();
            currentSelection = new ArrayList<>();
            viewsLottoBalls = new ArrayList<>();
        }

    }

    public void setListener(OnLottoSelected mListener) {
        this.mListener = mListener;
    }

    /**
     * Called to have the fragment instantiate its user interface view.
     * This is optional, and non-graphical fragments can return null (which
     * is the default implementation).  This will be called between
     * {@link #onCreate(Bundle)} and {@link #onActivityCreated(Bundle)}.
     * <p>
     * <p>If you return a View from here, you will later be called in
     * {@link #onDestroyView} when the view is being released.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate
     *                           any views in the fragment,
     * @param container          If non-null, this is the parent view that the fragment's
     *                           UI should be attached to.  The fragment should not add the view itself,
     *                           but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_lottoalarm, null);
        layoutBalls = (LinearLayout) parentView.findViewById(R.id.layout_balls);
        tvName = (TextView) parentView.findViewById(R.id.tv_lotto_name);
        tvNextDate = (TextView) parentView.findViewById(R.id.tv_nextdate);
        tvHighestBonus = (TextView) parentView.findViewById(R.id.tv_highestbonus);
        imSmall = (ImageView) parentView.findViewById(R.id.im_loggo);
        imLarge = (ImageView) parentView.findViewById(R.id.im_large);
        imSmall.setImageResource(imgLottoSmall);
        imLarge.setImageResource(imgLottoLarge);
        Button btnClear = (Button) parentView.findViewById(R.id.btn_clear);
        btnClear.setOnClickListener(this);
        Button btnQuickPick = (Button) parentView.findViewById(R.id.btn_quickpick);
        btnQuickPick.setOnClickListener(this);
        Button btnSave = (Button) parentView.findViewById(R.id.btn_save);
        btnSave.setOnClickListener(this);
        tvInfo = (TextView) parentView.findViewById(R.id.tv_info);
        setInfo();


        glNumbers = (GridView) parentView.findViewById(R.id.gl_numbers);
        adapterGridView = new AdapterGridViewNumbers(getContext(), highNumber, totalLottoBalls,
                amountBonusLottoBalls, amountLottoBalls, highestBonusNumber, displayNumbers);
        adapterGridView.setListenter(this);
        glNumbers.setAdapter(adapterGridView);

        return parentView;
    }


    private void setInfo() {
        String select = getContext().getResources().getString(R.string.select);
        String selectLotto = getContext().getResources().getString(R.string.select_lotto_numbers);
        String and = getContext().getResources().getString(R.string.and);
        String selectBonus = amountBonusLottoBalls > 1 ? getContext().getResources().getString(R.string.select_bonus_numbers) : getContext().getResources().getString(R.string.select_bonus_number);
        String drawDate = getContext().getResources().getString(R.string.next_draw_date);
        String highestBonus = getContext().getResources().getString(R.string.highest_bonusnumber);

        tvInfo.setText(select + " " + amountLottoBalls + " "
                + selectLotto + " " + and + " " +
                " " + amountBonusLottoBalls + " " + selectBonus);

        tvName.setText(lottoName);
        tvName.setTypeface(null, Typeface.BOLD);
        tvNextDate.setText(drawDate + " " + nextDrawDate);
        tvHighestBonus.setText(highestBonus + " " + highestBonusNumber);

    }

    private void setNextDate() {
        int position = 0;
        if (lottoName.equals(""))
            return;
        else if (lottoName.equals(LotteyBaseInfo.AUSOZLOTTO.getLottoTitle()))
            position = UtilConstants.AUS_OZ_LOTTO;
        else if (lottoName.equals(LotteyBaseInfo.AUSlOTTO.getLottoTitle()))
            position = UtilConstants.AUS_LOTTO;
        else if (lottoName.equals(LotteyBaseInfo.AUSPOWERBALL.getLottoTitle()))
            position = UtilConstants.AUS_POWERBALL;
        else if (lottoName.equals(LotteyBaseInfo.BRITISHLOTTO.getLottoTitle()))
            position = UtilConstants.BRITISH_LOTTO;
        else if (lottoName.equals(LotteyBaseInfo.CANADIANLOTTO.getLottoTitle()))
            position = UtilConstants.CANADIAN_LOTT;
        else if (lottoName.equals(LotteyBaseInfo.EUROMILLIONS.getLottoTitle()))
            position = UtilConstants.EURO_MILLIONS;
        else if (lottoName.equals(LotteyBaseInfo.EUROJACKPOT.getLottoTitle()))
            position = UtilConstants.EURO_JACKPOT;
        else if (lottoName.equals(LotteyBaseInfo.FRENCHLOTTO.getLottoTitle()))
            position = UtilConstants.FRENCH_LOTTO;
        else if (lottoName.equals(LotteyBaseInfo.GERMANLOTTO.getLottoTitle()))
            position = UtilConstants.GERMAN_LOTTO;
        else if (lottoName.equals(LotteyBaseInfo.IRISHLOTTO.getLottoTitle()))
            position = UtilConstants.IRISH_LOTTO;
        else if (lottoName.equals(LotteyBaseInfo.SPANISHLOTTO.getLottoTitle()))
            position = UtilConstants.SPANISH_LOTTO;
        else if (lottoName.equals(LotteyBaseInfo.USAMEGAMILLIONS.getLottoTitle()))
            position = UtilConstants.USA_MEGA_MILLIONS;
        else if (lottoName.equals(LotteyBaseInfo.USAPOWERBALL.getLottoTitle()))
            position = UtilConstants.USA_POWERBALL;

        CalendarLotto calendarLotto = new CalendarLotto(position);
        nextDrawDate = calendarLotto.getNextDrawDay();
    }

    @Override
    public void numberAdded(NumberLottoCard number, float x, float y) {
        xClick = x;
        yClick = y;
        if (isDeafault) {
            Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.slide_up);
            layoutBalls.startAnimation(anim);
            isDeafault = false;
        }
        currentSelection.add(number);
        int numberShow = number.getNumber() + 1;
        final LottoBall ball = createLottoBall(numberShow, number.getBonus(), number.getId());
        ball.setVisibility(View.INVISIBLE);

        viewsLottoBalls.add(ball);
        layoutBalls.addView(ball);
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.slide_rtl);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                ball.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ball.startAnimation(anim);
    }

    @Override
    public void numberRemoved(NumberLottoCard number) {
        removeView(number);
    }

    @Override
    public void invalidBonusNumber(int number) {
        showError(ERROR_BONUS);
    }


    private LottoBall createLottoBall(int number, boolean isBonus, int id) {
        LottoBall ball = new LottoBall(getContext(),isBonus);
        ball.setLayoutParams(new ViewGroup.LayoutParams(110, 110));
        ball.setText(number + "");
        ball.setId(id);
        return ball;
    }

    private void removeView(NumberLottoCard numberLottoCard) {
        View view = null;
        for (int i = 0; i < layoutBalls.getChildCount(); i++) {
            if (layoutBalls.getChildAt(i).getId() == numberLottoCard.getId()) {
                view = layoutBalls.getChildAt(i);
                LottoBall ball = (LottoBall) view;
                Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.slide_ltr);
                view.startAnimation(anim);
                layoutBalls.removeView(view);
                viewsLottoBalls.remove(ball);
                currentSelection.remove(numberLottoCard);
                if (layoutBalls.getChildCount() == 0) {
                    isDeafault = true;
                }
            }
        }

    }


    private void setDisplayNumbers() {
        displayNumbers = new ArrayList<>();
        for (int i = 0; i < highNumber; i++) {
            displayNumbers.add(new LottoCardNumber(i, i + 1, i));
        }
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_clear:
                clear();
                break;
            case R.id.btn_quickpick:
                quickPick();
                break;
            case R.id.btn_save:
                if (totalLottoBalls == currentSelection.size()) {
                    createUserLotto();
                } else {
                    showError(ERROR_SAVE);
                }

                break;
            default:
                break;
        }
    }


    private void clear() {
        isDeafault = true;

        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.slide_down);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                adapterGridView.removeAllViews();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        layoutBalls.startAnimation(anim);
    }

    private void quickPick() {
        adapterGridView.removeAllViews();
        adapterGridView.quickPick();
    }

    private void createUserLotto() {
        String ballsString = "";
        String ballsBonusString = "";
        int position = 0;
        for (int i = 0; i < currentSelection.size(); i++) {
            if (currentSelection.get(i).getBonus() == false) {
                ballsString += currentSelection.get(i).getNumber() + 1;
                if (currentSelection.size()-1 > position)/// edited not work remove -1
                    ballsString += ",";
            }
            position++;
        }
        position = 0;
        for (int i = 0; i < currentSelection.size(); i++) {
            if (currentSelection.get(i).getBonus() == true) {
                ballsBonusString += currentSelection.get(i).getNumber() + 1;
                if (currentSelection.size()-1 > position)/// edited not work remove -1
                    ballsBonusString += ',';
            }
        }

        UserLotto lotto = new UserLotto();
        lotto.setDate(nextDrawDate);
        lotto.setLottoName(lottoName);
        lotto.setLottoNumbers(ballsString);
        lotto.setBonusNumbers(ballsBonusString);
        if (mListener != null)
            mListener.onSave(lotto);

    }

    private void showError(int error) {
        String errorMessage = "";
        switch (error) {
            case ERROR_BONUS:
                errorMessage = String.format(getContext().getResources().getString(R.string.bonus_error), highestBonusNumber);
                final Snackbar snackbarSave = Snackbar.make(parentView, errorMessage, Snackbar.LENGTH_LONG);
                snackbarSave.setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbarSave.dismiss();
                    }
                });
                snackbarSave.show();
                break;
            case ERROR_SAVE:
                errorMessage = String.format(getContext().getResources().getString(R.string.save_error), totalLottoBalls);
                final Snackbar snackbarBonus = Snackbar.make(parentView, errorMessage, Snackbar.LENGTH_LONG);
                snackbarBonus.setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbarBonus.dismiss();
                    }
                });
                snackbarBonus.show();
                break;

        }
    }

    public interface OnLottoSelected {
        public void onSave(UserLotto userLotto);
    }
}
