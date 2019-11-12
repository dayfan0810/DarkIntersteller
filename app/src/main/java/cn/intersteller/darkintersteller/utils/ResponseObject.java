package cn.intersteller.darkintersteller.utils;


public class ResponseObject<T> {
    private T result;
    private String state;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
