package com.lee.common;

public enum CodeMsg {
    SUCCESS(200, "操作成功"),
    FAILED(110, "亲，出现错误了哟"),
    CAR_CAR_NUM_EXIST_ERROR(121,"车牌号已经存在") ,
     CAR_CAR_IMG_SAVE_ERROR(122,"图片保存失败") ,

    //400100 开头是用户模块
    USER_LOGINNAME_ISEMPTY(400101,"登录账号为空"),
    USER_LOGINPASSWORD_ISEMPTY(400102,"登录密码为空"),
    USER_LOGINCHECKCODE_ISEMPTY(400103,"登录验证码为空"),
    USER_LOGIN_CHECKCODE_ERROR(400104,"登录验证码错误"),
    USER_LOGIN_ACCOUNT_ERROR(400105,"登录账号密码错误"),
    USER_LOGINNAME_EXIST_ERROR(400106,"登录账号已被占用"),
    USER_IDCARDE_EXIST_ERROR(400107,"登录账号身份证号已被占用"),
    USER_PHONE_EXIST_ERROR(400108,"登录账号手机号已被占用"),
    USER_USER_ID_FORMAT_ERROR(400109,"用户ID格式错误"),
    USER_USER_IMG_UPDATE_ERROR(400110,"用户图像修改失败"),

    CUSTOMER_IDCARDE_EXIST_ERROR(400201,"客户身份证号已被占用"),
    CUSTOMER_PHONE_EXIST_ERROR(400202,"客户手机号已被占用"),

    RENT_CUSTOMER_IDCARD_ERROR(400401,"客户身份证号无效"),
    RENT_CAR_CARNUM_ERROR(400402,"汽车车牌号错误"),
    RENT_CAR_RENTED_ERROR(400403,"汽车已出租"),
    RENT_FAILD_ERROR(400404,"汽车出租失败,汽车信息不一致,请刷新"),

    CEHCK_RENT_NO_ERROR(400501,"出租单号信息有误"),
    CEHCK_CREATE_ERROR(400502,"检查单创建失败");

    public Integer code;
    public String msg;

    CodeMsg(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
