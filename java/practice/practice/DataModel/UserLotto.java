package practice.practice.DataModel;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
import practice.practice.DAO.SqliteHelperUserTickets;

import static java.lang.System.in;

/**
 * Created by ThorneBird on 3/24/2017.
 */
public class UserLotto implements Parcelable {

    private String lottoName;
    private String lottoNumbers;
    private String bonusNumbers;
    private String drawDayName;
    private int drawDayValue;
    private String drawDate;
    private String description;
    private  ArrayList <Integer>lottoBalls;
    private  ArrayList <Integer> bonusBalls;
    private int numbersCount;
    private int bonusNumbersCount;
    private boolean isHasBonusNumbers = true;

    public UserLotto() {
        super();
    }

    public UserLotto(String name, String lottoNumbers,
                     String bonusNumber, String drawDate,
                     String description, boolean isDefault) {
        this.lottoName = name;
        if (isDefault) {
            LottoSelectorManager manager = new LottoSelectorManager(name);
            numbersCount = manager.getNumbersCount();
            bonusNumbersCount = manager.getBonusNumbersCount();
            setDefaultBalls();
        }
    }

    public void setLottoName(String lottoName) {
        this.lottoName = lottoName;
    }

    public String getLottoName() {
        return lottoName;
    }

    public void setDate(String date) {
        this.drawDate = date;
    }

    public String getDrawDate() {
        return drawDate;
    }

    public void setLottoNumbers(String lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
        if(lottoBalls!=null)
            lottoBalls.clear();
        setLottoArray(lottoNumbers);
    }

    public String getLottoNumbers() {
        return lottoNumbers;
    }

    public void setBonusNumbers(String bonusNumbers) {
        this.bonusNumbers = bonusNumbers;
        if(bonusBalls!=null)
            bonusBalls.clear();
        setLottoBonusArray(bonusNumbers);
    }

    public String getBonusNumbers() {
        return bonusNumbers;
    }

    public int getTotalNumbersCount() {
        return numbersCount + bonusNumbersCount;
    }

    private void setDefaultBalls() {
        if (numbersCount > 0) {
            lottoBalls=new ArrayList<>();
            for (int i = 0; i < numbersCount; i++) {
                lottoBalls.add(new Integer(i));
            }
        }
        if (bonusNumbersCount > 0) {

            for (int i = 0; i < bonusNumbersCount; i++) {
                bonusBalls.add(new Integer(i));
            }
        } else if (bonusNumbersCount == 0) {
            isHasBonusNumbers = false;
        }
    }


    private void setLottoArray(String numbers){
        if(lottoBalls==null)
            lottoBalls=new ArrayList<>();
        String numberToAdd="";
        for(int i=0;i<numbers.length();i++){
            if(numbers.charAt(i)!=',') {
                numberToAdd += numbers.charAt(i);
            }else if(numbers.charAt(i)==','&&!numberToAdd.equals("")){
                lottoBalls.add(new Integer(numberToAdd));
                numberToAdd="";
            }
        }
    }

    private void setLottoBonusArray(String numbers){
        if(bonusBalls==null)
            bonusBalls=new ArrayList<>();
        String numberToAdd="";
        for(int i=0;i<numbers.length();i++){
            if(numbers.charAt(i)!=',') {
                numberToAdd += numbers.charAt(i);
            }else if(numbers.charAt(i)==','&&!numberToAdd.equals("")){
                bonusBalls.add(new Integer(numberToAdd));
                numberToAdd="";
            }
        }
    }

