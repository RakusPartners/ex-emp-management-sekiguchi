package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Employee;
import com.example.repository.EmployeeRepository;

//授業員情報⼀覧を全件検索する業務処理を⾏うメソッドを作成・・・・

@Service
@Transactional
public class EmployeeService {


    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> showList(){
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList;

    }

    //従業員情報を取得する。
    public Employee showDetail(Integer id){

        Employee employee= employeeRepository.load(id);
        return employee;
    }

    //従業員情報を更新する
    public void upadate(Employee employee){
        employeeRepository.update(employee);
    }

    
}
