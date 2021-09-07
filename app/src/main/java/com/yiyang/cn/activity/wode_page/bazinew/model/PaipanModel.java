package com.yiyang.cn.activity.wode_page.bazinew.model;

import java.io.Serializable;
import java.util.List;

public class PaipanModel implements Serializable {


    /**
     * msg_code : 0000
     * msg : ok
     * row_num : 1
     * data : [{"mingpan_id":"597","mingpan_bazi":[{"msg":"暂无数据"}],"mingpan_xingming":[{"msg":"暂无数据"}],"mingpan_ziwei":[{"mingZhu":"巨门","solarTermsbaZi":["壬申","辛亥","癸卯","壬戌"],"shenZhu":"天梁","shiErGong":["兄弟","命宫","父母","福德","田宅","官禄","仆役","迁移","疾厄","身财","子女","夫妻"],"shiErGongNaYin":["桑松木","桑松木","金箔金","金箔金","佛灯火","佛灯火","天河水","天河水","大驿土","大驿土","钗钏金","钗钏金"],"xing_se":[["#7f1f8b","#1800ea","#1800ea","#1800ea","#606eab","#606eab","#606eab","#606eab"],["#1800ea","#1800ea","#1800ea","#606eab","#606eab","#606eab"],["#7f1f8b","#1800ea","#606eab","#606eab","#606eab"],["#1800ea","#606eab","#606eab","#606eab","#606eab","#606eab"],["#7f1f8b","#606eab","#606eab","#606eab"],["#7f1f8b","#1800ea","#1800ea","#606eab","#606eab","#606eab","#606eab","#606eab"],["#7f1f8b","#7f1f8b","#606eab","#606eab","#606eab","#606eab","#606eab"],["#7f1f8b","#7f1f8b","#606eab","#606eab","#606eab"],["#7f1f8b","#1800ea","#606eab","#606eab","#606eab","#606eab","#606eab"],["#7f1f8b","#7f1f8b","#1800ea","#606eab","#606eab","#606eab","#606eab","#606eab","#606eab"],["#7f1f8b","#7f1f8b","#1800ea","#606eab","#606eab","#606eab"],["#7f1f8b","#1800ea","#606eab","#606eab","#606eab","#606eab"]],"xing":[["七杀+旺","文昌+得","火星+陷","擎羊+陷","封诰","龙池","大禄","大鸾"],["左辅+庙+科","右弼+庙","地空+陷","天喜","大羊","月德"],["廉贞+庙","文曲+平","天虚","凤阁","命马"],["天魁+庙","恩光","截空","大昌","大魁","大哭"],["破军+旺","台辅","解神","蜚廉"],["天同+庙","天马+平","天钺+旺","三台","天贵","天寿","大马","大钺"],["武曲+旺+忌","天府+旺","天伤","天刑","天月","天福","大喜"],["太阳+得","太阴+陷","红鸾","寡宿","大火"],["贪狼+平","铃星+陷","天使","天巫","阴煞","大铃","大空"],["天机+旺","巨门+庙","地劫+平","八座","天空","破碎","天才","大虚","天厨"],["紫微+得+权","天相+得","陀罗+庙","天姚","天官","天哭"],["天梁+陷+禄","禄存+庙","孤辰","旬空","大曲","大陀"]],"jiangQian":["将星","攀鞍","岁驿","息神","华盖","劫煞","灾煞","天煞","指背","咸池","月煞","亡神"],"wuXingJu":"木三局","shengNianNaYin":"剑锋金","yinyang":"阳男","xiaoXian":["3 15 27 39 51 63 75 87 99 111","4 16 28 40 52 64 76 88 100 112","5 17 29 41 53 65 77 89 101 113","6 18 30 42 54 66 78 90 102 114","7 19 31 43 55 67 79 91 103 115","8 20 32 44 56 68 80 92 104 116","9 21 33 45 57 69 81 93 105 117","10 22 34 46 58 70 82 94 106 118","11 23 35 47 59 71 83 95 107 119","12 24 36 48 60 72 84 96 108 120","1 13 25 37 49 61 73 85 97 109","2 14 26 38 50 62 74 86 98 110"],"solar_birthday":"1992年11月23日 戌时","liuDou":"丑","daXian":["113-122","3-12","13-22","23-32","33-42","43-52","53-62","63-72","73-82","83-92","93-102","103-112"],"baZi":["壬申","辛亥","癸卯","壬戌"],"lunar_birthday":"一九九二年十月廿九 戌时","shiErGongTianGan":["壬","癸","壬","癸","甲","乙","丙","丁","戊","己","庚","辛"],"mingJuNaYin":"桑松木","wuXingChangSheng":["沐浴","冠带","临官","帝旺","衰","病","死","墓","绝","胎","养","长生"],"mingSite":"1","suiQian":["官符","小耗","大耗","龙德","白虎","天德","吊客","病符","岁建","晦气","丧门","贯索"],"name":"哦哟喂","ziDou":"丑","age":"29","boShi":["力士","青龙","小耗","将军","奏书","飞廉","喜神","病伏","大耗","伏兵","官伏","博士"],"shengXiao":"猴"}]}]
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

    public static class DataBean implements Serializable {
        /**
         * mingpan_id : 597
         * mingpan_bazi : [{"msg":"暂无数据"}]
         * mingpan_xingming : [{"msg":"暂无数据"}]
         * mingpan_ziwei : [{"mingZhu":"巨门","solarTermsbaZi":["壬申","辛亥","癸卯","壬戌"],"shenZhu":"天梁","shiErGong":["兄弟","命宫","父母","福德","田宅","官禄","仆役","迁移","疾厄","身财","子女","夫妻"],"shiErGongNaYin":["桑松木","桑松木","金箔金","金箔金","佛灯火","佛灯火","天河水","天河水","大驿土","大驿土","钗钏金","钗钏金"],"xing_se":[["#7f1f8b","#1800ea","#1800ea","#1800ea","#606eab","#606eab","#606eab","#606eab"],["#1800ea","#1800ea","#1800ea","#606eab","#606eab","#606eab"],["#7f1f8b","#1800ea","#606eab","#606eab","#606eab"],["#1800ea","#606eab","#606eab","#606eab","#606eab","#606eab"],["#7f1f8b","#606eab","#606eab","#606eab"],["#7f1f8b","#1800ea","#1800ea","#606eab","#606eab","#606eab","#606eab","#606eab"],["#7f1f8b","#7f1f8b","#606eab","#606eab","#606eab","#606eab","#606eab"],["#7f1f8b","#7f1f8b","#606eab","#606eab","#606eab"],["#7f1f8b","#1800ea","#606eab","#606eab","#606eab","#606eab","#606eab"],["#7f1f8b","#7f1f8b","#1800ea","#606eab","#606eab","#606eab","#606eab","#606eab","#606eab"],["#7f1f8b","#7f1f8b","#1800ea","#606eab","#606eab","#606eab"],["#7f1f8b","#1800ea","#606eab","#606eab","#606eab","#606eab"]],"xing":[["七杀+旺","文昌+得","火星+陷","擎羊+陷","封诰","龙池","大禄","大鸾"],["左辅+庙+科","右弼+庙","地空+陷","天喜","大羊","月德"],["廉贞+庙","文曲+平","天虚","凤阁","命马"],["天魁+庙","恩光","截空","大昌","大魁","大哭"],["破军+旺","台辅","解神","蜚廉"],["天同+庙","天马+平","天钺+旺","三台","天贵","天寿","大马","大钺"],["武曲+旺+忌","天府+旺","天伤","天刑","天月","天福","大喜"],["太阳+得","太阴+陷","红鸾","寡宿","大火"],["贪狼+平","铃星+陷","天使","天巫","阴煞","大铃","大空"],["天机+旺","巨门+庙","地劫+平","八座","天空","破碎","天才","大虚","天厨"],["紫微+得+权","天相+得","陀罗+庙","天姚","天官","天哭"],["天梁+陷+禄","禄存+庙","孤辰","旬空","大曲","大陀"]],"jiangQian":["将星","攀鞍","岁驿","息神","华盖","劫煞","灾煞","天煞","指背","咸池","月煞","亡神"],"wuXingJu":"木三局","shengNianNaYin":"剑锋金","yinyang":"阳男","xiaoXian":["3 15 27 39 51 63 75 87 99 111","4 16 28 40 52 64 76 88 100 112","5 17 29 41 53 65 77 89 101 113","6 18 30 42 54 66 78 90 102 114","7 19 31 43 55 67 79 91 103 115","8 20 32 44 56 68 80 92 104 116","9 21 33 45 57 69 81 93 105 117","10 22 34 46 58 70 82 94 106 118","11 23 35 47 59 71 83 95 107 119","12 24 36 48 60 72 84 96 108 120","1 13 25 37 49 61 73 85 97 109","2 14 26 38 50 62 74 86 98 110"],"solar_birthday":"1992年11月23日 戌时","liuDou":"丑","daXian":["113-122","3-12","13-22","23-32","33-42","43-52","53-62","63-72","73-82","83-92","93-102","103-112"],"baZi":["壬申","辛亥","癸卯","壬戌"],"lunar_birthday":"一九九二年十月廿九 戌时","shiErGongTianGan":["壬","癸","壬","癸","甲","乙","丙","丁","戊","己","庚","辛"],"mingJuNaYin":"桑松木","wuXingChangSheng":["沐浴","冠带","临官","帝旺","衰","病","死","墓","绝","胎","养","长生"],"mingSite":"1","suiQian":["官符","小耗","大耗","龙德","白虎","天德","吊客","病符","岁建","晦气","丧门","贯索"],"name":"哦哟喂","ziDou":"丑","age":"29","boShi":["力士","青龙","小耗","将军","奏书","飞廉","喜神","病伏","大耗","伏兵","官伏","博士"],"shengXiao":"猴"}]
         */

