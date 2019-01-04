package in.hiaccounts.pdfgenerator.purchase.purchase_inovices;

import android.content.Context;

import com.lowagie.text.DocumentException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import in.hiaccounts.hinext.purchase.model.purchase_saveresponses.Checkout_ResponseData;

/**
 *
 * Created by Prateek on 6/1/2017.
 */
public interface PosPurchaseInvoicePdf {

    void generatePurchaseTaxInvoice(Context context, Checkout_ResponseData invoiceData, OutputStream outPutStream, InputStream inputStream) throws IOException,  DocumentException;


}
