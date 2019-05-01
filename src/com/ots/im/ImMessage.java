package com.ots.im;

import java.sql.Timestamp;

public class ImMessage {
    private Long imMessageId;
    private Long imMessageFrom;
    private Long imMessageTarget;
    private String imMessageContent;
    private Timestamp imMessageTime;


    public Long getImMessageId() {
        return imMessageId;
    }

    public void setImMessageId(Long imMessageId) {
        this.imMessageId = imMessageId;
    }

    public Long getImMessageFrom() {
        return imMessageFrom;
    }

    public void setImMessageFrom(Long imMessageFrom) {
        this.imMessageFrom = imMessageFrom;
    }

    public Long getImMessageTarget() {
        return imMessageTarget;
    }

    public void setImMessageTarget(Long imMessageTarget) {
        this.imMessageTarget = imMessageTarget;
    }

    public String getImMessageContent() {
        return imMessageContent;
    }

    public void setImMessageContent(String imMessageContent) {
        this.imMessageContent = imMessageContent;
    }

    public Timestamp getImMessageTime() {
        return imMessageTime;
    }

    public void setImMessageTime(Timestamp imMessageTime) {
        this.imMessageTime = imMessageTime;
    }
}
