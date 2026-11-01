package com.narturebelle.providers.repository;

import com.narturebelle.providers.model.BookingStatus;
import com.narturebelle.providers.model.shared.SharedBooking;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SharedBookingRepository extends JpaRepository<SharedBooking, Long> {

    List<SharedBooking> findByProviderIdAndStatusAndScheduledStartAfter(Long providerId, BookingStatus status, LocalDateTime after);
}
