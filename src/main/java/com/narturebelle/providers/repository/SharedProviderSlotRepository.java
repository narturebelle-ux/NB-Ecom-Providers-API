package com.narturebelle.providers.repository;

import com.narturebelle.providers.model.SlotStatus;
import com.narturebelle.providers.model.shared.SharedProviderSlot;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SharedProviderSlotRepository extends JpaRepository<SharedProviderSlot, Long> {

    List<SharedProviderSlot> findByProviderIdAndStatusAndStartAtAfterOrderByStartAtAsc(Long providerId, SlotStatus status, LocalDateTime after);

    /** Any slot that still holds this time: open, or booked and possibly under way. Removed slots don't count. */
    @Query("select count(s) > 0 from SharedProviderSlot s where s.providerId = :providerId"
            + " and s.status <> com.narturebelle.providers.model.SlotStatus.CANCELLED and s.startAt < :endAt and s.endAt > :startAt")
    boolean overlaps(@Param("providerId") Long providerId, @Param("startAt") LocalDateTime startAt, @Param("endAt") LocalDateTime endAt);
}
