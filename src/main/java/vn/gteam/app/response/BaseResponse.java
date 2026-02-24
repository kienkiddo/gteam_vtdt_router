package vn.gteam.app.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import vn.gteam.reflect.MyReflection;

@Getter
@NoArgsConstructor
@MyReflection
public class BaseResponse {
    private int code;
    private String message;

    public BaseResponse setFail(String message){
        this.code = ResponseCode.FAIL;
        this.message = message;
        return this;
    }

    public BaseResponse setSuccess(){
        this.setSuccess("Success!");
        return this;
    }

    public BaseResponse setSuccess(String message){
        this.code = ResponseCode.SUCCESS;
        this.message = message;
        return this;
    }
}
