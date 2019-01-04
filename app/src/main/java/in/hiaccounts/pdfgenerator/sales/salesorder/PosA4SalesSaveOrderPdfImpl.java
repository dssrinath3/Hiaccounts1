package in.hiaccounts.pdfgenerator.sales.salesorder;

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
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import harmony.java.awt.Color;
import in.hiaccounts.R;
import in.hiaccounts.hinext.checkout.model.sales.Checkout_Sales_ResponseData;
import in.hiaccounts.hinext.purchase.model.purchase_saveresponses.CompanyData;
import in.hiaccounts.hinext.sales.model.save_response.SelectedItemsList;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.EnglishNumberToWords;

/**
 * Created by Prateek on 6/1/2017.
 */
public class PosA4SalesSaveOrderPdfImpl implements PosSalesSaveOrderPdf {

    @Override
    public void generateSalesSaveOrder(Context context, Checkout_Sales_ResponseData retailPrintDTO, OutputStream outPutStream, InputStream inputStream) throws IOException, DocumentException {

        Document document = new Document((PageSize.A4));
        PdfWriter.getInstance(document, outPutStream);
        document.open();


        Font fontHeader = new Font(Font.HELVETICA, 12, Font.BOLD);

        Font fontTableHeader = new Font(Font.HELVETICA, 8, Font.BOLD, Color.BLACK);
        Font fontTable = new Font(Font.HELVETICA, 7);

        Font fontTaxBold = new Font(Font.HELVETICA, 10, Font.BOLD, Color.BLACK);
        Font fontTax = new Font(Font.HELVETICA, 10);


        Paragraph header = new Paragraph();
        header.add(new Chunk(Constant.PDF_TITLE_SALESSAVEORDER, fontHeader));
        header.setAlignment(Element.ALIGN_CENTER);

        PdfPTable compnayInfo = new PdfPTable(2);
        if (retailPrintDTO.getCompanyData() != null) {

            compnayInfo.setWidths(new float[]{2, 4});
            compnayInfo.setWidthPercentage(100);
            compnayInfo.setSpacingBefore(20);

            List<Chunk> billingInoChunks = new ArrayList<>();
            if (retailPrintDTO.getCompanyData() != null) {
               in.hiaccounts.hinext.checkout.model.sales.CompanyData companyData = retailPrintDTO.getCompanyData();
                billingInoChunks.add(new Chunk(Constant.PDF_GSTIN, fontTaxBold));
                billingInoChunks.add(new Chunk(companyData.getRegisterNo(), fontTax));
                billingInoChunks.add(new Chunk(Chunk.NEWLINE));

                billingInoChunks.add(new Chunk(Constant.PDF_NAME, fontTaxBold));
                if (companyData.getCompanyName() != null) {
                    billingInoChunks.add(new Chunk(companyData.getCompanyName(), fontTax));
                }
                billingInoChunks.add(new Chunk(Chunk.NEWLINE));

                billingInoChunks.add(new Chunk(Constant.PDF_ADDRESS, fontTaxBold));
                if (companyData.getAddress() != null)
                    billingInoChunks.add(new Chunk(companyData.getAddress(), fontTax));
                billingInoChunks.add(new Chunk(Chunk.NEWLINE));

                billingInoChunks.add(new Chunk(Constant.PDF_STATE, fontTaxBold));
                if (companyData.getStateName() != null)
                    billingInoChunks.add(new Chunk(companyData.getStateName(), fontTax));
                billingInoChunks.add(new Chunk(Chunk.NEWLINE));

                billingInoChunks.add(new Chunk(Constant.PDF_TITLE_SALESSAVEORDERNO + " : ", fontTaxBold));
                if (retailPrintDTO.getSoNo() != null)
                    billingInoChunks.add(new Chunk(String.valueOf(retailPrintDTO.getSoNo()), fontTax));
                billingInoChunks.add(new Chunk(Chunk.NEWLINE));


                billingInoChunks.add(new Chunk(Constant.PDF_INVDATE, fontTaxBold));
                if (retailPrintDTO.getDate() != null)
                    billingInoChunks.add(new Chunk(retailPrintDTO.getDate(), fontTax));
            }
            compnayInfo.addCell(getCell(billingInoChunks, PdfPCell.ALIGN_LEFT));


            if (inputStream != null) {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                Image image = Image.getInstance(stream.toByteArray());
                image.scaleAbsolute(50, 50);
                compnayInfo.addCell(getTableCell(image, Element.ALIGN_RIGHT, Element.ALIGN_RIGHT));
            } else {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_app_icon);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                Image image = Image.getInstance(stream.toByteArray());
                image.scaleAbsolute(50, 50);
                compnayInfo.addCell(getTableCell(image, Element.ALIGN_RIGHT, Element.ALIGN_RIGHT));
            }


        }

