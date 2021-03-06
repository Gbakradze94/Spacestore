package com.onlinestore.admin.brands;

import com.onlinestore.admin.repository.BrandRepository;
import com.onlinestore.admin.service.BrandService;
import com.onlinestore.common.entity.Brand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class BrandServiceTests {
    @MockBean
    private BrandRepository brandRepository;

    @InjectMocks
    private BrandService service;

    @Test
    void testCheckUniqueInNewModeReturnDuplicate(){
        Integer id = null;
        String name = "Acer";
        Brand brand = new Brand(name);

        Mockito.when(brandRepository.findByName(name)).thenReturn(brand);

        String result = service.checkUnique(id,name);
        assertThat(result).isEqualTo("Duplicate");
    }

    @Test
    public void testCheckUniqueInNewModeReturnOK(){
        Integer id = null;
        String name = "AMD";

        Mockito.when(brandRepository.findByName(name)).thenReturn(null);

        String result = service.checkUnique(id,name);
        assertThat(result).isEqualTo("OK");
    }

    @Test
    public void testCheckUniqueInEditModeReturnDuplicate(){
        Integer id = 1;
        String name = "Canon";
        Brand brand = new Brand(id,name);

        Mockito.when(brandRepository.findByName(name)).thenReturn(brand);

        String result = service.checkUnique(2,"Canon");
        assertThat(result).isEqualTo("Duplicate");
    }

    @Test
    public void testCheckUniqueInEditModeReturnOK() {
        Integer id = 1;
        String name = "Acer";
        Brand brand = new Brand(id,name);

        Mockito.when(brandRepository.findByName(name)).thenReturn(brand);

        String result = service.checkUnique(id, "Acer");
        assertThat(result).isEqualTo("OK");
    }
}
