package practice.practice.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import practice.practice.DataModel.UserAlarm;

/**
 * Created by ThorneBird on 6/4/2017.
 */

public class SqliteHelperAlarms extends SQLiteOpenHelper {
    private final static String DATABASE_ALARMS = "alarms.db";
    private final static int DB_VERSION = 2;

    ////
    private final static String TABLE_ALARMS = "tableAlarms";
    ///
    private final static String KEY_NAME = "lottoName";
    private final static String KEY_DATE = "lottoDate";
    private final static String KEY_TABLENAME_TICKET = "tableName";

    private final static String CREATE_TABLE = "CREATE TABLE " + TABLE_ALARMS + "(" + KEY_NAME + " TEXT," + KEY_TABLENAME_TICKET + " TEXT," + KEY_DATE + " TEXT" + ")";


    public SqliteHelperAlarms(Context context) {
        super(context, DATABASE_ALARMS, null, DB_VERSION);
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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALARMS);
        onCreate(db);
    }

    public boolean addAlarm(String tableNameTicket, String lottoName, String lottoDate) {
        boolean isAdded = false;
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, lottoName);
        contentValues.put(KEY_DATE,lottoDate); // REmoved to test & sqltusersaved
        contentValues.put(KEY_TABLENAME_TICKET, tableNameTicket);

        long result = db.insert(TABLE_ALARMS, null, contentValues);
        if (result != -1) {
            isAdded = true;
        }
        db.close();
        return isAdded;
    }

    public ArrayList<UserAlarm> getAlarmsByDate(String lottoDate) {
        ArrayList<UserAlarm> alarms = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String selection = KEY_DATE + " = ?";

        Cursor cursor = db.query(TABLE_ALARMS, new String[]{KEY_NAME, KEY_DATE, KEY_TABLENAME_TICKET},
                selection, new String[]{lottoDate}, null, null, null, null);
        while (cursor.moveToNext()) {
            UserAlarm alarm = new UserAlarm();
            alarm.setLottoName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            alarm.setLottoDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
            alarm.setTicketTableName(cursor.getString(cursor.getColumnIndex(KEY_TABLENAME_TICKET)));
            alarms.add(alarm);
        }
        cursor.close();
        db.close();
        return alarms;
    }

    public ArrayList<UserAlarm> getAllAlarms() {
        ArrayList<UserAlarm> allAlrams = new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();
        String query=" SELECT * FROM "+TABLE_ALARMS;
        Cursor cursor=db.rawQuery(query,null);

        while (cursor.moveToNext()){
            UserAlarm alarm = new UserAlarm();
            alarm.setLottoName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            alarm.setLottoDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
            alarm.setTicketTableName(cursor.getString(cursor.getColumnIndex(KEY_TABLENAME_TICKET)));
            allAlrams.add(alarm);
        }
        cursor.close();
        db.close();
        return allAlrams;
    }
}
