package practice.practice.DataModel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ThorneBird on 5/6/2017.
 */

public class LottoCardNumber implements Parcelable {

    private int number;
    private int position;
    private int id;

    public LottoCardNumber() {}

    public LottoCardNumber(int id, int number, int position) {
        this.number = number;
        this.position = position;
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable
     * instance's marshaled representation. For example, if the object will
     * include a file descriptor in the output of {@link #writeToParcel(Parcel, int)},
     * the return value of this method must include the
     * {@link #CONTENTS_FILE_DESCRIPTOR} bit.
     *
     * @return a bitmask indicating the set of special object types marshaled
     * by this Parcelable object instance.
     * @see #CONTENTS_FILE_DESCRIPTOR
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(number);
        dest.writeInt(id);
        dest.writeInt(position);

    }

    public static final Parcelable.Creator CREATOR=new Parcelable.Creator(){
        public LottoCardNumber createFromParcel(Parcel in) {
            return new LottoCardNumber(in);
        }

        public LottoCardNumber[] newArray(int size) {
            return new LottoCardNumber[size];
        }
    };


    public LottoCardNumber(Parcel in) {
        number=in.readInt();
        id=in.readInt();
        position=in.readInt();
    }
}
