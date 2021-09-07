package com.yiyang.cn.model;

import java.util.List;

public class FuWuGongZhongBean {

    /**
     * msg_code : 0000
     * msg : ok
     * row_num : 30
     * data : [{"name":"家具维修","id":"1"},{"name":"沙发清洗","id":"2"},{"name":"同城快递","id":"3"},{"name":"家具拆装","id":"4"},{"name":"沙发换面","id":"5"},{"name":"搬家搬运","id":"6"},{"name":"家具贴膜","id":"7"},{"name":"开锁服务","id":"8"},{"name":"电脑维修","id":"9"},{"name":"瓷砖美缝","id":"10"},{"name":"家政保洁","id":"11"},{"name":"甲醛治理","id":"12"},{"name":"家电维修","id":"13"},{"name":"监控维护","id":"14"},{"name":"窗户维修","id":"15"},{"name":"软包房门","id":"16"},{"name":"电工服务","id":"17"},{"name":"家电清洗","id":"18"},{"name":"墙面大白","id":"19"},{"name":"玻璃定制","id":"20"},{"name":"水暖服务","id":"21"},{"name":"木工服务","id":"22"},{"name":"瓦工贴砖","id":"23"},{"name":"纱窗定制","id":"24"},{"name":"房屋改造","id":"25"},{"name":"管道疏通","id":"26"},{"name":"家具翻新","id":"27"},{"name":"车库门维修","id":"28"},{"name":"专业制冷","id":"29"},{"name":"白钢装饰","id":"30"}]
     */

    private String msg_code;
    private String msg;
    private String row_num;
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

    public String getRow_num() {
        return row_num;
    }

    public void setRow_num(String row_num) {
        this.row_num = row_num;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * name : 家具维修
         * id : 1
         */

        private String name;
        private String id;
        public String type = "0";

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
