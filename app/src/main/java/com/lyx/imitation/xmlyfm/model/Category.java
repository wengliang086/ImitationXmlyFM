package com.lyx.imitation.xmlyfm.model;

import java.util.List;

/**
 * Created by Administrator on 2016/11/10.
 */

public class Category {

    public int ret;
    public String msg;
    public List<Entry> list;

    public class Entry {
        public String title;
        public String coverPath;
    }
}
