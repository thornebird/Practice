package practice.practice.DataModel;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by ThorneBird on 6/9/2017.
 */

public class XmlParserLotto {

    private final static String KEY_TITLE = "title";
    private final static String KEY_DESCRIPTION = "description";
    private final static String KEY_CATEGEORY = "category";
    private final static String KEY_INVALID="Lottries results";
    private InputStream is;


    public XmlParserLotto(InputStream is) {
        this.is=is;
    }

    public ArrayList<LottocentreLottDraw> parseLoadedLotto() {
         ArrayList<LottocentreLottDraw>lottDraws=new ArrayList<>();
        try {
            XmlPullParserFactory xmlFactoryObject = XmlPullParserFactory.newInstance();
            XmlPullParser parser = xmlFactoryObject.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(is, null);
            String name = "";
            String description = "";
            String title = "";
            String categoryString = "";
            ArrayList<String> category = new ArrayList<>();
            LottocentreLottDraw lottDraw = new LottocentreLottDraw();
            int event = parser.getEventType();


            while (event != XmlPullParser.END_DOCUMENT) {

                if (lottDraw.getTitle() != null && lottDraw.getDescription() != null) {
                    lottDraw.setCategory(category);
                    if(!lottDraw.getTitle().equals(KEY_INVALID)) {
                        lottDraws.add(lottDraw);
                    }
                    category = new ArrayList<>();
                    lottDraw = new LottocentreLottDraw();
                }

                switch (event) {
                    case XmlPullParser.START_DOCUMENT:
                        lottDraws = new ArrayList<>();
                        break;
                    case XmlPullParser.START_TAG:
                        name = parser.getName();

                        if (name.equals(KEY_CATEGEORY)) {
                            categoryString = parser.nextText();
                            if (categoryString != null) {
                                category.add(categoryString);
                            }
                        }
                        if (name.equals(KEY_TITLE)) {
                            title = parser.nextText();
                            if (title != null) {
                                lottDraw.setTitle(title);
                            }
                        } else if (name.equals(KEY_DESCRIPTION)) {
                            description = parser.nextText();
                            if (description != null) {
                                lottDraw.setDescription(description);
                            }
                        }
                        break;
                }

                event = parser.next();
            }

            while (event != XmlPullParser.END_DOCUMENT) {
                /// run check here
            }

        } catch (XmlPullParserException e) {
            Log.e("Error", e.toString());
        } catch (IOException e) {
            Log.e("Error", e.toString());
        }
        return lottDraws;
    }



  /*  private ArrayList<LottocentreLottDraw> parseLoadedLottoAlarm(ArrayList<UserLotto>lottos){

        ArrayList<LottocentreLottDraw>lottDraws=new ArrayList<>();

        try {
            XmlPullParserFactory xmlFactoryObject = XmlPullParserFactory.newInstance();
            XmlPullParser parser = xmlFactoryObject.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(is, null);
            String name = "";
            String description = "";
            String title = "";
            String categoryString = "";
            ArrayList<String> category = new ArrayList<>();
            LottocentreLottDraw lottDraw = new LottocentreLottDraw();
            int event = parser.getEventType();


            while (event != XmlPullParser.END_DOCUMENT) {

                if (lottDraw.getTitle() != null && lottDraw.getDescription() != null) {
                    lottDraw.setCategory(category);
                    lottDraws.add(lottDraw);
                    category = new ArrayList<>();
                    lottDraw = new LottocentreLottDraw();
                }

                switch (event) {
                    case XmlPullParser.START_DOCUMENT:
                        lottDraws = new ArrayList<>();
                        break;
                    case XmlPullParser.START_TAG:
                        name = parser.getName();

                        if (name.equals(KEY_CATEGEORY)) {
                            categoryString = parser.nextText();
                            if (categoryString != null) {
                                category.add(categoryString);
                            }
                        }
                        if (name.equals(KEY_TITLE)) {
                            title = parser.nextText();
                            if (title != null) {
                                lottDraw.setTitle(title);
                            }
                        } else if (name.equals(KEY_DESCRIPTION)) {
                            description = parser.nextText();
                            if (description != null) {
                                lottDraw.setDescription(description);
                            }
                        }
                        break;
                }

                event = parser.next();
            }

            while (event != XmlPullParser.END_DOCUMENT) {
                /// run check here
            }

        } catch (XmlPullParserException e) {
            Log.e("Error", e.toString());
        } catch (IOException e) {
            Log.e("Error", e.toString());
        }




        return lottDraws;
    }
*/
}