        private String mingpan_id;
        private List<MingpanBaziBean> mingpan_bazi;
        private List<MingpanXingmingBean> mingpan_xingming;
        private List<MingpanZiweiBean> mingpan_ziwei;

        public String getMingpan_id() {
            return mingpan_id;
        }

        public void setMingpan_id(String mingpan_id) {
            this.mingpan_id = mingpan_id;
        }

        public List<MingpanBaziBean> getMingpan_bazi() {
            return mingpan_bazi;
        }

        public void setMingpan_bazi(List<MingpanBaziBean> mingpan_bazi) {
            this.mingpan_bazi = mingpan_bazi;
        }

        public List<MingpanXingmingBean> getMingpan_xingming() {
            return mingpan_xingming;
        }

        public void setMingpan_xingming(List<MingpanXingmingBean> mingpan_xingming) {
            this.mingpan_xingming = mingpan_xingming;
        }

        public List<MingpanZiweiBean> getMingpan_ziwei() {
            return mingpan_ziwei;
        }

        public void setMingpan_ziwei(List<MingpanZiweiBean> mingpan_ziwei) {
            this.mingpan_ziwei = mingpan_ziwei;
        }

        public static class MingpanBaziBean  implements Serializable {
            /**
             * msg : 暂无数据
             */

            private String msg;

