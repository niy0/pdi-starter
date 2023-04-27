package com.pidSpringBoot.pidSpringBoot.user;

import com.pidSpringBoot.pidSpringBoot.role.Role;
import com.pidSpringBoot.pidSpringBoot.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired private RoleRepository roleRepository;

    public void save(User user) {
        userRepository.save(user);

    }

    public List<Role> listRoles () {
        return roleRepository.findAll();
    }

    public void registerDefaultUser(User user){
        Optional<Role> test = roleRepository.findById(2);
        user.addRole(test.get());
        userRepository.save(user);
    }

    public void setPasswordEncoder(User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    public UserDetails loadUserByUsername(String userLogin) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(userLogin);
        if(user == null) {
            throw new UsernameNotFoundException("User avec cet login, pas trouvé");
        }
        return new CustomUserDetails(user);
    }

}
