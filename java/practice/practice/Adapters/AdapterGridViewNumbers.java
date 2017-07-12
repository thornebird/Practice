package practice.practice.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import practice.practice.DataModel.LottoCardNumber;
import practice.practice.DataModel.NumberLottoCard;
import practice.practice.R;

import static android.R.attr.value;

/**
 * Created by ThorneBird on 4/21/2017.
 */

public class AdapterGridViewNumbers extends BaseAdapter {

    private Context context;
    private OnNumerAdded mNumberListener;
    private int highestNumber;
    private int numberCount;
    private int bonusNumbersCount;
    private int lottoNumbersCount;
    private int hightestBonusNumber;
    private int selectedNumbersCount = 0;
    private ArrayList<NumberLottoCard> selectedNumbers;
    private ArrayList<LottoCardNumber> displayNumbers;
    private ArrayList<TextView> textViews;
    private TextView tvNumber;
    private float xClick;
    private float yClick;

    public AdapterGridViewNumbers(Context context,
                                  int highestNumber,
                                  int numberCount,
                                  int bonusNumbersCount,
                                  int lottoNumbersCount,
                                  int highestBonusNumber,
                                  ArrayList<LottoCardNumber> displayNumbers) {
        this.context = context;
        this.highestNumber = highestNumber;
        this.numberCount = numberCount;
        this.bonusNumbersCount = bonusNumbersCount;
        this.lottoNumbersCount = lottoNumbersCount;
        this.hightestBonusNumber = highestBonusNumber;
        Log.i("Highest bonust", highestBonusNumber + "");
        this.displayNumbers = displayNumbers;
        selectedNumbers = new ArrayList<>(numberCount);
        textViews = new ArrayList<>(highestNumber);
    }

    public void setListenter(OnNumerAdded mNumberListener) {
        this.mNumberListener = mNumberListener;
    }

    /*public void updateSelection(int highestNumber,
                                int numberCount,
                                int bonusNumbersCount,
                                int lottoNumbersCount,
                                ArrayList<LottoCardNumber> displayNumbers,
                                boolean isReset) {
        this.highestNumber = highestNumber;
        this.numberCount = numberCount;
        this.bonusNumbersCount = bonusNumbersCount;
        this.lottoNumbersCount = lottoNumbersCount;
        removeAllViews();
        *//*if (textViews == null)
            textViews = new ArrayList<>(highestNumber);
        if (selectedNumbers == null) {
            selectedNumbers = new ArrayList<>(numberCount);
        }*//*
        //  textViews.clear();
        this.displayNumbers.clear();
        this.displayNumbers.addAll(displayNumbers);
        selectedNumbers.ensureCapacity(numberCount);
        this.getCount();
        this.notifyDataSetInvalidated();
    }*/

