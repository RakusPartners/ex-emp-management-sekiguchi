package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministratorService;

import jakarta.servlet.http.HttpSession;

//管理者登録画面を表示する処理を記述する
@Controller
@RequestMapping("/")
public class AdministratorController {

    @Autowired
    private AdministratorService service;

    @Autowired
    private HttpSession session;

    //「administrator/insert.html」にフォワードする処理
    //※フォームを引数で受け取ることで従業員登録する際のリクエストパラメータが格納されるInsertAdministratorForm オブジェクトが Model オブジェクト(リクエストスコープ)に⾃動的に格納されます
    @GetMapping("/toInsert")
    public String toInsert(InsertAdministratorForm form,Model model){
        // if(result.hasErrors()){
        //     return "insert";
        // }

        return "administrator/insert";
    }

    //管理者情報を登録
    //redirect
    @PostMapping("/insert")
    public String insert(InsertAdministratorForm form){
        Administrator administrator = new Administrator();
        //InsertAdministratorForm オブジェクトの中⾝を今インスタンス化した Administrator ドメインオブジェクトにコピー
        BeanUtils.copyProperties(form, administrator);
        service.insert(administrator);
        ///」(ログイン画⾯)にリダイレクト
        return "redirect:/";

    }
//フォームを引数で受け取ることで従業員登録する際のリクエストパラメータが格納されるLoginFormオブジェクトが Model オブジェクト(リクエストスコープ)に⾃動的に格納されます。
//フォームの受け取り    
    @GetMapping("/")
    public String toLogin(LoginForm form,Model model){
        return "administrator/login";
    }

    //・今回ログインした管理者の名前をセッションスコープに⼊れて、ログイン後のページに「○○さんこんにちは」と表⽰します。
    //受け取った情報で画面切り替え
    @PostMapping("/login")
    public String login(LoginForm form,Model model){

        Administrator administrator = service.login(form.getMailAddress(), form.getPassword());

        //controller内のメソッドにアクセスしたい場合→redirect
        //今までの方法　return "";　はhtmlに接続する法王
        if(administrator == null){
            model.addAttribute("error", "メールアドレスまたはパスワードが不正です。");
            return "administrator/login";
        }else{
            session.setAttribute("administratorName",administrator);
            return "redirect:/employee/showList";
        }

       

    }
}
