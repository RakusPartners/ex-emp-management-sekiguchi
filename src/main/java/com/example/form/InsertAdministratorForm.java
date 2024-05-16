package com.example.form;


//管理者情報登録時に使用するフォーム
public class InsertAdministratorForm {
   
    //名前
    // @NotBlank(message="必須")
    private String name;
    //メールアドレス
    // @NotBlank(message="必須")
    private String mailAddress;
    //パスワード
    // @NotBlank(message="必須")
    private String password;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMailAddress() {
        return mailAddress;
    }
    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "InsertAdministratorForm [name=" + name + ", mailAddress=" + mailAddress + ", password=" + password
                + "]";
    }

    
    
}
