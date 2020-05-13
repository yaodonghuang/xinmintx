package com.xinmintx.factory.scheduled;

import com.xinmintx.factory.common.ResultCode;
import com.xinmintx.factory.mapper.UserMapper;
import com.xinmintx.factory.model.User;
import com.xinmintx.factory.service.UserAccountService;
import com.xinmintx.factory.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: com.xinmintx.factory.scheduled.OpenAccount
 * @Author:Pikachu
 * @Date: 2019/12/27 9:32
 * @Version: v1.0
 */
@Component
public class OpenAccount {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private UserService userService;
    @Scheduled(cron = "59 59 23 ? * *")
    public void openAccount() {
        List<User> list = userService.query();
        if(list != null && list.size() > 0){
            for (User us:list){
                Map<String,String> paramMap = new HashMap<>();
                paramMap.put("userName",us.getName());
                paramMap.put("IdCard",us.getIdcard());
                paramMap.put("phone",us.getCellphone());
                if(StringUtils.isNotEmpty(us.getName())
                        && StringUtils.isNotEmpty(us.getIdcard())
                        && StringUtils.isNotEmpty(us.getCellphone())){
                    Map<String,String> map =  userAccountService.userAccount(paramMap);
                    if(map != null && map.size() > 0){
                        for (Map.Entry<String,String> str : map.entrySet()){
                            if ("userId".equalsIgnoreCase(str.getKey())){
                                String id =str.getValue();
                                Long userId = us.getId();
                                userMapper.upUserId(id,userId);
                            }
                        }
                    }
                }
            }
        }
   }
}
