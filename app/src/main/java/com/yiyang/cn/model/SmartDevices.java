package com.yiyang.cn.model;

import java.util.List;

/**
 * Created by Sl on 2019/6/14.
 */

public class SmartDevices {

    /**
     * msg_code : 0000
     * msg : ok
     * row_num : 1
     * data : [{"deviceList":[{"item_name":"摄像机","item_id":"1029","img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=8198","device":[{"user_device_id":"55","device_title":"神灯智能摄像机","device_state_text":"未连接","device_state":"1","img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=8403","img_detail_url":"http://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=7280","item_detail":"长按重置按钮，保持3秒左右，至指示灯变为黄色，即重置成功","device_id_three":"1550"},{"user_device_id":"60","device_title":"智能摄像机","device_state_text":"未连接","device_state":"1","img_url":"https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=5560","img_detail_url":"http://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=7281","item_detail":"长按重置按钮，保持3秒左右，至指示灯变为黄色，即重置成功","device_id_three":"1034"},{"user_device_id":"61","device_title":"神灯智能摄像机","device_state_text":"未连接","device_state":"1","img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=8403","img_detail_url":"http://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=7280","item_detail":"长按重置按钮，保持3秒左右，至指示灯变为黄色，即重置成功","device_id_three":"1550"}]},{"item_name":"电源开关","item_id":"1037","img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=8199","device":[]},{"item_name":"照明","item_id":"1260","img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=8200","device":[]},{"item_name":"家居安防","item_id":"1262","img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=8201","device":[]},{"item_name":"环境电器","item_id":"1270","img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=8202","device":[]},{"item_name":"传感器","item_id":"1281","img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=8203","device":[]},{"item_name":"娱乐影音","item_id":"1287","img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=8402","device":[]},{"item_name":"生活电器","item_id":"1288","img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=8363","device":[{"user_device_id":"54","device_title":"智能坐便盖","device_state_text":"未连接","device_state":"1","img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=8458","img_detail_url":"http://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=7392","item_detail":"手机手机初次连接或连接不上，请按下模式键与灯光键5秒，听到滴滴滴提示音后，WiFi重置成功。","device_id_three":"1606"}]}],"carBoxList":[{"car_brand_name":"阿斯顿·马丁Rapide","gps_x":"45.666027","gaode_y":"126.611563","available_check":"1","gps_y":"126.605662","car_brand_url":"https://shop.hljsdkj.com/commons/images/siye_autoparts/brand/130131578038733348.jpg","lock_detail":"-","baidu_y":"126.617941","baidu_x":"45.6742745","server_id":"5/","user_car_id":"27","zhu_apc":"065","ccid":"aaaaaaaaaaaaaaaaaaaaaa05","plate_number":"黑E6666654","gaode_x":"45.6679638","gps_addr":"哈尔滨市南岗区电缆街170"}]}]
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
        private List<DeviceListBean> deviceList;
        private List<CarBoxListBean> carBoxList;

        public List<DeviceListBean> getDeviceList() {
            return deviceList;
        }

        public void setDeviceList(List<DeviceListBean> deviceList) {
            this.deviceList = deviceList;
        }

        public List<CarBoxListBean> getCarBoxList() {
            return carBoxList;
        }

        public void setCarBoxList(List<CarBoxListBean> carBoxList) {
            this.carBoxList = carBoxList;
        }

        public static class DeviceListBean {
            /**
             * item_name : 摄像机
             * item_id : 1029
             * img_url : https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=8198
             * device : [{"user_device_id":"55","device_title":"神灯智能摄像机","device_state_text":"未连接","device_state":"1","img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=8403","img_detail_url":"http://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=7280","item_detail":"长按重置按钮，保持3秒左右，至指示灯变为黄色，即重置成功","device_id_three":"1550"},{"user_device_id":"60","device_title":"智能摄像机","device_state_text":"未连接","device_state":"1","img_url":"https://jy.hljsdkj.com/Frame/uploadFile/showImg?file_id=5560","img_detail_url":"http://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=7281","item_detail":"长按重置按钮，保持3秒左右，至指示灯变为黄色，即重置成功","device_id_three":"1034"},{"user_device_id":"61","device_title":"神灯智能摄像机","device_state_text":"未连接","device_state":"1","img_url":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=8403","img_detail_url":"http://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=7280","item_detail":"长按重置按钮，保持3秒左右，至指示灯变为黄色，即重置成功","device_id_three":"1550"}]
             */

            private String item_name;
            private String item_id;
            private String img_url;
            private List<DeviceBean> device;

            public String getItem_name() {
                return item_name;
            }

            public void setItem_name(String item_name) {
                this.item_name = item_name;
            }

            public String getItem_id() {
                return item_id;
            }

