package com.xinmintx.factory.service;


import com.xinmintx.common.json.JSON;
import com.xinmintx.factory.common.ResultCode;

import java.util.Map;

public interface NoteService {
    Map note(Map<String, String> paramMap);
}
