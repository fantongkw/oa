package com.ccc.oa.service.impl;

import com.ccc.oa.dao.UserDao;
import com.ccc.oa.model.Department;
import com.ccc.oa.model.Member;
import com.ccc.oa.model.Role;
import com.ccc.oa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserService {
    private static final Long DEFAULT_ROLE_ID = 5L;
    private static final String DEFAULT_PROFILE_PICTURE = "/images/faces-clipart/default.jpg";
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public int deleteById(Long id) {
        Member member = userDao.selectById(id);
        if (member != null) {
            return userDao.deleteById(id);
        }
        return 0;
    }

    @Override
    public int insert(Member member) {
        return userDao.insert(member);
    }

    @Override
    @Transactional
    public int insertSelective(Member member) {
        Member duplicate = userDao.loadUserByUsername(member.getUsername());
        if (duplicate == null) {
            member.setRoleId(DEFAULT_ROLE_ID);
            member.setPassword(passwordEncoder.encode(member.getPassword()));
            member.setProfilePicture(DEFAULT_PROFILE_PICTURE);
            member.setDate(new java.sql.Date(new Date().getTime()));
            return userDao.insertSelective(member);
        }
        return 0;
    }

    @Override
    public List<Member> selectAllUser() {
        return userDao.selectAllUser();
    }

    @Override
    public Member selectById(Long id) {
        return userDao.selectById(id);
    }

    @Override
    public Member loadUserByUsername(String username) {
        return userDao.loadUserByUsername(username);
    }

    @Override
    public Member selectByEmail(String email) {
        return userDao.selectByEmail(email);
    }

    @Override
    public Role selectRole(Long roleId) {
        return userDao.selectRole(roleId);
    }

    @Override
    public Department selectDept(Long departmentId) {
        return userDao.selectDept(departmentId);
    }

    @Transactional
    @Override
    public int updateByIdSelective(Member member) {
        Member duplicate = userDao.loadUserByUsername(member.getUsername());
        if (duplicate == null) {
            member.setPassword(passwordEncoder.encode(member.getPassword()));
            return userDao.updateByIdSelective(member);
        }
        return 0;
    }
}
