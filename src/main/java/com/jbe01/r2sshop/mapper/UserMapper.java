package com.jbe01.r2sshop.mapper;

import com.jbe01.r2sshop.dto.responses.UserDetailDto;
import com.jbe01.r2sshop.dto.responses.UserListDto;
import com.jbe01.r2sshop.entity.UserRole;
import com.jbe01.r2sshop.entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserListDto toUserResponseDto(Users users);

    List<UserListDto> listUserResponseToListUsers(List<Users> users);

    @Mapping(target = "roles", source = "userRoles", qualifiedByName = "mapRoles")
    UserDetailDto toDetailDTO(Users user);

    @Named("mapRoles")
    public static List<String> mapRoles(List<UserRole> userRoles) {
        if (userRoles == null) {
            return null;
        }
        return userRoles.stream()
                .map(userRole -> userRole.getRoles().getName())
                .collect(Collectors.toList());
    }
}
