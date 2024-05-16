package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Employee;
import com.example.form.UpdateEmployeeForm;
import com.example.service.EmployeeService;

//従業員情報を検索する処理を記述する
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //従業員⼀覧を出⼒する。
    @GetMapping("/showList")
    public String showList(Model model){
        List<Employee> employeeList = employeeService.showList();
        model.addAttribute("employeeList",employeeList);
        return "employee/list";

    }

    //リクエストパラメータで送られてくる従業員 ID を引数に(int 型に変換してから)渡し、employeeService のshowDetail()メソッドを呼ぶ。その結果従業員情報(Employee)が戻り値として返ってくるのでそれを受け取る
    //※フォームを引数で受け取ることで扶養⼈数を更新する際のリクエストパラメータが格納されるUpdateEmployeeForm オブジェクトが Model オブジェクト(リクエストスコープ)に⾃動的に格納されます
    @GetMapping("/showDetail")
    //String id リクエストパラメータで送られてくる従業員 ID が格納されます
    public String showDetail(String id,UpdateEmployeeForm form,Model model){
        int intId =Integer.valueOf(id);
        Employee employee = employeeService.showDetail(intId);

        model.addAttribute("employee", employee);

        return "employee/detail";
    }
    
    //従業員詳細(ここでは扶養⼈数のみ)を更新する
    @PostMapping("/update")
    public String update(UpdateEmployeeForm form){

        //送られてきたリクエストパラメータの id を使⽤してEmployee ドメインを主キー検索す
        int intId=Integer.valueOf(form.getId());
        // employeeService.showDetail(intId);
        //リクエストパラメータの扶養⼈数を、検索してきた Employee ドメインにセットし上書き
        Employee employee = employeeService.showDetail(intId);
        int intDependentsCount = Integer.valueOf(form.getDependentsCount());
        employee.setDependentsCount(intDependentsCount);
        //employeeService の update()メソッドを呼ぶ
        employeeService.upadate(employee);

        return "redirect:/employee/showList";
    }
}
