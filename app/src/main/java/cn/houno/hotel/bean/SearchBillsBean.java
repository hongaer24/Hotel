package cn.houno.hotel.bean;

import java.util.List;

/**
 * 房态更改查询
 * Created by qzc on 2017-05-12.
 */

public class SearchBillsBean {

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
         * id : 389CE657-0343-4195-BC0C-62C661B50301
         * workorderid : 3CEAE706-738A-4F94-9BE8-7124EEF9607A
         * workorderidcode : 07
         * roomstatedayid : 6B231BAF-DA30-4500-A00D-23ABE5DA1F5E
         * roomidcode : A336
         * roomname : 高级双床房
         * sys_roomstatecode : OD
         * newroomstatecode : OC
         * roomtypeidcode : ST
         * roomtypename : 高级双床房
         * isfinish : 1
         * workloginname : LF0010020
         * workname : 邱志超
         * hotelidcode : LF001
         * companyinfoidcode : BOTAO
         * loginname : lf0010020
         * businessday : 2016-11-29 00:00:00.000
         * createdatetime : 2017-05-11 15:06:02.000
         * finishdatetime : 2017-05-11 16:30:31.000
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
