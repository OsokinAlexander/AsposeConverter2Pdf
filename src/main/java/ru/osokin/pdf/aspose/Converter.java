package ru.osokin.pdf.aspose;

import java.io.InputStream;
import java.io.OutputStream;

/** Converter interface for transformation from someting to someting.
 * @author Osokin Alexander
 * @since 1.0
 */
interface Converter {
    /** Convert document.
     *  @param from from one stream (file, bytes)
     *  @param to to other stream (file, bytes)
     *  @return success of convertion
     */
    boolean convert(InputStream from, OutputStream to);
}
