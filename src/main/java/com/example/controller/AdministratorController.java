package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.service.AdministratorService;

//管理者登録画面を表示する処理を記述する
@Controller
@RequestMapping("/")
public class AdministratorController {

    @Autowired
    private AdministratorService service;

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
    @PostMapping("/insert")
    public String insert(InsertAdministratorForm form){
        Administrator administrator = new Administrator();
        //InsertAdministratorForm オブジェクトの中⾝を今インスタンス化した Administrator ドメインオブジェクトにコピー
        BeanUtils.copyProperties(form, administrator);
        service.insert(administrator);
        ///」(ログイン画⾯)にリダイレクト
        return "redirect:/";

    }

}