        /*    Paragraph compnayInfo = new Paragraph();
        if (retailPrintDTO.getCompanyData() != null) {
            CompanyData companyData = retailPrintDTO.getCompanyData();
            compnayInfo.setAlignment(Element.ALIGN_LEFT);
            compnayInfo.setSpacingBefore(30);
            compnayInfo.setLeading(10,0);


            compnayInfo.add(new Chunk(Constant.PDF_GSTIN, fontTaxBold));
            compnayInfo.add(new Chunk(" : ",fontTax));
            compnayInfo.add(new Chunk(companyData.getRegisterNo(), fontTax));
            compnayInfo.add(Chunk.NEWLINE);

            compnayInfo.add(new Phrase(new Chunk(Constant.PDF_NAME, fontTaxBold)));
            compnayInfo.add(new Chunk(" : ",fontTax));
            if (companyData.getCompanyName() != null) {
                compnayInfo.add(new Phrase(new Chunk(companyData.getCompanyName(), fontTax)));
            }
            compnayInfo.add(Chunk.NEWLINE);

            compnayInfo.add(new Phrase(new Chunk(Constant.PDF_ADDRESS, fontTaxBold)));
            compnayInfo.add(new Chunk(" : ",fontTax));
            if (companyData.getAddress() != null)
                compnayInfo.add(new Phrase(new Chunk(companyData.getAddress(), fontTax)));
            compnayInfo.add(Chunk.NEWLINE);


            compnayInfo.add(new Phrase(new Chunk(Constant.PDF_STATE, fontTaxBold)));
            compnayInfo.add(new Chunk(" : ",fontTax));
            if (companyData.getState() != null)
                if (companyData.getState().getStateName() != null)
                    compnayInfo.add(new Phrase(new Chunk(companyData.getState().getStateName(), fontTax)));
            compnayInfo.add(Chunk.NEWLINE);
        }

        if (retailPrintDTO.getSiData() != null) {
            compnayInfo.add(new Phrase(new Chunk(Constant.PDF_TITLE_SALESSAVEORDERNO, fontTaxBold)));
            compnayInfo.add(new Chunk(" : ",fontTax));
            if (retailPrintDTO.getSoNo() != null)
                compnayInfo.add(new Phrase(new Chunk(retailPrintDTO.getSoNo(), fontTax)));
            compnayInfo.add(Chunk.NEWLINE);
        }

        compnayInfo.add(new Phrase(new Chunk(Constant.PDF_INVDATE, fontTaxBold)));
        compnayInfo.add(new Chunk(" : ",fontTax));
        if (retailPrintDTO.getDate() != null)
            compnayInfo.add(new Phrase(new Chunk(retailPrintDTO.getDate(), fontTax)));
        compnayInfo.add(Chunk.NEWLINE);
*/

        PdfPTable billInfoTable = new PdfPTable(2);
        billInfoTable.setSpacingBefore(20);
        billInfoTable.setWidths(new float[]{2,2});
        billInfoTable.setWidthPercentage(100);

        billInfoTable.addCell(getCell(Constant.PDF_BIILING_TITLE, PdfPCell.ALIGN_LEFT, fontHeader));
        billInfoTable.addCell(getCell(Constant.PDF_SHIPPING_TITLE, PdfPCell.ALIGN_LEFT, fontHeader));

        String inventGSTIN = "", inventCustomerName = "", inventAddress = "", inventState = "", inventNo = "", inventDate = "";
        if (retailPrintDTO.getSiData() != null)

