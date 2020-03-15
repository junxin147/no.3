package javabean;

public class Page {
    private int nowpage;
    private int countpage;

    public Page() {
    }

    public Page(int nowpage, int countpage) {
        this.nowpage = nowpage;
        this.countpage = countpage;
    }

    public int getNowpage() {
        return nowpage;
    }

    public void setNowpage(int nowpage) {
        this.nowpage = nowpage;
    }

    public int getCountpage() {
        return countpage;
    }

    public void setCountpage(int countpage) {
        this.countpage = countpage;
    }
}
