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
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

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

    /** Create Document with default test Aspose license. */
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
    @SuppressWarnings("UnstableApiUsage")
    public final ConvertResult convert(final InputStream from) {
        if (from == null) {
            return new ConvertResult(false, "Null input file");
        }
        try {
            byte[] inputBytes = IOUtils.toByteArray(from);
            if (inputBytes.length == 0) {
                return new ConvertResult(false, "Empty input file");
            }
            MediaType mediaType = QUALIFIER.getContentType(new ByteArrayInputStream(inputBytes));
            InputStream streamForConvert = new ByteArrayInputStream(inputBytes);
            if (mediaType.is(MediaType.PDF)) {
                return new ConvertResult(inputBytes, "Not convert PDF to PDF. Result equals input pdf");
            } else if (mediaType.is(MediaType.ANY_IMAGE_TYPE)) {
                return convertImage2Pdf(new ByteArrayInputStream(inputBytes));
            } else if (mediaType.is(MediaType.OPENDOCUMENT_TEXT) || mediaType.is(MediaType.MICROSOFT_WORD)
                    || mediaType.is(MediaType.OOXML_DOCUMENT) || mediaType.subtype().equals("rtf")) {
                return convertWordsDocument2Pdf(new ByteArrayInputStream(inputBytes));
            } else if (mediaType.is(MediaType.OOXML_SHEET) || mediaType.is(MediaType.MICROSOFT_EXCEL)) {
                return convertCellsDocument2Pdf(new ByteArrayInputStream(inputBytes));
            } else if (mediaType.is(MediaType.OOXML_PRESENTATION) || mediaType.is(MediaType.MICROSOFT_POWERPOINT)) {
                return convertPresentationDocument2Pdf(new ByteArrayInputStream(inputBytes));
            } else {
                return new ConvertResult(false, "Not supported file format");
            }
        } catch (Exception | Error e) {
            LOGGER.error("Could not convert file to pdf", e);
            return new ConvertResult(false, "Could not convert file to pdf");
        }
    }

    /** Convert doc, docx, odt, rtf documents to PDF.
     * @param inputStream input stream (file, bytes)
     * @return result of conversion
     * @throws Exception generate Aspose library
     */
    private ConvertResult convertWordsDocument2Pdf(final InputStream inputStream) throws Exception {
        com.aspose.words.Document doc = new com.aspose.words.Document(inputStream);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        doc.save(outputStream, com.aspose.words.SaveFormat.PDF);
        return new ConvertResult(outputStream.toByteArray());
    }

    /** Convert xls, xlsx documents to PDF.
     * @param inputStream input stream (file, bytes)
     * @return result of conversion
     * @throws Exception generate Aspose library
     */
    private ConvertResult convertCellsDocument2Pdf(final InputStream inputStream) throws Exception {
        Workbook workbook = new Workbook(inputStream);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.save(outputStream, SaveFormat.PDF);
        return new ConvertResult(outputStream.toByteArray());
    }

    /** Convert ppt, pptx documents to PDF.
     * @param inputStream input stream (file, bytes)
     * @return result of conversion
     */
    private ConvertResult convertPresentationDocument2Pdf(final InputStream inputStream) {
        Presentation presentation = new Presentation(inputStream);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        presentation.save(outputStream, com.aspose.slides.SaveFormat.Pdf);
        return new ConvertResult(outputStream.toByteArray());
    }

    /** Convert images bmp, png, jpg, JPEG2000 (jp2, jpf) to PDF.
     * @param inputStream input stream (file, bytes)
     * @return result of conversion
     * @throws IOException generate bad inputStream
     */
    private ConvertResult convertImage2Pdf(final InputStream inputStream) throws IOException {
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
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        doc.save(outputStream);
        return new ConvertResult(outputStream.toByteArray());
    }
}