        {
            if (retailPrintDTO.getSiData().getCustomerGst() != null) {
                inventGSTIN = retailPrintDTO.getSiData().getCustomerGst();
            }
            if (retailPrintDTO.getSiData().getCutomerName() != null) {
                inventCustomerName = retailPrintDTO.getSiData().getCutomerName();
            }
            if (retailPrintDTO.getSiData().getCustomerAddress() != null) {
                inventAddress = String.valueOf(retailPrintDTO.getSiData().getCustomerAddress());

            }
            if (retailPrintDTO.getSiData().getCustomerState() != null) {

                inventState = retailPrintDTO.getSiData().getCustomerState();

            }
            if (retailPrintDTO.getSoNo() != null) {

                inventNo = String.valueOf(retailPrintDTO.getSoNo());

            }
            if (retailPrintDTO.getDate() != null) {

                inventDate = retailPrintDTO.getDate();

            }
        }
        String locationGSTIN = "", locationCustomerName = "", locationAddress = "", locationState = "", locationInNo = "", locationInDate = "";

        if (retailPrintDTO.getSiData() != null)

        {
            if (retailPrintDTO.getSiData().getLocationGst() != null) {
                locationGSTIN = retailPrintDTO.getSiData().getLocationGst();
            }
            if (retailPrintDTO.getSiData().getCutomerName() != null) {
                locationCustomerName = retailPrintDTO.getSiData().getCutomerName();
            }
            if (retailPrintDTO.getSiData().getLocationAddress() != null) {
                locationAddress = String.valueOf(retailPrintDTO.getSiData().getLocationAddress());

            }
            if (retailPrintDTO.getSiData().getLocationState() != null) {

                locationState = retailPrintDTO.getSiData().getLocationState();

            }
            if (retailPrintDTO.getSoNo() != null) {
                locationInNo = String.valueOf(retailPrintDTO.getSoNo());

            }
        }
        if (retailPrintDTO.getDate() != null)

        {
            locationInDate = retailPrintDTO.getDate();
        }

        List<Chunk>chunkList=new ArrayList<>();

        chunkList.add(new Chunk(Constant.PDF_GSTIN,fontTaxBold));
        chunkList.add(new Chunk(inventGSTIN,fontTax));
        chunkList.add(new Chunk(Chunk.NEWLINE));
        chunkList.add(new Chunk(Constant.PDF_NAME,fontTaxBold));
        chunkList.add(new Chunk(inventCustomerName,fontTax));
        chunkList.add(new Chunk(Chunk.NEWLINE));
        chunkList.add(new Chunk(Constant.PDF_ADDRESS, fontTaxBold));
        chunkList.add(new Chunk(inventAddress, fontTax));
        chunkList.add(new Chunk(Chunk.NEWLINE));
        chunkList.add(new Chunk(Constant.PDF_STATE, fontTaxBold));
        chunkList.add(new Chunk(inventState, fontTax));
        chunkList.add(new Chunk(Chunk.NEWLINE));
        chunkList.add(new Chunk(Constant.PDF_TITLE_SALESSAVEORDERNO, fontTaxBold));
        chunkList.add(new Chunk(inventNo, fontTax));
        chunkList.add(new Chunk(Chunk.NEWLINE));
        chunkList.add(new Chunk(Constant.PDF_INVDATE, fontTaxBold));
        chunkList.add(new Chunk(inventDate, fontTax));

        billInfoTable.addCell(getCell(chunkList, PdfPCell.ALIGN_LEFT));

        List<Chunk>shiipedChunkList=new ArrayList<>();

        shiipedChunkList.add(new Chunk(Constant.PDF_GSTIN,fontTaxBold));
        shiipedChunkList.add(new Chunk(locationGSTIN,fontTax));
        shiipedChunkList.add(new Chunk(Chunk.NEWLINE));
        shiipedChunkList.add(new Chunk(Constant.PDF_NAME,fontTaxBold));
        shiipedChunkList.add(new Chunk(locationCustomerName,fontTax));
        shiipedChunkList.add(new Chunk(Chunk.NEWLINE));
        shiipedChunkList.add(new Chunk(Constant.PDF_ADDRESS, fontTaxBold));
        shiipedChunkList.add(new Chunk(locationAddress, fontTax));
        shiipedChunkList.add(new Chunk(Chunk.NEWLINE));
        shiipedChunkList.add(new Chunk(Constant.PDF_STATE, fontTaxBold));
        shiipedChunkList.add(new Chunk(locationState, fontTax));
        shiipedChunkList.add(new Chunk(Chunk.NEWLINE));
        shiipedChunkList.add(new Chunk(Constant.PDF_INVNO, fontTaxBold));
        shiipedChunkList.add(new Chunk(locationInNo, fontTax));
        shiipedChunkList.add(new Chunk(Chunk.NEWLINE));
        shiipedChunkList.add(new Chunk(Constant.PDF_INVDATE, fontTaxBold));
        shiipedChunkList.add(new Chunk(locationInDate, fontTax));

