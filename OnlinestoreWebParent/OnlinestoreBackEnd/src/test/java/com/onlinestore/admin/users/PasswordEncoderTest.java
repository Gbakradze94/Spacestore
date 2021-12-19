package com.onlinestore.admin.users;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PasswordEncoderTest {
    @Test
    public void testEncodePassword(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "password";
        String encodedPassrod = passwordEncoder.encode(rawPassword);

        System.out.println(encodedPassrod);

        boolean matches = passwordEncoder.matches(rawPassword,encodedPassrod);
        assertThat(matches).isTrue();
    }
}
