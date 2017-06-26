package com.atguigu.p2p.bean;

/**
 * Created by Administrator on 2017/6/26.
 */

public class LoginBean {


    /**
     * data : {"name":"xiaolongge","imageurl":"http://182.92.5.3:8081/P2PInvest/images/tx.png","iscredit":"true","phone":"15321970103"}
     * success : true
     */

    private DataBean data;
    private boolean success;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class DataBean {
        /**
         * name : xiaolongge
         * imageurl : http://182.92.5.3:8081/P2PInvest/images/tx.png
         * iscredit : true
         * phone : 15321970103
         */

        private String name;
        private String imageurl;
        private String iscredit;
        private String phone;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImageurl() {
            return imageurl;
        }

        public void setImageurl(String imageurl) {
            this.imageurl = imageurl;
        }

        public String getIscredit() {
            return iscredit;
        }

        public void setIscredit(String iscredit) {
            this.iscredit = iscredit;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}