        billInfoTable.addCell(getCell(shiipedChunkList, PdfPCell.ALIGN_LEFT));


        PdfPTable itemDescriptionTable = new PdfPTable(17);
        itemDescriptionTable.setWidths(new float[]{0.75f, 3.50f, 1.50f, 0.75f, 0.75f, 1, 1, 1, 1.20f, 1, 1.20f, 1, 1.20f, 1, 1.20f, 1, 1.20f});
        itemDescriptionTable.setSpacingBefore(20);
        itemDescriptionTable.setWidthPercentage(100);
        // itemDescriptionTable.setLockedWidth(true);
        //itemDescriptionTable.setTotalWidth(550f);
        PdfPCell cell;
        cell = addAlignmetToCell(Constant.PDF_ITEMDESC_SNO, Element.ALIGN_CENTER, fontTableHeader);
        cell.setRowspan(2);
        itemDescriptionTable.addCell(cell);
        cell = addAlignmetToCell(Constant.PDF_ITEMDESC_DESCRIPTION, Element.ALIGN_CENTER, fontTableHeader);
        cell.setRowspan(2);
        itemDescriptionTable.addCell(cell);
        cell = addAlignmetToCell(Constant.PDF_ITEMDESC_HSNCODE, Element.ALIGN_CENTER, fontTableHeader);
        cell.setRowspan(2);
        itemDescriptionTable.addCell(cell);
        cell = addAlignmetToCell(Constant.PDF_ITEMDESC_QTY, Element.ALIGN_CENTER, fontTableHeader);
        cell.setRowspan(2);
        itemDescriptionTable.addCell(cell);
        cell = addAlignmetToCell(Constant.PDF_ITEMDESC_UNIT, Element.ALIGN_CENTER, fontTableHeader);
        cell.setRowspan(2);
        itemDescriptionTable.addCell(cell);
        cell = addAlignmetToCell(Constant.PDF_ITEMDESC_RATE, Element.ALIGN_CENTER, fontTableHeader);
        cell.setRowspan(2);
        itemDescriptionTable.addCell(cell);
        cell =addAlignmetToCell(Constant.PDF_ITEMDESC_TOTAL, Element.ALIGN_CENTER, fontTableHeader);
        cell.setRowspan(2);
        itemDescriptionTable.addCell(cell);
        cell = addAlignmetToCell(Constant.PDF_ITEMDESC_DISCOUNT, Element.ALIGN_CENTER, fontTableHeader);
        cell.setRowspan(2);
        itemDescriptionTable.addCell(cell);
        cell = addAlignmetToCell(Constant.PDF_ITEMDESC_TAXVALUE, Element.ALIGN_CENTER, fontTableHeader);
        cell.setRowspan(2);
        itemDescriptionTable.addCell(cell);
        cell = addAlignmetToCell(Constant.PDF_ITEMDESC_CGST, Element.ALIGN_CENTER, fontTableHeader);
        cell.setColspan(2);
        itemDescriptionTable.addCell(cell);

        cell =

                addAlignmetToCell(Constant.PDF_ITEMDESC_SGST, Element.ALIGN_CENTER, fontTableHeader);
        cell.setColspan(2);
        itemDescriptionTable.addCell(cell);

        cell =

                addAlignmetToCell(Constant.PDF_ITEMDESC_IGST, Element.ALIGN_CENTER, fontTableHeader);
        cell.setColspan(2);
        itemDescriptionTable.addCell(cell);

        cell =

                addAlignmetToCell(Constant.PDF_ITEMDESC_CESS, Element.ALIGN_CENTER, fontTableHeader);
        cell.setColspan(2);
        itemDescriptionTable.addCell(cell);

