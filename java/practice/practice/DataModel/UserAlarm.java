package practice.practice.DataModel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThorneBird on 6/4/2017.
 */

public class UserAlarm implements Parcelable {

    private String lottoName;
    private String lottoDate;
    private String ticketTableName;

    public UserAlarm() {
    }

    public String getLottoName() {
        return lottoName;
    }

    public void setLottoName(String lottoName) {
        this.lottoName = lottoName;
    }

    public String getLottoDate() {
        return lottoDate;
    }

    public void setLottoDate(String lottoDate) {
        this.lottoDate = lottoDate;
    }

    public String getTicketTableName() {
        return ticketTableName;
    }

    public void setTicketTableName(String ticketTableName) {
        this.ticketTableName = ticketTableName;
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
        dest.writeString(lottoName);
        dest.writeString(lottoDate);
        dest.writeString(ticketTableName);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public UserAlarm createFromParcel(Parcel in) {
            return new UserAlarm(in);
        }

        public UserAlarm[] newArray(int size) {
            return new UserAlarm[size];
        }
    };

    public UserAlarm(Parcel parcel) {
        lottoName = parcel.readString();
        lottoDate = parcel.readString();
        ticketTableName = parcel.readString();
    }


}
