package in.hiaccounts.pdfgenerator.sales.salesinvoice;

import android.content.Context;

import com.lowagie.text.DocumentException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import in.hiaccounts.hinext.checkout.model.sales.Checkout_Sales_ResponseData;
import in.hiaccounts.hinext.sales.model.sales_quotation.Sales_QuoationResponseData;

/**
 *
 * Created by Prateek on 6/1/2017.
 */
public interface PosSalesInvoicePdf {

    void generateTaxInvoice(Context context, Checkout_Sales_ResponseData retailPrintDTO, OutputStream outPutStream, InputStream inputStream) throws IOException,  DocumentException;

    void generateTaxInvoiceForQuoattion(Context context, Sales_QuoationResponseData retailPrintDTO, OutputStream outPutStream, InputStream inputStream) throws IOException,  DocumentException;

}
