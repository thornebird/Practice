package practice.practice.DataModel;

import java.util.Date;

/**
 * Created by ThorneBird on 3/22/2017.
 */
public abstract class BaseLotto {
    private String title;
    private String description;
   /* private int[] lottoNumbers;
    private int bonusNumber;*/
//    private String drawDayName;
  //  private int drawDayValue;
   // private BaseLotto baselLotto;
   // private Date drawDate;
  //  private String payout;
   // public BaseLotto(){}

    public BaseLotto(/*String title,String description*/) {
        super();
       /* this.title = title;
        this.description=description;*/

    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

  /* public int[] getLottoNumbers() {
        return lottoNumbers;
    }

    public void setLottoNumbers(int[] lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    public void setBonusNumber(int bonusNumber) {
        this.bonusNumber = bonusNumber;
    }

    public void setDayName(String dayName){
        this.drawDayName=dayName;
    }

    public String getDrawDayName(){
        return drawDayName;
    }

    public void setDrawDayValue(int drawDayValue){
        this.drawDayValue=drawDayValue;
    }

    public int getDrawDayValue(){
        return drawDayValue;
    }

    public void setDrawDate(Date drawDate){
        this.drawDate=drawDate;
    }

    public Date getDrawDate(){
        return drawDate;
    }

    public void setPayout(String payout){
        this.payout=payout;
    }

    public String getPayout(){
        return payout;
    }

    public enum DrawDays {
        SUNDAY(0,"Sunday"),
        MONDAY(1,"Monday"),
        TUESDAY(2,"Tuesday"),
        WEDNESDAY(3,"Wednesday"),
        THURSDAY(4,"Thursday"),
        FRIDAY(5,"Friday"),
        SATURDAY(6,"Saturday");

        private int drawDays;
        private String dayName;

        DrawDays(int drawDays,String dayName) {
            this.drawDays = drawDays;
            this.dayName=dayName;
        }

        public  int getDrawDay(){
            return drawDays;
        }

        public String getDayName(){
            return dayName;
        }
   }

*/

}
