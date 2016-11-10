package com.lyx.imitation.xmlyfm.model;

import java.util.List;

/**
 * Created by user on 2016/7/27.
 */
public class AncherModel {
    public List<Data> list;

    public class Data {
        public List<Message> list;
        public String title;
    }

    public class Message {
        public String nickname;
        public String smallLogo;
        public int uid;
        public String verifyTitle;
    }
}
