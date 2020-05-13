package com.xinmintx.merchant.model;

import java.util.Date;

/**
 * @ClassName: com.xinmintx.merchant.model.MerchantGoodsPic
 * @Author:Pikachu
 * @Date: 2020/2/13 17:42
 * @Version: v1.0
 */

public class MerchantGoodsPic {
    private Long id;
    private Long mgoodsId;
    private Date createTime;
    private String pic;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMgoodsId() {
        return mgoodsId;
    }

    public void setMgoodsId(Long mgoodsId) {
        this.mgoodsId = mgoodsId;
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
}
