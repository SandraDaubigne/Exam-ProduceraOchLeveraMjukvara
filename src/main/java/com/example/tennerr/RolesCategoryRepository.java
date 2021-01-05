package com.example.tennerr;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesCategoryRepository extends JpaRepository<RolesCategory, Long> {
}
