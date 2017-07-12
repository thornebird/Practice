package practice.practice.DataModel;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by ThorneBird on 6/10/2017.
 */

public class LottoResultCalculator implements Parcelable{

    private String lottoName;
    private LottocentreLottDraw lottoDraw;
    private UserLotto userLotto;
    private ArrayList<Integer>numbers;
    private ArrayList<Integer>bonusNumbers;
    private int numbersCount;
    private int bonusNumbersCount;


    public LottoResultCalculator(LottocentreLottDraw lottoDraw, UserLotto userLotto){
        this.lottoDraw=lottoDraw;
        this.userLotto=userLotto;
        calculateDifference();
    }


    public UserLotto getUserLotto(){
        return userLotto;
    }

    public LottocentreLottDraw getLottoDraw(){
        return lottoDraw;
    }

    public ArrayList<Integer>getNumbers(){
        return numbers;
    }

    public String getNumbersString(){
        String number="";
        for(int i=0;i<numbers.size();i++){
            number+=numbers.get(i);
            number+=",";
        }
        return number;
    }



    public ArrayList<Integer>getBonusNumbers(){
        return bonusNumbers;
    }

    public String getBonusNumbersString(){
        String bonusNumber="";

        for(int i=0;i<bonusNumbers.size();i++){
            bonusNumber+=bonusNumbers.get(i);
            bonusNumber+=",";
        }
        return bonusNumber;
    }
    public int getNumbersCount(){
        return numbersCount;
    }

    public int getBonusNumbersCount(){
        return bonusNumbersCount;
    }

    public String getLottoName(){
        return lottoName;
    }

    public void calculateDifference(){
         numbers=new ArrayList<>();
         bonusNumbers=new ArrayList<>();

        for(int i=0;i<lottoDraw.getDrawNumbers().size();i++){
            Integer number=lottoDraw.getDrawNumbers().get(i);
            for(int ii=0;ii<userLotto.getLottoBallsArray().size();ii++){
                if(number.equals(userLotto.getLottoBallsArray().get(ii))){
                    numbersCount++;
                    numbers.add(number);
                    Log.i("numbersadded","numbersadded");

                }
            }
        }
        for(int i=0;i<lottoDraw.getDrawBonusNumbers().size();i++){
            Integer number=lottoDraw.getDrawBonusNumbers().get(i);
            for(int ii=0;ii<userLotto.getBonusBallsArray().size();ii++){
                if(number.equals(userLotto.getBonusBallsArray().get(ii))){
                    bonusNumbersCount++;
                    bonusNumbers.add(number);
                    Log.i("bonusnumadded","bonusnumadded");

                }
            }
        }
        Log.i("Numberscount",numbersCount+"");
        Log.i("Numberscount1",bonusNumbersCount+"");
        Log.i("Numberscount2",numbers.size()+"");
        Log.i("Numberscount3",bonusNumbers.size()+"");
    }

    @Override
    public String toString() {
        return "LottoResultCalculator{" +
                "lottoDraw=" + lottoDraw +
                ", userLotto=" + userLotto +
                ", numbers=" + numbers +
                ", bonusNumbers=" + bonusNumbers +
                ", numbersCount=" + numbersCount +
                ", bonusNumbersCount=" + bonusNumbersCount +
                '}';
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable
     * instance's marshaled representation. For example, if the object will
     * include a file descriptor in the output of {@link #writeToParcel(Parcel, int)},
     * the return value of this method must include the
     * {@link #CONTENTS_FILE_DESCRIPTOR} bit.
     *
     * @return a bitmask indicating the set of special object types marshaled
     * by this Parcelable object instance.
     * @see #CONTENTS_FILE_DESCRIPTOR
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(lottoName);
        dest.writeParcelable(lottoDraw,flags);
        dest.writeParcelable(userLotto,flags);
        dest.writeSerializable(numbers);
        dest.writeSerializable(bonusNumbers);
        dest.writeInt(numbersCount);
        dest.writeInt(bonusNumbersCount);
    }

    private LottoResultCalculator(Parcel in){
        lottoDraw=(LottocentreLottDraw)in.readParcelable(LottocentreLottDraw.class.getClassLoader());
        userLotto=(UserLotto)in.readParcelable(UserLotto.class.getClassLoader());
        numbers=(ArrayList<Integer>)in.readSerializable();
        bonusNumbers=(ArrayList<Integer>)in.readSerializable();
        numbersCount=in.readInt();
        bonusNumbersCount=in.readInt();
        lottoName=in.readString();
    }

    public static final Parcelable.Creator<LottoResultCalculator> CREATOR = new Creator<LottoResultCalculator>() {
        @Override
        public LottoResultCalculator createFromParcel(Parcel source) {
            return new LottoResultCalculator(source);
        }

        @Override
        public LottoResultCalculator[] newArray(int size) {
            return new LottoResultCalculator[size];
        }
    };

}
