package com.abc;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONPath;
import ink.rayin.htmladapter.base.PdfGenerator;
import ink.rayin.htmladapter.openhtmltopdf.service.PdfBoxGenerator;
import ink.rayin.tools.utils.ResourceUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationText;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;

@Slf4j
public class PdfDemo {
    static PdfGenerator pdfGenerator;

    static {
        try {
            pdfGenerator = new PdfBoxGenerator();
            pdfGenerator.init();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // @Test
    public static void exp01ElementGenerateTest() throws Exception {
        log.info("exp01ElementGenerateTest start time：" + new Timestamp(System.currentTimeMillis()));

        String outputFileClass = ResourceUtil.getResourceAbsolutePathByClassPath("");

        // 生成pdf路径
        // generate pdf path
        String outputFile = new File(outputFileClass)
                .getParentFile().getParent()
                + "/tmp/"
                + "example01_openhtmltopdf_" + System.currentTimeMillis() + ".pdf";

        // 数据参数可以为空
//        pdfGenerator.generatePdfFileByHtmlAndData(
//                 "/home/ben/work/explore/pdf-report/src/main/resources/ele1.html" , null, outputFile);
        pdfGenerator.generatePdfFileByTplConfigFile(ResourceUtil.getResourceAbsolutePathByClassPath("/home/ben/work/explore/pdf-report/src/main/resources/tpl.json"), null, outputFile);

        log.info("exp01ElementGenerateTest end time：" + new Timestamp(System.currentTimeMillis()));
    }

    public static void main(String[] args) throws Exception {
//        exp01ElementGenerateTest();
//        generateDemoPdf();
        //Creating PDF document object

//        insertPdfPage();

//        extractImageFromPdf();

//        parsePdf();

//        editPdf();
//        readPdf();
        parsePdf();
//        verifyPdf();
    }

    private static void editPdf() throws IOException {
        String tempFile = "/home/ben/Downloads/b0b7f141ae674858bb4e71097ffcb5d8.pdf";
        File file = new File(tempFile);
        PDDocument document = PDDocument.load(file);
        PDDocumentInformation info = document.getDocumentInformation();
        info.setKeywords("xxxxx");
        document.setDocumentInformation(info);
        PDAnnotation pa = new PDAnnotationText();
        pa.setHidden(true);
        pa.setContents("6d8094a96066a8e4e514d913e571d3fd");
        pa.setAnnotationName("md5");
        document.getPage(0).setAnnotations(Arrays.asList(pa));
        document.save(tempFile);
        HashMap pdfMeta = new HashMap<String, String>();
        pdfMeta.put("Author", info.getAuthor());
        pdfMeta.put("Creator", info.getCreator());
        pdfMeta.put("Keywords", info.getKeywords());
        pdfMeta.put("Producer", info.getProducer());
        pdfMeta.put("Subject", info.getSubject());
        pdfMeta.put("Title", info.getTitle());
        pdfMeta.put("PagesInfo", info.getCustomMetadataValue("PagesInfo"));

        System.out.println(pdfMeta);

    }

    private static void readPdf() throws IOException {
        File file = new File("/home/ben/Downloads/7ae4e7183ea94e01b7771c268c6f23aa.pdf");
        PDDocument document = PDDocument.load(file);
        PDDocumentInformation info = document.getDocumentInformation();

        HashMap pdfMeta = new HashMap<String, String>();
        pdfMeta.put("Author", info.getAuthor());
        pdfMeta.put("Creator", info.getCreator());
        pdfMeta.put("Keywords", info.getKeywords());
        pdfMeta.put("Producer", info.getProducer());
        pdfMeta.put("Subject", info.getSubject());
        pdfMeta.put("Title", info.getTitle());
        pdfMeta.put("PagesInfo", info.getCustomMetadataValue("PagesInfo"));

        System.out.println(pdfMeta);

    }


    private static void parsePdf() throws IOException {
        String tempFile = "/home/ben/Downloads/b0b7f141ae674858bb4e71097ffcb5d8.pdf";
        File file = new File(tempFile);
        PDDocument document = PDDocument.load(file);
        PDDocumentInformation info = document.getDocumentInformation();

        HashMap pdfMeta = new HashMap<String, String>();
        pdfMeta.put("Author", info.getAuthor());
        pdfMeta.put("Creator", info.getCreator());
        pdfMeta.put("Keywords", info.getKeywords());
        pdfMeta.put("Producer", info.getProducer());
        pdfMeta.put("Subject", info.getSubject());
        pdfMeta.put("Title", info.getTitle());
        pdfMeta.put("PagesInfo", info.getCustomMetadataValue("PagesInfo"));

        System.out.println(pdfMeta);


        InputStream fis = new FileInputStream(file);
        String s = pdfGenerator.pdfPageInfoRead(fis);
        System.out.println(s);

        PDFTextStripper pdfStripper = new PDFTextStripper();
        String text = pdfStripper.getText(document);
        System.out.println("=================>text");
        String pdfMd5 = DigestUtils.md5Hex(text+"ctj1507");
        log.info("开标一览表生成,pdfMd5={}", pdfMd5);

        System.out.println(text);
        System.out.println(text.hashCode());
        String t2 = text;
        System.out.println(t2.hashCode());

        document.close();

//        try (PDDocument document = PDDocument.load(file)) {
//            // The order of the text tokens in a PDF file may not be in the same as they appear
//            // visually on the screen, so tell PDFBox to sort by text position
//            setSortByPosition(true);
//
//            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("bcbc.txt"), UTF_8)) {
//                // This will take a PDDocument and write the text of that document to the writer.
//                writeText(document, writer);
//            }
//        } catch (InvalidPasswordException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private static void verifyPdf() throws IOException {
        File file = new File("/home/ben/Downloads/b0b7f141ae674858bb4e71097ffcb5d8.pdf");

        PDDocument document = PDDocument.load(file);
        PDDocumentInformation info = document.getDocumentInformation();

        PDFTextStripper pdfStripper = new PDFTextStripper();
        String text = pdfStripper.getText(document);
        String pdfMd5 = DigestUtils.md5Hex(text + info.getAuthor());
        log.info("开标一览表生成,pdfMd5={}", pdfMd5);

        System.out.println("=================>verifyPdf");
        log.info("开标一览表生成,verifyPdf pdfMd5={}, keywords={} result={},", pdfMd5, info.getKeywords(), pdfMd5.equals(info.getKeywords()));


        document.close();

    }


    private static void extractImageFromPdf() throws IOException {
        //Loading an existing PDF document
        File file = new File("/home/ben/Desktop/demo2.pdf");
        PDDocument document = PDDocument.load(file);

        //Instantiating the PDFRenderer class
        PDFRenderer renderer = new PDFRenderer(document);

        //Rendering an image from the PDF document
        BufferedImage image = renderer.renderImage(0);

        //Writing the image to a file
        ImageIO.write(image, "JPEG", new File("/home/ben/Desktop/ExtractImage_OP.png"));
        System.out.println("Image created");

        //Closing the document
        document.close();


    }

    private static void insertPdfPage() throws IOException {
//        PDDocument document = new PDDocument();
        File file = new File("/home/ben/Desktop/test.pdf");
        PDDocument doc1 = PDDocument.load(file);

        File f2 = new File("/home/ben/Desktop/demo.pdf");
        PDDocument doc2 = PDDocument.load(f2);
        PDPage p1 = doc2.getPage(0);
        PDPage p2 = doc1.getPage(3);

        doc1.getPages().insertAfter(p1, p2);

//        PDFMergerUtility PDFmerger = new PDFMergerUtility();
//        PDFmerger.
//        document.addPage(doc1.getPage(5));
//        for (int i=0; i<2; i++){
//            //Creating a blank page
//            PDPage blankPage = new PDPage();
//
//            //Adding the blank page to the document
//            doc1.addPage(blankPage);
//        }
        //Saving the document
//        document.save("/home/ben/Desktop/test_OUT.pdf");
        doc1.save("/home/ben/Desktop/test_OUT.pdf");
        System.out.println("PDF created");

        //Closing the document
        doc1.close();
        doc2.close();
    }

    private static void md5Info() throws IOException {
//        PDDocument document = new PDDocument();
        File file = new File("/home/ben/Desktop/test.pdf");
        PDDocument doc1 = PDDocument.load(file);
        PDDocumentInformation pi = doc1.getDocumentInformation();
        pi.setKeywords("mdk.xxxxx.yyyy.zzz");
        doc1.setDocumentInformation(pi);

        doc1.save("/home/ben/Desktop/test_OUT.pdf");
        System.out.println("PDF created");

        //Closing the document
        doc1.close();
    }

    private static void generateDemoPdf() throws IOException {
        PdfGenerator pdfGenerator = new PdfBoxGenerator();
        pdfGenerator.init();

        JSONObject jsonData = new JSONObject();
        JSONPath.set(jsonData, "barcode", "123UUIDrandomUUID456");
        jsonData.put("barcode", "1234567890");
        jsonData.put("xxx", "1234567890");
        jsonData.put("car", "2021年末：100%");


        //单个构件生成，数据参数可以为空
//        pdfGenerator.generatePdfFileByHtmlAndData("/home/ben/work/explore/pdf-report/src/main/resources/demo.html", jsonData, "/home/ben/work/explore/pdf-report/src/main/resources/demo.pdf");

        //
        pdfGenerator.generatePdfFileByTplConfigFile(ResourceUtil.getResourceAbsolutePathByClassPath("bid.json"), jsonData, "/tmp/demo.pdf");

    }
}
