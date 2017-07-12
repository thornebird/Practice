package practice.practice;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;

import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import java.util.ArrayList;

import practice.practice.Activities.LottoTicketActivity;
import practice.practice.DataModel.LottocentreLottDraw;

/**
 * Created by ThorneBird on 4/1/2017.
 */

public class MyApplication extends android.app.Application {



    private ArrayList<LottocentreLottDraw> ausOzLottos;
    private ArrayList<LottocentreLottDraw> usaPowerBall;
    private ArrayList<LottocentreLottDraw> ausPowerBall;
    private ArrayList<LottocentreLottDraw> ausLotto;
    private ArrayList<LottocentreLottDraw> britishLotto;
    private ArrayList<LottocentreLottDraw> canadianLotto;
    private ArrayList<LottocentreLottDraw> usaMegaMillions;
    private ArrayList<LottocentreLottDraw> euroMillions;
    private ArrayList<LottocentreLottDraw> euroJackpot;
    private ArrayList<LottocentreLottDraw> spanishLotto;
    private ArrayList<LottocentreLottDraw> frenchLotto;
    private ArrayList<LottocentreLottDraw> germanLotto;
    private ArrayList<LottocentreLottDraw> irishLotto;



    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

    }




    public ArrayList<LottocentreLottDraw> getAusOzLottos() {
        return ausOzLottos;
    }

    public void setAusOzLottos(ArrayList<LottocentreLottDraw> ausOzLottos) {
        this.ausOzLottos = ausOzLottos;
    }

    public ArrayList<LottocentreLottDraw> getUsaPowerBall() {
        return usaPowerBall;
    }

    public void setUsaPowerBall(ArrayList<LottocentreLottDraw> usaPowerBall) {
        this.usaPowerBall = usaPowerBall;
    }

    public ArrayList<LottocentreLottDraw> getAusPowerBall() {
        return ausPowerBall;
    }

    public void setAusPowerBall(ArrayList<LottocentreLottDraw> ausPowerBall) {
        this.ausPowerBall = ausPowerBall;
    }

    public ArrayList<LottocentreLottDraw> getAusLotto() {
        return ausLotto;
    }

    public void setAusLotto(ArrayList<LottocentreLottDraw> ausLotto) {
        this.ausLotto = ausLotto;
    }

    public ArrayList<LottocentreLottDraw> getBritishLotto() {
        return britishLotto;
    }

    public void setBritishLotto(ArrayList<LottocentreLottDraw> britishLotto) {
        this.britishLotto = britishLotto;
    }

    public ArrayList<LottocentreLottDraw> getCanadianLotto() {
        return canadianLotto;
    }

    public void setCanadianLotto(ArrayList<LottocentreLottDraw> canadianLotto) {
        this.canadianLotto = canadianLotto;
    }

    public ArrayList<LottocentreLottDraw> getUsaMegaMillions() {
        return usaMegaMillions;
    }

    public void setUsaMegaMillions(ArrayList<LottocentreLottDraw> usaMegaMillions) {
        this.usaMegaMillions = usaMegaMillions;
    }

    public ArrayList<LottocentreLottDraw> getEuroMillions() {
        return euroMillions;
    }

    public void setEuroMillions(ArrayList<LottocentreLottDraw> euroMillions) {
        this.euroMillions = euroMillions;
    }

    public ArrayList<LottocentreLottDraw> getEuroJackpot() {
        return euroJackpot;
    }

    public void setEuroJackpot(ArrayList<LottocentreLottDraw> euroJackpot) {
        this.euroJackpot = euroJackpot;
    }

    public ArrayList<LottocentreLottDraw> getSpanishLotto() {
        return spanishLotto;
    }

    public void setSpanishLotto(ArrayList<LottocentreLottDraw> spanishLotto) {
        this.spanishLotto = spanishLotto;
    }

    public ArrayList<LottocentreLottDraw> getFrenchLotto() {
        return frenchLotto;
    }

    public void setFrenchLotto(ArrayList<LottocentreLottDraw> frenchLotto) {
        this.frenchLotto = frenchLotto;
    }

    public ArrayList<LottocentreLottDraw> getGermanLotto() {
        return germanLotto;
    }

    public void setGermanLotto(ArrayList<LottocentreLottDraw> germanLotto) {
        this.germanLotto = germanLotto;
    }

    public ArrayList<LottocentreLottDraw> getIrishLotto() {
        return irishLotto;
    }

    public void setIrishLotto(ArrayList<LottocentreLottDraw> irishLotto) {
        this.irishLotto = irishLotto;
    }
}
