package practice.practice.DataModel;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThorneBird on 6/13/2017.
 */

public class LottoResultSql implements Parcelable {

    String lottoName;
    String lottoDate;
    String drawNumbers;
    String drawBonusNumbers;
    String matchingNumbers;
    String matchingBonusNumbers;
    String userNumbers;
    String userBonusNumbers;
    private ArrayList<Integer> drawNumbersList;
    private ArrayList<Integer> drawBonusNumbersList;
    private ArrayList<Integer> mathchingNumbersList;
    private ArrayList<Integer> matchingBonusNumbersList;
    private ArrayList<Integer>userNumbersList;
    private ArrayList<Integer>userBonusNumberList;


    public LottoResultSql() {}

    public String getLottoName() {
        return lottoName;
    }

    public void setLottoName(String lottoName) {
        this.lottoName = lottoName;
    }

    public String getLottoDate() {
        return lottoDate;
    }

    public void setLottoDate(String lottoDate) {
        this.lottoDate = lottoDate;
    }

    public String getDrawNumbers() {
        return drawNumbers;
    }

    public void setDrawNumbers(String drawNumbers) {
        this.drawNumbers = drawNumbers;
        setDrawNumbersList(drawNumbers);
    }

    public String getDrawBonusNumbers() {
        return drawBonusNumbers;
    }

    public void setDrawBonusNumbers(String drawBonusNumbers) {
        this.drawBonusNumbers = drawBonusNumbers;
        setDrawBonusNumbersList(drawBonusNumbers);
    }

    public String getMatchingNumbers() {
        return matchingNumbers;
    }

    public void setMatchingNumbers(String matchingNumbers) {
        this.matchingNumbers = matchingNumbers;
        setMathchingNumbersList(matchingNumbers);
    }

    public String getMatchingBonusNumbers() {
        return matchingBonusNumbers;
    }

    public void setMatchingBonusNumbers(String matchingBonusNumbers) {
        this.matchingBonusNumbers = matchingBonusNumbers;
        setMatchingBonusNumbersList(matchingBonusNumbers);
    }

    public String getUserNumbers() {
        return userNumbers;
    }

    public void setUserNumbers(String userNumbers) {
        this.userNumbers = userNumbers;
        setUserNumbersList(userNumbers);
    }

    public String getUserBonusNumbers() {
        return userBonusNumbers;
    }

    public void setUserBonusNumbers(String userBonusNumbers) {
        this.userBonusNumbers = userBonusNumbers;
        setUserBonusNumberList(userBonusNumbers);
    }

    private void setDrawNumbersList(String list) {
        if(drawNumbersList==null)
            drawNumbersList=new ArrayList<>();
        String numberToAdd="";
        for(int i=0;i<list.length();i++){
            if(list.charAt(i)!=',') {
                numberToAdd += list.charAt(i);
            }else if(list.charAt(i)==','&&!numberToAdd.equals("")){
                drawNumbersList.add(new Integer(numberToAdd));
                numberToAdd="";
            }
        }
        if(!numberToAdd.equals("")){
            drawNumbersList.add(new Integer(numberToAdd));
        }
    }

    private void setDrawBonusNumbersList(String list) {
        if(drawBonusNumbersList==null)
            drawBonusNumbersList=new ArrayList<>();
        String numberToAdd="";
        for(int i=0;i<list.length();i++){
            if(list.charAt(i)!=',') {
                numberToAdd += list.charAt(i);
            }else if(list.charAt(i)==','&&!numberToAdd.equals("")){
                drawBonusNumbersList.add(new Integer(numberToAdd));
                numberToAdd="";
            }
        }if(!numberToAdd.equals("")) {
            drawBonusNumbersList.add(new Integer(numberToAdd));
        }
    }

    private void setMathchingNumbersList(String list) {
        if(mathchingNumbersList==null)
            mathchingNumbersList=new ArrayList<>();
        String numberToAdd="";
        for(int i=0;i<list.length();i++){
            if(list.charAt(i)!=',') {
                numberToAdd += list.charAt(i);
            }else if(list.charAt(i)==','&&!numberToAdd.equals("")){
                mathchingNumbersList.add(new Integer(numberToAdd));
                numberToAdd="";
            }
        }
        if(!numberToAdd.equals("")){
            mathchingNumbersList.add(new Integer(numberToAdd));
        }
    }

