package com.abdullah.repository;

import com.abdullah.repository.entity.UserProfile;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserProfileRepository extends ElasticsearchRepository<UserProfile,String> {

    Optional<Boolean> existsByUserprofileidExists(Long id);
    Optional<UserProfile>findOptionalByAuthId(Long authId);

}
