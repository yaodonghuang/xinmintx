package com.xinmintx.community.model;

import java.util.Date;

/**
 *  社区消息实体类
 * @author hyd
 */
public class CommunityMessage {
    private String state;// 状态
    private Date createTime;// 消息时间
    private String pic;// 图片
    private String format;// 规格
    private String type;// 类型：1配送消息，2.投票信息，3提货点信息，4供货商信息，5帮办信息
    private String name;// 物品名称
    private String promptMessage;//提示信息
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getPromptMessage() {
        return promptMessage;
    }

    public void setPromptMessage(String promptMessage) {
        this.promptMessage = promptMessage;
    }
}
