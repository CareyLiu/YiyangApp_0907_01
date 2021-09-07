package com.yiyang.cn.activity.tuya_device.device.model;

public class DeviceGaojing {


    /**
     * i18nData : {"name":{"en":"Door Alarm","zh_CN":"开门报警","zh":"开门报警"},"content":{"en":"${device} was opened,please check in time!","zh_CN":"${device} 被打开，请请及时检查！","zh":"${device} 被打开，请请及时检查！"}}
     * enabled : true
     * isLogicRule : false
     * boundForPanel : false
     * id : 2cRVi8tOGAbQLFOZ
     * stickyOnTop : false
     * newLocalScene : false
     * boundForWiFiPanel : false
     * iotAutoAlarm : false
     * localLinkage : false
     * name : 开门报警
     * auditStatus : 1
     */

    private I18nDataBean i18nData;
    private boolean enabled;
    private boolean isLogicRule;
    private boolean boundForPanel;
    private String id;
    private boolean stickyOnTop;
    private boolean newLocalScene;
    private boolean boundForWiFiPanel;
    private boolean iotAutoAlarm;
    private boolean localLinkage;
    private String name;
    private int auditStatus;

    public I18nDataBean getI18nData() {
        return i18nData;
    }

    public void setI18nData(I18nDataBean i18nData) {
        this.i18nData = i18nData;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isIsLogicRule() {
        return isLogicRule;
    }

    public void setIsLogicRule(boolean isLogicRule) {
        this.isLogicRule = isLogicRule;
    }

    public boolean isBoundForPanel() {
        return boundForPanel;
    }

    public void setBoundForPanel(boolean boundForPanel) {
        this.boundForPanel = boundForPanel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isStickyOnTop() {
        return stickyOnTop;
    }

    public void setStickyOnTop(boolean stickyOnTop) {
        this.stickyOnTop = stickyOnTop;
    }

    public boolean isNewLocalScene() {
        return newLocalScene;
    }

    public void setNewLocalScene(boolean newLocalScene) {
        this.newLocalScene = newLocalScene;
    }

    public boolean isBoundForWiFiPanel() {
        return boundForWiFiPanel;
    }

    public void setBoundForWiFiPanel(boolean boundForWiFiPanel) {
        this.boundForWiFiPanel = boundForWiFiPanel;
    }

    public boolean isIotAutoAlarm() {
        return iotAutoAlarm;
    }

    public void setIotAutoAlarm(boolean iotAutoAlarm) {
        this.iotAutoAlarm = iotAutoAlarm;
    }

    public boolean isLocalLinkage() {
        return localLinkage;
    }

    public void setLocalLinkage(boolean localLinkage) {
        this.localLinkage = localLinkage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(int auditStatus) {
        this.auditStatus = auditStatus;
    }

    public static class I18nDataBean {
        /**
         * name : {"en":"Door Alarm","zh_CN":"开门报警","zh":"开门报警"}
         * content : {"en":"${device} was opened,please check in time!","zh_CN":"${device} 被打开，请请及时检查！","zh":"${device} 被打开，请请及时检查！"}
         */

        private NameBean name;
        private ContentBean content;

        public NameBean getName() {
            return name;
        }

        public void setName(NameBean name) {
            this.name = name;
        }

        public ContentBean getContent() {
            return content;
        }

        public void setContent(ContentBean content) {
            this.content = content;
        }

        public static class NameBean {
            /**
             * en : Door Alarm
             * zh_CN : 开门报警
             * zh : 开门报警
             */

            private String en;
            private String zh_CN;
            private String zh;

            public String getEn() {
                return en;
            }

            public void setEn(String en) {
                this.en = en;
            }

            public String getZh_CN() {
                return zh_CN;
            }

            public void setZh_CN(String zh_CN) {
                this.zh_CN = zh_CN;
            }

            public String getZh() {
                return zh;
            }

            public void setZh(String zh) {
                this.zh = zh;
            }
        }

        public static class ContentBean {
            /**
             * en : ${device} was opened,please check in time!
             * zh_CN : ${device} 被打开，请请及时检查！
             * zh : ${device} 被打开，请请及时检查！
             */

            private String en;
            private String zh_CN;
            private String zh;

            public String getEn() {
                return en;
            }

            public void setEn(String en) {
                this.en = en;
            }

            public String getZh_CN() {
                return zh_CN;
            }

            public void setZh_CN(String zh_CN) {
                this.zh_CN = zh_CN;
            }

            public String getZh() {
                return zh;
            }

            public void setZh(String zh) {
                this.zh = zh;
            }
        }
    }
}
