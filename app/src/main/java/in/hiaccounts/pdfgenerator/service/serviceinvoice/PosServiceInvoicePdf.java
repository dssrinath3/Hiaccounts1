package in.hiaccounts.pdfgenerator.service.serviceinvoice;

import android.content.Context;

import com.lowagie.text.DocumentException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import in.hiaccounts.hinext.service.model.service_invoice.ServiceSaveData;

/**
 * Created by administrator on 26/2/18.
 */

public interface PosServiceInvoicePdf {
    void generateTaxInvoice(Context context, ServiceSaveData retailPrintDTO, OutputStream outPutStream, InputStream inputStream) throws IOException, DocumentException;
}