            public String getMsg() {
                return msg;
            }

            public void setMsg(String msg) {
                this.msg = msg;
            }
        }

        public static class MingpanXingmingBean  implements Serializable {
            /**
             * msg : 暂无数据
             */

            private String msg;

            public String getMsg() {
                return msg;
            }

            public void setMsg(String msg) {
                this.msg = msg;
            }
        }

        public static class MingpanZiweiBean  implements Serializable {
            /**
             * mingZhu : 巨门
             * solarTermsbaZi : ["壬申","辛亥","癸卯","壬戌"]
             * shenZhu : 天梁
             * shiErGong : ["兄弟","命宫","父母","福德","田宅","官禄","仆役","迁移","疾厄","身财","子女","夫妻"]
             * shiErGongNaYin : ["桑松木","桑松木","金箔金","金箔金","佛灯火","佛灯火","天河水","天河水","大驿土","大驿土","钗钏金","钗钏金"]
             * xing_se : [["#7f1f8b","#1800ea","#1800ea","#1800ea","#606eab","#606eab","#606eab","#606eab"],["#1800ea","#1800ea","#1800ea","#606eab","#606eab","#606eab"],["#7f1f8b","#1800ea","#606eab","#606eab","#606eab"],["#1800ea","#606eab","#606eab","#606eab","#606eab","#606eab"],["#7f1f8b","#606eab","#606eab","#606eab"],["#7f1f8b","#1800ea","#1800ea","#606eab","#606eab","#606eab","#606eab","#606eab"],["#7f1f8b","#7f1f8b","#606eab","#606eab","#606eab","#606eab","#606eab"],["#7f1f8b","#7f1f8b","#606eab","#606eab","#606eab"],["#7f1f8b","#1800ea","#606eab","#606eab","#606eab","#606eab","#606eab"],["#7f1f8b","#7f1f8b","#1800ea","#606eab","#606eab","#606eab","#606eab","#606eab","#606eab"],["#7f1f8b","#7f1f8b","#1800ea","#606eab","#606eab","#606eab"],["#7f1f8b","#1800ea","#606eab","#606eab","#606eab","#606eab"]]
             * xing : [["七杀+旺","文昌+得","火星+陷","擎羊+陷","封诰","龙池","大禄","大鸾"],["左辅+庙+科","右弼+庙","地空+陷","天喜","大羊","月德"],["廉贞+庙","文曲+平","天虚","凤阁","命马"],["天魁+庙","恩光","截空","大昌","大魁","大哭"],["破军+旺","台辅","解神","蜚廉"],["天同+庙","天马+平","天钺+旺","三台","天贵","天寿","大马","大钺"],["武曲+旺+忌","天府+旺","天伤","天刑","天月","天福","大喜"],["太阳+得","太阴+陷","红鸾","寡宿","大火"],["贪狼+平","铃星+陷","天使","天巫","阴煞","大铃","大空"],["天机+旺","巨门+庙","地劫+平","八座","天空","破碎","天才","大虚","天厨"],["紫微+得+权","天相+得","陀罗+庙","天姚","天官","天哭"],["天梁+陷+禄","禄存+庙","孤辰","旬空","大曲","大陀"]]
             * jiangQian : ["将星","攀鞍","岁驿","息神","华盖","劫煞","灾煞","天煞","指背","咸池","月煞","亡神"]
             * wuXingJu : 木三局
             * shengNianNaYin : 剑锋金
             * yinyang : 阳男
             * xiaoXian : ["3 15 27 39 51 63 75 87 99 111","4 16 28 40 52 64 76 88 100 112","5 17 29 41 53 65 77 89 101 113","6 18 30 42 54 66 78 90 102 114","7 19 31 43 55 67 79 91 103 115","8 20 32 44 56 68 80 92 104 116","9 21 33 45 57 69 81 93 105 117","10 22 34 46 58 70 82 94 106 118","11 23 35 47 59 71 83 95 107 119","12 24 36 48 60 72 84 96 108 120","1 13 25 37 49 61 73 85 97 109","2 14 26 38 50 62 74 86 98 110"]
             * solar_birthday : 1992年11月23日 戌时
             * liuDou : 丑
             * daXian : ["113-122","3-12","13-22","23-32","33-42","43-52","53-62","63-72","73-82","83-92","93-102","103-112"]
             * baZi : ["壬申","辛亥","癸卯","壬戌"]
             * lunar_birthday : 一九九二年十月廿九 戌时
             * shiErGongTianGan : ["壬","癸","壬","癸","甲","乙","丙","丁","戊","己","庚","辛"]
             * mingJuNaYin : 桑松木
             * wuXingChangSheng : ["沐浴","冠带","临官","帝旺","衰","病","死","墓","绝","胎","养","长生"]
             * mingSite : 1
             * suiQian : ["官符","小耗","大耗","龙德","白虎","天德","吊客","病符","岁建","晦气","丧门","贯索"]
             * name : 哦哟喂
             * ziDou : 丑
             * age : 29
             * boShi : ["力士","青龙","小耗","将军","奏书","飞廉","喜神","病伏","大耗","伏兵","官伏","博士"]
             * shengXiao : 猴
             */