        itemDescriptionTable.addCell(

                addAlignmetToCell(Constant.PDF_ITEMDESC_RATE, Element.ALIGN_CENTER, fontTableHeader));
        itemDescriptionTable.addCell(

                addAlignmetToCell(Constant.PDF_ITEMDESC_AMOUNT, Element.ALIGN_CENTER, fontTableHeader));
        itemDescriptionTable.addCell(

                addAlignmetToCell(Constant.PDF_ITEMDESC_RATE, Element.ALIGN_CENTER, fontTableHeader));
        itemDescriptionTable.addCell(

                addAlignmetToCell(Constant.PDF_ITEMDESC_AMOUNT, Element.ALIGN_CENTER, fontTableHeader));
        itemDescriptionTable.addCell(

                addAlignmetToCell(Constant.PDF_ITEMDESC_RATE, Element.ALIGN_CENTER, fontTableHeader));
        itemDescriptionTable.addCell(

                addAlignmetToCell(Constant.PDF_ITEMDESC_AMOUNT, Element.ALIGN_CENTER, fontTableHeader));
        itemDescriptionTable.addCell(

                addAlignmetToCell(Constant.PDF_ITEMDESC_RATE, Element.ALIGN_CENTER, fontTableHeader));
        itemDescriptionTable.addCell(

                addAlignmetToCell(Constant.PDF_ITEMDESC_AMOUNT, Element.ALIGN_CENTER, fontTableHeader));

        String itemTaxableValue = "";
        List<SelectedItemsList> itemsList = retailPrintDTO.getSiData().getSelectedItemsList();
        int j = 1;
        for (
                SelectedItemsList selectedItem : itemsList)

