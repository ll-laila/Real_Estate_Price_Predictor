package facture.factureservice.repository;

import facture.factureservice.entity.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FactureRepository extends JpaRepository<Facture, Long> {
    Optional<Facture> findByOrderId(Long orderId);
}
