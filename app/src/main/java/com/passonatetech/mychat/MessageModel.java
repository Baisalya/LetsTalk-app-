package com.passonatetech.mychat;

public class MessageModel {
    private String msgId;
    public String senderId;
    private String message;

    public MessageModel(String msgId,String senderId,String message) {
        this.msgId=msgId;
        this.senderId=senderId;
        this.message=message;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageModel(){

    }
}
