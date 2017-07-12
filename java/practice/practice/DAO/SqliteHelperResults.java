package practice.practice.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import practice.practice.DataModel.LottoResultCalculator;
import practice.practice.DataModel.LottoResultSql;

/**
 * Created by ThorneBird on 6/13/2017.
 */

public class SqliteHelperResults extends SQLiteOpenHelper {

    private final static String DATABASE_RESULTS = "results.db";
    private final static String TABLE_RESULTS = "tableReults";
    private final static int VERSION = 1;

    private final static String KEY_LOTTONAME = "lottoName";
    private final static String KEY_DATE = "lottoDate";
    private final static String KEY_DRAWNUMBERS = "lottoNumbers";
    private final static String KEY_DRAW_BONUSNUMBERS = "bonusNumbers";
    private final static String KEY_USER_DRAWNUMBERS = "userLottoNumbers";
    private final static String KEY_USER_DRAW_BONUSNUMBERS = "userBonusNumbers";
    private final static String KEY_MACTHING_NUMBERS = "matchingNumbers";
    private final static String KEY_MATCHING_BONUS_NUMBERS = "matchingBonusNumbers";

    private final static String CREATE_TABLE = "CREATE TABLE " + TABLE_RESULTS + "(" + KEY_LOTTONAME + " TEXT," + KEY_DATE + " TEXT," + KEY_DRAWNUMBERS + " TEXT," + KEY_DRAW_BONUSNUMBERS + " TEXT," + KEY_USER_DRAWNUMBERS + " TEXT," + KEY_USER_DRAW_BONUSNUMBERS + " TEXT," + KEY_MACTHING_NUMBERS + " TEXT," + KEY_MATCHING_BONUS_NUMBERS + " TEXT" + ")";


