package com.onlinestore.product;

import com.onlinestore.common.entity.product.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProductRepositoryTests {
    @Autowired
    ProductRepository repository;

    @Test
    public void testFindByAlias(){
        String alias = "canon-eos-m50";
        Product product = repository.findByAlias(alias);

        assertThat(product).isNotNull();
    }
}
