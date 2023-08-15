package com.mentoring.ecommerce.adapter.in.web.mapper;

import com.mentoring.ecommerce.adapter.in.web.dto.UserDTO;
import com.mentoring.ecommerce.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends CommonMapper<User, UserDTO> {
}
