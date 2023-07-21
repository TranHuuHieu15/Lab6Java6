package edu.poly.lab6.config;

import edu.poly.lab6.auth.Role;
import edu.poly.lab6.entity.User;
import edu.poly.lab6.repository.UserRepository;
import edu.poly.lab6.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Autowired
    UserService service;

    @Autowired
    UserRepository repository;

    //	Mã hóa mật khẩu
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // quản lý dữ liệu người dùng
    @Bean
    public AuthenticationProvider getAuthenticationProvider() {
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setPasswordEncoder(passwordEncoder());
        dao.setUserDetailsService(service);
        return dao;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable().cors().disable()
                .authorizeHttpRequests() // bắt đầu cấu hình quy tắt cho việc xác thực yêu cầu  http
                .requestMatchers("/home/index").permitAll() // với endpoint /home/index thì sẽ được cho qua
                .and().formLogin() // trả về page login nếu chưa authenticate
                .and().authenticationProvider(getAuthenticationProvider()).build();
    }

    @Bean
    public CommandLineRunner getCommandRunner() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                User user = User.builder().username("hieu").password(passwordEncoder().encode("123")).roles(Role.ADMIN).build();
                repository.save(user);
            }
        };
    }

}