        {
            itemDescriptionTable.addCell(getDescriptionCell("" + j, PdfCell.ALIGN_CENTER, fontTable));
            j++;
            String description = "";
            String itemName = "";
            if (selectedItem.getItemName() != null)
                itemName = selectedItem.getItemName();
            if (selectedItem.getItemDescription() != null)
                description = selectedItem.getItemDescription();
            itemDescriptionTable.addCell(getDescriptionCell(itemName + " " + description, PdfPCell.ALIGN_LEFT, fontTable));

            String itemHsnCode = "";
            if (selectedItem.getHsnCode() != null)
                itemHsnCode = selectedItem.getHsnCode();
            itemDescriptionTable.addCell(getDescriptionCell(itemHsnCode, PdfPCell.ALIGN_RIGHT, fontTable));


            itemDescriptionTable.addCell(getDescriptionCell("" + selectedItem.getQty(), PdfPCell.ALIGN_RIGHT, fontTable));

            String itemUnit = "";
            if (selectedItem.getUomName() != null)
                itemUnit = selectedItem.getUomName();
            itemDescriptionTable.addCell(getDescriptionCell(itemUnit, PdfPCell.ALIGN_CENTER, fontTable));

            String itemUnitPrice = "";
            if (selectedItem.getUnitPrice() != null)
                itemUnitPrice = "" + String.format("%.2f", selectedItem.getUnitPrice());
            itemDescriptionTable.addCell(getDescriptionCell(itemUnitPrice, PdfPCell.ALIGN_RIGHT, fontTable));

            String itemTotalAmount = "";
            if (selectedItem.getUnitPrice() != null)
                itemTotalAmount = "" + String.format("%.2f", selectedItem.getAmtexclusivetax());
            itemDescriptionTable.addCell(getDescriptionCell(itemTotalAmount, PdfPCell.ALIGN_RIGHT, fontTable));

            String itemDiscountAmount = "";
            if (selectedItem.getDiscountAmt() != null)
                itemDiscountAmount = "" + String.format("%.2f", selectedItem.getDiscountAmt());
            itemDescriptionTable.addCell(getDescriptionCell(itemDiscountAmount, PdfPCell.ALIGN_RIGHT, fontTable));


            double discountAmt = 0;
            double amtExclusiveTax = 0;
            if (selectedItem.getAmtexclusivetax() != null)
                amtExclusiveTax = selectedItem.getAmtexclusivetax();
            if (selectedItem.getDiscountAmt() != null)
                discountAmt = selectedItem.getDiscountAmt();
            itemTaxableValue = "" + String.format("%.2f", (amtExclusiveTax - discountAmt));
            itemDescriptionTable.addCell(getDescriptionCell(itemTaxableValue, PdfPCell.ALIGN_RIGHT, fontTable));

            String itemCgstsgstsplitvalues = "0.00";
            String itemTaxPercentageSplit = "0.00";
            double cessAmt=0;
            if (selectedItem.getCessTaxAmt()!=null)
                cessAmt=selectedItem.getCessTaxAmt();

            if (retailPrintDTO.getSiData().getTaxType()!=null) {
                if (retailPrintDTO.getSiData().getTaxType().equals("CGST:SGST/UGST")) {
                    if (selectedItem.getTaxamt()!=null)
                    itemCgstsgstsplitvalues = "" + String.format("%.2f", ((selectedItem.getTaxamt()-cessAmt) / 2));

                    if (selectedItem.getTaxpercent()!=null)
                    itemTaxPercentageSplit = "" + String.format("%.2f", (selectedItem.getTaxpercent() / 2));

                    itemDescriptionTable.addCell(getDescriptionCell(itemTaxPercentageSplit, PdfPCell.ALIGN_RIGHT, fontTable));
                    itemDescriptionTable.addCell(getDescriptionCell(itemCgstsgstsplitvalues, PdfPCell.ALIGN_RIGHT, fontTable));
                    itemDescriptionTable.addCell(getDescriptionCell(itemTaxPercentageSplit, PdfPCell.ALIGN_RIGHT, fontTable));
                    itemDescriptionTable.addCell(getDescriptionCell(itemCgstsgstsplitvalues, PdfPCell.ALIGN_RIGHT, fontTable));
                    itemDescriptionTable.addCell(getDescriptionCell("0.00", PdfPCell.ALIGN_RIGHT, fontTable));
                    itemDescriptionTable.addCell(getDescriptionCell("0.00", PdfPCell.ALIGN_RIGHT, fontTable));


                }
                if (retailPrintDTO.getSiData().getTaxType().equals("IGST")) {

                    if (selectedItem.getTaxamt()!=null)
                        itemCgstsgstsplitvalues = "" + String.format("%.2f", (selectedItem.getTaxamt()-cessAmt));

                    if (selectedItem.getTaxpercent()!=null)
                        itemTaxPercentageSplit = "" + String.format("%.2f", (selectedItem.getTaxpercent()));

                    itemDescriptionTable.addCell(getDescriptionCell("0.00", PdfPCell.ALIGN_RIGHT, fontTable));
                    itemDescriptionTable.addCell(getDescriptionCell("0.00", PdfPCell.ALIGN_RIGHT, fontTable));
                    itemDescriptionTable.addCell(getDescriptionCell("0.00", PdfPCell.ALIGN_RIGHT, fontTable));
                    itemDescriptionTable.addCell(getDescriptionCell("0.00", PdfPCell.ALIGN_RIGHT, fontTable));
                    itemDescriptionTable.addCell(getDescriptionCell(itemTaxPercentageSplit, PdfPCell.ALIGN_RIGHT, fontTable));
                    itemDescriptionTable.addCell(getDescriptionCell(itemCgstsgstsplitvalues, PdfPCell.ALIGN_RIGHT, fontTable));
                }

            }else {
                itemDescriptionTable.addCell(getDescriptionCell("0.00", PdfPCell.ALIGN_RIGHT, fontTable));
                itemDescriptionTable.addCell(getDescriptionCell("0.00", PdfPCell.ALIGN_RIGHT, fontTable));
                itemDescriptionTable.addCell(getDescriptionCell("0.00", PdfPCell.ALIGN_RIGHT, fontTable));
                itemDescriptionTable.addCell(getDescriptionCell("0.00", PdfPCell.ALIGN_RIGHT, fontTable));
                itemDescriptionTable.addCell(getDescriptionCell("0.00", PdfPCell.ALIGN_RIGHT, fontTable));
                itemDescriptionTable.addCell(getDescriptionCell("0.00", PdfPCell.ALIGN_RIGHT, fontTable));

            }



            String itemCess = "";
            if (selectedItem.getCess() != null)
                itemCess = "" + String.format("%.2f", selectedItem.getCess());
            itemDescriptionTable.addCell(getDescriptionCell(itemCess, PdfPCell.ALIGN_RIGHT, fontTable));

            String itemCessTaxAmt = "";
            if (selectedItem.getCessTaxAmt() != null)
                itemCessTaxAmt = "" + String.format("%.2f", selectedItem.getCessTaxAmt());
            itemDescriptionTable.addCell(getDescriptionCell(itemCessTaxAmt, PdfPCell.ALIGN_RIGHT, fontTable));
        }

        float height = 300 - itemDescriptionTable.getTotalHeight();
        for (
                int i = 0; i < itemDescriptionTable.getNumberOfColumns(); i++)

