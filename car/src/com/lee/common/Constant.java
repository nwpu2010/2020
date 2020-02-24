package com.lee.common;

public interface Constant {
    /**
     * 表单数据校验错误码
     */
//    public  static final
    Integer FORM_CHECK_ERROR_CODE = 4001001;
    /**
     * 系统一级菜单
     */
    Integer SYSMENU_LEVEL_1_MENUID = 0;

    String CURRENT_USER = "current_user";
    String CHECK_CODE = "check_code" ;
    Integer CHECK_CODE_ERROR =4002001 ;
    Integer LOGINNAME_ISBLANK_ERROR = 4002002;
    Integer PASSWORD_ISBLANK_ERROR =4002003 ;
    Integer LOGIN_FAILED_ERROR = 4002004;
    Integer ADD_USER_ERROR = 4002005;
    /**
     * 系统用户默认密码
     */
    String SYSUSER_DEFAULT_PWD ="123456" ;
    /*客户相关错误代码*/
    Integer PHONE_EXIST_ERROR =4003001;
    Integer IDCARD_EXIST_ERROR = 4003002;
    /**
     * 存储文件的路径
     */
    String FILE_ABSOLUT_PATH ="resources/upload" ;
    Integer CAR_NOT_RENT = 4004001;


    /**
     *  表单数据校验错误码
     */
    Integer FORM_DATA_CHECK_ERROR_CODE = 4001001;
    /**
     *  一级菜单标识
     */
    Integer LEVEL_1_MENU_ID = 0;
    /**
     *  默认密码
     */
    String DEFAULT_PASSWORD = "123456";
    /**
     *  时间格式：yyyy-MM-dd HH:mm:ss
     */
    String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    /**
     *  时间格式 ： yyyyMMddHHmmssSSS
     */
    String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";

    /**
     *  车辆已出租
     */
    Integer CAR_RENT_ED = 2;
    /**
     *  出租状态 未归还
     */
    Integer RENT_FLAG_NOT_RETURN = 1;
    /**
     *   出租状态 已归还
     */
    Integer RENT_FLAG_RETURNED = 2;
}
