package com.moutamid.elearningapp.models;

public class Model_Message {
    private String message;
    private String senderUid;
    private String receiverUid;
    private long timestamp;
    private String type;
    private String mode;
    private int unreadChatCount = 0;

    public Model_Message() {
    }

    public Model_Message(String message, String senderUid, String receiverUid, long timestamp, String type, String mode, int unreadChatCount) {
        this.message = message;
        this.senderUid = senderUid;
        this.receiverUid = receiverUid;
        this.timestamp = timestamp;
        this.type = type;
        this.mode = mode;
        this.unreadChatCount = unreadChatCount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenderUid() {
        return senderUid;
    }

    public void setSenderUid(String senderUid) {
        this.senderUid = senderUid;
    }

    public String getReceiverUid() {
        return receiverUid;
    }

    public void setReceiverUid(String receiverUid) {
        this.receiverUid = receiverUid;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public int getUnreadChatCount() {
        return unreadChatCount;
    }

    public void setUnreadChatCount(int unreadChatCount) {
        this.unreadChatCount = unreadChatCount;
    }
}
