package com.Chatop.services;

import java.util.Optional;
import com.Chatop.model.Rental;
import com.Chatop.repository.RentalRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    public Optional<Rental> getRental(final Long id) {
        return rentalRepository.findById(id);
    }

    public Iterable<Rental> getRentals() {
        return rentalRepository.findAll();
    }

    public void deleteRental(final Long id) {
        rentalRepository.deleteById(id);
    }

    public Rental saveRental(Rental rental) {
        return rentalRepository.save(rental);
    }
}
