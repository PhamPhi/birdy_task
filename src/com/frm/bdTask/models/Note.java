package com.frm.bdTask.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * @author: Larry Pham.
 * @since: 3/19/2014.
 * @version: 2014.03.19.
 */
public class Note implements Parcelable {
    private int mId;
    private String mTitle;
    private String mContent;
    private int mStatus;
    private int mColor;
    
    private Date mCreatedAt;
    private Date mUpdatedAt;

    public Note() {

    }

    public Note(int mId, String mTitle, String mContent, int color) {
        this.mId = mId;
        this.mTitle = mTitle;
        this.mContent = mContent;
        this.mColor = color;
    }

    public Note(int mId, String mTitle, String mContent, int mStatus, int inColor) {
        this.mId = mId;
        this.mTitle = mTitle;
        this.mContent = mContent;
        this.mStatus = mStatus;
        this.mColor = inColor;
    }

    public Note(int mId, String mTitle, String mContent, int mStatus, int color, Date mCreatedAt) {
        this.mId = mId;
        this.mTitle = mTitle;
        this.mContent = mContent;
        this.mStatus = mStatus;
        this.mColor = color;
        this.mCreatedAt = mCreatedAt;

    }

    public Note(int mId, String mTitle, String mContent, int mStatus, int color,
                    Date mCreatedAt, Date mUpdatedAt) {
        this.mId = mId;
        this.mTitle = mTitle;
        this.mContent = mContent;
        this.mStatus = mStatus;
        this.mColor = color;
        this.mCreatedAt = mCreatedAt;
        this.mUpdatedAt = mUpdatedAt;

    }

    public Note(Parcel in){
        this.mId = in.readInt();
        this.mTitle = in.readString();
        this.mContent = in.readString();
        this.mStatus = in.readInt();
        this.mColor = in.readInt();

        this.mCreatedAt = (Date) in.readSerializable();
        this.mUpdatedAt = (Date) in.readSerializable();
    }
    /** GETTERS AND SETTERS **/
    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String mContent) {
        this.mContent = mContent;
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int mColor) {
        this.mColor = mColor;
    }

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int mStatus) {
        this.mStatus = mStatus;
    }

    public Date getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(Date mCreatedAt) {
        this.mCreatedAt = mCreatedAt;
    }

    public Date getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(Date mUpdatedAt) {
        this.mUpdatedAt = mUpdatedAt;
    }


    @Override
    public int describeContents() {
        return 0;
    }
    /** Assining this object as the parcelable object .*/

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mTitle);
        dest.writeString(mContent);

        dest.writeInt(mStatus);
        dest.writeInt(mColor);

        dest.writeSerializable(mCreatedAt);
        dest.writeSerializable(mUpdatedAt);
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel source) {
            return new Note(source);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };


}
