package in.hiaccounts.pdfgenerator.purchase.purchase_orders;

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
import com.lowagie.text.pdf.draw.VerticalPositionMark;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import harmony.java.awt.Color;
import in.hiaccounts.R;
import in.hiaccounts.hinext.purchase.model.purchase_saveresponses.Checkout_ResponseData;
import in.hiaccounts.hinext.purchase.model.purchase_saveresponses.InventoryLocationData;
import in.hiaccounts.hinext.purchase.model.purchase_saveresponses.SelectedItemsList;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.EnglishNumberToWords;

/**
 * Created by Prateek on 6/1/2017.
 */
public class PosA4PurchaseOrderPdfImpl implements PosPurchaseOrderPdf {

    @Override
    public void generatePurchaseOrderInvoice(Context context, Checkout_ResponseData retailPrintDTO, OutputStream outPutStream, InputStream inputStream) throws IOException, DocumentException {

        Document document = new Document((PageSize.A4));
        PdfWriter.getInstance(document, outPutStream);
        document.open();
        InventoryLocationData inventoryLocationData = retailPrintDTO.getInventoryLocationData();

       /* Font font12Bold = new Font(Font.NORMAL, 12, Font.BOLD);
        Font font6Bold = new Font(Font.NORMAL, 6, Font.BOLD,Color.BLACK);
        Font font6 = new Font(Font.NORMAL, 8);
*/

        Font fontHeader = new Font(Font.HELVETICA, 12, Font.BOLD);

        Font fontTableHeader = new Font(Font.HELVETICA, 8, Font.BOLD, Color.BLACK);
        Font fontTable = new Font(Font.HELVETICA, 7);

        Font fontTaxBold = new Font(Font.HELVETICA, 10, Font.BOLD, Color.BLACK);
        Font fontTax = new Font(Font.HELVETICA, 10);

        Chunk glue = new Chunk(new VerticalPositionMark());


        Paragraph header = new Paragraph();
        header.add(new Chunk(Constant.PDF_PURCHASE_TITLEPURCHASEORDER, fontHeader));
        header.setAlignment(Element.ALIGN_CENTER);

       // Paragraph compnayInfo = new Paragraph();
        PdfPTable compnayInfo = new PdfPTable(2);
        PdfPTable billInfoTable = new PdfPTable(2);

        if (retailPrintDTO.getPiData()!=null){

            /*compnayInfo.setAlignment(Element.ALIGN_LEFT);
            compnayInfo.setSpacingBefore(30);
            compnayInfo.setLeading(10,0);*/
            compnayInfo.setWidths(new float[]{2, 4});
            compnayInfo.setWidthPercentage(100);
            compnayInfo.setSpacingBefore(20);

            List<Chunk> billingInoChunks = new ArrayList<>();
            if (retailPrintDTO.getCompanyData() != null) {
                billingInoChunks.add(new Chunk(Constant.PDF_GSTIN, fontTaxBold));
                if (retailPrintDTO.getPiData().getSupplierGst() != null) {
                    billingInoChunks.add(new Chunk(retailPrintDTO.getPiData().getSupplierGst(), fontTax));
                }
                billingInoChunks.add(new Chunk(Chunk.NEWLINE));

                billingInoChunks.add(new Chunk(Constant.PDF_NAME, fontTaxBold));
                if (retailPrintDTO.getPiData().getSupplierName() != null) {
                    billingInoChunks.add(new Chunk(retailPrintDTO.getPiData().getSupplierName(), fontTax));
                }
                billingInoChunks.add(new Chunk(Chunk.NEWLINE));

                billingInoChunks.add(new Chunk(Constant.PDF_ADDRESS, fontTaxBold));
                if (retailPrintDTO.getPiData().getSupplierAddress() != null)
                    billingInoChunks.add(new Chunk(retailPrintDTO.getPiData().getSupplierAddress(), fontTax));
                billingInoChunks.add(new Chunk(Chunk.NEWLINE));

                billingInoChunks.add(new Chunk(Constant.PDF_STATE, fontTaxBold));
                if (retailPrintDTO.getPiData().getSupplierState() != null)
                    billingInoChunks.add(new Chunk(retailPrintDTO.getPiData().getSupplierState(), fontTax));
                billingInoChunks.add(new Chunk(Chunk.NEWLINE));

                billingInoChunks.add(new Chunk(Constant.PDF_INVNO + " : ", fontTaxBold));
                if (retailPrintDTO.getPiData().getPiNo() != null)
                    billingInoChunks.add(new Chunk(retailPrintDTO.getPiData().getPiNo(), fontTax));
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
         /*   compnayInfo.add(new Chunk(Constant.PDF_GSTIN, fontTaxBold));
            if (retailPrintDTO.getPiData().getSupplierGst()!=null){
                compnayInfo.add(new Chunk(retailPrintDTO.getPiData().getSupplierGst(), fontTax));
            }else {
                compnayInfo.add(new Chunk("", fontTax));
            }
            compnayInfo.add(Chunk.NEWLINE);

            compnayInfo.add(new Phrase(new Chunk(Constant.PDF_NAME, fontTaxBold)));
            if (retailPrintDTO.getPiData().getSupplierName()!= null) {
                compnayInfo.add(new Phrase(new Chunk(retailPrintDTO.getPiData().getSupplierName(), fontTax)));
            }else {
                compnayInfo.add(new Chunk("", fontTax));
            }
            compnayInfo.add(Chunk.NEWLINE);

            compnayInfo.add(new Phrase(new Chunk(Constant.PDF_ADDRESS, fontTaxBold)));
            if (retailPrintDTO.getPiData().getSupplierAddress()!= null) {
                compnayInfo.add(new Phrase(new Chunk(retailPrintDTO.getPiData().getSupplierAddress(), fontTax)));
            }else {
                compnayInfo.add(new Chunk("", fontTax));
            }
            compnayInfo.add(Chunk.NEWLINE);

            compnayInfo.add(new Phrase(new Chunk(Constant.PDF_STATE, fontTaxBold)));
            if (retailPrintDTO.getPiData().getSupplierState()!= null)
                if (retailPrintDTO.getPiData().getSupplierState() != null) {
                    compnayInfo.add(new Phrase(new Chunk(retailPrintDTO.getPiData().getSupplierState(), fontTax)));
                }else {
                    compnayInfo.add(new Chunk("", fontTax));
                }
            compnayInfo.add(Chunk.NEWLINE);

            compnayInfo.add(new Phrase(new Chunk(Constant.PDF_PURCHASEORDERNO, fontTaxBold)));
            if (retailPrintDTO.getPiData().getPiNo() != null)
                compnayInfo.add(new Phrase(new Chunk(retailPrintDTO.getPiData().getPiNo(), fontTax)));
            compnayInfo.add(Chunk.NEWLINE);


            compnayInfo.add(new Phrase(new Chunk(Constant.PDF_INVDATE, fontTaxBold)));
            compnayInfo.add(new Chunk(" : ",fontTax));
            if (retailPrintDTO.getDate() != null)
                compnayInfo.add(new Phrase(new Chunk(retailPrintDTO.getDate(), fontTax)));
            compnayInfo.add(Chunk.NEWLINE);
*/



            billInfoTable.setSpacingBefore(20);
            billInfoTable.setWidths(new float[]{2,2});
            billInfoTable.setWidthPercentage(100);

            billInfoTable.addCell(getCell(Constant.PDF_BIILING_TITLE, PdfPCell.ALIGN_LEFT, fontHeader));
            billInfoTable.addCell(getCell(Constant.PDF_PURCHASE_SHIPPING_TITLE, PdfPCell.ALIGN_LEFT, fontHeader));

            String inventGSTIN = "", inventCustomerName = "", inventAddress = "", inventState = "", inventNo = "";
            if (retailPrintDTO.getCompanyData() != null) {
                if (retailPrintDTO.getCompanyData().getRegisterNo()!= null) {
                    inventGSTIN = retailPrintDTO.getCompanyData().getRegisterNo();
                }
                if (retailPrintDTO.getCompanyData().getCompanyName() != null) {
                    inventCustomerName = retailPrintDTO.getCompanyData().getCompanyName();
                }
                if (retailPrintDTO.getCompanyData().getAddress() != null) {
                    inventAddress = retailPrintDTO.getCompanyData().getAddress();

                }
                if (retailPrintDTO.getCompanyData().getState() != null) {
                    if (retailPrintDTO.getCompanyData().getState().getStateName()!=null)
                        inventState = retailPrintDTO.getCompanyData().getState().getStateName();

                }
                if (retailPrintDTO.getPiData().getPiNo()!= null) {

                    inventNo = retailPrintDTO.getPiData().getPiNo();

                }
            }

            String locationGSTIN = "", locationCustomerName = "", locationAddress = "", locationState = "", locationInNo = "", locationInDate = "";


            if (retailPrintDTO.getPiData().getSupplierGst() != null) {
                locationGSTIN = retailPrintDTO.getPiData().getSupplierGst();
            }
            if (retailPrintDTO.getPiData().getSupplierName() != null) {
                locationCustomerName = retailPrintDTO.getPiData().getSupplierName();
            }
            if (retailPrintDTO.getPiData().getSupplierAddress() != null) {
                locationAddress = retailPrintDTO.getPiData().getSupplierAddress();

            }
            if (retailPrintDTO.getPiData().getSupplierState() != null) {

                locationState = retailPrintDTO.getPiData().getSupplierState();

            }
            if (retailPrintDTO.getPiData().getPiNo() != null)

            {
                locationInNo = retailPrintDTO.getPiData().getPiNo();
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
            chunkList.add(new Chunk(Constant.PDF_PURCHASEORDERNO, fontTaxBold));
            chunkList.add(new Chunk(inventNo, fontTax));

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
            shiipedChunkList.add(new Chunk(Constant.PDF_PURCHASEORDERNO, fontTaxBold));
            shiipedChunkList.add(new Chunk(locationInNo, fontTax));

            billInfoTable.addCell(getCell(shiipedChunkList, PdfPCell.ALIGN_LEFT));

        }

        PdfPTable itemDescriptionTable = new PdfPTable(17);
        itemDescriptionTable.setWidths(new float[]{0.75f, 3.50f, 1.50f, 0.75f, 0.75f, 1, 1, 1, 1.20f, 1, 1.20f, 1, 1.20f, 1, 1.20f, 1, 1.20f});
        itemDescriptionTable.setSpacingBefore(20);
        itemDescriptionTable.setWidthPercentage(100);

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

        cell = addAlignmetToCell(Constant.PDF_ITEMDESC_SGST, Element.ALIGN_CENTER, fontTableHeader);
        cell.setColspan(2);
        itemDescriptionTable.addCell(cell);

        cell = addAlignmetToCell(Constant.PDF_ITEMDESC_IGST, Element.ALIGN_CENTER, fontTableHeader);
        cell.setColspan(2);
        itemDescriptionTable.addCell(cell);

        cell = addAlignmetToCell(Constant.PDF_ITEMDESC_CESS, Element.ALIGN_CENTER, fontTableHeader);
        cell.setColspan(2);
        itemDescriptionTable.addCell(cell);

        itemDescriptionTable.addCell(addAlignmetToCell(Constant.PDF_ITEMDESC_RATE, Element.ALIGN_CENTER, fontTableHeader));
        itemDescriptionTable.addCell(addAlignmetToCell(Constant.PDF_ITEMDESC_AMOUNT, Element.ALIGN_CENTER, fontTableHeader));
        itemDescriptionTable.addCell(addAlignmetToCell(Constant.PDF_ITEMDESC_RATE, Element.ALIGN_CENTER, fontTableHeader));
        itemDescriptionTable.addCell(addAlignmetToCell(Constant.PDF_ITEMDESC_AMOUNT, Element.ALIGN_CENTER, fontTableHeader));
        itemDescriptionTable.addCell(addAlignmetToCell(Constant.PDF_ITEMDESC_RATE, Element.ALIGN_CENTER, fontTableHeader));
        itemDescriptionTable.addCell(addAlignmetToCell(Constant.PDF_ITEMDESC_AMOUNT, Element.ALIGN_CENTER, fontTableHeader));
        itemDescriptionTable.addCell(addAlignmetToCell(Constant.PDF_ITEMDESC_RATE, Element.ALIGN_CENTER, fontTableHeader));
        itemDescriptionTable.addCell(addAlignmetToCell(Constant.PDF_ITEMDESC_AMOUNT, Element.ALIGN_CENTER, fontTableHeader));

        String itemTaxableValue = "";
        List<SelectedItemsList> itemsList = retailPrintDTO.getPiData().getSelectedItemsList();
        int j = 1;
        for (SelectedItemsList selectedItem : itemsList) {
            itemDescriptionTable.addCell(getDescriptionCell("" + j, PdfCell.ALIGN_CENTER, fontTable));
            j++;
            String description = "";
            String itemName = "";
            if (selectedItem.getItemName() != null)
                itemName = selectedItem.getItemName();
            itemDescriptionTable.addCell(getDescriptionCell(itemName, PdfPCell.ALIGN_LEFT, fontTable));

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
             itemUnitPrice = "" + String.format("%.2f", selectedItem.getUnitPrice());
            itemDescriptionTable.addCell(getDescriptionCell(itemUnitPrice, PdfPCell.ALIGN_RIGHT, fontTable));

            String itemTotalAmount = "";
            itemTotalAmount = "" + String.format("%.2f", selectedItem.getAmtexclusivetax());
            itemDescriptionTable.addCell(getDescriptionCell(itemTotalAmount, PdfPCell.ALIGN_RIGHT, fontTable));

            String itemDiscountAmount = "";
            itemDiscountAmount = "" + String.format("%.2f", selectedItem.getDiscountAmt());
            itemDescriptionTable.addCell(getDescriptionCell(itemDiscountAmount, PdfPCell.ALIGN_RIGHT, fontTable));


            double discountAmt = 0;
            double amtExclusiveTax = 0;
            amtExclusiveTax = selectedItem.getAmtexclusivetax();
            discountAmt = selectedItem.getDiscountAmt();
            itemTaxableValue = "" + String.format("%.2f", (amtExclusiveTax - discountAmt));
            itemDescriptionTable.addCell(getDescriptionCell(itemTaxableValue, PdfPCell.ALIGN_RIGHT, fontTable));

            String itemCgstsgstsplitvalues = "0.00";
            String itemTaxPercentageSplit = "0.00";
            double cessAmt=selectedItem.getCessTaxAmt();

            if (retailPrintDTO.getPiData().getTaxType()!=null) {
                if (retailPrintDTO.getPiData().getTaxType().equals("CGST:SGST/UGST")) {
                    itemCgstsgstsplitvalues = "" + String.format("%.2f", ((selectedItem.getTaxamt()-cessAmt )/ 2));
                    itemTaxPercentageSplit = "" + String.format("%.2f", (selectedItem.getTaxpercent() / 2));

                    itemDescriptionTable.addCell(getDescriptionCell(itemTaxPercentageSplit, PdfPCell.ALIGN_RIGHT, fontTable));
                    itemDescriptionTable.addCell(getDescriptionCell(itemCgstsgstsplitvalues, PdfPCell.ALIGN_RIGHT, fontTable));
                    itemDescriptionTable.addCell(getDescriptionCell(itemTaxPercentageSplit, PdfPCell.ALIGN_RIGHT, fontTable));
                    itemDescriptionTable.addCell(getDescriptionCell(itemCgstsgstsplitvalues, PdfPCell.ALIGN_RIGHT, fontTable));
                    itemDescriptionTable.addCell(getDescriptionCell("0.00", PdfPCell.ALIGN_RIGHT, fontTable));
                    itemDescriptionTable.addCell(getDescriptionCell("0.00", PdfPCell.ALIGN_RIGHT, fontTable));


                }
                if (retailPrintDTO.getPiData().getTaxType().equals("IGST")) {
                    itemCgstsgstsplitvalues = "" + String.format("%.2f", ((selectedItem.getTaxamt()-cessAmt)));
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
            itemCess = "" + String.format("%.2f", selectedItem.getCess());
            itemDescriptionTable.addCell(getDescriptionCell(itemCess, PdfPCell.ALIGN_RIGHT, fontTable));

            String itemCessTaxAmt = "";
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
        bottomTotalTable.setWidths(new float[]{13.2f, 0.25f, 1.50f});
        bottomTotalTable.setSpacingBefore(2);
        bottomTotalTable.setWidthPercentage(100);
        // bottomTotalTable.setLockedWidth(true);


        bottomTotalTable.addCell(getBottomTableCell(Constant.PDF_TOTALAMOUNTEINC, PdfPCell.ALIGN_RIGHT, fontTaxBold));
        bottomTotalTable.addCell(getBottomTableCell(":", PdfPCell.ALIGN_CENTER, fontTax));
        bottomTotalTable.addCell(getBottomTableCell(""+retailPrintDTO.getTotalExcludingTax(), PdfPCell.ALIGN_RIGHT, fontTax));

        String totalAmtGST = "";
        totalAmtGST = "" + String.format("%.2f", (retailPrintDTO.getTaxAmt()-retailPrintDTO.getCessTaxAmt()));

        bottomTotalTable.addCell(getBottomTableCell(Constant.PDF_GSTAMT, PdfPCell.ALIGN_RIGHT, fontTaxBold));
        bottomTotalTable.addCell(getBottomTableCell(":", PdfPCell.ALIGN_CENTER, fontTax));
        bottomTotalTable.addCell(getBottomTableCell(totalAmtGST, PdfPCell.ALIGN_RIGHT, fontTax));

        String totalAmtCess = "";
        totalAmtCess = "" + String.format("%.2f", retailPrintDTO.getCessTaxAmt());

        bottomTotalTable.addCell(getBottomTableCell(Constant.PDF_CESSAMT, PdfPCell.ALIGN_RIGHT, fontTaxBold));
        bottomTotalTable.addCell(getBottomTableCell(":", PdfPCell.ALIGN_CENTER, fontTax));
        bottomTotalTable.addCell(getBottomTableCell(totalAmtCess, PdfPCell.ALIGN_RIGHT, fontTax));

        String totalAmtInc = "";
        totalAmtInc = "" + String.format("%.2f", retailPrintDTO.getTotalIncludingTax());

        bottomTotalTable.addCell(getBottomTableCell(Constant.PDF_TOTALINVOICEAMT, PdfPCell.ALIGN_RIGHT, fontTaxBold));
        bottomTotalTable.addCell(getBottomTableCell(":", PdfPCell.ALIGN_CENTER, fontTax));
        bottomTotalTable.addCell(getBottomTableCell(totalAmtInc, PdfPCell.ALIGN_RIGHT, fontTax));

        /*String totalAmtPaid = "";
        totalAmtPaid = "" + String.format("%.2f", retailPrintDTO.getTotalPaid());

        bottomTotalTable.addCell(getBottomTableCell(Constant.PDF_ADVANCEPAIDPAYMENT, PdfPCell.ALIGN_RIGHT, fontTaxBold));
        bottomTotalTable.addCell(getBottomTableCell(":", PdfPCell.ALIGN_CENTER, fontTax));
        bottomTotalTable.addCell(getBottomTableCell(totalAmtPaid, PdfPCell.ALIGN_RIGHT, fontTax));

        String totalAmtBalance = "";
        totalAmtBalance = "" + String.format("%.2f", retailPrintDTO.getBalance());

        bottomTotalTable.addCell(getBottomTableCell(Constant.PDF_BALANCEAMT, PdfPCell.ALIGN_RIGHT, fontTaxBold));
        bottomTotalTable.addCell(getBottomTableCell(":", PdfPCell.ALIGN_CENTER, fontTax));
        bottomTotalTable.addCell(getBottomTableCell(totalAmtBalance, PdfPCell.ALIGN_RIGHT, fontTax));
*/

        Paragraph para = new Paragraph();
        String totalAmtInc1 = "0";
        totalAmtInc1 = "" + String.format("%.2f", retailPrintDTO.getTotalIncludingTax());

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
