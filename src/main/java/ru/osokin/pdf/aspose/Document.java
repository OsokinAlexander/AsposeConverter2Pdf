package ru.osokin.pdf.aspose;

import com.aspose.cells.SaveFormat;
import com.aspose.cells.Workbook;
import com.aspose.pdf.Image;
import com.aspose.pdf.Page;
import com.aspose.slides.Presentation;
import com.google.common.net.MediaType;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/** Document that can convert from some formats to PDF.
 * @author Osokin Alexander
 * @since 1.0
 */
public class Document implements Converter {
    /** Slf4j logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(Document.class);
    /** Qualifier of content type of input stream. */
    private static final Qualifier QUALIFIER = new TikaQualifier();
    /** License that needs for converting to PDF. */
    private final License asposeLicense;

    /** Create Document with default Aspose license. */
    public Document() {
        this.asposeLicense = new AsposeLicense();
    }

    /** Create Document with license.
     * @param license license for production convertion of documents.
     */
    public Document(final License license) {
        this.asposeLicense = license;
    }

    /** {@inheritDoc}
     * Output PDF documents will be have little damage without initialize of any license. */
    @Override
    public final boolean convert(final InputStream from, OutputStream to) {
        if (from == null) {
            LOGGER.error("Null input file");
            return false;
        }
        try {
            byte[] inputBytes = IOUtils.toByteArray(from);
            if (inputBytes.length == 0) {
                LOGGER.error("Empty input file");
                return false;
            }
            MediaType mediaType = QUALIFIER.getContentType(new ByteArrayInputStream(inputBytes));
            if (mediaType.is(MediaType.PDF)) {
                LOGGER.info("Not convert PDF to PDF. Output equals input pdf");
                IOUtils.copy(new ByteArrayInputStream(inputBytes), to);
            } else if (mediaType.is(MediaType.ANY_IMAGE_TYPE)) {
                convertImage2Pdf(new ByteArrayInputStream(inputBytes), to);
            } else if (mediaType.is(MediaType.OPENDOCUMENT_TEXT) || mediaType.is(MediaType.MICROSOFT_WORD)
                    || mediaType.is(MediaType.OOXML_DOCUMENT) || mediaType.subtype().equals("rtf")) {
                convertWordsDocument2Pdf(new ByteArrayInputStream(inputBytes), to);
            } else if (mediaType.is(MediaType.OOXML_SHEET) || mediaType.is(MediaType.MICROSOFT_EXCEL)) {
                convertCellsDocument2Pdf(new ByteArrayInputStream(inputBytes), to);
            } else if (mediaType.is(MediaType.OOXML_PRESENTATION) || mediaType.is(MediaType.MICROSOFT_POWERPOINT)) {
                convertPresentationDocument2Pdf(new ByteArrayInputStream(inputBytes), to);
            } else {
                LOGGER.error("Not supported file format");
                return false;
            }
        } catch (Exception | Error e) {
            LOGGER.error("Could not convert file to pdf", e);
            return false;
        }
        return true;
    }

    /** Convert doc, docx, odt, rtf documents to PDF.
     * @param inputStream input stream (file, bytes)
     * @param outputStream PDF stream (file, bytes)
     * @throws Exception generate Aspose library
     */
    private void convertWordsDocument2Pdf(final InputStream inputStream, OutputStream outputStream) throws Exception {
        com.aspose.words.Document doc = new com.aspose.words.Document(inputStream);
        doc.save(outputStream, com.aspose.words.SaveFormat.PDF);
    }

    /** Convert xls, xlsx documents to PDF.
     * @param inputStream input stream (file, bytes)
     * @param outputStream PDF stream (file, bytes)
     * @throws Exception generate Aspose library
     */
    private void convertCellsDocument2Pdf(final InputStream inputStream, OutputStream outputStream) throws Exception {
        Workbook workbook = new Workbook(inputStream);
        workbook.save(outputStream, SaveFormat.PDF);
    }

    /** Convert ppt, pptx documents to PDF.
     * @param inputStream input stream (file, bytes)
     * @param outputStream PDF stream (file, bytes)
     */
    private void convertPresentationDocument2Pdf(final InputStream inputStream, OutputStream outputStream) {
        Presentation presentation = new Presentation(inputStream);
        presentation.save(outputStream, com.aspose.slides.SaveFormat.Pdf);
    }

    /** Convert images bmp, png, jpg, JPEG2000 (jp2, jpf) to PDF.
     * @param inputStream input stream (file, bytes)
     * @param outputStream PDF stream (file, bytes)
     * @throws IOException generate bad inputStream
     */
    private void convertImage2Pdf(final InputStream inputStream, OutputStream outputStream) throws IOException {
        com.aspose.pdf.Document doc = new com.aspose.pdf.Document();
        Page page = doc.getPages().add();
        page.getPageInfo().getMargin().setBottom(0);
        page.getPageInfo().getMargin().setTop(0);
        page.getPageInfo().getMargin().setLeft(0);
        page.getPageInfo().getMargin().setRight(0);
        BufferedImage bufferedImage = ImageIO.read(inputStream);
        page.getPageInfo().setLandscape(bufferedImage.getWidth() > bufferedImage.getHeight());
        Image image = new Image();
        image.setBufferedImage(bufferedImage);
        page.getParagraphs().add(image);
        doc.save(outputStream);
    }
}
