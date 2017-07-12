package practice.practice.DataModel;

/**
 * Created by ThorneBird on 4/11/2017.
 */

public enum LotteyBaseInfo {

    AUSOZLOTTO("AUSTRALIAN OZ LOTTO", 3, 0, 0, 7, 2, 9,45,45),// maximum number:45, (user picky only 7, 2 bonus are supplemenetary)
    AUSPOWERBALL("AUSTRALIAN POWERBALL", 5, 0, 0, 6, 1, 7,40,20),// maximum number:40, bonus 20
    AUSlOTTO("AUSTRALIAN LOTTO", 7, 0, 0, 6, 2, 8,45,45),// maximum number :45, (user picky only 6, 2 bonus are supplemenetary)
    BRITISHLOTTO("British Lotto", 4, 7, 0, 6, 1, 7,59,59),// maximum number: 59, (user picky only 6, 1 bonus are supplemenetary)
    CANADIANLOTTO("CANADIAN LOTTO 6/49", 4, 7, 0, 6, 1, 7,49,49),// maximum number: 49 (user picky only 6, 1 bonus are supplemenetary)
    EUROMILLIONS("EuroMillions", 3, 6, 0, 5, 2, 7,50,50),// maximum number: 50, bonus 1-12
    EUROJACKPOT("Eurojackpot", 6, 0, 0, 5, 2, 7,50,10),// nmaximum number: 50,10
    FRENCHLOTTO("French Lotto", 2, 7, 4, 5, 1, 6,49,10),// maximum number: 49,10
    GERMANLOTTO("German Lotto", 4, 7, 0, 6, 1, 7,49,9),// maximum number:   49,9
    IRISHLOTTO("Irish Lotto", 7, 3, 0, 6, 1, 7,47,47),// maximum number: 47,47
    SPANISHLOTTO("Spanish Lotto", 5, 7, 0, 6, 2, 8,49,9),// maximum number: 49,9
    USAMEGAMILLIONS("US MEGA MILLIONS", 3, 6, 0, 5, 1, 6,75,14),//maximum number: 75,15
    USAPOWERBALL("US POWERBALL", 4, 7, 0, 5, 1, 6,69,26);//maximum number: 69,26


    private String lottoTitle;
    private int drawDayOne;
    private int drawDayTwo;
    private int drawDayThree;
    private int amountNumbers;
    private int amountBonusNumbers;
    private int numberCount;
    private int highestNumber;
    private int highestBonusNumber;

    private LotteyBaseInfo(String lottoTitle, int drawDayOne, int drawDayTwo,
                           int drawDayThree, int amountNumbers, int amountBonusNumbers,
                           int numbercount,int highestNumber,int highestBonusNumber) {
        this.lottoTitle = lottoTitle;
        this.drawDayOne = drawDayOne;
        this.drawDayTwo = drawDayTwo;
        this.drawDayThree = drawDayThree;
        this.amountBonusNumbers = amountBonusNumbers;
        this.amountNumbers = amountNumbers;
        this.numberCount = numbercount;
        this.highestNumber=highestNumber;
        this.highestBonusNumber=highestBonusNumber;
    }


    public String getLottoTitle() {
        return lottoTitle;
    }


    public int getDrawDayOne() {
        return drawDayOne;
    }

    public int getDrawDayTwo() {
        return drawDayTwo;
    }

    public int getDrawDayThree() { return drawDayThree;
    }

    public int getHighestNumber(){ return highestNumber;}

    public int getHighestBonusNumber(){return highestBonusNumber;}


    public int getAmountNumbers() {
        return amountNumbers;
    }

    public int getAmountBonusNumbers() {
        return amountBonusNumbers;
    }

    public int getNumberCount() {
        return numberCount;
    }
}
