package com.onlinestore.admin.setting.state;

import com.onlinestore.common.entity.Country;
import com.onlinestore.common.entity.State;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StateRepository extends CrudRepository<State, Integer> {
    public List<State> findByCountryOrderByNameAsc(Country country);
}