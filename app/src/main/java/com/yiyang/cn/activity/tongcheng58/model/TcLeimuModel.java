package com.yiyang.cn.activity.tongcheng58.model;

import java.util.List;

public class TcLeimuModel {


    /**
     * msg_code : 0000
     * msg : ok
     * row_num : 20
     * data : [{"item_id":"1807","item_name":"汽车生活","item":[{"item_id":"1808","item_name":"汽车生活"},{"item_id":"1809","item_name":"二手车大全"}]},{"item_id":"1810","item_name":"家具建材","item":[{"item_id":"1811","item_name":"家居建材"},{"item_id":"1812","item_name":"品牌家具"}]},{"item_id":"1813","item_name":"特色美食","item":[{"item_id":"1814","item_name":"美食"}]},{"item_id":"1815","item_name":"休闲","item":[{"item_id":"1816","item_name":"休闲娱乐"}]},{"item_id":"1817","item_name":"文化学校","item":[{"item_id":"1818","item_name":"文化学校"}]},{"item_id":"1819","item_name":"电子家电","item":[{"item_id":"1820","item_name":"电子家电"}]},{"item_id":"1821","item_name":"精品服饰","item":[{"item_id":"1822","item_name":"精品服饰"}]},{"item_id":"1823","item_name":"房产中介","item":[{"item_id":"1824","item_name":"房产中介"}]},{"item_id":"1825","item_name":"孕婴商店","item":[{"item_id":"1826","item_name":"孕婴商店"},{"item_id":"1827","item_name":"儿童摄影"}]},{"item_id":"1828","item_name":"婚庆礼仪","item":[{"item_id":"1829","item_name":"婚庆礼仪"}]},{"item_id":"1830","item_name":"名烟名酒","item":[{"item_id":"1831","item_name":"名烟名酒"}]},{"item_id":"1832","item_name":"生鲜超市","item":[{"item_id":"1833","item_name":"生鲜超市"}]},{"item_id":"1834","item_name":"宠物生活","item":[{"item_id":"1835","item_name":"宠物生活"}]},{"item_id":"1836","item_name":"美容美发","item":[{"item_id":"1837","item_name":"美容美发"}]},{"item_id":"1838","item_name":"农资","item":[{"item_id":"1839","item_name":"农资"}]},{"item_id":"1840","item_name":"养老院","item":[{"item_id":"1841","item_name":"养老院"}]},{"item_id":"1842","item_name":"眼镜店","item":[{"item_id":"1843","item_name":"眼镜店"}]},{"item_id":"1844","item_name":"洗衣修鞋","item":[{"item_id":"1845","item_name":"洗衣店"},{"item_id":"1846","item_name":"修鞋店"}]},{"item_id":"1847","item_name":"本地特产","item":[{"item_id":"1848","item_name":"本地特产"}]},{"item_id":"1849","item_name":"其它","item":[{"item_id":"1850","item_name":"其它"}]}]
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
         * item_id : 1807
         * item_name : 汽车生活
         * item : [{"item_id":"1808","item_name":"汽车生活"},{"item_id":"1809","item_name":"二手车大全"}]
         */

        private String item_id;
        private String item_name;
        private List<ItemBean> item;

        public String getItem_id() {
            return item_id;
        }

        public void setItem_id(String item_id) {
            this.item_id = item_id;
        }

        public String getItem_name() {
            return item_name;
        }

        public void setItem_name(String item_name) {
            this.item_name = item_name;
        }

        public List<ItemBean> getItem() {
            return item;
        }

        public void setItem(List<ItemBean> item) {
            this.item = item;
        }

        public static class ItemBean {
            /**
             * item_id : 1808
             * item_name : 汽车生活
             */

            private String item_id;
            private String item_name;

            public String getItem_id() {
                return item_id;
            }

            public void setItem_id(String item_id) {
                this.item_id = item_id;
            }

            public String getItem_name() {
                return item_name;
            }

            public void setItem_name(String item_name) {
                this.item_name = item_name;
            }
        }
    }
}
