package com.onlinestore.admin.users;

import com.onlinestore.admin.repository.UserRepository;
import com.onlinestore.common.entity.Role;
import com.onlinestore.common.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
    @Autowired
    private UserRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser() {
        Role testUserRole = entityManager.find(Role.class, 1);
        User testUser = new User("test@mail.net", "password", "test", "testson");
        testUser.addRole(testUserRole);

        User savedTestUser = repository.save(testUser);
        assertThat(savedTestUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateUserWithTwoRoles() {
        User userWithTwoRoles = new User("userWithTwoRoles", "tworolespassword",
                "two", "rows");
        Role editorRole = new Role(3);
        Role assistantRole = new Role(5);

        userWithTwoRoles.addRole(editorRole);
        userWithTwoRoles.addRole(assistantRole);

        User savedUserWithTwoRoles = repository.save(userWithTwoRoles);
        assertThat(savedUserWithTwoRoles.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAllUsers(){
        Iterable<User> userList = repository.findAll();
        userList.forEach(System.out::println);
    }

    @Test
    public void testGetUserById(){
        User testUser = repository.findById(1).get();
        System.out.println(testUser);
        assertThat(testUser).isNotNull();
    }

    @Test
    public void testUpdateUserDetails(){
        User testUser = repository.findById(1).get();
        testUser.setEnabled(true);
        testUser.setEmail("changedEmail@mail.com");

        repository.save(testUser);
    }

    // To test removing editor role with id of 3, and adding salesman role with id of 2
    @Test
    public void testUpdateUserRoles(){
        User testUser = repository.findById(2).get();
        Role editorRole = new Role(3);
        Role salesMan = new Role(2);

        testUser.getRoles().remove(editorRole);
        testUser.addRole(salesMan);
    }

    @Test
    public void testDeleteUser(){
        Integer userId = 2;
        repository.deleteById(userId);
    }

    @Test
    public void testGetUserByEmail(){
        String email = "demo@com";
        User user = repository.getUserByEmail(email);

        assertThat(user).isNotNull();
    }

    @Test
    public void testCountById(){
        Integer id = 1;
        Long countById = repository.countById(id);

        assertThat(countById).isNotNull().isGreaterThan(0);
    }

    @Test
    public void testDisableUser(){
        Integer id = 24;
        repository.updateEnabledStatus(id,false);
    }

    @Test
    public void testEnableUser(){
        Integer id = 1;
        repository.updateEnabledStatus(id,true);
    }

    @Test
    public void testListFirstPage(){
        int pageNumber = 0;
        int pageSize = 4;
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        Page<User> page = repository.findAll(pageable);

        List<User> listUsers = page.getContent();

        listUsers.forEach(user -> System.out.println(user));

        assertThat(listUsers.size()).isEqualTo(pageSize);
    }

    @Test
    public void testSearchUsers(){
        String keyword = "bruce";
        int pageNumber = 0;
        int pageSize = 4;
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        Page<User> page = repository.findAll(keyword,pageable);

        List<User> listUsers = page.getContent();

        listUsers.forEach(user -> System.out.println(user));

        assertThat(listUsers.size()).isGreaterThan(0);
    }
}
