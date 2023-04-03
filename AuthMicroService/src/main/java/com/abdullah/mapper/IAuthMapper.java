package com.abdullah.mapper;

import com.abdullah.dto.request.ReqisterRequestDto;
import com.abdullah.dto.request.UserProfileSaveRequestDto;
import com.abdullah.repository.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface IAuthMapper {
    IAuthMapper INSTANCE= Mappers.getMapper(IAuthMapper.class);

    Auth toAuth(final ReqisterRequestDto dto);

    @Mapping(target = "authid",source = "id")
    UserProfileSaveRequestDto fromAuth(final Auth auth);
}
