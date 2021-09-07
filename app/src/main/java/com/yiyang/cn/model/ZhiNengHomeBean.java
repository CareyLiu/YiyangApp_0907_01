package com.yiyang.cn.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class ZhiNengHomeBean {

    /**
     * msg_code : 0000
     * msg : ok
     * row_num : 1
     * data : [{"member_type":"2","device_num":"15","family_id":"17","family_name":"我的爱家","device":[{"room_name":"默认房间","device_name":"神灯管家","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10533","online_state":"2","device_id":"44","work_state":"2","warn_state":"3","device_ccid":"asasasasasasasasasasas01","device_type":"0","server_id":"1/","device_ccid_up":"0"},{"room_name":"默认房间","device_name":"灯","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10524","online_state":"2","device_id":"45","work_state":"2","warn_state":"3","device_ccid":"0101","device_type":"01","server_id":"","device_ccid_up":"asasasasasasasasasasas01"},{"room_name":"默认房间","device_name":"灯2","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10524","online_state":"2","device_id":"46","work_state":"2","warn_state":"3","device_ccid":"0102","device_type":"01","server_id":"","device_ccid_up":"asasasasasasasasasasas01"},{"room_name":"主卧","device_name":"灯3","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10524","online_state":"2","device_id":"47","work_state":"2","warn_state":"3","device_ccid":"0103","device_type":"01","server_id":"","device_ccid_up":"asasasasasasasasasasas01"},{"room_name":"默认房间","device_name":"插座","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10530","online_state":"2","device_id":"48","work_state":"2","warn_state":"3","device_ccid":"0201","device_type":"02","server_id":"","device_ccid_up":"asasasasasasasasasasas01"},{"room_name":"默认房间","device_name":"插座2","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10530","online_state":"2","device_id":"49","work_state":"2","warn_state":"3","device_ccid":"0202","device_type":"02","server_id":"","device_ccid_up":"asasasasasasasasasasas01"},{"room_name":"默认房间","device_name":"插座3","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10530","online_state":"2","device_id":"50","work_state":"2","warn_state":"3","device_ccid":"0203","device_type":"02","server_id":"","device_ccid_up":"asasasasasasasasasasas01"},{"room_name":"主卧","device_name":"自动喂鱼","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10532","online_state":"2","device_id":"51","work_state":"2","warn_state":"3","device_ccid":"0301","device_type":"03","server_id":"","device_ccid_up":"asasasasasasasasasasas01"},{"room_name":"默认房间","device_name":"自动浇花","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10531","online_state":"2","device_id":"52","work_state":"2","warn_state":"3","device_ccid":"0401","device_type":"04","server_id":"","device_ccid_up":"asasasasasasasasasasas01"},{"room_name":"默认房间","device_name":"烟雾报警","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10529","online_state":"2","device_id":"53","work_state":"3","warn_state":"1","device_ccid":"1101","device_type":"11","server_id":"","device_ccid_up":"asasasasasasasasasasas01"},{"room_name":"默认房间","device_name":"门磁检测","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10528","online_state":"2","device_id":"54","work_state":"3","warn_state":"1","device_ccid":"1201","device_type":"12","server_id":"","device_ccid_up":"asasasasasasasasasasas01"},{"room_name":"默认房间","device_name":"漏水检测","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10527","online_state":"2","device_id":"55","work_state":"3","warn_state":"1","device_ccid":"1301","device_type":"13","server_id":"","device_ccid_up":"asasasasasasasasasasas01"},{"room_name":"默认房间","device_name":"雷达监测","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10526","online_state":"2","device_id":"56","work_state":"3","warn_state":"1","device_ccid":"1401","device_type":"14","server_id":"","device_ccid_up":"asasasasasasasasasasas01"},{"room_name":"默认房间","device_name":"紧急开关","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10525","online_state":"2","device_id":"57","work_state":"3","warn_state":"1","device_ccid":"1501","device_type":"15","server_id":"","device_ccid_up":"asasasasasasasasasasas01"},{"room_name":"默认房间","device_name":"风水摆件","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10534","online_state":"2","device_id":"58","work_state":"2","warn_state":"3","device_ccid":"6001","device_type":"60","server_id":"","device_ccid_up":"asasasasasasasasasasas01"}],"room":[{"room_id":"11","room_name":"主卧","family_id":"17","device_number":"2"},{"room_id":"13","room_name":"餐厅","family_id":"17","device_number":"0"},{"room_id":"0","room_name":"默认房间","family_id":"17","device_number":"13"}]}]
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
         * member_type : 2
         * device_num : 15
         * family_id : 17
         * family_name : 我的爱家
         * device : [{"room_name":"默认房间","device_name":"神灯管家","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10533","online_state":"2","device_id":"44","work_state":"2","warn_state":"3","device_ccid":"asasasasasasasasasasas01","device_type":"0","server_id":"1/","device_ccid_up":"0"},{"room_name":"默认房间","device_name":"灯","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10524","online_state":"2","device_id":"45","work_state":"2","warn_state":"3","device_ccid":"0101","device_type":"01","server_id":"","device_ccid_up":"asasasasasasasasasasas01"},{"room_name":"默认房间","device_name":"灯2","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10524","online_state":"2","device_id":"46","work_state":"2","warn_state":"3","device_ccid":"0102","device_type":"01","server_id":"","device_ccid_up":"asasasasasasasasasasas01"},{"room_name":"主卧","device_name":"灯3","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10524","online_state":"2","device_id":"47","work_state":"2","warn_state":"3","device_ccid":"0103","device_type":"01","server_id":"","device_ccid_up":"asasasasasasasasasasas01"},{"room_name":"默认房间","device_name":"插座","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10530","online_state":"2","device_id":"48","work_state":"2","warn_state":"3","device_ccid":"0201","device_type":"02","server_id":"","device_ccid_up":"asasasasasasasasasasas01"},{"room_name":"默认房间","device_name":"插座2","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10530","online_state":"2","device_id":"49","work_state":"2","warn_state":"3","device_ccid":"0202","device_type":"02","server_id":"","device_ccid_up":"asasasasasasasasasasas01"},{"room_name":"默认房间","device_name":"插座3","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10530","online_state":"2","device_id":"50","work_state":"2","warn_state":"3","device_ccid":"0203","device_type":"02","server_id":"","device_ccid_up":"asasasasasasasasasasas01"},{"room_name":"主卧","device_name":"自动喂鱼","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10532","online_state":"2","device_id":"51","work_state":"2","warn_state":"3","device_ccid":"0301","device_type":"03","server_id":"","device_ccid_up":"asasasasasasasasasasas01"},{"room_name":"默认房间","device_name":"自动浇花","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10531","online_state":"2","device_id":"52","work_state":"2","warn_state":"3","device_ccid":"0401","device_type":"04","server_id":"","device_ccid_up":"asasasasasasasasasasas01"},{"room_name":"默认房间","device_name":"烟雾报警","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10529","online_state":"2","device_id":"53","work_state":"3","warn_state":"1","device_ccid":"1101","device_type":"11","server_id":"","device_ccid_up":"asasasasasasasasasasas01"},{"room_name":"默认房间","device_name":"门磁检测","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10528","online_state":"2","device_id":"54","work_state":"3","warn_state":"1","device_ccid":"1201","device_type":"12","server_id":"","device_ccid_up":"asasasasasasasasasasas01"},{"room_name":"默认房间","device_name":"漏水检测","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10527","online_state":"2","device_id":"55","work_state":"3","warn_state":"1","device_ccid":"1301","device_type":"13","server_id":"","device_ccid_up":"asasasasasasasasasasas01"},{"room_name":"默认房间","device_name":"雷达监测","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10526","online_state":"2","device_id":"56","work_state":"3","warn_state":"1","device_ccid":"1401","device_type":"14","server_id":"","device_ccid_up":"asasasasasasasasasasas01"},{"room_name":"默认房间","device_name":"紧急开关","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10525","online_state":"2","device_id":"57","work_state":"3","warn_state":"1","device_ccid":"1501","device_type":"15","server_id":"","device_ccid_up":"asasasasasasasasasasas01"},{"room_name":"默认房间","device_name":"风水摆件","device_type_pic":"https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10534","online_state":"2","device_id":"58","work_state":"2","warn_state":"3","device_ccid":"6001","device_type":"60","server_id":"","device_ccid_up":"asasasasasasasasasasas01"}]
         * room : [{"room_id":"11","room_name":"主卧","family_id":"17","device_number":"2"},{"room_id":"13","room_name":"餐厅","family_id":"17","device_number":"0"},{"room_id":"0","room_name":"默认房间","family_id":"17","device_number":"13"}]
         */

        private String member_type;
        private String device_num;
        private String family_id;
        private String ty_family_id;
        private String family_name;
        public String temperature;//温度
        public String humidity;//湿度
        private ArrayList<DeviceBean> device;
        private ArrayList<RoomBean> room;

        public String getTy_family_id() {
            return ty_family_id;
        }

        public void setTy_family_id(String ty_family_id) {
            this.ty_family_id = ty_family_id;
        }

        public String getMember_type() {
            return member_type;
        }

        public void setMember_type(String member_type) {
            this.member_type = member_type;
        }

        public String getDevice_num() {
            return device_num;
        }

        public void setDevice_num(String device_num) {
            this.device_num = device_num;
        }

        public String getFamily_id() {
            return family_id;
        }

        public void setFamily_id(String family_id) {
            this.family_id = family_id;
        }

        public String getFamily_name() {
            return family_name;
        }

        public void setFamily_name(String family_name) {
            this.family_name = family_name;
        }

        public ArrayList<DeviceBean> getDevice() {
            return device;
        }

        public void setDevice(ArrayList<DeviceBean> device) {
            this.device = device;
        }

        public ArrayList<RoomBean> getRoom() {
            return room;
        }

        public void setRoom(ArrayList<RoomBean> room) {
            this.room = room;
        }

        public static class DeviceBean implements Parcelable {
            /**
             * room_name : 默认房间
             * device_name : 神灯管家
             * device_type_pic : https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=10533
             * online_state : 2
             * device_id : 44
             * work_state : 2
             * warn_state : 3
             * device_ccid : asasasasasasasasasasas01
             * device_type : 0
             * server_id : 1/
             * device_ccid_up : 0
             * device_category_code
             */

            private String room_name;
            private String device_name;
            private String device_type_pic;
            private String online_state;
            private String device_id;
            private String work_state;
            private String warn_state;
            private String device_ccid;
            private String device_type;
            private String server_id;
            private String device_ccid_up;
            /**
             * ty_room_id :
             * device_category : 01
             * ty_device_ccid :
             * ty_family_id :
             */

            private String ty_room_id;
            private String device_category;
            private String ty_device_ccid;
            private String ty_family_id;
            private String device_category_code;

            protected DeviceBean(Parcel in) {
                room_name = in.readString();
                device_name = in.readString();
                device_type_pic = in.readString();
                online_state = in.readString();
                device_id = in.readString();
                work_state = in.readString();
                warn_state = in.readString();
                device_ccid = in.readString();
                device_type = in.readString();
                server_id = in.readString();
                device_ccid_up = in.readString();
                ty_room_id = in.readString();
                device_category = in.readString();
                ty_device_ccid = in.readString();
                ty_family_id = in.readString();
            }

            public DeviceBean() {

            }

            public String getDevice_category_code() {
                return device_category_code;
            }

            public void setDevice_category_code(String device_category_code) {
                this.device_category_code = device_category_code;
            }

            public static final Creator<DeviceBean> CREATOR = new Creator<DeviceBean>() {
                @Override
                public DeviceBean createFromParcel(Parcel in) {
                    return new DeviceBean(in);
                }

                @Override
                public DeviceBean[] newArray(int size) {
                    return new DeviceBean[size];
                }
            };

            public String getRoom_name() {
                return room_name;
            }

            public void setRoom_name(String room_name) {
                this.room_name = room_name;
            }

            public String getDevice_name() {
                return device_name;
            }

            public void setDevice_name(String device_name) {
                this.device_name = device_name;
            }

            public String getDevice_type_pic() {
                return device_type_pic;
            }

            public void setDevice_type_pic(String device_type_pic) {
                this.device_type_pic = device_type_pic;
            }

            public String getOnline_state() {
                return online_state;
            }

            public void setOnline_state(String online_state) {
                this.online_state = online_state;
            }

            public String getDevice_id() {
                return device_id;
            }

            public void setDevice_id(String device_id) {
                this.device_id = device_id;
            }

            public String getWork_state() {
                return work_state;
            }

            public void setWork_state(String work_state) {
                this.work_state = work_state;
            }

            public String getWarn_state() {
                return warn_state;
            }

            public void setWarn_state(String warn_state) {
                this.warn_state = warn_state;
            }

            public String getDevice_ccid() {
                return device_ccid;
            }

            public void setDevice_ccid(String device_ccid) {
                this.device_ccid = device_ccid;
            }

            public String getDevice_type() {
                return device_type;
            }

            public void setDevice_type(String device_type) {
                this.device_type = device_type;
            }

            public String getServer_id() {
                return server_id;
            }

            public void setServer_id(String server_id) {
                this.server_id = server_id;
            }

            public String getDevice_ccid_up() {
                return device_ccid_up;
            }

            public void setDevice_ccid_up(String device_ccid_up) {
                this.device_ccid_up = device_ccid_up;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(room_name);
                dest.writeString(device_name);
                dest.writeString(device_type_pic);
                dest.writeString(online_state);
                dest.writeString(device_id);
                dest.writeString(work_state);
                dest.writeString(warn_state);
                dest.writeString(device_ccid);
                dest.writeString(device_type);
                dest.writeString(server_id);
                dest.writeString(device_ccid_up);
                dest.writeString(ty_room_id);
                dest.writeString(device_category);
                dest.writeString(ty_device_ccid);
                dest.writeString(ty_family_id);
            }

            public String getTy_room_id() {
                return ty_room_id;
            }

            public void setTy_room_id(String ty_room_id) {
                this.ty_room_id = ty_room_id;
            }

            public String getDevice_category() {
                return device_category;
            }

            public void setDevice_category(String device_category) {
                this.device_category = device_category;
            }

            public String getTy_device_ccid() {
                return ty_device_ccid;
            }

            public void setTy_device_ccid(String ty_device_ccid) {
                this.ty_device_ccid = ty_device_ccid;
            }

            public String getTy_family_id() {
                return ty_family_id;
            }

            public void setTy_family_id(String ty_family_id) {
                this.ty_family_id = ty_family_id;
            }
        }

        public static class RoomBean implements Parcelable {
            /**
             * room_id : 11
             * room_name : 主卧
             * family_id : 17
             * device_number : 2
             */

            private String room_id;
            private String room_name;
            private String family_id;
            private String device_number;

            protected RoomBean(Parcel in) {
                room_id = in.readString();
                room_name = in.readString();
                family_id = in.readString();
                device_number = in.readString();
            }

            public static final Creator<RoomBean> CREATOR = new Creator<RoomBean>() {
                @Override
                public RoomBean createFromParcel(Parcel in) {
                    return new RoomBean(in);
                }

                @Override
                public RoomBean[] newArray(int size) {
                    return new RoomBean[size];
                }
            };

            public String getRoom_id() {
                return room_id;
            }

            public void setRoom_id(String room_id) {
                this.room_id = room_id;
            }

            public String getRoom_name() {
                return room_name;
            }

            public void setRoom_name(String room_name) {
                this.room_name = room_name;
            }

            public String getFamily_id() {
                return family_id;
            }

            public void setFamily_id(String family_id) {
                this.family_id = family_id;
            }

            public String getDevice_number() {
                return device_number;
            }

            public void setDevice_number(String device_number) {
                this.device_number = device_number;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(room_id);
                dest.writeString(room_name);
                dest.writeString(family_id);
                dest.writeString(device_number);
            }
        }
    }
}
