package practice.practice.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import practice.practice.DataModel.UserLotto;

/**
 * Created by ThorneBird on 4/19/2017.
 */

public class SqliteHelperUserTickets extends SQLiteOpenHelper {
    private final static String DATABASE_SAVED_LOTTERIES = "savedlotteries.db";
    private final static int VERSION = 2;

    public final static String TABLE_AUSOZLOTTO = "tableAusOzLotto";
    public final static String TABLE_AUSPOWERBALL="tableAusPowerball";
    public final static String TABLE_AUSLOTTO="tableAusLotto";
    public final static String TABLE_USPOWERBALL="tableUsaPowerball";
    public final static String TABLE_USEMEGAMILLIONS="tableUsaMegaMillions";
    public final static String TABLE_BRITISHLOTTO="tableBritishLotto";
    public final static String TABLE_CANADIANLOTTO="tableCanadianLotto";
    public final static String TABLE_EUROJACKPOT="tableEuroJackpot";
    public final static String TABLE_EUROMILLIONS="tableEuroMillions";
    public final static String TABLE_SPANISHLOTTO="tableSpanishLotto";
    public final static String TABLE_FRENCHLOTTO="tableFrenchLotto";
    public final static String TABLE_GERMANLOTTO="tableGermanLotto";
    public final static String TABLE_IRISHLOTTO="tableIrishLotto";

    ////////////////////  Table keys
    private final static String KEY_LOTTONAME = "lottoName";
    private final static String KEY_DATE = "date";
    private final static String KEY_LOTTONUMERS = "lottoNumbers";
    private final static String KEY_BONUSNUMBERS = "bonusNumbers";

    private final static String CREATE_TABLE_AUSLOTTO = "CREATE TABLE " + TABLE_AUSLOTTO + "(" + KEY_LOTTONAME + " TEXT," + KEY_DATE + " TEXT," + KEY_LOTTONUMERS + " TEXT," + KEY_BONUSNUMBERS + " TEXT" + ")";
    private final static String CREATE_TABLE_AUSOZLOTTO = "CREATE TABLE " + TABLE_AUSOZLOTTO + "(" + KEY_LOTTONAME + " TEXT," + KEY_DATE + " TEXT," + KEY_LOTTONUMERS + " TEXT," + KEY_BONUSNUMBERS + " TEXT" + ")";
    private final static String CREATE_TABLE_AUSPOWERBALL = "CREATE TABLE " + TABLE_AUSPOWERBALL + "(" + KEY_LOTTONAME + " TEXT," + KEY_DATE + " TEXT," + KEY_LOTTONUMERS + " TEXT," + KEY_BONUSNUMBERS + " TEXT" + ")";
    private final static String CREATE_TABLE_USPOWERBALL = "CREATE TABLE " + TABLE_USPOWERBALL + "(" + KEY_LOTTONAME + " TEXT," + KEY_DATE + " TEXT," + KEY_LOTTONUMERS + " TEXT," + KEY_BONUSNUMBERS + " TEXT" + ")";
    private final static String CREATE_TABLE_USEMEGAMILLIONS = "CREATE TABLE " + TABLE_USEMEGAMILLIONS + "(" + KEY_LOTTONAME + " TEXT," + KEY_DATE + " TEXT," + KEY_LOTTONUMERS + " TEXT," + KEY_BONUSNUMBERS + " TEXT" + ")";
    private final static String CREATE_TABLE_BRITISHLOTTO = "CREATE TABLE " + TABLE_BRITISHLOTTO + "(" + KEY_LOTTONAME + " TEXT," + KEY_DATE + " TEXT," + KEY_LOTTONUMERS + " TEXT," + KEY_BONUSNUMBERS + " TEXT" + ")";
    private final static String CREATE_TABLE_CANADIANLOTTO = "CREATE TABLE " + TABLE_CANADIANLOTTO + "(" + KEY_LOTTONAME + " TEXT," + KEY_DATE + " TEXT," + KEY_LOTTONUMERS + " TEXT," + KEY_BONUSNUMBERS + " TEXT" + ")";
    private final static String CREATE_TABLE_EUROJACKPOT = "CREATE TABLE " + TABLE_EUROJACKPOT + "(" + KEY_LOTTONAME + " TEXT," + KEY_DATE + " TEXT," + KEY_LOTTONUMERS + " TEXT," + KEY_BONUSNUMBERS + " TEXT" + ")";
    private final static String CREATE_TABLE_EUROMILLIONS = "CREATE TABLE " + TABLE_EUROMILLIONS + "(" + KEY_LOTTONAME + " TEXT," + KEY_DATE + " TEXT," + KEY_LOTTONUMERS + " TEXT," + KEY_BONUSNUMBERS + " TEXT" + ")";
    private final static String CREATE_TABLE_SPANISHLOTTO = "CREATE TABLE " + TABLE_SPANISHLOTTO + "(" + KEY_LOTTONAME + " TEXT," + KEY_DATE + " TEXT," + KEY_LOTTONUMERS + " TEXT," + KEY_BONUSNUMBERS + " TEXT" + ")";
    private final static String CREATE_TABLE_FRENCHLOTTO = "CREATE TABLE " + TABLE_FRENCHLOTTO + "(" + KEY_LOTTONAME + " TEXT," + KEY_DATE + " TEXT," + KEY_LOTTONUMERS + " TEXT," + KEY_BONUSNUMBERS + " TEXT" + ")";
    private final static String CREATE_TABLE_GERMANLOTTO = "CREATE TABLE " + TABLE_GERMANLOTTO + "(" + KEY_LOTTONAME + " TEXT," + KEY_DATE + " TEXT," + KEY_LOTTONUMERS + " TEXT," + KEY_BONUSNUMBERS + " TEXT" + ")";
    private final static String CREATE_TABLE_IRISHLOTTO = "CREATE TABLE " + TABLE_IRISHLOTTO + "(" + KEY_LOTTONAME + " TEXT," + KEY_DATE + " TEXT," + KEY_LOTTONUMERS + " TEXT," + KEY_BONUSNUMBERS + " TEXT" + ")";


