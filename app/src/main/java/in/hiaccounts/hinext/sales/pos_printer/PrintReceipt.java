package in.hiaccounts.hinext.sales.pos_printer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mocoo.hang.rtprinter.driver.BitmapConvertUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import in.hiaccounts.R;
import in.hiaccounts.hinext.checkout.activity.Activity_Checkout;
import in.hiaccounts.hinext.checkout.model.CardPaymentList;
import in.hiaccounts.hinext.checkout.model.MultiBankPaymentList;
import in.hiaccounts.hinext.checkout.model.MultiCashPaymentList;
import in.hiaccounts.hinext.checkout.model.MultiVoucherPayment;
import in.hiaccounts.hinext.checkout.model.sales.Checkout_Sales_ResponseData;
import in.hiaccounts.hinext.purchase.model.purchase_saveresponses.Checkout_ResponseData;
import in.hiaccounts.hinext.purchase.model.purchase_saveresponses.CompanyData;
import in.hiaccounts.hinext.restaurant.activity.Activity_PrintList;
import in.hiaccounts.hinext.restaurant.activity.Activity_PrintList_DailyReport;
import in.hiaccounts.hinext.restaurant.activity.Activity_RestraPayment1;
import in.hiaccounts.hinext.restaurant.activity.RestuarantActivity;
import in.hiaccounts.hinext.restaurant.model.category_item.DailyReportData;
import in.hiaccounts.hinext.restaurant.model.category_item.DailyReportItemData;
import in.hiaccounts.hinext.restaurant.model.category_item.Payment;
import in.hiaccounts.hinext.restaurant.model.checkout.DialyReportData;
import in.hiaccounts.hinext.restaurant.model.checkout.Lassi;
import in.hiaccounts.hinext.restaurant.model.checkout.RestraCheckoutData;
import in.hiaccounts.hinext.restaurant.model.checkout.RestraCheckoutItem;
import in.hiaccounts.hinext.restaurant.model.checkout.RestraSaveCheckoutData;
import in.hiaccounts.hinext.sales.model.save_response.SelectedItemsList;
import in.hiaccounts.hinext.sales.model.save_response.TaxSummaryList;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

/**
 * This class is responsible to generate a static sales receipt and to print that receipt
 */
public class PrintReceipt {
    private static final char NEWLINE = '\n';
    private static final String SPACE_SEPARATOR = "";
    //if text has \n, \r or \t symbols it's better to split by \s+
    private static final String SPLIT_REGEXP= "";
  /*  public static byte[] createTextReceiptData(Emulation emulation, ILocalizeReceipts localizeReceipts, boolean utf8) {
        ICommandBuilder builder = StarIoExt.createCommandBuilder(emulation);

        builder.beginDocument();

        localizeReceipts.appendTextReceiptData(builder, utf8);

        builder.appendCutPaper(CutPaperAction.PartialCutWithFeed);

        builder.endDocument();

        return builder.getCommands();
    }*/




    public static boolean printInvoice3Inch(Context context, Checkout_Sales_ResponseData saleInoviceData) {
        if (Activity_Checkout.BLUETOOTH_PRINTER.IsNoConnection()) {
            return false;
        }

        if (saleInoviceData == null) {
            return false;
        } else {
            String companyName = "";
            String companyTelNumber = "";
            String companyFaxNumber = "";
            String companyGSTNumber = "";
            String date = "", time = "", invoiceNo = "", invoiceCustomerName = "";
            if (saleInoviceData.getDate() != null) {
                date = saleInoviceData.getDate();
            }
            if (saleInoviceData.getTime() != null) {
                time = saleInoviceData.getTime();
            }
            if (saleInoviceData.getSiData() != null) {
                if (saleInoviceData.getSiData().getSrlnNo() != null)
                    invoiceNo = saleInoviceData.getSiData().getSrlnNo();
                if (saleInoviceData.getSiData().getCutomerName() != null)
                    invoiceCustomerName = saleInoviceData.getSiData().getCutomerName();
            }
            in.hiaccounts.hinext.checkout.model.sales.CompanyData companyData = saleInoviceData.getCompanyData();
            if (companyData != null) {
                if (saleInoviceData.getCompanyData().getCompanyName() != null) {
                    companyName = saleInoviceData.getCompanyData().getCompanyName().toUpperCase();
                }
                if (companyData.getPhone() != null)
                    companyTelNumber = companyData.getPhone();
                if (companyData.getFax() != null)
                    companyFaxNumber = companyData.getFax();
                if (companyData.getRegisterNo() != null)
                    companyGSTNumber = companyData.getRegisterNo();
            }


            //LF = Line feed
            Activity_Checkout.BLUETOOTH_PRINTER.Begin();
            Activity_Checkout.BLUETOOTH_PRINTER.LF();
            Activity_Checkout.BLUETOOTH_PRINTER.LF();
            Activity_Checkout.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
            Activity_Checkout.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
            Activity_Checkout.BLUETOOTH_PRINTER.SetFontEnlarge((byte)0x5);//normal

            //BT_Write() method will initiate the printer to start printing.
            Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(companyName);

            Activity_Checkout.BLUETOOTH_PRINTER.LF();
            Activity_Checkout.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_Checkout.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);
            Activity_Checkout.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);
            Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(
                    "Tel: " + companyTelNumber +
                            "\nFax: " + companyFaxNumber +
                            "\nGst Reg No.: " + companyGSTNumber +
                            "\nDate:" + date + " " + time +
                            "\nSerial Invoice No.:" + invoiceNo +
                            "\nCustomer Name:" + invoiceCustomerName);

            Activity_Checkout.BLUETOOTH_PRINTER.LF();

