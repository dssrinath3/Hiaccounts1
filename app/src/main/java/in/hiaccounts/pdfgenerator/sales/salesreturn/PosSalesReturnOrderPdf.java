package in.hiaccounts.pdfgenerator.sales.salesreturn;

import android.content.Context;

import com.lowagie.text.DocumentException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import in.hiaccounts.hinext.checkout.model.sales.Checkout_Sales_ResponseData;

/**
 *
 * Created by Prateek on 6/1/2017.
 */
public interface PosSalesReturnOrderPdf {

    void generateSalesReturnOrder(Context context, Checkout_Sales_ResponseData retailPrintDTO, OutputStream outPutStream, InputStream inputStream) throws IOException,  DocumentException;


}
