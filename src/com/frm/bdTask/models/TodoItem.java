package com.frm.bdTask.models;

/**
 * @author: Larry Pham.
 * @since: 3/19/2014.
 * @version: 2014.03.19.
 */
public class TodoItem {

	private int mId;
	private String mContent;
	private int mStatus;
	private String created_at;

	public TodoItem() {

	}

	public TodoItem(String inContent, int status) {
		this.mContent = inContent;
		this.mStatus = status;
	}

	public TodoItem(int mId, String mContent, int inStatus) {
		this.mId = mId;
		this.mContent = mContent;
		this.mStatus = inStatus;
	}

	public TodoItem(int mId, String mContent, int mStatus, String created_at) {
		this.mId = mId;
		this.mContent = mContent;
		this.mStatus = mStatus;
		this.created_at = created_at;
	}

	public int getStatus() {
		return mStatus;
	}

	public void setStatus(int inStatus) {
		this.mStatus = inStatus;
	}

	public int getId() {
		return mId;
	}

	public void setId(int mId) {
		this.mId = mId;
	}

	public String getContent() {
		return mContent;
	}

	public void setContent(String mContent) {
		this.mContent = mContent;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
}
