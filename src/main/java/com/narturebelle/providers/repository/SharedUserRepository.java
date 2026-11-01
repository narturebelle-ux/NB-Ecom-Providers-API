package com.narturebelle.providers.repository;

import com.narturebelle.providers.model.shared.SharedUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SharedUserRepository extends JpaRepository<SharedUser, Long> {

    Optional<SharedUser> findByFirebaseUid(String firebaseUid);
}