    public SqliteHelperUserTickets(Context context) {
        super(context, DATABASE_SAVED_LOTTERIES, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_AUSPOWERBALL);
        db.execSQL(CREATE_TABLE_AUSLOTTO);
        db.execSQL(CREATE_TABLE_AUSOZLOTTO);
        db.execSQL(CREATE_TABLE_USPOWERBALL);
        db.execSQL(CREATE_TABLE_USEMEGAMILLIONS);
        db.execSQL(CREATE_TABLE_BRITISHLOTTO);
        db.execSQL(CREATE_TABLE_CANADIANLOTTO);
        db.execSQL(CREATE_TABLE_EUROJACKPOT);
        db.execSQL(CREATE_TABLE_EUROMILLIONS);
        db.execSQL(CREATE_TABLE_SPANISHLOTTO);
        db.execSQL(CREATE_TABLE_FRENCHLOTTO);
        db.execSQL(CREATE_TABLE_GERMANLOTTO);
        db.execSQL(CREATE_TABLE_IRISHLOTTO);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AUSOZLOTTO);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_AUSPOWERBALL);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_AUSLOTTO);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USPOWERBALL);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USEMEGAMILLIONS);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_BRITISHLOTTO);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_CANADIANLOTTO);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_EUROJACKPOT);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_EUROMILLIONS);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_SPANISHLOTTO);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_FRENCHLOTTO);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_GERMANLOTTO);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_IRISHLOTTO);
        onCreate(db);
    }

    public boolean addLottoItem(String tableName,UserLotto lotto) {
        boolean isSuccess = false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_LOTTONAME, lotto.getLottoName());
        contentValues.put(KEY_DATE, lotto.getDrawDate());
        contentValues.put(KEY_LOTTONUMERS, lotto.getLottoNumbers());
        contentValues.put(KEY_BONUSNUMBERS, lotto.getBonusNumbers());
        long rowInserted = db.insert(tableName, null, contentValues);
        if (rowInserted != -1)
            isSuccess = true;
        db.close();
        return isSuccess;
    }

    public ArrayList<UserLotto> getItemsByDate(String lottoName, String date,String tableName) {
        ArrayList<UserLotto> lottos = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        //String query = " SELECT * FROM " + TABLE_SAVEDLOTTERIES + " WHERE " + KEY_LOTTONAME + " =?  AND " + KEY_DATE + " = ?";
        String selection = KEY_LOTTONAME + " = ? AND " + KEY_DATE + " = ?";
        Cursor cursor = db.query(tableName, new String[]{KEY_LOTTONAME, KEY_DATE}, selection, new String[]{lottoName, date}, null, null, null);

        if (cursor.moveToFirst()) {
            UserLotto lotto = new UserLotto();
            lotto.setLottoName(cursor.getString(cursor.getColumnIndex(KEY_LOTTONAME)));
            lotto.setDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
            lotto.setLottoNumbers(cursor.getString(cursor.getColumnIndex(KEY_LOTTONUMERS)));
            lotto.setBonusNumbers(cursor.getString(cursor.getColumnIndex(KEY_BONUSNUMBERS)));
            lottos.add(lotto);

        }
        cursor.close();
        db.close();
        return lottos;
    }

    public boolean isItemExist(String tableName,String lottoName, String date, String lottoNumbers, String bonusNumbers) {
        boolean isExist=false;

        SQLiteDatabase db = this.getReadableDatabase();

        //String query = " SELECT * FROM " + TABLE_SAVEDLOTTERIES + " WHERE " + KEY_LOTTONAME + " =?  AND " + KEY_DATE + " = ?";
        String selection = KEY_LOTTONAME + " = ? AND " + KEY_DATE + " = ? AND "+KEY_LOTTONUMERS + " = ? AND " + KEY_BONUSNUMBERS + " = ?";
        Cursor cursor = db.query(tableName, new String[]{KEY_LOTTONAME, KEY_DATE,KEY_LOTTONUMERS,KEY_BONUSNUMBERS},
                selection, new String[]{lottoName, date, lottoNumbers, bonusNumbers}, null, null, null);

        if (cursor.moveToFirst()) {
            isExist=true;
        }
        cursor.close();
        db.close();
        return isExist;
    }

    public ArrayList<UserLotto> getAllItems(String tableName) {
        ArrayList<UserLotto> lottos = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String query = " SELECT * FROM " + tableName;

        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()) {
            UserLotto lotto = new UserLotto();
            lotto.setLottoName(cursor.getString(cursor.getColumnIndex(KEY_LOTTONAME)));
            lotto.setDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
            lotto.setLottoNumbers(cursor.getString(cursor.getColumnIndex(KEY_LOTTONUMERS)));
            lotto.setBonusNumbers(cursor.getString(cursor.getColumnIndex(KEY_BONUSNUMBERS)));
            lottos.add(lotto);
        }
        cursor.close();
        db.close();

        return lottos;
    }

    public ArrayList<UserLotto> getLottosByDate(String tableName,String date){

        ArrayList<UserLotto> lottos=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();
        String selection = KEY_DATE +" = ?";
        Cursor cursor = db.query(tableName, new String[]{KEY_LOTTONAME, KEY_DATE,KEY_LOTTONUMERS,KEY_BONUSNUMBERS},
                selection,new String []{date},null,null,null,null);

        while (cursor.moveToNext()){
            UserLotto lotto = new UserLotto();
            lotto.setLottoName(cursor.getString(cursor.getColumnIndex(KEY_LOTTONAME)));
            lotto.setDate(cursor.getString(cursor.getColumnIndex(KEY_DATE))); //Removed to test & Sqlalrms
            lotto.setLottoNumbers(cursor.getString(cursor.getColumnIndex(KEY_LOTTONUMERS)));
            lotto.setBonusNumbers(cursor.getString(cursor.getColumnIndex(KEY_BONUSNUMBERS)));
            lottos.add(lotto);
        }
        cursor.close();
        db.close();
        return lottos;
    }
}


