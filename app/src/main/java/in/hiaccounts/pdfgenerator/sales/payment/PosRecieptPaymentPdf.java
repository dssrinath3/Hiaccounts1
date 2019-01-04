package in.hiaccounts.pdfgenerator.sales.payment;

import android.content.Context;

import com.lowagie.text.DocumentException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import in.hiaccounts.hinext.sales.model.printlist.payement_receipt.PrintPaymentReceiptData;

/**
 *
 * Created by Prateek on 6/1/2017.
 */
public interface PosRecieptPaymentPdf {

    void generatePaymentPdf(Context context, PrintPaymentReceiptData printData, OutputStream outPutStream, InputStream inputStream) throws IOException,  DocumentException;


}
