package practice.practice.DataModel;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThorneBird on 3/27/2017.
 */
public class LottocentreLottDraw implements Parcelable {

    private int id;
    private String title;
    private String description;
    private final static String STRING_DATE = ":";
    private final static String STRING_WINNINGNUMERS = "Winning Numbers:";
    private final static String STRING_JACKPOT = "Jackpot:";
    private final static String STRING_WINNERS = "Total Winners:";
    private String lottoDate = "";
    private String winningNumbers = "";
    private String bonusNumers = "";
    private String jackpot = "";
    private String totalWinners = "";
    private ArrayList<String> category;
    private String categoryString;
    private ArrayList<Integer> drawNumbers;
    private ArrayList<Integer> drawBonusNumbers;
    private int numbersCount = 0;
    private int numbersBonusCount = 0;

    public LottocentreLottDraw() {}

    public LottocentreLottDraw(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
        setDate(title);
    }

    public void setId(int id){
        this.id=id;
        Log.i("ID",id+"/"+title);
    }

    public int getId(){
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
        if (description.contains(STRING_WINNINGNUMERS))
            setWinningNumbers(description);
        if (description.contains(STRING_JACKPOT))
            setJackpot(description);
        if (description.contains(STRING_WINNERS))
            setWinners();

    }


    public String getDescription() {
        return description;
    }

    private void setDate(String title) {
        if (!title.contains(STRING_DATE))
            return;
        int position = title.lastIndexOf(STRING_DATE);
        //String date = "";
        for (int i = position + 1; i < title.length(); i++) {
            if (title.charAt(i) != ' ')
                lottoDate += title.charAt(i);
        }
        //  SimpleDateFormat dateFormat = new SimpleDateFormat("m/d/yyyy");
        // Date dateLotto = dateFormat.parse(date);
        //date = dateFormat.format(dateLotto);
    }

    private void setWinningNumbers(String description) {
        int position = description.indexOf(STRING_WINNINGNUMERS);
        int length = STRING_WINNINGNUMERS.length();
        position = position + length;
        int currenPosition = 0;
        for (int i = position; i < description.length(); i++) {
            currenPosition = i;
            if (description.charAt(i) == ';')
                break;

            if (description.charAt(i) != ' ')
                winningNumbers += description.charAt(i);
        }
        for (; currenPosition < description.length(); currenPosition++) {
            if (description.charAt(currenPosition) == '.')
                break;
            if (description.charAt(currenPosition) != ' ' && description.charAt(currenPosition) != ';')
                bonusNumers += description.charAt(currenPosition);
        }
        Log.i("Winning", winningNumbers);
        Log.i("Bonus", bonusNumers);
    }

    private void setWinners() {
        int position = description.indexOf(STRING_WINNERS);
        int length = STRING_WINNERS.length();
        position = position + length;
        for (int i = position; i < description.length(); i++) {
            if (description.charAt(i) == '.')
                break;
            if (description.charAt(i) != ' ')
                totalWinners += description.charAt(i);
        }
    }

    private void setJackpot(String description) {
        int position = description.indexOf(STRING_JACKPOT);
        int length = STRING_JACKPOT.length();
        position = position + length;
        for (int i = position; i < description.length(); i++) {
            if (description.charAt(i) == '.')
                break;
            if (description.charAt(i) != ' ')
                jackpot += description.charAt(i);
        }
    }

    public void setDateFromString(String date) {
        this.lottoDate = date;
    }

    public String getDate() {
        return lottoDate;
    }

    public void setWinningNumberFromString(String numbers) {
        this.winningNumbers = numbers;
        setNumbersArray();
    }


    public void setBonusNumberFromString(String bonusNumbers) {
        this.bonusNumers = bonusNumbers;
       setBonusNumbersArray();
    }


