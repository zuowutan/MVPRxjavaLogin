package com.example.tzw.mvprxjavalogin.mvp.model;

/**
 * Created by Administrator on 2017/9/21.
 */

public class MVPLoginResultBean {


    /**
     * status : YHDL_000
     * sid : 8CDCF01AA18197885EFAB099581568DABCEB3A1C87064442
     * data : {"user":{"user_name":"18218013313h","channel_id":0,"user_phone":"18218013313","user_email":"","head_portrait":"http://htcdn2.hjwan.com/htcdn2hjwan/HeadPortrait/headPortrait_1505118348489_86695.JPEG","money":9999998.88}}
     * msg : 登录成功
     */

    private String status;
    private String sid;
    private DataEntity data;
    private String msg;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public String getSid() {
        return sid;
    }

    public DataEntity getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "MVPLoginResultBean{" +
                "status='" + status + '\'' +
                ", sid='" + sid + '\'' +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }

    public static class DataEntity {
        /**
         * user : {"user_name":"18218013313h","channel_id":0,"user_phone":"18218013313","user_email":"","head_portrait":"http://htcdn2.hjwan.com/htcdn2hjwan/HeadPortrait/headPortrait_1505118348489_86695.JPEG","money":9999998.88}
         */

        private UserEntity user;

        public void setUser(UserEntity user) {
            this.user = user;
        }

        public UserEntity getUser() {
            return user;
        }

        public static class UserEntity {
            /**
             * user_name : 18218013313h
             * channel_id : 0
             * user_phone : 18218013313
             * user_email :
             * head_portrait : http://htcdn2.hjwan.com/htcdn2hjwan/HeadPortrait/headPortrait_1505118348489_86695.JPEG
             * money : 9999998.88
             */

            private String user_name;
            private int channel_id;
            private String user_phone;
            private String user_email;
            private String head_portrait;
            private double money;

            @Override
            public String toString() {
                return "UserEntity{" +
                        "user_name='" + user_name + '\'' +
                        ", channel_id=" + channel_id +
                        ", user_phone='" + user_phone + '\'' +
                        ", user_email='" + user_email + '\'' +
                        ", head_portrait='" + head_portrait + '\'' +
                        ", money=" + money +
                        '}';
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public void setChannel_id(int channel_id) {
                this.channel_id = channel_id;
            }

            public void setUser_phone(String user_phone) {
                this.user_phone = user_phone;
            }

            public void setUser_email(String user_email) {
                this.user_email = user_email;
            }

            public void setHead_portrait(String head_portrait) {
                this.head_portrait = head_portrait;
            }

            public void setMoney(double money) {
                this.money = money;
            }

            public String getUser_name() {
                return user_name;
            }

            public int getChannel_id() {
                return channel_id;
            }

            public String getUser_phone() {
                return user_phone;
            }

            public String getUser_email() {
                return user_email;
            }

            public String getHead_portrait() {
                return head_portrait;
            }

            public double getMoney() {
                return money;
            }
        }
    }
}
