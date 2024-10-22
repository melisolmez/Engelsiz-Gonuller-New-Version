package dev.melis.EngelsizGonuller.support.result;

import lombok.Getter;

@Getter
public class AuthenticationResult {

    private Boolean isSuccess;
    private String message;
    private OperationFailureReason reason;

    private AuthenticationResult(){}

    public static AuthenticationResult success(){
        return new AuthenticationResult()
                .setSuccess(true);
    }
    public static AuthenticationResult success(String message){
        return new AuthenticationResult()
                .setMessage(message)
                .setSuccess(true);
    }

    public static AuthenticationResult failure(OperationFailureReason reason){
        return new AuthenticationResult()
                .setReason(reason)
                .setSuccess(false);
    }

    public static AuthenticationResult failure(OperationFailureReason reason, String message){
        return new AuthenticationResult()
                .setReason(reason)
                .setSuccess(false)
                .setMessage(message);
    }

    public AuthenticationResult setMessage(String message){
        this.message = message;
        return this;
    }

    public AuthenticationResult setSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
        return this;
    }
    public AuthenticationResult setReason(OperationFailureReason reason) {
        this.reason = reason;
        return this;
    }

}
