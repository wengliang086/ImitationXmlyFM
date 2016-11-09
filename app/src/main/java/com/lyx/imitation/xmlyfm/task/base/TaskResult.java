package com.lyx.imitation.xmlyfm.task.base;

/**
 * Created by Administrator on 2016/11/9.
 */

public class TaskResult {
    /**
     * 异步任务唯一标识，每一个异步任务的标识都不同
     */
    public int action;
    /**
     * retsultCode 服务器返回的ret的值 0 代表成功
     */
    public int resultCode = -1;
    public Object data;
}
