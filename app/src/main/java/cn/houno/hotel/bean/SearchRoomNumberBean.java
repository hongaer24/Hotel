package cn.houno.hotel.bean;

import java.util.List;

/**
 * Created by qzc on 2017-05-13.
 */

public class  SearchRoomNumberBean {


    /**
     * info : [{"day":"2016-11-29","info":[{"day":"2016-11-29","idcode":"SK","num":"25"},{"day":"2016-11-29","idcode":"DST","num":"0"},{"day":"2016-11-29","idcode":"SS","num":"6"},{"day":"2016-11-29","idcode":"ST","num":"15"},{"day":"2016-11-29","idcode":"DK","num":"1"},{"day":"2016-11-29","idcode":"DT","num":"2"},{"day":"2016-11-29","idcode":"DGVT","num":"0"},{"day":"2016-11-29","idcode":"SGVT","num":"5"},{"day":"2016-11-29","idcode":"SDK","num":"0"},{"day":"2016-11-29","idcode":"PM","num":null},{"day":"2016-11-29","idcode":"DS","num":"5"},{"day":"2016-11-29","idcode":"ES","num":"0"},{"day":"2016-11-29","idcode":"PS","num":"0"},{"day":"2016-11-29","idcode":"DSK","num":"1"}]},{"day":"2016-11-30","info":[{"day":"2016-11-30","idcode":"SK","num":"18"},{"day":"2016-11-30","idcode":"DST","num":"0"},{"day":"2016-11-30","idcode":"SS","num":"6"},{"day":"2016-11-30","idcode":"ST","num":"15"},{"day":"2016-11-30","idcode":"DK","num":"8"},{"day":"2016-11-30","idcode":"DT","num":"11"},{"day":"2016-11-30","idcode":"DGVT","num":"13"},{"day":"2016-11-30","idcode":"SGVT","num":"9"},{"day":"2016-11-30","idcode":"SDK","num":"0"},{"day":"2016-11-30","idcode":"PM","num":null},{"day":"2016-11-30","idcode":"DS","num":"7"},{"day":"2016-11-30","idcode":"ES","num":"0"},{"day":"2016-11-30","idcode":"PS","num":"1"},{"day":"2016-11-30","idcode":"DSK","num":"1"}]},{"day":"2016-12-01","info":[{"day":"2016-12-01","idcode":"SK","num":"17"},{"day":"2016-12-01","idcode":"DST","num":"0"},{"day":"2016-12-01","idcode":"SS","num":"7"},{"day":"2016-12-01","idcode":"ST","num":"15"},{"day":"2016-12-01","idcode":"DK","num":"8"},{"day":"2016-12-01","idcode":"DT","num":"11"},{"day":"2016-12-01","idcode":"DGVT","num":"13"},{"day":"2016-12-01","idcode":"SGVT","num":"10"},{"day":"2016-12-01","idcode":"SDK","num":"0"},{"day":"2016-12-01","idcode":"PM","num":null},{"day":"2016-12-01","idcode":"DS","num":"7"},{"day":"2016-12-01","idcode":"ES","num":"0"},{"day":"2016-12-01","idcode":"PS","num":"1"},{"day":"2016-12-01","idcode":"DSK","num":"1"}]}]
     * status : 2
     * msg : 获取数据成功
     */

    private int status;
    private String msg;
    private List<InfoBeanX> info;

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

    public List<InfoBeanX> getInfo() {
        return info;
    }

    public void setInfo(List<InfoBeanX> info) {
        this.info = info;
    }

    public static class InfoBeanX {
        /**
         * day : 2016-11-29
         * info : [{"day":"2016-11-29","idcode":"SK","num":"25"},{"day":"2016-11-29","idcode":"DST","num":"0"},{"day":"2016-11-29","idcode":"SS","num":"6"},{"day":"2016-11-29","idcode":"ST","num":"15"},{"day":"2016-11-29","idcode":"DK","num":"1"},{"day":"2016-11-29","idcode":"DT","num":"2"},{"day":"2016-11-29","idcode":"DGVT","num":"0"},{"day":"2016-11-29","idcode":"SGVT","num":"5"},{"day":"2016-11-29","idcode":"SDK","num":"0"},{"day":"2016-11-29","idcode":"PM","num":null},{"day":"2016-11-29","idcode":"DS","num":"5"},{"day":"2016-11-29","idcode":"ES","num":"0"},{"day":"2016-11-29","idcode":"PS","num":"0"},{"day":"2016-11-29","idcode":"DSK","num":"1"}]
         */

        private String day;
        private List<InfoBean> info;

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * day : 2016-11-29
             * idcode : SK
             * num : 25
             */

            private String day;
            private String idcode;
            private String num;

            public String getDay() {
                return day;
            }

            public void setDay(String day) {
                this.day = day;
            }

            public String getIdcode() {
                return idcode;
            }

            public void setIdcode(String idcode) {
                this.idcode = idcode;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }
        }
    }
}