            private String mingZhu;
            private String shenZhu;
            private String wuXingJu;
            private String shengNianNaYin;
            private String yinyang;
            private String solar_birthday;
            private String liuDou;
            private String lunar_birthday;
            private String mingJuNaYin;
            private String mingSite;
            private String name;
            private String ziDou;
            private String age;
            private String shengXiao;
            private List<String> solarTermsbaZi;
            private List<String> shiErGong;
            private List<String> shiErGongNaYin;
            private List<List<String>> xing_se;
            private List<List<String>> xing;
            private List<String> jiangQian;
            private List<String> xiaoXian;
            private List<String> daXian;
            private List<String> baZi;
            private List<String> shiErGongTianGan;
            private List<String> wuXingChangSheng;
            private List<String> suiQian;
            private List<String> boShi;

            public String getMingZhu() {
                return mingZhu;
            }

            public void setMingZhu(String mingZhu) {
                this.mingZhu = mingZhu;
            }

            public String getShenZhu() {
                return shenZhu;
            }

            public void setShenZhu(String shenZhu) {
                this.shenZhu = shenZhu;
            }

            public String getWuXingJu() {
                return wuXingJu;
            }

            public void setWuXingJu(String wuXingJu) {
                this.wuXingJu = wuXingJu;
            }

            public String getShengNianNaYin() {
                return shengNianNaYin;
            }

