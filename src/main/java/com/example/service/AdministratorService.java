package com.example.service;
//管理者情報を登録する業務処理

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Administrator;
import com.example.repository.AdministratorRepository;

@Service
@Transactional
public class AdministratorService {

    @Autowired
    private AdministratorRepository repository;

    //管理者情報を挿⼊する
    public void insert(Administrator administrator){
        repository.insert(administrator);
    }
    //ログイン処理をする
    public Administrator login(String mailAddress,String password){
       Administrator administrator= repository.findByMailAddressAndPassword(mailAddress, password);
        return administrator;
    }

}
