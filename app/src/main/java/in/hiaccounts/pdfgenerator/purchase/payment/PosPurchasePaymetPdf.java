package in.hiaccounts.pdfgenerator.purchase.payment;

import android.content.Context;

import com.lowagie.text.DocumentException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import in.hiaccounts.hinext.purchase.model.purchase_saveresponses.Purchase_PaymentResponse;

/**
 *
 * Created by Prateek on 6/1/2017.
 */
public interface PosPurchasePaymetPdf {

    void generatePaymentPdf(Context context, Purchase_PaymentResponse printData, OutputStream outPutStream, InputStream inputStream) throws IOException,  DocumentException;


}
