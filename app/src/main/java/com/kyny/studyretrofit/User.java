package com.kyny.studyretrofit;

/**
 * @author: guoxuxiong
 * 时间:2019/6/25
 * 邮箱:553605867@qq.com
 * 描述:
 */
public class User {


    /**
     * code : KYNYADMIN
     * group_oid :
     * inc_dept_name :
     * inc_dept_oid :
     * inc_name : 科源能源
     * inc_oid : 1
     * name : 科源能源管理员
     * oid : 1
     * permissions :
     * pwd : 111111
     */
    public  Record  record;
    private String  name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Record getRecord() {
        return record;
    }


    public void setRecord(Record record) {
        this.record = record;
    }

    public static class Record{
        public String id;//用户id
        public String username;//用户姓名
        public String deptId;//部门id部门id
        public String siteId;//单位id
        public String orgId;//组织id
        public String locInterval;//组织id
        public String roleCode;//角色
        public String phoneNumber;//角色
        public String roleNames;//角色名称

        public String getRoleNames() {
            return roleNames;
        }

        public void setRoleNames(String roleNames) {
            this.roleNames = roleNames;
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

        public String siteName;//单位名称
        public String alarmTel;//紧急求助电话
        public String alarmMsg;//紧急求助短信

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getDeptId() {
            return deptId;
        }

        public void setDeptId(String deptId) {
            this.deptId = deptId;
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

        public String getAlarmTel() {
            return alarmTel;
        }

        public void setAlarmTel(String alarmTel) {
            this.alarmTel = alarmTel;
        }

        public String getAlarmMsg() {
            return alarmMsg;
        }

        public void setAlarmMsg(String alarmMsg) {
            this.alarmMsg = alarmMsg;
        }
    }

}
