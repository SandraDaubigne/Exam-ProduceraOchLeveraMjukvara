package com.example.tennerr;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTwoRepository extends JpaRepository<User, Long> {

}
