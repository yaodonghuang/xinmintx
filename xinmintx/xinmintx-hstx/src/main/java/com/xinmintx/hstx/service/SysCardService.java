package com.xinmintx.hstx.service;


import com.xinmintx.hstx.pojo.po.SysCard;

import java.util.List;

public interface SysCardService {
    List<SysCard> findCharactersByDate();

    SysCard findSysCardById (int sysCardId);
}
