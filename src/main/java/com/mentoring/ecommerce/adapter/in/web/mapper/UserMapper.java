package com.mentoring.ecommerce.adapter.in.web.mapper;

import com.mentoring.ecommerce.adapter.in.web.request.UserRequest;
import com.mentoring.ecommerce.adapter.in.web.response.UserResponse;
import com.mentoring.ecommerce.domain.User;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toDomain(final UserRequest request);
    UserResponse toResponse(final User product);

    default Page<UserResponse> allToResponse(Page<User> products) {
        return products.map(this::toResponse);
    }
}
