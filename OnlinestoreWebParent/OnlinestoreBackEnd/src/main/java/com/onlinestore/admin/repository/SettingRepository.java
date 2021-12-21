package com.onlinestore.admin.repository;

import com.onlinestore.common.entity.setting.Setting;
import com.onlinestore.common.entity.setting.SettingCategory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SettingRepository extends CrudRepository<Setting, String> {
    public List<Setting> findByCategory(SettingCategory category);
}