    public String getSqliteTicketTableName(){
            if(lottoName.equals(LotteyBaseInfo.AUSOZLOTTO.getLottoTitle())) {
                return SqliteHelperUserTickets.TABLE_AUSOZLOTTO;
            }else if(lottoName.equals(LotteyBaseInfo.AUSPOWERBALL.getLottoTitle())){
                return SqliteHelperUserTickets.TABLE_AUSPOWERBALL;
            }else if(lottoName.equals(LotteyBaseInfo.AUSlOTTO.getLottoTitle())){
                return SqliteHelperUserTickets.TABLE_AUSLOTTO;
            }else if(lottoName.equals(LotteyBaseInfo.BRITISHLOTTO.getLottoTitle())){
                return SqliteHelperUserTickets.TABLE_BRITISHLOTTO;
            }else if(lottoName.equals(LotteyBaseInfo.CANADIANLOTTO.getLottoTitle())){
                return SqliteHelperUserTickets.TABLE_CANADIANLOTTO;
            }else if(lottoName.equals(LotteyBaseInfo.EUROMILLIONS.getLottoTitle())){
                return SqliteHelperUserTickets.TABLE_EUROMILLIONS;
            }else if(lottoName.equals(LotteyBaseInfo.EUROJACKPOT.getLottoTitle())){
                return SqliteHelperUserTickets.TABLE_EUROJACKPOT;
            }else if(lottoName.equals(LotteyBaseInfo.FRENCHLOTTO.getLottoTitle())){
                return SqliteHelperUserTickets.TABLE_FRENCHLOTTO;
            }else if(lottoName.equals(LotteyBaseInfo.GERMANLOTTO.getLottoTitle())){
                return SqliteHelperUserTickets.TABLE_GERMANLOTTO;
            }else if(lottoName.equals(LotteyBaseInfo.IRISHLOTTO.getLottoTitle())){
                return SqliteHelperUserTickets.TABLE_IRISHLOTTO;
            }else if(lottoName.equals(LotteyBaseInfo.SPANISHLOTTO.getLottoTitle())){
                return SqliteHelperUserTickets.TABLE_SPANISHLOTTO;
            }else if(lottoName.equals(LotteyBaseInfo.USAMEGAMILLIONS.getLottoTitle())){
                return SqliteHelperUserTickets.TABLE_USEMEGAMILLIONS;
            }else if(lottoName.equals(LotteyBaseInfo.USAPOWERBALL.getLottoTitle())){
                return SqliteHelperUserTickets.TABLE_USPOWERBALL;
            }

            return "";

    }

    public ArrayList<Integer> getLottoBallsArray() {
        return lottoBalls;
    }

    public ArrayList<Integer> getBonusBallsArray() {
        return bonusBalls;
    }

    public int getNumbersCount() {
        return numbersCount;
    }

    public int getBonusNumbersCount() {
        return bonusNumbersCount;
    }

    public boolean isHasBonusNumbers() {
        return isHasBonusNumbers;
    }

    @Override
    public String toString() {
        return "UserLotto{" +
                "lottoName='" + lottoName + '\'' +
                ", lottoNumbers='" + lottoNumbers + '\'' +
                ", bonusNumbers='" + bonusNumbers + '\'' +
                ", drawDayName='" + drawDayName + '\'' +
                ", drawDayValue=" + drawDayValue +
                ", drawDate='" + drawDate + '\'' +
                ", description='" + description +
                ", numbersCount=" + numbersCount +
                ", bonusNumbersCount=" + bonusNumbersCount +
                ", isHasBonusNumbers=" + isHasBonusNumbers +
                '}';
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(lottoName);
        out.writeString(bonusNumbers);
        out.writeString(lottoNumbers);
        out.writeString(drawDayName);
        out.writeInt(drawDayValue);
        out.writeString(drawDate);
        out.writeString(description);
        out.writeSerializable(lottoBalls);
        out.writeSerializable(bonusBalls);
        out.writeByte((byte) (isHasBonusNumbers ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public UserLotto createFromParcel(Parcel in) {
            return new UserLotto(in);
        }

        public UserLotto[] newArray(int size) {
            return new UserLotto[size];
        }
    };

    public UserLotto(Parcel parcel) {
        lottoName = parcel.readString();
        bonusNumbers = parcel.readString();
        lottoNumbers = parcel.readString();
        drawDayName = parcel.readString();
        drawDayValue = parcel.readInt();
        drawDate = parcel.readString();
        description = parcel.readString();
        lottoBalls=(ArrayList<Integer>) parcel.readSerializable();
        bonusBalls= (ArrayList<Integer>) parcel.readSerializable();
////
        /*ArrayList<Integer> lottoBalls = null;
        parcel.readList(lottoBalls,ArrayList.class.getClassLoader());
        setLottoBalls(lottoBalls);
////
        ArrayList<Integer> bonusBalls = null;
        parcel.readList(bonusBalls,ArrayList.class.getClassLoader());
        setBonusBalls(bonusBalls);*/

        isHasBonusNumbers = parcel.readByte() != 0;
    }
}
