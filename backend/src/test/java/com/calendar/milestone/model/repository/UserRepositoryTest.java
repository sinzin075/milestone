package com.calendar.milestone.model.repository;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import org.springframework.jdbc.core.JdbcTemplate;
import com.calendar.milestone.model.value.Password;

public class UserRepositoryTest {
    @Test
    void testUpdatePassword() {
        JdbcTemplate mockJdbcTemplate = mock(JdbcTemplate.class);
        when(mockJdbcTemplate.update(any(String.class), any(String.class), any(Integer.class)))
                .thenReturn(1);
        UserRepository userRepository = new UserRepository(mockJdbcTemplate);
        Password password = Password.encode("Show12!!asdkfuaou");
        int test = userRepository.updatePassword(2, password);
        assertThat(test).isEqualTo(1);

    }
}
