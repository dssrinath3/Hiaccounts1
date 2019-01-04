package in.hiaccounts.pdfgenerator.generaltransaction;

import android.content.Context;

import com.lowagie.text.DocumentException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import in.hiaccounts.hinext.generaltransaction.model.checkout.GTJournalData;

/**
 *
 * Created by Prateek on 6/1/2017.
 */
public interface GTJournalVoucherPdf {

    void generateJournalVoucherPdf(Context context, GTJournalData printData, OutputStream outPutStream, InputStream inputStream) throws IOException,  DocumentException;


}
