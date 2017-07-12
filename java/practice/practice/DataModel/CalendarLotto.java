package practice.practice.DataModel;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import practice.practice.Constants.UtilConstants;

/**
 * Created by ThorneBird on 4/16/2017.
 */

public class CalendarLotto {
    private int today;
    private int drawDayOne;
    private int drawDayTwo;
    private int drawDayThree;
    private String nextDrawDay;
    private String dayToCompare;
    private Calendar calendar;
    private int currentPosition;


    public CalendarLotto(int position) {
        calendar = Calendar.getInstance();
        today = calendar.get(Calendar.DAY_OF_WEEK);
        currentPosition = position;
        getLottoDays();
    }


    private void getLottoDays() {
        if (currentPosition == UtilConstants.AUS_LOTTO) {
            drawDayOne = LotteyBaseInfo.AUSlOTTO.getDrawDayOne();
            drawDayTwo = LotteyBaseInfo.AUSlOTTO.getDrawDayTwo();
            drawDayThree = LotteyBaseInfo.AUSlOTTO.getDrawDayThree();
        } else if (currentPosition == UtilConstants.AUS_POWERBALL) {
            drawDayOne = LotteyBaseInfo.AUSPOWERBALL.getDrawDayOne();
            drawDayTwo = LotteyBaseInfo.AUSPOWERBALL.getDrawDayTwo();
            drawDayThree = LotteyBaseInfo.AUSPOWERBALL.getDrawDayThree();
        } else if (currentPosition == UtilConstants.AUS_OZ_LOTTO) {
            drawDayOne = LotteyBaseInfo.AUSOZLOTTO.getDrawDayOne();
            drawDayTwo = LotteyBaseInfo.AUSOZLOTTO.getDrawDayTwo();
            drawDayThree = LotteyBaseInfo.AUSOZLOTTO.getDrawDayThree();
        } else if (currentPosition == UtilConstants.BRITISH_LOTTO) {
            drawDayOne = LotteyBaseInfo.BRITISHLOTTO.getDrawDayOne();
            drawDayTwo = LotteyBaseInfo.BRITISHLOTTO.getDrawDayTwo();
            drawDayThree = LotteyBaseInfo.BRITISHLOTTO.getDrawDayThree();
        } else if (currentPosition == UtilConstants.CANADIAN_LOTT) {
            drawDayOne = LotteyBaseInfo.CANADIANLOTTO.getDrawDayOne();
            drawDayTwo = LotteyBaseInfo.CANADIANLOTTO.getDrawDayTwo();
            drawDayThree = LotteyBaseInfo.CANADIANLOTTO.getDrawDayThree();
        } else if (currentPosition == UtilConstants.EURO_MILLIONS) {
            drawDayOne = LotteyBaseInfo.EUROMILLIONS.getDrawDayOne();
            drawDayTwo = LotteyBaseInfo.EUROMILLIONS.getDrawDayTwo();
            drawDayThree = LotteyBaseInfo.EUROMILLIONS.getDrawDayThree();
        } else if (currentPosition == UtilConstants.EURO_JACKPOT) {
            drawDayOne = LotteyBaseInfo.EUROJACKPOT.getDrawDayOne();
            drawDayTwo = LotteyBaseInfo.EUROJACKPOT.getDrawDayTwo();
            drawDayThree = LotteyBaseInfo.EUROJACKPOT.getDrawDayThree();
        } else if (currentPosition == UtilConstants.FRENCH_LOTTO) {
            drawDayOne = LotteyBaseInfo.FRENCHLOTTO.getDrawDayOne();
            drawDayTwo = LotteyBaseInfo.FRENCHLOTTO.getDrawDayTwo();
            drawDayThree = LotteyBaseInfo.FRENCHLOTTO.getDrawDayThree();
        } else if (currentPosition == UtilConstants.GERMAN_LOTTO) {
            drawDayOne = LotteyBaseInfo.GERMANLOTTO.getDrawDayOne();
            drawDayTwo = LotteyBaseInfo.GERMANLOTTO.getDrawDayTwo();
            drawDayThree = LotteyBaseInfo.GERMANLOTTO.getDrawDayThree();
        } else if (currentPosition == UtilConstants.IRISH_LOTTO) {
            drawDayOne = LotteyBaseInfo.IRISHLOTTO.getDrawDayOne();
            drawDayTwo = LotteyBaseInfo.IRISHLOTTO.getDrawDayTwo();
            drawDayThree = LotteyBaseInfo.IRISHLOTTO.getDrawDayThree();
        } else if (currentPosition == UtilConstants.SPANISH_LOTTO) {
            drawDayOne = LotteyBaseInfo.SPANISHLOTTO.getDrawDayOne();
            drawDayTwo = LotteyBaseInfo.SPANISHLOTTO.getDrawDayTwo();
            drawDayThree = LotteyBaseInfo.SPANISHLOTTO.getDrawDayThree();
        } else if (currentPosition == UtilConstants.USA_MEGA_MILLIONS) {
            drawDayOne = LotteyBaseInfo.USAMEGAMILLIONS.getDrawDayOne();
            drawDayTwo = LotteyBaseInfo.USAMEGAMILLIONS.getDrawDayTwo();
            drawDayThree = LotteyBaseInfo.USAMEGAMILLIONS.getDrawDayThree();
        } else if (currentPosition == UtilConstants.USA_POWERBALL) {
            drawDayOne = LotteyBaseInfo.USAPOWERBALL.getDrawDayOne();
            drawDayTwo = LotteyBaseInfo.USAPOWERBALL.getDrawDayTwo();
            drawDayThree = LotteyBaseInfo.USAPOWERBALL.getDrawDayThree();
        }
        checkDays();
    }

    private void checkDays() {
        int dayCount=0;
        while (today != drawDayTwo && today != drawDayOne && today != drawDayThree) {
            dayCount++;
            if (today == 7 && today != drawDayTwo && today != drawDayOne && today != drawDayThree) {
                today = 1;
            }
            today++;
        }
        calendar.add(Calendar.DAY_OF_WEEK, dayCount);
        Date dateNextDate = calendar.getTime();

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        nextDrawDay = dateFormat.format(dateNextDate);
    }


    public String getNextDrawDay() {
        return nextDrawDay;
    }

}
