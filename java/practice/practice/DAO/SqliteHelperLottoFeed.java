package practice.practice.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import practice.practice.DataModel.LottocentreLottDraw;

/**
 * Created by ThorneBird on 3/16/2017.
 */
public class SqliteHelperLottoFeed extends SQLiteOpenHelper {

    private final static String DATABASE_LOTTERIES = "lotteries.db";
    /// Tables
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

   // Table keys
    private final static int VERSION = 3;
    private static final String KEY_ID="_id";
    private static final String KEY_LOTTO_DESCRIPTION = "lottoDescription";
    private static final String KEY_LOTTO_TITLE = "lottoTitle";
    private static final String KEY_LOTTONUMBERS="lottoNumbers";
    private static final String KEY_BONUSNUMBERS="bonusNumbers";
    private static final String KEY_DATE="lottoDate";
    private static final String KEY_CATEGORY="category";

    // Create tables
    private final static String CREATE_TABLE_AUSOZLOTTO = "CREATE TABLE " + TABLE_AUSOZLOTTO + "(" +KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +KEY_LOTTO_TITLE + " TEXT,"+KEY_DATE+ " TEXT,"+KEY_LOTTONUMBERS+ " TEXT,"+KEY_BONUSNUMBERS+ " TEXT,"+KEY_CATEGORY+" TEXT," + KEY_LOTTO_DESCRIPTION + " TEXT" + ")";
    private final static String CREATE_TABLE_AUSPOWERBALL = "CREATE TABLE " + TABLE_AUSPOWERBALL + "(" +KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +KEY_LOTTO_TITLE + " TEXT," +KEY_DATE+ " TEXT,"+KEY_LOTTONUMBERS+ " TEXT,"+KEY_BONUSNUMBERS+ " TEXT,"+KEY_CATEGORY+" TEXT,"+ KEY_LOTTO_DESCRIPTION + " TEXT" + ")";
    private final static String CREATE_TABLE_AUSLOTTO = "CREATE TABLE " + TABLE_AUSLOTTO + "(" +KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +KEY_LOTTO_TITLE + " TEXT,"+KEY_DATE+ " TEXT,"+KEY_LOTTONUMBERS+ " TEXT,"+KEY_BONUSNUMBERS+ " TEXT,"+KEY_CATEGORY+" TEXT," + KEY_LOTTO_DESCRIPTION + " TEXT" + ")";
    private final static String CREATE_TABLE_USPOWERBALL = "CREATE TABLE " + TABLE_USPOWERBALL + "(" +KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +KEY_LOTTO_TITLE + " TEXT,"+KEY_DATE+ " TEXT,"+KEY_LOTTONUMBERS+ " TEXT,"+KEY_BONUSNUMBERS+ " TEXT,"+KEY_CATEGORY+" TEXT," + KEY_LOTTO_DESCRIPTION + " TEXT" + ")";
    private final static String CREATE_TABLE_USEMEGAMILLIONS = "CREATE TABLE " + TABLE_USEMEGAMILLIONS + "(" +KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +KEY_LOTTO_TITLE + " TEXT,"+KEY_DATE+ " TEXT,"+KEY_LOTTONUMBERS+ " TEXT,"+KEY_BONUSNUMBERS+ " TEXT,"+KEY_CATEGORY+" TEXT," + KEY_LOTTO_DESCRIPTION + " TEXT" + ")";
    private final static String CREATE_TABLE_BRITISHLOTTO = "CREATE TABLE " + TABLE_BRITISHLOTTO + "(" +KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +KEY_LOTTO_TITLE + " TEXT,"+KEY_DATE+ " TEXT,"+KEY_LOTTONUMBERS+ " TEXT,"+KEY_BONUSNUMBERS+ " TEXT,"+KEY_CATEGORY+" TEXT," + KEY_LOTTO_DESCRIPTION + " TEXT" + ")";
    private final static String CREATE_TABLE_CANADIANLOTTO = "CREATE TABLE " + TABLE_CANADIANLOTTO + "(" +KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +KEY_LOTTO_TITLE + " TEXT," +KEY_DATE+ " TEXT,"+KEY_LOTTONUMBERS+ " TEXT,"+KEY_BONUSNUMBERS+ " TEXT,"+KEY_CATEGORY+" TEXT,"+ KEY_LOTTO_DESCRIPTION + " TEXT" + ")";
    private final static String CREATE_TABLE_EUROJACKPOT = "CREATE TABLE " + TABLE_EUROJACKPOT + "(" +KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +KEY_LOTTO_TITLE + " TEXT," +KEY_DATE+ " TEXT,"+KEY_LOTTONUMBERS+ " TEXT,"+KEY_BONUSNUMBERS+ " TEXT,"+KEY_CATEGORY+" TEXT,"+ KEY_LOTTO_DESCRIPTION + " TEXT" + ")";
    private final static String CREATE_TABLE_EUROMILLIONS = "CREATE TABLE " + TABLE_EUROMILLIONS + "(" +KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +KEY_LOTTO_TITLE + " TEXT,"+KEY_DATE+ " TEXT,"+KEY_LOTTONUMBERS+ " TEXT,"+KEY_BONUSNUMBERS+ " TEXT,"+KEY_CATEGORY+" TEXT," + KEY_LOTTO_DESCRIPTION + " TEXT" + ")";
    private final static String CREATE_TABLE_SPANISHLOTTO = "CREATE TABLE " + TABLE_SPANISHLOTTO + "(" +KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +KEY_LOTTO_TITLE + " TEXT,"+KEY_DATE+ " TEXT,"+KEY_LOTTONUMBERS+ " TEXT,"+KEY_BONUSNUMBERS+ " TEXT,"+KEY_CATEGORY+" TEXT," + KEY_LOTTO_DESCRIPTION + " TEXT" + ")";
    private final static String CREATE_TABLE_FRENCHLOTTO = "CREATE TABLE " + TABLE_FRENCHLOTTO + "(" +KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +KEY_LOTTO_TITLE + " TEXT,"+KEY_DATE+ " TEXT,"+KEY_LOTTONUMBERS+ " TEXT,"+KEY_BONUSNUMBERS+ " TEXT,"+KEY_CATEGORY+" TEXT," + KEY_LOTTO_DESCRIPTION + " TEXT" + ")";
    private final static String CREATE_TABLE_GERMANLOTTO = "CREATE TABLE " + TABLE_GERMANLOTTO + "(" +KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +KEY_LOTTO_TITLE + " TEXT,"+KEY_DATE+ " TEXT,"+KEY_LOTTONUMBERS+ " TEXT,"+KEY_BONUSNUMBERS+ " TEXT,"+KEY_CATEGORY+" TEXT," + KEY_LOTTO_DESCRIPTION + " TEXT" + ")";
    private final static String CREATE_TABLE_IRISHLOTTO = "CREATE TABLE " + TABLE_IRISHLOTTO + "(" +KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +KEY_LOTTO_TITLE + " TEXT,"+KEY_DATE+ " TEXT,"+KEY_LOTTONUMBERS+ " TEXT,"+KEY_BONUSNUMBERS+ " TEXT,"+KEY_CATEGORY+" TEXT," + KEY_LOTTO_DESCRIPTION + " TEXT" + ")";


