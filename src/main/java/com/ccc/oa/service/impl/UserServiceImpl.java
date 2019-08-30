package com.ccc.oa.service.impl;

import com.ccc.oa.dao.UserDao;
import com.ccc.oa.model.Department;
import com.ccc.oa.model.Member;
import com.ccc.oa.model.Role;
import com.ccc.oa.service.UserService;
import com.ccc.oa.utils.AvatarUtil;
import com.ccc.oa.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserService {
    private static final Long DEFAULT_ROLE_ID = 5L;
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
        return userDao.deleteById(id);
    }

    @Transactional
    @Override
    public int insert(Member member) {
        if (!isExist(member)) {
            member.setRoleId(DEFAULT_ROLE_ID);
            member.setPassword(passwordEncoder.encode(member.getPassword()));
            member.setAvatar(AvatarUtil.getAvatarPath(member.getUsername()));
            member.setDate(new Date(new java.util.Date().getTime()));
            return userDao.insert(member);
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
    public Department selectDepartment(Long departmentId) {
        return userDao.selectDepartment(departmentId);
    }

    @Transactional
    @Override
    public int updateById(Member member) {
        if (isExist(member)) {
            return userDao.updateById(member);
        }
        return 0;
    }

    @Transactional
    @Override
    public int changePassword(Member member, String password) {
        member.setPassword(passwordEncoder.encode(password));
        return userDao.updateById(member);
    }

    private boolean isExist(Member member) {
        if (null != member) {
            Member exist = userDao.selectById(member.getId());
            return null != exist;
        }
        return false;
    }
}
