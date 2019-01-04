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
import in.hiaccounts.hinext.generaltransaction.model.checkout.GTJournalData;
import in.hiaccounts.hinext.generaltransaction.model.payee_account.Account;
import in.hiaccounts.hinext.generaltransaction.model.response.CompanyData;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.SharedPreference;

/**
 * Created by Prateek on 6/1/2017.
 */
public class GTJournalVoucherPdfImpl implements GTJournalVoucherPdf {

    @Override
    public void generateJournalVoucherPdf(Context context, GTJournalData printData, OutputStream outPutStream, InputStream inputStream) throws IOException, DocumentException {

        Document document = new Document((PageSize.A4));
        PdfWriter.getInstance(document, outPutStream);
        document.open();

        Font fontHeader = new Font(Font.HELVETICA, 12, Font.BOLD);
        Font fontTableHeader = new Font(Font.HELVETICA, 8, Font.BOLD, Color.BLACK);
        Font fontTable = new Font(Font.HELVETICA, 10);
        Font fontTaxBold = new Font(Font.HELVETICA, 10, Font.BOLD, Color.BLACK);
        Font fontTax = new Font(Font.HELVETICA, 10);
        Chunk glue = new Chunk(new VerticalPositionMark());


        Paragraph paragraph=new Paragraph(new Chunk(Constant.PDF_TITLE_GTJOURNAL, fontHeader));
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.add(lineSeperator());

        String receiptNo="",companyDate="",prepraredBy="";
        if (printData.getJeNo()!=null){
            receiptNo=printData.getJeNo();
        }
      /*  final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        companyDate=day+"-"+(month+1)+"-"+year;*/
      if (printData.getJepDate()!=null){
          companyDate=printData.getJepDate();
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
            chunks.add(new Chunk("Transaction ID",fontTax));
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

        PdfPTable actDescriptionTable = new PdfPTable(4);
        actDescriptionTable.setWidths(new float[]{1.50f, 1.50f, 1f,1f});
        actDescriptionTable.setSpacingBefore(20);
        actDescriptionTable.setWidthPercentage(100);
        PdfPCell cell;

        cell = addAlignmetToCell(Constant.PDF_ACCOUNTNAME, Element.ALIGN_LEFT, fontTaxBold);
        actDescriptionTable.addCell(cell);

        cell = addAlignmetToCell(Constant.PDF_ACCOUNTDESCRIPTION, Element.ALIGN_LEFT, fontTaxBold);
        actDescriptionTable.addCell(cell);

        cell = addAlignmetToCell(Constant.PDF_DEBIT, Element.ALIGN_LEFT, fontTaxBold);
        actDescriptionTable.addCell(cell);

        cell = addAlignmetToCell(Constant.PDF_CREDIT, Element.ALIGN_LEFT, fontTaxBold);
        actDescriptionTable.addCell(cell);

        List<Account> accountList = printData.getJournalEntryDetailsList();

        for (Account account : accountList) {

            if (account.getAccountName() != null)
                actDescriptionTable.addCell(getDescriptionCell(account.getAccountName(), PdfPCell.ALIGN_LEFT, fontTable));

            if (account.getAccountDescription() != null)
                actDescriptionTable.addCell(getDescriptionCell(account.getAccountDescription(), PdfPCell.ALIGN_LEFT, fontTable));

            actDescriptionTable.addCell(getDescriptionCell(""+account.getDebitAmount(), PdfPCell.ALIGN_LEFT, fontTable));

            actDescriptionTable.addCell(getDescriptionCell(""+account.getCreditAmount(), PdfPCell.ALIGN_LEFT, fontTable));
        }





        Paragraph paragraph2=new Paragraph();
        paragraph2.add(Chunk.NEWLINE);
        paragraph2.setAlignment(Element.ALIGN_LEFT);

        paragraph2.add(new Chunk(Constant.PDF_TOTALDEBIT +""+printData.getTotalDebit(), fontTable));
        paragraph2.add(Chunk.NEWLINE);
        paragraph2.add(new Chunk(Constant.PDF_TOTALCREDIT +""+printData.getTotalCredit(), fontTable));








        Paragraph paragraph3=new Paragraph();
        paragraph3.add(lineSeperator());

        PdfPTable header3 = new PdfPTable(2);
        header3.setSpacingBefore(200);
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







        document.add(paragraph);
        document.add(tableCompanyInfo);
        document.add(actDescriptionTable);
        document.add(paragraph2);
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
        cell.setPadding(5);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(Rectangle.BOX);

        return cell;
    }

    private PdfPCell addAlignmetToCell(String text, int alignment, Font font) {
        if (text == null) {
            text = "";
        }
        Phrase elements = new Phrase(new Chunk(text, font));
        PdfPCell cell = new PdfPCell(elements);
        cell.setPadding(5);
        cell.setBorder(Rectangle.BOX);
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
