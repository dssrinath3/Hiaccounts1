package in.hiaccounts.pdfgenerator.sales.payment;

import android.content.Context;

import com.lowagie.text.DocumentException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import in.hiaccounts.hinext.sales.model.save_response.SavePaymentPrintResponse;

/**
 *
 * Created by Prateek on 6/1/2017.
 */
public interface PosSalesPaymetPdf {

    void generatePaymentPdf(Context context, SavePaymentPrintResponse printData, OutputStream outPutStream, InputStream inputStream) throws IOException,  DocumentException;


}
