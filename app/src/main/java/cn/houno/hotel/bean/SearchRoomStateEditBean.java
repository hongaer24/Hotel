package cn.houno.hotel.bean;

import java.util.List;

/**
 * Created by qzc on 2017-05-12.
 */

public class SearchRoomStateEditBean {


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
         * id : 2EB54815-5D5A-DA29-81E7-46B4C6EC3A9A
         * roomstatedayid : 6B231BAF-DA30-4500-A00D-23ABE5DA1F5E
         * roomcode : A336
         * oldsys_roomstatecode : OC
         * oldsys_roomsys_color : 16737894
         * sys_roomstatecode : OD
         * sys_roomsys_color : 4210752
         * businessday : 2016-11-29 00:00:00.000
         * hotelidcode : LF001
         * companyinfoidcode : BOTAO
         * loginname : LF0010020
         * createdatetime : 2017-05-11 14:55:55.000
         * row_number : 1
         */

        private String id;
        private String roomstatedayid;
        private String roomcode;
        private String oldsys_roomstatecode;
        private String oldsys_roomsys_color;
        private String sys_roomstatecode;
        private String sys_roomsys_color;
        private String businessday;
        private String hotelidcode;
        private String companyinfoidcode;
        private String loginname;
        private String createdatetime;
        private String row_number;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRoomstatedayid() {
            return roomstatedayid;
        }

        public void setRoomstatedayid(String roomstatedayid) {
            this.roomstatedayid = roomstatedayid;
        }

        public String getRoomcode() {
            return roomcode;
        }

        public void setRoomcode(String roomcode) {
            this.roomcode = roomcode;
        }

        public String getOldsys_roomstatecode() {
            return oldsys_roomstatecode;
        }

        public void setOldsys_roomstatecode(String oldsys_roomstatecode) {
            this.oldsys_roomstatecode = oldsys_roomstatecode;
        }

        public String getOldsys_roomsys_color() {
            return oldsys_roomsys_color;
        }

        public void setOldsys_roomsys_color(String oldsys_roomsys_color) {
            this.oldsys_roomsys_color = oldsys_roomsys_color;
        }

        public String getSys_roomstatecode() {
            return sys_roomstatecode;
        }

        public void setSys_roomstatecode(String sys_roomstatecode) {
            this.sys_roomstatecode = sys_roomstatecode;
        }

        public String getSys_roomsys_color() {
            return sys_roomsys_color;
        }

        public void setSys_roomsys_color(String sys_roomsys_color) {
            this.sys_roomsys_color = sys_roomsys_color;
        }

        public String getBusinessday() {
            return businessday;
        }

        public void setBusinessday(String businessday) {
            this.businessday = businessday;
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

        public String getLoginname() {
            return loginname;
        }

        public void setLoginname(String loginname) {
            this.loginname = loginname;
        }

        public String getCreatedatetime() {
            return createdatetime;
        }

        public void setCreatedatetime(String createdatetime) {
            this.createdatetime = createdatetime;
        }

        public String getRow_number() {
            return row_number;
        }

        public void setRow_number(String row_number) {
            this.row_number = row_number;
        }
    }
}
