package cn.dbj.knowledge.validation.vo;

public enum ResultCodeEnum {
    /**
     * result code
     */
    SUCCESS(0, "操作成功"),
    FAIL(-1, "操作失败"),
    SERVER_ERROR(500, "服务器异常"),
    PARAMS_ERROR(503, "参数异常");

    private final Integer code;
    private final String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static String getMessageByCode(Integer code) {
        for (ResultCodeEnum codeEnum : ResultCodeEnum.values()) {
            if (codeEnum.code.equals(code)) {
                return codeEnum.message;
            }
        }
        return null;
    }
}