    private void setNumbersArray() {
        drawNumbers = new ArrayList<>();
        String numbers = "";
        for (int i = 0; i < winningNumbers.length(); i++) {
            if (winningNumbers.charAt(i) == '0' ||
                    winningNumbers.charAt(i) == '1' ||
                    winningNumbers.charAt(i) == '2' ||
                    winningNumbers.charAt(i) == '3' ||
                    winningNumbers.charAt(i) == '4' ||
                    winningNumbers.charAt(i) == '5' ||
                    winningNumbers.charAt(i) == '6' ||
                    winningNumbers.charAt(i) == '7' ||
                    winningNumbers.charAt(i) == '8' ||
                    winningNumbers.charAt(i) == '9') {
                numbers += winningNumbers.charAt(i);
            } else if (winningNumbers.charAt(i) == ',') {
                drawNumbers.add(Integer.parseInt(numbers));
                numbers = "";
            }
        }
        if(!numbers.equals(""))
            drawNumbers.add(Integer.parseInt(numbers));
    }

    private void setBonusNumbersArray() {
        String numbers = "";
        drawBonusNumbers = new ArrayList<>();
        for (int i = 0; i < bonusNumers.length(); i++) {
            if (bonusNumers.charAt(i) == '0' ||
                    bonusNumers.charAt(i) == '1' ||
                    bonusNumers.charAt(i) == '2' ||
                    bonusNumers.charAt(i) == '3' ||
                    bonusNumers.charAt(i) == '4' ||
                    bonusNumers.charAt(i) == '5' ||
                    bonusNumers.charAt(i) == '6' ||
                    bonusNumers.charAt(i) == '7' ||
                    bonusNumers.charAt(i) == '8' ||
                    bonusNumers.charAt(i) == '9') {
                numbers += bonusNumers.charAt(i);
            } else if (bonusNumers.charAt(i) == ',' || bonusNumers.charAt(i) == ';'
                    && bonusNumers.charAt(i) != ' ' && !numbers.equals("")) {
                drawBonusNumbers.add(Integer.parseInt(numbers));
                numbers = "";
            }
        }
        if (!numbers.equals(""))
            drawBonusNumbers.add(Integer.parseInt(numbers));
    }

    public ArrayList<Integer> getDrawNumbers() {
        return drawNumbers;
    }

    public ArrayList<Integer> getDrawBonusNumbers() {
        return drawBonusNumbers;
    }

    public String getWinningNumbers() {
        return winningNumbers;
    }

    public String getBonusNumbers() {
        return bonusNumers;
    }

    public void setJackpotFromString(String jackpot) {
        this.jackpot = jackpot;

    }

    public String getJackpot() {
        return jackpot;
    }

    public void setTotalWinnersString(String winners) {
        this.totalWinners = winners;
    }

    public String getWinners() {
        return totalWinners;
    }

    public void setCategoryFromString(String categoryFromString) {
        this.categoryString = categoryFromString;
    }

    public void setCategory(ArrayList<String> category) {
        this.category = category;
    }

    public ArrayList<String> getCategory() {
        return category;
    }

    public int getNumbersCount() {
        return numbersCount;
    }


  /*  public void setDrawNumberParcel(ArrayList<Integer>drawNumbers){
        this.drawNumbers=drawNumbers;
    }

    public void setDrawBonusParcel(ArrayList<Integer>drawBonusNumbers){
        this.drawBonusNumbers=drawBonusNumbers;
    }*/

    @Override
    public String toString() {
        return title + " " + description;
    }

    public static final Parcelable.Creator<LottocentreLottDraw> CREATOR = new Creator<LottocentreLottDraw>() {
        @Override
        public LottocentreLottDraw createFromParcel(Parcel source) {
            return new LottocentreLottDraw(source);
        }

        @Override
        public LottocentreLottDraw[] newArray(int size) {
            return new LottocentreLottDraw[size];
        }
    };

    private LottocentreLottDraw(Parcel in) {
        title = in.readString();
        description = in.readString();
        winningNumbers = in.readString();
        lottoDate = in.readString();
        jackpot = in.readString();
        totalWinners = in.readString();
        bonusNumers = in.readString();
        drawNumbers=(ArrayList<Integer>) in.readSerializable();
        drawBonusNumbers=(ArrayList<Integer>) in.readSerializable();
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(lottoDate);
        dest.writeString(winningNumbers);
        dest.writeString(bonusNumers);
        dest.writeString(jackpot);
        dest.writeString(totalWinners);
        dest.writeSerializable(drawNumbers);
        dest.writeSerializable(drawBonusNumbers);
    }
}
