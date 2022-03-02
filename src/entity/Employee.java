package entity;

public class Employee {

    /**従業員*/
    private String EmployeeCode;
    /**苗字*/
    private String lastName;
    /**名前*/
    private String firstName;
    /**苗字かな*/
    private String lastKanaName;
    /**名前かな*/
    private String firstKanaName;
    /**性別*/
    private int gender;
    /**生年月日*/
    private String birthDay;
    /**部署*/
    private String sectionCode;
    /**入社日*/
    private String hireDate;
    /**パスワード*/
    private String password;

    /**コンストラクタ*/
    public Employee() {
    }

    public String getEmployeeCode() {
        return EmployeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        EmployeeCode = employeeCode;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastKanaName() {
        return lastKanaName;
    }

    public void setLastKanaName(String lastKanaName) {
        this.lastKanaName = lastKanaName;
    }

    public String getFirstKanaName() {
        return firstKanaName;
    }

    public void setFirstKanaName(String firstKanaName) {
        this.firstKanaName = firstKanaName;
    }

    public int getGenter() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }


    public void setGender(String gender) {
        if(gender.equals("男性")) {
            this.gender = 0;
        }else if (gender.equals("女性")) {
            this.gender = 1;
        }
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
