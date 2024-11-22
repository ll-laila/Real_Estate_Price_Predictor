package facture.factureservice;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.properties.TextAlignment;
import facture.factureservice.entity.Facture;

import java.io.IOException;

public class PdfGenerator {

    public void generateInvoicePdf(Facture invoice) throws IOException {
        // Define output PDF file
        String outputPath = "invoice_" + invoice.getId() + ".pdf";

        // Create a PdfWriter instance
        PdfWriter writer = new PdfWriter(outputPath);

        // Create a PdfDocument instance
        PdfDocument pdf = new PdfDocument(writer);

        // Create a Document to add content
        Document document = new Document(pdf);

        // Add invoice details as Paragraphs
        document.add(new Paragraph("Invoice ID: " + invoice.getId()).setTextAlignment(TextAlignment.LEFT));
        document.add(new Paragraph("Order ID: " + invoice.getOrderId()).setTextAlignment(TextAlignment.LEFT));
        document.add(new Paragraph("Customer ID: " + invoice.getCustomerId()).setTextAlignment(TextAlignment.LEFT));
        document.add(new Paragraph("Total Amount: " + invoice.getTotalAmount()).setTextAlignment(TextAlignment.LEFT));
        document.add(new Paragraph("Invoice Date: " + invoice.getInvoiceDate()).setTextAlignment(TextAlignment.LEFT));
        document.add(new Paragraph("Status: " + invoice.getInvoiceStatus()).setTextAlignment(TextAlignment.LEFT));

        // Close the document to save it
        document.close();

        System.out.println("Invoice PDF generated successfully: " + outputPath);
    }
}
