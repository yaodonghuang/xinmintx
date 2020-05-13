package com.xinmintx.web.controller.tool;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author hyd
 */
@Data
@Component
public class UrlConfig {

    /**
     *  请求结束任务定时器url
     */
    @Value("${hstx.endTaskUrl}")
    private String endTaskUrl;
    @Value("${hstx.startTaskUrl}")
    private String startTaskUrl;
    @Value("${hstx.changeTaskUrl}")
    private String changeTaskUrl;

    public String getEndTaskUrl() {
        return endTaskUrl;
    }

    public void setEndTaskUrl(String endTaskUrl) {
        this.endTaskUrl = endTaskUrl;
    }
}
