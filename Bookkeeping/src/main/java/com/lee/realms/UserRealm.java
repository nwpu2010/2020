package com.lee.realms;

import com.lee.common.ActiveUser;
import com.lee.domain.SysUser;
import com.lee.service.BillTypeService;
import com.lee.service.BillsService;
import com.lee.service.SysUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserRealm extends AuthorizingRealm {


        @Autowired
        private SysUserService sysUserService;

    /*
    授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        ActiveUser activeUser = (ActiveUser) principalCollection.getPrimaryPrincipal();

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        if (activeUser.getRoles() != null && activeUser.getRoles().size() > 0) {
            simpleAuthorizationInfo.addRoles(activeUser.getRoles());//list用add方法
        }
        if (activeUser.getPermissions() != null && activeUser.getPermissions().size() > 0) {
            simpleAuthorizationInfo.addStringPermissions(activeUser.getPermissions());
        }
        return simpleAuthorizationInfo;
    }
    /*
    认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //使用String类型的用户名来做身份认证
        String  loginName= (String) authenticationToken.getPrincipal();
        //t通过查询数据库来获取凭证信息
        SysUser sysUser = sysUserService.selectUserByLoginName(loginName);
        String password = null;
        if (null != sysUser) {
            password = sysUser.getPwd();
            if (password == null) {
                return null;
            }
        } else {

            return null;
        }
        ActiveUser activeUser = new ActiveUser(sysUser);
        ByteSource bytes = ByteSource.Util.bytes(sysUser.getLoginname()+sysUser.getName());
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(activeUser,
                password, bytes,this.getClass().getSimpleName());
        return simpleAuthenticationInfo;


    }
}
