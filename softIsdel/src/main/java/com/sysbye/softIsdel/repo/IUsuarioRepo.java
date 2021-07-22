/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysbye.softIsdel.repo;

import org.springframework.data.repository.CrudRepository;

import com.sysbye.softIsdel.models.entities.Usuario;




/**
 *
 * @author matia
 */
public interface IUsuarioRepo extends CrudRepository<Usuario, Long>{
    
	public Usuario findByUsername(String username);
}
