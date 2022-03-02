package entity;

import java.util.Date;

public class ViewListDisplay {
    /**従業員コード*/
    private String employeeCode;
    /**従業員名*/
    private String employeeName;
    /**名前*/
    private String EmployeeKanaName;
    /**性別*/
    private String gender;
    /**生年月日*/
    private Date birthDay;
    /**部署名*/
    private String sectionName;
    /**入社日*/
    private Date hireDate;
    /**コンストラクタ*/
    public ViewListDisplay() {
    }
    public String getEmployeeCode() {
        return employeeCode;
    }
    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }
    public String getEmployeeName() {
        return employeeName;
    }
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    public String getEmployeeKanaName() {
        return EmployeeKanaName;
    }
    public void setEmployeeKanaName(String employeeKanaName) {
        EmployeeKanaName = employeeKanaName;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(int gender) {
        if(gender == 0) {
            this.gender = "男性";
        } else {
            this.gender = "女性";
        }
    }
    public Date getBirthDay() {
        return birthDay;
    }
    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
    public String getSectionName() {
        return sectionName;
    }
    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }
    public Date getHireDate() {
        return hireDate;
    }
    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }


}
