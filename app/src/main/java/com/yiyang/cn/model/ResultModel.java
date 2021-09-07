package com.yiyang.cn.model;

import java.util.List;

public class ResultModel {
    /**
     * answer : {"text":"正在为您操作","type":"T"}
     * category : OS8569425439.ceshi
     * data : {"result":null}
     * intentType : custom
     * rc : 0
     * semantic : [{"entrypoint":"ent","hazard":false,"intent":"dengl","score":1,"slots":[{"begin":0,"end":2,"name":"caozuo","normValue":"打开","value":"打开"},{"begin":2,"end":4,"name":"weizhi","normValue":"客厅","value":"客厅"},{"begin":4,"end":5,"name":"shebei","normValue":"灯","value":"灯"}],"template":"{caozuo}{weizhi}{shebei}"}]
     * semanticType : 0
     * service : OS8569425439.ceshi
     * sessionIsEnd : false
     * shouldEndSession : true
     * sid : atn04b05556@dx00011341fd48a11100
     * state : null
     * text : 打开客厅灯
     * uuid : atn04b05556@dx00011341fd48a11100
     * vendor : OS8569425439
     * version : 7.0
     * voice_answer : [{"content":"正在为您操作","type":"TTS"}]
     */

    private AnswerBean answer;
    private String category;
    private DataBean data;
    private String intentType;
    private int rc;
    private int semanticType;
    private String service;
    private boolean sessionIsEnd;
    private boolean shouldEndSession;
    private String sid;
    private Object state;
    private String text;
    private String uuid;
    private String vendor;
    private String version;
    private List<SemanticBean> semantic;
    private List<VoiceAnswerBean> voice_answer;

    public AnswerBean getAnswer() {
        return answer;
    }

    public void setAnswer(AnswerBean answer) {
        this.answer = answer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getIntentType() {
        return intentType;
    }

    public void setIntentType(String intentType) {
        this.intentType = intentType;
    }

    public int getRc() {
        return rc;
    }

    public void setRc(int rc) {
        this.rc = rc;
    }

    public int getSemanticType() {
        return semanticType;
    }

    public void setSemanticType(int semanticType) {
        this.semanticType = semanticType;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public boolean isSessionIsEnd() {
        return sessionIsEnd;
    }

    public void setSessionIsEnd(boolean sessionIsEnd) {
        this.sessionIsEnd = sessionIsEnd;
    }

    public boolean isShouldEndSession() {
        return shouldEndSession;
    }

    public void setShouldEndSession(boolean shouldEndSession) {
        this.shouldEndSession = shouldEndSession;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public Object getState() {
        return state;
    }

    public void setState(Object state) {
        this.state = state;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<SemanticBean> getSemantic() {
        return semantic;
    }

    public void setSemantic(List<SemanticBean> semantic) {
        this.semantic = semantic;
    }

    public List<VoiceAnswerBean> getVoice_answer() {
        return voice_answer;
    }

    public void setVoice_answer(List<VoiceAnswerBean> voice_answer) {
        this.voice_answer = voice_answer;
    }

    public static class AnswerBean {
        /**
         * text : 正在为您操作
         * type : T
         */

        private String text;
        private String type;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class DataBean {
        /**
         * result : null
         */

        private Object result;

        public Object getResult() {
            return result;
        }

        public void setResult(Object result) {
            this.result = result;
        }
    }

    public static class SemanticBean {
        /**
         * entrypoint : ent
         * hazard : false
         * intent : dengl
         * score : 1
         * slots : [{"begin":0,"end":2,"name":"caozuo","normValue":"打开","value":"打开"},{"begin":2,"end":4,"name":"weizhi","normValue":"客厅","value":"客厅"},{"begin":4,"end":5,"name":"shebei","normValue":"灯","value":"灯"}]
         * template : {caozuo}{weizhi}{shebei}
         */

        private String entrypoint;
        private boolean hazard;
        private String intent;
        private float score;
        private String template;
        private List<SlotsBean> slots;

        public String getEntrypoint() {
            return entrypoint;
        }

        public void setEntrypoint(String entrypoint) {
            this.entrypoint = entrypoint;
        }

        public boolean isHazard() {
            return hazard;
        }

        public void setHazard(boolean hazard) {
            this.hazard = hazard;
        }

        public String getIntent() {
            return intent;
        }

        public void setIntent(String intent) {
            this.intent = intent;
        }

        public float getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getTemplate() {
            return template;
        }

        public void setTemplate(String template) {
            this.template = template;
        }

        public List<SlotsBean> getSlots() {
            return slots;
        }

        public void setSlots(List<SlotsBean> slots) {
            this.slots = slots;
        }

        public static class SlotsBean {
            /**
             * begin : 0
             * end : 2
             * name : caozuo
             * normValue : 打开
             * value : 打开
             */

            private int begin;
            private int end;
            private String name;
            private String normValue;
            private String value;

            public int getBegin() {
                return begin;
            }

            public void setBegin(int begin) {
                this.begin = begin;
            }

            public int getEnd() {
                return end;
            }

            public void setEnd(int end) {
                this.end = end;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNormValue() {
                return normValue;
            }

            public void setNormValue(String normValue) {
                this.normValue = normValue;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }

    public static class VoiceAnswerBean {
        /**
         * content : 正在为您操作
         * type : TTS
         */

        private String content;
        private String type;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
