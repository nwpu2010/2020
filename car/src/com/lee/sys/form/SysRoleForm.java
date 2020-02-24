package com.lee.sys.form;

import javax.validation.constraints.NotBlank;

public class SysRoleForm {

    private Integer id;
    @NotBlank(message = "角色名称不能为空")
    private String name;

    private String desc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