            Activity_Checkout.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
            Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line));
            Activity_Checkout.BLUETOOTH_PRINTER.LF();

			/*Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(" Description " +
                    "  Unit Price " +
					"  Unit " +
					"  Total " +
					"         ");*/

            if (saleInoviceData.getSiData() != null) {
                String itemDescription = "", itemName = "";
                String itemUnitPrice = "";
                String itemQty = "";
                String itemAmtInclTax = "";
                String itemTax = "";
                if (saleInoviceData.getSiData().getSelectedItemsList() != null) {
                    Activity_Checkout.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                    Activity_Checkout.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x5);
                    Activity_Checkout.BLUETOOTH_PRINTER.BT_Write("Item Details");
                    Activity_Checkout.BLUETOOTH_PRINTER.LF();

                    for (int i = 0; i < saleInoviceData.getSiData().getSelectedItemsList().size(); i++) {
                        SelectedItemsList item = saleInoviceData.getSiData().getSelectedItemsList().get(i);
                        if (item != null) {
                            if (item.getItemName() != null)
                                itemName = item.getItemName();
                            if (item.getItemDescription() != null)
                                itemDescription = item.getItemDescription();
                            if (item.getUnitPrice() != null)
                                itemUnitPrice = String.format("%.2f", item.getUnitPrice());
                            if (item.getAmtinclusivetax() != null)
                                itemAmtInclTax = String.format("%.2f", item.getAmtinclusivetax());
                            if (item.getTaxName() != null)
                                itemTax = item.getTaxName();
                            itemQty = "" + item.getQty();


						/*	Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(itemDescription + "  " +
									itemUnitPrice + " "
									+ itemQty + " "
									+ itemAmtInclTax + "  "
									+ itemTax + "  ");
							Activity_Checkout.BLUETOOTH_PRINTER.LF();*/


                            Activity_Checkout.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal font
                            Activity_Checkout.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                            Activity_Checkout.BLUETOOTH_PRINTER.BT_Write((i + 1) + ".) " + itemName + " " + itemDescription);
                            Activity_Checkout.BLUETOOTH_PRINTER.LF();
                            Activity_Checkout.BLUETOOTH_PRINTER.BT_Write("    " + itemQty + " x " + itemUnitPrice + " + " + item.getTaxpercent() + "%" + " = " + itemAmtInclTax + "\n");

                        }
                    }
                }
            }
            Activity_Checkout.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line));
            Activity_Checkout.BLUETOOTH_PRINTER.LF();

            Activity_Checkout.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_Checkout.BLUETOOTH_PRINTER.BT_Write("Item Count");
            Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(" ");
            if (saleInoviceData.getSiData().getItemCount() != null)
                Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(" " + saleInoviceData.getSiData().getItemCount());
            Activity_Checkout.BLUETOOTH_PRINTER.LF();

            Activity_Checkout.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_Checkout.BLUETOOTH_PRINTER.BT_Write("Total Sales exclusive GST");
            Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(" ");
            if (saleInoviceData.getTotalIncludingTax() != 0)
                Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(String.format("%.2f",saleInoviceData.getTotalExcludingTax()));
            Activity_Checkout.BLUETOOTH_PRINTER.LF();

            Activity_Checkout.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_Checkout.BLUETOOTH_PRINTER.BT_Write("Total GST");
            Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(" ");
            if (saleInoviceData.getTotalIncludingTax() != 0)
                Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(String.format("%.2f",saleInoviceData.getTaxAmt()));
            Activity_Checkout.BLUETOOTH_PRINTER.LF();

            Activity_Checkout.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_Checkout.BLUETOOTH_PRINTER.BT_Write("Total Sales inclusive GST");
            Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(" ");
            if (saleInoviceData.getTotalIncludingTax() != 0)
                Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(String.format("%.2f",saleInoviceData.getTotalIncludingTax()));
            Activity_Checkout.BLUETOOTH_PRINTER.LF();


            if (saleInoviceData.getSiData() != null) {
                Activity_Checkout.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                Activity_Checkout.BLUETOOTH_PRINTER.BT_Write("Discount Amount");
                Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(" ");
                if (saleInoviceData.getSiData().getDiscountAmount() != null)
                    Activity_Checkout.BLUETOOTH_PRINTER.BT_Write("" + saleInoviceData.getSiData().getDiscountAmount());
                Activity_Checkout.BLUETOOTH_PRINTER.LF();
            }

            Activity_Checkout.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_Checkout.BLUETOOTH_PRINTER.BT_Write("Round Off");
            Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(" ");
            if (saleInoviceData.getTotalIncludingTax() != 0)
                Activity_Checkout.BLUETOOTH_PRINTER.BT_Write("0.00");
            Activity_Checkout.BLUETOOTH_PRINTER.LF();
            Activity_Checkout.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line));
            Activity_Checkout.BLUETOOTH_PRINTER.LF();

            String taxName = "", taxPercantage = "", taxAmt = "", taxableAmt = "";

            if (saleInoviceData.getSiData().getTaxSummaryList() != null) {
                Activity_Checkout.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                Activity_Checkout.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x5);
                Activity_Checkout.BLUETOOTH_PRINTER.BT_Write("GST Summary");
                Activity_Checkout.BLUETOOTH_PRINTER.LF();
                for (int i = 0; i < saleInoviceData.getSiData().getTaxSummaryList().size(); i++) {
                    TaxSummaryList taxSummary = saleInoviceData.getSiData().getTaxSummaryList().get(i);
                    if (taxSummary != null) {
                        if (taxSummary.getTaxName() != null)
                            taxName = taxSummary.getTaxName();
                        if (taxSummary.getTaxPercent() != null)
                            taxPercantage = String.format("%.2f", taxSummary.getTaxPercent());
                        if (taxSummary.getTaxAmount() != null)
                            taxAmt = String.format("%.2f", taxSummary.getTaxAmount());
                        if (taxSummary.getAmt() != null)
                            taxableAmt = String.format("%.2f", taxSummary.getAmt());


                        Activity_Checkout.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal font
                        Activity_Checkout.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                        Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(taxName + "(" + taxPercantage + "%)=" + "  " + taxableAmt + "  " + taxAmt + "\n");
                    }
                }
                Activity_Checkout.BLUETOOTH_PRINTER.LF();
                Activity_Checkout.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line));
                Activity_Checkout.BLUETOOTH_PRINTER.LF();
                Activity_Checkout.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT

                String taxType = "";
                if (saleInoviceData.getSiData().getTaxType() != null) {
                    taxType = saleInoviceData.getSiData().getTaxType();
                }
                String totalTaxAmt = "";
                if (taxType.equals(Constant.PDF_ITEMDESC_IGST)) {
                    if (saleInoviceData.getTaxAmt() != 0) {
                        totalTaxAmt = String.format("%.2f", saleInoviceData.getTaxAmt());
                        Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(Constant.POS_TAXTYPE + "" + taxType + ":" + totalTaxAmt);
                    }
                } else {
                    if (saleInoviceData.getTaxAmt() != 0) {
                        totalTaxAmt = String.format("%.2f", saleInoviceData.getTaxAmt() / 2);
                        Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(Constant.POS_TAXTYPE + "" + taxType + ":" + totalTaxAmt + "+" + totalTaxAmt);
                    }
                }
                Activity_Checkout.BLUETOOTH_PRINTER.LF();
                if (saleInoviceData.getSiData().getCessTotalTaxAmt() != null)
                    Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(Constant.POS_CESS + "" +String.format("%.2f",saleInoviceData.getSiData().getCessTotalTaxAmt()));
                Activity_Checkout.BLUETOOTH_PRINTER.LF();
                Activity_Checkout.BLUETOOTH_PRINTER.LF();
                Activity_Checkout.BLUETOOTH_PRINTER.LF();

                if (saleInoviceData.getDate() != null)
                    Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(Constant.POS_PRINTDATE + " " + saleInoviceData.getDate());
                Activity_Checkout.BLUETOOTH_PRINTER.LF();

                SharedPreference sharedPreference = SharedPreference.getInstance(context);
                String userName = sharedPreference.getData(Constant.USERNAME);
                if (userName != null) {
                    Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(Constant.POS_SALESPERSON + " " + userName);
                }
                Activity_Checkout.BLUETOOTH_PRINTER.LF();
                Activity_Checkout.BLUETOOTH_PRINTER.LF();
                Activity_Checkout.BLUETOOTH_PRINTER.LF();
                Activity_Checkout.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
                Activity_Checkout.BLUETOOTH_PRINTER.BT_Write("Thank You.");
                Activity_Checkout.BLUETOOTH_PRINTER.LF();
                Activity_Checkout.BLUETOOTH_PRINTER.LF();
                Activity_Checkout.BLUETOOTH_PRINTER.LF();

                Activity_Checkout.BLUETOOTH_PRINTER.FeedAndCutPaper((byte)1,(byte)10);


            }
            return true;

        }
    }

    public static boolean printInvoice2Inch(Context context, Checkout_Sales_ResponseData saleInoviceData) {
        if (Activity_Checkout.BLUETOOTH_PRINTER.IsNoConnection()) {
            return false;
        }

        if (saleInoviceData == null) {
            return false;
        } else {
            String companyName = "";
            String companyTelNumber = "";
            String companyFaxNumber = "";
            String companyGSTNumber = "";
            String date = "", time = "", invoiceNo = "", invoiceCustomerName = "";
            if (saleInoviceData.getDate() != null) {
                date = saleInoviceData.getDate();
            }
            if (saleInoviceData.getTime() != null) {
                time = saleInoviceData.getTime();
            }
            if (saleInoviceData.getSiData() != null) {
                if (saleInoviceData.getSiData().getSrlnNo() != null)
                    invoiceNo = saleInoviceData.getSiData().getSrlnNo();
                if (saleInoviceData.getSiData().getCutomerName() != null)
                    invoiceCustomerName = saleInoviceData.getSiData().getCutomerName();
            }
            in.hiaccounts.hinext.checkout.model.sales.CompanyData companyData = saleInoviceData.getCompanyData();
            //CompanyData companyData = saleInoviceData.getCompanyData();
            if (companyData != null) {
                if (saleInoviceData.getCompanyData().getCompanyName() != null) {
                    companyName = saleInoviceData.getCompanyData().getCompanyName().toUpperCase();
                }
                if (companyData.getPhone() != null)
                    companyTelNumber = companyData.getPhone();
                if (companyData.getFax() != null)
                    companyFaxNumber = companyData.getFax();
                if (companyData.getRegisterNo() != null)
                    companyGSTNumber = companyData.getRegisterNo();
            }


            //LF = Line feed
            Activity_Checkout.BLUETOOTH_PRINTER.Begin();
            Activity_Checkout.BLUETOOTH_PRINTER.LF();
            Activity_Checkout.BLUETOOTH_PRINTER.LF();
            Activity_Checkout.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
            Activity_Checkout.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
            Activity_Checkout.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x01);//normal

            //BT_Write() method will initiate the printer to start printing.
            Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(companyName);

            Activity_Checkout.BLUETOOTH_PRINTER.LF();
            Activity_Checkout.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_Checkout.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);
            Activity_Checkout.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);
            Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(
                    "Tel: " + companyTelNumber +
                            "\nFax: " + companyFaxNumber +
                            "\nGst Reg No.: " + companyGSTNumber +
                            "\nDate:" + date + " " + time +
                            "\nSerial Invoice No.:" + invoiceNo +
                            "\nCustomer Name:" + invoiceCustomerName);

            Activity_Checkout.BLUETOOTH_PRINTER.LF();

            Activity_Checkout.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
            Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line2inch));
            Activity_Checkout.BLUETOOTH_PRINTER.LF();

			/*Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(" Description " +
                    "  Unit Price " +
					"  Unit " +
					"  Total " +
					"         ");*/

            if (saleInoviceData.getSiData() != null) {
                String itemDescription = "", itemName = "";
                String itemUnitPrice = "";
                String itemQty = "";
                String itemAmtInclTax = "";
                String itemTax = "";
                if (saleInoviceData.getSiData().getSelectedItemsList() != null) {
                    Activity_Checkout.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                    Activity_Checkout.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);
                    Activity_Checkout.BLUETOOTH_PRINTER.BT_Write("Item Details");
                    Activity_Checkout.BLUETOOTH_PRINTER.LF();

                    for (int i = 0; i < saleInoviceData.getSiData().getSelectedItemsList().size(); i++) {
                        SelectedItemsList item = saleInoviceData.getSiData().getSelectedItemsList().get(i);
                        if (item != null) {
                            if (item.getItemName() != null)
                                itemName = item.getItemName();
                            if (item.getItemDescription() != null)
                                itemDescription = item.getItemDescription();
                            if (item.getUnitPrice() != null)
                                itemUnitPrice = String.format("%.2f", item.getUnitPrice());
                            if (item.getAmtinclusivetax() != null)
                                itemAmtInclTax = String.format("%.2f", item.getAmtinclusivetax());
                            if (item.getTaxName() != null)
                                itemTax = item.getTaxName();
                            itemQty = "" + item.getQty();


						/*	Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(itemDescription + "  " +
									itemUnitPrice + " "
									+ itemQty + " "
									+ itemAmtInclTax + "  "
									+ itemTax + "  ");
							Activity_Checkout.BLUETOOTH_PRINTER.LF();*/


                            Activity_Checkout.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal font
                            Activity_Checkout.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                            Activity_Checkout.BLUETOOTH_PRINTER.BT_Write((i + 1) + ".) " + itemName + " " + itemDescription);
                            Activity_Checkout.BLUETOOTH_PRINTER.LF();
                            Activity_Checkout.BLUETOOTH_PRINTER.BT_Write("    " + itemQty + " x " + itemUnitPrice + " + " + item.getTaxpercent() + "%" + " = " + itemAmtInclTax + "\n");

                        }
                    }
                }
            }
            Activity_Checkout.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line2inch));
            Activity_Checkout.BLUETOOTH_PRINTER.LF();

            Activity_Checkout.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_Checkout.BLUETOOTH_PRINTER.BT_Write("Item Count");
            Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(" ");
            if (saleInoviceData.getSiData().getItemCount() != null)
                Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(" " + saleInoviceData.getSiData().getItemCount());
            Activity_Checkout.BLUETOOTH_PRINTER.LF();

            Activity_Checkout.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_Checkout.BLUETOOTH_PRINTER.BT_Write("Total Sales exclusive GST");
            Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(" ");
            if (saleInoviceData.getTotalIncludingTax() != 0)
                Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(String.format("%.2f",saleInoviceData.getTotalExcludingTax()));
            Activity_Checkout.BLUETOOTH_PRINTER.LF();

            Activity_Checkout.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_Checkout.BLUETOOTH_PRINTER.BT_Write("Total GST");
            Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(" ");
            if (saleInoviceData.getTotalIncludingTax() != 0)
                Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(String.format("%.2f",saleInoviceData.getTaxAmt()));
            Activity_Checkout.BLUETOOTH_PRINTER.LF();

            Activity_Checkout.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_Checkout.BLUETOOTH_PRINTER.BT_Write("Total Sales inclusive GST");
            Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(" ");
            if (saleInoviceData.getTotalIncludingTax() != 0)
                Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(String.format("%.2f",saleInoviceData.getTotalIncludingTax()));
            Activity_Checkout.BLUETOOTH_PRINTER.LF();


            if (saleInoviceData.getSiData() != null) {
                Activity_Checkout.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                Activity_Checkout.BLUETOOTH_PRINTER.BT_Write("Discount Amount");
                Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(" ");
                if (saleInoviceData.getSiData().getDiscountAmount() != null)
                    Activity_Checkout.BLUETOOTH_PRINTER.BT_Write("" + saleInoviceData.getSiData().getDiscountAmount());
                Activity_Checkout.BLUETOOTH_PRINTER.LF();
            }

            Activity_Checkout.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_Checkout.BLUETOOTH_PRINTER.BT_Write("Round Off");
            Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(" ");
            if (saleInoviceData.getTotalIncludingTax() != 0)
                Activity_Checkout.BLUETOOTH_PRINTER.BT_Write("0.00");
            Activity_Checkout.BLUETOOTH_PRINTER.LF();
            Activity_Checkout.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line2inch));
            Activity_Checkout.BLUETOOTH_PRINTER.LF();

            String taxName = "", taxPercantage = "", taxAmt = "", taxableAmt = "";

            if (saleInoviceData.getSiData().getTaxSummaryList() != null) {
                Activity_Checkout.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                Activity_Checkout.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);
                Activity_Checkout.BLUETOOTH_PRINTER.BT_Write("GST Summary");
                Activity_Checkout.BLUETOOTH_PRINTER.LF();
                for (int i = 0; i < saleInoviceData.getSiData().getTaxSummaryList().size(); i++) {
                    TaxSummaryList taxSummary = saleInoviceData.getSiData().getTaxSummaryList().get(i);
                    if (taxSummary != null) {
                        if (taxSummary.getTaxName() != null)
                            taxName = taxSummary.getTaxName();
                        if (taxSummary.getTaxPercent() != null)
                            taxPercantage = String.format("%.2f", taxSummary.getTaxPercent());
                        if (taxSummary.getTaxAmount() != null)
                            taxAmt = String.format("%.2f", taxSummary.getTaxAmount());
                        if (taxSummary.getAmt() != null)
                            taxableAmt = String.format("%.2f", taxSummary.getAmt());


                        Activity_Checkout.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal font
                        Activity_Checkout.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                        Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(taxName + "(" + taxPercantage + "%)=" + "  " + taxableAmt + "  " + taxAmt + "\n");
                    }
                }
                Activity_Checkout.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line2inch));
                Activity_Checkout.BLUETOOTH_PRINTER.LF();
                Activity_Checkout.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT

                String taxType = "";
                if (saleInoviceData.getSiData().getTaxType() != null) {
                    taxType = saleInoviceData.getSiData().getTaxType();
                }
                String totalTaxAmt = "";
                if (taxType.equals(Constant.PDF_ITEMDESC_IGST)) {
                    if (saleInoviceData.getTaxAmt() != 0) {
                        totalTaxAmt = String.format("%.2f", saleInoviceData.getTaxAmt());
                        Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(Constant.POS_TAXTYPE + "" + taxType + ":" + totalTaxAmt);
                    }
                } else {
                    if (saleInoviceData.getTaxAmt() != 0) {
                        totalTaxAmt = String.format("%.2f", saleInoviceData.getTaxAmt() / 2);
                        Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(Constant.POS_TAXTYPE + "" + taxType + ":" + totalTaxAmt + "+" + totalTaxAmt);
                    }
                }
                Activity_Checkout.BLUETOOTH_PRINTER.LF();
                if (saleInoviceData.getSiData().getCessTotalTaxAmt() != null)
                    Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(Constant.POS_CESS + "" +String.format("%.2f",saleInoviceData.getSiData().getCessTotalTaxAmt()));
                Activity_Checkout.BLUETOOTH_PRINTER.LF();
                Activity_Checkout.BLUETOOTH_PRINTER.LF();
                Activity_Checkout.BLUETOOTH_PRINTER.LF();

                if (saleInoviceData.getDate() != null)
                    Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(Constant.POS_PRINTDATE + " " + saleInoviceData.getDate());
                Activity_Checkout.BLUETOOTH_PRINTER.LF();

                SharedPreference sharedPreference = SharedPreference.getInstance(context);
                String userName = sharedPreference.getData(Constant.USERNAME);
                if (userName != null) {
                    Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(Constant.POS_SALESPERSON + " " + userName);
                }
                Activity_Checkout.BLUETOOTH_PRINTER.LF();
                Activity_Checkout.BLUETOOTH_PRINTER.LF();
                Activity_Checkout.BLUETOOTH_PRINTER.LF();
                Activity_Checkout.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
                Activity_Checkout.BLUETOOTH_PRINTER.BT_Write("Thank You.");
                Activity_Checkout.BLUETOOTH_PRINTER.LF();
                Activity_Checkout.BLUETOOTH_PRINTER.LF();
                Activity_Checkout.BLUETOOTH_PRINTER.LF();

                Activity_Checkout.BLUETOOTH_PRINTER.FeedAndCutPaper((byte)1,(byte)10);


            }
            return true;

        }
    }

   /* public static boolean printInvoice2Inch(Context context, RestraSaveCheckoutData saleInoviceData,Bitmap bitmap) {
        if (Activity_RestraPayment1.BLUETOOTH_PRINTER.IsNoConnection()) {
            return false;
        }

        if (saleInoviceData == null) {
            return false;
        } else {
            String companyName = "";
            String companyTelNumber = "";
            String companyFaxNumber = "";
            String companyGSTNumber = "";
            String companyAddress="";
            String date = "", time = "", invoiceNo = "", invoiceCustomerName = "";
            if (saleInoviceData.getDate() != null) {
                date = saleInoviceData.getDate();
            }
           *//* if (saleInoviceData.getTime() != null) {
                time = saleInoviceData.getTime();
            }*//*
            if (saleInoviceData.getSiData() != null) {
                if (saleInoviceData.getSiData().getSrlnNo() != null)
                    invoiceNo = saleInoviceData.getSiData().getSrlnNo();
                if (saleInoviceData.getSiData().getCutomerName() != null){}
                //invoiceCustomerName = saleInoviceData.getSiData().getUserName();
            }
            in.hiaccounts.hinext.restaurant.model.checkout.CompanyData companyData = saleInoviceData.getCompanyData();
            if (companyData != null) {
                if (saleInoviceData.getCompanyData().getCompanyName() != null) {
                    companyName = saleInoviceData.getCompanyData().getCompanyName().toUpperCase();
                }
                if (companyData.getAddress() != null)
                    companyAddress = companyData.getAddress();
                if (companyData.getPhone() != null)
                    companyTelNumber = companyData.getPhone();
                if (companyData.getFax() != null)
                    companyFaxNumber = companyData.getFax();
                if (companyData.getRegisterNo() != null)
                    companyGSTNumber = companyData.getRegisterNo();
            }


            //LF = Line feed
            Activity_RestraPayment1.BLUETOOTH_PRINTER.Begin();
            Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            //   Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
            //Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("");
            // Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
            if (bitmap!=null){
                //Activity_RestraPayment1.BLUETOOTH_PRINTER.printByteData(logoByteArray);

               // Activity_RestraPayment1.BLUETOOTH_PRINTER.printImage(Bitmap.createScaledBitmap(bitmap, 200, 200, false));
                Bitmap newBm = BitmapConvertUtil.decodeSampledBitmapFromBitmap(bitmap, 100);
                byte xL = (byte)(((newBm.getWidth() - 1) / 8 + 1) % 256);
                byte xH = (byte)(((newBm.getWidth() - 1) / 8 + 1) / 256);
                byte yL = (byte)(newBm.getHeight() % 256);
                byte yH = (byte)(newBm.getHeight() / 256);
                byte[] pixels = BitmapConvertUtil.convert(newBm);
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(new byte[]{29, 118, 48, 1, xL, xH, yL, yH});
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(pixels);
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(new byte[]{10});
            }

            //BT_Write() method will initiate the printer to start printing.

            Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x01);//normal
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(companyName);
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
            Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();

            //BT_Write() method will initiate the printer to start printing.
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(companyAddress);

            Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(
                    "Gst Reg No.: " + companyGSTNumber +
                            "\nTel: " + companyTelNumber +
                            "\nFax: " + companyFaxNumber +
                            "\nDate:" + date + " " + time +
                            "\nTax Invoice No.:" + invoiceNo );
            //+"\nCustomer Name:" + invoiceCustomerName

            Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();

            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line2inch));
            Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();

			*//*Activity_Checkout.BLUETOOTH_PRINTER.BT_Write(" Description " +
                    "  Unit Price " +
					"  Unit " +
					"  Total " +
					"         ");*//*

            double amtExTax = 0,amtIncTax= 0;

            if (saleInoviceData.getSiData() != null) {
                String  itemName = "";
                String itemUnitPrice = "";
                String itemQty = "";
                String itemAmtInclTax = "";
                String itemTax = "";
                if (saleInoviceData.getSiData().getSelectedItemsList() != null) {
                    Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                    Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);
                    Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Item Details");
                    Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();

                    for (int i = 0; i < saleInoviceData.getSiData().getSelectedItemsList().size(); i++) {
                        RestraCheckoutItem item = saleInoviceData.getSiData().getSelectedItemsList().get(i);
                        if (item != null) {
                            if (item.getItemName() != null)
                                itemName = item.getItemName();
                            itemUnitPrice = String.format("%.2f", item.getUnitPrice());
                            itemAmtInclTax = String.format("%.2f", (item.getQty()*item.getUnitPrice()));

                            itemQty = "" + item.getQty();
                            amtExTax=amtExTax+(item.getQty()*item.getUnitPrice());




                            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal font
                            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write((i + 1) + ".) " + itemName);
                            Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("    " + itemQty + " x " + itemUnitPrice + " = " + itemAmtInclTax+ "\n");

                            //  Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("    " + itemQty + " x " + itemUnitPrice + "               = " + itemAmtExTax + "\n");

                        }


                    }
                }
            }
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line2inch));
            Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();

            *//*Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Item Count");
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(" ");
            if (saleInoviceData.getSiData().getItemCount() != null)
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(" " + saleInoviceData.getSiData().getItemCount());
            Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();*//*

            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 2);//LEFT
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(
                    "Item Total            " + String.format("%.2f",amtExTax) +
                            "\nService Charge        "+ String.format("%.2f",0d) +
                            "\nGST 5%                "+String.format("%.2f",saleInoviceData.getSiData().getTotalTaxAmt())+
                            "\nGrand Total           "+String.format("%.2f",saleInoviceData.getSiData().getTotalCheckOutamt()));
//                            "\nGrand Total            " + " : "+ saleInoviceData.getSiData().getTotalCheckOutamt() );


            *//*Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Item Total");
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("             ");

            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(String.format("%.2f",amtExTax));
            Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();

            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Service Charge");
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("              ");
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("0.00");
            Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();

            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("AMT INCL Service Charge");
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(" ");
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(String.format("%.2f",amtExTax));
            Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();


            if (saleInoviceData.getSiData() != null) {
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("GST 5%");
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("        ");
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("" + saleInoviceData.getSiData().getTotalTaxAmt());
                Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            }
*//**//*
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Grand Total");
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("            ");
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(""+saleInoviceData.getSiData().getTotalCheckOutamt());
            Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line2inch));*//*
            Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();

            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("GST Summary");
            Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            String taxType = "";
            if (saleInoviceData.getSiData().getTaxType() != null) {
                taxType = saleInoviceData.getSiData().getTaxType();
            }
            String totalTaxAmt = "";
            if (taxType.equals(Constant.PDF_ITEMDESC_IGST)) {
                totalTaxAmt = String.format("%.2f", saleInoviceData.getSiData().getTotalTaxAmt());
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(Constant.POS_TAXTYPE + "" + taxType + ":" + totalTaxAmt);
            } else {
                totalTaxAmt = String.format("%.2f", saleInoviceData.getSiData().getTotalTaxAmt() / 2);
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("CGST :     " + totalTaxAmt);
                Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("SGST :     " + totalTaxAmt );

            }
           *//* String taxName = "", taxPercantage = "", taxAmt = "", taxableAmt = "";

            if (saleInoviceData.getSiData().getSelectedItemsList() != null) {
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("GST Summary");
                Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                for (int i = 0; i < saleInoviceData.getSiData().getSelectedItemsList().size(); i++) {
                    taxableAmt = String.format("%.2f", saleInoviceData.getSiData().getSelectedItemsList().get(i).getAmtinclusivetax());
                    taxAmt = String.format("%.2f", ((saleInoviceData.getSiData().getSelectedItemsList().get(i).getAmtexclusivetax()*5)/100));


                        Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal font
                        Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                        Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("GST-5 (5.00%)=" + "  " + taxableAmt + "  " + taxAmt + "\n");
                    }
                }*//*
            Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line2inch));
            Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT


              *//*  Activity_Checkout.BLUETOOTH_PRINTER.LF();
                if (saleInoviceData.getSiData().getCessTotalTaxAmt() != null)
                    Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(Constant.POS_CESS + "" +String.format("%.2f",saleInoviceData.getSiData().getCessTotalTaxAmt()));*//*
            Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();

              *//*  if (saleInoviceData.getDate() != null)
                    Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(Constant.POS_PRINTDATE + " " + saleInoviceData.getDate());
                Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();*//*

            SharedPreference sharedPreference = SharedPreference.getInstance((Activity) context);
            String userName = sharedPreference.getData(Constant.USERNAME);
            if (userName != null) {
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Prepared By : " + userName);
            }
            Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//Center
            if (companyData.getWeb()!=null)
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(""+companyData.getWeb());
            Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//Center
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Thank You.");
            Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();

            Activity_RestraPayment1.BLUETOOTH_PRINTER.FeedAndCutPaper((byte)1,(byte)10);


        }
        return true;

    }*/
   public static boolean printInvoice3Inch(Context context, RestraSaveCheckoutData saleInoviceData, Bitmap bitmap, String paidAmount, boolean isNotification) {
       if (Activity_RestraPayment1.BLUETOOTH_PRINTER.IsNoConnection()) {
           return false;
       }

       if (saleInoviceData == null) {
           return false;
       } else {
           String serialNo= "";
           String tokenNo = "";
           String companyName = "";
           String companyTelNumber = "";
           String companyFaxNumber = "";
           String companyGSTNumber = "";
           String companyAddress="";
           String date = "", time = "", invoiceNo = "", invoiceCustomerName = "";

           if (saleInoviceData.getDate() != null) {
               date = saleInoviceData.getDate();
           }
           /* if (saleInoviceData.getTime() != null) {
                time = saleInoviceData.getTime();
            }*/
           if (saleInoviceData.getSiData() != null) {
               if (saleInoviceData.getSiData().getSiNo() != null)
                   invoiceNo = saleInoviceData.getSiData().getSiNo();
               if (saleInoviceData.getSiData().getCutomerName() != null){}
               //invoiceCustomerName = saleInoviceData.getSiData().getUserName();
               if (saleInoviceData.getSiData().getTokenNo()!=null){

                   tokenNo=""+saleInoviceData.getSiData().getTokenNo();
               }
           }


           in.hiaccounts.hinext.restaurant.model.checkout.CompanyData companyData = saleInoviceData.getCompanyData();
           if (companyData != null) {
               if (saleInoviceData.getCompanyData().getCompanyName() != null) {
                   companyName = saleInoviceData.getCompanyData().getCompanyName().toUpperCase();
               }
               if (companyData.getAddress() != null)
                   companyAddress = companyData.getAddress();
               if (companyData.getPhone() != null)
                   companyTelNumber = companyData.getPhone();
               if (companyData.getFax() != null)
                   companyFaxNumber = companyData.getFax();
               if (companyData.getRegisterNo() != null)
                   companyGSTNumber = companyData.getRegisterNo();
           }


           //LF = Line feed
           Activity_RestraPayment1.BLUETOOTH_PRINTER.Begin();
           Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
           // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
        /*    Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("");
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30); */   //30 * 0.125mm
   /*        if (bitmap!=null) {
               //Activity_RestraPayment1.BLUETOOTH_PRINTER.printByteData(logoByteArray);

               // Activity_RestraPayment1.BLUETOOTH_PRINTER.printImage(Bitmap.createScaledBitmap(bitmap, 200, 200, false));
               Bitmap newBm = BitmapConvertUtil.decodeSampledBitmapFromBitmap(bitmap, 100);
               byte xL = (byte) (((newBm.getWidth() - 1) / 8 + 1) % 256);
               byte xH = (byte) (((newBm.getWidth() - 1) / 8 + 1) / 256);
               byte yL = (byte) (newBm.getHeight() % 256);
               byte yH = (byte) (newBm.getHeight() / 256);
               byte[] pixels = BitmapConvertUtil.convert(newBm);

               Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
               Activity_RestraPayment1.BLUETOOTH_PRINTER.printImage(Bitmap.createScaledBitmap(bitmap, 150, 150, false));

                *//*Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(new byte[]{29, 118, 118, 10, xL, xH, yL, yH});

                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//CENTER
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(pixels);*//*
           }*/


           if (companyName!=null && !companyName.equals("")) {
               Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
               Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
               Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
               Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x11);//normal
               Activity_RestraPayment1.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
               Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(companyName +"\n");
               //  Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
           }


           if (companyAddress!=null && !companyAddress.equals("")) {
               Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
               Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
               Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
               Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( companyAddress +"\n");
               // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
           }

           if (companyTelNumber!=null && !companyTelNumber.equals("")) {
               Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
               Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
               Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
               Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Phone : " + companyTelNumber +"\n");
               // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
           }

           if (companyGSTNumber!=null && !companyGSTNumber.equals("")) {
               Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
               Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
               Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
               Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("GSTIN: " + companyGSTNumber +"\n");
               // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
           }
           if (date!=null && !date.equals("")) {
              // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
               Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
               Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
               Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
               Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("DATE: " + date +"\n");
               //  Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
           }
           if (invoiceNo!=null && !invoiceNo.equals("")) {
               Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
               Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
               Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
               Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Invoice No: " + invoiceNo +"\n");
               //  Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
           }
           // String customerName="customerA";
           if (saleInoviceData.getSiData().getCutomerName()!=null && !saleInoviceData.getSiData().getCutomerName().equals("")){
               //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
               Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
               Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
               Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
               Activity_RestraPayment1.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
               Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Customer: "+saleInoviceData.getSiData().getCutomerName() +"\n");
           }
           if (saleInoviceData.getSiData().getCustomerNo()!=null && !saleInoviceData.getSiData().getCustomerNo().equals("")){
               //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
               Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
               Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
               Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
               Activity_RestraPayment1.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
               Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Phone: "+saleInoviceData.getSiData().getCustomerNo() +"\n");
           }
           if (saleInoviceData.getSiData().getCustomerAddress()!=null && !saleInoviceData.getSiData().getCustomerAddress().equals("")){
               //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
               Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
               Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
               Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
               Activity_RestraPayment1.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
               Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Address: "+saleInoviceData.getSiData().getCustomerAddress() +"\n");
           }
           //String waiterName="NA";
           if (saleInoviceData.getSiData().getEmployeeName()!=null && !saleInoviceData.getSiData().getEmployeeName().equals("") && !isNotification){
               // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
               Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
               Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
               Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
               Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Steward: "+saleInoviceData.getSiData().getEmployeeName()+ "\n");
           }

           if (saleInoviceData.getSiData().getTable()!=null && !saleInoviceData.getSiData().getTable().equals("")){
               //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
               Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
               Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
               Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
               Activity_RestraPayment1.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
               Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Table No: "+saleInoviceData.getSiData().getTable() +"\n");
           }

           if (saleInoviceData.getSiData().getEmail()!=null && !saleInoviceData.getSiData().getEmail().equals("")){
               // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
               Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
               Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
               Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
               Activity_RestraPayment1.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
               Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Token: "+saleInoviceData.getSiData().getEmail() +"\n");
           }
           //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
           Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
           Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
           Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line)+ "\n");

           double amtExTax = 0,amtIncTax= 0,totalAmount=0.00;
           if (saleInoviceData.getSiData() != null) {
               String  itemName = "";
               String itemUnitPrice = "";
               String taxPercentage = "";
               String itemQty = "";
               String itemAmtInclTax = "";
               String itemTax = "";
               String qtyHeader = "Qty";
               int qtyHeaderStringLenght = 4-qtyHeader.length();
               for (int j=0;j<qtyHeaderStringLenght;j++){
                   qtyHeader=" "+qtyHeader;
               }
               String priceHeader = "Price";
               int priceSpaceStringLenght = 8-priceHeader.length();
               for (int j=0;j<priceSpaceStringLenght;j++){
                   priceHeader=" "+priceHeader;
               }

               String amountHeader = "Amount";
               int amountSpaceStringLenght = 8-amountHeader.length();
               for (int j=0;j<amountSpaceStringLenght;j++){
                   amountHeader=" "+amountHeader;
               }
               String itemHeader = "Item Details";
              /* int itemHeaderStringLenght = itemHeader.length();
               for (int j=0;j<itemHeaderStringLenght;j++){
                   itemHeader=itemHeader+" ";
               }*/
               String itemheader=padRight(itemHeader, 23);
               if (saleInoviceData.getSiData().getSelectedItemsList() != null) {

                   // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                   Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 2);//LEFT
                   Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);
                   Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);
                   Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(itemheader+"  " +priceHeader+" "+qtyHeader+"  "+amountHeader+"\n");
                   // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                   //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                   Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//CENTER
                   Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line)+ "\n");


                   for (int i = 0; i < saleInoviceData.getSiData().getSelectedItemsList().size(); i++) {
                       RestraCheckoutItem item = saleInoviceData.getSiData().getSelectedItemsList().get(i);
                       if (item != null) {


                           itemUnitPrice = String.format("%.2f", item.getUnitPrice());

                           int amtSpaceStringLenght = 8-itemUnitPrice.length();
                           for (int j=0;j<amtSpaceStringLenght;j++){
                               itemUnitPrice=" "+itemUnitPrice;
                           }

                           if (item.getItemName() != null)
                               itemName = item.getItemName();

                           int itemNameSpaceStringLenght = 0;
                           for (int j=0;j<itemNameSpaceStringLenght;j++){
                               itemName=itemName+" ";
                           }

                           //taxPercentage = String.format("%.2f", item.getGstItemTax());
                           totalAmount +=item.getQty()*item.getUnitPrice();
                            itemAmtInclTax = String.format("%.2f", (item.getQty()*item.getUnitPrice()));
                           //itemAmtInclTax = String.format("%.2f", (item.getAmtinclusivetax()));

                           int amtSpaceStringLenght1=8-itemAmtInclTax.length();
                           for (int j=0;j<amtSpaceStringLenght1;j++){
                               itemAmtInclTax=" "+itemAmtInclTax;
                           }


                           itemQty = "" + item.getQty();
                           int amtSpaceStringLenght2 = 4-itemQty.length();
                           for (int j=0;j<amtSpaceStringLenght2;j++){
                               itemQty=itemQty+" ";
                           }
                           amtExTax=amtExTax+(item.getQty()*item.getUnitPrice());

                           Double price = (item.getAmtinclusivetax())/(item.getQty());

                           String priceVal = String.format("%.2f",item.getUnitPrice());


                           //Double amount = item.getUnitPrice()*item.getQty();
                          // String amountTot = String.format("%.2f",amount);



                           int amtStringLenght2 = 4-priceVal.length();
                           for (int j=0;j<amtStringLenght2;j++){
                               priceVal=" "+priceVal;
                           }

                           String itemname=padRight(itemName, 26);

                         /*   Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal font
                            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( itemName +"\n");*/

                           //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                           Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                           Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                           Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(itemname+" " + priceVal +"  " + itemQty + "  " + itemAmtInclTax + "\n");



                       }


                   }
               }
           }
           //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
           Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
           Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line));

           String totalCheckOutAmt=String.format("%.2f",totalAmount);
           String totalTaxAmt=String.format("%.2f",saleInoviceData.getSiData().getTotalTaxAmt());
           String discountAmtPercentage="";

           if (saleInoviceData.getSiData().getDiscountAmount()!=0.00){
               discountAmtPercentage = String.valueOf(saleInoviceData.getSiData().getDiscountAmount());
               if (discountAmtPercentage!=null){
                   int discountSpaceStringLenght=8-discountAmtPercentage.length();
                   for (int j=0;j<discountSpaceStringLenght;j++){
                       discountAmtPercentage=" "+discountAmtPercentage;
                   }
               }


           }else {
               discountAmtPercentage="0.00";
           }


           String discount="";

           if (saleInoviceData.getSiData().getDiscountAmtInPercentage()!=0.0){
               discount=String.format("%.2f",saleInoviceData.getSiData().getDiscountAmtInPercentage());

           }else{
               discount=String.format("%.2f",0.0d);
           }
           if (discountAmtPercentage!=null){
               int discountSpaceStringLenght=8-discountAmtPercentage.length();
               for (int j=0;j<discountSpaceStringLenght;j++){
                   discountAmtPercentage=" "+discountAmtPercentage;
               }
           }


           if (totalCheckOutAmt!=null){
               int amtSpaceStringLenght=8-totalCheckOutAmt.length();
               for (int j=0;j<amtSpaceStringLenght;j++){
                   totalCheckOutAmt=" "+totalCheckOutAmt;
               }
           }


           if (totalTaxAmt!=null){
               int taxSpaceStringLenght=8-totalTaxAmt.length();
               for (int j=0;j<taxSpaceStringLenght;j++){
                   totalTaxAmt=" "+totalTaxAmt;
               }
           }


           String subTotal ="Sub Total:";
           String subTotalName=padRight(subTotal, 40);
           Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
           Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
           Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(subTotalName + totalCheckOutAmt +"\n" );
           if (discount!=null && !discount.equals("0.00")){
               String discountHeader ="Discount:("+discount+"%): ";
               String discountHeaderName=padRight(discountHeader, 40);
               // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
               Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
               Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(discountHeaderName + discountAmtPercentage +"\n");

           }
           // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
           Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
           Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line)+"\n");
           String taxPercentageSplit ="",netBill="";
            Double netBalance =0.00;
           if (saleInoviceData.getSiData().getSelectedItemsList()!=null && saleInoviceData.getSiData().getSelectedItemsList().size()>0){
               int count = saleInoviceData.getSiData().getSelectedItemsList().size();
               Double taxPercentage = saleInoviceData.getSiData().getTotalTaxAmt()/2;
               taxPercentageSplit = String.format("%.2f",taxPercentage);
           }else{
               taxPercentageSplit = "0.00";
           }

           if (totalAmount!=0.00){
               netBalance = totalAmount - saleInoviceData.getSiData().getDiscountAmount();
               netBill =  String.format("%.2f",netBalance);
               if (netBill!=null){
                   int taxPerSpaceStringLenght=8-netBill.length();
                   for (int j=0;j<taxPerSpaceStringLenght;j++){
                       netBill=" "+netBill;
                   }
               }
           }

           if (taxPercentageSplit!=null){
               int taxPerSpaceStringLenght=8-taxPercentageSplit.length();
               for (int j=0;j<taxPerSpaceStringLenght;j++){
                   taxPercentageSplit=" "+taxPercentageSplit;
               }
           }

           //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
           String netTotalHeader ="Net Total : ";
           String netTotalHeaderName=padRight(netTotalHeader, 40);
           Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
           Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( netTotalHeaderName+ netBill +"\n");
           if (taxPercentageSplit!=null){
               Double taxPer=0.00;
               if (saleInoviceData.getSiData().getTaxWiseSummaryList()!=null&& saleInoviceData.getSiData().getTaxWiseSummaryList().size()>0){
                   taxPer = saleInoviceData.getSiData().getTaxWiseSummaryList().get(0).getTaxPercent()/2;
                   String cgstHeader ="CGST("+taxPer +"%) :";
                   String cgstHeaderName=padRight(cgstHeader, 40);
                   String sgstHeader ="SGST ("+taxPer +"%):";
                   String sgstHeaderName=padRight(sgstHeader, 40);
                   //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                   Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                   Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( cgstHeaderName+ taxPercentageSplit +"\n");
                   //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                   Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                   Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( sgstHeaderName+ taxPercentageSplit +"\n");



               }

           }
           String roundingOffValue="";
           Double roundval=0.00;
           if (saleInoviceData.getSiData().getRoundingOffValue()!=null && !saleInoviceData.getSiData().getRoundingOffValue().equals("0.00")){
               roundval = Double.valueOf(saleInoviceData.getSiData().getRoundingOffValue());

               if(roundval*1<0){
                   roundingOffValue = String.valueOf(roundval*-1);
               }else{
                   roundingOffValue = String.valueOf((( roundval*-1))/1);
               }


               if (roundingOffValue!=null){
                   int roundingOffValueSpaceStringLenght=8-roundingOffValue.length();
                   for (int j=0;j<roundingOffValueSpaceStringLenght;j++){
                       roundingOffValue=" "+roundingOffValue;
                   }
               }

               if (roundingOffValue!=null){

                   // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                   String roundingOffHeader ="RoundingOffValue: ";
                   String roundingOffHeaderName=padRight(roundingOffHeader, 40);
                   Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                   Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( roundingOffHeaderName + roundingOffValue +"\n");

               }
           }




           //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
           Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
           Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line)+"\n");


           //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
          /* Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 2);//LEFT
           Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("  Total GST : " + totalTaxAmt +"\n");

        */
           Double grandTotal=0.00;
           if (paidAmount!=null){
               grandTotal = Double.valueOf(paidAmount);
           }
           paidAmount = String.format("%.2f",grandTotal);

           if (paidAmount!=null){
               int paidAmtSpaceStringLenght=8-paidAmount.length();
               for (int j=0;j<paidAmtSpaceStringLenght;j++){
                   paidAmount=" "+paidAmount;
               }
           }
           //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
           String grandTotalHeader ="Grand Total : ";
           String grandTotalHeaderName=padRight(grandTotalHeader, 40);
           Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
           Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( grandTotalHeaderName+ paidAmount  +"\n");
           Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
           Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line)+"\n");
           //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
           Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
           Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
           Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Payment Method:" + "\n");
           Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
           Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line)+"\n");
           if (saleInoviceData.getSiData().getCashPayment() !=null && saleInoviceData.getSiData().getCashPayment().getMultiCashPaymentList().size()>0){
               if (saleInoviceData.getSiData().getCashPayment().getPaymentTypeCash()!=null && saleInoviceData.getSiData().getCashPayment().getPaymentTypeCash().equals("Cash")){
                   String cashAmount="";
                   for (MultiCashPaymentList cashPaymentList:saleInoviceData.getSiData().getCashPayment().getMultiCashPaymentList()){
                       cashAmount = String.format("%.2f",cashPaymentList.getCashAmt());

                       if (cashAmount!=null){
                           int paidAmtSpaceStringLenght=8-cashAmount.length();
                           for (int j=0;j<paidAmtSpaceStringLenght;j++){
                               cashAmount=" "+cashAmount;
                           }
                       }

                       String cashAmountHeader = saleInoviceData.getSiData().getCashPayment().getPaymentTypeCash();
                       String cashAmountHeaderName=padRight(cashAmountHeader, 40);
                       Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                       Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( cashAmountHeaderName+ cashAmount  +"\n");

                   }
               }

           }
           if (saleInoviceData.getSiData().getBankPayment() !=null && saleInoviceData.getSiData().getBankPayment().getMultiBankPaymentList().size()>0){
               if (saleInoviceData.getSiData().getBankPayment().getPaymentTypeCash()!=null && !saleInoviceData.getSiData().getBankPayment().getPaymentTypeCash().equals("")){
                   Double totalBank=0.00;
                   String bank="";
                   for (MultiBankPaymentList bankPaymentList:saleInoviceData.getSiData().getBankPayment().getMultiBankPaymentList()){
                       if (bankPaymentList.getAmount()!=0.00 ){
                           totalBank+=bankPaymentList.getAmount();
                       }
                   }
                   bank = String.format("%.2f",totalBank);
                   if (bank!=null){
                       int paidAmtSpaceStringLenght=8-bank.length();
                       for (int j=0;j<paidAmtSpaceStringLenght;j++){
                           bank=" "+bank;
                       }
                   }
                   String bankHeader = saleInoviceData.getSiData().getBankPayment().getPaymentTypeCash();
                   String bankHeaderHeaderName=padRight(bankHeader, 40);
                   Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                   Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(bankHeaderHeaderName + bank +"\n");

                   for (MultiBankPaymentList bankPaymentList:saleInoviceData.getSiData().getBankPayment().getMultiBankPaymentList()){
                       if (bankPaymentList.getBankName()!=null && !bankPaymentList.getBankName().equals("")){
                           String  bankname = "Bank Name ";
                           String banknameHeaderName=padRight(bankname, 15);

                           Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                           Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( banknameHeaderName +": "+ bankPaymentList.getBankName()  +"\n");

                       }
                       if (bankPaymentList.getDate()!=null && !bankPaymentList.getDate().equals("")){
                           //DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                           //Calendar cal = Calendar.getInstance();
                           //dateFormat.format(cal.getTime())
                           String  bankdate = "Date ";
                           String bankdateHeaderName=padRight(bankdate, 15);
                           Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                           String str = bankPaymentList.getDate();

                           try {
                               long timestamp = Long.parseLong(str);
                               Date d = new Date(timestamp);
                               //final TimeZone utc = TimeZone.getTimeZone("UTC");
                               SimpleDateFormat sf = new SimpleDateFormat("dd-MMM-yyyy @hh:mm a");
                               //sf.setTimeZone(utc);
                               //String strUTCDate = dateFormatter.format(dt);
                               //SimpleDateFormat sf = new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                               Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                               Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( bankdateHeaderName+": "+ sf.format(d)  +"\n");

                           } catch (Exception e) {
                               e.printStackTrace();
                           }

                       }
                       if (bankPaymentList.getAmount()!=0.00 ){
                           String  bankAmount = "Amount ";
                           String bankAmountHeaderName=padRight(bankAmount, 15);
                           Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                           Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( bankAmountHeaderName+": "+ bankPaymentList.getAmount()  +"\n");

                       }
                   }
               }

           }
           if (saleInoviceData.getSiData().getCreditPayment() !=null && saleInoviceData.getSiData().getCreditPayment().getCardPaymentList().size()>0){
               if (saleInoviceData.getSiData().getCreditPayment().getPaymentTypeCash()!=null && !saleInoviceData.getSiData().getCreditPayment().getPaymentTypeCash().equals("")){

                   Double totalCard=0.00;
                   String card="";
                   for (CardPaymentList cardPaymentList:saleInoviceData.getSiData().getCreditPayment().getCardPaymentList()){
                       if (cardPaymentList.getCardAmt()!=0.00 ){
                           totalCard+=cardPaymentList.getCardAmt();
                       }
                   }
                   card = String.format("%.2f",totalCard);
                   if (card!=null){
                       int paidAmtSpaceStringLenght=8-card.length();
                       for (int j=0;j<paidAmtSpaceStringLenght;j++){
                           card=" "+card;
                       }
                   }
                   String cardHeader = saleInoviceData.getSiData().getCreditPayment().getPaymentTypeCash();
                   String cardHeaderHeaderName=padRight(cardHeader, 40);
                   Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                   Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(cardHeaderHeaderName + card +"\n");

                   for (CardPaymentList cardPaymentList:saleInoviceData.getSiData().getCreditPayment().getCardPaymentList()){
                       if (cardPaymentList.getCardNo()!=null && !cardPaymentList.getCardNo().equals("")){
                           String  cardNo = "Card No ";
                           String cardHeaderName=padRight(cardNo, 15);
                           Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                           Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( cardHeaderName+": "+ cardPaymentList.getCardNo()  +"\n");

                       }
                       if (cardPaymentList.getCardDate()!=null && !cardPaymentList.getCardDate().equals("")){
                           String  cardDate = "Date ";
                           String cardHeaderName=padRight(cardDate, 15);
                           DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss.SSSXXX");
                           try {
                               Date datecard = dateFormat.parse(cardPaymentList.getCardDate());//You will get date object relative to server/client timezone wherever it is parsed
                               DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy @hh:mm a"); //If you need time just put specific format for time like 'HH:mm:ss'
                               String dateStr = formatter.format(datecard);
                               Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                               Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( cardHeaderName+": "+ dateStr +"\n");

                           } catch (ParseException e) {
                               e.printStackTrace();
                           }



                       }
                       if (cardPaymentList.getCardAmt()!=0.00){
                           String  cardAmt = "Card Amount ";
                           String cardHeaderName=padRight(cardAmt, 15);
                           Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                           Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( cardHeaderName+": "+ cardPaymentList.getCardAmt()  +"\n");

                       }

                   }
               }

           }
           if (saleInoviceData.getSiData().getVoucherPayment() !=null && saleInoviceData.getSiData().getVoucherPayment().getMultiVoucherPayments().size()>0){
               if (saleInoviceData.getSiData().getVoucherPayment().getPaymentTypeCash()!=null && saleInoviceData.getSiData().getVoucherPayment().getPaymentTypeCash().equals("Discount Voucher")){
                   if (saleInoviceData.getSiData().getVoucherPayment().getMultiVoucherPayments().get(0).getVoucherNo()!=null && !saleInoviceData.getSiData().getVoucherPayment().getMultiVoucherPayments().get(0).getVoucherNo().equals("Discount Given")){
                       Double totalVoucher=0.00;
                       String voucher="";
                       for (MultiVoucherPayment voucherPayment:saleInoviceData.getSiData().getVoucherPayment().getMultiVoucherPayments()){
                           if (voucherPayment.getVoucherAmt()!=0.00 ){
                               totalVoucher+=voucherPayment.getVoucherAmt();
                           }
                       }
                       voucher = String.format("%.2f",totalVoucher);
                       if (voucher!=null){
                           int paidAmtSpaceStringLenght=8-voucher.length();
                           for (int j=0;j<paidAmtSpaceStringLenght;j++){
                               voucher=" "+voucher;
                           }
                       }
                       String voucherHeader = saleInoviceData.getSiData().getVoucherPayment().getPaymentTypeCash();
                       String voucherHeaderName=padRight(voucherHeader, 40);
                       Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                       Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(voucherHeaderName + voucher +"\n");

                   }
                   for (MultiVoucherPayment voucherPayment:saleInoviceData.getSiData().getVoucherPayment().getMultiVoucherPayments()){
                       // if (voucherPayment.getVoucherNo()!=null && !voucherPayment.getVoucherNo().equals("")){
                       if (voucherPayment.getVoucherNo()!=null && !voucherPayment.getVoucherNo().equals("")){
                           String  voucher = "Voucher No ";
                           String voucherHeaderName=padRight(voucher, 15);
                           Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                           Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( voucherHeaderName+": "+ voucherPayment.getVoucherNo()  +"\n");

                       }
                       if (voucherPayment.getVoucherAmt()!=0.00){
                           String  voucher = "Voucher Amount ";
                           String voucherHeaderName=padRight(voucher, 15);
                           Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                           Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( voucherHeaderName+": "+ voucherPayment.getVoucherAmt()  +"\n");

                       }
                       //  }




                   }
               }

           }
           // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
           Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
           Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_star)  +"\n");
           Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
         /*   Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();*/



        /*   Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
           Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte)30);
           if (saleInoviceData.getSiData().getTable()!=null && !saleInoviceData.getSiData().getTable().equals("")){
               Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Sale Type : Dine-In Order" +"\n");

           }
           else {
               Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Sale Type : Take Away" +"\n");

           }
           //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
           Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
           Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte)30);
           Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Payment Type: Cash" +"\n");*/
           //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
           Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
           Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte)30);
           Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Thank You...! Visit again" +"\n");
           // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
           Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
           Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte)30);
           Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Designed & Powered by Hiaccounts" +"\n");
           Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
           Activity_RestraPayment1.BLUETOOTH_PRINTER.PartialCutPaper();


       }
       return true;

   }
    public static boolean printInvoice3InchCustomer(Context context, RestraCheckoutData saleInoviceData, Bitmap bitmap, String paidAmount, in.hiaccounts.hinext.controlpanel.model.company_setup.compnayinformation.CompanyData companyData, double totalTaxAmount, boolean isNotification) {
        if (Activity_RestraPayment1.BLUETOOTH_PRINTER.IsNoConnection()) {
            return false;
        }

        if (saleInoviceData == null) {
            return false;
        } else {
            String serialNo= "";
            String tokenNo = "";
            String companyName = "";
            String companyTelNumber = "";
            String companyFaxNumber = "";
            String companyGSTNumber = "";
            String companyAddress="";
            String date = "", time = "", invoiceNo = "", invoiceCustomerName = "";
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date dateFo = new Date();
            date = dateFormat.format(dateFo);




            if (companyData != null) {
                if (companyData.getCompanyName() != null) {
                    companyName = companyData.getCompanyName().toUpperCase();
                }
                if (companyData.getAddress() != null)
                    companyAddress = companyData.getAddress();
                if (companyData.getPhone() != null)
                    companyTelNumber = companyData.getPhone();
                if (companyData.getFax() != null)
                    companyFaxNumber = companyData.getFax();
                if (companyData.getRegisterNo() != null)
                    companyGSTNumber = companyData.getRegisterNo();
            }


            //LF = Line feed
            Activity_RestraPayment1.BLUETOOTH_PRINTER.Begin();

           // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
        /*    Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("");
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30); */   //30 * 0.125mm
/*            if (bitmap!=null) {
                //Activity_RestraPayment1.BLUETOOTH_PRINTER.printByteData(logoByteArray);

                // Activity_RestraPayment1.BLUETOOTH_PRINTER.printImage(Bitmap.createScaledBitmap(bitmap, 200, 200, false));
                Bitmap newBm = BitmapConvertUtil.decodeSampledBitmapFromBitmap(bitmap, 100);
                byte xL = (byte) (((newBm.getWidth() - 1) / 8 + 1) % 256);
                byte xH = (byte) (((newBm.getWidth() - 1) / 8 + 1) / 256);
                byte yL = (byte) (newBm.getHeight() % 256);
                byte yH = (byte) (newBm.getHeight() / 256);
                byte[] pixels = BitmapConvertUtil.convert(newBm);

                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_RestraPayment1.BLUETOOTH_PRINTER.printImage(Bitmap.createScaledBitmap(bitmap, 150, 150, false));

                *//*Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(new byte[]{29, 118, 118, 10, xL, xH, yL, yH});

                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//CENTER
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(pixels);*//*
            }*/


            if (companyName!=null && !companyName.equals("")) {
                Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x11);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(companyName +"\n");
              //  Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            }


            if (companyAddress!=null && !companyAddress.equals("")) {
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( companyAddress +"\n");
                // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            }

            if (companyTelNumber!=null && !companyTelNumber.equals("")) {
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Phone : " + companyTelNumber +"\n");
               // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            }

            if (companyGSTNumber!=null && !companyGSTNumber.equals("")) {
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("GSTIN: " + companyGSTNumber +"\n");
               // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            }


            if (date!=null && !date.equals("")) {
                //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("DATE: " + date +"\n");
                //  Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            }
            //if (invoiceNo!=null && !invoiceNo.equals("")) {
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Invoice Number: * Customer Bill *"+"\n");
                //  Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            //}
            if (saleInoviceData.getCustomerName()!=null && !saleInoviceData.getCustomerName().equals("")){
                //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Customer: "+saleInoviceData.getCustomerName() +"\n");
            }
            if (saleInoviceData.getCustomerNo()!=null && !saleInoviceData.getCustomerNo().equals("")){
                //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Phone: "+saleInoviceData.getCustomerNo() +"\n");
            }
            if (saleInoviceData.getCustomerAddress()!=null && !saleInoviceData.getCustomerAddress().equals("")){
                //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Address: "+saleInoviceData.getCustomerAddress() +"\n");
            }
             //String waiterName="NA";
            if (saleInoviceData.getWaiterName()!=null && !saleInoviceData.getWaiterName().equals("") && !isNotification){
               // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Steward: "+saleInoviceData.getWaiterName()+ "\n");
            }

            if (saleInoviceData.getTableName()!=null && !saleInoviceData.getTableName().equals("")){
                //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Table No: "+saleInoviceData.getTableName() +"\n");
            }

            Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line)+ "\n");

            double amtExTax = 0,amtIncTax= 0,totalAmount=0.00,taxPer=0.00,gstTaxPercantage=0.00;
            if (saleInoviceData != null) {
                String  itemName = "";
                String itemUnitPrice = "";
                String taxPercentage = "";
                String itemQty = "";
                String itemAmtInclTax = "";
                String itemTax = "";
                String qtyHeader = "Qty";
                int qtyHeaderStringLenght = 4-qtyHeader.length();
                for (int j=0;j<qtyHeaderStringLenght;j++){
                    qtyHeader=" "+qtyHeader;
                }
                String priceHeader = "Price";
                int priceSpaceStringLenght = 8-priceHeader.length();
                for (int j=0;j<priceSpaceStringLenght;j++){
                    priceHeader=" "+priceHeader;
                }

                String amountHeader = "Amount";
                int amountSpaceStringLenght = 8-amountHeader.length();
                for (int j=0;j<amountSpaceStringLenght;j++){
                    amountHeader=" "+amountHeader;
                }
                String itemHeader = "Item Details";
              /* int itemHeaderStringLenght = itemHeader.length();
               for (int j=0;j<itemHeaderStringLenght;j++){
                   itemHeader=itemHeader+" ";
               }*/
                String itemheader=padRight(itemHeader, 23);
                if (saleInoviceData.getSelectedItemsList() != null) {

                    // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                    Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 2);//LEFT
                    Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);
                    Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);
                    Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(itemheader+"  " +priceHeader+" "+qtyHeader+"  "+amountHeader+"\n");
                    // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                    //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                    Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//CENTER
                    Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line)+ "\n");


                    for (int i = 0; i < saleInoviceData.getSelectedItemsList().size(); i++) {
                        RestraCheckoutItem item = saleInoviceData.getSelectedItemsList().get(i);
                        if (item != null) {


                            itemUnitPrice = String.format("%.2f", item.getUnitPrice());

                            int amtSpaceStringLenght = 8-itemUnitPrice.length();
                            for (int j=0;j<amtSpaceStringLenght;j++){
                                itemUnitPrice=" "+itemUnitPrice;
                            }

                            if (item.getItemName() != null)
                                itemName = item.getItemName();

                            int itemNameSpaceStringLenght = 0;
                            for (int j=0;j<itemNameSpaceStringLenght;j++){
                                itemName=itemName+" ";
                            }

                            gstTaxPercantage =  item.getGstTaxPercantage();
                            //taxPer = item.getGstItemTax();
                            totalAmount +=item.getQty()*item.getUnitPrice();
                            itemAmtInclTax = String.format("%.2f", (item.getQty()*item.getUnitPrice()));
                            //itemAmtInclTax = String.format("%.2f", (item.getAmtinclusivetax()));

                            int amtSpaceStringLenght1=8-itemAmtInclTax.length();
                            for (int j=0;j<amtSpaceStringLenght1;j++){
                                itemAmtInclTax=" "+itemAmtInclTax;
                            }


                            itemQty = "" + item.getQty();
                            int amtSpaceStringLenght2 = 4-itemQty.length();
                            for (int j=0;j<amtSpaceStringLenght2;j++){
                                itemQty=itemQty+" ";
                            }
                            amtExTax=amtExTax+(item.getQty()*item.getUnitPrice());

                            Double price = (item.getAmtinclusivetax())/(item.getQty());

                            String priceVal = String.format("%.2f",item.getUnitPrice());


                            //Double amount = item.getUnitPrice()*item.getQty();
                            // String amountTot = String.format("%.2f",amount);



                            int amtStringLenght2 = 4-priceVal.length();
                            for (int j=0;j<amtStringLenght2;j++){
                                priceVal=" "+priceVal;
                            }

                            String itemname=padRight(itemName, 26);

                         /*   Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal font
                            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( itemName +"\n");*/

                            //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(itemname+" " + priceVal +"  " + itemQty + "  " + itemAmtInclTax + "\n");



                        }


                    }
                }
            }
            //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line));

            String totalCheckOutAmt=String.format("%.2f",totalAmount);

            String discountAmtPercentage="";

            if (saleInoviceData.getDiscountAmount()!=null && !saleInoviceData.getDiscountAmount().equals("")){
                discountAmtPercentage = String.format("%.2f",saleInoviceData.getDiscountAmount());
                if (discountAmtPercentage!=null){
                    int discountSpaceStringLenght=8-discountAmtPercentage.length();
                    for (int j=0;j<discountSpaceStringLenght;j++){
                        discountAmtPercentage=" "+discountAmtPercentage;
                    }
                }


            }else {
                discountAmtPercentage="0.00";
            }


            String discount="";

            if (saleInoviceData.getDiscountAmtInPercentage()!=0.0){
                discount=String.format("%.2f",saleInoviceData.getDiscountAmtInPercentage());

            }else{
                discount=String.format("%.2f",0.0d);
            }
            if (discountAmtPercentage!=null){
                int discountSpaceStringLenght=8-discountAmtPercentage.length();
                for (int j=0;j<discountSpaceStringLenght;j++){
                    discountAmtPercentage=" "+discountAmtPercentage;
                }
            }


            if (totalCheckOutAmt!=null){
                int amtSpaceStringLenght=8-totalCheckOutAmt.length();
                for (int j=0;j<amtSpaceStringLenght;j++){
                    totalCheckOutAmt=" "+totalCheckOutAmt;
                }
            }




            String subTotal ="Sub Total:";
            String subTotalName=padRight(subTotal, 40);
            Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(subTotalName + totalCheckOutAmt +"\n" );
            if (discount!=null && !discount.equals("0.00")){
                String discountHeader ="Discount:("+discount+"%): ";
                String discountHeaderName=padRight(discountHeader, 40);
                // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(discountHeaderName + discountAmtPercentage +"\n");

            }
            // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line)+"\n");
            String taxPercentageSplit ="",netBill="";
            Double netBalance =0.00;
            String totalTaxAmt="";
            if (totalTaxAmount!=0.00){
                totalTaxAmt=String.format("%.2f",(totalTaxAmount/2));
            }else{
                totalTaxAmt ="0.00";
            }



            if (totalTaxAmt!=null){
                int taxSpaceStringLenght=8-totalTaxAmt.length();
                for (int j=0;j<taxSpaceStringLenght;j++){
                    totalTaxAmt=" "+totalTaxAmt;
                }
            }

            try {
                if (totalAmount!=0.00 || saleInoviceData.getDiscountAmount()!=null){
                    netBalance = totalAmount - saleInoviceData.getDiscountAmount();
                    netBill =  String.format("%.2f",netBalance);
                    if (netBill!=null){
                        int taxPerSpaceStringLenght=8-netBill.length();
                        for (int j=0;j<taxPerSpaceStringLenght;j++){
                            netBill=" "+netBill;
                        }
                    }
                }
            }catch (Exception e){

            }




            //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            String netTotalHeader ="Net Total : ";
            String netTotalHeaderName=padRight(netTotalHeader, 40);
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( netTotalHeaderName+ netBill +"\n");
            if (totalTaxAmt!=null){
                Double gstTaxPerc=0.00;
                if (saleInoviceData!=null){
                    gstTaxPerc = gstTaxPercantage/2;
                    String cgstHeader ="CGST("+gstTaxPerc +"%) :";
                    String cgstHeaderName=padRight(cgstHeader, 40);
                    String sgstHeader ="SGST ("+gstTaxPerc +"%):";
                    String sgstHeaderName=padRight(sgstHeader, 40);
                    //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                    if (netBalance!=0.00){
                        //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                        Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                        Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( cgstHeaderName+ totalTaxAmt +"\n");
                        //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                        Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                        Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( sgstHeaderName+ totalTaxAmt +"\n");

                    }else{
                        //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                        Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                        Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( cgstHeaderName+ 0.00 +"\n");
                        //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                        Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                        Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( sgstHeaderName+ 0.00 +"\n");

                    }

                }

            }
            String roundingOffValue="";
            Double roundval=0.00;
            if (saleInoviceData.getRoundingOffValue()!=null && !saleInoviceData.getRoundingOffValue().equals("0.00")){
                roundval = Double.valueOf(saleInoviceData.getRoundingOffValue());

                if(roundval*1<0){
                    roundingOffValue = String.valueOf(roundval*-1);
                }else{
                    roundingOffValue = String.valueOf((( roundval*-1))/1);
                }


                if (roundingOffValue!=null){
                    int roundingOffValueSpaceStringLenght=8-roundingOffValue.length();
                    for (int j=0;j<roundingOffValueSpaceStringLenght;j++){
                        roundingOffValue=" "+roundingOffValue;
                    }
                }

                if (roundingOffValue!=null){

                    // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                    String roundingOffHeader ="RoundingOffValue: ";
                    String roundingOffHeaderName=padRight(roundingOffHeader, 40);
                    Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                    Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( roundingOffHeaderName + roundingOffValue +"\n");

                }
            }



            //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line)+"\n");


            //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
          /* Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 2);//LEFT
           Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("  Total GST : " + totalTaxAmt +"\n");

        */
            Double grandTotal=0.00;
            if (paidAmount!=null){
                grandTotal = Double.valueOf(paidAmount);
            }
            paidAmount = String.format("%.2f",grandTotal);

            if (paidAmount!=null){
                int paidAmtSpaceStringLenght=8-paidAmount.length();
                for (int j=0;j<paidAmtSpaceStringLenght;j++){
                    paidAmount=" "+paidAmount;
                }
            }
            //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            String grandTotalHeader ="Grand Total : ";
            String grandTotalHeaderName=padRight(grandTotalHeader, 40);
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( grandTotalHeaderName+ paidAmount  +"\n");

            // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_star)  +"\n");
            Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();

            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte)30);
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Thank You...! Visit again" +"\n");
           // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte)30);
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Designed & Powered by Hiaccounts" +"\n");
            Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            Activity_RestraPayment1.BLUETOOTH_PRINTER.PartialCutPaper();


            //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            Activity_RestraPayment1.BLUETOOTH_PRINTER.CutPaper();

        }
        return true;

    }

    public static String padRight(String s, int n) {
        return String.format("%0$-"+n+"s", s);
    }
    public static boolean  printInvoice2Inch(Context context, RestraSaveCheckoutData saleInoviceData, Bitmap bitmap, String paidAmount, boolean isNotification) {
        if (Activity_RestraPayment1.BLUETOOTH_PRINTER.IsNoConnection()) {
            return false;
        }

        if (saleInoviceData == null) {
            return false;
        } else {
            String serialNo= "";
            String tokenNo = "";
            String companyName = "";
            String companyTelNumber = "";
            String companyFaxNumber = "";
            String companyGSTNumber = "";
            String companyAddress="";
            String date = "", time = "", invoiceNo = "", invoiceCustomerName = "";

            if (saleInoviceData.getDate() != null) {
                date = saleInoviceData.getDate();
            }
           /* if (saleInoviceData.getTime() != null) {
                time = saleInoviceData.getTime();
            }*/
            if (saleInoviceData.getSiData() != null) {
                if (saleInoviceData.getSiData().getSiNo() != null)
                    invoiceNo = saleInoviceData.getSiData().getSiNo();
                if (saleInoviceData.getSiData().getCutomerName() != null){}
                //invoiceCustomerName = saleInoviceData.getSiData().getUserName();
                if (saleInoviceData.getSiData().getTokenNo()!=null){

                    tokenNo=""+saleInoviceData.getSiData().getTokenNo();
                }
            }


            in.hiaccounts.hinext.restaurant.model.checkout.CompanyData companyData = saleInoviceData.getCompanyData();
            if (companyData != null) {
                if (saleInoviceData.getCompanyData().getCompanyName() != null) {
                    companyName = saleInoviceData.getCompanyData().getCompanyName().toUpperCase();
                }
                if (companyData.getAddress() != null)
                    companyAddress = companyData.getAddress();
                if (companyData.getPhone() != null)
                    companyTelNumber = companyData.getPhone();
                if (companyData.getFax() != null)
                    companyFaxNumber = companyData.getFax();
                if (companyData.getRegisterNo() != null)
                    companyGSTNumber = companyData.getRegisterNo();
            }


            //LF = Line feed
            Activity_RestraPayment1.BLUETOOTH_PRINTER.Begin();
            Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
        /*    Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("");
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30); */   //30 * 0.125mm
        /*    if (bitmap!=null) {
                //Activity_RestraPayment1.BLUETOOTH_PRINTER.printByteData(logoByteArray);

                // Activity_RestraPayment1.BLUETOOTH_PRINTER.printImage(Bitmap.createScaledBitmap(bitmap, 200, 200, false));
                Bitmap newBm = BitmapConvertUtil.decodeSampledBitmapFromBitmap(bitmap, 100);
                byte xL = (byte) (((newBm.getWidth() - 1) / 8 + 1) % 256);
                byte xH = (byte) (((newBm.getWidth() - 1) / 8 + 1) / 256);
                byte yL = (byte) (newBm.getHeight() % 256);
                byte yH = (byte) (newBm.getHeight() / 256);
                byte[] pixels = BitmapConvertUtil.convert(newBm);

                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_RestraPayment1.BLUETOOTH_PRINTER.printImage(Bitmap.createScaledBitmap(bitmap, 150, 150, false));

                *//*Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(new byte[]{29, 118, 118, 10, xL, xH, yL, yH});

                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//CENTER
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(pixels);*//*
            }*/


            if (companyName!=null && !companyName.equals("")) {
                Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x11);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(companyName +"\n");
                //  Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            }


            if (companyAddress!=null && !companyAddress.equals("")) {
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( companyAddress +"\n");
                // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            }

            if (companyTelNumber!=null && !companyTelNumber.equals("")) {
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Phone : " + companyTelNumber +"\n");
                // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            }

            if (companyGSTNumber!=null && !companyGSTNumber.equals("")) {
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("GSTIN: " + companyGSTNumber +"\n");
                // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            }
            if (date!=null && !date.equals("")) {
                // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("DATE: " + date +"\n");
                //  Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            }
            if (invoiceNo!=null && !invoiceNo.equals("")) {
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Invoice No: " + invoiceNo +"\n");
                //  Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            }
            // String customerName="customerA";
            if (saleInoviceData.getSiData().getCutomerName()!=null && !saleInoviceData.getSiData().getCutomerName().equals("")){
                //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Customer: "+saleInoviceData.getSiData().getCutomerName() +"\n");
            }
            if (saleInoviceData.getSiData().getCustomerNo()!=null && !saleInoviceData.getSiData().getCustomerNo().equals("")){
                //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Phone: "+saleInoviceData.getSiData().getCustomerNo() +"\n");
            }
            if (saleInoviceData.getSiData().getCustomerAddress()!=null && !saleInoviceData.getSiData().getCustomerAddress().equals("")){
                //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Address: "+saleInoviceData.getSiData().getCustomerAddress() +"\n");
            }
            //String waiterName="NA";
            if (saleInoviceData.getSiData().getEmployeeName()!=null && !saleInoviceData.getSiData().getEmployeeName().equals("") && !isNotification){
                // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Steward: "+saleInoviceData.getSiData().getEmployeeName()+ "\n");
            }

            if (saleInoviceData.getSiData().getTable()!=null && !saleInoviceData.getSiData().getTable().equals("")){
                //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Table No: "+saleInoviceData.getSiData().getTable() +"\n");
            }

            if (saleInoviceData.getSiData().getEmail()!=null && !saleInoviceData.getSiData().getEmail().equals("")){
                // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Token: "+saleInoviceData.getSiData().getEmail() +"\n");
            }
            //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line2inch)+ "\n");

            double amtExTax = 0,amtIncTax= 0,totalAmount=0.00;
            if (saleInoviceData.getSiData() != null) {
                String  itemName = "";
                String itemUnitPrice = "";
                String taxPercentage = "";
                String itemQty = "";
                String itemAmtInclTax = "";
                String itemTax = "";
                String qtyHeader = "Qty";
                int qtyHeaderStringLenght = 4-qtyHeader.length();
                for (int j=0;j<qtyHeaderStringLenght;j++){
                    qtyHeader=" "+qtyHeader;
                }
                String priceHeader = "Price";
                int priceSpaceStringLenght = 8-priceHeader.length();
                for (int j=0;j<priceSpaceStringLenght;j++){
                    priceHeader=" "+priceHeader;
                }

                String amountHeader = "Amount";
                int amountSpaceStringLenght = 8-amountHeader.length();
                for (int j=0;j<amountSpaceStringLenght;j++){
                    amountHeader=" "+amountHeader;
                }
                String itemHeader = "Item";
              /* int itemHeaderStringLenght = itemHeader.length();
               for (int j=0;j<itemHeaderStringLenght;j++){
                   itemHeader=itemHeader+" ";
               }*/
                String itemheader=padRight(itemHeader, 8);
                if (saleInoviceData.getSiData().getSelectedItemsList() != null) {

                    // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                    Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
                    Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);
                    Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);
                    Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(itemheader+" " +priceHeader+" "+qtyHeader+" "+amountHeader+"\n");
                    // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                    //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                    Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//CENTER
                    Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line2inch)+ "\n");


                    for (int i = 0; i < saleInoviceData.getSiData().getSelectedItemsList().size(); i++) {
                        RestraCheckoutItem item = saleInoviceData.getSiData().getSelectedItemsList().get(i);
                        if (item != null) {


                            itemUnitPrice = String.format("%.2f", item.getUnitPrice());

                            int amtSpaceStringLenght = 8-itemUnitPrice.length();
                            for (int j=0;j<amtSpaceStringLenght;j++){
                                itemUnitPrice=" "+itemUnitPrice;
                            }

                            if (item.getItemName() != null)
                                itemName = item.getItemName();

                            int itemNameSpaceStringLenght = 0;
                            for (int j=0;j<itemNameSpaceStringLenght;j++){
                                itemName=itemName+" ";
                            }

                            //taxPercentage = String.format("%.2f", item.getGstItemTax());
                            totalAmount +=item.getQty()*item.getUnitPrice();
                            itemAmtInclTax = String.format("%.2f", (item.getQty()*item.getUnitPrice()));
                            //itemAmtInclTax = String.format("%.2f", (item.getAmtinclusivetax()));

                            int amtSpaceStringLenght1=8-itemAmtInclTax.length();
                            for (int j=0;j<amtSpaceStringLenght1;j++){
                                itemAmtInclTax=" "+itemAmtInclTax;
                            }


                            itemQty = "" + item.getQty();
                            int amtSpaceStringLenght2 = 4-itemQty.length();
                            for (int j=0;j<amtSpaceStringLenght2;j++){
                                itemQty=""+itemQty;
                            }
                            amtExTax=amtExTax+(item.getQty()*item.getUnitPrice());

                            Double price = (item.getAmtinclusivetax())/(item.getQty());

                            String priceVal = String.format("%.2f",item.getUnitPrice());


                            //Double amount = item.getUnitPrice()*item.getQty();
                            // String amountTot = String.format("%.2f",amount);



                            int amtStringLenght2 = 8-priceVal.length();
                            for (int j=0;j<amtStringLenght2;j++){
                                priceVal=priceVal+" ";
                            }

                            String itemname="";
                            if (itemName.length()>11){
                                itemname = customerBreakLines(itemName,11,priceVal,itemQty,itemAmtInclTax);
                                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//CENTER
                                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( itemname +  "\n");
                            }else{
                                itemname=padRight(itemName, 11);
                                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//CENTER
                                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(itemname+" "+priceVal+" "+itemQty+" "+itemAmtInclTax + "\n");
                            }

                        }


                    }
                }
            }
            //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line2inch));

            String totalCheckOutAmt=String.format("%.2f",totalAmount);
            String totalTaxAmt=String.format("%.2f",saleInoviceData.getSiData().getTotalTaxAmt());
            String discountAmtPercentage="";

            if (saleInoviceData.getSiData().getDiscountAmount()!=0.00 ){
                discountAmtPercentage = String.valueOf(saleInoviceData.getSiData().getDiscountAmount());
                if (discountAmtPercentage!=null){
                    int discountSpaceStringLenght=8-discountAmtPercentage.length();
                    for (int j=0;j<discountSpaceStringLenght;j++){
                        discountAmtPercentage=" "+discountAmtPercentage;
                    }
                }


            }else {
                discountAmtPercentage="0.00";
            }


            String discount="";

            if (saleInoviceData.getSiData().getDiscountAmtInPercentage()!=0.0){
                discount=String.format("%.2f",saleInoviceData.getSiData().getDiscountAmtInPercentage());

            }else{
                discount=String.format("%.2f",0.0d);
            }
            if (discountAmtPercentage!=null){
                int discountSpaceStringLenght=8-discountAmtPercentage.length();
                for (int j=0;j<discountSpaceStringLenght;j++){
                    discountAmtPercentage=" "+discountAmtPercentage;
                }
            }


            if (totalCheckOutAmt!=null){
                int amtSpaceStringLenght=8-totalCheckOutAmt.length();
                for (int j=0;j<amtSpaceStringLenght;j++){
                    totalCheckOutAmt=" "+totalCheckOutAmt;
                }
            }


            if (totalTaxAmt!=null){
                int taxSpaceStringLenght=8-totalTaxAmt.length();
                for (int j=0;j<taxSpaceStringLenght;j++){
                    totalTaxAmt=" "+totalTaxAmt;
                }
            }


            String subTotal ="Sub Total:";
            String subTotalName=padRight(subTotal, 24);
            Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(subTotalName + totalCheckOutAmt +"\n" );
            if (discount!=null && !discount.equals("0.00")){
                String discountHeader ="Discount:("+discount+"%): ";
                String discountHeaderName=padRight(discountHeader, 24);
                // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(discountHeaderName + discountAmtPercentage +"\n");

            }
            // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line2inch)+"\n");
            String taxPercentageSplit ="",netBill="";
            Double netBalance =0.00;
            if (saleInoviceData.getSiData().getSelectedItemsList()!=null && saleInoviceData.getSiData().getSelectedItemsList().size()>0){
                int count = saleInoviceData.getSiData().getSelectedItemsList().size();
                Double taxPercentage = saleInoviceData.getSiData().getTotalTaxAmt()/2;
                taxPercentageSplit = String.format("%.2f",taxPercentage);
            }else{
                taxPercentageSplit = "0.00";
            }

            if (totalAmount!=0.00){
                netBalance = totalAmount - saleInoviceData.getSiData().getDiscountAmount();
                netBill =  String.format("%.2f",netBalance);
                if (netBill!=null){
                    int taxPerSpaceStringLenght=8-netBill.length();
                    for (int j=0;j<taxPerSpaceStringLenght;j++){
                        netBill=" "+netBill;
                    }
                }
            }

            if (taxPercentageSplit!=null){
                int taxPerSpaceStringLenght=8-taxPercentageSplit.length();
                for (int j=0;j<taxPerSpaceStringLenght;j++){
                    taxPercentageSplit=" "+taxPercentageSplit;
                }
            }

            //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            String netTotalHeader ="Net Total : ";
            String netTotalHeaderName=padRight(netTotalHeader, 24);
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( netTotalHeaderName+ netBill +"\n");
            if (taxPercentageSplit!=null){
                Double taxPer=0.00;
                if (saleInoviceData.getSiData().getTaxWiseSummaryList()!=null&& saleInoviceData.getSiData().getTaxWiseSummaryList().size()>0){
                    taxPer = saleInoviceData.getSiData().getTaxWiseSummaryList().get(0).getTaxPercent()/2;
                    String cgstHeader ="CGST("+taxPer +"%) :";
                    String cgstHeaderName=padRight(cgstHeader, 24);
                    String sgstHeader ="SGST ("+taxPer +"%):";
                    String sgstHeaderName=padRight(sgstHeader, 24);
                    //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                    Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                    Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( cgstHeaderName+ taxPercentageSplit +"\n");
                    //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                    Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                    Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( sgstHeaderName+ taxPercentageSplit +"\n");



                }

            }
            String roundingOffValue="";
            Double roundval=0.00;
            if (saleInoviceData.getSiData().getRoundingOffValue()!=null && !saleInoviceData.getSiData().getRoundingOffValue().equals("0.00")){
                roundval = Double.valueOf(saleInoviceData.getSiData().getRoundingOffValue());

                if(roundval*1<0){
                    roundingOffValue = String.valueOf(roundval*-1);
                }else{
                    roundingOffValue = String.valueOf((( roundval*-1))/1);
                }


                if (roundingOffValue!=null){
                    int roundingOffValueSpaceStringLenght=8-roundingOffValue.length();
                    for (int j=0;j<roundingOffValueSpaceStringLenght;j++){
                        roundingOffValue=" "+roundingOffValue;
                    }
                }

                if (roundingOffValue!=null){

                    // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                    String roundingOffHeader ="RoundingOffValue: ";
                    String roundingOffHeaderName=padRight(roundingOffHeader, 24);
                    Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                    Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( roundingOffHeaderName + roundingOffValue +"\n");

                }
            }



            //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line2inch)+"\n");


            //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
          /* Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 2);//LEFT
           Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("  Total GST : " + totalTaxAmt +"\n");

        */
            Double grandTotal=0.00;
            if (paidAmount!=null){
                grandTotal = Double.valueOf(paidAmount);
            }
            paidAmount = String.format("%.2f",grandTotal);

            if (paidAmount!=null){
                int paidAmtSpaceStringLenght=8-paidAmount.length();
                for (int j=0;j<paidAmtSpaceStringLenght;j++){
                    paidAmount=" "+paidAmount;
                }
            }
            //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            String grandTotalHeader ="Grand Total : ";
            String grandTotalHeaderName=padRight(grandTotalHeader, 24);
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( grandTotalHeaderName+ paidAmount  +"\n");
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line2inch)+"\n");
            //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Payment Method:" + "\n");
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line2inch)+"\n");
            if (saleInoviceData.getSiData().getCashPayment() !=null && saleInoviceData.getSiData().getCashPayment().getMultiCashPaymentList().size()>0){
               if (saleInoviceData.getSiData().getCashPayment().getPaymentTypeCash()!=null && saleInoviceData.getSiData().getCashPayment().getPaymentTypeCash().equals("Cash")){
                   String cashAmount="";
                   for (MultiCashPaymentList cashPaymentList:saleInoviceData.getSiData().getCashPayment().getMultiCashPaymentList()){
                       cashAmount = String.format("%.2f",cashPaymentList.getCashAmt());

                       if (cashAmount!=null){
                           int paidAmtSpaceStringLenght=8-cashAmount.length();
                           for (int j=0;j<paidAmtSpaceStringLenght;j++){
                               cashAmount=" "+cashAmount;
                           }
                       }

                       String cashAmountHeader = saleInoviceData.getSiData().getCashPayment().getPaymentTypeCash();
                       String cashAmountHeaderName=padRight(cashAmountHeader, 24);
                       Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                       Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( cashAmountHeaderName+ cashAmount  +"\n");

                   }
               }

            }
            if (saleInoviceData.getSiData().getBankPayment() !=null && saleInoviceData.getSiData().getBankPayment().getMultiBankPaymentList().size()>0){
                if (saleInoviceData.getSiData().getBankPayment().getPaymentTypeCash()!=null && !saleInoviceData.getSiData().getBankPayment().getPaymentTypeCash().equals("")){
                  Double totalBank=0.00;
                    String bank="";
                    for (MultiBankPaymentList bankPaymentList:saleInoviceData.getSiData().getBankPayment().getMultiBankPaymentList()){
                        if (bankPaymentList.getAmount()!=0.00 ){
                            totalBank+=bankPaymentList.getAmount();
                        }
                    }
                    bank = String.format("%.2f",totalBank);
                    if (bank!=null){
                        int paidAmtSpaceStringLenght=8-bank.length();
                        for (int j=0;j<paidAmtSpaceStringLenght;j++){
                            bank=" "+bank;
                        }
                    }
                    String bankHeader = saleInoviceData.getSiData().getBankPayment().getPaymentTypeCash();
                    String bankHeaderHeaderName=padRight(bankHeader, 24);
                    Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                    Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(bankHeaderHeaderName + bank +"\n");

                    for (MultiBankPaymentList bankPaymentList:saleInoviceData.getSiData().getBankPayment().getMultiBankPaymentList()){
                        if (bankPaymentList.getBankName()!=null && !bankPaymentList.getBankName().equals("")){
                           String  bankname = "Bank Name ";
                            String banknameHeaderName=padRight(bankname, 15);

                            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( banknameHeaderName +": "+ bankPaymentList.getBankName()  +"\n");

                        }
                        if (bankPaymentList.getDate()!=null && !bankPaymentList.getDate().equals("")){
                            //DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                            //Calendar cal = Calendar.getInstance();
                            //dateFormat.format(cal.getTime())
                            String  bankdate = "Date ";
                            String bankdateHeaderName=padRight(bankdate, 15);
                            String str = bankPaymentList.getDate();

                            try {
                                long timestamp = Long.parseLong(str);
                                Date d = new Date(timestamp);
                               // final TimeZone utc = TimeZone.getTimeZone("UTC");
                                SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy @hh:mm a");
                               // sf.setTimeZone(utc);
                                //String strUTCDate = dateFormatter.format(dt);
                                //SimpleDateFormat sf = new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( bankdateHeaderName+": "+ sf.format(d)  +"\n");

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                        if (bankPaymentList.getAmount()!=0.00 ){
                            String  bankAmount = "Amount ";
                            String bankAmountHeaderName=padRight(bankAmount, 15);
                            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( bankAmountHeaderName+": "+ bankPaymentList.getAmount()  +"\n");

                        }
                    }
                }

            }
            if (saleInoviceData.getSiData().getCreditPayment() !=null && saleInoviceData.getSiData().getCreditPayment().getCardPaymentList().size()>0){
                if (saleInoviceData.getSiData().getCreditPayment().getPaymentTypeCash()!=null && !saleInoviceData.getSiData().getCreditPayment().getPaymentTypeCash().equals("")){

                    Double totalCard=0.00;
                    String card="";
                    for (CardPaymentList cardPaymentList:saleInoviceData.getSiData().getCreditPayment().getCardPaymentList()){
                        if (cardPaymentList.getCardAmt()!=0.00 ){
                            totalCard+=cardPaymentList.getCardAmt();
                        }
                    }
                    card = String.format("%.2f",totalCard);
                    if (card!=null){
                        int paidAmtSpaceStringLenght=8-card.length();
                        for (int j=0;j<paidAmtSpaceStringLenght;j++){
                            card=" "+card;
                        }
                    }
                    String cardHeader = saleInoviceData.getSiData().getCreditPayment().getPaymentTypeCash();
                    String cardHeaderHeaderName=padRight(cardHeader, 24);
                    Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                    Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(cardHeaderHeaderName + card +"\n");

                    for (CardPaymentList cardPaymentList:saleInoviceData.getSiData().getCreditPayment().getCardPaymentList()){
                        if (cardPaymentList.getCardNo()!=null && !cardPaymentList.getCardNo().equals("")){
                            String  cardNo = "Card No ";
                            String cardHeaderName=padRight(cardNo, 15);
                            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( cardHeaderName+": "+ cardPaymentList.getCardNo()  +"\n");

                        }
                        if (cardPaymentList.getCardDate()!=null && !cardPaymentList.getCardDate().equals("")){
                            String  cardDate = "Date ";
                            String cardHeaderName=padRight(cardDate, 15);
                            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss.SSSXXX");
                            try {
                                Date datecard = dateFormat.parse(cardPaymentList.getCardDate());//You will get date object relative to server/client timezone wherever it is parsed
                                DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy @hh:mm a"); //If you need time just put specific format for time like 'HH:mm:ss'
                                String dateStr = formatter.format(datecard);
                                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( cardHeaderName+": "+ dateStr +"\n");

                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                        if (cardPaymentList.getCardAmt()!=0.00){
                            String  cardAmt = "Card Amount ";
                            String cardHeaderName=padRight(cardAmt, 15);
                            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( cardHeaderName+": "+ cardPaymentList.getCardAmt()  +"\n");

                        }

                    }
                }

            }
            if (saleInoviceData.getSiData().getVoucherPayment() !=null && saleInoviceData.getSiData().getVoucherPayment().getMultiVoucherPayments().size()>0){
                if (saleInoviceData.getSiData().getVoucherPayment().getPaymentTypeCash()!=null && saleInoviceData.getSiData().getVoucherPayment().getPaymentTypeCash().equals("Discount Voucher")){
                    if (saleInoviceData.getSiData().getVoucherPayment().getMultiVoucherPayments().get(0).getVoucherNo()!=null && !saleInoviceData.getSiData().getVoucherPayment().getMultiVoucherPayments().get(0).getVoucherNo().equals("Discount Given")){
                     Double totalVoucher=0.00;
                        String voucher="";
                        for (MultiVoucherPayment voucherPayment:saleInoviceData.getSiData().getVoucherPayment().getMultiVoucherPayments()){
                            if (voucherPayment.getVoucherAmt()!=0.00 ){
                                totalVoucher+=voucherPayment.getVoucherAmt();
                            }
                        }
                        voucher = String.format("%.2f",totalVoucher);
                        if (voucher!=null){
                            int paidAmtSpaceStringLenght=8-voucher.length();
                            for (int j=0;j<paidAmtSpaceStringLenght;j++){
                                voucher=" "+voucher;
                            }
                        }
                        String voucherHeader = saleInoviceData.getSiData().getVoucherPayment().getPaymentTypeCash();
                        String voucherHeaderName=padRight(voucherHeader, 24);
                        Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                        Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(voucherHeaderName + voucher +"\n");

                    }
                    for (MultiVoucherPayment voucherPayment:saleInoviceData.getSiData().getVoucherPayment().getMultiVoucherPayments()){
                       // if (voucherPayment.getVoucherNo()!=null && !voucherPayment.getVoucherNo().equals("")){
                            if (voucherPayment.getVoucherNo()!=null && !voucherPayment.getVoucherNo().equals("")){
                                String  voucher = "Voucher No ";
                                String voucherHeaderName=padRight(voucher, 15);
                                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( voucherHeaderName+": "+ voucherPayment.getVoucherNo()  +"\n");

                            }
                            if (voucherPayment.getVoucherAmt()!=0.00){
                                String  voucher = "Voucher Amount ";
                                String voucherHeaderName=padRight(voucher, 15);
                                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( voucherHeaderName+": "+ voucherPayment.getVoucherAmt()  +"\n");

                            }
                      //  }




                    }
                }

            }
            // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_star2inch)  +"\n");
            Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();

            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte)30);
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Thank You...! Visit again" +"\n");
            // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte)30);
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Designed & Powered by Hiaccounts" +"\n");
            Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            Activity_RestraPayment1.BLUETOOTH_PRINTER.PartialCutPaper();


        }
        return true;
    }
    public static boolean  printInvoice2InchCustomer(Context context, RestraCheckoutData saleInoviceData, Bitmap bitmap, String paidAmount, in.hiaccounts.hinext.controlpanel.model.company_setup.compnayinformation.CompanyData companyData, double totalTaxAmount, boolean isNotification) {
        if (Activity_RestraPayment1.BLUETOOTH_PRINTER.IsNoConnection()) {
            return false;
        }

        if (saleInoviceData == null) {
            return false;
        } else {
            String serialNo= "";
            String tokenNo = "";
            String companyName = "";
            String companyTelNumber = "";
            String companyFaxNumber = "";
            String companyGSTNumber = "";
            String companyAddress="";
            String date = "", time = "", invoiceNo = "", invoiceCustomerName = "";
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date dateFo = new Date();
            date = dateFormat.format(dateFo);


            if (companyData != null) {
                if (companyData.getCompanyName() != null) {
                    companyName = companyData.getCompanyName().toUpperCase();
                }
                if (companyData.getAddress() != null)
                    companyAddress = companyData.getAddress();
                if (companyData.getPhone() != null)
                    companyTelNumber = companyData.getPhone();
                if (companyData.getFax() != null)
                    companyFaxNumber = companyData.getFax();
                if (companyData.getRegisterNo() != null)
                    companyGSTNumber = companyData.getRegisterNo();
            }


            //LF = Line feed
            Activity_RestraPayment1.BLUETOOTH_PRINTER.Begin();

            // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
        /*    Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("");
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30); */   //30 * 0.125mm
     /*       if (bitmap!=null) {
                //Activity_RestraPayment1.BLUETOOTH_PRINTER.printByteData(logoByteArray);

                // Activity_RestraPayment1.BLUETOOTH_PRINTER.printImage(Bitmap.createScaledBitmap(bitmap, 200, 200, false));
                Bitmap newBm = BitmapConvertUtil.decodeSampledBitmapFromBitmap(bitmap, 100);
                byte xL = (byte) (((newBm.getWidth() - 1) / 8 + 1) % 256);
                byte xH = (byte) (((newBm.getWidth() - 1) / 8 + 1) / 256);
                byte yL = (byte) (newBm.getHeight() % 256);
                byte yH = (byte) (newBm.getHeight() / 256);
                byte[] pixels = BitmapConvertUtil.convert(newBm);

                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_RestraPayment1.BLUETOOTH_PRINTER.printImage(Bitmap.createScaledBitmap(bitmap, 150, 150, false));

                *//*Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(new byte[]{29, 118, 118, 10, xL, xH, yL, yH});

                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//CENTER
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(pixels);*//*
            }*/


            if (companyName!=null && !companyName.equals("")) {
                Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x11);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(companyName +"\n");
                //  Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            }


            if (companyAddress!=null && !companyAddress.equals("")) {
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( companyAddress +"\n");
                // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            }

            if (companyTelNumber!=null && !companyTelNumber.equals("")) {
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Phone : " + companyTelNumber +"\n");
                // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            }

            if (companyGSTNumber!=null && !companyGSTNumber.equals("")) {
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("GSTIN: " + companyGSTNumber +"\n");
                // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            }


            if (date!=null && !date.equals("")) {
                //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("DATE: " + date +"\n");
                //  Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            }
            //if (invoiceNo!=null && !invoiceNo.equals("")) {
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("InvoiceNumber:* Customer Bill *"+"\n");
            //  Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            //}
            if (saleInoviceData.getCustomerName()!=null && !saleInoviceData.getCustomerName().equals("")){
                //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Customer: "+saleInoviceData.getCustomerName() +"\n");
            }
            if (saleInoviceData.getCustomerNo()!=null && !saleInoviceData.getCustomerNo().equals("")){
                //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Phone: "+saleInoviceData.getCustomerNo() +"\n");
            }
            if (saleInoviceData.getCustomerAddress()!=null && !saleInoviceData.getCustomerAddress().equals("")){
                //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Address: "+saleInoviceData.getCustomerAddress() +"\n");
            }
            //String waiterName="NA";
            if (saleInoviceData.getWaiterName()!=null && !saleInoviceData.getWaiterName().equals("") && !isNotification){
                // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Steward: "+saleInoviceData.getWaiterName()+ "\n");
            }

            if (saleInoviceData.getTableName()!=null && !saleInoviceData.getTableName().equals("")){
                //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Table No: "+saleInoviceData.getTableName() +"\n");
            }

            Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line2inch)+ "\n");

            double amtExTax = 0,amtIncTax= 0,totalAmount=0.00, taxPer=0.00,gstTaxPercantage=0.00;
            if (saleInoviceData != null) {
                String  itemName = "";
                String itemUnitPrice = "";
                String taxPercentage = "";
                String itemQty = "";
                String itemAmtInclTax = "";
                String itemTax = "";
                String qtyHeader = "Qty";
                int qtyHeaderStringLenght = 4-qtyHeader.length();
                for (int j=0;j<qtyHeaderStringLenght;j++){
                    qtyHeader=" "+qtyHeader;
                }
                String priceHeader = "Price";
                int priceSpaceStringLenght = 8-priceHeader.length();
                for (int j=0;j<priceSpaceStringLenght;j++){
                    priceHeader=" "+priceHeader;
                }

                String amountHeader = "Amount";
                int amountSpaceStringLenght = 8-amountHeader.length();
                for (int j=0;j<amountSpaceStringLenght;j++){
                    amountHeader=" "+amountHeader;
                }
                String itemHeader = "Item";
              /* int itemHeaderStringLenght = itemHeader.length();
               for (int j=0;j<itemHeaderStringLenght;j++){
                   itemHeader=itemHeader+" ";
               }*/
                String itemheader=padRight(itemHeader, 8);
                if (saleInoviceData.getSelectedItemsList() != null) {
                    // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                    Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                    Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);
                    Activity_RestraPayment1.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);
                    Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(itemheader+" "+priceHeader+" "+qtyHeader+" "+amountHeader+"\n");
                    // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                    //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                    Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//CENTER
                    Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line2inch)+ "\n");


                    for (int i = 0; i < saleInoviceData.getSelectedItemsList().size(); i++) {
                        RestraCheckoutItem item = saleInoviceData.getSelectedItemsList().get(i);
                        if (item != null) {
                            itemUnitPrice = String.format("%.2f", item.getUnitPrice());
                            int amtSpaceStringLenght = 8-itemUnitPrice.length();
                            for (int j=0;j<amtSpaceStringLenght;j++){
                                itemUnitPrice=" "+itemUnitPrice;
                            }

                            if (item.getItemName() != null)
                                itemName = item.getItemName();

                            int itemNameSpaceStringLenght = 0;
                            for (int j=0;j<itemNameSpaceStringLenght;j++){
                                itemName=itemName+" ";
                            }
                            //taxPer = Double.parseDouble(String.format("%.2f", item.getGstItemTax()));
                            gstTaxPercantage = item.getGstTaxPercantage();
                            totalAmount +=item.getQty()*item.getUnitPrice();
                            itemAmtInclTax = String.format("%.2f", (item.getQty()*item.getUnitPrice()));
                            //itemAmtInclTax = String.format("%.2f", (item.getAmtinclusivetax()));

                            int amtSpaceStringLenght1=8-itemAmtInclTax.length();
                            for (int j=0;j<amtSpaceStringLenght1;j++){
                                itemAmtInclTax=" "+itemAmtInclTax;
                            }


                            itemQty = "" + item.getQty();
                            int amtSpaceStringLenght2 = 4-itemQty.length();
                            for (int j=0;j<amtSpaceStringLenght2;j++){
                                itemQty=""+itemQty;
                            }
                            amtExTax=amtExTax+(item.getQty()*item.getUnitPrice());
                            String priceVal = String.format("%.2f",item.getUnitPrice());

                            int amtStringLenght2 = 8-priceVal.length();
                            for (int j=0;j<amtStringLenght2;j++){
                                priceVal=priceVal+" ";
                            }

                            String itemname="";
                            if (itemName.length()>11){
                                itemname = customerBreakLines(itemName,11,priceVal,itemQty,itemAmtInclTax);
                                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//CENTER
                                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( itemname +  "\n");
                            }else{
                                itemname=padRight(itemName, 11);
                                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//CENTER
                                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(itemname+" "+priceVal+" "+itemQty+" "+itemAmtInclTax + "\n");
                            }


                        }


                    }
                }
            }
            //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line2inch));

            String totalCheckOutAmt=String.format("%.2f",totalAmount);

            String discountAmtPercentage="";

            if (saleInoviceData.getDiscountAmount()!=null && !saleInoviceData.getDiscountAmount().equals("")){
                discountAmtPercentage = String.format("%.2f",saleInoviceData.getDiscountAmount());
                if (discountAmtPercentage!=null){
                    int discountSpaceStringLenght=8-discountAmtPercentage.length();
                    for (int j=0;j<discountSpaceStringLenght;j++){
                        discountAmtPercentage=" "+discountAmtPercentage;
                    }
                }


            }else {
                discountAmtPercentage="0.00";
            }


            String discount="";

            if (saleInoviceData.getDiscountAmtInPercentage()!=0.0){
                discount=String.format("%.2f",saleInoviceData.getDiscountAmtInPercentage());

            }else{
                discount=String.format("%.2f",0.0d);
            }
            if (discountAmtPercentage!=null){
                int discountSpaceStringLenght=8-discountAmtPercentage.length();
                for (int j=0;j<discountSpaceStringLenght;j++){
                    discountAmtPercentage=" "+discountAmtPercentage;
                }
            }


            if (totalCheckOutAmt!=null){
                int amtSpaceStringLenght=8-totalCheckOutAmt.length();
                for (int j=0;j<amtSpaceStringLenght;j++){
                    totalCheckOutAmt=" "+totalCheckOutAmt;
                }
            }




            String subTotal ="Sub Total:";
            String subTotalName=padRight(subTotal, 24);
            Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(subTotalName + totalCheckOutAmt +"\n" );
            if (discount!=null && !discount.equals("0.00")){
                String discountHeader ="Discount:("+discount+"%): ";
                String discountHeaderName=padRight(discountHeader, 24);
                // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(discountHeaderName + discountAmtPercentage +"\n");

            }
            // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line2inch)+"\n");
            String taxPercentageSplit ="",netBill="";
            Double netBalance =0.00;

            String totalTaxAmt="";
            if (totalTaxAmount!=0.00){
                 totalTaxAmt=String.format("%.2f",(totalTaxAmount/2));
            }else{
                totalTaxAmt ="0.00";
            }



            if (totalTaxAmt!=null){
                int taxSpaceStringLenght=8-totalTaxAmt.length();
                for (int j=0;j<taxSpaceStringLenght;j++){
                    totalTaxAmt=" "+totalTaxAmt;
                }
            }


            if (totalAmount!=0.00){
                netBalance = totalAmount - saleInoviceData.getDiscountAmount();
                netBill =  String.format("%.2f",netBalance);
                if (netBill!=null){
                    int taxPerSpaceStringLenght=8-netBill.length();
                    for (int j=0;j<taxPerSpaceStringLenght;j++){
                        netBill=" "+netBill;
                    }
                }
            }



            //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            String netTotalHeader ="Net Total : ";
            String netTotalHeaderName=padRight(netTotalHeader, 24);
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( netTotalHeaderName+ netBill +"\n");
            if (totalTaxAmt!=null){
                Double gstTaxPer=0.00;
                if (saleInoviceData!=null){
                    gstTaxPer = gstTaxPercantage/2;
                    String cgstHeader ="CGST("+gstTaxPer +"%) :";
                    String cgstHeaderName=padRight(cgstHeader, 24);
                    String sgstHeader ="SGST ("+gstTaxPer +"%):";
                    String sgstHeaderName=padRight(sgstHeader, 24);

                    if (netBalance!=0.00){
                        //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                        Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                        Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( cgstHeaderName+ totalTaxAmt +"\n");
                        //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                        Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                        Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( sgstHeaderName+ totalTaxAmt +"\n");

                    }else{
                        //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                        Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                        Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( cgstHeaderName+ 0.00 +"\n");
                        //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                        Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                        Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( sgstHeaderName+ 0.00 +"\n");

                    }



                }

            }
            String roundingOffValue="";
            Double roundval=0.00;
            if (saleInoviceData.getRoundingOffValue()!=null && !saleInoviceData.getRoundingOffValue().equals("0.00")){
                roundval = Double.valueOf(saleInoviceData.getRoundingOffValue());

                if(roundval*1<0){
                    roundingOffValue = String.valueOf(roundval*-1);
                }else{
                    roundingOffValue = String.valueOf((( roundval*-1))/1);
                }


                if (roundingOffValue!=null){
                    int roundingOffValueSpaceStringLenght=8-roundingOffValue.length();
                    for (int j=0;j<roundingOffValueSpaceStringLenght;j++){
                        roundingOffValue=" "+roundingOffValue;
                    }
                }

                if (roundingOffValue!=null){

                    // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                    String roundingOffHeader ="RoundingOffValue: ";
                    String roundingOffHeaderName=padRight(roundingOffHeader, 24);
                    Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                    Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( roundingOffHeaderName + roundingOffValue +"\n");

                }
            }


            //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line2inch)+"\n");


            //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
          /* Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 2);//LEFT
           Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("  Total GST : " + totalTaxAmt +"\n");

        */
            Double grandTotal=0.00;
            if (paidAmount!=null){
                grandTotal = Double.valueOf(paidAmount);
            }
            paidAmount = String.format("%.2f",grandTotal);

            if (paidAmount!=null){
                int paidAmtSpaceStringLenght=8-paidAmount.length();
                for (int j=0;j<paidAmtSpaceStringLenght;j++){
                    paidAmount=" "+paidAmount;
                }
            }
            //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            String grandTotalHeader ="Grand Total : ";
            String grandTotalHeaderName=padRight(grandTotalHeader, 24);
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write( grandTotalHeaderName+ paidAmount  +"\n");

            // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_star2inch)  +"\n");
            Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();

            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte)30);
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Thank You...! Visit again" +"\n");
            // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
            Activity_RestraPayment1.BLUETOOTH_PRINTER.SetLineSpacing((byte)30);
            Activity_RestraPayment1.BLUETOOTH_PRINTER.BT_Write("Designed & Powered by Hiaccounts" +"\n");
            Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            Activity_RestraPayment1.BLUETOOTH_PRINTER.PartialCutPaper();


            //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
            Activity_RestraPayment1.BLUETOOTH_PRINTER.CutPaper();

        }
        return true;

    }

    private static String splittName(String remainString, String priceVal, String itemQty, String itemAmtInclTax) {
        int wrapLength = 11;
        String wrapString = new String();

        //String remainString = "The quick brown fox jumps over the lazy dog The quick brown fox jumps over the lazy dog";
        boolean flag =true;
        while(remainString.length()>wrapLength){
            int lastIndex = remainString.lastIndexOf(" ", wrapLength);
            wrapString = wrapString.concat(remainString.substring(0, lastIndex));
            if (flag){
                wrapString = wrapString.concat("    "+priceVal+" "+itemQty+" "+itemAmtInclTax+"\n");
                flag = false;
            }else{
                wrapString = wrapString.concat("\n");
            }
            remainString = remainString.substring(lastIndex+1, remainString.length());
        }
        return wrapString;
   }


    public static boolean printInvoicePrintlist3Inch(Context context, RestraSaveCheckoutData saleInoviceData,Bitmap bitmap) {

        if (Activity_PrintList.BLUETOOTH_PRINTER.IsNoConnection()) {
            return false;
        }

        if (saleInoviceData == null) {
            return false;
        } else {
            String serialNo= "";
            String tokenNo = "";
            String companyName = "";
            String companyTelNumber = "";
            String companyFaxNumber = "";
            String companyGSTNumber = "";
            String companyAddress="";
            String date = "", time = "", invoiceNo = "", invoiceCustomerName = "";

            if (saleInoviceData.getDate() != null) {
                date = saleInoviceData.getRestaurantDate();
            }
           /* if (saleInoviceData.getTime() != null) {
                time = saleInoviceData.getTime();
            }*/
            if (saleInoviceData.getSiData() != null) {
                if (saleInoviceData.getSiData().getSiNo() != null)
                    invoiceNo = saleInoviceData.getSiData().getSiNo();
                if (saleInoviceData.getSiData().getCutomerName() != null){}
                //invoiceCustomerName = saleInoviceData.getSiData().getUserName();
                if (saleInoviceData.getSiData().getTokenNo()!=null){

                    tokenNo=""+saleInoviceData.getSiData().getTokenNo();
                }
            }


            in.hiaccounts.hinext.restaurant.model.checkout.CompanyData companyData = saleInoviceData.getCompanyData();
            if (companyData != null) {
                if (saleInoviceData.getCompanyData().getCompanyName() != null) {
                    companyName = saleInoviceData.getCompanyData().getCompanyName().toUpperCase();
                }
                if (companyData.getAddress() != null)
                    companyAddress = companyData.getAddress();
                if (companyData.getPhone() != null)
                    companyTelNumber = companyData.getPhone();
                if (companyData.getFax() != null)
                    companyFaxNumber = companyData.getFax();
                if (companyData.getRegisterNo() != null)
                    companyGSTNumber = companyData.getRegisterNo();
            }


            //LF = Line feed
            Activity_PrintList.BLUETOOTH_PRINTER.Begin();
            Activity_PrintList.BLUETOOTH_PRINTER.LF();
            // Activity_PrintList.BLUETOOTH_PRINTER.LF();
        /*    Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("");
            Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30); */   //30 * 0.125mm
    /*        if (bitmap!=null) {
                //Activity_PrintList.BLUETOOTH_PRINTER.printByteData(logoByteArray);

                // Activity_PrintList.BLUETOOTH_PRINTER.printImage(Bitmap.createScaledBitmap(bitmap, 200, 200, false));
                Bitmap newBm = BitmapConvertUtil.decodeSampledBitmapFromBitmap(bitmap, 100);
                byte xL = (byte) (((newBm.getWidth() - 1) / 8 + 1) % 256);
                byte xH = (byte) (((newBm.getWidth() - 1) / 8 + 1) / 256);
                byte yL = (byte) (newBm.getHeight() % 256);
                byte yH = (byte) (newBm.getHeight() / 256);
                byte[] pixels = BitmapConvertUtil.convert(newBm);

                Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_PrintList.BLUETOOTH_PRINTER.printImage(Bitmap.createScaledBitmap(bitmap, 150, 150, false));

                *//*Activity_PrintList.BLUETOOTH_PRINTER.BT_Write(new byte[]{29, 118, 118, 10, xL, xH, yL, yH});

                Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//CENTER
                Activity_PrintList.BLUETOOTH_PRINTER.BT_Write(pixels);*//*
            }*/


            if (companyName!=null && !companyName.equals("")) {
                Activity_PrintList.BLUETOOTH_PRINTER.LF();
                Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_PrintList.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x11);//normal
                Activity_PrintList.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
                Activity_PrintList.BLUETOOTH_PRINTER.BT_Write(companyName +"\n");
                //  Activity_PrintList.BLUETOOTH_PRINTER.LF();
            }


            if (companyAddress!=null && !companyAddress.equals("")) {
                Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_PrintList.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_PrintList.BLUETOOTH_PRINTER.BT_Write( companyAddress +"\n");
                // Activity_PrintList.BLUETOOTH_PRINTER.LF();
            }

            if (companyTelNumber!=null && !companyTelNumber.equals("")) {
                Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_PrintList.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("Phone : " + companyTelNumber +"\n");
                // Activity_PrintList.BLUETOOTH_PRINTER.LF();
            }

            if (companyGSTNumber!=null && !companyGSTNumber.equals("")) {
                Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_PrintList.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("GSTIN: " + companyGSTNumber +"\n");
                // Activity_PrintList.BLUETOOTH_PRINTER.LF();
            }
            if (date!=null && !date.equals("")) {
                // Activity_PrintList.BLUETOOTH_PRINTER.LF();
                Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_PrintList.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("DATE: " + date +"\n");
                //  Activity_PrintList.BLUETOOTH_PRINTER.LF();
            }
            if (invoiceNo!=null && !invoiceNo.equals("")) {
                Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_PrintList.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("Invoice No: " + invoiceNo +"\n");
                //  Activity_PrintList.BLUETOOTH_PRINTER.LF();
            }
            // String customerName="customerA";
            if (saleInoviceData.getSiData().getCutomerName()!=null && !saleInoviceData.getSiData().getCutomerName().equals("")){
                //Activity_PrintList.BLUETOOTH_PRINTER.LF();
                Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_PrintList.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_PrintList.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
                Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("Customer: "+saleInoviceData.getSiData().getCutomerName() +"\n");
            }
            if (saleInoviceData.getSiData().getCustomerNo()!=null && !saleInoviceData.getSiData().getCustomerNo().equals("")){
                //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_PrintList.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_PrintList.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
                Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("Phone: "+saleInoviceData.getSiData().getCustomerNo() +"\n");
            }
            if (saleInoviceData.getSiData().getCustomerAddress()!=null && !saleInoviceData.getSiData().getCustomerAddress().equals("")){
                //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_PrintList.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_PrintList.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
                Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("Address: "+saleInoviceData.getSiData().getCustomerAddress() +"\n");
            }
            //String waiterName="NA";
            if (saleInoviceData.getSiData().getEmployeeName()!=null && !saleInoviceData.getSiData().getEmployeeName().equals("")){
                // Activity_PrintList.BLUETOOTH_PRINTER.LF();
                Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_PrintList.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("Steward: "+saleInoviceData.getSiData().getEmployeeName()+ "\n");
            }

            if (saleInoviceData.getSiData().getTable()!=null && !saleInoviceData.getSiData().getTable().equals("")){
                //Activity_PrintList.BLUETOOTH_PRINTER.LF();
                Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_PrintList.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_PrintList.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
                Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("Table No: "+saleInoviceData.getSiData().getTable() +"\n");
            }

            if (saleInoviceData.getSiData().getEmail()!=null && !saleInoviceData.getSiData().getEmail().equals("")){
                // Activity_PrintList.BLUETOOTH_PRINTER.LF();
                Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_PrintList.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_PrintList.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
                Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("Token: "+saleInoviceData.getSiData().getEmail() +"\n");
            }
            //Activity_PrintList.BLUETOOTH_PRINTER.LF();
            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_PrintList.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line)+ "\n");

            double amtExTax = 0,amtIncTax= 0,totalAmount=0.00;
            if (saleInoviceData.getSiData() != null) {
                String  itemName = "";
                String itemUnitPrice = "";
                String taxPercentage = "";
                String itemQty = "";
                String itemAmtInclTax = "";
                String itemTax = "";
                String qtyHeader = "Qty";
                int qtyHeaderStringLenght = 4-qtyHeader.length();
                for (int j=0;j<qtyHeaderStringLenght;j++){
                    qtyHeader=" "+qtyHeader;
                }
                String priceHeader = "Price";
                int priceSpaceStringLenght = 8-priceHeader.length();
                for (int j=0;j<priceSpaceStringLenght;j++){
                    priceHeader=" "+priceHeader;
                }

                String amountHeader = "Amount";
                int amountSpaceStringLenght = 8-amountHeader.length();
                for (int j=0;j<amountSpaceStringLenght;j++){
                    amountHeader=" "+amountHeader;
                }
                String itemHeader = "Item Details";
              /* int itemHeaderStringLenght = itemHeader.length();
               for (int j=0;j<itemHeaderStringLenght;j++){
                   itemHeader=itemHeader+" ";
               }*/
                String itemheader=padRight(itemHeader, 23);
                if (saleInoviceData.getSiData().getSelectedItemsList() != null) {

                    // Activity_PrintList.BLUETOOTH_PRINTER.LF();
                    Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 2);//LEFT
                    Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);
                    Activity_PrintList.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);
                    Activity_PrintList.BLUETOOTH_PRINTER.BT_Write(itemheader+"  " +priceHeader+" "+qtyHeader+"  "+amountHeader+"\n");
                    // Activity_PrintList.BLUETOOTH_PRINTER.LF();
                    //Activity_PrintList.BLUETOOTH_PRINTER.LF();
                    Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//CENTER
                    Activity_PrintList.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line)+ "\n");


                    for (int i = 0; i < saleInoviceData.getSiData().getSelectedItemsList().size(); i++) {
                        RestraCheckoutItem item = saleInoviceData.getSiData().getSelectedItemsList().get(i);
                        if (item != null) {


                            itemUnitPrice = String.format("%.2f", item.getUnitPrice());

                            int amtSpaceStringLenght = 8-itemUnitPrice.length();
                            for (int j=0;j<amtSpaceStringLenght;j++){
                                itemUnitPrice=" "+itemUnitPrice;
                            }

                            if (item.getItemName() != null)
                                itemName = item.getItemName();

                            int itemNameSpaceStringLenght = 0;
                            for (int j=0;j<itemNameSpaceStringLenght;j++){
                                itemName=itemName+" ";
                            }

                            //taxPercentage = String.format("%.2f", item.getGstItemTax());
                            totalAmount +=item.getQty()*item.getUnitPrice();
                            itemAmtInclTax = String.format("%.2f", (item.getQty()*item.getUnitPrice()));
                            //itemAmtInclTax = String.format("%.2f", (item.getAmtinclusivetax()));

                            int amtSpaceStringLenght1=8-itemAmtInclTax.length();
                            for (int j=0;j<amtSpaceStringLenght1;j++){
                                itemAmtInclTax=" "+itemAmtInclTax;
                            }


                            itemQty = "" + item.getQty();
                            int amtSpaceStringLenght2 = 4-itemQty.length();
                            for (int j=0;j<amtSpaceStringLenght2;j++){
                                itemQty=itemQty+" ";
                            }
                            amtExTax=amtExTax+(item.getQty()*item.getUnitPrice());

                            Double price = (item.getAmtinclusivetax())/(item.getQty());

                            String priceVal = String.format("%.2f",item.getUnitPrice());


                            //Double amount = item.getUnitPrice()*item.getQty();
                            // String amountTot = String.format("%.2f",amount);



                            int amtStringLenght2 = 4-priceVal.length();
                            for (int j=0;j<amtStringLenght2;j++){
                                priceVal=" "+priceVal;
                            }

                            String itemname=padRight(itemName, 26);

                         /*   Activity_PrintList.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal font
                            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                            Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write( itemName +"\n");*/

                            //Activity_PrintList.BLUETOOTH_PRINTER.LF();
                            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                            Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write(itemname+" " + priceVal +"  " + itemQty + "  " + itemAmtInclTax + "\n");



                        }


                    }
                }
            }
            //Activity_PrintList.BLUETOOTH_PRINTER.LF();
            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line));
            String discount="";

            if (saleInoviceData.getSiData().getDiscountAmtInPercentage()!=0.0){
                discount=String.format("%.2f",saleInoviceData.getSiData().getDiscountAmtInPercentage());

            }else{
                discount=String.format("%.2f",0.0d);
            }
            String totalCheckOutAmt=String.format("%.2f",totalAmount);
            String totalTaxAmt=String.format("%.2f",saleInoviceData.getSiData().getTotalTaxAmt());
            String paidAmount = String.format("%.2f",saleInoviceData.getSiData().getTotalCheckOutamt());
            String discountAmtPercentage="";
            discountAmtPercentage = String.format("%.2f",(totalAmount*saleInoviceData.getSiData().getDiscountAmtInPercentage())/100);
            if (discountAmtPercentage!=null){
                int discountSpaceStringLenght=8-discountAmtPercentage.length();
                for (int j=0;j<discountSpaceStringLenght;j++){
                    discountAmtPercentage=" "+discountAmtPercentage;
                }
            }else {
                discountAmtPercentage = String.format("%.2f",saleInoviceData.getSiData().getDiscountAmtInPercentage());
                int discountSpaceStringLenght=8-discountAmtPercentage.length();
                for (int j=0;j<discountSpaceStringLenght;j++){
                    discountAmtPercentage=" "+discountAmtPercentage;
                }
            }
        /*    if (saleInoviceData.getSiData().getDiscountAmtInPercentage()!=null && !saleInoviceData.getSiData().getDiscountAmtInPercentage().equals("")){
                discountAmtPercentage = String.valueOf(saleInoviceData.getSiData().getDiscountAmtInPercentage());
                if (discountAmtPercentage!=null){
                    int discountSpaceStringLenght=8-discountAmtPercentage.length();
                    for (int j=0;j<discountSpaceStringLenght;j++){
                        discountAmtPercentage=" "+discountAmtPercentage;
                    }
                }


            }else {
                discountAmtPercentage="0.00";
            }*/



            if (discountAmtPercentage!=null){
                int discountSpaceStringLenght=8-discountAmtPercentage.length();
                for (int j=0;j<discountSpaceStringLenght;j++){
                    discountAmtPercentage=" "+discountAmtPercentage;
                }
            }


            if (totalCheckOutAmt!=null){
                int amtSpaceStringLenght=8-totalCheckOutAmt.length();
                for (int j=0;j<amtSpaceStringLenght;j++){
                    totalCheckOutAmt=" "+totalCheckOutAmt;
                }
            }


            if (totalTaxAmt!=null){
                int taxSpaceStringLenght=8-totalTaxAmt.length();
                for (int j=0;j<taxSpaceStringLenght;j++){
                    totalTaxAmt=" "+totalTaxAmt;
                }
            }


            String subTotal ="Sub Total:";
            String subTotalName=padRight(subTotal, 40);
            Activity_PrintList.BLUETOOTH_PRINTER.LF();
            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write(subTotalName + totalCheckOutAmt +"\n" );
            if (discount!=null && !discount.equals("0.00")){
                String discountHeader ="Discount:("+discount+"%): ";
                String discountHeaderName=padRight(discountHeader, 40);
                // Activity_PrintList.BLUETOOTH_PRINTER.LF();
                Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                Activity_PrintList.BLUETOOTH_PRINTER.BT_Write(discountHeaderName + discountAmtPercentage +"\n");

            }
            // Activity_PrintList.BLUETOOTH_PRINTER.LF();
            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line)+"\n");
            String taxPercentageSplit ="",netBill="";
            Double netBalance =0.00;
            if (saleInoviceData.getSiData().getSelectedItemsList()!=null && saleInoviceData.getSiData().getSelectedItemsList().size()>0){
                int count = saleInoviceData.getSiData().getSelectedItemsList().size();
                Double taxPercentage = saleInoviceData.getSiData().getTotalTaxAmt()/2;
                taxPercentageSplit = String.format("%.2f",taxPercentage);
            }else{
                taxPercentageSplit = "0.00";
            }

            if (totalAmount!=0.00){
                netBalance = totalAmount - Double.valueOf(discountAmtPercentage);
                netBill =  String.format("%.2f",netBalance);
                if (netBill!=null){
                    int taxPerSpaceStringLenght=8-netBill.length();
                    for (int j=0;j<taxPerSpaceStringLenght;j++){
                        netBill=" "+netBill;
                    }
                }
            }

            if (taxPercentageSplit!=null){
                int taxPerSpaceStringLenght=8-taxPercentageSplit.length();
                for (int j=0;j<taxPerSpaceStringLenght;j++){
                    taxPercentageSplit=" "+taxPercentageSplit;
                }
            }

            //Activity_PrintList.BLUETOOTH_PRINTER.LF();
            String netTotalHeader ="Net Total : ";
            String netTotalHeaderName=padRight(netTotalHeader, 40);
            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write( netTotalHeaderName+ netBill +"\n");
            if (taxPercentageSplit!=null){
                Double taxPer=0.00;
                if (saleInoviceData.getSiData().getTaxWiseSummaryList()!=null&& saleInoviceData.getSiData().getTaxWiseSummaryList().size()>0){
                    taxPer = saleInoviceData.getSiData().getTaxWiseSummaryList().get(0).getTaxPercent()/2;
                    String cgstHeader ="CGST("+taxPer +"%) :";
                    String cgstHeaderName=padRight(cgstHeader, 40);
                    String sgstHeader ="SGST ("+taxPer +"%):";
                    String sgstHeaderName=padRight(sgstHeader, 40);
                    //Activity_PrintList.BLUETOOTH_PRINTER.LF();
                    Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                    Activity_PrintList.BLUETOOTH_PRINTER.BT_Write( cgstHeaderName+ taxPercentageSplit +"\n");
                    //Activity_PrintList.BLUETOOTH_PRINTER.LF();
                    Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                    Activity_PrintList.BLUETOOTH_PRINTER.BT_Write( sgstHeaderName+ taxPercentageSplit +"\n");



                }

            }
            String roundingOffValue="";
            Double roundval=0.00;
            if (saleInoviceData.getSiData().getRoundingOffValue()!=null && !saleInoviceData.getSiData().getRoundingOffValue().equals("0.0")){
                roundval = Double.valueOf(saleInoviceData.getSiData().getRoundingOffValue());

                if(roundval*1<0){
                    roundingOffValue = String.valueOf(roundval*-1);
                }else{
                    roundingOffValue = String.valueOf((( roundval*-1))/1);
                }


                if (roundingOffValue!=null){
                    int roundingOffValueSpaceStringLenght=8-roundingOffValue.length();
                    for (int j=0;j<roundingOffValueSpaceStringLenght;j++){
                        roundingOffValue=" "+roundingOffValue;
                    }
                }

                if (roundingOffValue!=null){

                    // Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                    String roundingOffHeader ="RoundingOffValue: ";
                    String roundingOffHeaderName=padRight(roundingOffHeader, 40);
                    Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                    Activity_PrintList.BLUETOOTH_PRINTER.BT_Write( roundingOffHeaderName + roundingOffValue +"\n");

                }
            }



            //Activity_PrintList.BLUETOOTH_PRINTER.LF();
            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line)+"\n");


            //Activity_PrintList.BLUETOOTH_PRINTER.LF();
          /* Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 2);//LEFT
           Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("  Total GST : " + totalTaxAmt +"\n");

        */
            Double grandTotal=0.00;
           /* if (paidAmount!=null){
                grandTotal = Double.valueOf(paidAmount);
            }*/


            if (paidAmount!=null){
                int paidAmtSpaceStringLenght=8-paidAmount.length();
                for (int j=0;j<paidAmtSpaceStringLenght;j++){
                    paidAmount=" "+paidAmount;
                }
            }
            //Activity_PrintList.BLUETOOTH_PRINTER.LF();
            String grandTotalHeader ="Grand Total : ";
            String grandTotalHeaderName=padRight(grandTotalHeader, 40);
            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write( grandTotalHeaderName+ paidAmount  +"\n");
            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line)+"\n");
            //Activity_PrintList.BLUETOOTH_PRINTER.LF();
            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("Payment Method:" + "\n");
            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line)+"\n");

            if (saleInoviceData.getSiData().getCashPayment() !=null && saleInoviceData.getSiData().getCashPayment().getTotalCPAmountTendered()!=0.00){
                String cashAmount="";
                if (saleInoviceData.getSiData().getCashPayment().getTotalCPAmountTendered()!=0.00 ){
                    cashAmount = String.format("%.2f",saleInoviceData.getSiData().getCashPayment().getTotalCPAmountTendered());

                    if (cashAmount!=null){
                        int paidAmtSpaceStringLenght=8-cashAmount.length();
                        for (int j=0;j<paidAmtSpaceStringLenght;j++){
                            cashAmount=" "+cashAmount;
                        }
                    }

                    String cashAmountHeader = "Cash :";
                    String cashAmountHeaderName=padRight(cashAmountHeader, 40);
                    Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                    Activity_PrintList.BLUETOOTH_PRINTER.BT_Write( cashAmountHeaderName+ cashAmount  +"\n");


                }
            }
            if (saleInoviceData.getSiData().getBankPayment() !=null && saleInoviceData.getSiData().getBankPayment().getMultiBankPaymentList().size()>0){
                if (saleInoviceData.getSiData().getBankPayment().getPaymentTypeCash()!=null && !saleInoviceData.getSiData().getBankPayment().getPaymentTypeCash().equals("")){
                    Double totalBank=0.00;
                    String bank="";
                    for (MultiBankPaymentList bankPaymentList:saleInoviceData.getSiData().getBankPayment().getMultiBankPaymentList()){
                        if (bankPaymentList.getAmount()!=0.00 ){
                            totalBank+=bankPaymentList.getAmount();
                        }
                    }
                    bank = String.format("%.2f",totalBank);
                    if (bank!=null){
                        int paidAmtSpaceStringLenght=8-bank.length();
                        for (int j=0;j<paidAmtSpaceStringLenght;j++){
                            bank=" "+bank;
                        }
                    }
                    String bankHeader = saleInoviceData.getSiData().getBankPayment().getPaymentTypeCash();
                    String bankHeaderHeaderName=padRight(bankHeader, 40);
                    Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                    Activity_PrintList.BLUETOOTH_PRINTER.BT_Write(bankHeaderHeaderName + bank +"\n");

                    for (MultiBankPaymentList bankPaymentList:saleInoviceData.getSiData().getBankPayment().getMultiBankPaymentList()){
                        if (bankPaymentList.getBankName()!=null && !bankPaymentList.getBankName().equals("")){
                            String  bankname = "Bank Name ";
                            String banknameHeaderName=padRight(bankname, 15);

                            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write( banknameHeaderName +": "+ bankPaymentList.getBankName()  +"\n");

                        }
                        if (bankPaymentList.getDate()!=null && !bankPaymentList.getDate().equals("")){
                            //DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                            //Calendar cal = Calendar.getInstance();
                            //dateFormat.format(cal.getTime())
                            String  bankdate = "Date ";
                            String bankdateHeaderName=padRight(bankdate, 15);
                            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT

                            String str = bankPaymentList.getDate();

                            try {
                                long timestamp = Long.parseLong(str);
                                Date d = new Date(timestamp);
                                //final TimeZone utc = TimeZone.getTimeZone("UTC");
                                SimpleDateFormat sf = new SimpleDateFormat("dd-MMM-yyyy @hh:mm a");
                               // sf.setTimeZone(utc);
                                Activity_PrintList.BLUETOOTH_PRINTER.BT_Write( bankdateHeaderName+": "+ sf.format(d)  +"\n");

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        if (bankPaymentList.getAmount()!=0.00 ){
                            String  bankAmount = "Amount ";
                            String bankAmountHeaderName=padRight(bankAmount, 15);
                            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write( bankAmountHeaderName+": "+ bankPaymentList.getAmount()  +"\n");

                        }
                    }
                }

            }
            if (saleInoviceData.getSiData().getCreditPayment() !=null && saleInoviceData.getSiData().getCreditPayment().getCardPaymentList().size()>0){
                if (saleInoviceData.getSiData().getCreditPayment().getPaymentTypeCash()!=null && !saleInoviceData.getSiData().getCreditPayment().getPaymentTypeCash().equals("")){

                    Double totalCard=0.00;
                    String card="";
                    for (CardPaymentList cardPaymentList:saleInoviceData.getSiData().getCreditPayment().getCardPaymentList()){
                        if (cardPaymentList.getCardAmt()!=0.00 ){
                            totalCard+=cardPaymentList.getCardAmt();
                        }
                    }
                    card = String.format("%.2f",totalCard);
                    if (card!=null){
                        int paidAmtSpaceStringLenght=8-card.length();
                        for (int j=0;j<paidAmtSpaceStringLenght;j++){
                            card=" "+card;
                        }
                    }
                    String cardHeader = saleInoviceData.getSiData().getCreditPayment().getPaymentTypeCash();
                    String cardHeaderHeaderName=padRight(cardHeader, 40);
                    Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                    Activity_PrintList.BLUETOOTH_PRINTER.BT_Write(cardHeaderHeaderName + card +"\n");

                    for (CardPaymentList cardPaymentList:saleInoviceData.getSiData().getCreditPayment().getCardPaymentList()){
                        if (cardPaymentList.getCardNo()!=null && !cardPaymentList.getCardNo().equals("")){
                            String  cardNo = "Card No ";
                            String cardHeaderName=padRight(cardNo, 15);
                            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write( cardHeaderName+": "+ cardPaymentList.getCardNo()  +"\n");

                        }
                        if (cardPaymentList.getCardDate()!=null && !cardPaymentList.getCardDate().equals("")){
                            String  cardDate = "Date ";
                            String cardHeaderName=padRight(cardDate, 15);
                            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss.SSSXXX");
                            try {
                                Date datecard = dateFormat.parse(cardPaymentList.getCardDate());//You will get date object relative to server/client timezone wherever it is parsed
                                DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy @hh:mm a"); //If you need time just put specific format for time like 'HH:mm:ss'
                                String dateStr = formatter.format(datecard);
                                Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                                Activity_PrintList.BLUETOOTH_PRINTER.BT_Write( cardHeaderName+": "+ dateStr +"\n");

                            } catch (ParseException e) {
                                e.printStackTrace();
                            }


                        }
                        if (cardPaymentList.getCardAmt()!=0.00){
                            String  cardAmt = "Card Amount ";
                            String cardHeaderName=padRight(cardAmt, 15);
                            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write( cardHeaderName+": "+ cardPaymentList.getCardAmt()  +"\n");

                        }

                    }
                }

            }
            if (saleInoviceData.getSiData().getVoucherPayment() !=null && saleInoviceData.getSiData().getVoucherPayment().getMultiVoucherPayments().size()>0){
                if (saleInoviceData.getSiData().getVoucherPayment().getPaymentTypeCash()!=null && saleInoviceData.getSiData().getVoucherPayment().getPaymentTypeCash().equals("Discount Voucher")){
                    if (saleInoviceData.getSiData().getVoucherPayment().getMultiVoucherPayments().get(0).getVoucherNo()!=null && !saleInoviceData.getSiData().getVoucherPayment().getMultiVoucherPayments().get(0).getVoucherNo().equals("Discount Given")){
                        Double totalVoucher=0.00;
                        String voucher="";
                        for (MultiVoucherPayment voucherPayment:saleInoviceData.getSiData().getVoucherPayment().getMultiVoucherPayments()){
                            if (voucherPayment.getVoucherAmt()!=0.00 ){
                                totalVoucher+=voucherPayment.getVoucherAmt();
                            }
                        }
                        voucher = String.format("%.2f",totalVoucher);
                        if (voucher!=null){
                            int paidAmtSpaceStringLenght=8-voucher.length();
                            for (int j=0;j<paidAmtSpaceStringLenght;j++){
                                voucher=" "+voucher;
                            }
                        }
                        String voucherHeader = saleInoviceData.getSiData().getVoucherPayment().getPaymentTypeCash();
                        String voucherHeaderName=padRight(voucherHeader, 40);
                        Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                        Activity_PrintList.BLUETOOTH_PRINTER.BT_Write(voucherHeaderName + voucher +"\n");

                    }
                    for (MultiVoucherPayment voucherPayment:saleInoviceData.getSiData().getVoucherPayment().getMultiVoucherPayments()){
                        // if (voucherPayment.getVoucherNo()!=null && !voucherPayment.getVoucherNo().equals("")){
                        if (voucherPayment.getVoucherNo()!=null && !voucherPayment.getVoucherNo().equals("")){
                            String  voucher = "Voucher No ";
                            String voucherHeaderName=padRight(voucher, 15);
                            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write( voucherHeaderName+": "+ voucherPayment.getVoucherNo()  +"\n");

                        }
                        if (voucherPayment.getVoucherAmt()!=0.00){
                            String  voucher = "Voucher Amount ";
                            String voucherHeaderName=padRight(voucher, 15);
                            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write( voucherHeaderName+": "+ voucherPayment.getVoucherAmt()  +"\n");

                        }
                        //  }

                    }
                }

            }
            // Activity_PrintList.BLUETOOTH_PRINTER.LF();
            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_star)  +"\n");
            Activity_PrintList.BLUETOOTH_PRINTER.LF();

            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
            Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte)30);
            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("Thank You...! Visit again" +"\n");
            // Activity_PrintList.BLUETOOTH_PRINTER.LF();
            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
            Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte)30);
            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("Designed & Powered by Hiaccounts" +"\n");
            Activity_PrintList.BLUETOOTH_PRINTER.LF();
            Activity_PrintList.BLUETOOTH_PRINTER.PartialCutPaper();


        }
        return true;


    }
    public static boolean printInvoiceTableOrder3Inch(Context context, RestraCheckoutData saleInoviceData, Bitmap bitmap, String tableName, in.hiaccounts.hinext.controlpanel.model.company_setup.compnayinformation.CompanyData companyData, List<RestraCheckoutItem> orderItemsList, Boolean notificationAppend) {
        if (RestuarantActivity.BLUETOOTH_PRINTER.IsNoConnection()) {
            return false;
        }

        if (saleInoviceData == null) {
            return false;
        } else {
            String companyName = "";
            String companyTelNumber = "";
            String companyFaxNumber = "";
            String companyGSTNumber = "";
            String companyAddress="";
            String  date ="", invoiceNo = "", invoiceCustomerName = "";


            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date dateFo = new Date();
            date = dateFormat.format(dateFo);

            if (companyData != null) {
                if (companyData.getCompanyName() != null) {
                    companyName = companyData.getCompanyName().toUpperCase();
                }
                if (companyData.getAddress() != null)
                    companyAddress = companyData.getAddress();
                if (companyData.getPhone() != null)
                    companyTelNumber = companyData.getPhone();
                if (companyData.getFax() != null)
                    companyFaxNumber = companyData.getFax();
                if (companyData.getRegisterNo() != null)
                    companyGSTNumber = companyData.getRegisterNo();
            }

            //LF = Line feed
                        RestuarantActivity.BLUETOOTH_PRINTER.Begin();
                        RestuarantActivity.BLUETOOTH_PRINTER.LF();
                        //   RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                        //RestuarantActivity.BLUETOOTH_PRINTER.BT_Write("");
                        // RestuarantActivity.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
/*                        if (bitmap!=null) {
                            //RestuarantActivity.BLUETOOTH_PRINTER.printByteData(logoByteArray);

                            // RestuarantActivity.BLUETOOTH_PRINTER.printImage(Bitmap.createScaledBitmap(bitmap, 200, 200, false));
             *//*   Bitmap newBm = BitmapConvertUtil.decodeSampledBitmapFromBitmap(bitmap, 100);
                byte xL = (byte) (((newBm.getWidth() - 1) / 8 + 1) % 256);
                byte xH = (byte) (((newBm.getWidth() - 1) / 8 + 1) / 256);
                byte yL = (byte) (newBm.getHeight() % 256);
                byte yH = (byte) (newBm.getHeight() / 256);
                byte[] pixels = BitmapConvertUtil.convert(newBm);

                RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                RestuarantActivity.BLUETOOTH_PRINTER.printImage(Bitmap.createScaledBitmap(bitmap, 150, 150, false));
*//*
                *//*RestuarantActivity.BLUETOOTH_PRINTER.BT_Write(new byte[]{29, 118, 118, 10, xL, xH, yL, yH});

                RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//CENTER
                RestuarantActivity.BLUETOOTH_PRINTER.BT_Write(pixels);*//*

                            Bitmap newBm = BitmapConvertUtil.decodeSampledBitmapFromBitmap(bitmap, 100);
                            byte xL = (byte)(((newBm.getWidth() - 1) / 8 + 1) % 256);
                            byte xH = (byte)(((newBm.getWidth() - 1) / 8 + 1) / 256);
                            byte yL = (byte)(newBm.getHeight() % 256);
                            byte yH = (byte)(newBm.getHeight() / 256);
                            Log.d("HsBluetoothPrintDriver", "xL = " + xL);
                            Log.d("HsBluetoothPrintDriver", "xH = " + xH);
                            Log.d("HsBluetoothPrintDriver", "yL = " + yL);
                            Log.d("HsBluetoothPrintDriver", "yH = " + yH);
                            byte[] pixels = BitmapConvertUtil.convert(newBm);
                            RestuarantActivity.BLUETOOTH_PRINTER.SetLeftStartSpacing((byte)30,(byte)30);
                            RestuarantActivity.BLUETOOTH_PRINTER.BT_Write(new byte[]{29, 118, 48, 0, xL, xH, yL, yH});
                            RestuarantActivity.BLUETOOTH_PRINTER.BT_Write(pixels);
                            RestuarantActivity.BLUETOOTH_PRINTER.BT_Write(new byte[]{10});
                        }*/
                        RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                        RestuarantActivity.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                        RestuarantActivity.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x11);//normal
                        RestuarantActivity.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal

                       /* RestuarantActivity.BLUETOOTH_PRINTER.BT_Write("Orders"+ "\n");
                        if (companyName!=null && !companyName.equals("")) {
                            RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                            RestuarantActivity.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                            RestuarantActivity.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x11);//normal
                            RestuarantActivity.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
                            RestuarantActivity.BLUETOOTH_PRINTER.BT_Write(companyName+ "\n");

                        }*/
                        if (saleInoviceData.getTableName()!=null && !saleInoviceData.getTableName().equals("")){
                            RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                            RestuarantActivity.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                            RestuarantActivity.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                            RestuarantActivity.BLUETOOTH_PRINTER.BT_Write("Table: "+saleInoviceData.getTableName()+ "\n");
                        }
                        if (saleInoviceData.getCustomerName()!=null && !saleInoviceData.getCustomerName().equals("")) {
                            //RestuarantActivity.BLUETOOTH_PRINTER.LF();
                            RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                            RestuarantActivity.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                            RestuarantActivity.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                            RestuarantActivity.BLUETOOTH_PRINTER.BT_Write("CustomerName: " + saleInoviceData.getCustomerName()+ "\n");

                        }
                        //String waiterName="NA";
                        if (!notificationAppend && saleInoviceData.getWaiterName()!=null && !saleInoviceData.getWaiterName().equals("")){
                            RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                            RestuarantActivity.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                            RestuarantActivity.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                            RestuarantActivity.BLUETOOTH_PRINTER.BT_Write("Steward: "+saleInoviceData.getWaiterName()+ "\n");
                        }
                        if (saleInoviceData.getTokenNo()!=null && !saleInoviceData.getTokenNo().equals("")) {
                            //RestuarantActivity.BLUETOOTH_PRINTER.LF();
                            RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                            RestuarantActivity.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                            RestuarantActivity.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                            RestuarantActivity.BLUETOOTH_PRINTER.BT_Write("Token: " + saleInoviceData.getTokenNo()+ "\n");

                        }



                        if (date!=null && !date.equals("")) {
                            //RestuarantActivity.BLUETOOTH_PRINTER.LF();
                            RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                            RestuarantActivity.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                            RestuarantActivity.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                            RestuarantActivity.BLUETOOTH_PRINTER.BT_Write("DATE: " + date+ "\n");

                        }

                        //RestuarantActivity.BLUETOOTH_PRINTER.LF();
                        RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//CENTER
                        RestuarantActivity.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line)+ "\n");
                        //RestuarantActivity.BLUETOOTH_PRINTER.LF();

                        double amtExTax = 0,amtIncTax= 0;
                        if (saleInoviceData != null) {
                            String  itemName = "",itemDesc="";
                            String itemUnitPrice = "";
                            String itemQty = "";
                            String itemAmtInclTax = "";
                            String itemTax = "";

                            String itemDetails = "Item Details";
                            String itemdetails=padRight(itemDetails, 23);

                            if (orderItemsList != null) {

                                if (orderItemsList!=null && orderItemsList.size()>0)
                                {
                                    RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//CENTER
                                    RestuarantActivity.BLUETOOTH_PRINTER.BT_Write(itemdetails+"           " +"Qty"+"\n");
                                    RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//CENTER
                                    RestuarantActivity.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line)+ "\n");

                                    for (int r = 0; r < orderItemsList.size(); r++) {
                                            RestraCheckoutItem item = orderItemsList.get(r);
                                            if (item != null) {
                                                if (item.getItemName() != null)
                                                    itemName = item.getItemName();


                                                itemQty = "" + item.getQty();
                                                int amtSpaceStringLenght2 = 4-itemQty.length();
                                                for (int j=0;j<amtSpaceStringLenght2;j++){
                                                    itemQty=" "+itemQty;
                                                }

                                                String itemname=padRight(itemName, 25);

                                                //String desc = padRight(itemDesc, 25);


                                                RestuarantActivity.BLUETOOTH_PRINTER.BT_Write(  itemname+"      "+itemQty+ "\n");

                                                if (item.getItemDescription()!=null && !item.getItemDescription().equals("")){
                                                    RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//CENTER
                                                    RestuarantActivity.BLUETOOTH_PRINTER.BT_Write("[" + item.getItemDescription() + "]"+"\n");
                                                }

                                            }
                                    }
                                }

                            }
                        }


                        RestuarantActivity.BLUETOOTH_PRINTER.LF();
                        RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                        RestuarantActivity.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);
                        RestuarantActivity.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line)+ "\n");
                        RestuarantActivity.BLUETOOTH_PRINTER.LF();
                        RestuarantActivity.BLUETOOTH_PRINTER.BT_Write( "\n");
                        RestuarantActivity.BLUETOOTH_PRINTER.LF();
                        RestuarantActivity.BLUETOOTH_PRINTER.BT_Write( "\n");
                        RestuarantActivity.BLUETOOTH_PRINTER.LF();
                        RestuarantActivity.BLUETOOTH_PRINTER.BT_Write( "\n");
                        RestuarantActivity.BLUETOOTH_PRINTER.CutPaper();
                    //RestuarantActivity.BLUETOOTH_PRINTER.LF();
                     /*   RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                        RestuarantActivity.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_star)+ "\n");

                        RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
                        RestuarantActivity.BLUETOOTH_PRINTER.SetLineSpacing((byte)30);
                        RestuarantActivity.BLUETOOTH_PRINTER.BT_Write("Thank You...!"+ "\n");
                        //RestuarantActivity.BLUETOOTH_PRINTER.LF();
                        RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
                        RestuarantActivity.BLUETOOTH_PRINTER.SetLineSpacing((byte)30);
                        RestuarantActivity.BLUETOOTH_PRINTER.BT_Write("Designed & Powered by Hiaccounts"+ "\n");
                        RestuarantActivity.BLUETOOTH_PRINTER.LF();
                        RestuarantActivity.BLUETOOTH_PRINTER.PartialCutPaper();
                        RestuarantActivity.BLUETOOTH_PRINTER.LF();
                        RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                        RestuarantActivity.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line)+ "\n");
                        RestuarantActivity.BLUETOOTH_PRINTER.LF();*/
        }
        return true;

    }
    public static boolean printInvoiceTableCancelOrder3Inch(Context context, RestraCheckoutData saleInoviceData, Bitmap bitmap, String tableName, in.hiaccounts.hinext.controlpanel.model.company_setup.compnayinformation.CompanyData companyData, List<RestraCheckoutItem> orderItemsList, Boolean notificationAppend) {
        if (RestuarantActivity.BLUETOOTH_PRINTER.IsNoConnection()) {
            return false;
        }

        if (saleInoviceData == null) {
            return false;
        } else {
            String companyName = "";
            String companyTelNumber = "";
            String companyFaxNumber = "";
            String companyGSTNumber = "";
            String companyAddress="";
            String  date ="", invoiceNo = "", invoiceCustomerName = "";


            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date dateFo = new Date();
            date = dateFormat.format(dateFo);

            if (companyData != null) {
                if (companyData.getCompanyName() != null) {
                    companyName = companyData.getCompanyName().toUpperCase();
                }
                if (companyData.getAddress() != null)
                    companyAddress = companyData.getAddress();
                if (companyData.getPhone() != null)
                    companyTelNumber = companyData.getPhone();
                if (companyData.getFax() != null)
                    companyFaxNumber = companyData.getFax();
                if (companyData.getRegisterNo() != null)
                    companyGSTNumber = companyData.getRegisterNo();
            }

            //LF = Line feed
            RestuarantActivity.BLUETOOTH_PRINTER.Begin();
            RestuarantActivity.BLUETOOTH_PRINTER.LF();
            //   RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
            //RestuarantActivity.BLUETOOTH_PRINTER.BT_Write("");
            // RestuarantActivity.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
/*            if (bitmap!=null) {
                //RestuarantActivity.BLUETOOTH_PRINTER.printByteData(logoByteArray);

                // RestuarantActivity.BLUETOOTH_PRINTER.printImage(Bitmap.createScaledBitmap(bitmap, 200, 200, false));
             *//*   Bitmap newBm = BitmapConvertUtil.decodeSampledBitmapFromBitmap(bitmap, 100);
                byte xL = (byte) (((newBm.getWidth() - 1) / 8 + 1) % 256);
                byte xH = (byte) (((newBm.getWidth() - 1) / 8 + 1) / 256);
                byte yL = (byte) (newBm.getHeight() % 256);
                byte yH = (byte) (newBm.getHeight() / 256);
                byte[] pixels = BitmapConvertUtil.convert(newBm);

                RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                RestuarantActivity.BLUETOOTH_PRINTER.printImage(Bitmap.createScaledBitmap(bitmap, 150, 150, false));
*//*
                *//*RestuarantActivity.BLUETOOTH_PRINTER.BT_Write(new byte[]{29, 118, 118, 10, xL, xH, yL, yH});

                RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//CENTER
                RestuarantActivity.BLUETOOTH_PRINTER.BT_Write(pixels);*//*

                Bitmap newBm = BitmapConvertUtil.decodeSampledBitmapFromBitmap(bitmap, 100);
                byte xL = (byte)(((newBm.getWidth() - 1) / 8 + 1) % 256);
                byte xH = (byte)(((newBm.getWidth() - 1) / 8 + 1) / 256);
                byte yL = (byte)(newBm.getHeight() % 256);
                byte yH = (byte)(newBm.getHeight() / 256);
                Log.d("HsBluetoothPrintDriver", "xL = " + xL);
                Log.d("HsBluetoothPrintDriver", "xH = " + xH);
                Log.d("HsBluetoothPrintDriver", "yL = " + yL);
                Log.d("HsBluetoothPrintDriver", "yH = " + yH);
                byte[] pixels = BitmapConvertUtil.convert(newBm);
                RestuarantActivity.BLUETOOTH_PRINTER.SetLeftStartSpacing((byte)30,(byte)30);
                RestuarantActivity.BLUETOOTH_PRINTER.BT_Write(new byte[]{29, 118, 48, 0, xL, xH, yL, yH});
                RestuarantActivity.BLUETOOTH_PRINTER.BT_Write(pixels);
                RestuarantActivity.BLUETOOTH_PRINTER.BT_Write(new byte[]{10});
            }*/
            RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
            RestuarantActivity.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
            RestuarantActivity.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x11);//normal
            RestuarantActivity.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
            RestuarantActivity.BLUETOOTH_PRINTER.BT_Write("Cancel Orders"+ "\n");


            if (companyName!=null && !companyName.equals("")) {
                RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                RestuarantActivity.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                RestuarantActivity.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x11);//normal
                RestuarantActivity.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
                RestuarantActivity.BLUETOOTH_PRINTER.BT_Write(companyName+ "\n");

            }

            if (saleInoviceData.getTableName()!=null && !saleInoviceData.getTableName().equals("")){
                RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                RestuarantActivity.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                RestuarantActivity.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                RestuarantActivity.BLUETOOTH_PRINTER.BT_Write("Table: "+saleInoviceData.getTableName()+ "\n");
            }
            if (saleInoviceData.getCustomerName()!=null && !saleInoviceData.getCustomerName().equals("")) {
                //RestuarantActivity.BLUETOOTH_PRINTER.LF();
                RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                RestuarantActivity.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                RestuarantActivity.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                RestuarantActivity.BLUETOOTH_PRINTER.BT_Write("CustomerName: " + saleInoviceData.getCustomerName()+ "\n");

            }
            //String waiterName="NA";
            if (!notificationAppend && saleInoviceData.getWaiterName()!=null && !saleInoviceData.getWaiterName().equals("")){
                RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                RestuarantActivity.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                RestuarantActivity.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                RestuarantActivity.BLUETOOTH_PRINTER.BT_Write("Steward: "+saleInoviceData.getWaiterName()+ "\n");
            }
            if (saleInoviceData.getTokenNo()!=null && !saleInoviceData.getTokenNo().equals("")) {
                //RestuarantActivity.BLUETOOTH_PRINTER.LF();
                RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                RestuarantActivity.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                RestuarantActivity.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                RestuarantActivity.BLUETOOTH_PRINTER.BT_Write("Token: " + saleInoviceData.getTokenNo()+ "\n");

            }



            if (date!=null && !date.equals("")) {
                //RestuarantActivity.BLUETOOTH_PRINTER.LF();
                RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                RestuarantActivity.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                RestuarantActivity.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                RestuarantActivity.BLUETOOTH_PRINTER.BT_Write("DATE: " + date+ "\n");

            }

            //RestuarantActivity.BLUETOOTH_PRINTER.LF();
            RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//CENTER
            RestuarantActivity.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line)+ "\n");
            //RestuarantActivity.BLUETOOTH_PRINTER.LF();

            double amtExTax = 0,amtIncTax= 0;
            if (saleInoviceData != null) {
                String  itemName = "",itemDesc="";
                String itemUnitPrice = "";
                String itemQty = "";
                String itemAmtInclTax = "";
                String itemTax = "";

                String itemDetails = "Item Details";
                String itemdetails=padRight(itemDetails, 23);

                if (orderItemsList != null) {

                    if (orderItemsList!=null && orderItemsList.size()>0)
                    {
                        RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//CENTER
                        RestuarantActivity.BLUETOOTH_PRINTER.BT_Write(itemdetails+"           " +"Qty"+"\n");
                        RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//CENTER
                        RestuarantActivity.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line)+ "\n");

                        for (int r = 0; r < orderItemsList.size(); r++) {
                            RestraCheckoutItem item = orderItemsList.get(r);
                            if (item != null) {
                                if (item.getItemName() != null)
                                    itemName = item.getItemName();


                                itemQty = "" + item.getQty();
                                int amtSpaceStringLenght2 = 4-itemQty.length();
                                for (int j=0;j<amtSpaceStringLenght2;j++){
                                    itemQty=" "+itemQty;
                                }

                                String itemname=padRight(itemName, 25);

                                //String desc = padRight(itemDesc, 25);

                                amtExTax=amtExTax+(item.getQty()*item.getUnitPrice());
                                RestuarantActivity.BLUETOOTH_PRINTER.BT_Write(  itemname+"      "+itemQty+ "\n");

                                if (item.getItemDescription()!=null && !item.getItemDescription().equals("")){
                                    RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//CENTER
                                    RestuarantActivity.BLUETOOTH_PRINTER.BT_Write("[" + item.getItemDescription() + "]"+"\n");
                                }

                            }
                        }
                    }

                }
            }

            RestuarantActivity.BLUETOOTH_PRINTER.LF();
            RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            RestuarantActivity.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);
            RestuarantActivity.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line)+ "\n");
            RestuarantActivity.BLUETOOTH_PRINTER.LF();
            RestuarantActivity.BLUETOOTH_PRINTER.BT_Write( "\n");
            RestuarantActivity.BLUETOOTH_PRINTER.LF();
            RestuarantActivity.BLUETOOTH_PRINTER.BT_Write( "\n");
            RestuarantActivity.BLUETOOTH_PRINTER.LF();
            RestuarantActivity.BLUETOOTH_PRINTER.BT_Write( "\n");
            RestuarantActivity.BLUETOOTH_PRINTER.CutPaper();
        }
        return true;

    }
    public static boolean printCloseDailyReport3Inch(Activity activity, List<String> headerList, HashMap<String, List<DailyReportItemData>> childList, Bitmap bitmap, String tableName, in.hiaccounts.hinext.controlpanel.model.company_setup.compnayinformation.CompanyData companyData) {
        if (Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.IsNoConnection()) {
            return false;
        }

        if (headerList == null) {
            return false;
        } else {
            String companyName = "";
            String companyTelNumber = "";
            String companyFaxNumber = "";
            String companyGSTNumber = "";
            String companyAddress="";
            String  date ="", invoiceNo = "", invoiceCustomerName = "";
            String  itemName = "";
            String itemQty = "";
            String itemAmtInclTax = "";

            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date dateFo = new Date();
            date = dateFormat.format(dateFo);

            if (companyData != null) {
                if (companyData.getCompanyName() != null) {
                    companyName = companyData.getCompanyName().toUpperCase();
                }
                if (companyData.getAddress() != null)
                    companyAddress = companyData.getAddress();
                if (companyData.getPhone() != null)
                    companyTelNumber = companyData.getPhone();
                if (companyData.getFax() != null)
                    companyFaxNumber = companyData.getFax();
                if (companyData.getRegisterNo() != null)
                    companyGSTNumber = companyData.getRegisterNo();
            }


            //LF = Line feed
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.Begin();
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.LF();
   /*         if (bitmap!=null) {

                Bitmap newBm = BitmapConvertUtil.decodeSampledBitmapFromBitmap(bitmap, 100);
                byte xL = (byte)(((newBm.getWidth() - 1) / 8 + 1) % 256);
                byte xH = (byte)(((newBm.getWidth() - 1) / 8 + 1) / 256);
                byte yL = (byte)(newBm.getHeight() % 256);
                byte yH = (byte)(newBm.getHeight() / 256);
                Log.d("HsBluetoothPrintDriver", "xL = " + xL);
                Log.d("HsBluetoothPrintDriver", "xH = " + xH);
                Log.d("HsBluetoothPrintDriver", "yL = " + yL);
                Log.d("HsBluetoothPrintDriver", "yH = " + yH);
                byte[] pixels = BitmapConvertUtil.convert(newBm);
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetLeftStartSpacing((byte)30,(byte)30);
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write(new byte[]{29, 118, 48, 0, xL, xH, yL, yH});
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write(pixels);
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write(new byte[]{10});
            }*/


            if (companyName!=null && !companyName.equals("")) {
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x11);//normal
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write(companyName+ "\n");

            }


            if (date!=null && !date.equals("")) {
                //Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.LF();
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write("DATE: " + date+ "\n");

            }

            //Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.LF();
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//CENTER
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write(activity.getResources().getString(R.string.print_line)+ "\n");
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.LF();

            double grandTotal=0.0;
            for (int i=0;i<headerList.size();i++){
               // Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.LF();
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//CENTER
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x05);//normal
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write( headerList.get(i)+ "\n");

                int flag=0;
                double totalPrice=0.0;
                for (Map.Entry<String, List<DailyReportItemData>> entry : childList.entrySet()) {
                    for (DailyReportItemData item:entry.getValue()){
                        if (headerList.get(i).equals(item.getItemCategoryName())){
                            flag=1;
                            totalPrice = totalPrice + item.getItemTotalAmountInTax();
                            if (item.getItemName() != null)
                                itemName = item.getItemName();

                            int itemNameSpaceStringLenght = 0;
                            for (int j=0;j<itemNameSpaceStringLenght;j++){
                                itemName=itemName+" ";
                            }


                            itemAmtInclTax = String.format("%.2f", (item.getItemTotalAmountInTax()));

                            int amtSpaceStringLenght1=8-itemAmtInclTax.length();
                            for (int j=0;j<amtSpaceStringLenght1;j++){
                                itemAmtInclTax=" "+itemAmtInclTax;
                            }


                            itemQty = "" + item.getItemQuantity();
                            int amtSpaceStringLenght2 = 4-itemQty.length();
                            for (int j=0;j<amtSpaceStringLenght2;j++){
                                itemQty=itemQty+" ";
                            }
                            //Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.LF();
                            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte)2);//CENTER
                            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write( itemName+" "+itemQty+"   "+itemAmtInclTax+ "\n");

                        }

                    }
                    if (entry.getValue().size() == 0) {
                        headerList.remove(entry.getKey());
                    }



                }
                if (totalPrice!=0.0  && flag == 1){
                    grandTotal+=totalPrice;
                    Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
                    Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write(activity.getResources().getString(R.string.print_line)+ "\n");

                    String price = String.format("%.2f",totalPrice);
                    if (price!=null){
                        int amtSpaceStringLenght=8-price.length();
                        for (int j=0;j<amtSpaceStringLenght;j++){
                            price=" "+price;
                        }
                    }
                    Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 2);//LEFT
                    Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write("  Total : " + price +"\n" );

                    Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
                    Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write(activity.getResources().getString(R.string.print_line)+ "\n");

                }

            }
            String grandTotalPrice = String.format("%.2f",grandTotal);
            if (grandTotalPrice!=null){
                int amtSpaceStringLenght=8-grandTotalPrice.length();
                for (int j=0;j<amtSpaceStringLenght;j++){
                    grandTotalPrice=" "+grandTotalPrice;
                }
            }
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 2);//LEFT
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write("  GrandTotal : " + grandTotalPrice +"\n" );




            //Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.LF();
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write(activity.getResources().getString(R.string.print_star)+ "\n");

            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetLineSpacing((byte)30);
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write("Thank You...!"+ "\n");
            //Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.LF();
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetLineSpacing((byte)30);
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write("Designed & Powered by Hiaccounts"+ "\n");
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.LF();
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.PartialCutPaper();
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.LF();
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write(activity.getResources().getString(R.string.print_line)+ "\n");
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.LF();


        }
        return true;

    }
    public static boolean printDailyReport3Inch(Activity activity, List<String> headerList, HashMap<String, List<DailyReportItemData>> childList, Bitmap bitmap, String tableName, in.hiaccounts.hinext.controlpanel.model.company_setup.compnayinformation.CompanyData companyData, DailyReportData dailyReportData, String paymentType) {
        if (Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.IsNoConnection()) {
            return false;
        }

        if (headerList == null) {
            return false;
        } else {
            String companyName = "";
            String companyTelNumber = "";
            String companyFaxNumber = "";
            String companyGSTNumber = "";
            String companyAddress="";
            String  date ="", invoiceNo = "", invoiceCustomerName = "";
            String  itemName = "";
            String itemQty = "";
            String itemAmtInclTax = "";

            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date dateFo = new Date();
            date = dateFormat.format(dateFo);

            if (companyData != null) {
                if (companyData.getCompanyName() != null) {
                    companyName = companyData.getCompanyName().toUpperCase();
                }
                if (companyData.getAddress() != null)
                    companyAddress = companyData.getAddress();
                if (companyData.getPhone() != null)
                    companyTelNumber = companyData.getPhone();
                if (companyData.getFax() != null)
                    companyFaxNumber = companyData.getFax();
                if (companyData.getRegisterNo() != null)
                    companyGSTNumber = companyData.getRegisterNo();
            }


            //LF = Line feed
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.Begin();
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.LF();
       /*     if (bitmap!=null) {

                Bitmap newBm = BitmapConvertUtil.decodeSampledBitmapFromBitmap(bitmap, 100);
                byte xL = (byte)(((newBm.getWidth() - 1) / 8 + 1) % 256);
                byte xH = (byte)(((newBm.getWidth() - 1) / 8 + 1) / 256);
                byte yL = (byte)(newBm.getHeight() % 256);
                byte yH = (byte)(newBm.getHeight() / 256);
                Log.d("HsBluetoothPrintDriver", "xL = " + xL);
                Log.d("HsBluetoothPrintDriver", "xH = " + xH);
                Log.d("HsBluetoothPrintDriver", "yL = " + yL);
                Log.d("HsBluetoothPrintDriver", "yH = " + yH);
                byte[] pixels = BitmapConvertUtil.convert(newBm);
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetLeftStartSpacing((byte)30,(byte)30);
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write(new byte[]{29, 118, 48, 0, xL, xH, yL, yH});
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write(pixels);
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write(new byte[]{10});
            }*/


            if (companyName!=null && !companyName.equals("")) {
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x11);//normal
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write(companyName+ "\n");

            }


            if (date!=null && !date.equals("")) {
                //Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.LF();
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write("DATE: " + date+ "\n");

            }

            //Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.LF();
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//CENTER
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write(activity.getResources().getString(R.string.print_line)+ "\n");
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.LF();

            double grandTotal=0.0;
            for (int i=0;i<headerList.size();i++){
                // Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.LF();
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//CENTER
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x05);//normal
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write( headerList.get(i)+ "\n");

                int flag=0;
                double totalPrice=0.0;
                for (Map.Entry<String, List<DailyReportItemData>> entry : childList.entrySet()) {
                    for (DailyReportItemData item:entry.getValue()){
                        if (headerList.get(i).equals(item.getItemCategoryName())){
                            flag=1;
                            totalPrice = totalPrice + item.getItemTotalAmountInTax();
                            if (item.getItemName() != null)
                                itemName = item.getItemName();

                            int itemNameSpaceStringLenght = 0;
                            for (int j=0;j<itemNameSpaceStringLenght;j++){
                                itemName=itemName+" ";
                            }


                            itemAmtInclTax = String.format("%.2f", (item.getItemTotalAmountInTax()));

                            int amtSpaceStringLenght1=8-itemAmtInclTax.length();
                            for (int j=0;j<amtSpaceStringLenght1;j++){
                                itemAmtInclTax=" "+itemAmtInclTax;
                            }


                            itemQty = "" + item.getItemQuantity();
                            int amtSpaceStringLenght2 = 4-itemQty.length();
                            for (int j=0;j<amtSpaceStringLenght2;j++){
                                itemQty=itemQty+" ";
                            }
                            //Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.LF();
                            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte)2);//CENTER
                            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write( itemName+" "+itemQty+"   "+itemAmtInclTax+ "\n");

                        }

                    }
                    if (entry.getValue().size() == 0) {
                        headerList.remove(entry.getKey());
                    }



                }
                if (totalPrice!=0.0  && flag == 1){
                    grandTotal+=totalPrice;
                    Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
                    Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write(activity.getResources().getString(R.string.print_line)+ "\n");

                    String price = String.format("%.2f",totalPrice);
                    if (price!=null){
                        int amtSpaceStringLenght=8-price.length();
                        for (int j=0;j<amtSpaceStringLenght;j++){
                            price=" "+price;
                        }
                    }
                    Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 2);//LEFT
                    Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write("  Total : " + price +"\n" );

                    Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
                    Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write(activity.getResources().getString(R.string.print_line)+ "\n");

                }

            }
            String grandTotalPrice="";
            if (dailyReportData.getTotalAmount()!=null && !dailyReportData.getTotalAmount().equals("")){
                grandTotalPrice = String.format("%.2f",dailyReportData.getTotalAmount());
                if (grandTotalPrice!=null){
                    int amtSpaceStringLenght=8-grandTotalPrice.length();
                    for (int j=0;j<amtSpaceStringLenght;j++){
                        grandTotalPrice=" "+grandTotalPrice;
                    }
                }
            }else{
                grandTotalPrice="0.00";
            }
            if (dailyReportData.getDiscount()!=null && dailyReportData.getDiscount()!=0.00){
                String discount = String.format("%.2f",dailyReportData.getDiscount());
                if (discount!=null){
                    int amtSpaceStringLenght=8-discount.length();
                    for (int j=0;j<amtSpaceStringLenght;j++){
                        discount=" "+discount;
                    }
                }
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 2);//LEFT
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write(" Total Discount : " + discount +"\n" );

            }

            String roundingOffValue="";
            Double roundval=0.00;
            if (dailyReportData.getRoundingOff()!=null && dailyReportData.getRoundingOff() !=0.00 ){
                //String roundingOff = String.format("%.2f",dailyReportData.getRoundingOff());
                roundval = Double.valueOf(dailyReportData.getRoundingOff());

                if(roundval*1<0){
                    BigDecimal bd = new BigDecimal( roundval*-1);
                    bd = bd.setScale(2, RoundingMode.HALF_UP);
                    roundingOffValue = String.valueOf(bd);
                }else{
                    BigDecimal bd = new BigDecimal( (( roundval*-1))/1);
                    bd = bd.setScale(2, RoundingMode.HALF_UP);
                    roundingOffValue = String.valueOf(bd);
                }
                if (roundingOffValue!=null){
                    int amtSpaceStringLenght=8-roundingOffValue.length();
                    for (int j=0;j<amtSpaceStringLenght;j++){
                        roundingOffValue=" "+roundingOffValue;
                    }
                }
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 2);//LEFT
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write("  Rounding Off : " + roundingOffValue +"\n" );
            }
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 2);//LEFT
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write("  Total Bill : " + grandTotalPrice +"\n" );
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write(activity.getResources().getString(R.string.print_line)+ "\n");

            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write("  PaymentType : \n" );


            if (dailyReportData.getPayment()!=null){
                JSONObject jsonObject = null;
                Gson gson = new Gson();
                try {
                    jsonObject = new JSONObject(paymentType);

                    Map<String, Object> map = gson.fromJson(jsonObject.getString("Payment"), new TypeToken<Map<String, Object>>(){}.getType());
                    for(Map.Entry m:map.entrySet()){
                        Double d = null;
                        if (m.getValue() instanceof Double) {
                            d = (Double) m.getValue();
                            if (d !=null && d !=0.00){
                                String cash = String.format("%.2f",m.getValue());
                                if (cash!=null){
                                    int amtSpaceStringLenght=8-cash.length();
                                    for (int j=0;j<amtSpaceStringLenght;j++){
                                        cash=" "+cash;
                                    }
                                }
                                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 2);//LEFT
                                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write(m.getKey()+": " + cash +"\n" );

                            }
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            //Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.LF();
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write(activity.getResources().getString(R.string.print_star)+ "\n");

            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetLineSpacing((byte)30);
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write("Thank You...!"+ "\n");
            //Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.LF();
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetLineSpacing((byte)30);
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write("Designed & Powered by Hiaccounts"+ "\n");
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.LF();
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.PartialCutPaper();
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.LF();
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write(activity.getResources().getString(R.string.print_line)+ "\n");
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.LF();


        }
        return true;

    }
    public static boolean printDailyReport2Inch(Activity activity, List<String> headerList, HashMap<String, List<DailyReportItemData>> childList, Bitmap bitmap, String tableName, in.hiaccounts.hinext.controlpanel.model.company_setup.compnayinformation.CompanyData companyData, DailyReportData dailyReportData, String paymentType) {
        if (Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.IsNoConnection()) {
            return false;
        }

        if (headerList == null) {
            return false;
        } else {
            String companyName = "";
            String companyTelNumber = "";
            String companyFaxNumber = "";
            String companyGSTNumber = "";
            String companyAddress="";
            String  date ="", invoiceNo = "", invoiceCustomerName = "";
            String  itemName = "";
            String itemQty = "";
            String itemAmtInclTax = "";

            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date dateFo = new Date();
            date = dateFormat.format(dateFo);

            if (companyData != null) {
                if (companyData.getCompanyName() != null) {
                    companyName = companyData.getCompanyName().toUpperCase();
                }
                if (companyData.getAddress() != null)
                    companyAddress = companyData.getAddress();
                if (companyData.getPhone() != null)
                    companyTelNumber = companyData.getPhone();
                if (companyData.getFax() != null)
                    companyFaxNumber = companyData.getFax();
                if (companyData.getRegisterNo() != null)
                    companyGSTNumber = companyData.getRegisterNo();
            }


            //LF = Line feed
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.Begin();
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.LF();
     /*       if (bitmap!=null) {

                Bitmap newBm = BitmapConvertUtil.decodeSampledBitmapFromBitmap(bitmap, 100);
                byte xL = (byte)(((newBm.getWidth() - 1) / 8 + 1) % 256);
                byte xH = (byte)(((newBm.getWidth() - 1) / 8 + 1) / 256);
                byte yL = (byte)(newBm.getHeight() % 256);
                byte yH = (byte)(newBm.getHeight() / 256);
                Log.d("HsBluetoothPrintDriver", "xL = " + xL);
                Log.d("HsBluetoothPrintDriver", "xH = " + xH);
                Log.d("HsBluetoothPrintDriver", "yL = " + yL);
                Log.d("HsBluetoothPrintDriver", "yH = " + yH);
                byte[] pixels = BitmapConvertUtil.convert(newBm);
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetLeftStartSpacing((byte)30,(byte)30);
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write(new byte[]{29, 118, 48, 0, xL, xH, yL, yH});
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write(pixels);
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write(new byte[]{10});
            }*/


            if (companyName!=null && !companyName.equals("")) {
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x11);//normal
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write(companyName+ "\n");

            }


            if (date!=null && !date.equals("")) {
                //Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.LF();
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write("DATE: " + date+ "\n");

            }

            //Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.LF();
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//CENTER
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write(activity.getResources().getString(R.string.print_line2inch)+ "\n");
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.LF();

            double grandTotal=0.0;
            for (int i=0;i<headerList.size();i++){
                // Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.LF();
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//CENTER
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write( headerList.get(i)+ "\n");
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write("-----------------------"+ "\n");
                int flag=0;
                double totalPrice=0.0;
                for (Map.Entry<String, List<DailyReportItemData>> entry : childList.entrySet()) {
                    for (DailyReportItemData item:entry.getValue()){
                        if (headerList.get(i).equals(item.getItemCategoryName())){
                            flag=1;
                            totalPrice = totalPrice + item.getItemTotalAmountInTax();
                            if (item.getItemName() != null)
                                itemName = item.getItemName();

                            int itemNameSpaceStringLenght = 0;
                            for (int j=0;j<itemNameSpaceStringLenght;j++){
                                itemName=itemName+" ";
                            }


                            itemAmtInclTax = String.format("%.2f", (item.getItemTotalAmountInTax()));
                            //itemAmtInclTax = String.format("%.2f", (item.getAmtinclusivetax()));

                            int amtSpaceStringLenght1=8-itemAmtInclTax.length();
                            for (int j=0;j<amtSpaceStringLenght1;j++){
                                itemAmtInclTax=" "+itemAmtInclTax;
                            }


                            itemQty = "" + item.getItemQuantity();
                            int amtSpaceStringLenght2 = 4-itemQty.length();
                            for (int j=0;j<amtSpaceStringLenght2;j++){
                                itemQty=itemQty+" ";
                            }

                            String itemname="";
                            if (itemName.length()>18){
                                itemname = dailyReportBreakLines(itemName,18,itemQty,itemAmtInclTax);
                                //Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.LF();
                                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte)0);//CENTER
                                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write( itemname+ "\n");
                            }else{
                                itemname=padRight(itemName, 18);
                                //Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.LF();
                                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte)0);//CENTER
                                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write( itemname+" "+itemQty+" "+itemAmtInclTax+ "\n");
                            }


                        }

                    }
                    if (entry.getValue().size() == 0) {
                        headerList.remove(entry.getKey());
                    }



                }
                if (totalPrice!=0.0  && flag == 1){
                    grandTotal+=totalPrice;
                    Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
                    Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write(activity.getResources().getString(R.string.print_line2inch)+ "\n");

                    String price = String.format("%.2f",totalPrice);
                    if (price!=null){
                        int amtSpaceStringLenght=8-price.length();
                        for (int j=0;j<amtSpaceStringLenght;j++){
                            price=" "+price;
                        }
                    }
                    Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 2);//LEFT
                    Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write("  Total : " + price +"\n" );
                    Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
                    Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write(activity.getResources().getString(R.string.print_line2inch)+ "\n");

                }

            }
            String grandTotalPrice="";
            if (dailyReportData.getTotalAmount()!=null && !dailyReportData.getTotalAmount().equals("")){
                 grandTotalPrice = String.format("%.2f",dailyReportData.getTotalAmount());
                if (grandTotalPrice!=null){
                    int amtSpaceStringLenght=8-grandTotalPrice.length();
                    for (int j=0;j<amtSpaceStringLenght;j++){
                        grandTotalPrice=" "+grandTotalPrice;
                    }
                }
            }else{
                grandTotalPrice="0.00";
            }
            if (dailyReportData.getDiscount()!=null && dailyReportData.getDiscount()!=0.00){
                String discount = String.format("%.2f",dailyReportData.getDiscount());
                if (discount!=null){
                    int amtSpaceStringLenght=8-discount.length();
                    for (int j=0;j<amtSpaceStringLenght;j++){
                        discount=" "+discount;
                    }
                }
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 2);//LEFT
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write(" Total Discount : " + discount +"\n" );

            }
            String roundingOffValue="";
            Double roundval=0.00;
            if (dailyReportData.getRoundingOff()!=null &&dailyReportData.getRoundingOff() !=0.00 ){
                //String roundingOff = String.format("%.2f",dailyReportData.getRoundingOff());
                roundval = Double.valueOf(dailyReportData.getRoundingOff());

                if(roundval*1<0){
                    BigDecimal bd = new BigDecimal( roundval*-1);
                    bd = bd.setScale(2, RoundingMode.HALF_UP);
                    roundingOffValue = String.valueOf(bd);
                }else{
                    BigDecimal bd = new BigDecimal( (( roundval*-1))/1);
                    bd = bd.setScale(2, RoundingMode.HALF_UP);
                    roundingOffValue = String.valueOf(bd);
                }
                if (roundingOffValue!=null){
                    int amtSpaceStringLenght=8-roundingOffValue.length();
                    for (int j=0;j<amtSpaceStringLenght;j++){
                        roundingOffValue=" "+roundingOffValue;
                    }
                }
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 2);//LEFT
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write("  Rounding Off : " + roundingOffValue +"\n" );
            }
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 2);//LEFT
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write("  Total Bill : " + grandTotalPrice +"\n" );
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write(activity.getResources().getString(R.string.print_line2inch)+ "\n");

            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write("  PaymentType : \n" );
            if (dailyReportData.getPayment()!=null){
                JSONObject jsonObject = null;
                Gson gson = new Gson();
                try {
                    jsonObject = new JSONObject(paymentType);

                    Map<String, Object> map = gson.fromJson(jsonObject.getString("Payment"), new TypeToken<Map<String, Object>>(){}.getType());
                    for(Map.Entry m:map.entrySet()){
                        Double d = null;
                        if (m.getValue() instanceof Double) {
                            d = (Double) m.getValue();
                            if (d !=null && d !=0.00){
                                String cash = String.format("%.2f",m.getValue());
                                if (cash!=null){
                                    int amtSpaceStringLenght=8-cash.length();
                                    for (int j=0;j<amtSpaceStringLenght;j++){
                                        cash=" "+cash;
                                    }
                                }
                                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 2);//LEFT
                                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write(m.getKey()+": " + cash +"\n" );

                            }
                        }



                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
            //Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.LF();
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write(activity.getResources().getString(R.string.print_star2inch)+ "\n");

            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetLineSpacing((byte)30);
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write("Thank You...!"+ "\n");
            //Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.LF();
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetLineSpacing((byte)30);
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write("Designed & Powered by Hiaccounts"+ "\n");
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.LF();
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.PartialCutPaper();
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.LF();
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write(activity.getResources().getString(R.string.print_line2inch)+ "\n");
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.LF();


        }
        return true;

    }
    public static boolean printCloseDailyReport2Inch(Activity activity, List<String> headerList, HashMap<String, List<DailyReportItemData>> childList, Bitmap bitmap, String tableName, in.hiaccounts.hinext.controlpanel.model.company_setup.compnayinformation.CompanyData companyData) {
        if (Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.IsNoConnection()) {
            return false;
        }

        if (headerList == null) {
            return false;
        } else {
            String companyName = "";
            String companyTelNumber = "";
            String companyFaxNumber = "";
            String companyGSTNumber = "";
            String companyAddress="";
            String  date ="", invoiceNo = "", invoiceCustomerName = "";
            String  itemName = "";
            String itemQty = "";
            String itemAmtInclTax = "";

            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date dateFo = new Date();
            date = dateFormat.format(dateFo);

            if (companyData != null) {
                if (companyData.getCompanyName() != null) {
                    companyName = companyData.getCompanyName().toUpperCase();
                }
                if (companyData.getAddress() != null)
                    companyAddress = companyData.getAddress();
                if (companyData.getPhone() != null)
                    companyTelNumber = companyData.getPhone();
                if (companyData.getFax() != null)
                    companyFaxNumber = companyData.getFax();
                if (companyData.getRegisterNo() != null)
                    companyGSTNumber = companyData.getRegisterNo();
            }


            //LF = Line feed
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.Begin();
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.LF();
      /*      if (bitmap!=null) {

                Bitmap newBm = BitmapConvertUtil.decodeSampledBitmapFromBitmap(bitmap, 100);
                byte xL = (byte)(((newBm.getWidth() - 1) / 8 + 1) % 256);
                byte xH = (byte)(((newBm.getWidth() - 1) / 8 + 1) / 256);
                byte yL = (byte)(newBm.getHeight() % 256);
                byte yH = (byte)(newBm.getHeight() / 256);
                Log.d("HsBluetoothPrintDriver", "xL = " + xL);
                Log.d("HsBluetoothPrintDriver", "xH = " + xH);
                Log.d("HsBluetoothPrintDriver", "yL = " + yL);
                Log.d("HsBluetoothPrintDriver", "yH = " + yH);
                byte[] pixels = BitmapConvertUtil.convert(newBm);
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetLeftStartSpacing((byte)30,(byte)30);
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write(new byte[]{29, 118, 48, 0, xL, xH, yL, yH});
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write(pixels);
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write(new byte[]{10});
            }*/


            if (companyName!=null && !companyName.equals("")) {
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x11);//normal
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write(companyName+ "\n");

            }


            if (date!=null && !date.equals("")) {
                //Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.LF();
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write("DATE: " + date+ "\n");

            }

            //Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.LF();
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//CENTER
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write(activity.getResources().getString(R.string.print_line2inch)+ "\n");
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.LF();

            double grandTotal=0.0;
            for (int i=0;i<headerList.size();i++){
                // Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.LF();
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//CENTER
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write( headerList.get(i)+ "\n");
                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write("-----------------------"+ "\n");
                int flag=0;
                double totalPrice=0.0;
                for (Map.Entry<String, List<DailyReportItemData>> entry : childList.entrySet()) {
                    for (DailyReportItemData item:entry.getValue()){
                        if (headerList.get(i).equals(item.getItemCategoryName())){
                            flag=1;
                            totalPrice = totalPrice + item.getItemTotalAmountInTax();
                            if (item.getItemName() != null)
                                itemName = item.getItemName();

                            int itemNameSpaceStringLenght = 0;
                            for (int j=0;j<itemNameSpaceStringLenght;j++){
                                itemName=itemName+" ";
                            }


                            itemAmtInclTax = String.format("%.2f", (item.getItemTotalAmountInTax()));
                            //itemAmtInclTax = String.format("%.2f", (item.getAmtinclusivetax()));

                            int amtSpaceStringLenght1=8-itemAmtInclTax.length();
                            for (int j=0;j<amtSpaceStringLenght1;j++){
                                itemAmtInclTax=" "+itemAmtInclTax;
                            }


                            itemQty = "" + item.getItemQuantity();
                            int amtSpaceStringLenght2 = 4-itemQty.length();
                            for (int j=0;j<amtSpaceStringLenght2;j++){
                                itemQty=itemQty+" ";
                            }
                            String itemname="";
                            if (itemName.length()>18){
                                itemname = dailyReportBreakLines(itemName,18,itemQty,itemAmtInclTax);
                                //Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.LF();
                                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte)0);//CENTER
                                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write( itemname+ "\n");
                            }else{
                                itemname=padRight(itemName, 18);
                                //Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.LF();
                                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte)0);//CENTER
                                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                                Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write( itemname+" "+itemQty+" "+itemAmtInclTax+ "\n");
                            }
                        }

                    }
                    if (entry.getValue().size() == 0) {
                        headerList.remove(entry.getKey());
                    }



                }
                if (totalPrice!=0.0  && flag == 1){
                    grandTotal+=totalPrice;
                    Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
                    Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write(activity.getResources().getString(R.string.print_line2inch)+ "\n");

                    String price = String.format("%.2f",totalPrice);
                    if (price!=null){
                        int amtSpaceStringLenght=8-price.length();
                        for (int j=0;j<amtSpaceStringLenght;j++){
                            price=" "+price;
                        }
                    }
                    Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 2);//LEFT
                    Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write("  Total : " + price +"\n" );

                    Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
                    Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write(activity.getResources().getString(R.string.print_line2inch)+ "\n");

                }

            }
            String grandTotalPrice = String.format("%.2f",grandTotal);
            if (grandTotalPrice!=null){
                int amtSpaceStringLenght=8-grandTotalPrice.length();
                for (int j=0;j<amtSpaceStringLenght;j++){
                    grandTotalPrice=" "+grandTotalPrice;
                }
            }
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 2);//LEFT
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write("  GrandTotal : " + grandTotalPrice +"\n" );




            //Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.LF();
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write(activity.getResources().getString(R.string.print_star2inch)+ "\n");

            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetLineSpacing((byte)30);
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write("Thank You...!"+ "\n");
            //Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.LF();
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetLineSpacing((byte)30);
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write("Designed & Powered by Hiaccounts"+ "\n");
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.LF();
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.PartialCutPaper();
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.LF();
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.BT_Write(activity.getResources().getString(R.string.print_line2inch)+ "\n");
            Activity_PrintList_DailyReport.BLUETOOTH_PRINTER.LF();


        }
        return true;

    }
    public static boolean printInvoicePrintlist2Inch(Context context, RestraSaveCheckoutData saleInoviceData,Bitmap bitmap) {

        if (Activity_PrintList.BLUETOOTH_PRINTER.IsNoConnection()) {
            return false;
        }

        if (saleInoviceData == null) {
            return false;
        } else {
            String serialNo= "";
            String tokenNo = "";
            String companyName = "";
            String companyTelNumber = "";
            String companyFaxNumber = "";
            String companyGSTNumber = "";
            String companyAddress="";
            String date = "", time = "", invoiceNo = "", invoiceCustomerName = "";

            if (saleInoviceData.getDate() != null) {
                date = saleInoviceData.getRestaurantDate();
            }
           /* if (saleInoviceData.getTime() != null) {
                time = saleInoviceData.getTime();
            }*/
            if (saleInoviceData.getSiData() != null) {
                if (saleInoviceData.getSiData().getSiNo() != null)
                    invoiceNo = saleInoviceData.getSiData().getSiNo();
                if (saleInoviceData.getSiData().getCutomerName() != null){}
                //invoiceCustomerName = saleInoviceData.getSiData().getUserName();
                if (saleInoviceData.getSiData().getTokenNo()!=null){

                    tokenNo=""+saleInoviceData.getSiData().getTokenNo();
                }
            }


            in.hiaccounts.hinext.restaurant.model.checkout.CompanyData companyData = saleInoviceData.getCompanyData();
            if (companyData != null) {
                if (saleInoviceData.getCompanyData().getCompanyName() != null) {
                    companyName = saleInoviceData.getCompanyData().getCompanyName().toUpperCase();
                }
                if (companyData.getAddress() != null)
                    companyAddress = companyData.getAddress();
                if (companyData.getPhone() != null)
                    companyTelNumber = companyData.getPhone();
                if (companyData.getFax() != null)
                    companyFaxNumber = companyData.getFax();
                if (companyData.getRegisterNo() != null)
                    companyGSTNumber = companyData.getRegisterNo();
            }


            //LF = Line feed
            Activity_PrintList.BLUETOOTH_PRINTER.Begin();
            Activity_PrintList.BLUETOOTH_PRINTER.LF();
            // Activity_PrintList.BLUETOOTH_PRINTER.LF();
        /*    Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("");
            Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30); */   //30 * 0.125mm
        /*    if (bitmap!=null) {
                //Activity_PrintList.BLUETOOTH_PRINTER.printByteData(logoByteArray);

                // Activity_PrintList.BLUETOOTH_PRINTER.printImage(Bitmap.createScaledBitmap(bitmap, 200, 200, false));
                Bitmap newBm = BitmapConvertUtil.decodeSampledBitmapFromBitmap(bitmap, 100);
                byte xL = (byte) (((newBm.getWidth() - 1) / 8 + 1) % 256);
                byte xH = (byte) (((newBm.getWidth() - 1) / 8 + 1) / 256);
                byte yL = (byte) (newBm.getHeight() % 256);
                byte yH = (byte) (newBm.getHeight() / 256);
                byte[] pixels = BitmapConvertUtil.convert(newBm);

                Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_PrintList.BLUETOOTH_PRINTER.printImage(Bitmap.createScaledBitmap(bitmap, 150, 150, false));

                *//*Activity_PrintList.BLUETOOTH_PRINTER.BT_Write(new byte[]{29, 118, 118, 10, xL, xH, yL, yH});

                Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//CENTER
                Activity_PrintList.BLUETOOTH_PRINTER.BT_Write(pixels);*//*
            }*/


            if (companyName!=null && !companyName.equals("")) {
                Activity_PrintList.BLUETOOTH_PRINTER.LF();
                Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_PrintList.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x11);//normal
                Activity_PrintList.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
                Activity_PrintList.BLUETOOTH_PRINTER.BT_Write(companyName +"\n");
                //  Activity_PrintList.BLUETOOTH_PRINTER.LF();
            }


            if (companyAddress!=null && !companyAddress.equals("")) {
                Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_PrintList.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_PrintList.BLUETOOTH_PRINTER.BT_Write( companyAddress +"\n");
                // Activity_PrintList.BLUETOOTH_PRINTER.LF();
            }

            if (companyTelNumber!=null && !companyTelNumber.equals("")) {
                Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_PrintList.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("Phone : " + companyTelNumber +"\n");
                // Activity_PrintList.BLUETOOTH_PRINTER.LF();
            }

            if (companyGSTNumber!=null && !companyGSTNumber.equals("")) {
                Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_PrintList.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("GSTIN: " + companyGSTNumber +"\n");
                // Activity_PrintList.BLUETOOTH_PRINTER.LF();
            }
            if (date!=null && !date.equals("")) {
                // Activity_PrintList.BLUETOOTH_PRINTER.LF();
                Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_PrintList.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("DATE: " + date +"\n");
                //  Activity_PrintList.BLUETOOTH_PRINTER.LF();
            }
            if (invoiceNo!=null && !invoiceNo.equals("")) {
                Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_PrintList.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("Invoice No: " + invoiceNo +"\n");
                //  Activity_PrintList.BLUETOOTH_PRINTER.LF();
            }
            // String customerName="customerA";
            if (saleInoviceData.getSiData().getCutomerName()!=null && !saleInoviceData.getSiData().getCutomerName().equals("")){
                //Activity_PrintList.BLUETOOTH_PRINTER.LF();
                Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_PrintList.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_PrintList.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
                Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("Customer: "+saleInoviceData.getSiData().getCutomerName() +"\n");
            }
            if (saleInoviceData.getSiData().getCustomerNo()!=null && !saleInoviceData.getSiData().getCustomerNo().equals("")){
                //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_PrintList.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_PrintList.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
                Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("Phone: "+saleInoviceData.getSiData().getCustomerNo() +"\n");
            }
            if (saleInoviceData.getSiData().getCustomerAddress()!=null && !saleInoviceData.getSiData().getCustomerAddress().equals("")){
                //Activity_RestraPayment1.BLUETOOTH_PRINTER.LF();
                Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_PrintList.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_PrintList.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
                Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("Address: "+saleInoviceData.getSiData().getCustomerAddress() +"\n");
            }
            //String waiterName="NA";
            if (saleInoviceData.getSiData().getEmployeeName()!=null && !saleInoviceData.getSiData().getEmployeeName().equals("")){
                // Activity_PrintList.BLUETOOTH_PRINTER.LF();
                Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_PrintList.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("Steward: "+saleInoviceData.getSiData().getEmployeeName()+ "\n");
            }

            if (saleInoviceData.getSiData().getTable()!=null && !saleInoviceData.getSiData().getTable().equals("")){
                //Activity_PrintList.BLUETOOTH_PRINTER.LF();
                Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_PrintList.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_PrintList.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
                Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("Table No: "+saleInoviceData.getSiData().getTable() +"\n");
            }

            if (saleInoviceData.getSiData().getEmail()!=null && !saleInoviceData.getSiData().getEmail().equals("")){
                // Activity_PrintList.BLUETOOTH_PRINTER.LF();
                Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_PrintList.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_PrintList.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
                Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("Token: "+saleInoviceData.getSiData().getEmail() +"\n");
            }
            //Activity_PrintList.BLUETOOTH_PRINTER.LF();
            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_PrintList.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line2inch)+ "\n");

            double amtExTax = 0,amtIncTax= 0,totalAmount=0.00;
            if (saleInoviceData.getSiData() != null) {
                String  itemName = "";
                String itemUnitPrice = "";
                String taxPercentage = "";
                String itemQty = "";
                String itemAmtInclTax = "";
                String itemTax = "";
                String qtyHeader = "Qty";
                int qtyHeaderStringLenght = 4-qtyHeader.length();
                for (int j=0;j<qtyHeaderStringLenght;j++){
                    qtyHeader=" "+qtyHeader;
                }
                String priceHeader = "Price";
                int priceSpaceStringLenght = 8-priceHeader.length();
                for (int j=0;j<priceSpaceStringLenght;j++){
                    priceHeader=" "+priceHeader;
                }

                String amountHeader = "Amount";
                int amountSpaceStringLenght = 8-amountHeader.length();
                for (int j=0;j<amountSpaceStringLenght;j++){
                    amountHeader=" "+amountHeader;
                }

                String itemHeader = "Item";
                String itemheader=padRight(itemHeader, 8);
                if (saleInoviceData.getSiData().getSelectedItemsList() != null) {

                    // Activity_PrintList.BLUETOOTH_PRINTER.LF();
                    Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                    Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);
                    Activity_PrintList.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);
                    Activity_PrintList.BLUETOOTH_PRINTER.BT_Write(itemheader+" " +priceHeader+" "+qtyHeader+" "+amountHeader+"\n");
                    // Activity_PrintList.BLUETOOTH_PRINTER.LF();
                    //Activity_PrintList.BLUETOOTH_PRINTER.LF();
                    Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//CENTER
                    Activity_PrintList.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line2inch)+ "\n");


                    for (int i = 0; i < saleInoviceData.getSiData().getSelectedItemsList().size(); i++) {
                        RestraCheckoutItem item = saleInoviceData.getSiData().getSelectedItemsList().get(i);
                        if (item != null) {


                            itemUnitPrice = String.format("%.2f", item.getUnitPrice());

                            int amtSpaceStringLenght = 8-itemUnitPrice.length();
                            for (int j=0;j<amtSpaceStringLenght;j++){
                                itemUnitPrice=" "+itemUnitPrice;
                            }

                            if (item.getItemName() != null)
                                itemName = item.getItemName();

                            int itemNameSpaceStringLenght = 0;
                            for (int j=0;j<itemNameSpaceStringLenght;j++){
                                itemName=itemName+" ";
                            }

                            //taxPercentage = String.format("%.2f", item.getGstItemTax());
                            totalAmount +=item.getQty()*item.getUnitPrice();
                            itemAmtInclTax = String.format("%.2f", (item.getQty()*item.getUnitPrice()));
                            //itemAmtInclTax = String.format("%.2f", (item.getAmtinclusivetax()));

                            int amtSpaceStringLenght1=8-itemAmtInclTax.length();
                            for (int j=0;j<amtSpaceStringLenght1;j++){
                                itemAmtInclTax=" "+itemAmtInclTax;
                            }


                            itemQty = "" + item.getQty();
                            int amtSpaceStringLenght2 = 4-itemQty.length();
                            for (int j=0;j<amtSpaceStringLenght2;j++){
                                itemQty=""+itemQty;
                            }
                            amtExTax=amtExTax+(item.getQty()*item.getUnitPrice());

                            Double price = (item.getAmtinclusivetax())/(item.getQty());

                            String priceVal = String.format("%.2f",item.getUnitPrice());


                            //Double amount = item.getUnitPrice()*item.getQty();
                            // String amountTot = String.format("%.2f",amount);



                            int amtStringLenght2 = 8-priceVal.length();
                            for (int j=0;j<amtStringLenght2;j++){
                                priceVal=priceVal+" ";
                            }

                            String itemname="";
                            if (itemName.length()>11){
                                itemname = customerBreakLines(itemName,11,priceVal,itemQty,itemAmtInclTax);
                                Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//CENTER
                                Activity_PrintList.BLUETOOTH_PRINTER.BT_Write( itemname +  "\n");
                            }else{
                                itemname=padRight(itemName, 11);
                                Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//CENTER
                                Activity_PrintList.BLUETOOTH_PRINTER.BT_Write(itemname+" "+priceVal+" "+itemQty+" "+itemAmtInclTax + "\n");
                            }


                        }


                    }
                }
            }
            //Activity_PrintList.BLUETOOTH_PRINTER.LF();
            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line2inch));
            String discount="";

            if (saleInoviceData.getSiData().getDiscountAmtInPercentage()!=0.0){
                discount=String.format("%.2f",saleInoviceData.getSiData().getDiscountAmtInPercentage());

            }else{
                discount=String.format("%.2f",0.0d);
            }
            String totalCheckOutAmt=String.format("%.2f",totalAmount);
            String totalTaxAmt=String.format("%.2f",saleInoviceData.getSiData().getTotalTaxAmt());
            String paidAmount = String.format("%.2f",saleInoviceData.getSiData().getTotalCheckOutamt());
            String discountAmtPercentage="";
            discountAmtPercentage = String.format("%.2f",(totalAmount*saleInoviceData.getSiData().getDiscountAmtInPercentage())/100);
            if (discountAmtPercentage!=null){
                int discountSpaceStringLenght=8-discountAmtPercentage.length();
                for (int j=0;j<discountSpaceStringLenght;j++){
                    discountAmtPercentage=" "+discountAmtPercentage;
                }
            }else {
                discountAmtPercentage = String.format("%.2f",saleInoviceData.getSiData().getDiscountAmtInPercentage());
                int discountSpaceStringLenght=8-discountAmtPercentage.length();
                for (int j=0;j<discountSpaceStringLenght;j++){
                    discountAmtPercentage=" "+discountAmtPercentage;
                }
            }
        /*    if (saleInoviceData.getSiData().getDiscountAmtInPercentage()!=null && !saleInoviceData.getSiData().getDiscountAmtInPercentage().equals("")){
                discountAmtPercentage = String.valueOf(saleInoviceData.getSiData().getDiscountAmtInPercentage());
                if (discountAmtPercentage!=null){
                    int discountSpaceStringLenght=8-discountAmtPercentage.length();
                    for (int j=0;j<discountSpaceStringLenght;j++){
                        discountAmtPercentage=" "+discountAmtPercentage;
                    }
                }


            }else {
                discountAmtPercentage="0.00";
            }*/


            if (discountAmtPercentage!=null){
                int discountSpaceStringLenght=8-discountAmtPercentage.length();
                for (int j=0;j<discountSpaceStringLenght;j++){
                    discountAmtPercentage=" "+discountAmtPercentage;
                }
            }


            if (totalCheckOutAmt!=null){
                int amtSpaceStringLenght=8-totalCheckOutAmt.length();
                for (int j=0;j<amtSpaceStringLenght;j++){
                    totalCheckOutAmt=" "+totalCheckOutAmt;
                }
            }


            if (totalTaxAmt!=null){
                int taxSpaceStringLenght=8-totalTaxAmt.length();
                for (int j=0;j<taxSpaceStringLenght;j++){
                    totalTaxAmt=" "+totalTaxAmt;
                }
            }


            String subTotal ="Sub Total:";
            String subTotalName=padRight(subTotal, 24);
            Activity_PrintList.BLUETOOTH_PRINTER.LF();
            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write(subTotalName + totalCheckOutAmt +"\n" );
            if (discount!=null && !discount.equals("0.00")){
                String discountHeader ="Discount:("+discount+"%): ";
                String discountHeaderName=padRight(discountHeader, 24);
                // Activity_PrintList.BLUETOOTH_PRINTER.LF();
                Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                Activity_PrintList.BLUETOOTH_PRINTER.BT_Write(discountHeaderName + discountAmtPercentage +"\n");

            }
            // Activity_PrintList.BLUETOOTH_PRINTER.LF();
            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line2inch)+"\n");
            String taxPercentageSplit ="",netBill="";
            Double netBalance =0.00;
            if (saleInoviceData.getSiData().getSelectedItemsList()!=null && saleInoviceData.getSiData().getSelectedItemsList().size()>0){
                int count = saleInoviceData.getSiData().getSelectedItemsList().size();
                Double taxPercentage = saleInoviceData.getSiData().getTotalTaxAmt()/2;
                taxPercentageSplit = String.format("%.2f",taxPercentage);
            }else{
                taxPercentageSplit = "0.00";
            }

            if (totalAmount!=0.00){
                netBalance = totalAmount - Double.valueOf(discountAmtPercentage);
                netBill =  String.format("%.2f",netBalance);
                if (netBill!=null){
                    int taxPerSpaceStringLenght=8-netBill.length();
                    for (int j=0;j<taxPerSpaceStringLenght;j++){
                        netBill=" "+netBill;
                    }
                }
            }

            if (taxPercentageSplit!=null){
                int taxPerSpaceStringLenght=8-taxPercentageSplit.length();
                for (int j=0;j<taxPerSpaceStringLenght;j++){
                    taxPercentageSplit=" "+taxPercentageSplit;
                }
            }

            //Activity_PrintList.BLUETOOTH_PRINTER.LF();
            String netTotalHeader ="Net Total : ";
            String netTotalHeaderName=padRight(netTotalHeader, 24);
            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write( netTotalHeaderName+ netBill +"\n");
            if (taxPercentageSplit!=null){
                Double taxPer=0.00;
                if (saleInoviceData.getSiData().getTaxWiseSummaryList()!=null&& saleInoviceData.getSiData().getTaxWiseSummaryList().size()>0){
                    taxPer = saleInoviceData.getSiData().getTaxWiseSummaryList().get(0).getTaxPercent()/2;
                    String cgstHeader ="CGST("+taxPer +"%) :";
                    String cgstHeaderName=padRight(cgstHeader, 24);
                    String sgstHeader ="SGST ("+taxPer +"%):";
                    String sgstHeaderName=padRight(sgstHeader, 24);
                    //Activity_PrintList.BLUETOOTH_PRINTER.LF();
                    Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                    Activity_PrintList.BLUETOOTH_PRINTER.BT_Write( cgstHeaderName+ taxPercentageSplit +"\n");
                    //Activity_PrintList.BLUETOOTH_PRINTER.LF();
                    Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                    Activity_PrintList.BLUETOOTH_PRINTER.BT_Write( sgstHeaderName+ taxPercentageSplit +"\n");



                }

            }

            String roundingOffValue="";
            Double roundval=0.00;
            if (saleInoviceData.getSiData().getRoundingOffValue()!=null && !saleInoviceData.getSiData().getRoundingOffValue().equals("0.0")){
                roundval = Double.valueOf(saleInoviceData.getSiData().getRoundingOffValue());

                if(roundval*1<0){
                    roundingOffValue = String.valueOf(roundval*-1);
                }else{
                    roundingOffValue = String.valueOf((( roundval*-1))/1);
                }


                if (roundingOffValue!=null){
                    int roundingOffValueSpaceStringLenght=8-roundingOffValue.length();
                    for (int j=0;j<roundingOffValueSpaceStringLenght;j++){
                        roundingOffValue=" "+roundingOffValue;
                    }
                }

                if (roundingOffValue!=null){

                    // Activity_PrintList.BLUETOOTH_PRINTER.LF();
                    String roundingOffHeader ="RoundingOffValue: ";
                    String roundingOffHeaderName=padRight(roundingOffHeader, 24);
                    Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                    Activity_PrintList.BLUETOOTH_PRINTER.BT_Write( roundingOffHeaderName + roundingOffValue +"\n");

                }
            }



            //Activity_PrintList.BLUETOOTH_PRINTER.LF();
            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line2inch)+"\n");


            //Activity_PrintList.BLUETOOTH_PRINTER.LF();
          /* Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 2);//LEFT
           Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("  Total GST : " + totalTaxAmt +"\n");

        */
            Double grandTotal=0.00;
           /* if (paidAmount!=null){
                grandTotal = Double.valueOf(paidAmount);
            }*/


            if (paidAmount!=null){
                int paidAmtSpaceStringLenght=8-paidAmount.length();
                for (int j=0;j<paidAmtSpaceStringLenght;j++){
                    paidAmount=" "+paidAmount;
                }
            }
            //Activity_PrintList.BLUETOOTH_PRINTER.LF();
            String grandTotalHeader ="Grand Total : ";
            String grandTotalHeaderName=padRight(grandTotalHeader, 24);
            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write( grandTotalHeaderName+ paidAmount  +"\n");
            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line2inch)+"\n");
            //Activity_PrintList.BLUETOOTH_PRINTER.LF();
            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("Payment Method:" + "\n");
            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line2inch)+"\n");
            if (saleInoviceData.getSiData().getCashPayment() !=null && saleInoviceData.getSiData().getCashPayment().getTotalCPAmountTendered()!=0.00){
                String cashAmount="";
                if (saleInoviceData.getSiData().getCashPayment().getTotalCPAmountTendered()!=0.00 ){
                    cashAmount = String.format("%.2f",saleInoviceData.getSiData().getCashPayment().getTotalCPAmountTendered());

                    if (cashAmount!=null){
                        int paidAmtSpaceStringLenght=8-cashAmount.length();
                        for (int j=0;j<paidAmtSpaceStringLenght;j++){
                            cashAmount=" "+cashAmount;
                        }
                    }

                    String cashAmountHeader = "Cash :";
                    String cashAmountHeaderName=padRight(cashAmountHeader, 24);
                    Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                    Activity_PrintList.BLUETOOTH_PRINTER.BT_Write( cashAmountHeaderName+ cashAmount  +"\n");


                }
            }
            if (saleInoviceData.getSiData().getBankPayment() !=null && saleInoviceData.getSiData().getBankPayment().getMultiBankPaymentList().size()>0){
                if (saleInoviceData.getSiData().getBankPayment().getPaymentTypeCash()!=null && !saleInoviceData.getSiData().getBankPayment().getPaymentTypeCash().equals("")){
                    Double totalBank=0.00;
                    String bank="";
                    for (MultiBankPaymentList bankPaymentList:saleInoviceData.getSiData().getBankPayment().getMultiBankPaymentList()){
                        if (bankPaymentList.getAmount()!=0.00 ){
                            totalBank+=bankPaymentList.getAmount();
                        }
                    }
                    bank = String.format("%.2f",totalBank);
                    if (bank!=null){
                        int paidAmtSpaceStringLenght=8-bank.length();
                        for (int j=0;j<paidAmtSpaceStringLenght;j++){
                            bank=" "+bank;
                        }
                    }
                    String bankHeader = saleInoviceData.getSiData().getBankPayment().getPaymentTypeCash();
                    String bankHeaderHeaderName=padRight(bankHeader, 24);
                    Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                    Activity_PrintList.BLUETOOTH_PRINTER.BT_Write(bankHeaderHeaderName + bank +"\n");

                    for (MultiBankPaymentList bankPaymentList:saleInoviceData.getSiData().getBankPayment().getMultiBankPaymentList()){
                        if (bankPaymentList.getBankName()!=null && !bankPaymentList.getBankName().equals("")){
                            String  bankname = "Bank Name ";
                            String banknameHeaderName=padRight(bankname, 15);

                            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write( banknameHeaderName +": "+ bankPaymentList.getBankName()  +"\n");

                        }
                        if (bankPaymentList.getDate()!=null && !bankPaymentList.getDate().equals("")){
                            String  bankdate = "Date ";
                            String bankdateHeaderName=padRight(bankdate, 15);
                            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                            String str = bankPaymentList.getDate();

                            try {
                                long timestamp = Long.parseLong(str);
                                Date d = new Date(timestamp);
                                final TimeZone utc = TimeZone.getTimeZone("UTC");
                                SimpleDateFormat sf = new SimpleDateFormat("dd-MMM-yyyy @hh:mm a");
                                //sf.setTimeZone(utc);
                                Activity_PrintList.BLUETOOTH_PRINTER.BT_Write( bankdateHeaderName+": "+ sf.format(d)  +"\n");

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                        if (bankPaymentList.getAmount()!=0.00 ){
                            String  bankAmount = "Amount ";
                            String bankAmountHeaderName=padRight(bankAmount, 15);
                            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write( bankAmountHeaderName+": "+ bankPaymentList.getAmount()  +"\n");

                        }
                    }
                }

            }
            if (saleInoviceData.getSiData().getCreditPayment() !=null && saleInoviceData.getSiData().getCreditPayment().getCardPaymentList().size()>0){
                if (saleInoviceData.getSiData().getCreditPayment().getPaymentTypeCash()!=null && !saleInoviceData.getSiData().getCreditPayment().getPaymentTypeCash().equals("")){

                    Double totalCard=0.00;
                    String card="";
                    for (CardPaymentList cardPaymentList:saleInoviceData.getSiData().getCreditPayment().getCardPaymentList()){
                        if (cardPaymentList.getCardAmt()!=0.00 ){
                            totalCard+=cardPaymentList.getCardAmt();
                        }
                    }
                    card = String.format("%.2f",totalCard);
                    if (card!=null){
                        int paidAmtSpaceStringLenght=8-card.length();
                        for (int j=0;j<paidAmtSpaceStringLenght;j++){
                            card=" "+card;
                        }
                    }
                    String cardHeader = saleInoviceData.getSiData().getCreditPayment().getPaymentTypeCash();
                    String cardHeaderHeaderName=padRight(cardHeader, 24);
                    Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                    Activity_PrintList.BLUETOOTH_PRINTER.BT_Write(cardHeaderHeaderName + card +"\n");

                    for (CardPaymentList cardPaymentList:saleInoviceData.getSiData().getCreditPayment().getCardPaymentList()){
                        if (cardPaymentList.getCardNo()!=null && !cardPaymentList.getCardNo().equals("")){
                            String  cardNo = "Card No ";
                            String cardHeaderName=padRight(cardNo, 15);
                            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write( cardHeaderName+": "+ cardPaymentList.getCardNo()  +"\n");

                        }
                        if (cardPaymentList.getCardDate()!=null && !cardPaymentList.getCardDate().equals("")){
                            String  cardDate = "Date ";
                            String cardHeaderName=padRight(cardDate, 15);
                            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss.SSSXXX");
                            try {
                                Date datecard = dateFormat.parse(cardPaymentList.getCardDate());//You will get date object relative to server/client timezone wherever it is parsed
                                DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy @hh:mm a"); //If you need time just put specific format for time like 'HH:mm:ss'
                                String dateStr = formatter.format(datecard);
                                Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                                Activity_PrintList.BLUETOOTH_PRINTER.BT_Write( cardHeaderName+": "+ dateStr +"\n");

                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                        if (cardPaymentList.getCardAmt()!=0.00){
                            String  cardAmt = "Card Amount ";
                            String cardHeaderName=padRight(cardAmt, 15);
                            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write( cardHeaderName+": "+ cardPaymentList.getCardAmt()  +"\n");

                        }

                    }
                }

            }
            if (saleInoviceData.getSiData().getVoucherPayment() !=null && saleInoviceData.getSiData().getVoucherPayment().getMultiVoucherPayments().size()>0){
                if (saleInoviceData.getSiData().getVoucherPayment().getPaymentTypeCash()!=null && saleInoviceData.getSiData().getVoucherPayment().getPaymentTypeCash().equals("Discount Voucher")){
                    if (saleInoviceData.getSiData().getVoucherPayment().getMultiVoucherPayments().get(0).getVoucherNo()!=null && !saleInoviceData.getSiData().getVoucherPayment().getMultiVoucherPayments().get(0).getVoucherNo().equals("Discount Given")){
                        Double totalVoucher=0.00;
                        String voucher="";
                        for (MultiVoucherPayment voucherPayment:saleInoviceData.getSiData().getVoucherPayment().getMultiVoucherPayments()){
                            if (voucherPayment.getVoucherAmt()!=0.00 ){
                                totalVoucher+=voucherPayment.getVoucherAmt();
                            }
                        }
                        voucher = String.format("%.2f",totalVoucher);
                        if (voucher!=null){
                            int paidAmtSpaceStringLenght=8-voucher.length();
                            for (int j=0;j<paidAmtSpaceStringLenght;j++){
                                voucher=" "+voucher;
                            }
                        }
                        String voucherHeader = saleInoviceData.getSiData().getVoucherPayment().getPaymentTypeCash();
                        String voucherHeaderName=padRight(voucherHeader, 24);
                        Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                        Activity_PrintList.BLUETOOTH_PRINTER.BT_Write(voucherHeaderName + voucher +"\n");

                    }
                    for (MultiVoucherPayment voucherPayment:saleInoviceData.getSiData().getVoucherPayment().getMultiVoucherPayments()){
                        // if (voucherPayment.getVoucherNo()!=null && !voucherPayment.getVoucherNo().equals("")){
                        if (voucherPayment.getVoucherNo()!=null && !voucherPayment.getVoucherNo().equals("")){
                            String  voucher = "Voucher No ";
                            String voucherHeaderName=padRight(voucher, 15);
                            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write( voucherHeaderName+": "+ voucherPayment.getVoucherNo()  +"\n");

                        }
                        if (voucherPayment.getVoucherAmt()!=0.00){
                            String  voucher = "Voucher Amount ";
                            String voucherHeaderName=padRight(voucher, 15);
                            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write( voucherHeaderName+": "+ voucherPayment.getVoucherAmt()  +"\n");

                        }
                        //  }




                    }
                }

            }
            // Activity_PrintList.BLUETOOTH_PRINTER.LF();
            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_star2inch)  +"\n");
            Activity_PrintList.BLUETOOTH_PRINTER.LF();

            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
            Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte)30);
            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("Thank You...! Visit again" +"\n");
            // Activity_PrintList.BLUETOOTH_PRINTER.LF();
            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
            Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte)30);
            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("Designed & Powered by Hiaccounts" +"\n");
            Activity_PrintList.BLUETOOTH_PRINTER.LF();
            Activity_PrintList.BLUETOOTH_PRINTER.PartialCutPaper();


        }
        return true;

    }

    public static boolean printInvoiceTableOrder2Inch(Context context, RestraCheckoutData saleInoviceData, Bitmap bitmap, String tableName, in.hiaccounts.hinext.controlpanel.model.company_setup.compnayinformation.CompanyData companyData, List<RestraCheckoutItem> orderItemsList, Boolean notificationAppend) {
        if (RestuarantActivity.BLUETOOTH_PRINTER.IsNoConnection()) {
            return false;
        }

        if (saleInoviceData == null) {
            return false;
        } else {
            String companyName = "";
            String companyTelNumber = "";
            String companyFaxNumber = "";
            String companyGSTNumber = "";
            String companyAddress="";
            String  date ="", invoiceNo = "", invoiceCustomerName = "";


            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date dateFo = new Date();
            date = dateFormat.format(dateFo);

            if (companyData != null) {
                if (companyData.getCompanyName() != null) {
                    companyName = companyData.getCompanyName().toUpperCase();
                }
                if (companyData.getAddress() != null)
                    companyAddress = companyData.getAddress();
                if (companyData.getPhone() != null)
                    companyTelNumber = companyData.getPhone();
                if (companyData.getFax() != null)
                    companyFaxNumber = companyData.getFax();
                if (companyData.getRegisterNo() != null)
                    companyGSTNumber = companyData.getRegisterNo();
            }


            //LF = Line feed
            RestuarantActivity.BLUETOOTH_PRINTER.Begin();
            RestuarantActivity.BLUETOOTH_PRINTER.LF();
            //   RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
            //RestuarantActivity.BLUETOOTH_PRINTER.BT_Write("");
            // RestuarantActivity.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
/*            if (bitmap!=null) {
                //RestuarantActivity.BLUETOOTH_PRINTER.printByteData(logoByteArray);

                // RestuarantActivity.BLUETOOTH_PRINTER.printImage(Bitmap.createScaledBitmap(bitmap, 200, 200, false));
             *//*   Bitmap newBm = BitmapConvertUtil.decodeSampledBitmapFromBitmap(bitmap, 100);
                byte xL = (byte) (((newBm.getWidth() - 1) / 8 + 1) % 256);
                byte xH = (byte) (((newBm.getWidth() - 1) / 8 + 1) / 256);
                byte yL = (byte) (newBm.getHeight() % 256);
                byte yH = (byte) (newBm.getHeight() / 256);
                byte[] pixels = BitmapConvertUtil.convert(newBm);

                RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                RestuarantActivity.BLUETOOTH_PRINTER.printImage(Bitmap.createScaledBitmap(bitmap, 150, 150, false));
*//*
                *//*RestuarantActivity.BLUETOOTH_PRINTER.BT_Write(new byte[]{29, 118, 118, 10, xL, xH, yL, yH});

                RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//CENTER
                RestuarantActivity.BLUETOOTH_PRINTER.BT_Write(pixels);*//*

                Bitmap newBm = BitmapConvertUtil.decodeSampledBitmapFromBitmap(bitmap, 100);
                byte xL = (byte)(((newBm.getWidth() - 1) / 8 + 1) % 256);
                byte xH = (byte)(((newBm.getWidth() - 1) / 8 + 1) / 256);
                byte yL = (byte)(newBm.getHeight() % 256);
                byte yH = (byte)(newBm.getHeight() / 256);
                Log.d("HsBluetoothPrintDriver", "xL = " + xL);
                Log.d("HsBluetoothPrintDriver", "xH = " + xH);
                Log.d("HsBluetoothPrintDriver", "yL = " + yL);
                Log.d("HsBluetoothPrintDriver", "yH = " + yH);
                byte[] pixels = BitmapConvertUtil.convert(newBm);
                RestuarantActivity.BLUETOOTH_PRINTER.SetLeftStartSpacing((byte)30,(byte)30);
                RestuarantActivity.BLUETOOTH_PRINTER.BT_Write(new byte[]{29, 118, 48, 0, xL, xH, yL, yH});
                RestuarantActivity.BLUETOOTH_PRINTER.BT_Write(pixels);
                RestuarantActivity.BLUETOOTH_PRINTER.BT_Write(new byte[]{10});
            }*/
            if (saleInoviceData.getTableName()!=null && !saleInoviceData.getTableName().equals("")){
                RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                RestuarantActivity.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                RestuarantActivity.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                RestuarantActivity.BLUETOOTH_PRINTER.BT_Write("Table: "+saleInoviceData.getTableName()+ "\n");
            }
            if (saleInoviceData.getCustomerName()!=null && !saleInoviceData.getCustomerName().equals("")) {
                //RestuarantActivity.BLUETOOTH_PRINTER.LF();
                RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                RestuarantActivity.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                RestuarantActivity.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                RestuarantActivity.BLUETOOTH_PRINTER.BT_Write("CustomerName: " + saleInoviceData.getCustomerName()+ "\n");

            }
            //String waiterName="NA";
            if (!notificationAppend && saleInoviceData.getWaiterName()!=null && !saleInoviceData.getWaiterName().equals("")){
                RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                RestuarantActivity.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                RestuarantActivity.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                RestuarantActivity.BLUETOOTH_PRINTER.BT_Write("Steward: "+saleInoviceData.getWaiterName()+ "\n");
            }
            if (saleInoviceData.getTokenNo()!=null && !saleInoviceData.getTokenNo().equals("")) {
                //RestuarantActivity.BLUETOOTH_PRINTER.LF();
                RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                RestuarantActivity.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                RestuarantActivity.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                RestuarantActivity.BLUETOOTH_PRINTER.BT_Write("Token: " + saleInoviceData.getTokenNo()+ "\n");

            }



            if (date!=null && !date.equals("")) {
                //RestuarantActivity.BLUETOOTH_PRINTER.LF();
                RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                RestuarantActivity.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                RestuarantActivity.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                RestuarantActivity.BLUETOOTH_PRINTER.BT_Write("DATE: " + date+ "\n");

            }
            //RestuarantActivity.BLUETOOTH_PRINTER.LF();
            RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
            RestuarantActivity.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line2inch)+ "\n");
            //RestuarantActivity.BLUETOOTH_PRINTER.LF();

            double amtExTax = 0,amtIncTax= 0;
            if (orderItemsList != null) {
                String  itemName = "",itemDesc="";
                String itemUnitPrice = "";
                String itemQty = "";
                String itemAmtInclTax = "";
                String itemTax = "";

                String itemDetails = "Item ";
                String itemdetails=padRight(itemDetails, 27);


                if (orderItemsList != null) {
                    RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//CENTER
                    RestuarantActivity.BLUETOOTH_PRINTER.BT_Write(itemdetails+" " +"Qty"+"\n");
                    RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                    RestuarantActivity.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line2inch)+ "\n");

                    for (int i = 0; i < orderItemsList.size(); i++) {
                            RestraCheckoutItem item = orderItemsList.get(i);
                            if (item != null) {
                                if (item.getItemName() != null)
                                    itemName = item.getItemName();




                                itemQty = "" + item.getQty();
                                int amtSpaceStringLenght2 = 4-itemQty.length();
                                for (int j=0;j<amtSpaceStringLenght2;j++){
                                    itemQty=" "+itemQty;
                                }

                               // String itemname=padRight(itemName, 15);


                                //String desc = padRight(itemDesc, 25);
                                amtExTax=amtExTax+(item.getQty()*item.getUnitPrice());
                                String   itemname="";

                                if (itemName.length()>24){
                                    itemname = kotBreakLines(itemName,24,itemQty);
                                    RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//CENTER
                                    RestuarantActivity.BLUETOOTH_PRINTER.BT_Write( itemname +  "\n");
                                }else{
                                    String itemname1=padRight(itemName, 24);
                                    RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//CENTER
                                    RestuarantActivity.BLUETOOTH_PRINTER.BT_Write( itemname1 +" "+itemQty + "\n");
                                }



                                if (item.getItemDescription()!=null && !item.getItemDescription().equals("")){
                                    RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//CENTER
                                    RestuarantActivity.BLUETOOTH_PRINTER.BT_Write("[" + item.getItemDescription() + "]"+"\n");
                                }

                            }
                    }
                }
            }

      /*      //RestuarantActivity.BLUETOOTH_PRINTER.LF();
            RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            RestuarantActivity.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_star2inch)+ "\n");

            RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
            RestuarantActivity.BLUETOOTH_PRINTER.SetLineSpacing((byte)30);
            RestuarantActivity.BLUETOOTH_PRINTER.BT_Write("Thank You...!"+ "\n");
            //RestuarantActivity.BLUETOOTH_PRINTER.LF();
            RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
            RestuarantActivity.BLUETOOTH_PRINTER.SetLineSpacing((byte)30);
            RestuarantActivity.BLUETOOTH_PRINTER.BT_Write("Designed & Powered by Hiaccounts"+ "\n");
            RestuarantActivity.BLUETOOTH_PRINTER.LF();*/
            RestuarantActivity.BLUETOOTH_PRINTER.LF();
            RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            RestuarantActivity.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);
            RestuarantActivity.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line2inch)+ "\n");
            RestuarantActivity.BLUETOOTH_PRINTER.LF();
            RestuarantActivity.BLUETOOTH_PRINTER.BT_Write( "\n");
            RestuarantActivity.BLUETOOTH_PRINTER.LF();
            RestuarantActivity.BLUETOOTH_PRINTER.BT_Write( "\n");
            RestuarantActivity.BLUETOOTH_PRINTER.LF();
            RestuarantActivity.BLUETOOTH_PRINTER.BT_Write( "\n");
            RestuarantActivity.BLUETOOTH_PRINTER.CutPaper();


        }
        return true;

    }



    public static String kotBreakLines(String input, int maxLineLength, String itemQty) {

        String[] tokens = input.split(SPLIT_REGEXP);
        StringBuilder output = new StringBuilder(input.length());
        int lineLen = 0;
        boolean flag =true;
        for (int i = 0; i < tokens.length; i++) {
            String word = tokens[i];

                if (lineLen + (word).length() > maxLineLength ) {
                    if (i > 0) {
                        if (flag){
                            output.append(" ");
                            output.append(itemQty);
                            output.append(NEWLINE);
                            flag =false;
                        }else {
                            output.append(NEWLINE);

                        }
                    }
                    lineLen = 0;
                }
                output.append(word);
                lineLen += word.length();

        }
        return output.toString();
    }
    public static String customerBreakLines(String input, int maxLineLength, String priceVal, String qty, String amountInc) {

        String[] tokens = input.split(SPLIT_REGEXP);
        StringBuilder output = new StringBuilder(input.length());
        int lineLen = 0;
        boolean flag =true;
        for (int i = 0; i < tokens.length; i++) {
            String word = tokens[i];

            if (lineLen + (word).length() > maxLineLength ) {
                if (i > 0) {
                    if (flag){
                        output.append(" ");
                        output.append(priceVal);
                        output.append(" ");
                        output.append(qty);
                        output.append(" ");
                        output.append(amountInc);
                        output.append(NEWLINE);
                        flag =false;
                    }else {
                        output.append(NEWLINE);

                    }
                }
                lineLen = 0;
            }
            output.append(word);
            lineLen += word.length();

        }
        return output.toString();
    }
    public static String dailyReportBreakLines(String input, int maxLineLength, String qty, String amountInc) {

        String[] tokens = input.split(SPLIT_REGEXP);
        StringBuilder output = new StringBuilder(input.length());
        int lineLen = 0;
        boolean flag =true;
        for (int i = 0; i < tokens.length; i++) {
            String word = tokens[i];

            if (lineLen + (word).length() > maxLineLength ) {
                if (i > 0) {
                    if (flag){
                        output.append(" ");
                        output.append(qty);
                        output.append(" ");
                        output.append(amountInc);
                        output.append(NEWLINE);
                        flag =false;
                    }else {
                        output.append(NEWLINE);

                    }
                }
                lineLen = 0;
            }
            output.append(word);
            lineLen += word.length();

        }
        return output.toString();
    }
    public static boolean printInvoiceTableCancelOrder2Inch(Context context, RestraCheckoutData saleInoviceData, Bitmap bitmap, String tableName, in.hiaccounts.hinext.controlpanel.model.company_setup.compnayinformation.CompanyData companyData, List<RestraCheckoutItem> orderItemsList, Boolean notificationAppend) {
        if (RestuarantActivity.BLUETOOTH_PRINTER.IsNoConnection()) {
            return false;
        }

        if (saleInoviceData == null) {
            return false;
        } else {
            String companyName = "";
            String companyTelNumber = "";
            String companyFaxNumber = "";
            String companyGSTNumber = "";
            String companyAddress="";
            String  date ="", invoiceNo = "", invoiceCustomerName = "";


            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date dateFo = new Date();
            date = dateFormat.format(dateFo);

            if (companyData != null) {
                if (companyData.getCompanyName() != null) {
                    companyName = companyData.getCompanyName().toUpperCase();
                }
                if (companyData.getAddress() != null)
                    companyAddress = companyData.getAddress();
                if (companyData.getPhone() != null)
                    companyTelNumber = companyData.getPhone();
                if (companyData.getFax() != null)
                    companyFaxNumber = companyData.getFax();
                if (companyData.getRegisterNo() != null)
                    companyGSTNumber = companyData.getRegisterNo();
            }


            //LF = Line feed
            RestuarantActivity.BLUETOOTH_PRINTER.Begin();
            RestuarantActivity.BLUETOOTH_PRINTER.LF();
            //   RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
            //RestuarantActivity.BLUETOOTH_PRINTER.BT_Write("");
            // RestuarantActivity.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
/*            if (bitmap!=null) {
                //RestuarantActivity.BLUETOOTH_PRINTER.printByteData(logoByteArray);

                // RestuarantActivity.BLUETOOTH_PRINTER.printImage(Bitmap.createScaledBitmap(bitmap, 200, 200, false));
             *//*   Bitmap newBm = BitmapConvertUtil.decodeSampledBitmapFromBitmap(bitmap, 100);
                byte xL = (byte) (((newBm.getWidth() - 1) / 8 + 1) % 256);
                byte xH = (byte) (((newBm.getWidth() - 1) / 8 + 1) / 256);
                byte yL = (byte) (newBm.getHeight() % 256);
                byte yH = (byte) (newBm.getHeight() / 256);
                byte[] pixels = BitmapConvertUtil.convert(newBm);

                RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                RestuarantActivity.BLUETOOTH_PRINTER.printImage(Bitmap.createScaledBitmap(bitmap, 150, 150, false));
*//*
                *//*RestuarantActivity.BLUETOOTH_PRINTER.BT_Write(new byte[]{29, 118, 118, 10, xL, xH, yL, yH});

                RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//CENTER
                RestuarantActivity.BLUETOOTH_PRINTER.BT_Write(pixels);*//*

                Bitmap newBm = BitmapConvertUtil.decodeSampledBitmapFromBitmap(bitmap, 100);
                byte xL = (byte)(((newBm.getWidth() - 1) / 8 + 1) % 256);
                byte xH = (byte)(((newBm.getWidth() - 1) / 8 + 1) / 256);
                byte yL = (byte)(newBm.getHeight() % 256);
                byte yH = (byte)(newBm.getHeight() / 256);
                Log.d("HsBluetoothPrintDriver", "xL = " + xL);
                Log.d("HsBluetoothPrintDriver", "xH = " + xH);
                Log.d("HsBluetoothPrintDriver", "yL = " + yL);
                Log.d("HsBluetoothPrintDriver", "yH = " + yH);
                byte[] pixels = BitmapConvertUtil.convert(newBm);
                RestuarantActivity.BLUETOOTH_PRINTER.SetLeftStartSpacing((byte)30,(byte)30);
                RestuarantActivity.BLUETOOTH_PRINTER.BT_Write(new byte[]{29, 118, 48, 0, xL, xH, yL, yH});
                RestuarantActivity.BLUETOOTH_PRINTER.BT_Write(pixels);
                RestuarantActivity.BLUETOOTH_PRINTER.BT_Write(new byte[]{10});
            }*/
                RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                RestuarantActivity.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                RestuarantActivity.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x11);//normal
                RestuarantActivity.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
                RestuarantActivity.BLUETOOTH_PRINTER.BT_Write("Cancel Orders"+ "\n");


         /*   if (companyName!=null && !companyName.equals("")) {
                RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                RestuarantActivity.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                RestuarantActivity.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x11);//normal
                RestuarantActivity.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
                RestuarantActivity.BLUETOOTH_PRINTER.BT_Write(companyName+ "\n");

            }*/

            if (saleInoviceData.getTableName()!=null && !saleInoviceData.getTableName().equals("")){
                RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                RestuarantActivity.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                RestuarantActivity.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                RestuarantActivity.BLUETOOTH_PRINTER.BT_Write("Table: "+saleInoviceData.getTableName()+ "\n");
            }
            if (saleInoviceData.getCustomerName()!=null && !saleInoviceData.getCustomerName().equals("")) {
                //RestuarantActivity.BLUETOOTH_PRINTER.LF();
                RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                RestuarantActivity.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                RestuarantActivity.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                RestuarantActivity.BLUETOOTH_PRINTER.BT_Write("CustomerName: " + saleInoviceData.getCustomerName()+ "\n");

            }
            //String waiterName="NA";
            if (!notificationAppend && saleInoviceData.getWaiterName()!=null && !saleInoviceData.getWaiterName().equals("")){
                RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                RestuarantActivity.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                RestuarantActivity.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                RestuarantActivity.BLUETOOTH_PRINTER.BT_Write("Steward: "+saleInoviceData.getWaiterName()+ "\n");
            }
            if (saleInoviceData.getTokenNo()!=null && !saleInoviceData.getTokenNo().equals("")) {
                //RestuarantActivity.BLUETOOTH_PRINTER.LF();
                RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                RestuarantActivity.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                RestuarantActivity.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                RestuarantActivity.BLUETOOTH_PRINTER.BT_Write("Token: " + saleInoviceData.getTokenNo()+ "\n");

            }



            if (date!=null && !date.equals("")) {
                //RestuarantActivity.BLUETOOTH_PRINTER.LF();
                RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                RestuarantActivity.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                RestuarantActivity.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                RestuarantActivity.BLUETOOTH_PRINTER.BT_Write("DATE: " + date+ "\n");

            }

            //RestuarantActivity.BLUETOOTH_PRINTER.LF();
            RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
            RestuarantActivity.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line2inch)+ "\n");
            //RestuarantActivity.BLUETOOTH_PRINTER.LF();

            double amtExTax = 0,amtIncTax= 0;
            if (orderItemsList != null) {
                String  itemName = "",itemDesc="";
                String itemUnitPrice = "";
                String itemQty = "";
                String itemAmtInclTax = "";
                String itemTax = "";

                String itemDetails = "Item";
                String itemdetails=padRight(itemDetails, 27);


                if (orderItemsList != null) {
                    RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//CENTER
                    RestuarantActivity.BLUETOOTH_PRINTER.BT_Write(itemdetails+" " +"Qty"+"\n");
                    RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                    RestuarantActivity.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line2inch)+ "\n");

                    for (int i = 0; i < orderItemsList.size(); i++) {
                        RestraCheckoutItem item = orderItemsList.get(i);
                        if (item != null) {
                            if (item.getItemName() != null)
                                itemName = item.getItemName();




                            itemQty = "" + item.getQty();
                            int amtSpaceStringLenght2 = 4-itemQty.length();
                            for (int j=0;j<amtSpaceStringLenght2;j++){
                                itemQty=" "+itemQty;
                            }

                           // String itemname=padRight(itemName, 15);

                            //String desc = padRight(itemDesc, 25);
                            String itemname ="";
                            amtExTax=amtExTax+(item.getQty()*item.getUnitPrice());
                            if (itemName.length()>24){
                                itemname = kotBreakLines(itemName,24,itemQty);
                                RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//CENTER
                                RestuarantActivity.BLUETOOTH_PRINTER.BT_Write( itemname +  "\n");
                            }else{
                                String itemname1=padRight(itemName, 24);
                                RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//CENTER
                                RestuarantActivity.BLUETOOTH_PRINTER.BT_Write( itemname1 +" "+itemQty + "\n");
                            }
                            if (item.getItemDescription()!=null && !item.getItemDescription().equals("")){
                                RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//CENTER
                                RestuarantActivity.BLUETOOTH_PRINTER.BT_Write("[" + item.getItemDescription() + "]"+"\n");
                            }

                        }
                    }
                }
            }

            //RestuarantActivity.BLUETOOTH_PRINTER.LF();
         /*   RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            RestuarantActivity.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_star2inch)+ "\n");

            RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
            RestuarantActivity.BLUETOOTH_PRINTER.SetLineSpacing((byte)30);
            RestuarantActivity.BLUETOOTH_PRINTER.BT_Write("Thank You...!"+ "\n");
            //RestuarantActivity.BLUETOOTH_PRINTER.LF();
            RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
            RestuarantActivity.BLUETOOTH_PRINTER.SetLineSpacing((byte)30);
            RestuarantActivity.BLUETOOTH_PRINTER.BT_Write("Designed & Powered by Hiaccounts"+ "\n");
            RestuarantActivity.BLUETOOTH_PRINTER.LF();*/
            RestuarantActivity.BLUETOOTH_PRINTER.LF();
            RestuarantActivity.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            RestuarantActivity.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);
            RestuarantActivity.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line2inch)+ "\n");
            RestuarantActivity.BLUETOOTH_PRINTER.LF();
            RestuarantActivity.BLUETOOTH_PRINTER.BT_Write( "\n");
            RestuarantActivity.BLUETOOTH_PRINTER.LF();
            RestuarantActivity.BLUETOOTH_PRINTER.BT_Write( "\n");
            RestuarantActivity.BLUETOOTH_PRINTER.LF();
            RestuarantActivity.BLUETOOTH_PRINTER.BT_Write( "\n");
            RestuarantActivity.BLUETOOTH_PRINTER.CutPaper();


        }
        return true;

    }

    public static boolean printInvoiceDialyReport3Inch(Context context, RestraSaveCheckoutData saleInoviceData, Bitmap bitmap) {
        if (Activity_PrintList.BLUETOOTH_PRINTER.IsNoConnection()) {
            return false;
        }

        if (saleInoviceData == null) {
            return false;
        } else {
            String serialNo= "";
            String tokenNo = "";
            String companyName = "";
            String companyTelNumber = "";
            String companyFaxNumber = "";
            String companyGSTNumber = "";
            String companyAddress="";
            String date = "", time = "", invoiceNo = "", invoiceCustomerName = "" ;
         /*   if (saleInoviceData.getDate() != null) {
                date = saleInoviceData.getDate();
            }
           *//* if (saleInoviceData.getTime() != null) {
                time = saleInoviceData.getTime();
            }*//*
            if (saleInoviceData.getSiData() != null) {
                if (saleInoviceData.getSiData().getSiNo() != null)
                    invoiceNo = saleInoviceData.getSiData().getSiNo();
                if (saleInoviceData.getSiData().getCutomerName() != null){}
                //invoiceCustomerName = saleInoviceData.getSiData().getUserName();

                if (saleInoviceData.getSiData().getTokenNo()!=null){

                    tokenNo=""+saleInoviceData.getSiData().getTokenNo();
                }

            }*/


          /*  if (saleInoviceData.getRestaurantTokenRecord()!=null){

                *//*if (saleInoviceData.getRestaurantTokenRecord().getSiNo()!=null){

                    serialNo=saleInoviceData.getRestaurantTokenRecord().getSiNo();
                }*//*

                if (saleInoviceData.getRestaurantTokenRecord().getToken()!=null){

                    tokenNo=saleInoviceData.getRestaurantTokenRecord().getToken();
                }

            }*/

            SharedPreference sharedPreference = SharedPreference.getInstance(context);
            if (sharedPreference != null) {

                String applicationDataJson = sharedPreference.getData(Constant.COMPANYDATA);


                if (applicationDataJson != null) {
                    Gson gson = new Gson();
                    in.hiaccounts.hinext.controlpanel.model.company_setup.compnayinformation.CompanyData companyData = gson.fromJson(applicationDataJson, in.hiaccounts.hinext.controlpanel.model.company_setup.compnayinformation.CompanyData.class);
                    if (companyData != null) {
                        if (companyData.getCompanyName() != null) {
                            companyName = companyData.getCompanyName().toUpperCase();
                        }
                        if (companyData.getAddress() != null)
                            companyAddress = companyData.getAddress();
                        if (companyData.getPhone() != null)
                            companyTelNumber = companyData.getPhone();
                        if (companyData.getFax() != null)
                            companyFaxNumber = companyData.getFax();
                        if (companyData.getRegisterNo() != null)
                            companyGSTNumber = companyData.getRegisterNo();

                        if (companyData.getGstregisteredDate()!=null){
                            date=companyData.getGstregisteredDate();
                        }

                    }

                    }
                }



                  //  in.hiaccounts.hinext.controlpanel.model.company_setup.compnayinformation.CompanyData companyData = gson.fromJson(applicationDataJson, in.hiaccounts.hinext.controlpanel.model.company_setup.compnayinformation.CompanyData.class);

            //in.hiaccounts.hinext.restaurant.model.checkout.CompanyData companyData = saleInoviceData.getCompanyData();



            //LF = Line feed
            Activity_PrintList.BLUETOOTH_PRINTER.Begin();
            Activity_PrintList.BLUETOOTH_PRINTER.LF();
            Activity_PrintList.BLUETOOTH_PRINTER.LF();
            if (bitmap!=null) {

              /*  Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte)20);
                Activity_PrintList.BLUETOOTH_PRINTER.printImage(Bitmap.createScaledBitmap(bitmap, 100, 100, true));*/

                Bitmap newBm = BitmapConvertUtil.decodeSampledBitmapFromBitmap(bitmap, 100);
                byte xL = (byte)(((newBm.getWidth() - 1) / 8 + 1) % 256);
                byte xH = (byte)(((newBm.getWidth() - 1) / 8 + 1) / 256);
                byte yL = (byte)(newBm.getHeight() % 256);
                byte yH = (byte)(newBm.getHeight() / 256);
                Log.d("HsBluetoothPrintDriver", "xL = " + xL);
                Log.d("HsBluetoothPrintDriver", "xH = " + xH);
                Log.d("HsBluetoothPrintDriver", "yL = " + yL);
                Log.d("HsBluetoothPrintDriver", "yH = " + yH);
                byte[] pixels = BitmapConvertUtil.convert(newBm);
                Activity_PrintList.BLUETOOTH_PRINTER.SetLeftStartSpacing((byte)30,(byte)30);
                Activity_PrintList.BLUETOOTH_PRINTER.BT_Write(new byte[]{29, 118, 48, 0, xL, xH, yL, yH});
                Activity_PrintList.BLUETOOTH_PRINTER.BT_Write(pixels);
                Activity_PrintList.BLUETOOTH_PRINTER.BT_Write(new byte[]{10});
            }
            if (companyName!=null && !companyName.equals("")) {
                Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_PrintList.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x11);//normal
                Activity_PrintList.BLUETOOTH_PRINTER.SetBold((byte) 2);//normal
                Activity_PrintList.BLUETOOTH_PRINTER.BT_Write(companyName);
                Activity_PrintList.BLUETOOTH_PRINTER.LF();
            }

            if (companyTelNumber!=null && !companyTelNumber.equals("")) {
                Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_PrintList.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("Phone : " + companyTelNumber);
                Activity_PrintList.BLUETOOTH_PRINTER.LF();
            }

            if (companyGSTNumber!=null && !companyGSTNumber.equals("")) {
                Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_PrintList.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("GSTIN: " + companyGSTNumber);
                Activity_PrintList.BLUETOOTH_PRINTER.LF();
            }

            if (date!=null && !date.equals("")) {
                Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_PrintList.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("DATE: " + date);
                Activity_PrintList.BLUETOOTH_PRINTER.LF();
            }

            if (invoiceNo!=null && !invoiceNo.equals("")) {
                Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte) 40);    //30 * 0.125mm
                Activity_PrintList.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
                Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("Invoice No: " + invoiceNo);
                Activity_PrintList.BLUETOOTH_PRINTER.LF();
            }

            /*if (tokenNo!=null && !tokenNo.equals("")){
                Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//CENTER
                Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                Activity_PrintList.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x05);//normal
                Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("Token No: "+tokenNo);
            }Activity_PrintList.BLUETOOTH_PRINTER.LF();*/
            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_PrintList.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal
            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_equal));
            Activity_PrintList.BLUETOOTH_PRINTER.LF();

            double amtExTax = 0,amtIncTax= 0;
            if (saleInoviceData.getSiData() != null) {
                String  itemName = "";
                String itemUnitPrice = "";
                String itemQty = "";
                String itemAmtInclTax = "";
                String itemTax = "";
                if (saleInoviceData.getSiData() != null) {
                    Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                    Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);
                    Activity_PrintList.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);
                    Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("Item Details");
                    Activity_PrintList.BLUETOOTH_PRINTER.LF();

                   /* for (int i = 0; i < saleInoviceData.getSiData().getSelectedItemsList().size(); i++) {
                        RestraCheckoutItem item =   saleInoviceData.getSiData().getSelectedItemsList().get(i);
                        if (item != null) {
                            if (item.getItemName() != null)
                                itemName = item.getItemName();
                            itemUnitPrice = String.format("%.2f", item.getTotalAmtReceived());
                            // itemAmtInclTax = String.format("%.2f", (item.getQty()*item.getUnitPrice()));
                            itemAmtInclTax = String.format("%.2f", (item.getUnitPrice()));
                            itemQty = "" + item.getQtySold();
                            amtExTax=amtExTax+(item.getQtySold()*item.getTotalAmtReceived());


                            Activity_PrintList.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal font
                            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                            Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write((i + 1) + ".) " + itemName);
                            Activity_PrintList.BLUETOOTH_PRINTER.LF();
//                            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("    " + itemQty + " Nos " + itemUnitPrice + " = " + itemAmtInclTax+ "\n");
                            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("    " + itemQty + " X " + itemAmtInclTax+ "\n");


                            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line));
                            Activity_PrintList.BLUETOOTH_PRINTER.LF();

                            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 2);//LEFT
                            String totalCheckOutAmt=String.format("%.2f",saleInoviceData.getLassi().get(i).getTotalAmtReceived());
                            String totalTaxAmt=String.format("%.2f",saleInoviceData.getLassi().get(i).getTotalAmtReceived());

                            int amtSpaceStringLenght=8-totalCheckOutAmt.length();
                            for (int j=0;j<amtSpaceStringLenght;j++){
                                totalCheckOutAmt=" "+totalCheckOutAmt;
                            }


                            int taxSpaceStringLenght=8-totalTaxAmt.length();
                            for (int j=0;j<taxSpaceStringLenght;j++){
                                totalTaxAmt=" "+totalTaxAmt;
                            }

                            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("       Total Bill : " + totalCheckOutAmt+"  ");

                            Activity_PrintList.BLUETOOTH_PRINTER.LF();
                            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("              GST : " + totalTaxAmt+"  ");

                            Activity_PrintList.BLUETOOTH_PRINTER.LF();
                            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("Amount to be paid : " + totalCheckOutAmt+"  ");

                        }


                    }*/
                }
            }

         /*   Activity_PrintList.BLUETOOTH_PRINTER.BT_Write(
                    "       Total Bill:                   " + String.format("%.2f",saleInoviceData.getSiData().getTotalCheckOutamt())+"  "+
                            "\n   GST- Tax(5.0%):                   "+String.format("%.2f",saleInoviceData.getSiData().getTotalTaxAmt())+"  "+
                            "\nAmount to be paid:                   "+String.format("%.2f",saleInoviceData.getSiData().getTotalCheckOutamt())+"  ");
*/

            Activity_PrintList.BLUETOOTH_PRINTER.LF();
            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_star));
            Activity_PrintList.BLUETOOTH_PRINTER.LF();
            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_PrintList.BLUETOOTH_PRINTER.LF();



            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte)30);
            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("Sale Type : Dine-In Order");
            Activity_PrintList.BLUETOOTH_PRINTER.LF();
            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte)30);
            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("Payment Type: Cash");
            Activity_PrintList.BLUETOOTH_PRINTER.LF();

            Activity_PrintList.BLUETOOTH_PRINTER.LF();
            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
            Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte)30);
            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("Thank You...! Visit again");
            Activity_PrintList.BLUETOOTH_PRINTER.LF();
            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
            Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte)30);
            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("Designed & Powered by Hiaccounts");
            Activity_PrintList.BLUETOOTH_PRINTER.LF();
            Activity_PrintList.BLUETOOTH_PRINTER.LF();
            Activity_PrintList.BLUETOOTH_PRINTER.PartialCutPaper();
            Activity_PrintList.BLUETOOTH_PRINTER.LF();
            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write(context.getResources().getString(R.string.print_line));
            Activity_PrintList.BLUETOOTH_PRINTER.LF();
            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
            Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte)30);
  /*          Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("Token No: "+tokenNo);
            Activity_PrintList.BLUETOOTH_PRINTER.LF();
            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 1);//LEFT
            Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte)30);
            Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("Serial No: " + invoiceNo);
            if (saleInoviceData.getSiData() != null) {
                String itemName = "";
                String itemQty = "";
                if (saleInoviceData.getSiData().getSelectedItemsList() != null) {
                    Activity_PrintList.BLUETOOTH_PRINTER.LF();
                    Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                    Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);
                    Activity_PrintList.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);
                    Activity_PrintList.BLUETOOTH_PRINTER.BT_Write("   Item Details");
                    Activity_PrintList.BLUETOOTH_PRINTER.LF();
                    for (int i = 0; i < saleInoviceData.getSiData().getSelectedItemsList().size(); i++) {
                        RestraCheckoutItem item = saleInoviceData.getSiData().getSelectedItemsList().get(i);
                        if (item != null) {
                            if (item.getItemName() != null)
                                itemName = item.getItemName();
                            itemQty = "" + item.getQty();
                            int qtySpaceStringLenght = 3 - itemQty.length();
                            for (int j = 0; j < qtySpaceStringLenght; j++) {
                                itemQty = " " + itemQty;
                            }
                            Activity_PrintList.BLUETOOTH_PRINTER.SetFontEnlarge((byte) 0x00);//normal font
                            Activity_PrintList.BLUETOOTH_PRINTER.SetAlignMode((byte) 0);//LEFT
                            Activity_PrintList.BLUETOOTH_PRINTER.SetLineSpacing((byte) 30);    //30 * 0.125mm
                            if (itemName.length() >= 32) {
                                Activity_PrintList.BLUETOOTH_PRINTER.BT_Write((i + 1) + ".) " + itemName.substring(0, 32) + " X " + itemQty + " Nos" + "\n");
                            } else {
                                String spaceString = "";
                                int spaceStringLenght = 32 - itemName.length();
                                for (int j = 0; j < spaceStringLenght; j++) {
                                    spaceString = spaceString + " ";
                                }
                                Activity_PrintList.BLUETOOTH_PRINTER.BT_Write((i + 1) + ".) " + itemName.concat(spaceString) + " X " + itemQty + " Nos" + "\n");
                            }
                        }
                    }
                }
                Activity_PrintList.BLUETOOTH_PRINTER.LF();
                Activity_PrintList.BLUETOOTH_PRINTER.LF();
                Activity_PrintList.BLUETOOTH_PRINTER.CutPaper();

            }*/
        }
        return true;

    }


}






