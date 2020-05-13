package com.xinmintx.hstx.service.impl;

import com.xinmintx.hstx.service.MemberbonusSettleService;
import com.xinmintx.hstx.util.ApplicationContextProvider;

/**
 * 奖金池结算runnable
 *
 * @author hyd
 */
public class RunnnableMemberBonus implements Runnable {
    private MemberbonusSettleService memberbonusSettleService;

    @Override
    public void run() {
        this.memberbonusSettleService = ApplicationContextProvider.getBean(MemberbonusSettleService.class);
        memberbonusSettleService.bonusSettle();
    }
}
