package cn.houno.hotel.bean;

import java.util.List;

/**
 * Created by Administrator on 2017-06-28.
 */

public class RoomSceneryBean {


    /**
     * status : 2
     * info : [{"id":"1","name":"海景","idcode":"SEA","info":"123","hotelidcode":"LF001","companyinfoidcode":"BOTAO","createdatetime":"2016-11-30 16:17:25.000","status":"1","row_number":"1"},{"id":"2","name":"园景","idcode":"GV","info":"","hotelidcode":"LF001","companyinfoidcode":"BOTAO","createdatetime":"2016-12-08 12:20:39.000","status":"1","row_number":"2"},{"id":"080F2E0C-4E13-4C15-9352-3E2BA2C8E4B8","name":"山景","idcode":"SJ","info":"吴弟弟的","hotelidcode":"LF001","companyinfoidcode":"BOTAO","createdatetime":"2017-03-25 21:51:35.000","status":"1","row_number":"3"}]
     * msg : 数据获取成功
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
         * id : 1
         * name : 海景
         * idcode : SEA
         * info : 123
         * hotelidcode : LF001
         * companyinfoidcode : BOTAO
         * createdatetime : 2016-11-30 16:17:25.000
         * status : 1
         * row_number : 1
         */

        private String id;
        private String name;
        private String idcode;
        private String info;
        private String hotelidcode;
        private String companyinfoidcode;
        private String createdatetime;
        private String status;
        private String row_number;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

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

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getHotelidcode() {
            return hotelidcode;
        }

        public void setHotelidcode(String hotelidcode) {
            this.hotelidcode = hotelidcode;
        }

        public String getCompanyinfoidcode() {
            return companyinfoidcode;
        }

        public void setCompanyinfoidcode(String companyinfoidcode) {
            this.companyinfoidcode = companyinfoidcode;
        }

        public String getCreatedatetime() {
            return createdatetime;
        }

        public void setCreatedatetime(String createdatetime) {
            this.createdatetime = createdatetime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getRow_number() {
            return row_number;
        }

        public void setRow_number(String row_number) {
            this.row_number = row_number;
        }
    }
}
