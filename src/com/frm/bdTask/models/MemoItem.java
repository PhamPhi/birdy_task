package com.frm.bdTask.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: Larry Pham.
 * @since: 3/19/2014.
 * @version: 2014.03.19.
 */
public class MemoItem {
    private int mId;
    private String mTitle;
    private String mContent;
    private int mMemoKind;
    private int mStatus;

    private Date mCreatedAt;
    private Date mUpdatedAt;
    private List<Todo> mTodos = new ArrayList<Todo>();
    
    public MemoItem() {

    }

    public MemoItem(int mId, String mTitle, String mContent, int mMemoKind) {
        this.mId = mId;
        this.mTitle = mTitle;
        this.mContent = mContent;
        this.mMemoKind = mMemoKind;

        if (mMemoKind == MEMO_KIND.TODO){
            mTodos = new ArrayList<Todo>();
        }
    }

    public MemoItem(int mId, String mTitle, String mContent, int mMemoKind, int mStatus) {
        this.mId = mId;
        this.mTitle = mTitle;
        this.mContent = mContent;
        this.mMemoKind = mMemoKind;
        this.mStatus = mStatus;
        if (mMemoKind == MEMO_KIND.TODO){
            mTodos = new ArrayList<Todo>();
        }
    }

    public MemoItem(int mId, String mTitle, String mContent, int mMemoKind, int mStatus, Date mCreatedAt) {
        this.mId = mId;
        this.mTitle = mTitle;
        this.mContent = mContent;
        this.mMemoKind = mMemoKind;
        this.mStatus = mStatus;
        this.mCreatedAt = mCreatedAt;
        if (mMemoKind == MEMO_KIND.TODO){
            mTodos = new ArrayList<Todo>();
        }
    }

    public MemoItem(int mId, String mTitle, String mContent, int mMemoKind, int mStatus,
                    Date mCreatedAt, Date mUpdatedAt) {
        this.mId = mId;
        this.mTitle = mTitle;
        this.mContent = mContent;
        this.mMemoKind = mMemoKind;
        this.mStatus = mStatus;
        this.mCreatedAt = mCreatedAt;
        this.mUpdatedAt = mUpdatedAt;
        if (mMemoKind == MEMO_KIND.TODO){
            mTodos = new ArrayList<Todo>();
        }
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

    public int getemoKind() {
        return mMemoKind;
    }

    public void setemoKind(int mMemoKind) {
        this.mMemoKind = mMemoKind;
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

    public List<Todo> getTodos() {
        return mTodos;
    }

    public void setTodos(List<Todo> mTodos) {
        this.mTodos = mTodos;
    }

    public class MEMO_KIND{
        public static final int UNDEFINED= 2;
        public static final int TODO=1;
        public static final int DEFAULT= 0;
    }
}
