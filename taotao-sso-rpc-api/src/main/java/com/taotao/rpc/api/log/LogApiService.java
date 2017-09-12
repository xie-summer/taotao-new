package com.taotao.rpc.api.log;

import com.taotao.core.Service;
import com.taotao.model.Log;

/**
 * 日志记录
 */
public interface LogApiService extends Service<Log> {

    /** 插入日志
     * @param log
     */
    public void insertLogSelective(Log log);
}
