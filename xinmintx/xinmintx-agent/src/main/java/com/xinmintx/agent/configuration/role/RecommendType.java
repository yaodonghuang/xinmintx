package com.xinmintx.agent.configuration.role;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2020/1/21 0021
 * @time: 下午 21:16
 * @Description: 分润类型(1, 推荐获取分成 ; 2, 享受分成 ; 3, 上级获取分成 ; 4, 享受团队分成)
 */
public class RecommendType {
    /**
     * 直推/推荐获取分成
     */
    public static final Integer DIRECT = 1;
    /**
     * 间推/享受分成
     */
    public static final Integer INDIRECT = 2;
    /**
     * 上级获取分成/针对员工推荐商户情况
     */
    public static final Integer SUPERIOR = 3;
    /**
     * 团队产生获取/享受团队分成
     */
    public static final Integer TEAM = 4;
}
