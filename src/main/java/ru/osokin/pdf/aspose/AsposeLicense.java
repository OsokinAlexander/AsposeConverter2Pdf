package ru.osokin.pdf.aspose;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;

/** Licence implementation with Aspose libraries.
 * @author Osokin Alexander
 * @since 1.0
 */
public class AsposeLicense implements License {
    /** Slf4j logger. */
    private static final Logger LOGGER =
            LoggerFactory.getLogger(AsposeLicense.class);
    /** Flag that Total license is used. */
    private boolean isTotalLicense;
    /** Flag that Pdf license is used. */
    private boolean isPdfLicense;
    /** Flag that Words license is used. */
    private boolean isWordsLicense;
    /** Flag that Cells license is used. */
    private boolean isCellsLicense;
    /** Flag that Slides license is used. */
    private boolean isSlidesLicense;

    /** {@inheritDoc} */
    @Override
    public final boolean isTotal() {
        return isTotalLicense;
    }

    /** {@inheritDoc} */
    @Override
    public final boolean isPdf() {
        return isPdfLicense;
    }

    /** {@inheritDoc} */
    @Override
    public final boolean isWords() {
        return isWordsLicense;
    }

    /** {@inheritDoc} */
    @Override
    public final boolean isCells() {
        return isCellsLicense;
    }

    /** {@inheritDoc} */
    @Override
    public final boolean isSlides() {
        return isSlidesLicense;
    }

    /** {@inheritDoc} */
    @Override
    public final boolean setTotal(final InputStream licenseStream) {
        if (licenseStream == null) {
            setAllFalse();
            return false;
        }
        try {
            byte[] licenseBytes = IOUtils.toByteArray(licenseStream);
            isTotalLicense = false;
            setPdf(new ByteArrayInputStream(licenseBytes));
            setWords(new ByteArrayInputStream(licenseBytes));
            setCells(new ByteArrayInputStream(licenseBytes));
            setSlides(new ByteArrayInputStream(licenseBytes));
            if (isPdfLicense && isWordsLicense && isCellsLicense && isSlidesLicense) {
                isTotalLicense = true;
            }
        } catch (Exception e) {
            setAllFalse();
        }
        return isTotalLicense;
    }

    /** {@inheritDoc} */
    @Override
    public final boolean setPdf(final InputStream licenseStream) {
        isPdfLicense = initialize(new com.aspose.pdf.License(), licenseStream);
        return isPdfLicense;
    }

    /** {@inheritDoc} */
    @Override
    public final boolean setWords(final InputStream licenseStream) {
        isWordsLicense = initialize(new com.aspose.words.License(), licenseStream);
        return isWordsLicense;
    }

    /** {@inheritDoc} */
    @Override
    public final boolean setCells(final InputStream licenseSteam) {
        isCellsLicense = initialize(new com.aspose.cells.License(), licenseSteam);
        return isCellsLicense;
    }

    /** {@inheritDoc} */
    @Override
    public final boolean setSlides(final InputStream licenseStream) {
        isSlidesLicense = initialize(new com.aspose.slides.License(), licenseStream);
        return isSlidesLicense;
    }

    /** Set all license flags to false. */
    private void setAllFalse() {
        isTotalLicense = false;
        isPdfLicense = false;
        isWordsLicense = false;
        isCellsLicense = false;
        isSlidesLicense = false;
    }

    /** Reflection initialization of license
     * because Aspose licenses are not inherited from one class or interface.
     * @param license Aspose license class
     * @param licenseStream license stream (file, bytes)
     * @return success of initialization
     */
    private boolean initialize(final Object license, final InputStream licenseStream) {
        try {
            if (licenseStream == null) {
                LOGGER.warn("Could not find license " + license.getClass().getCanonicalName());
                return false;
            }
            Method setLicenseMethod = license.getClass().getMethod("setLicense", InputStream.class);
            setLicenseMethod.invoke(license, licenseStream);
        } catch (Exception e) {
            LOGGER.error("Could not load license " + license.getClass().getCanonicalName(), e);
            return false;
        }
        return true;
    }
}
