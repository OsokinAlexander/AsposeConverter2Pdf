package ru.osokin.pdf.aspose;

import com.google.common.net.MediaType;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.ContentHandler;

import java.io.InputStream;

/** Tika implementation of Qualifier interface.
 * @author Osokin Alexander
 * @since 1.0
 */
class TikaQualifier implements Qualifier {
    /** Slf4j logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(TikaQualifier.class);

    /** {@inheritDoc} */
    @Override
    @SuppressWarnings("UnstableApiUsage")
    public final MediaType getContentType(final InputStream inputStream) {
        ContentHandler contenthandler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        AutoDetectParser parser = new AutoDetectParser();
        try {
            parser.parse(inputStream, contenthandler, metadata);
        } catch (Exception | Error e) {
            LOGGER.error("Could not detect file content type");
            throw new RuntimeException(e);
        }
        return MediaType.parse(metadata.get(Metadata.CONTENT_TYPE));
    }
}
