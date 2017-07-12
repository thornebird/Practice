package practice.practice.DataModel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ThorneBird on 4/29/2017.
 */

public class NumberLottoCard implements Parcelable {
    private int number;
    private boolean isSelected;
    private int id;
    private boolean isBonus;

    public NumberLottoCard() {
    }

    public NumberLottoCard(int number, boolean isSelected, int position,boolean isBonus) {
        this.isSelected = isSelected;
        this.number = number;
        this.id=position;
        this.isBonus=isBonus;
    }


    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public boolean getIsSelcted() {
        return isSelected;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setIsBonus(boolean isBonus){
        this.isBonus=isBonus;
    }

    public boolean getBonus(){ return isBonus;}

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "number: "+number+" id:"+id;
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
        dest.writeByte((byte) (isSelected ? 1 : 0));
        dest.writeInt(id);
        dest.writeByte((byte)(isBonus?1:0));
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public NumberLottoCard createFromParcel(Parcel in) {
            return new NumberLottoCard(in);
        }

        public NumberLottoCard[] newArray(int size) {
            return new NumberLottoCard[size];
        }
    };

    public NumberLottoCard(Parcel parcel) {
        number = parcel.readInt();
        isSelected = parcel.readByte() != 0;
        id = parcel.readInt();
        isBonus=parcel.readByte()!=0;
    }
}
