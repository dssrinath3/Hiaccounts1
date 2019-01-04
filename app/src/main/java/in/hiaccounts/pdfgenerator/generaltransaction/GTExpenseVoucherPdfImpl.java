package in.hiaccounts.pdfgenerator.generaltransaction;

import android.content.Context;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;
import com.lowagie.text.pdf.draw.VerticalPositionMark;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import harmony.java.awt.Color;
import in.hiaccounts.hinext.generaltransaction.model.response.CompanyData;
import in.hiaccounts.hinext.generaltransaction.model.response.SaveResponseData;
import in.hiaccounts.hinext.generaltransaction.model.response.SelectedAccountList;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.SharedPreference;

/**
 * Created by Prateek on 6/1/2017.
 */
public class GTExpenseVoucherPdfImpl implements GTExpenseVoucherPdf {

    @Override
    public void generateExpenseVoucherPdf(Context context, SaveResponseData printData, OutputStream outPutStream, InputStream inputStream) throws IOException, DocumentException {

        Document document = new Document((PageSize.A4));
        PdfWriter.getInstance(document, outPutStream);
        document.open();

        Font fontHeader = new Font(Font.HELVETICA, 12, Font.BOLD);
        Font fontTableHeader = new Font(Font.HELVETICA, 8, Font.BOLD, Color.BLACK);
        Font fontTable = new Font(Font.HELVETICA, 10);
        Font fontTaxBold = new Font(Font.HELVETICA, 10, Font.BOLD, Color.BLACK);
        Font fontTax = new Font(Font.HELVETICA, 10);
        Chunk glue = new Chunk(new VerticalPositionMark());


        Paragraph paragraph=new Paragraph(new Chunk(Constant.PDF_TITLE_GTEXPENSES, fontHeader));
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.add(lineSeperator());

        String receiptNo="",companyDate="",prepraredBy="";
        if (printData.getOpNo()!=null){
            receiptNo=printData.getOpNo();
        }
      /*  final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        companyDate=day+"-"+(month+1)+"-"+year;*/
      if (printData.getOpDate()!=null){
          companyDate=printData.getOpDate();
      }

        SharedPreference sharedPreference=SharedPreference.getInstance(context);
        prepraredBy=sharedPreference.getData(Constant.USERNAME);

        CompanyData companyData=printData.getCompanyData();
        List<Chunk>chunks=new ArrayList<>();
        List<Chunk>chunks1=new ArrayList<>();
        List<Chunk>chunks2=new ArrayList<>();
        PdfPTable tableCompanyInfo = new PdfPTable(3);

        if (companyData!=null){
            chunks.add(new Chunk("Company Name",fontTax));
            chunks.add(new Chunk(Chunk.NEWLINE));
            chunks.add(new Chunk("Company No",fontTax));
            chunks.add(new Chunk(Chunk.NEWLINE));
            chunks.add(new Chunk("GST No",fontTax));
            chunks.add(new Chunk(Chunk.NEWLINE));
            chunks.add(new Chunk("Receipt No",fontTax));
            chunks.add(new Chunk(Chunk.NEWLINE));
            chunks.add(new Chunk("Date",fontTax));
            chunks.add(new Chunk(Chunk.NEWLINE));
            chunks.add(new Chunk("Prepared By",fontTax));
            chunks.add(new Chunk(Chunk.NEWLINE));

            tableCompanyInfo.setSpacingBefore(10);
            tableCompanyInfo.setSpacingAfter(10);
            tableCompanyInfo.setWidths(new float[]{0.50f,0.20f,2});
            tableCompanyInfo.setWidthPercentage(100);

            tableCompanyInfo.addCell(getCell(chunks, PdfPCell.ALIGN_LEFT));

            chunks1.add(new Chunk(" : ",fontTax));
            chunks1.add(new Chunk(Chunk.NEWLINE));
            chunks1.add(new Chunk(" : ",fontTax));
            chunks1.add(new Chunk(Chunk.NEWLINE));
            chunks1.add(new Chunk(" : ",fontTax));
            chunks1.add(new Chunk(Chunk.NEWLINE));
            chunks1.add(new Chunk(" : ",fontTax));
            chunks1.add(new Chunk(Chunk.NEWLINE));
            chunks1.add(new Chunk(" : ",fontTax));
            chunks1.add(new Chunk(Chunk.NEWLINE));
            chunks1.add(new Chunk(" : ",fontTax));
            chunks1.add(new Chunk(Chunk.NEWLINE));
            tableCompanyInfo.addCell(getCell(chunks1, PdfPCell.ALIGN_LEFT));

            if (companyData.getCompanyName()!=null)
                chunks2.add(new Chunk(companyData.getCompanyName(),fontTax));
            chunks2.add(new Chunk(Chunk.NEWLINE));
            if (companyData.getCompanyNo()!=null)
                chunks2.add(new Chunk(companyData.getCompanyNo(),fontTax));
            chunks2.add(new Chunk(Chunk.NEWLINE));
            if (companyData.getRegisterNo()!=null)
                chunks2.add(new Chunk(companyData.getRegisterNo(),fontTax));
            chunks2.add(new Chunk(Chunk.NEWLINE));
            chunks2.add(new Chunk(receiptNo,fontTax));
            chunks2.add(new Chunk(Chunk.NEWLINE));
            chunks2.add(new Chunk(companyDate,fontTax));
            chunks2.add(new Chunk(Chunk.NEWLINE));
            chunks2.add(new Chunk(prepraredBy,fontTax));
            tableCompanyInfo.addCell(getCell(chunks2, PdfPCell.ALIGN_LEFT));

        }




        Paragraph paragraph1=new Paragraph();
        LineSeparator lineSeparator=new LineSeparator();
        lineSeparator.setOffset(10);
        paragraph1.add(lineSeparator);
        paragraph1.add(Chunk.NEWLINE);
        paragraph1.add(new Chunk(Constant.PDF_HEADER_GTPAYTO, fontHeader));
        paragraph1.setAlignment(Element.ALIGN_CENTER);

        Paragraph para=new Paragraph();
        if (printData.getSupplierName()!=null)
            para.add(new Chunk(new Chunk("Name   : "+printData.getSupplierName(),fontTax)));
        para.add(lineSeperator());



        PdfPTable actDescriptionTable = new PdfPTable(3);
        actDescriptionTable.setWidths(new float[]{1.50f, 1.50f, 1.50f});
        actDescriptionTable.setSpacingBefore(20);

        actDescriptionTable.setWidthPercentage(100);
        PdfPCell cell;

        cell = addAlignmetToCell(Constant.PDF_ACCOUNTNAME, Element.ALIGN_LEFT, fontTaxBold);
        actDescriptionTable.addCell(cell);

        cell = addAlignmetToCell(Constant.PDF_ACCOUNTDESCRIPTION, Element.ALIGN_LEFT, fontTaxBold);
        actDescriptionTable.addCell(cell);

        cell = addAlignmetToCell(Constant.PDF_ACCOUNTEXTAX, Element.ALIGN_LEFT, fontTaxBold);
        actDescriptionTable.addCell(cell);

        List<SelectedAccountList> accountList = printData.getSelectedAccountList();

        for (SelectedAccountList account : accountList) {

            if (account.getAccountName() != null)
                actDescriptionTable.addCell(getDescriptionCell(account.getAccountName(), PdfPCell.ALIGN_LEFT, fontTable));

            if (account.getAccountDescription() != null)
                actDescriptionTable.addCell(getDescriptionCell(account.getAccountDescription(), PdfPCell.ALIGN_LEFT, fontTable));

            if (account.getAmtexclusivetax() != null)
                actDescriptionTable.addCell(getDescriptionCell(""+account.getAmtexclusivetax(), PdfPCell.ALIGN_LEFT, fontTable));
        }





        Paragraph paragraph2=new Paragraph();
        paragraph2.setSpacingBefore(10);
        LineSeparator lineSeparator1=new LineSeparator();
        lineSeparator1.setOffset(10);
        paragraph2.add(lineSeparator1);
        paragraph2.add(Chunk.NEWLINE);
        paragraph2.setAlignment(Element.ALIGN_CENTER);

        paragraph2.add(new Chunk(Constant.PDF_HEADER_PAYMENTDETAILS, fontHeader));

        PdfPTable paymentDetailsTable = new PdfPTable(2);
        paymentDetailsTable.setSpacingBefore(5);
        paymentDetailsTable.setWidths(new float[]{2, 2});
        paymentDetailsTable.setWidthPercentage(100);
        List<Chunk>chunkList=new ArrayList<>();

        chunkList.add(new Chunk(Constant.PDF_INVOCIENO,fontTax));

        if (printData.getSupplierInvNo()!=null)
        chunkList.add(new Chunk(printData.getSupplierInvNo(),fontTax));
        chunkList.add(new Chunk(Chunk.NEWLINE));

        chunkList.add(new Chunk(Constant.PDF_INVDATE,fontTax));
        if (printData.getOpDate()!=null)
            chunkList.add(new Chunk(printData.getOpDate(),fontTax));
        chunkList.add(new Chunk(Chunk.NEWLINE));


        paymentDetailsTable.addCell(getCell(chunkList, PdfPCell.ALIGN_LEFT));

        List<Chunk>chunkList1=new ArrayList<>();

        chunkList1.add(new Chunk(Constant.PDF_AMTTAXABLE,fontTax));
        if (printData.getTotalCheckOutamt()!=null)
        chunkList1.add(new Chunk(""+printData.getTotalCheckOutamt(),fontTax));
        chunkList1.add(new Chunk(Chunk.NEWLINE));

        chunkList1.add(new Chunk(Constant.PDF_AMTGST,fontTax));

        String taxType="";
        if (printData.getTaxType()!=null){
            taxType=printData.getTaxType();
        }

        String taxAmt="";
        if (printData.getTotalTaxAmt()!=null){

            if (taxType.equals("IGST")){
                taxAmt=""+printData.getTotalTaxAmt();
            }
            if (taxType.equals("CGST:SGST/UGST")){
                taxAmt=""+(printData.getTotalTaxAmt()/2)+"+"+(printData.getTotalTaxAmt()/2);
            }

        }
        chunkList1.add(new Chunk(taxType+" : "+taxAmt,fontTax));
        chunkList1.add(new Chunk(Chunk.NEWLINE));

        chunkList1.add(new Chunk(Constant.PDF_AMTTOTAL,fontTax));
        if (printData.getTotalCheckOutamt()!=null)
            chunkList1.add(new Chunk(""+printData.getTotalCheckOutamt(),fontTax));
        chunkList1.add(new Chunk(Chunk.NEWLINE));

        chunkList1.add(new Chunk(Constant.PDF_INVOCIEAMTPAID,fontTax));
        if (printData.getAmountPaid()!=null)
        chunkList1.add(new Chunk(""+printData.getAmountPaid(),fontTax));
        chunkList1.add(new Chunk(Chunk.NEWLINE));

        chunkList1.add(new Chunk(Constant.PDF_PAYMENTTYPE,fontTax));
        if (printData.getPaymentType()!=null)
        chunkList1.add(new Chunk(printData.getPaymentType(),fontTax));
        chunkList1.add(new Chunk(Chunk.NEWLINE));


        paymentDetailsTable.addCell(getCell(chunkList1, PdfPCell.ALIGN_LEFT));

        Paragraph paragraph3=new Paragraph();
        paragraph3.add(lineSeperator());

        PdfPTable header3 = new PdfPTable(3);
        header3.setSpacingBefore(100);
        header3.setWidthPercentage(100);
        PdfPCell header3Cell;
        Paragraph paragraph4=new Paragraph(new Chunk(Constant.PDF_APPROCEBY, fontHeader));
        header3Cell = new PdfPCell(paragraph4);
        header3Cell.setBorder(Rectangle.NO_BORDER);
        header3Cell.setFixedHeight(25);
        header3Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        header3.addCell(header3Cell);

        Paragraph paragraph5=new Paragraph(new Chunk(Constant.PDF_PREPAREDBY, fontHeader));
        header3Cell = new PdfPCell(paragraph5);
        header3Cell.setBorder(Rectangle.NO_BORDER);
        header3Cell.setFixedHeight(25);
        header3Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        header3.addCell(header3Cell);

        Paragraph paragraph6=new Paragraph(new Chunk(Constant.PDF_RECEIVEDBY, fontHeader));
        header3Cell = new PdfPCell(paragraph6);
        header3Cell.setBorder(Rectangle.NO_BORDER);
        header3Cell.setFixedHeight(25);
        header3Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        header3.addCell(header3Cell);





        document.add(paragraph);
        document.add(tableCompanyInfo);
        document.add(paragraph1);
        document.add(para);
        document.add(actDescriptionTable);
        document.add(paragraph2);
        document.add(paymentDetailsTable);
        document.add(paragraph3);
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

    public LineSeparator lineSeperator(){
        LineSeparator line = new LineSeparator();

        line.setOffset(-10);
        return line;
    }

    private PdfPCell getDescriptionCell(String text, int alignment, Font font) {
        Phrase elements = new Phrase(new Chunk(text, font));
        PdfPCell cell = new PdfPCell(elements);
        cell.setPadding(1);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }

    private PdfPCell addAlignmetToCell(String text, int alignment, Font font) {
        if (text == null) {
            text = "";
        }
        Phrase elements = new Phrase(new Chunk(text, font));
        PdfPCell cell = new PdfPCell(elements);
        cell.setPadding(2);
        cell.setBorder(Rectangle.NO_BORDER);
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
        cell.setLeading(15,0);
        return cell;
    }

}
