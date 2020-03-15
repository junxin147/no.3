package javabean;

public class MyCount {
    private int mycount;
    private String staffname;
    public MyCount() {
    }

    public String getStaffname() {
        return staffname;
    }

    public void setStaffname(String staffname) {
        this.staffname = staffname;
    }

    public MyCount(int mycount) {
        this.mycount = mycount;
    }

    public int getMycount() {
        return mycount;
    }

    public void setMycount(int mycount) {
        this.mycount = mycount;
    }
}
