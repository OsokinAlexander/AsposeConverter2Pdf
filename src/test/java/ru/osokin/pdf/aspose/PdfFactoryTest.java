package ru.osokin.pdf.aspose;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PdfFactoryTest {

    private PdfFactory pdfFactory = new PdfFactory();
    private final boolean isDeleteTempFile = false;

    @Before
    public void before() {
//        License license = new AsposeLicense();
//        license.setTotal(PdfFactoryTest.class.getResourceAsStream("/aspose-license/Aspose.Total.Java.lic"));
//        pdfFactory = new PdfFactory(license);
    }


    @Test
    public void convertNull2Pdf() {
        ConvertResult result = pdfFactory.convert(null);
        assertFalse(result.success());
        assertEquals(PdfFactory.NULL_INPUT_FILE, result.resultMessage());
    }

    @Test
    public void convertEmpty2Pdf() {
        ConvertResult result = pdfFactory.convert(PdfFactoryTest.class.getResourceAsStream("/doc-examples/empty.txt"));
        assertFalse(result.success());
        assertEquals(PdfFactory.EMPTY_INPUT_FILE, result.resultMessage());
    }

    @Test
    public void convertText2Pdf() {
        ConvertResult result = pdfFactory.convert(PdfFactoryTest.class.getResourceAsStream("/doc-examples/test.txt"));
        assertFalse(result.success());
        assertEquals(PdfFactory.NOT_SUPPORTED_FILE_FORMAT, result.resultMessage());
    }

    @Test
    public void convertPdf2Pdf() throws IOException {
        File resultFile = getTempFile("pdf");
        File inputFile = new File("src/test/resources/doc-examples/test.pdf");
        ConvertResult result = pdfFactory.convert(PdfFactoryTest.class.getResourceAsStream("/doc-examples/test.pdf"));
        assertTrue(result.success());
        assertEquals(PdfFactory.EQUALS_RESULT_PDF, result.resultMessage());
        FileUtils.writeByteArrayToFile(resultFile, result.pdfDocument());
        assertEquals(inputFile.length(), resultFile.length());
    }

    @Test
    public void convertBmp2Pdf() throws IOException {
        File resultFile = getTempFile("bmp");
        assertTrue(convert2Pdf("/doc-examples/test.bmp", resultFile));
        assertTrue(resultFile.length() > 0);
    }

    @Test
    public void convertTif2Pdf() throws IOException {
        File resultFile = getTempFile("tiff");
        assertTrue(convert2Pdf("/doc-examples/test.tif", resultFile));
        assertTrue(resultFile.length() > 0);
    }

    @Test
    public void convertJpeg2Pdf() throws IOException {
        File resultFile = getTempFile("jpeg");
        assertTrue(convert2Pdf("/doc-examples/test.jpg", resultFile));
        assertTrue(resultFile.length() > 0);
    }

    @Test
    public void convertJpeg2000Jp2_2Pdf() throws IOException {
        File resultFile = getTempFile("jpeg2000");
        assertTrue(convert2Pdf("/doc-examples/test.jp2", resultFile));
        assertTrue(resultFile.length() > 0);
    }

    @Test
    public void convertJpeg2000Jpf_2Pdf() throws IOException {
        File resultFile = getTempFile("jpeg2000");
        assertTrue(convert2Pdf("/doc-examples/test.jpf", resultFile));
        assertTrue(resultFile.length() > 0);
    }

    @Test
    public void convertPortraitJpeg2Pdf() throws IOException {
        File resultFile = getTempFile("portrait-jpeg");
        assertTrue(convert2Pdf("/doc-examples/test_portrait.jpg", resultFile));
        assertTrue(resultFile.length() > 0);
    }

    @Test
    public void convertBigJpeg2Pdf() throws IOException {
        File resultFile = getTempFile("big_jpeg");
        assertTrue(convert2Pdf("/doc-examples/test_big.jpg", resultFile));
        assertTrue(resultFile.length() > 0);
    }

    @Test
    public void convertPng2Pdf() throws IOException {
        File resultFile = getTempFile("png");
        assertTrue(convert2Pdf("/doc-examples/test.png", resultFile));
        assertTrue(resultFile.length() > 0);
    }

    @Test
    public void convertScreenshotPng2Pdf() throws IOException {
        File resultFile = getTempFile("png");
        assertTrue(convert2Pdf("/doc-examples/screenshot.png", resultFile));
        assertTrue(resultFile.length() > 0);
    }

    @Test
    public void convertDoc2Pdf() throws IOException {
        File resultFile = getTempFile("doc");
        assertTrue(convert2Pdf("/doc-examples/test.doc", resultFile));
        assertTrue(resultFile.length() > 0);
    }

    @Test
    public void convertDocx2Pdf() throws IOException {
        File resultFile = getTempFile("docx");
        assertTrue(convert2Pdf("/doc-examples/test.docx", resultFile));
        assertTrue(resultFile.length() > 0);
    }

    @Test
    public void convertXls2Pdf() throws IOException {
        File resultFile = getTempFile("xls");
        assertTrue(convert2Pdf("/doc-examples/test.xls", resultFile));
        assertTrue(resultFile.length() > 0);
    }

    @Test
    public void convertXlsx2Pdf() throws IOException {
        File resultFile = getTempFile("xlsx");
        assertTrue(convert2Pdf("/doc-examples/test.xlsx", resultFile));
        assertTrue(resultFile.length() > 0);
    }

    @Test
    public void convertPpt2Pdf() throws IOException {
        File resultFile = getTempFile("ppt");
        assertTrue(convert2Pdf("/doc-examples/test.ppt", resultFile));
        assertTrue(resultFile.length() > 0);
    }

    @Test
    public void convertPptx2Pdf() throws IOException {
        File resultFile = getTempFile("pptx");
        assertTrue(convert2Pdf("/doc-examples/test.pptx", resultFile));
        assertTrue(resultFile.length() > 0);
    }

    @Test
    public void convertOdt2Pdf() throws IOException {
        File resultFile = getTempFile("odt");
        assertTrue(convert2Pdf("/doc-examples/test.odt", resultFile));
        assertTrue(resultFile.length() > 0);
    }

    @Test
    public void convertRtf2Pdf() throws IOException {
        File resultFile = getTempFile("rtf");
        assertTrue(convert2Pdf("/doc-examples/test.rtf", resultFile));
        assertTrue(resultFile.length() > 0);
    }

    private File getTempFile(String prefix) throws IOException {
        File tempFile = File.createTempFile("aspose_" + prefix, ".pdf");
        if (isDeleteTempFile) {
            tempFile.deleteOnExit();
        } else {
            System.out.println(tempFile.getAbsolutePath());
        }
        return tempFile;
    }

    private boolean convert2Pdf(String resourceFile, File resultFile) throws IOException {
        ConvertResult result = pdfFactory.convert(PdfFactoryTest.class.getResourceAsStream(resourceFile));
        if (result.success()) {
            FileUtils.writeByteArrayToFile(resultFile, result.pdfDocument());
        }
        return result.success();
    }
}
