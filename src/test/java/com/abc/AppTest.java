package com.abc;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONPath;
import ink.rayin.htmladapter.base.PdfGenerator;
import ink.rayin.htmladapter.openhtmltopdf.service.PdfBoxGenerator;
import ink.rayin.tools.utils.ResourceUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
@Slf4j
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void verifyPdfTest() throws IOException {
        String tempFile = "/home/ben/Downloads/9fcb189fe74340e6b3005c594f75b693.pdf";
        boolean isOriginalPdf = verifyPdf(tempFile);
        log.info("开标一览表生成,  isOriginalPdf={},", isOriginalPdf);

    }

    private static boolean verifyPdf(String tempFile) throws IOException {
        File file = new File(tempFile);

        PDDocument document = PDDocument.load(file);
        PDDocument d2 = new PDDocument();
        PDPage bidOpenPage = document.getPage(0); //默认第一
        if (document.getNumberOfPages() > 5) {
            bidOpenPage = document.getPage(4);
        }
        //页取annotation中的md5值
        List<PDAnnotation> al = bidOpenPage.getAnnotations(x -> x.getAnnotationName().equals("md5"));
        if (CollectionUtils.isEmpty(al)) {
            log.error("开标一览表生成,verifyPdf 取没d5有的annotation.. ");
            return false;
        }
        String originalMd5 = al.get(0).getContents();
        log.info("开标一览表生成,verifyPdf originalMd5={}.. ", originalMd5);

        d2.addPage(bidOpenPage);


        PDFTextStripper pdfStripper = new PDFTextStripper();
        String text = pdfStripper.getText(d2);
        String pdfMd5 = DigestUtils.md5Hex(text + "ctj1507");

        boolean isOriginalPdf = pdfMd5.equals(originalMd5);
        log.info("开标一览表生成,verifyPdf pdfMd5={}, originalMd5={} result={},", pdfMd5, originalMd5, isOriginalPdf);
        document.close();
        d2.close();
        return isOriginalPdf;
    }


    @Test
    public void generateDemoPdfTest() throws IOException {
        PdfGenerator pdfGenerator = new PdfBoxGenerator();
        pdfGenerator.init();

        JSONObject jsonData = new JSONObject();
        JSONPath.set(jsonData, "barcode", "123UUIDrandomUUID456");
        jsonData.put("barcode", "1234567890");
        jsonData.put("xxx", "1234567890");
        jsonData.put("car", "2021年末：100%");

        BidOpenItem capitalRatio = new BidOpenItem();
        capitalRatio.setItemName("2022年末：");
        capitalRatio.setItemValue("10");
        capitalRatio.setItemUnit("%");
        capitalRatio.setDescription("总行2021年末的");
        jsonData.put("capitalRatio", capitalRatio);

        BidOpenItem nplRatio = new BidOpenItem();
        nplRatio.setItemName("2022年末：");
        nplRatio.setItemValue("10");
        nplRatio.setItemUnit("%");
        nplRatio.setDescription("总行2021年末的");
        jsonData.put("nplRatio", nplRatio);

        jsonData.put("provisionCoverageRatio", nplRatio);
        jsonData.put("liquidityCoverageRatio", nplRatio);
        jsonData.put("liquidityRatio", nplRatio);
        jsonData.put("currentDepositRate", nplRatio);
        jsonData.put("pbcDepositRate", nplRatio);

        jsonData.put("assetsInShaanxi", nplRatio);
        jsonData.put("totalTax", nplRatio);
        jsonData.put("iit", nplRatio);
        jsonData.put("cit", nplRatio);
        jsonData.put("vat", nplRatio);

        BidOpenItem generalDepositBalance = new BidOpenItem();
        jsonData.put("generalDepositBalance", generalDepositBalance);

        BidOpenItem nationalDebt = new BidOpenItem();
        nationalDebt.setItemName("为本期国库定期存款可提供的符合条件的国债:");
        nationalDebt.setItemValue("20");
        nationalDebt.setItemUnit("亿元");
        jsonData.put("nationalDebt", nationalDebt);

        BidOpenItem governmentDebt = new BidOpenItem();
        governmentDebt.setItemName("地方政府债：");
        governmentDebt.setItemValue("10");
        governmentDebt.setItemUnit("亿元");
        jsonData.put("governmentDebt", governmentDebt);

        int barcodeHash = jsonData.hashCode();
        if (barcodeHash < 0) {
            barcodeHash = barcodeHash * -1;
        }
        jsonData.put("barcode", String.valueOf(barcodeHash));

        pdfGenerator.generatePdfFileByTplConfigFile(ResourceUtil.getResourceAbsolutePathByClassPath("bid.json"), jsonData, "/tmp/demo.pdf");

    }


}
