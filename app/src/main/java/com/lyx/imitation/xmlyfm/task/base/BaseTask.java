package com.lyx.imitation.xmlyfm.task.base;

import android.os.AsyncTask;

/**
 * Created by Administrator on 2016/11/9.
 */

public abstract class BaseTask extends AsyncTask<String, Void, TaskResult> {

    private TaskCallback taskCallback;

    public BaseTask(TaskCallback taskCallback) {
        this.taskCallback = taskCallback;
    }

    @Override
    protected void onPostExecute(TaskResult result) {
        if (taskCallback != null) {
            taskCallback.onTaskFinished(result);
        }
    }
}