    public SqliteHelperResults(Context context) {
        super(context, DATABASE_RESULTS, null, VERSION);
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     * <p>
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE);
        onCreate(db);
    }

    public boolean addLottoResult(LottoResultCalculator lottoResults) {
        boolean isAdded = false;

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_LOTTONAME, lottoResults.getUserLotto().getLottoName());
        contentValues.put(KEY_DATE, lottoResults.getUserLotto().getDrawDate());
        contentValues.put(KEY_DRAWNUMBERS, lottoResults.getLottoDraw().getWinningNumbers());
        contentValues.put(KEY_DRAW_BONUSNUMBERS, lottoResults.getLottoDraw().getBonusNumbers());
        contentValues.put(KEY_MACTHING_NUMBERS, lottoResults.getNumbersString());
        contentValues.put(KEY_MATCHING_BONUS_NUMBERS, lottoResults.getBonusNumbersString());
        contentValues.put(KEY_USER_DRAWNUMBERS, lottoResults.getUserLotto().getLottoNumbers());
        contentValues.put(KEY_USER_DRAW_BONUSNUMBERS, lottoResults.getUserLotto().getBonusNumbers());

        long rowInserted = db.insert(TABLE_RESULTS, null, contentValues);
        if (rowInserted != -1)
            isAdded = true;
        db.close();

        return isAdded;
    }

    public ArrayList<LottoResultSql> getLottoResultsDate(String date) {
        ArrayList<LottoResultSql> lottoResults = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String selection = KEY_DATE + " = ?";
        Cursor cursor = db.query(TABLE_RESULTS, new String[]{KEY_LOTTONAME, KEY_DATE, KEY_DRAWNUMBERS, KEY_DRAW_BONUSNUMBERS,
                        KEY_MACTHING_NUMBERS, KEY_MATCHING_BONUS_NUMBERS, KEY_USER_DRAWNUMBERS, KEY_USER_DRAW_BONUSNUMBERS},
                selection, new String[]{date}, null, null, null, null);

        while (cursor.moveToNext()) {
            LottoResultSql lottoResultSql = new LottoResultSql();
            lottoResultSql.setLottoName(cursor.getString(cursor.getColumnIndex(KEY_LOTTONAME)));
            lottoResultSql.setLottoDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
            lottoResultSql.setDrawNumbers(cursor.getString(cursor.getColumnIndex(KEY_DRAWNUMBERS)));
            lottoResultSql.setDrawBonusNumbers(cursor.getString(cursor.getColumnIndex(KEY_DRAW_BONUSNUMBERS)));
            lottoResultSql.setMatchingNumbers(cursor.getString(cursor.getColumnIndex(KEY_MACTHING_NUMBERS)));
            lottoResultSql.setMatchingBonusNumbers(cursor.getString(cursor.getColumnIndex(KEY_MATCHING_BONUS_NUMBERS)));
            lottoResultSql.setUserNumbers(cursor.getString(cursor.getColumnIndex(KEY_USER_DRAWNUMBERS)));
            lottoResultSql.setUserBonusNumbers(cursor.getString(cursor.getColumnIndex(KEY_USER_DRAW_BONUSNUMBERS)));
            lottoResults.add(lottoResultSql);

        }
        return lottoResults;
    }

    public ArrayList<LottoResultSql> getAllLottoResults(){
        ArrayList<LottoResultSql> allResults=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();
        String query=" SELECT * FROM "+TABLE_RESULTS;

        Cursor cursor=db.rawQuery(query,null);
        while (cursor.moveToNext()){
            LottoResultSql lottoResultSql = new LottoResultSql();
            lottoResultSql.setLottoName(cursor.getString(cursor.getColumnIndex(KEY_LOTTONAME)));
            lottoResultSql.setLottoDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
            lottoResultSql.setDrawNumbers(cursor.getString(cursor.getColumnIndex(KEY_DRAWNUMBERS)));
            lottoResultSql.setDrawBonusNumbers(cursor.getString(cursor.getColumnIndex(KEY_DRAW_BONUSNUMBERS)));
            lottoResultSql.setMatchingNumbers(cursor.getString(cursor.getColumnIndex(KEY_MACTHING_NUMBERS)));
            lottoResultSql.setMatchingBonusNumbers(cursor.getString(cursor.getColumnIndex(KEY_MATCHING_BONUS_NUMBERS)));
            lottoResultSql.setUserNumbers(cursor.getString(cursor.getColumnIndex(KEY_USER_DRAWNUMBERS)));
            lottoResultSql.setUserBonusNumbers(cursor.getString(cursor.getColumnIndex(KEY_USER_DRAW_BONUSNUMBERS)));
            allResults.add(lottoResultSql);
        }
        return allResults;
    }

    public ArrayList<LottoResultSql> getLottoResultsName(String name) {
        ArrayList<LottoResultSql> lottoResults = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String selection = KEY_LOTTONAME + " = ?";
        Cursor cursor = db.query(TABLE_RESULTS, new String[]{KEY_LOTTONAME, KEY_DATE, KEY_DRAWNUMBERS, KEY_DRAW_BONUSNUMBERS,
                        KEY_MACTHING_NUMBERS, KEY_MATCHING_BONUS_NUMBERS, KEY_USER_DRAWNUMBERS, KEY_USER_DRAW_BONUSNUMBERS},
                selection, new String[]{name}, null, null, null, null);

        while (cursor.moveToNext()) {
            LottoResultSql lottoResultSql = new LottoResultSql();
            lottoResultSql.setLottoName(cursor.getString(cursor.getColumnIndex(KEY_LOTTONAME)));
            lottoResultSql.setLottoDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
            lottoResultSql.setDrawNumbers(cursor.getString(cursor.getColumnIndex(KEY_DRAWNUMBERS)));
            lottoResultSql.setDrawBonusNumbers(cursor.getString(cursor.getColumnIndex(KEY_DRAW_BONUSNUMBERS)));
            lottoResultSql.setMatchingNumbers(cursor.getString(cursor.getColumnIndex(KEY_MACTHING_NUMBERS)));
            lottoResultSql.setMatchingBonusNumbers(cursor.getString(cursor.getColumnIndex(KEY_MATCHING_BONUS_NUMBERS)));
            lottoResultSql.setUserNumbers(cursor.getString(cursor.getColumnIndex(KEY_USER_DRAWNUMBERS)));
            lottoResultSql.setUserBonusNumbers(cursor.getString(cursor.getColumnIndex(KEY_USER_DRAW_BONUSNUMBERS)));
            lottoResults.add(lottoResultSql);

        }
        return lottoResults;
    }

    public ArrayList<LottoResultSql> getLottoResults(String name,String date) {
        ArrayList<LottoResultSql> lottoResults = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String selection = KEY_LOTTONAME + " = ? AND "+KEY_DATE+" = ?";
        Cursor cursor = db.query(TABLE_RESULTS, new String[]{KEY_LOTTONAME, KEY_DATE, KEY_DRAWNUMBERS, KEY_DRAW_BONUSNUMBERS,
                        KEY_MACTHING_NUMBERS, KEY_MATCHING_BONUS_NUMBERS, KEY_USER_DRAWNUMBERS, KEY_USER_DRAW_BONUSNUMBERS},
                selection, new String[]{name,date}, null, null, null, null);

        while (cursor.moveToNext()) {
            LottoResultSql lottoResultSql = new LottoResultSql();
            lottoResultSql.setLottoName(cursor.getString(cursor.getColumnIndex(KEY_LOTTONAME)));
            lottoResultSql.setLottoDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
            lottoResultSql.setDrawNumbers(cursor.getString(cursor.getColumnIndex(KEY_DRAWNUMBERS)));
            lottoResultSql.setDrawBonusNumbers(cursor.getString(cursor.getColumnIndex(KEY_DRAW_BONUSNUMBERS)));
            lottoResultSql.setMatchingNumbers(cursor.getString(cursor.getColumnIndex(KEY_MACTHING_NUMBERS)));
            lottoResultSql.setMatchingBonusNumbers(cursor.getString(cursor.getColumnIndex(KEY_MATCHING_BONUS_NUMBERS)));
            lottoResultSql.setUserNumbers(cursor.getString(cursor.getColumnIndex(KEY_USER_DRAWNUMBERS)));
            lottoResultSql.setUserBonusNumbers(cursor.getString(cursor.getColumnIndex(KEY_USER_DRAW_BONUSNUMBERS)));
            lottoResults.add(lottoResultSql);

        }
        return lottoResults;
    }

    public LottoResultSql getResults(String name,String date){
        LottoResultSql lottoResultSql=new LottoResultSql();
        SQLiteDatabase db = getReadableDatabase();

        String selection = KEY_LOTTONAME + " = ? AND "+KEY_DATE+" = ?";

        Cursor cursor = db.query(TABLE_RESULTS, new String[]{KEY_LOTTONAME, KEY_DATE, KEY_DRAWNUMBERS, KEY_DRAW_BONUSNUMBERS,
                        KEY_MACTHING_NUMBERS, KEY_MATCHING_BONUS_NUMBERS, KEY_USER_DRAWNUMBERS, KEY_USER_DRAW_BONUSNUMBERS},
                selection, new String[]{name,date}, null, null, null, null);

        if(cursor.moveToNext()){
            lottoResultSql.setLottoName(cursor.getString(cursor.getColumnIndex(KEY_LOTTONAME)));
            lottoResultSql.setLottoDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
            lottoResultSql.setDrawNumbers(cursor.getString(cursor.getColumnIndex(KEY_DRAWNUMBERS)));
            lottoResultSql.setDrawBonusNumbers(cursor.getString(cursor.getColumnIndex(KEY_DRAW_BONUSNUMBERS)));
            lottoResultSql.setMatchingNumbers(cursor.getString(cursor.getColumnIndex(KEY_MACTHING_NUMBERS)));
            lottoResultSql.setMatchingBonusNumbers(cursor.getString(cursor.getColumnIndex(KEY_MATCHING_BONUS_NUMBERS)));
            lottoResultSql.setUserNumbers(cursor.getString(cursor.getColumnIndex(KEY_USER_DRAWNUMBERS)));
            lottoResultSql.setUserBonusNumbers(cursor.getString(cursor.getColumnIndex(KEY_USER_DRAW_BONUSNUMBERS)));
        }

        return lottoResultSql;
    }
    public boolean isResultExist(String name,String date) {
        boolean isExist = false;
        SQLiteDatabase db = getReadableDatabase();
       /* String selectString = "SELECT * FROM " + TABLE_RESULTS + " WHERE " + KEY_LOTTONAME + " =?";
        Cursor cursor = db.rawQuery(selectString, new String[]{title});*/

        String selection = KEY_LOTTONAME + " = ? AND "+KEY_DATE+" = ?";

        Cursor cursor = db.query(TABLE_RESULTS, new String[]{KEY_LOTTONAME, KEY_DATE, KEY_DRAWNUMBERS, KEY_DRAW_BONUSNUMBERS,
                        KEY_MACTHING_NUMBERS, KEY_MATCHING_BONUS_NUMBERS, KEY_USER_DRAWNUMBERS, KEY_USER_DRAW_BONUSNUMBERS},
                selection, new String[]{name,date}, null, null, null, null);

        if (cursor.moveToFirst()) {
            isExist = true;
        }
        cursor.close();
        db.close();

        return isExist;
    }
}
