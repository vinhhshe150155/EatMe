package com.example.areal.Models;

public class ModelComment {
    String cId;

    String comment;

    String ptime;

    String udp;

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public String getUdp() {
        return udp;
    }

    public void setUdp(String udp) {
        this.udp = udp;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }



    String uemail;

    public ModelComment() {
    }



    public ModelComment(String cId, String comment, String ptime , String uemail) {
        this.cId = cId;
        this.comment = comment;
        this.ptime = ptime;

        this.uemail = uemail;


    }


}
