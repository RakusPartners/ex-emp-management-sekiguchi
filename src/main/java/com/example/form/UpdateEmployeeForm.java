package com.example.form;

//従業員情報更新時に使⽤するフォーム
public class UpdateEmployeeForm {

    //従業員 ID
    private String id;
    //扶養⼈数
    private String dependentsCount;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getDependentsCount() {
        return dependentsCount;
    }
    public void setDependentsCount(String dependentsCount) {
        this.dependentsCount = dependentsCount;
    }
    
    @Override
    public String toString() {
        return "UpdateEmployeeForm [id=" + id + ", dependentsCount=" + dependentsCount + "]";
    }

    



}
