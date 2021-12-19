package com.onlinestore.admin.users;

import com.onlinestore.common.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository repository;

    @Test
    public void testCreateFirstRole(){
        Role adminRole = new Role("Admin","Manages all operations");
        Role savedAdminRole = repository.save(adminRole);

        assertThat(savedAdminRole.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateSalesManRole(){
        Role salesMan = new Role("Salesman","Manages product price, customers, shipping" +
                " orders and sales reports.");
        repository.save(salesMan);
    }

    @Test
    public void testCreateEditorRole(){
        Role editor = new Role("Editor","Manages categories, brands and the catalogue" +
                " of products and services");
        repository.save(editor);
    }

    @Test
    public void testCreateCourierRole(){
        Role courier = new Role("Courier","Manages products delivery and " +
                "updates order statuses.");
        repository.save(courier);
    }

    @Test
    void testCreateAssistantRole(){
        Role assistant = new Role("Assistant","Manages FAQ and product rewiews, provides " +
                "information to questions");
        repository.save(assistant);
    }



}
