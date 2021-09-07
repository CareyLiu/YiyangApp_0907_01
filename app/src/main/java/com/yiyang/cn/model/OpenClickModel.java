package com.yiyang.cn.model;

public class OpenClickModel {

    /**
     * n_extras : {"code":"jyj_0006","device_ccid":"12010101","device_ccid_up":"aaaaaaaaaaaaa00990140118","device_id":"1171","device_type":"12","message_id":"76396119","notify_text":"您的水利默认房间有人进入","server_id":"8/","type":"5","uri_activity":"com.yiyang.cn.activity.OpenClickActivity"}
     * n_title : 聚易佳
     * n_content : 您的水利默认房间有人进入
     * msg_id : 54043426429133771
     * show_type : 4
     * rom_type : 2
     * _j_data_ : {"data_msgtype":1,"push_type":4,"is_vip":0}
     */

    private NExtrasBean n_extras;
    private String n_title;
    private String n_content;
    private long msg_id;
    private int show_type;
    private int rom_type;
    private String _j_data_;

    public NExtrasBean getN_extras() {
        return n_extras;
    }

    public void setN_extras(NExtrasBean n_extras) {
        this.n_extras = n_extras;
    }

    public String getN_title() {
        return n_title;
    }

    public void setN_title(String n_title) {
        this.n_title = n_title;
    }

    public String getN_content() {
        return n_content;
    }

    public void setN_content(String n_content) {
        this.n_content = n_content;
    }

    public long getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(long msg_id) {
        this.msg_id = msg_id;
    }

    public int getShow_type() {
        return show_type;
    }

    public void setShow_type(int show_type) {
        this.show_type = show_type;
    }

    public int getRom_type() {
        return rom_type;
    }

    public void setRom_type(int rom_type) {
        this.rom_type = rom_type;
    }

    public String get_j_data_() {
        return _j_data_;
    }

    public void set_j_data_(String _j_data_) {
        this._j_data_ = _j_data_;
    }

    public static class NExtrasBean {
        /**
         * code : jyj_0006
         * device_ccid : 12010101
         * device_ccid_up : aaaaaaaaaaaaa00990140118
         * device_id : 1171
         * device_type : 12
         * message_id : 76396119
         * notify_text : 您的水利默认房间有人进入
         * server_id : 8/
         * type : 5
         * uri_activity : com.yiyang.cn.activity.OpenClickActivity
         */

        private String code;
        private String device_ccid;
        private String device_ccid_up;
        private String device_id;
        private String device_type;
        private String message_id;
        private String notify_text;
        private String server_id;
        private String type;
        private String uri_activity;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDevice_ccid() {
            return device_ccid;
        }

        public void setDevice_ccid(String device_ccid) {
            this.device_ccid = device_ccid;
        }

        public String getDevice_ccid_up() {
            return device_ccid_up;
        }

        public void setDevice_ccid_up(String device_ccid_up) {
            this.device_ccid_up = device_ccid_up;
        }

        public String getDevice_id() {
            return device_id;
        }

        public void setDevice_id(String device_id) {
            this.device_id = device_id;
        }

        public String getDevice_type() {
            return device_type;
        }

        public void setDevice_type(String device_type) {
            this.device_type = device_type;
        }

        public String getMessage_id() {
            return message_id;
        }

        public void setMessage_id(String message_id) {
            this.message_id = message_id;
        }

        public String getNotify_text() {
            return notify_text;
        }

        public void setNotify_text(String notify_text) {
            this.notify_text = notify_text;
        }

        public String getServer_id() {
            return server_id;
        }

        public void setServer_id(String server_id) {
            this.server_id = server_id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUri_activity() {
            return uri_activity;
        }

        public void setUri_activity(String uri_activity) {
            this.uri_activity = uri_activity;
        }
    }
}
