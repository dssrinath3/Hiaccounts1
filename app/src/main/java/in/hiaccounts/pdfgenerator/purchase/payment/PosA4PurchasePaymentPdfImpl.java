package in.hiaccounts.pdfgenerator.purchase.payment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.VerticalPositionMark;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import harmony.java.awt.Color;
import in.hiaccounts.R;
import in.hiaccounts.hinext.purchase.model.purchase_saveresponses.Purchase_PaymentResponse;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.SharedPreference;

/**
 * Created by Prateek on 6/1/2017.
 */
public class PosA4PurchasePaymentPdfImpl implements PosPurchasePaymetPdf {

    @Override
    public void generatePaymentPdf(Context context, Purchase_PaymentResponse printData, OutputStream outPutStream, InputStream inputStream) throws IOException, DocumentException {

        Document document = new Document((PageSize.A4));
        PdfWriter.getInstance(document, outPutStream);
        document.open();





        Font fontHeader = new Font(Font.HELVETICA, 12, Font.BOLD);
        Font fontTableHeader = new Font(Font.HELVETICA, 8, Font.BOLD, Color.BLACK);
        Font fontTable = new Font(Font.HELVETICA, 7);
        Font fontTaxBold = new Font(Font.HELVETICA, 10, Font.BOLD, Color.BLACK);
        Font fontTax = new Font(Font.HELVETICA, 10);
        Chunk glue = new Chunk(new VerticalPositionMark());

        //Paragraph header = new Paragraph();
        PdfPTable header = new PdfPTable(1);
        header.setWidthPercentage(100);
/*

        Phrase phrase=new Phrase();
        for (Chunk chunk : chunks) {
            phrase.add(chunk);

        }
        PdfPCell cell = new PdfPCell(phrase);
        cell.setPadding(2);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setLeading(20,0);
*/

        Paragraph paragraph=new Paragraph(new Chunk(Constant.PDF_TITLE_PAYMENTHEADER, fontHeader));
        paragraph.setAlignment(Element.ALIGN_CENTER);
        PdfPCell headerCell = new PdfPCell(paragraph);
        headerCell.setBorder(Rectangle.BOTTOM);
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        headerCell.setFixedHeight(25);
        header.addCell(headerCell);
        //header.addCell();
        //header.setAlignment(Element.ALIGN_CENTER);

        PdfPTable companyInfoTable = new PdfPTable(3);
        companyInfoTable.setSpacingBefore(10);
        companyInfoTable.setWidths(new float[]{1,2, 2});
        companyInfoTable.setWidthPercentage(100);


        String companyName = "", companyNo = "", companyGSTNo = "", companyRecieptNo = "", companyDate = "", prepraredBy = "";
        if (printData.getCompanyName()!=null)
            companyName=printData.getCompanyName();

        if (printData.getCompanyNo()!=null)
            companyNo=printData.getCompanyNo();

        if (printData.getGstNo()!=null)
            companyGSTNo=printData.getGstNo();

        if (printData.getTranactionId()!=null)
            companyRecieptNo=printData.getTranactionId();

        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        companyDate=day+"-"+(month+1)+"-"+year;

        SharedPreference sharedPreference=SharedPreference.getInstance(context);
        prepraredBy=sharedPreference.getData(Constant.USERNAME);


        if (inputStream != null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            Image image = Image.getInstance(stream.toByteArray());
            image.scaleAbsolute(50, 50);
            companyInfoTable.addCell(getTableCell(image, Element.ALIGN_MIDDLE, Element.ALIGN_MIDDLE));
        } else {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_app_icon);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            Image image = Image.getInstance(stream.toByteArray());
            image.scaleAbsolute(50, 50);
            companyInfoTable.addCell(getTableCell(image, Element.ALIGN_MIDDLE, Element.ALIGN_MIDDLE));
        }

        List<Chunk>chunkList=new ArrayList<>();

        chunkList.add(new Chunk(Constant.PDF_CONAME,fontTax));
        chunkList.add(new Chunk(companyName,fontTax));
        chunkList.add(new Chunk(Chunk.NEWLINE));
        chunkList.add(new Chunk(Constant.PDF_CONUMBER,fontTax));
        chunkList.add(new Chunk(companyNo,fontTax));
        chunkList.add(new Chunk(Chunk.NEWLINE));
        chunkList.add(new Chunk(Constant.PDF_GSTNO, fontTax));
        chunkList.add(new Chunk(companyGSTNo, fontTax));
        chunkList.add(new Chunk(Chunk.NEWLINE));

        companyInfoTable.addCell(getCell(chunkList, PdfPCell.ALIGN_LEFT));

        List<Chunk>chunkList1=new ArrayList<>();

