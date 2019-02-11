package com.ccc.oa.security;

import com.ccc.oa.dao.RoleDao;
import com.ccc.oa.dao.UserDao;
import com.ccc.oa.model.Permission;
import com.ccc.oa.model.Role;
import com.ccc.oa.model.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    static final Logger LOG = LoggerFactory.getLogger(CustomUserDetailsService.class);
    private final UserDao userDao;
    private final RoleDao roleDao;

    @Autowired
    public CustomUserDetailsService(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = userDao.loadUserByUsername(username);
        if(member == null) {
            throw new UsernameNotFoundException("Could not find member " + username);
        }
        Role role = userDao.selectRole(member.getRoleId());
        System.out.println(role);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        List<Permission> permissions = roleDao.selectPermissions(role.getId());
        for (Permission permission : permissions){
            if (permission != null && permission.getName() != null){
                grantedAuthorities.add(new SimpleGrantedAuthority(permission.getName()));
            }
        }
        return new User(member.getUsername(), member.getPassword(), grantedAuthorities);
    }
}
