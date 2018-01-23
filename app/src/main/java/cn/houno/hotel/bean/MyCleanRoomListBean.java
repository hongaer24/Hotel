package cn.houno.hotel.bean;

import java.util.List;

/**
 * Created by qzc on 2017-05-11.
 */

public class MyCleanRoomListBean {


    /**
     * status : 2
     * info : [{"id":"7B1F4188-F86E-4FF0-BA63-39A07A982336","workorderid":"E4BEFF0E-B777-4C0F-A393-DDB6B3C3A6EE","workorderidcode":"06","roomstatedayid":"1C719975-AD51-4E80-BDF4-928CF71E38D8","roomidcode":"102","roomname":"豪华景观房","sys_roomstatecode":"VD","newroomstatecode":"","roomtypeidcode":"DGVT","roomtypename":"豪华景观房","isfinish":"0","workloginname":"LF0010019","workname":"李伟伟","hotelidcode":"LF001","companyinfoidcode":"BOTAO","loginname":"LF0010019","businessday":"2016-11-29 00:00:00.000","createdatetime":"2017-05-11 09:26:43.000","finishdatetime":null,"status":"1","row_number":"1"}]
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
         * id : 7B1F4188-F86E-4FF0-BA63-39A07A982336
         * workorderid : E4BEFF0E-B777-4C0F-A393-DDB6B3C3A6EE
         * workorderidcode : 06
         * roomstatedayid : 1C719975-AD51-4E80-BDF4-928CF71E38D8
         * roomidcode : 102
         * roomname : 豪华景观房
         * sys_roomstatecode : VD
         * newroomstatecode :
         * roomtypeidcode : DGVT
         * roomtypename : 豪华景观房
         * isfinish : 0
         * workloginname : LF0010019
         * workname : 李伟伟
         * hotelidcode : LF001
         * companyinfoidcode : BOTAO
         * loginname : LF0010019
         * businessday : 2016-11-29 00:00:00.000
         * createdatetime : 2017-05-11 09:26:43.000
         * finishdatetime : null
         * status : 1
         * row_number : 1
         */

        private String id;
        private String workorderid;
        private String workorderidcode;
        private String roomstatedayid;
        private String roomidcode;
        private String roomname;
        private String sys_roomstatecode;
        private String newroomstatecode;
        private String roomtypeidcode;
        private String roomtypename;
        private String isfinish;
        private String workloginname;
        private String workname;
        private String hotelidcode;
        private String companyinfoidcode;
        private String loginname;
        private String businessday;
        private String createdatetime;
        private String finishdatetime;
        private String status;
        private String row_number;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getWorkorderid() {
            return workorderid;
        }

        public void setWorkorderid(String workorderid) {
            this.workorderid = workorderid;
        }

        public String getWorkorderidcode() {
            return workorderidcode;
        }

        public void setWorkorderidcode(String workorderidcode) {
            this.workorderidcode = workorderidcode;
        }

        public String getRoomstatedayid() {
            return roomstatedayid;
        }

        public void setRoomstatedayid(String roomstatedayid) {
            this.roomstatedayid = roomstatedayid;
        }

        public String getRoomidcode() {
            return roomidcode;
        }

        public void setRoomidcode(String roomidcode) {
            this.roomidcode = roomidcode;
        }

        public String getRoomname() {
            return roomname;
        }

        public void setRoomname(String roomname) {
            this.roomname = roomname;
        }

        public String getSys_roomstatecode() {
            return sys_roomstatecode;
        }

        public void setSys_roomstatecode(String sys_roomstatecode) {
            this.sys_roomstatecode = sys_roomstatecode;
        }

        public String getNewroomstatecode() {
            return newroomstatecode;
        }

        public void setNewroomstatecode(String newroomstatecode) {
            this.newroomstatecode = newroomstatecode;
        }

        public String getRoomtypeidcode() {
            return roomtypeidcode;
        }

        public void setRoomtypeidcode(String roomtypeidcode) {
            this.roomtypeidcode = roomtypeidcode;
        }

        public String getRoomtypename() {
            return roomtypename;
        }

        public void setRoomtypename(String roomtypename) {
            this.roomtypename = roomtypename;
        }

        public String getIsfinish() {
            return isfinish;
        }

        public void setIsfinish(String isfinish) {
            this.isfinish = isfinish;
        }

        public String getWorkloginname() {
            return workloginname;
        }

        public void setWorkloginname(String workloginname) {
            this.workloginname = workloginname;
        }

        public String getWorkname() {
            return workname;
        }

        public void setWorkname(String workname) {
            this.workname = workname;
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

        public String getBusinessday() {
            return businessday;
        }

        public void setBusinessday(String businessday) {
            this.businessday = businessday;
        }

        public String getCreatedatetime() {
            return createdatetime;
        }

        public void setCreatedatetime(String createdatetime) {
            this.createdatetime = createdatetime;
        }

        public String getFinishdatetime() {
            return finishdatetime;
        }

        public void setFinishdatetime(String finishdatetime) {
            this.finishdatetime = finishdatetime;
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