    public int getCount() {
        return displayNumbers.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return displayNumbers.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LottoCardNumber lottoDisplay = displayNumbers.get(position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_grid, null);
            tvNumber = (TextView) convertView.findViewById(R.id.tv_number);
        }
        setTextData(tvNumber, lottoDisplay);
        return convertView;
    }

    private void setTextData(TextView tvNumber, LottoCardNumber lottoCardNumber) {
        tvNumber.setId(lottoCardNumber.getId());
        tvNumber.setText(lottoCardNumber.getNumber() + "");
        tvNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getClickPosition(v);
                addSelected(v.getId());
            }
        });
        textViews.add(tvNumber);
    }

    public void addSelected(int id) {
        boolean isLegal = true;
        int number = id;
        if (!checkNumber(number) && selectedNumbersCount < numberCount) {
            selectedNumbersCount++;
            Log.i("selectedNumbersCount", selectedNumbersCount + "");
            if (isBonus() && number >= hightestBonusNumber) {
                selectedNumbersCount--;
                isLegal=false;
                mNumberListener.invalidBonusNumber(number+1);
            }

            if(isLegal) {
                NumberLottoCard numberLottoCard = new NumberLottoCard(number, false, id /*+ 1*/, isBonus());
                Log.i("ID added", numberLottoCard.getId() + "");
                selectedNumbers.add(numberLottoCard);
                setBackground(number, false, numberLottoCard);
                Log.i("!exists", "!exists/" + selectedNumbers.toString());
                if (mNumberListener != null)
                    mNumberListener.numberAdded(numberLottoCard, xClick, yClick);
            }

        }
    }

    private boolean checkNumber(int number) {
        for (int i = 0; i < selectedNumbers.size(); i++) {
            if (selectedNumbers.get(i).getNumber() == number) {
                removeNumber(i, number);
                return true;
            }
        }
        return false;
    }

    private void removeNumber(int position, int number) {
        NumberLottoCard numberLottoCard = selectedNumbers.get(position);
        selectedNumbers.remove(numberLottoCard);
        selectedNumbersCount--;
        setBackground(number, true, numberLottoCard);
        if (mNumberListener != null)
            mNumberListener.numberRemoved(numberLottoCard);
    }


    private void setBackground(int position, boolean remove, NumberLottoCard numberLottoCard) {
        int color = 0;
        int textColor = 0;
        if (numberLottoCard.getBonus() == true && !remove) {
            color = Color.parseColor("#66ef66");
            textColor = Color.BLACK;
        } else if (numberLottoCard.getBonus() == false && !remove) {
            color = Color.parseColor("#00BFFF");
            textColor = Color.BLACK;
        } else if (remove) {
            color = Color.WHITE;
            textColor = Color.BLACK;
        }
        TextView tv = textViews.get(position + 1);
        tv.setTextColor(textColor);
        tv.setBackgroundColor(color);
    }

    private boolean isBonusOpen() {
        int bonusCount = 0;
        int lottoCount = 0;
        for (int ii = 0; ii < selectedNumbers.size(); ii++) {
            if (selectedNumbers.get(ii).getBonus() == false) {
                lottoCount++;
            }
        }
        if (lottoCount < lottoNumbersCount) {
            return false;
        }
        for (int i = 0; i < selectedNumbers.size(); i++) {
            if (selectedNumbers.get(i).getBonus() == true) {
                bonusCount++;
            }
        }
        if (bonusCount < bonusNumbersCount) {
            return true;
        }
        return false;
    }


    public void removeAllViews() {
        Iterator<NumberLottoCard> iterator = selectedNumbers.iterator();
        while (iterator.hasNext()) {
            //NumberLottoCard numberLottoCard = selectedNumbers.get(i);
            NumberLottoCard numberLottoCard = iterator.next();
            Log.i("selectedNumbersCount", selectedNumbersCount + "");
            // setBackground(numberLottoCard.getId(), true, numberLottoCard);
            TextView tv = null;
            for (int i = 0; i < textViews.size(); i++) {
                if (numberLottoCard.getId() /*- 1*/ == textViews.get(i).getId()) {
                    Log.i("ID re", numberLottoCard.getId() + "");
                    tv = textViews.get(i);
                    tv.setTextColor(Color.BLACK);
                    tv.setBackgroundColor(Color.WHITE);
                }
            }

            //  selectedNumbers.remove(numberLottoCard);
            iterator.remove();
            selectedNumbersCount--;
            if (mNumberListener != null)
                mNumberListener.numberRemoved(numberLottoCard);
        }

    }


    public void quickPick() {

        ArrayList<Integer> quickNumbers = new ArrayList<>();
        int i = 0;
        while (i < numberCount) {
            boolean isExist = false;
            int number = ThreadLocalRandom.current().nextInt(2, highestNumber + 2);
            number = number - 1;
            for (int f = 0; f < quickNumbers.size(); f++) {
                if (quickNumbers.get(f).equals(new Integer(number))) {
                    isExist = true;
                }
            }

            if(number>hightestBonusNumber&&quickNumbers.size()>=lottoNumbersCount) {
                isExist = true;
                 Log.i("higher","hbn: "+hightestBonusNumber+"snc: "+selectedNumbersCount+" lnc: "+lottoNumbersCount);
            }
            if (!isExist) {
                addSelected(number - 1);
                quickNumbers.add(new Integer(number));
                i++;
            }
        }


    }

    private boolean isBonus() {
        boolean isBonus = selectedNumbersCount > lottoNumbersCount && isBonusOpen() ? true : false;
        return isBonus;
    }


    private void getClickPosition(View v) {
        int fromLoc[] = new int[2];
        v.getLocationOnScreen(fromLoc);
        xClick = fromLoc[0];
        yClick = fromLoc[1];
    }

    public interface OnNumerAdded {
        public void numberAdded(NumberLottoCard numberLottoCard, float xClick, float yClick);
        public void numberRemoved(NumberLottoCard numberLottoCard);
        public void invalidBonusNumber(int number);
    }


}
