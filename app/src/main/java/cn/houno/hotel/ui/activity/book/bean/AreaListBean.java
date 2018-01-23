package cn.houno.hotel.ui.activity.book.bean;

import java.util.List;

/**
 * 国家列表
 * Created by qzc on 2017-06-08.
 */

public class AreaListBean {


    /**
     * info : [{"name":"中华人民共和国","idcode":"CN","enname":"China"},{"name":"美国","idcode":"USA","enname":"the United States of America,"},{"name":"日本","idcode":"JP","enname":"Japan"},{"name":"俄罗斯","idcode":"RU","enname":"Russia"},{"name":"葡萄牙","idcode":"PT","enname":"Portugal"},{"name":"英国","idcode":"UK","enname":"The United Kingdom of Great Britain and Northern I"},{"name":"法国","idcode":"FR","enname":"The French Republic"},{"name":"韩国","idcode":"KOR","enname":"Republic of Korea"},{"name":"西班牙","idcode":"ES","enname":"The Kingdom of Spain"},{"name":"德国","idcode":"DE","enname":"The Federal Republic of Germany"}]
     * status : 2
     * msg : 获取数据成功
     */

    private int status;
    private String msg;
    private List<InfoBean> info;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<InfoBean> getInfo() {
        return info;
    }

    public void setInfo(List<InfoBean> info) {
        this.info = info;
    }

    public static class InfoBean {
        /**
         * name : 中华人民共和国
         * idcode : CN
         * enname : China
         */

        private String name;
        private String idcode;
        private String enname;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIdcode() {
            return idcode;
        }

        public void setIdcode(String idcode) {
            this.idcode = idcode;
        }

        public String getEnname() {
            return enname;
        }

        public void setEnname(String enname) {
            this.enname = enname;
        }
    }
}
