package com.sysbye.softIsdel.repo;

import org.springframework.data.repository.CrudRepository;

import com.sysbye.softIsdel.models.entities.Role;

public interface IAuthoritiesRepo extends CrudRepository<Role, Long>{

	
}
