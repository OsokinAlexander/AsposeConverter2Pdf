# **AsposeConverter2Pdf**
This an easy wrapper for Aspose Java libraries which you must buy.
Convert MS Office documents and images to pdf documents.

## Input formats
This library supports formats: doc, docx, xls, xlsx, ppt, pptx, rtf, odt, png, bmp, jpeg, jp2, jpf, tif.

## Using
You can use this code for converting input document to pdf. Empty document constructor is a test mode for Aspose libraries.
```html
Document document = new Document();
ConvertResult result = document.convert(inputDocumentStream);
if (result.success()) {
    byte[] pdfDocument = result.pdfDocument();
}
```
If you have a Aspose license, you can set it. For example:
```html
License license = new AsposeLicense();
license.setTotal(inputLicenseSteam);
Document document = new Document(license);
```
Also you can look at my tests for all document formats.

## Aspose licenses for Java
You can use any Aspose license: Pdf, Words, Cells, Slides or Total for Java.

If you convert only images then you must use Pdf license.

If you convert only Word documents then you must use Words license.

If you convert only Excel documents then you must use Cells license.

If you convert only PowerPoint presentations then you must use Slides license.

If you convert all of them then you must use Total license.