    public SqliteHelperLottoFeed(Context context) {
        super(context, DATABASE_LOTTERIES, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_AUSOZLOTTO);
        db.execSQL(CREATE_TABLE_AUSPOWERBALL);
        db.execSQL(CREATE_TABLE_AUSLOTTO);
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


    public void addItemLotto(LottocentreLottDraw lottocentreLottDraw, String tableName) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_LOTTO_DESCRIPTION, lottocentreLottDraw.getDescription());
        cv.put(KEY_LOTTO_TITLE, lottocentreLottDraw.getTitle());
        cv.put(KEY_DATE,lottocentreLottDraw.getDate());
        cv.put(KEY_LOTTONUMBERS,lottocentreLottDraw.getWinningNumbers());
        cv.put(KEY_CATEGORY,lottocentreLottDraw.getCategory().toString());
        cv.put(KEY_BONUSNUMBERS,lottocentreLottDraw.getBonusNumbers());
        db.insert(tableName, null, cv);
        db.close();
    }

    public ArrayList<LottocentreLottDraw> getItemsLotto(String tableName) {
        ArrayList<LottocentreLottDraw> lottoItems = new ArrayList<>();
        String query = " SELECT * FROM " + tableName;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            LottocentreLottDraw item = new LottocentreLottDraw();

            item.setTitle(cursor.getString(cursor.getColumnIndex(KEY_LOTTO_TITLE)));
            item.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            item.setDescription(cursor.getString(cursor.getColumnIndex(KEY_LOTTO_DESCRIPTION)));
            item.setWinningNumberFromString(cursor.getString(cursor.getColumnIndex(KEY_LOTTONUMBERS)));
            item.setBonusNumberFromString(cursor.getString(cursor.getColumnIndex(KEY_BONUSNUMBERS)));
            item.setDateFromString(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
            item.setCategoryFromString(cursor.getString(cursor.getColumnIndex(KEY_CATEGORY)));
            lottoItems.add(item);
        }
        cursor.close();
        db.close();
        return lottoItems;
    }

    public LottocentreLottDraw getLottoItem(String tableName,String title){
        LottocentreLottDraw item=new LottocentreLottDraw();
        SQLiteDatabase db=getReadableDatabase();
        String selectString = "SELECT * FROM " +tableName + " WHERE " +KEY_LOTTO_TITLE+ " =?";

        Cursor cursor=db.rawQuery(selectString,new String[] {title});
        if(cursor.moveToNext()){
            item.setTitle(cursor.getString(cursor.getColumnIndex(KEY_LOTTO_TITLE)));
            item.setDescription(cursor.getString(cursor.getColumnIndex(KEY_LOTTO_DESCRIPTION)));
            item.setWinningNumberFromString(cursor.getString(cursor.getColumnIndex(KEY_LOTTONUMBERS)));
            item.setBonusNumberFromString(cursor.getString(cursor.getColumnIndex(KEY_BONUSNUMBERS)));
            item.setDateFromString(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
            item.setCategoryFromString(cursor.getString(cursor.getColumnIndex(KEY_CATEGORY)));
        }
        cursor.close();
        db.close();

        return  item;
    }


    public boolean isLottoExist(String tableName, String title){
        boolean isExist=false;
        SQLiteDatabase db=getReadableDatabase();
        String selectString = "SELECT * FROM " +tableName + " WHERE " +KEY_LOTTO_TITLE+ " =?";
        Cursor cursor=db.rawQuery(selectString,new String[] {title});
        if(cursor.moveToFirst()){
            isExist=true;
        }
        cursor.close();
        db.close();

        return isExist;
    }


}
