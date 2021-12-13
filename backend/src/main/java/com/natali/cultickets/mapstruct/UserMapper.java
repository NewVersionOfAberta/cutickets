package com.natali.cultickets.mapstruct;

import com.natali.cultickets.dto.UserDto;
import com.natali.cultickets.dto.UserGetDto;
import com.natali.cultickets.dto.UserPostDto;
import com.natali.cultickets.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserGetDto userToUserGetDto(User user);

    User userPostDtoToUser(UserPostDto userPostDto);

    UserGetDto userDtoToUserGetDto(UserDto userDto);

    UserDto userToUserDto(User user);
}
