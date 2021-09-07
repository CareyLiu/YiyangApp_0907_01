package com.yiyang.cn.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.lang.reflect.Method;

public class WitMqttFormatService {

    // 反射处理汽车数据
    @SuppressWarnings("unchecked")
    public HashMap<String, String> reqCarData(String text) throws Exception {
        text = text.substring(2, text.length() - 1);
        String[] arr = text.split("_");
        int len = arr.length;
        HashMap<String, String> entity = new HashMap<String, String>();
        for (int i = 0; i < len; i++) {
            text = arr[i];
            try {
                Class<?> userClass = Class.forName("com.yiyang.cn.service.WitMqttFormatService");
                WitMqttFormatService mf = (WitMqttFormatService) userClass.newInstance();
                mf.setText(text.substring(1));
                Method method = userClass.getMethod(text.substring(0, 1));//得到方法对象
                entity.putAll((HashMap<String, String>) method.invoke(mf));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        entity.put("time", systemTime("yyyy-MM-dd HH:mm:ss"));
        return entity;
    }

    public static String systemTime(String formatStr) {
        SimpleDateFormat getFormat = new SimpleDateFormat(formatStr);
        Date date = new Date();
        return getFormat.format(date);
    }

    private String text = "";

    public void setText(String text) {
        this.text = text;
    }

    // 汽车GPS经纬度
    public HashMap<String, String> g() {
        HashMap<String, String> entity = new HashMap<String, String>();
        try {
            if (0 <= text.substring(0, 6).indexOf("a"))
                return entity;
            // 纬度x 045.666666=045666666 9
            BigDecimal dec = new BigDecimal(text.substring(0, 3) + "." + text.substring(3, 9));
            entity.put("gps_x", dec.toString());
            // 经度y 126.666666=126666666 9
            dec = new BigDecimal(text.substring(9, 12) + "." + text.substring(12, 18));
            entity.put("gps_y", dec.toString());
            // 车头朝向方向:1东2南3西4北5西北 6西南7东南 8东北
            String car_orientations = text.substring(18, 19);
            if (!"a".equals(car_orientations))
                entity.put("car_orientations", car_orientations);
        } catch (Exception e) {
            System.out.println("WitMqttformatService-->g:" + e.toString());
            e.printStackTrace();
        }
        return entity;
    }

    // 四个胎压(250260270280)kp->2位一组:25.0=250 3.0=030 06.6=066 12
    public HashMap<String, String> h() {
        HashMap<String, String> entity = new HashMap<String, String>();
        try {
            String str = "", strA = "";
            int num = 0;
            for (int i = 0; i < 4; i++) {
                strA = text.substring(num, num + 3);
                str += 0 <= strA.indexOf("a") ? "0" : "," + new BigDecimal(strA.substring(0, 2) + "." + strA.substring(2, 3)).toString();
                num += 3;
            }
            entity.put("tyre_pressure", str.substring(1));
        } catch (Exception e) {
            System.out.println("WitMqttformatService-->h:" + e.toString());
            e.printStackTrace();
        }
        return entity;
    }

    // 汽车空调
    public HashMap<String, String> i() {
        HashMap<String, String> entity = new HashMap<String, String>();
        try {
            entity.put("air_go", text.substring(0, 1)); // 空调循环方式1.内循环 2.外循环 1
            entity.put("air_dangwei", text.substring(1, 2)); // 空调档位0-4档 1
            entity.put("air_model", text.substring(2, 3)); // 空调模式1-4模式 1
            entity.put("air_conditioner", text.substring(3, 6)); // 空调温度022整数 3
            entity.put("car_air", text.substring(6, 7)); // 盒子-空调：1.开启 2.关闭
            entity = formate_a(entity);
            entity = formate_num(entity);
        } catch (Exception e) {
            System.out.println("WitMqttformatService-->i:" + e.toString());
            e.printStackTrace();
        }
        return entity;
    }

    // 汽车时时数据
    public HashMap<String, String> j() {
        HashMap<String, String> entity = new HashMap<String, String>();
        try {
            // ACC状态(车钥匙门)1.lock锁车 2.acc收音机电源 3.on接通全部电源 4.start启动中
            entity.put("car_acc", text.substring(0, 1));
            // 当前时速:14223km/h,0静止 5
            entity.put("mph", text.substring(1, 6));
            // 剩余汽油2000升 4
            entity.put("benzin", text.substring(6, 10));
            // 油箱满油3050升 4
            entity.put("benzin_total", text.substring(10, 14));
            // 剩余油量能行驶公里15200公里 5
            entity.put("remaining_km", text.substring(14, 19));
            // 发动机转速表r/min 006000 6
            entity.put("rev", text.substring(19, 25));
            // 冷却液温度(-30)度 3
            entity.put("coolant", text.substring(25, 28));
            entity = formate_a(entity);
            entity = formate_num(entity);
        } catch (Exception e) {
            System.out.println("WitMqttformatService-->i:" + e.toString());
            e.printStackTrace();
        }
        return entity;
    }

    // 车门/车窗/车锁
    public HashMap<String, String> k() {
        HashMap<String, String> entity = new HashMap<String, String>();
        try {
            entity.put("car_door", text.substring(0, 4)); // 车门(1.开 2.关)4个位置1211 4 是
            entity.put("car_window", text.substring(4, 8)); // 4个车窗1.开启 2.关闭(1212) 4 是
            entity.put("car_scuttle", text.substring(8, 9)); // 天窗1.开启 2.关闭 1 是
            entity.put("car_door_lock", text.substring(9, 10));// 车锁1.开 2.关 1 是
            entity = formate_a(entity);
            entity = formate_num(entity);
        } catch (Exception e) {
            System.out.println("WitMqttformatService-->k:" + e.toString());
            e.printStackTrace();
        }
        return entity;
    }

    // 开关
    public HashMap<String, String> l() {
        HashMap<String, String> entity = new HashMap<String, String>();
        try {
            entity.put("traffic_collision", text.substring(0, 1)); // 撞车提醒1.无2.碰撞 1 是
            entity.put("urgency_stop", text.substring(1, 2));      // 急停1.无 2.急刹车 1 是
            entity.put("urgency_start", text.substring(2, 3));     // 猛踩油门1.无 2.猛踩油门 1 是
            entity.put("cut_electricity", text.substring(3, 4));   // 断电1.无 2.断电报警 1 是
            entity.put("cut_thread", text.substring(4, 5));        // 剪线1.无 2.剪线报警 1 是
            entity.put("car_oil", text.substring(5, 6));           // 油路1.断油 2.恢复油 1 是
            entity.put("car_trunk", text.substring(6, 7));         // 后备箱1.开启 2.关闭 1 是
            entity.put("car_light", text.substring(7, 8));         // 轮廓灯1.开启 2.关闭 1 是
            entity.put("car_high_beam", text.substring(8, 9));     // 远光灯1.开启 2.关闭 1 是
            entity.put("car_foglight", text.substring(9, 10));     // 雾灯1.开启 2.关闭 1 是
            entity.put("car_flasher", text.substring(10, 11));     // 双闪1.开启 2.关闭 1 是
            entity = formate_a(entity);
            entity = formate_num(entity);
        } catch (Exception e) {
            System.out.println("WitMqttformatService-->l:" + e.toString());
            e.printStackTrace();
        }
        return entity;
    }

    // 汽车其它数据
    public HashMap<String, String> m() {
        HashMap<String, String> entity = new HashMap<String, String>();
        try {
            // 汽车电瓶电量:报警030% 3 是
            entity.put("car_electric", text.substring(0, 3));
            // 玻璃水用量040% 3 是
            entity.put("bath_glass", text.substring(3, 6));
            // 变速箱温度023整数 3 是
            entity.put("gearbox_temperature", text.substring(6, 9));
            // 油耗27升/100km 整数 2 是
            entity.put("oil_wear", text.substring(9, 11));
            // 行驶总公里数 0052212 7 是
            entity.put("total_km", text.substring(11, 18));
            // 空气流量0.0g/s:5位00123=12.3 5 是
            String car_air_flow = text.substring(18, 23);
            car_air_flow = car_air_flow.substring(0, 4) + (("0".equals(car_air_flow.substring(4, 5))) ? "" : "." + car_air_flow.substring(4, 5));
            entity.put("car_air_flow", car_air_flow);
            // 进气温度0040度:3位 f代表负数 -012=-12 4 是
            entity.put("car_in_temp", text.substring(23, 27));
            // 燃油压力0.0KPA 5位 00236=23.6kpa 5 是
            String car_fuel_pressure = text.substring(27, 32);
            car_fuel_pressure = car_fuel_pressure.substring(0, 4) + ("0".equals(car_fuel_pressure.substring(4, 5)) ? "" : ("." + car_fuel_pressure.substring(4, 5)));
            entity.put("car_fuel_pressure", car_fuel_pressure);
            // 节气门温度4位 -026=-2.6度 4 是
            entity.put("car_thr_temp", text.substring(32, 36));
            // 进气管压力4位 0373=37.3kpa 4 是
            String car_intake_pipe_pre = text.substring(36, 40);
            car_intake_pipe_pre = car_intake_pipe_pre.substring(0, 3) + ("0".equals(car_intake_pipe_pre.substring(3, 4)) ? "" : ("." + car_intake_pipe_pre.substring(3, 4)));
            entity.put("car_intake_pipe_pre", car_intake_pipe_pre);
            entity = formate_a(entity);
            entity = formate_num(entity);
        } catch (Exception e) {
            System.out.println("WitMqttformatService-->m:" + e.toString());
            e.printStackTrace();
        }
        return entity;
    }

    /**
     * 硬件监控报警
     **/
    public HashMap<String, String> n() {
        HashMap<String, String> entity = new HashMap<String, String>();
        try {
            entity.put("s_one", text.substring(0, 1));           // 丢油0.无设备1.开启2.关闭
            entity.put("s_one_check", text.substring(1, 2));     // 丢油1.无 2.报警中
            entity.put("s_two", text.substring(2, 3));           // 丢货0.无设备1.开启2.关闭
            entity.put("s_two_check", text.substring(3, 4));     // 丢货1.无 2.报警中
            entity.put("s_three", text.substring(4, 5));         // 丢电池0.无设备1.开启2.关闭
            entity.put("s_three_check", text.substring(5, 6));   // 丢电池1.无 2.报警中
            entity.put("s_four", text.substring(6, 7));          // 一氧化碳报警 0.无设备1.开启2.关闭
            entity.put("s_four_check", text.substring(7, 8));    // 一氧化碳报警 1.无 2.报警中
            entity.put("s_five", text.substring(8, 9));          // 低氧气含量报警0.无设备1.开启2.关闭
            entity.put("s_five_check", text.substring(9, 10));   // 低氧气含量报警1.无 2.报警中
        } catch (Exception e) {
            System.out.println("WitMqttformatService-->n:" + e.toString());
            e.printStackTrace();
        }
        return entity;
    }

    /**
     * 硬件日志 gps信号参数 gprs信号强度
     **/
    public HashMap<String, String> p() {
        HashMap<String, String> entity = new HashMap<String, String>();
        entity.put("hw_gps_par", text.substring(0, 4));
        entity.put("hw_gprs_par", text.substring(4, 8));
        return entity;
    }

    /**
     * 硬件日志 数据直接存储到数据库，一条一条的存好 维护时候可以导出数据库数据   常看数据分析问题，内部信号量状态等观察区数据
     **/
    public HashMap<String, String> r() {
        HashMap<String, String> entity = new HashMap<String, String>();
        entity.put("hw_statu", text.substring(0, 8));
        return entity;
    }

    /**
     * 掉电不消失数据 设备出厂时候是0  一直累加 到产品生命周期终止
     **/
    public HashMap<String, String> s() {
        HashMap<String, String> entity = new HashMap<String, String>();
        entity.put("hw_cold_num", text.substring(0, 4));               // 冷启动次数                      4位
        entity.put("hw_warm_num", text.substring(4, 8));               // 热启动次数                      4位
        entity.put("hw_dog_num", text.substring(8, 12));               // 看门狗启动次数               4位
        entity.put("hw_cold_gprs_num", text.substring(12, 16));        // 冷启动gprs模块次数     4位
        entity.put("hw_rec_gprs_num", text.substring(16, 20));         // rprs模块重新连接次数 4位
        entity.put("hw_one_num", text.substring(20, 24));              // 外部引脚复位		 4位
        entity.put("hw_two_num", text.substring(24, 28));              // 软件复位		     4位
        entity.put("hw_three_num", text.substring(28, 32));            // 看门狗复位		 4位
        return entity;
    }

    // 驻车加热器时时数据
    public HashMap<String, String> M() {
        HashMap<String, String> entity = new HashMap<String, String>();
        try {
            // 驻车加热器:当前档位1至5档	1	是
            String oper_dang = text.substring(0, 1);
            entity.put("oper_dang", 0 <= oper_dang.indexOf("a") ? "" : oper_dang);
            // 预设温度1至32度	2	是
            String oper_wendu_now = text.substring(1, 3);
            oper_wendu_now = 0 <= oper_wendu_now.indexOf("a") ? "" : new BigDecimal(oper_wendu_now).toString();
            entity.put("oper_wendu_now", oper_wendu_now);
            // 驻车预热器:1.档位开机2.空调开机3.风暖关机4.水暖:开机5.水暖:关机	1	是
            entity.put("oper_open_close", text.substring(3, 4));
            // 驻车加热器:当前温度	3	是
            String oper_wendu = text.substring(4, 6);
            oper_wendu += "0".equals(text.substring(6, 7)) ? "" : "." + text.substring(6, 7);
            entity.put("oper_wendu", oper_wendu);
            // 水暖加热器:尾气温度 例如:-03	3	是
            entity.put("zhu_shui_tail_gas", text.substring(7, 10));
            // 驻车加热器:电压->0253 = 25.3	4	是
            entity.put("machine_voltage", text.substring(10, 13) + "." + text.substring(13, 14));
            // 驻车加热器:风机转速->13245	5	是
            entity.put("revolution", text.substring(14, 19));
            // 驻车加热器:加热塞功率->0264=26.4	4	是
            String power = text.substring(19, 23);
            entity.put("power", power.substring(0, 3) + "." + power.substring(3));
            // 驻车加热器:油泵频率->0265=26.5	4	是
            String frequency = text.substring(23, 27);
            entity.put("frequency", frequency.substring(0, 3) + "." + frequency.substring(3));
            // 驻车加热器:入风口温度->例如:-026	4	是
            entity.put("in_temperature", text.substring(27, 31));
            // 驻车加热器:出风口温度->0128	4	是
            entity.put("out_temperature", text.substring(31, 35));
            // 驻车加热器故障码->01至18	2	 标准故障码
            String zhu_car_stoppage_no = text.substring(35, 37);
            zhu_car_stoppage_no = 0 <= zhu_car_stoppage_no.indexOf("a") ? "" : String.valueOf(Integer.parseInt(zhu_car_stoppage_no));
            entity.put("zhu_car_stoppage_no", zhu_car_stoppage_no);
            // 水暖加热器:开关机状态
            String shui_open_close = text.substring(37, 38);
            entity.put("shui_open_close", 0 <= shui_open_close.indexOf("a") ? "5" : shui_open_close);
            entity.put("work_time", "1");
            try {
                entity.put("work_time", text.substring(38, 44));     // 风暖加热器:工作时长(小时)
                // 驻车加热器故障码->01至18	2	其它公司用
                String zhu_car_stoppage_no_o = text.substring(44, 46);
                zhu_car_stoppage_no_o = 0 <= zhu_car_stoppage_no_o.indexOf("a") ? "" : String.valueOf(Integer.parseInt(zhu_car_stoppage_no_o));
                entity.put("zhu_car_stoppage_no_o", zhu_car_stoppage_no_o);
            } catch (Exception e) {
            }
            entity = formate_a(entity);
            entity = formate_num(entity);
        } catch (Exception e) {
            System.out.println("WitMqttformatService-->M:" + e.toString());
            e.printStackTrace();
        }
        return entity;
    }

    /**
     * 状态
     **/
    public HashMap<String, String> Z() {
        HashMap<String, String> entity = new HashMap<String, String>();
        entity.put("gps_state", text.substring(0, 1));
        try {
            entity.put("version_feng", text.substring(1, 5));
            entity.put("version_cbox", text.substring(5, 8));
            entity.put("zhu_feng", text.substring(8, 9));
        } catch (Exception e) {
        }
        entity = formate_a(entity);
        entity = formate_num(entity);
        return entity;
    }

    /**
     * 数据里面有a占位，去除a
     **/
    @SuppressWarnings("rawtypes")
    public HashMap<String, String> formate_a(HashMap<String, String> entity) {
        if (null == entity || 0 == entity.size())
            return entity;
        HashMap<String, String> data = new HashMap<String, String>();
        Iterator<?> iterator = entity.entrySet().iterator();
        Map.Entry entry;
        String str, key;
        while (iterator.hasNext()) {
            entry = (Map.Entry) iterator.next();
            key = String.valueOf(entry.getKey());
            str = entity.get(key);
            if (null != str && !"".equals(str) && 0 > str.indexOf("a"))
                data.put(key, str);
            else
                data.put(key, "");
        }
        return data;
    }

    /**
     * 数据格式化成字符串
     **/
    @SuppressWarnings("rawtypes")
    public HashMap<String, String> formate_num(HashMap<String, String> entity) {
        if (null == entity || 0 == entity.size())
            return entity;
        Iterator<?> iterator = entity.entrySet().iterator();
        Map.Entry entry;
        String str, key;
        while (iterator.hasNext()) {
            entry = (Map.Entry) iterator.next();
            key = String.valueOf(entry.getKey());
            str = entity.get(key);
            if (null != str && !"".equals(str))
                entity.put(key, new BigDecimal(str).toString());
        }
        return entity;
    }
}
