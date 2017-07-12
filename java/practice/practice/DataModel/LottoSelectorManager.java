package practice.practice.DataModel;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.io.IOException;

import practice.practice.DAO.SqliteHelperLottoFeed;
import practice.practice.R;

/**
 * Created by ThorneBird on 4/28/2017.
 */

public class LottoSelectorManager {
    private Context context;
    private int lottoNumber;
    private int highesNumber;
    private int highestBonusNumber;
    private int numbersCount;
    private int bonusNumbersCount;
    private int smallLogo;
    private int largeLoggo;
    private String tableName;


    private String lottoName;

    public LottoSelectorManager(String lottoName) {
        this.lottoName = lottoName;
        try {
            setData();
        } catch (IOException ex) {

        }
    }



    public void reset(String newLottoName) {
        this.lottoName = newLottoName;
        try {
            setData();
        } catch (IOException ex) {

        }
    }

    private void setData() throws IOException {
        if (lottoName.equals("")) {
            throw new IOException("Requires lotto name");
        }

        if (lottoName.contains(LotteyBaseInfo.AUSOZLOTTO.getLottoTitle())) {
            highesNumber = LotteyBaseInfo.AUSOZLOTTO.getHighestNumber();
            highestBonusNumber = LotteyBaseInfo.AUSOZLOTTO.getHighestBonusNumber();
            numbersCount = LotteyBaseInfo.AUSOZLOTTO.getAmountNumbers();
            bonusNumbersCount = LotteyBaseInfo.AUSOZLOTTO.getAmountBonusNumbers();
            smallLogo = R.drawable.austraflag;
            largeLoggo=R.drawable.ozlotto;
            tableName= SqliteHelperLottoFeed.TABLE_AUSOZLOTTO;
        } else if (lottoName.contains(LotteyBaseInfo.AUSPOWERBALL.getLottoTitle())) {
            highesNumber = LotteyBaseInfo.AUSPOWERBALL.getHighestNumber();
            highestBonusNumber = LotteyBaseInfo.AUSPOWERBALL.getHighestBonusNumber();
            numbersCount = LotteyBaseInfo.AUSPOWERBALL.getAmountNumbers();
            bonusNumbersCount = LotteyBaseInfo.AUSPOWERBALL.getAmountBonusNumbers();
            smallLogo = R.drawable.austraflag;
            largeLoggo=R.drawable.auspowerball;
            tableName= SqliteHelperLottoFeed.TABLE_AUSPOWERBALL;
        } else if (lottoName.contains(LotteyBaseInfo.AUSlOTTO.getLottoTitle())) {
            highesNumber = LotteyBaseInfo.AUSlOTTO.getHighestNumber();
            highestBonusNumber = LotteyBaseInfo.AUSlOTTO.getHighestBonusNumber();
            numbersCount = LotteyBaseInfo.AUSlOTTO.getAmountNumbers();
            bonusNumbersCount = LotteyBaseInfo.AUSlOTTO.getAmountBonusNumbers();
            smallLogo = R.drawable.austraflag;
            largeLoggo = R.drawable.auslotto;
            tableName= SqliteHelperLottoFeed.TABLE_AUSLOTTO;
        } else if (lottoName.contains(LotteyBaseInfo.BRITISHLOTTO.getLottoTitle())) {
            highesNumber = LotteyBaseInfo.BRITISHLOTTO.getHighestNumber();
            highestBonusNumber = LotteyBaseInfo.BRITISHLOTTO.getHighestBonusNumber();
            numbersCount = LotteyBaseInfo.BRITISHLOTTO.getAmountNumbers();
            bonusNumbersCount = LotteyBaseInfo.BRITISHLOTTO.getAmountBonusNumbers();
            smallLogo = R.drawable.britishflag;
            largeLoggo=R.drawable.britishlotto;
            tableName= SqliteHelperLottoFeed.TABLE_BRITISHLOTTO;
        } else if (lottoName.contains(LotteyBaseInfo.CANADIANLOTTO.getLottoTitle())) {
            highesNumber = LotteyBaseInfo.CANADIANLOTTO.getHighestNumber();
            highestBonusNumber = LotteyBaseInfo.CANADIANLOTTO.getHighestBonusNumber();
            numbersCount = LotteyBaseInfo.CANADIANLOTTO.getAmountNumbers();
            bonusNumbersCount = LotteyBaseInfo.CANADIANLOTTO.getAmountBonusNumbers();
            smallLogo = R.drawable.canflag;
            largeLoggo=R.drawable.canadianlotto;
            tableName= SqliteHelperLottoFeed.TABLE_CANADIANLOTTO;
        } else if (lottoName.contains(LotteyBaseInfo.EUROMILLIONS.getLottoTitle())) {
            highesNumber = LotteyBaseInfo.EUROMILLIONS.getHighestNumber();
            highestBonusNumber = LotteyBaseInfo.EUROMILLIONS.getHighestBonusNumber();
            numbersCount = LotteyBaseInfo.EUROMILLIONS.getAmountNumbers();
            bonusNumbersCount = LotteyBaseInfo.EUROMILLIONS.getAmountBonusNumbers();
            smallLogo = R.drawable.europflag;
            largeLoggo=R.drawable.euromillions;
            tableName= SqliteHelperLottoFeed.TABLE_EUROMILLIONS;
        } else if (lottoName.contains(LotteyBaseInfo.EUROJACKPOT.getLottoTitle())) {
            highesNumber = LotteyBaseInfo.EUROJACKPOT.getHighestNumber();
            highestBonusNumber = LotteyBaseInfo.EUROJACKPOT.getHighestBonusNumber();
            numbersCount = LotteyBaseInfo.EUROJACKPOT.getAmountNumbers();
            bonusNumbersCount = LotteyBaseInfo.EUROJACKPOT.getAmountBonusNumbers();
            smallLogo = R.drawable.europflag;
            largeLoggo=R.drawable.eurojackpot;
            tableName= SqliteHelperLottoFeed.TABLE_EUROJACKPOT;
        } else if (lottoName.contains(LotteyBaseInfo.FRENCHLOTTO.getLottoTitle())) {
            highesNumber = LotteyBaseInfo.FRENCHLOTTO.getHighestNumber();
            highestBonusNumber = LotteyBaseInfo.FRENCHLOTTO.getHighestBonusNumber();
            numbersCount = LotteyBaseInfo.FRENCHLOTTO.getAmountNumbers();
            bonusNumbersCount = LotteyBaseInfo.FRENCHLOTTO.getAmountBonusNumbers();
            smallLogo = R.drawable.frenchflag;
            largeLoggo=R.drawable.frenchlotto;
            tableName= SqliteHelperLottoFeed.TABLE_FRENCHLOTTO;
        } else if (lottoName.contains(LotteyBaseInfo.GERMANLOTTO.getLottoTitle())) {
            highesNumber = LotteyBaseInfo.GERMANLOTTO.getHighestNumber();
            highestBonusNumber = LotteyBaseInfo.GERMANLOTTO.getHighestBonusNumber();
            numbersCount = LotteyBaseInfo.GERMANLOTTO.getAmountNumbers();
            bonusNumbersCount = LotteyBaseInfo.GERMANLOTTO.getAmountBonusNumbers();
            smallLogo = R.drawable.germanflag;
            largeLoggo=R.drawable.germanlotto;
            tableName= SqliteHelperLottoFeed.TABLE_GERMANLOTTO;
        } else if (lottoName.contains(LotteyBaseInfo.IRISHLOTTO.getLottoTitle())) {
            highesNumber = LotteyBaseInfo.IRISHLOTTO.getHighestNumber();
            highestBonusNumber = LotteyBaseInfo.IRISHLOTTO.getHighestBonusNumber();
            numbersCount = LotteyBaseInfo.IRISHLOTTO.getAmountNumbers();
            bonusNumbersCount = LotteyBaseInfo.IRISHLOTTO.getAmountBonusNumbers();
            smallLogo = R.drawable.irishflag;
            largeLoggo=R.drawable.irishlotto;
            tableName= SqliteHelperLottoFeed.TABLE_IRISHLOTTO;
        } else if (lottoName.contains(LotteyBaseInfo.SPANISHLOTTO.getLottoTitle())) {
            highesNumber = LotteyBaseInfo.SPANISHLOTTO.getHighestNumber();
            highestBonusNumber = LotteyBaseInfo.SPANISHLOTTO.getHighestBonusNumber();
            numbersCount = LotteyBaseInfo.SPANISHLOTTO.getAmountNumbers();
            bonusNumbersCount = LotteyBaseInfo.SPANISHLOTTO.getAmountBonusNumbers();
            smallLogo = R.drawable.spanishflag;
            largeLoggo =R.drawable.spanishlotto;
            tableName= SqliteHelperLottoFeed.TABLE_SPANISHLOTTO;
        } else if (lottoName.contains(LotteyBaseInfo.USAMEGAMILLIONS.getLottoTitle())) {
            highesNumber = LotteyBaseInfo.USAMEGAMILLIONS.getHighestNumber();
            highestBonusNumber = LotteyBaseInfo.USAMEGAMILLIONS.getHighestBonusNumber();
            numbersCount = LotteyBaseInfo.USAMEGAMILLIONS.getAmountNumbers();
            bonusNumbersCount = LotteyBaseInfo.USAMEGAMILLIONS.getAmountBonusNumbers();
            smallLogo = R.drawable.usaflag;
            largeLoggo=R.drawable.usamegamillions;
            tableName= SqliteHelperLottoFeed.TABLE_USEMEGAMILLIONS;
        } else if (lottoName.contains(LotteyBaseInfo.USAPOWERBALL.getLottoTitle())) {
            highesNumber = LotteyBaseInfo.USAPOWERBALL.getHighestNumber();
            highestBonusNumber = LotteyBaseInfo.USAPOWERBALL.getHighestBonusNumber();
            numbersCount = LotteyBaseInfo.USAPOWERBALL.getAmountNumbers();
            bonusNumbersCount = LotteyBaseInfo.USAPOWERBALL.getAmountBonusNumbers();
            smallLogo = R.drawable.usaflag;
            largeLoggo=R.drawable.usapowerball;
            tableName= SqliteHelperLottoFeed.TABLE_USPOWERBALL;
        }
    }

    public int getHighesNumber() {
        return highesNumber;
    }

    public int getHighestBonusNumber() {
        return highestBonusNumber;
    }

    public int getNumbersCount() {
        return numbersCount;
    }

    public int getBonusNumbersCount() {
        return bonusNumbersCount;
    }

    public int getTotalNumbersCount() {
        return bonusNumbersCount + numbersCount;
    }

    public int getSmallLogo() {
        return smallLogo;
    }

    public int getLargeLoggo() { return largeLoggo; }

    public String getTableName(){ return tableName; }
}
