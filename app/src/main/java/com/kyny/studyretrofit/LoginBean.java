package com.kyny.studyretrofit;



/**
 * @author: guoxuxiong
 * 时间:2020/6/12
 * 邮箱:553605867@qq.com
 * 描述:
 */
public class LoginBean {


    /**
     * access_token : 6a5b39fd-43a5-4d1f-9fad-ac4925663c1b
     * token_type : bearer
     * refresh_token : 326b6792-c85e-445e-8811-0c63ac939f2a
     * expires_in : 42243
     * scope : server
     * tenant_id : 3
     * license : made by kyny
     * user_id : 1
     * site_id : 192
     * active : true
     * dept_id : 1
     * username : sysadmin
     */

    private String access_token;
    private String token_type;
    private String refresh_token;
    private int expires_in;
    private String scope;
    private int tenant_id;
    private String license;
    private int user_id;
    private int site_id;
    private boolean active;
    private int dept_id;
    private String username;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public int getTenant_id() {
        return tenant_id;
    }

    public void setTenant_id(int tenant_id) {
        this.tenant_id = tenant_id;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getSite_id() {
        return site_id;
    }

    public void setSite_id(int site_id) {
        this.site_id = site_id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getDept_id() {
        return dept_id;
    }

    public void setDept_id(int dept_id) {
        this.dept_id = dept_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
