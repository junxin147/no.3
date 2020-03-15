package javabean;

import com.sample.Appointment;
import com.sample.Scheduling;

import java.util.List;

public class Message {
    private String msg;

    private List<com.sample.Scheduling> mylist;
    private   List<com.sample.Appointment> mygetlist;
    private   List<Staff> mystafflist;
    private Staff staff;
    public Message() {
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public List<Staff> getMystafflist() {
        return mystafflist;
    }

    public void setMystafflist(List<Staff> mystafflist) {
        this.mystafflist = mystafflist;
    }

    public List<Appointment> getMygetlist() {
        return mygetlist;
    }

    public void setMygetlist(List<Appointment> mygetlist) {
        this.mygetlist = mygetlist;
    }

    public List<Scheduling> getMylist() {
        return mylist;
    }

    public void setMylist(List<Scheduling> mylist) {
        this.mylist = mylist;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
