package facture.factureservice.service;


import facture.factureservice.entity.Facture;
import facture.factureservice.repository.FactureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FactureService {

    @Autowired
    private final FactureRepository invoiceRepository;

    public Facture createInvoice(Long orderId, Long customerId, Double totalAmount) {
        Facture invoice = new Facture();
        invoice.setOrderId(orderId);
        invoice.setCustomerId(customerId);
        invoice.setTotalAmount(totalAmount);
        invoice.setInvoiceDate(LocalDateTime.now());
        invoice.setInvoiceStatus("PENDING");
        return invoiceRepository.save(invoice);
    }

    public Optional<Facture> getInvoiceByOrderId(Long orderId) {
        return invoiceRepository.findByOrderId(orderId);
    }

    public Facture markAsPaid(Long invoiceId) {
        Facture invoice = invoiceRepository.findById(invoiceId).orElseThrow();
        invoice.setInvoiceStatus("PAID");
        return invoiceRepository.save(invoice);
    }
}

