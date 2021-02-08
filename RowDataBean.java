package cn.sccl.app.module.firstaid.bean;

import java.io.Serializable;
import java.util.List;

public class RowDataBean implements Serializable {

    private String id;//主键
    private List<ColumnDataBean> detailDataBean;//详细描述
    private List<ColumnShortDataBean> shortDataBean;//简称

    public List<ColumnShortDataBean> getShortDataBean() {
        return shortDataBean;
    }

    public void setShortDataBean(List<ColumnShortDataBean> shortDataBean) {
        this.shortDataBean = shortDataBean;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ColumnDataBean> getDataBean() {
        return detailDataBean;
    }

    public void setDataBean(List<ColumnDataBean> dataBean) {
        this.detailDataBean = dataBean;
    }

    public static class ColumnDataBean implements Serializable {
        private String id;
        private String pId;
        private String language;//语种
        private String value;//值

        public ColumnDataBean(String id, String pId) {
            this.id = id;
            this.pId = pId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getpId() {
            return pId;
        }

        public void setpId(String pId) {
            this.pId = pId;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class ColumnShortDataBean implements Serializable {
        private String id;
        private String pId;
        private String language="chinese";//语种
        private String value;//值
        private boolean isExpand;
        private int position;

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public ColumnShortDataBean(String id, String pId) {
            this.id = id;
            this.pId = pId;
        }

        public ColumnShortDataBean(String id, String pId,String value) {
            this.id = id;
            this.pId = pId;
            this.value = value;
        }

        public boolean isExpand() {
            return isExpand;
        }

        public void setExpand(boolean expand) {
            isExpand = expand;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getpId() {
            return pId;
        }

        public void setpId(String pId) {
            this.pId = pId;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}