package by.vironit.taskscheduler.repository;

import by.vironit.taskscheduler.entities.AppUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
class AppUserRepositoryTest {

    @Autowired
    private AppUserRepository userRepository;
    private AppUser createdUser;

    @BeforeEach
    public void setUp() {
        AppUser user = new AppUser();
        createdUser = userRepository.save(user);
    }

    @Test
    void findByEmail() {

        String email = createdUser.getEmail();
        Optional<AppUser> foundUser = userRepository.findByEmail(email);
        then(foundUser.orElse(null)).isEqualTo(createdUser);
    }

    @Test
    void getById() {

        Long id = createdUser.getId();
        Optional<AppUser> foundUser = Optional.ofNullable(userRepository.getById(id));
        then(foundUser.orElse(null)).isEqualTo(createdUser);
    }

    @AfterEach
    public void deleteObject() {
        userRepository.delete(createdUser);
    }

}