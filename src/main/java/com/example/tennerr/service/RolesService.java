package com.example.tennerr.service;

import com.example.tennerr.entity.Roles;
import com.example.tennerr.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolesService {

    @Autowired
    private RolesRepository rolesRepository;

    public void saveRole(Roles roles){ rolesRepository.save(roles);
    }
}
