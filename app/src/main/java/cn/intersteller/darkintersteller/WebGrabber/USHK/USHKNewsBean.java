package cn.intersteller.darkintersteller.WebGrabber.USHK;

public class USHKNewsBean {

    private final String time;
    private final String text;

    public String getTime() {
        return time;
    }

    public String getText() {
        return text;
    }

    public USHKNewsBean(String time, String text) {
        this.time = time;
        this.text = text;
    }


}
