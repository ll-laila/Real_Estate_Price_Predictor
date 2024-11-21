package facture.factureservice.controller;

import facture.factureservice.entity.Facture;
import facture.factureservice.request.InvoiceRequest;
import facture.factureservice.service.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class FactureController {


    @Autowired
    private FactureService invoiceService;

    @PostMapping("/create")
    public ResponseEntity<Facture> createInvoice(@RequestBody InvoiceRequest invoiceRequest) {
        Facture invoice = invoiceService.createInvoice(
                invoiceRequest.getOrderId(),
                invoiceRequest.getCustomerId(),
                invoiceRequest.getTotalAmount()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(invoice);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<Facture> getInvoiceByOrderId(@PathVariable Long orderId) {
        return invoiceService.getInvoiceByOrderId(orderId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/pay/{invoiceId}")
    public ResponseEntity<Facture> markAsPaid(@PathVariable Long invoiceId) {
        Facture invoice = invoiceService.markAsPaid(invoiceId);
        return ResponseEntity.ok(invoice);
    }
}