        {
            PdfPCell pdfPCell = new PdfPCell();
            pdfPCell.setBorder(PdfPCell.LEFT | PdfPCell.RIGHT | PdfPCell.BOTTOM);
            if (height > 0) {
                pdfPCell.setMinimumHeight(height);
            }
            itemDescriptionTable.addCell(pdfPCell);
        }

        PdfPTable bottomTotalTable = new PdfPTable(3);
        bottomTotalTable.setWidths(new float[]{
                        13.2f, 0.25f, 1.50f
                });
        bottomTotalTable.setSpacingBefore(2);
        bottomTotalTable.setWidthPercentage(100);
        // bottomTotalTable.setLockedWidth(true);


        bottomTotalTable.addCell(getBottomTableCell(Constant.PDF_TOTALAMOUNTEINC, PdfPCell.ALIGN_RIGHT, fontTaxBold));
        bottomTotalTable.addCell(getBottomTableCell(":", PdfPCell.ALIGN_CENTER, fontTax));
        if (retailPrintDTO.getTotalExcludingTax()!=0)
        bottomTotalTable.addCell(getBottomTableCell(""+retailPrintDTO.getTotalExcludingTax(), PdfPCell.ALIGN_RIGHT, fontTax));

        String totalAmtGST = "";
        if (retailPrintDTO.getTaxAmt() != 0) {
            if (retailPrintDTO.getCessTaxAmt() != 0) {
                totalAmtGST = "" + String.format("%.2f", retailPrintDTO.getTaxAmt()-retailPrintDTO.getCessTaxAmt());
            }else {

            }
        }
        bottomTotalTable.addCell(getBottomTableCell(Constant.PDF_GSTAMT, PdfPCell.ALIGN_RIGHT, fontTaxBold));
        bottomTotalTable.addCell(getBottomTableCell(":", PdfPCell.ALIGN_CENTER, fontTax));
        bottomTotalTable.addCell(getBottomTableCell(totalAmtGST, PdfPCell.ALIGN_RIGHT, fontTax));

        String totalAmtCess = "";
        if (retailPrintDTO.getCessTaxAmt() != 0) {
            totalAmtCess = "" + String.format("%.2f", retailPrintDTO.getCessTaxAmt());
        }
        bottomTotalTable.addCell(getBottomTableCell(Constant.PDF_CESSAMT, PdfPCell.ALIGN_RIGHT, fontTaxBold));
        bottomTotalTable.addCell(getBottomTableCell(":", PdfPCell.ALIGN_CENTER, fontTax));
        bottomTotalTable.addCell(getBottomTableCell(totalAmtCess, PdfPCell.ALIGN_RIGHT, fontTax));

        String totalAmtInc = "";
        if (retailPrintDTO.getTotalIncludingTax() != 0) {
            totalAmtInc = "" + String.format("%.2f", retailPrintDTO.getTotalIncludingTax());
        }
        bottomTotalTable.addCell(getBottomTableCell(Constant.PDF_TOTALINVOICEAMT, PdfPCell.ALIGN_RIGHT, fontTaxBold));
        bottomTotalTable.addCell(getBottomTableCell(":", PdfPCell.ALIGN_CENTER, fontTax));
        bottomTotalTable.addCell(getBottomTableCell(totalAmtInc, PdfPCell.ALIGN_RIGHT, fontTax));

        Paragraph para = new Paragraph();
        String totalAmtInc1 = "0.0";
        if (retailPrintDTO.getTotalIncludingTax() != 0) {
            totalAmtInc1 = "" + String.format("%.2f", retailPrintDTO.getTotalIncludingTax());
        }

        String[] splitAmts = totalAmtInc1.split("\\.");
        para.setAlignment(Element.ALIGN_RIGHT);
        para.setLeading(10,0);
        para.add(new Phrase(new Chunk(Constant.PDF_TOTALINVOCIEAMTINWORDS, fontTaxBold)));
        para.add(new Chunk(" : ",fontTaxBold));
        String wordNumber = EnglishNumberToWords.convert(Long.parseLong(splitAmts[0])) + " Point " + EnglishNumberToWords.convert(Long.parseLong(splitAmts[1]))+" Rupees Only.";
        para.add(new Chunk(wordNumber, fontTax));


        document.add(header);
        document.add(compnayInfo);
        document.add(billInfoTable);
        //document.add(billingPar);
        document.add(itemDescriptionTable);
        document.add(bottomTotalTable);
        document.add(para);

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
        cell.setLeading(10,0);
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
