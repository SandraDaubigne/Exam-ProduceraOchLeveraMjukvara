package com.example.tennerr;

import org.springframework.data.repository.CrudRepository;

public interface EntityRepository extends CrudRepository<User, Long> {
}
