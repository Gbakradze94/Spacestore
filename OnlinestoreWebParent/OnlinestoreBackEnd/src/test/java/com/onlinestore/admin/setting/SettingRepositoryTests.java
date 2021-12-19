package com.onlinestore.admin.setting;

import com.onlinestore.common.entity.setting.Setting;
import com.onlinestore.common.entity.setting.SettingCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class SettingRepositoryTests {
    @Autowired
    SettingRepository settingRepository;

    @Test
    public void testCreateGeneralSettings(){
       // Setting siteName = new Setting("SITE_NAME","Spacestore", SettingCategory.GENERAL);
        Setting siteName = new Setting("SITE_LOGO","Onlinestore.png", SettingCategory.GENERAL);
        Setting copyright = new Setting("COPYRIGHT","Copyright (C) 2021 Spacestore ltd.", SettingCategory.GENERAL);
        settingRepository.saveAll(List.of(siteName,copyright));

        Iterable<Setting> savedSettingIterable = settingRepository.findAll();

        assertThat(savedSettingIterable).size().isGreaterThan(0);
    }

    @Test
    public void testCreateCurrencySettings() {
        Setting currencyId = new Setting("CURRENCY_ID", "1", SettingCategory.CURRENCY);
        Setting symbol = new Setting("CURRENCY_SYMBOL", "$", SettingCategory.CURRENCY);
        Setting symbolPosition = new Setting("CURRENCY_SYMBOL_POSITION", "before", SettingCategory.CURRENCY);
        Setting decimalPointType = new Setting("DECIMAL_POINT_TYPE", "POINT", SettingCategory.CURRENCY);
        Setting decimalDigits = new Setting("DECIMAL_DIGITS", "2", SettingCategory.CURRENCY);
        Setting thousandsPointType = new Setting("THOUSANDS_POINT_TYPE", "COMMA", SettingCategory.CURRENCY);

        settingRepository.saveAll(List.of(currencyId, symbol, symbolPosition, decimalPointType,
                decimalDigits, thousandsPointType));

    }

    @Test
    public void testListSettingsByCategory(){
        List<Setting> settings = settingRepository.findByCategory(SettingCategory.GENERAL);

        settings.forEach(System.out::println);
    }
}
