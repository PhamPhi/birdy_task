package com.frm.bdTask.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: Larry Pham.
 * @since: 3/19/2014.
 * @version: 2014.03.19.
 */
public class Note {
    private int mId;
    private String mTitle;
    private String mContent;
    private int mStatus;

    private Date mCreatedAt;
    private Date mUpdatedAt;

    public Note() {

    }

    public Note(int mId, String mTitle, String mContent, int mMemoKind) {
        this.mId = mId;
        this.mTitle = mTitle;
        this.mContent = mContent;
    }

    public Note(int mId, String mTitle, String mContent, int mMemoKind, int mStatus) {
        this.mId = mId;
        this.mTitle = mTitle;
        this.mContent = mContent;
        this.mStatus = mStatus;
    }

    public Note(int mId, String mTitle, String mContent, int mMemoKind, int mStatus, Date mCreatedAt) {
        this.mId = mId;
        this.mTitle = mTitle;
        this.mContent = mContent;
        this.mStatus = mStatus;
        this.mCreatedAt = mCreatedAt;

    }

    public Note(int mId, String mTitle, String mContent, int mMemoKind, int mStatus,
                    Date mCreatedAt, Date mUpdatedAt) {
        this.mId = mId;
        this.mTitle = mTitle;
        this.mContent = mContent;
        this.mStatus = mStatus;
        this.mCreatedAt = mCreatedAt;
        this.mUpdatedAt = mUpdatedAt;

    }

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

}
