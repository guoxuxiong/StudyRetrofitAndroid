package com.kyny.studyretrofit;



/**
 * @author: guoxuxiong
 * 时间:2020/6/12
 * 邮箱:553605867@qq.com
 * 描述:
 */
public class LoginBean {


    /**
     * record : {"alarmMsg":"发生紧急事故，请尽快来人救援！","alarmTel":"110","deptId":"301","id":"14","locInterval":"30","orgId":"","phoneNumber":"17378384228","roleCode":"mainData,driver","roleNames":"基本权限,驾驶员","siteId":"1000","siteName":"重庆分公司","username":"邓资华"}
     * resultCode : 1
     */

    private String record;
    private String resultCode;
    /**
     * alarmMsg : 发生紧急事故，请尽快来人救援！
     * alarmTel : 110
     * deptId : 301
     * id : 14
     * locInterval : 30
     * orgId :
     * phoneNumber : 17378384228
     * roleCode : mainData,driver
     * roleNames : 基本权限,驾驶员
     * siteId : 1000
     * siteName : 重庆分公司
     * username : 邓资华
     */

    private String alarmMsg;
    private String alarmTel;
    private String deptId;
    private String id;
    private String locInterval;
    private String orgId;
    private String phoneNumber;
    private String roleCode;
    private String roleNames;
    private String siteId;
    private String siteName;
    private String username;

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getAlarmMsg() {
        return alarmMsg;
    }

    public void setAlarmMsg(String alarmMsg) {
        this.alarmMsg = alarmMsg;
    }

    public String getAlarmTel() {
        return alarmTel;
    }

    public void setAlarmTel(String alarmTel) {
        this.alarmTel = alarmTel;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocInterval() {
        return locInterval;
    }

    public void setLocInterval(String locInterval) {
        this.locInterval = locInterval;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
