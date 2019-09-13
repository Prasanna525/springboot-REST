package com.prasanna.restservices.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.prasanna.restservices.dtos.UserMsDto;
import com.prasanna.restservices.entities.User;

@Mapper(componentModel = "Spring")
public interface UserMapper {
	
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	//User to UserMsDto
	@Mappings({
	@Mapping(source="role", target="roleName")
	})
	UserMsDto userToUserDto(User user);
	
	//List<User> to List<UserMsDto>
	List<UserMsDto> UsersToUsersDtos(List<User> user);

}
