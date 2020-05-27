package ru.kan.otus.libcat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kan.otus.libcat.domain.Role;
import ru.kan.otus.libcat.domain.Users;
import ru.kan.otus.libcat.repositories.UsersRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("Testing UserDetailsService")
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserDetailsServiceImplTest {

    private static final String SECURITY = "security";
    private static final String ADMINISTRATOR = "administrator";
    private static final String NAME = "admin";
    private static final String PASSWORD = "123";
    private Users admin;
    private Set<Role> roles;

    @MockBean
    private UsersRepository userRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @BeforeEach
    public void init() {
        roles = new HashSet<>();
        roles.add(Role.builder().id(Long.valueOf(1000)).name(ADMINISTRATOR).build());
        roles.add(Role.builder().id(Long.valueOf(1001)).name(SECURITY).build());

        admin = Users.builder().username(NAME).password(PASSWORD).roles(roles).build();
        when(userRepository.findByUsername(NAME)).thenReturn(admin);
    }

    @Test
    public void loadUserByUsername() {
        UserDetails userDetails = userDetailsService.loadUserByUsername(NAME);
        assertThat(userDetails).isEqualToComparingOnlyGivenFields(admin, "username", "password");
        assertThat(userDetails.getAuthorities()).isEqualTo(
                roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet()));
    }
}
