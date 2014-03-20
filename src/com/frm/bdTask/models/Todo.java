package com.frm.bdTask.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: Larry Pham.
 * @since: 3/19/2014.
 * @version: 2014.03.19.
 */
public class Todo{
    
    private int mId;
    private String mTitle;
    private String mContent;
    private int mStatus;
    private int mColorKind;

    private Date mCreatedAt;
    private Date mUpdatedAt;
    private List<TodoItem> todoItems = new ArrayList<TodoItem>();

    public Todo() {

    }

    public Todo(int mId, String mTitle, String mContent, int mStatus, Date mCreatedAt) {
        this.mId = mId;
        this.mTitle = mTitle;
        this.mContent = mContent;
        this.mStatus = mStatus;
        this.mCreatedAt = mCreatedAt;
    }

    public Todo(int mId, String mTitle, String mContent, int mStatus) {
        this.mId = mId;
        this.mTitle = mTitle;
        this.mContent = mContent;
        this.mStatus = mStatus;
    }

    public Todo(int mId, String mTitle, String mContent, int mStatus, Date mCreatedAt, Date mUpdatedAt) {
        this.mId = mId;
        this.mTitle = mTitle;
        this.mContent = mContent;
        this.mStatus = mStatus;
        this.mCreatedAt = mCreatedAt;
        this.mUpdatedAt = mUpdatedAt; 
        todoItems = new ArrayList<TodoItem>(); // Intializing the array of TodoItem. 
    }

    public Todo(int mId, String mTitle, String mContent, int mStatus, Date mCreatedAt, Date mUpdatedAt, List<TodoItem> todoItems) {
        this.mId = mId;
        this.mTitle = mTitle;
        this.mContent = mContent;
        this.mStatus = mStatus;
        this.mCreatedAt = mCreatedAt;
        this.mUpdatedAt = mUpdatedAt;
        this.todoItems = todoItems;
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

    public int getColorKind() {
        return mColorKind;
    }

    public void setColorKind(int mColorKind) {
        this.mColorKind = mColorKind;
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

    public List<TodoItem> getTodoItems() {
        return todoItems;
    }

    public void setTodoItems(List<TodoItem> todoItems) {
        this.todoItems = todoItems;
    }
}
