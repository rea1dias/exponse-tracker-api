package com.disa.expensetrackerapi.mapper;

import com.disa.expensetrackerapi.domain.dto.auth.AuthInfoResponse;
import com.disa.expensetrackerapi.domain.dto.auth.RegisterRequest;
import com.disa.expensetrackerapi.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    User toEntity(RegisterRequest request);

    AuthInfoResponse toAuthInfoResponse(User user);


}
