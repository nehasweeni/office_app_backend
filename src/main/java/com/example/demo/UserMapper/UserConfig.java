package com.example.demo.UserMapper;

import com.example.demo.data.domain.User;
import com.example.demo.dto.UserDTO;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

public class UserConfig extends ConfigurableMapper {
    public void configure(MapperFactory factory) {
        factory.classMap(User.class, UserDTO.class)
                .byDefault()
                .register();
    }
}
