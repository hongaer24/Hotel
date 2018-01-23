package cn.houno.hotel.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qzc on 2017/5/8 0008.
 */

public class RoomStatusListBean implements Serializable {


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
         * id : 52D65230-3099-425B-91E8-8FF4F826D6A3
         * roomcode : 101
         * sys_roomstatecode : OC
         * sys_roomsys_color : 16737894
         * roomclasscode : MDB
         * roomtypecode : DGVT
         * roomscenerycode : GV
         * floor : 1
         * grid_x : -1
         * grid_y : -1
         * comment : 房号:101MDB/豪华景观房(DGVT)/园景(GV)
         * businessday : 2016-11-29 00:00:00.000
         * hotelidcode : LF001
         * companyinfoidcode : BOTAO
         * status : 1
         * row_number : 1
         * roomstatedaydetailed : {"id":"2209D555-2613-405E-8243-6A1E4BCFE800","roomstatedayid":"52D65230-3099-425B-91E8-8FF4F826D6A3","roomidcode":"101","sys_roomstatecode":"EA","sys_roomsys_color":"16763238","businessday":"2016-11-29 00:00:00.000","loginname":"LF0010007","hotelidcode":"LF001","companyinfoidcode":"BOTAO","createdatetime":"2017-04-07 14:19:31.000","status":"0","row_number":"1"}
         * roomorderinfo : [{"mainname":"张三","begindate":"2016-11-29 00:00:00.000","enddate":"2016-11-30 00:00:00.000","daycount":"1","ratevalues":"300.00","partyid":"0"}]
         */

        private String id;
        private String roomcode;
        private String sys_roomstatecode;
        private String sys_roomsys_color;
        private String roomclasscode;
        private String roomtypecode;
        private String roomscenerycode;
        private String floor;
        private String grid_x;
        private String grid_y;
        private String comment;
        private String businessday;
        private String hotelidcode;
        private String companyinfoidcode;
        private String status;
        private String row_number;
        private RoomstatedaydetailedBean roomstatedaydetailed;
        private List<RoomorderinfoBean> roomorderinfo;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRoomcode() {
            return roomcode;
        }

        public void setRoomcode(String roomcode) {
            this.roomcode = roomcode;
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

        public String getRoomclasscode() {
            return roomclasscode;
        }

        public void setRoomclasscode(String roomclasscode) {
            this.roomclasscode = roomclasscode;
        }

        public String getRoomtypecode() {
            return roomtypecode;
        }

        public void setRoomtypecode(String roomtypecode) {
            this.roomtypecode = roomtypecode;
        }

        public String getRoomscenerycode() {
            return roomscenerycode;
        }

        public void setRoomscenerycode(String roomscenerycode) {
            this.roomscenerycode = roomscenerycode;
        }

        public String getFloor() {
            return floor;
        }

        public void setFloor(String floor) {
            this.floor = floor;
        }

        public String getGrid_x() {
            return grid_x;
        }

        public void setGrid_x(String grid_x) {
            this.grid_x = grid_x;
        }

        public String getGrid_y() {
            return grid_y;
        }

        public void setGrid_y(String grid_y) {
            this.grid_y = grid_y;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
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

        public RoomstatedaydetailedBean getRoomstatedaydetailed() {
            return roomstatedaydetailed;
        }

        public void setRoomstatedaydetailed(RoomstatedaydetailedBean roomstatedaydetailed) {
            this.roomstatedaydetailed = roomstatedaydetailed;
        }

        public List<RoomorderinfoBean> getRoomorderinfo() {
            return roomorderinfo;
        }

        public void setRoomorderinfo(List<RoomorderinfoBean> roomorderinfo) {
            this.roomorderinfo = roomorderinfo;
        }

        public static class RoomstatedaydetailedBean {
            /**
             * id : 2209D555-2613-405E-8243-6A1E4BCFE800
             * roomstatedayid : 52D65230-3099-425B-91E8-8FF4F826D6A3
             * roomidcode : 101
             * sys_roomstatecode : EA
             * sys_roomsys_color : 16763238
             * businessday : 2016-11-29 00:00:00.000
             * loginname : LF0010007
             * hotelidcode : LF001
             * companyinfoidcode : BOTAO
             * createdatetime : 2017-04-07 14:19:31.000
             * status : 0
             * row_number : 1
             */

            private String id;
            private String roomstatedayid;
            private String roomidcode;
            private String sys_roomstatecode;
            private String sys_roomsys_color;
            private String businessday;
            private String loginname;
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

            public String getLoginname() {
                return loginname;
            }

            public void setLoginname(String loginname) {
                this.loginname = loginname;
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

        public static class RoomorderinfoBean {
            /**
             * mainname : 张三
             * begindate : 2016-11-29 00:00:00.000
             * enddate : 2016-11-30 00:00:00.000
             * daycount : 1
             * ratevalues : 300.00
             * partyid : 0
             */

            private String mainname;
            private String begindate;
            private String enddate;
            private String daycount;
            private String ratevalues;
            private String partyid;

            public String getMainname() {
                return mainname;
            }

            public void setMainname(String mainname) {
                this.mainname = mainname;
            }

            public String getBegindate() {
                return begindate;
            }

            public void setBegindate(String begindate) {
                this.begindate = begindate;
            }

            public String getEnddate() {
                return enddate;
            }

            public void setEnddate(String enddate) {
                this.enddate = enddate;
            }

            public String getDaycount() {
                return daycount;
            }

            public void setDaycount(String daycount) {
                this.daycount = daycount;
            }

            public String getRatevalues() {
                return ratevalues;
            }

            public void setRatevalues(String ratevalues) {
                this.ratevalues = ratevalues;
            }

            public String getPartyid() {
                return partyid;
            }

            public void setPartyid(String partyid) {
                this.partyid = partyid;
            }
        }
    }
}