        chunkList1.add(new Chunk(Constant.PDF_RECIEPTNO,fontTax));
        chunkList1.add(new Chunk(companyRecieptNo,fontTax));
        chunkList1.add(new Chunk(Chunk.NEWLINE));
        chunkList1.add(new Chunk(Constant.PDF_DATE,fontTax));
        chunkList1.add(new Chunk(companyDate,fontTax));
        chunkList1.add(new Chunk(Chunk.NEWLINE));
        chunkList1.add(new Chunk(Constant.PDF_PREPAREDBY, fontTax));
        if (prepraredBy!=null){
            chunkList1.add(new Chunk(prepraredBy, fontTax));
        }else {
            chunkList1.add(new Chunk("", fontTax));

        }

        chunkList1.add(new Chunk(Chunk.NEWLINE));

        companyInfoTable.addCell(getCell(chunkList1, PdfPCell.ALIGN_LEFT));


       /* Paragraph header1 = new Paragraph();
        header1.add(new Chunk(Constant.PDF_TITLE_BILLTO, fontHeader));
        header1.setAlignment(Element.ALIGN_CENTER);*/
        PdfPTable header1 = new PdfPTable(1);
        header1.setSpacingBefore(20);
        header1.setWidthPercentage(100);

        Paragraph paragraph1=new Paragraph(new Chunk(Constant.PDF_TITLE_BILLTO, fontHeader));
        PdfPCell header1Cell = new PdfPCell(paragraph1);
        header1Cell.setBorder(PdfCell.LEFT |PdfCell.TOP |PdfCell.BOTTOM |PdfCell.RIGHT);
        header1Cell.setFixedHeight(25);
        header1Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        header1.addCell(header1Cell);

        PdfPTable billToTable = new PdfPTable(2);
        billToTable.setSpacingBefore(10);
        billToTable.setWidths(new float[]{2, 2});
        billToTable.setWidthPercentage(100);


        String customerName="",customerAddress="",customerEmail="",customerPhoneNumber="";
        if (printData.getSupplierName()!=null)
            customerName=printData.getSupplierName();

        if (printData.getAddress()!=null)
            customerAddress=printData.getAddress();

        if (printData.getEmail()!=null)
            customerEmail=printData.getEmail();

        if (printData.getPhoneNo()!=null)
            customerPhoneNumber=printData.getPhoneNo();

        List<Chunk>billToC1List=new ArrayList<>();

        billToC1List.add(new Chunk(Constant.PDF_SUPPLIERNAME,fontTax));
        billToC1List.add(new Chunk(customerName,fontTax));
        billToC1List.add(new Chunk(Chunk.NEWLINE));
        billToC1List.add(new Chunk(Constant.PDF_ADDRESS,fontTax));
        billToC1List.add(new Chunk(customerAddress,fontTax));
        billToC1List.add(new Chunk(Chunk.NEWLINE));
        billToTable.addCell(getCell(billToC1List, PdfPCell.ALIGN_LEFT));

        List<Chunk>billToC2List=new ArrayList<>();

        billToC2List.add(new Chunk(Constant.PDF_EMAIL,fontTax));
        billToC2List.add(new Chunk(customerEmail,fontTax));
        billToC2List.add(new Chunk(Chunk.NEWLINE));
        billToC2List.add(new Chunk(Constant.PDF_PHONENO,fontTax));
        billToC2List.add(new Chunk(customerPhoneNumber,fontTax));
        billToC2List.add(new Chunk(Chunk.NEWLINE));

        billToTable.addCell(getCell(billToC2List, PdfPCell.ALIGN_LEFT));


       /* Paragraph header2 = new Paragraph();
        header2.add(new Chunk(Constant.PDF_TITLE_PAYMENTDETAIL, fontHeader));
        header2.setAlignment(Element.ALIGN_CENTER);*/
        PdfPTable header2 = new PdfPTable(1);
        header2.setSpacingBefore(20);
        header2.setWidthPercentage(100);
        Paragraph paragraph2=new Paragraph(new Chunk(Constant.PDF_TITLE_PAYMENTDETAIL, fontHeader));
        paragraph2.setAlignment(Element.ALIGN_CENTER);
        PdfPCell header2Cell = new PdfPCell(paragraph2);
        header2Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        header2Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        header2Cell.setBorder(PdfCell.LEFT |PdfCell.TOP |PdfCell.BOTTOM |PdfCell.RIGHT);
        header2Cell.setFixedHeight(25);
        header2.addCell(header2Cell);
        PdfPTable paymentDetailTable = new PdfPTable(2);
        paymentDetailTable.setSpacingBefore(10);
        paymentDetailTable.setWidths(new float[]{2, 2});
        paymentDetailTable.setWidthPercentage(100);

        String invNumber="",invDate="",invAmt="",invAmtPaid="",invBalanceAmt="";
        if (printData.getInvoiceNo()!=null)
            invNumber=printData.getInvoiceNo();