            public void setItem_id(String item_id) {
                this.item_id = item_id;
            }

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }

            public List<DeviceBean> getDevice() {
                return device;
            }

            public void setDevice(List<DeviceBean> device) {
                this.device = device;
            }

            public static class DeviceBean {
                /**
                 * user_device_id : 55
                 * device_title : 神灯智能摄像机
                 * device_state_text : 未连接
                 * device_state : 1
                 * img_url : https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=8403
                 * img_detail_url : http://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=7280
                 * item_detail : 长按重置按钮，保持3秒左右，至指示灯变为黄色，即重置成功
                 * device_id_three : 1550
                 */

                private String user_device_id;
                private String device_title;
                private String device_state_text;
                private String device_state;
                private String img_url;
                private String img_detail_url;
                private String item_detail;
                private String device_id_three;

                public String getUser_device_id() {
                    return user_device_id;
                }

                public void setUser_device_id(String user_device_id) {
                    this.user_device_id = user_device_id;
                }

                public String getDevice_title() {
                    return device_title;
                }

                public void setDevice_title(String device_title) {
                    this.device_title = device_title;
                }

                public String getDevice_state_text() {
                    return device_state_text;
                }

                public void setDevice_state_text(String device_state_text) {
                    this.device_state_text = device_state_text;
                }

                public String getDevice_state() {
                    return device_state;
                }

                public void setDevice_state(String device_state) {
                    this.device_state = device_state;
                }

                public String getImg_url() {
                    return img_url;
                }

                public void setImg_url(String img_url) {
                    this.img_url = img_url;
                }

                public String getImg_detail_url() {
                    return img_detail_url;
                }

                public void setImg_detail_url(String img_detail_url) {
                    this.img_detail_url = img_detail_url;
                }

                public String getItem_detail() {
                    return item_detail;
                }

                public void setItem_detail(String item_detail) {
                    this.item_detail = item_detail;
                }

                public String getDevice_id_three() {
                    return device_id_three;
                }

                public void setDevice_id_three(String device_id_three) {
                    this.device_id_three = device_id_three;
                }
            }
        }

        public static class CarBoxListBean {
            /**
             * car_brand_name : 阿斯顿·马丁Rapide
             * gps_x : 45.666027
             * gaode_y : 126.611563
             * available_check : 1
             * gps_y : 126.605662
             * car_brand_url : https://shop.hljsdkj.com/commons/images/siye_autoparts/brand/130131578038733348.jpg
             * lock_detail : -
             * baidu_y : 126.617941
             * baidu_x : 45.6742745
             * server_id : 5/
             * user_car_id : 27
             * zhu_apc : 065
             * ccid : aaaaaaaaaaaaaaaaaaaaaa05
             * plate_number : 黑E6666654
             * gaode_x : 45.6679638
             * gps_addr : 哈尔滨市南岗区电缆街170
             */

            private String car_brand_name;
            private String gps_x;
            private String gaode_y;
            private String available_check;
            private String gps_y;
            private String car_brand_url;
            private String lock_detail;
            private String baidu_y;
            private String baidu_x;
            private String server_id;
            private String user_car_id;
            private String zhu_apc;
            private String ccid;
            private String plate_number;
            private String gaode_x;
            private String gps_addr;

            public String getCar_brand_name() {
                return car_brand_name;
            }

            public void setCar_brand_name(String car_brand_name) {
                this.car_brand_name = car_brand_name;
            }

            public String getGps_x() {
                return gps_x;
            }

            public void setGps_x(String gps_x) {
                this.gps_x = gps_x;
            }

            public String getGaode_y() {
                return gaode_y;
            }

            public void setGaode_y(String gaode_y) {
                this.gaode_y = gaode_y;
            }

            public String getAvailable_check() {
                return available_check;
            }

            public void setAvailable_check(String available_check) {
                this.available_check = available_check;
            }

            public String getGps_y() {
                return gps_y;
            }

            public void setGps_y(String gps_y) {
                this.gps_y = gps_y;
            }

            public String getCar_brand_url() {
                return car_brand_url;
            }

            public void setCar_brand_url(String car_brand_url) {
                this.car_brand_url = car_brand_url;
            }

            public String getLock_detail() {
                return lock_detail;
            }

            public void setLock_detail(String lock_detail) {
                this.lock_detail = lock_detail;
            }

            public String getBaidu_y() {
                return baidu_y;
            }

            public void setBaidu_y(String baidu_y) {
                this.baidu_y = baidu_y;
            }

            public String getBaidu_x() {
                return baidu_x;
            }

            public void setBaidu_x(String baidu_x) {
                this.baidu_x = baidu_x;
            }

            public String getServer_id() {
                return server_id;
            }

            public void setServer_id(String server_id) {
                this.server_id = server_id;
            }

            public String getUser_car_id() {
                return user_car_id;
            }

            public void setUser_car_id(String user_car_id) {
                this.user_car_id = user_car_id;
            }

            public String getZhu_apc() {
                return zhu_apc;
            }

            public void setZhu_apc(String zhu_apc) {
                this.zhu_apc = zhu_apc;
            }

            public String getCcid() {
                return ccid;
            }

            public void setCcid(String ccid) {
                this.ccid = ccid;
            }

            public String getPlate_number() {
                return plate_number;
            }

            public void setPlate_number(String plate_number) {
                this.plate_number = plate_number;
            }

            public String getGaode_x() {
                return gaode_x;
            }

            public void setGaode_x(String gaode_x) {
                this.gaode_x = gaode_x;
            }

            public String getGps_addr() {
                return gps_addr;
            }

            public void setGps_addr(String gps_addr) {
                this.gps_addr = gps_addr;
            }
        }
    }
}