            public void setShengNianNaYin(String shengNianNaYin) {
                this.shengNianNaYin = shengNianNaYin;
            }

            public String getYinyang() {
                return yinyang;
            }

            public void setYinyang(String yinyang) {
                this.yinyang = yinyang;
            }

            public String getSolar_birthday() {
                return solar_birthday;
            }

            public void setSolar_birthday(String solar_birthday) {
                this.solar_birthday = solar_birthday;
            }

            public String getLiuDou() {
                return liuDou;
            }

            public void setLiuDou(String liuDou) {
                this.liuDou = liuDou;
            }

            public String getLunar_birthday() {
                return lunar_birthday;
            }

            public void setLunar_birthday(String lunar_birthday) {
                this.lunar_birthday = lunar_birthday;
            }

            public String getMingJuNaYin() {
                return mingJuNaYin;
            }

            public void setMingJuNaYin(String mingJuNaYin) {
                this.mingJuNaYin = mingJuNaYin;
            }

            public String getMingSite() {
                return mingSite;
            }

            public void setMingSite(String mingSite) {
                this.mingSite = mingSite;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getZiDou() {
                return ziDou;
            }

            public void setZiDou(String ziDou) {
                this.ziDou = ziDou;
            }

            public String getAge() {
                return age;
            }

            public void setAge(String age) {
                this.age = age;
            }

            public String getShengXiao() {
                return shengXiao;
            }

            public void setShengXiao(String shengXiao) {
                this.shengXiao = shengXiao;
            }

            public List<String> getSolarTermsbaZi() {
                return solarTermsbaZi;
            }

            public void setSolarTermsbaZi(List<String> solarTermsbaZi) {
                this.solarTermsbaZi = solarTermsbaZi;
            }

            public List<String> getShiErGong() {
                return shiErGong;
            }

            public void setShiErGong(List<String> shiErGong) {
                this.shiErGong = shiErGong;
            }

            public List<String> getShiErGongNaYin() {
                return shiErGongNaYin;
            }

            public void setShiErGongNaYin(List<String> shiErGongNaYin) {
                this.shiErGongNaYin = shiErGongNaYin;
            }

            public List<List<String>> getXing_se() {
                return xing_se;
            }

            public void setXing_se(List<List<String>> xing_se) {
                this.xing_se = xing_se;
            }

            public List<List<String>> getXing() {
                return xing;
            }

            public void setXing(List<List<String>> xing) {
                this.xing = xing;
            }

            public List<String> getJiangQian() {
                return jiangQian;
            }

            public void setJiangQian(List<String> jiangQian) {
                this.jiangQian = jiangQian;
            }

            public List<String> getXiaoXian() {
                return xiaoXian;
            }

            public void setXiaoXian(List<String> xiaoXian) {
                this.xiaoXian = xiaoXian;
            }

            public List<String> getDaXian() {
                return daXian;
            }

            public void setDaXian(List<String> daXian) {
                this.daXian = daXian;
            }

            public List<String> getBaZi() {
                return baZi;
            }

            public void setBaZi(List<String> baZi) {
                this.baZi = baZi;
            }

            public List<String> getShiErGongTianGan() {
                return shiErGongTianGan;
            }

            public void setShiErGongTianGan(List<String> shiErGongTianGan) {
                this.shiErGongTianGan = shiErGongTianGan;
            }

            public List<String> getWuXingChangSheng() {
                return wuXingChangSheng;
            }

            public void setWuXingChangSheng(List<String> wuXingChangSheng) {
                this.wuXingChangSheng = wuXingChangSheng;
            }

            public List<String> getSuiQian() {
                return suiQian;
            }

            public void setSuiQian(List<String> suiQian) {
                this.suiQian = suiQian;
            }

            public List<String> getBoShi() {
                return boShi;
            }

            public void setBoShi(List<String> boShi) {
                this.boShi = boShi;
            }
        }
    }
}