        if (printData.getInvoiceDate()!=null)
            invDate=printData.getInvoiceDate();

        invAmt=""+printData.getInvoiceAmount();
        invAmtPaid=""+printData.getAmountPaid();
        invBalanceAmt=""+printData.getBalance();

        List<Chunk>paymentDetailC1List=new ArrayList<>();

        paymentDetailC1List.add(new Chunk(Constant.PDF_INVOCIENO,fontTax));
        paymentDetailC1List.add(new Chunk(invNumber,fontTax));
        paymentDetailC1List.add(new Chunk(Chunk.NEWLINE));
        paymentDetailC1List.add(new Chunk(Constant.PDF_INVOICEDATE,fontTax));
        paymentDetailC1List.add(new Chunk(invDate,fontTax));
        paymentDetailC1List.add(new Chunk(Chunk.NEWLINE));
        paymentDetailTable.addCell(getCell(paymentDetailC1List, PdfPCell.ALIGN_LEFT));

        List<Chunk>paymentDetailC2List=new ArrayList<>();

        paymentDetailC2List.add(new Chunk(Constant.PDF_INVOCIEAMT,fontTax));
        paymentDetailC2List.add(new Chunk(invAmt,fontTax));
        paymentDetailC2List.add(new Chunk(Chunk.NEWLINE));
        paymentDetailC2List.add(new Chunk(Constant.PDF_INVOCIEAMTPAID,fontTax));
        paymentDetailC2List.add(new Chunk(invAmtPaid,fontTax));
        paymentDetailC2List.add(new Chunk(Chunk.NEWLINE));
        paymentDetailC2List.add(new Chunk(Constant.PDF_INVOICEBALANCEAMT,fontTax));
        paymentDetailC2List.add(new Chunk(invBalanceAmt,fontTax));


        paymentDetailTable.addCell(getCell(paymentDetailC2List, PdfPCell.ALIGN_LEFT));


        Paragraph header3 = new Paragraph();
        header3.add(Chunk.NEWLINE);
        header3.add(Chunk.NEWLINE);
        header3.add(new Chunk(Constant.PDF_PREPAREDBY, fontTax));
        header3.add(Chunk.NEWLINE);
        header3.add(Chunk.NEWLINE);
        header3.add(new Chunk(Constant.PDF_APPROCEBY, fontTax));
        header3.setAlignment(Element.ALIGN_LEFT);



        document.add(header);
        document.add(companyInfoTable);
        document.add(header1);
        document.add(billToTable);
        document.add(header2);
        document.add(paymentDetailTable);
        document.add(header3);


        document.close();
    }

    private PdfPCell getCell(String text, int alignment, Font font) {
        Phrase elements = new Phrase(new Chunk(text, font));
        PdfPCell cell = new PdfPCell(elements);
        cell.setPadding(0);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setBorderWidth(0.50f);
        return cell;
    }

    private PdfPCell getBottomTableCell(String text, int alignment, Font font) {
        Phrase elements = new Phrase(new Chunk(text, font));
        PdfPCell cell = new PdfPCell(elements);
        //cell.setPadding(2);
        cell.setLeading(10,0);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setBorderWidth(0.50f);
        return cell;
    }

    private PdfPCell getDescriptionCell(String text, int alignment, Font font) {
        Phrase elements = new Phrase(new Chunk(text, font));
        PdfPCell cell = new PdfPCell(elements);
        cell.setPadding(1);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.LEFT | PdfPCell.RIGHT);
        return cell;
    }

    private PdfPCell addAlignmetToCell(String text, int alignment, Font font) {
        if (text == null) {
            text = "";
        }
        Phrase elements = new Phrase(new Chunk(text, font));
        PdfPCell cell = new PdfPCell(elements);
        cell.setPadding(2);
        cell.setBorder(PdfPCell.LEFT | PdfPCell.RIGHT | PdfPCell.TOP | PdfPCell.BOTTOM);
        //cell.setBorderWidth(0.50f);
        cell.setHorizontalAlignment(alignment);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        // cell.setFixedHeight(30);
        return cell;
    }


    private PdfPCell getCell(List<Chunk> chunks, int alignment) {
        Phrase phrase=new Phrase();
        for (Chunk chunk : chunks) {
            phrase.add(chunk);

        }
        PdfPCell cell = new PdfPCell(phrase);
        cell.setPadding(2);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setLeading(20,0);
        return cell;
    }

    private PdfPCell getTableCell(Image image, int horizontalAlignment, int vertiaclAlignment) {

        PdfPCell cell = new PdfPCell(image);
        cell.setPadding(0);
        cell.setHorizontalAlignment(horizontalAlignment);
        cell.setVerticalAlignment(vertiaclAlignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }


}
