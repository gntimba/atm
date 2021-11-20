package com.mahlodi.atm.Service;


import com.mahlodi.atm.DTO.userDTO;
import com.mahlodi.atm.model.ROLE;
import com.mahlodi.atm.persistence.Dao.UserDao;
import com.mahlodi.atm.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptEncoder;

    @Transactional
    public Long save(userDTO user) {

        User newUser = new User(user);


        newUser.setPassword(bCryptEncoder.encode(user.getPassword()));
        Set<ROLE> roles = new HashSet<>();
        roles.add(ROLE.LECTURER);
        roles.add(ROLE.ADMIN);
        newUser.setRoles(roles);
        return userDao.save(newUser).getId();
    }

    public Optional<User> findById(Long id) {
        Optional<User> Opuser = userDao.findById(id);
        if (Opuser.isPresent()) {
            Opuser.get().setPassword(null);
        }
        return Opuser;
    }

    public Optional<User> findByemail(String email) {
        Optional<User> Opuser = userDao.findByEmail(email);
        if (Opuser.isPresent()) {
            Opuser.get().setPassword(null);
        }
        return Opuser;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> opt = userDao.findByEmail(username);

        opt.get();

        org.springframework.security.core.userdetails.User springUser = null;

        if (opt.isEmpty()) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        } else {
            User user = opt.get();    //retrieving user from DB
            Set<ROLE> roles = user.getRoles();
            Set<GrantedAuthority> ga = new HashSet<>();
            for (ROLE role : roles) {
                ga.add(new SimpleGrantedAuthority(role.toString()));
            }

            springUser = new org.springframework.security.core.userdetails.User(
                    username,
                    user.getPassword(),
                    ga);
        }

        return springUser;
    }
}
