package ru.osokin.pdf.aspose;

import java.io.InputStream;

/** Converter interface for transformation from someting to someting.
 * @author Osokin Alexander
 * @since 1.0
 */
interface Converter {
    /** Convert document.
     *  @param from from one stream (file, bytes)
     *  @return success of convertion and result pdf document
     */
    ConvertResult convert(InputStream from);
}
