package ru.osokin.pdf.aspose;

/** Result of conversion document to PDF.
 * @author Osokin Alexander
 * @since 1.0
 */
class ConvertResult {
    /** Result is successful or not. */
    private final boolean success;
    /** Result pdf document. */
    private final byte[] document;
    /** Error or warning message. */
    private final String resultMessage;

    /** Create result.
     * @param result result status
     * @param message error message
     */
    ConvertResult(final boolean result, final String message) {
        this(result, null, message);
    }

    /** Create result.
     * @param pdfDocument result pdf document
     */
    ConvertResult(final byte[] pdfDocument) {
        this(pdfDocument, "");
    }

    /** Create result.
     * @param pdfDocument result pdf document
     * @param message warning message
     */
    ConvertResult(final byte[] pdfDocument, final String message) {
        this(true, pdfDocument, message);
    }

    /** Create result.
     * @param success result status
     * @param document result pdf document
     * @param resultMessage error or warning message
     */
    private ConvertResult(final boolean success, final byte[] document, final String resultMessage) {
        this.success = success;
        this.document = document;
        this.resultMessage = resultMessage;
    }

    /** Get result status.
     * @return successful flag
     */
    boolean success() {
        return success;
    }

    /** Get result pdf document.
     * @return pdf document bytes
     */
    byte[] pdfDocument() {
        return document;
    }

    /** Get error or warning of conversion.
     * @return message
     */
    String resultMessage() {
        return resultMessage;
    }
}
