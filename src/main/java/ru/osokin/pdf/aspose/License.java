package ru.osokin.pdf.aspose;

import java.io.InputStream;

/** License that needs for converting documents.
 * @author Osokin Alexander
 * @since 1.0
 */
public interface License {
    /** Check Total license initialize.
     * @return flag */
    boolean isTotal();

    /** Check Pdf license initialize.
     * @return flag */
    boolean isPdf();

    /** Check Words license initialize.
     * @return flag */
    boolean isWords();

    /** Check Cells license initialize.
     * @return flag */
    boolean isCells();

    /** Check Slides license initialize.
     * @return flag */
    boolean isSlides();

    /** Initialize Total license.
     * @param licenseStream stream of license (file of bytes)
     * @return success of initialize */
    boolean setTotal(InputStream licenseStream);

    /** Initialize Pdf license.
     * @param licenseStream stream of license (file of bytes)
     * @return success of initialize */
    boolean setPdf(InputStream licenseStream);

    /** Initialize Words license.
     * @param licenseStream stream of license (file of bytes)
     * @return success of initialize */
    boolean setWords(InputStream licenseStream);

    /** Initialize Cells license.
     * @param licenseStream stream of license (file of bytes)
     * @return success of initialize */
    boolean setCells(InputStream licenseStream);

    /** Initialize Slides license.
     * @param licenseStream stream of license (file of bytes)
     * @return success of initialize */
    boolean setSlides(InputStream licenseStream);
}
