package com.orange.demo.service;

import com.orange.demo.dao.IUserDao;
import com.orange.demo.domain.Rol;
import com.orange.demo.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service("userDetailsService")
public class UsuarioService implements UserDetailsService {

    @Autowired
    private IUserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = userDao.findByUsername(username);

        if(usuario == null) {
            throw new UsernameNotFoundException(username);
        }

        ArrayList<GrantedAuthority> roles = new ArrayList<>();

        for(Rol role: usuario.getRole()) {
            roles.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }
}
