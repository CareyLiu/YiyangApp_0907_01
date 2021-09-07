package com.yiyang.cn.activity.tuya_device.device.model;

import java.util.List;

public class DpModel {

    /**
     * dpc : []
     * dps : [{"dpId":101,"timeStamp":1606979082,"timeStr":"2020-12-03 15:04:42","value":"false"},{"dpId":101,"timeStamp":1606979080,"timeStr":"2020-12-03 15:04:40","value":"true"},{"dpId":101,"timeStamp":1606979078,"timeStr":"2020-12-03 15:04:38","value":"false"},{"dpId":101,"timeStamp":1606979076,"timeStr":"2020-12-03 15:04:36","value":"true"},{"dpId":101,"timeStamp":1606979070,"timeStr":"2020-12-03 15:04:30","value":"false"},{"dpId":101,"timeStamp":1606979028,"timeStr":"2020-12-03 15:03:48","value":"true"},{"dpId":101,"timeStamp":1606978978,"timeStr":"2020-12-03 15:02:58","value":"false"},{"dpId":101,"timeStamp":1606978916,"timeStr":"2020-12-03 15:01:56","value":"true"},{"dpId":101,"timeStamp":1606978910,"timeStr":"2020-12-03 15:01:50","value":"false"},{"dpId":101,"timeStamp":1606978896,"timeStr":"2020-12-03 15:01:36","value":"true"}]
     * hasNext : true
     * total : 21
     */

    private boolean hasNext;
    private int total;
    private List<?> dpc;
    private List<DpsBean> dps;

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<?> getDpc() {
        return dpc;
    }

    public void setDpc(List<?> dpc) {
        this.dpc = dpc;
    }

    public List<DpsBean> getDps() {
        return dps;
    }

    public void setDps(List<DpsBean> dps) {
        this.dps = dps;
    }

    public static class DpsBean {
        /**
         * dpId : 101
         * timeStamp : 1606979082
         * timeStr : 2020-12-03 15:04:42
         * value : false
         */

        private int dpId;
        private int timeStamp;
        private String timeStr;
        private String value;

        public int getDpId() {
            return dpId;
        }

        public void setDpId(int dpId) {
            this.dpId = dpId;
        }

        public int getTimeStamp() {
            return timeStamp;
        }

        public void setTimeStamp(int timeStamp) {
            this.timeStamp = timeStamp;
        }

        public String getTimeStr() {
            return timeStr;
        }

        public void setTimeStr(String timeStr) {
            this.timeStr = timeStr;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
