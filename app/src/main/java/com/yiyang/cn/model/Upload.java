package com.yiyang.cn.model;

import java.util.List;

public class Upload {


    /**
     * msg_code : 0000
     * msg : ok
     * data : [{"file_url":"https://shop.hljsdkj.com/","file_path":"/alidata/server/spring_file/","file_up_type":"wit","create_time":"2019-07-19 16:00:03","file_type":"1","file_name":"1563523192766.jpg","file_load_name":"20190719160003000001.jpg","file_all_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9253","file_save":"1","file_state":"1"}]
     */

    private String msg_code;
    private String msg;
    private List<DataBean> data;

    public String getMsg_code() {
        return msg_code;
    }

    public void setMsg_code(String msg_code) {
        this.msg_code = msg_code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * file_url : https://shop.hljsdkj.com/
         * file_path : /alidata/server/spring_file/
         * file_up_type : wit
         * create_time : 2019-07-19 16:00:03
         * file_type : 1
         * file_name : 1563523192766.jpg
         * file_load_name : 20190719160003000001.jpg
         * file_all_url : https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=9253
         * file_save : 1
         * file_state : 1
         */

        private String file_url;
        private String file_path;
        private String file_up_type;
        private String create_time;
        private String file_type;
        private String file_name;
        private String file_load_name;
        private String file_all_url;
        private String file_save;
        private String file_state;

        public String getFile_url() {
            return file_url;
        }

        public void setFile_url(String file_url) {
            this.file_url = file_url;
        }

        public String getFile_path() {
            return file_path;
        }

        public void setFile_path(String file_path) {
            this.file_path = file_path;
        }

        public String getFile_up_type() {
            return file_up_type;
        }

        public void setFile_up_type(String file_up_type) {
            this.file_up_type = file_up_type;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getFile_type() {
            return file_type;
        }

        public void setFile_type(String file_type) {
            this.file_type = file_type;
        }

        public String getFile_name() {
            return file_name;
        }

        public void setFile_name(String file_name) {
            this.file_name = file_name;
        }

        public String getFile_load_name() {
            return file_load_name;
        }

        public void setFile_load_name(String file_load_name) {
            this.file_load_name = file_load_name;
        }

        public String getFile_all_url() {
            return file_all_url;
        }

        public void setFile_all_url(String file_all_url) {
            this.file_all_url = file_all_url;
        }

        public String getFile_save() {
            return file_save;
        }

        public void setFile_save(String file_save) {
            this.file_save = file_save;
        }

        public String getFile_state() {
            return file_state;
        }

        public void setFile_state(String file_state) {
            this.file_state = file_state;
        }
    }
}