    private void setMatchingBonusNumbersList(String list) {
        if(matchingBonusNumbersList==null)
            matchingBonusNumbersList=new ArrayList<>();
        String numberToAdd="";
        for(int i=0;i<list.length();i++){
            if(list.charAt(i)!=',') {
                numberToAdd += list.charAt(i);
            }else if(list.charAt(i)==','&&!numberToAdd.equals("")){
                matchingBonusNumbersList.add(new Integer(numberToAdd));
                numberToAdd="";
            }
        }
        if(!numberToAdd.equals("")){
            matchingBonusNumbersList.add(new Integer(numberToAdd));
        }
    }

    private void setUserNumbersList(String list) {
        if(userNumbersList==null)
            userNumbersList=new ArrayList<>();
        String numberToAdd="";
        for(int i=0;i<list.length();i++) {
            if (list.charAt(i) != ',') {
                numberToAdd += list.charAt(i);
            } else if (list.charAt(i) == ',' && !numberToAdd.equals("")) {
                userNumbersList.add(new Integer(numberToAdd));
                numberToAdd = "";
            }
        }
        if(!numberToAdd.equals("")){
            userNumbersList.add(new Integer(numberToAdd));
        }
    }

    private void setUserBonusNumberList(String list) {
        if(userBonusNumberList==null)
            userBonusNumberList=new ArrayList<>();
        String numberToAdd="";
        for(int i=0;i<list.length();i++){
            if(list.charAt(i)!=',') {
                numberToAdd += list.charAt(i);
            }else if(list.charAt(i)==','&&!numberToAdd.equals("")){
                userBonusNumberList.add(new Integer(numberToAdd));
                numberToAdd="";
            }
        }
        if(!numberToAdd.equals("")){
            userBonusNumberList.add(new Integer(numberToAdd));
        }
    }

    public ArrayList<Integer> getDrawNumbersList() {
        return drawNumbersList;
    }

    public ArrayList<Integer> getDrawBonusNumbersList() {
        return drawBonusNumbersList;
    }

    public ArrayList<Integer> getMathchingNumbersList() {
        return mathchingNumbersList;
    }

    public ArrayList<Integer> getMatchingBonusNumbersList() {
        return matchingBonusNumbersList;
    }

    public ArrayList<Integer> getUserNumbersList() {
        return userNumbersList;
    }

    public ArrayList<Integer> getUserBonusNumberList() {
        return userBonusNumberList;
    }

    @Override
    public String toString() {
        return "LottoResultSql{" +
                "lottoName='" + lottoName + '\'' +
                ", lottoDate='" + lottoDate + '\'' +
                ", drawNumbers='" + drawNumbers + '\'' +
                ", drawBonusNumbers='" + drawBonusNumbers + '\'' +
                ", matchingNumbers='" + matchingNumbers + '\'' +
                ", matchingBonusNumbers='" + matchingBonusNumbers + '\'' +
                ", userNumbers='" + userNumbers + '\'' +
                ", userBonusNumbers='" + userBonusNumbers + '\'' +
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
        dest.writeString(lottoDate);
        dest.writeString(drawNumbers);
        dest.writeString(drawBonusNumbers);
        dest.writeString(matchingNumbers);
        dest.writeString(matchingBonusNumbers);
        dest.writeString(userNumbers);
        dest.writeString(userBonusNumbers);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public LottoResultSql createFromParcel(Parcel in) {
            return new LottoResultSql(in);
        }

        public LottoResultSql[] newArray(int size) {
            return new LottoResultSql[size];
        }
    };

    public LottoResultSql(Parcel in){
        lottoName=in.readString();
        lottoDate=in.readString();
        drawNumbers=in.readString();
        drawBonusNumbers=in.readString();
        matchingNumbers=in.readString();
        matchingBonusNumbers=in.readString();
        userNumbers=in.readString();
        userBonusNumbers=in.readString();
    }
}
