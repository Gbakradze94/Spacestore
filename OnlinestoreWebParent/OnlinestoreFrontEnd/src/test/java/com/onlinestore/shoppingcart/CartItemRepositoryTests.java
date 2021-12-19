package com.onlinestore.shoppingcart;


import com.onlinestore.common.entity.CartItem;
import com.onlinestore.common.entity.Customer;
import com.onlinestore.common.entity.product.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class CartItemRepositoryTests {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    void testSaveItem(){
        Integer customerId = 1;
        Integer productId = 1;

        Customer customer = entityManager.find(Customer.class,customerId);
        Product product = entityManager.find(Product.class,productId);

        CartItem newItem = new CartItem();
        newItem.setCustomer(customer);
        newItem.setProduct(product);
        newItem.setQuantity(1);

        CartItem savedItem = cartItemRepository.save(newItem);

        assertThat(savedItem.getId()).isPositive();
    }
}
