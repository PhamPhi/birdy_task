package com.frm.bdTask.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * @author: Larry Pham.
 * @since: 3/19/2014.
 * @version: 2014.03.19.
 */
public class NoteTag implements Parcelable {
	private int mId;
	private String mTagName;
	private String mContent;
	private int mColorKind;

	private int mStatus;
	private Date mTaggedDate;

	public NoteTag() {
	}

	public NoteTag(int mId, String mTagName, String mContent) {
		this.mId = mId;
		this.mTagName = mTagName;
		this.mContent = mContent;
	}

	public NoteTag(int mId, String mTagName, String mContent, int mColorKind) {
		this.mId = mId;
		this.mTagName = mTagName;
		this.mContent = mContent;
		this.mColorKind = mColorKind;
	}

	public NoteTag(int mId, String mTagName, String mContent, int mColorKind,
			int mStatus) {
		this.mId = mId;
		this.mTagName = mTagName;
		this.mContent = mContent;
		this.mColorKind = mColorKind;
		this.mStatus = mStatus;
	}

	public NoteTag(int mId, String mTagName, String mContent, int mColorKind,
			int mStatus, Date mTaggedDate) {
		this.mId = mId;
		this.mTagName = mTagName;
		this.mContent = mContent;
		this.mColorKind = mColorKind;
		this.mStatus = mStatus;
		this.mTaggedDate = mTaggedDate;
	}

	public NoteTag(Parcel in) {
		this.mId = in.readInt();
		this.mTagName = in.readString();
		this.mContent = in.readString();
		this.mColorKind = in.readInt();

		this.mStatus = in.readInt();
		this.mTaggedDate = (Date) in.readSerializable();
	}

	public int getmId() {
		return mId;
	}

	public void setmId(int mId) {
		this.mId = mId;
	}

	public String getmTagName() {
		return mTagName;
	}

	public void setmTagName(String mTagName) {
		this.mTagName = mTagName;
	}

	public String getmContent() {
		return mContent;
	}

	public void setmContent(String mContent) {
		this.mContent = mContent;
	}

	public int getmColorKind() {
		return mColorKind;
	}

	public void setmColorKind(int mColorKind) {
		this.mColorKind = mColorKind;
	}

	public int getmStatus() {
		return mStatus;
	}

	public void setmStatus(int mStatus) {
		this.mStatus = mStatus;
	}

	public Date getmTaggedDate() {
		return mTaggedDate;
	}

	public void setmTaggedDate(Date mTaggedDate) {
		this.mTaggedDate = mTaggedDate;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.mId);
		dest.writeString(this.mTagName);
		dest.writeString(this.mContent);
		dest.writeInt(this.mColorKind);

		dest.writeInt(this.mStatus);
		dest.writeSerializable(mTaggedDate);
	}

	public static final Creator<NoteTag> CREATOR = new Creator<NoteTag>() {
		@Override
		public NoteTag createFromParcel(Parcel source) {
			return new NoteTag(source);
		}

		@Override
		public NoteTag[] newArray(int size) {
			return new NoteTag[size];
		}
	};
}
