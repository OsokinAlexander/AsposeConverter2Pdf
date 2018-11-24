package ru.osokin.pdf.aspose;

import com.google.common.net.MediaType;

import java.io.InputStream;

/** Qualifier content of documents as someone type.
 * @author Osokin Alexander
 * @since 1.0
 */
interface Qualifier {
    /** Define input content type.
     * @param inputStream stream (file, bytes)
     * @return Guava media type of document
     */
    MediaType getContentType(InputStream inputStream);
}
