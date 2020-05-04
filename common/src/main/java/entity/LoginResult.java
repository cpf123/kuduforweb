package entity;

public class LoginResult {
    private Integer code;

    private Object data;



    public LoginResult( Integer code, Object data) {
        super();
        this.code = code;

        this.data = data;
    }



    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }

}
