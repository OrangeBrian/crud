package com.orange.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.orange.demo.domain.Usuario;

public interface IUserDao extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);
}
