# **AsposeConverter2Pdf**
This is an easy wrapper for Aspose Java libraries which you must buy.
This library converts MS Office documents and images to pdf documents in the best quality for Java.
 
**This is especially true for complex tables.** Other libraries could not do this with 
sufficient quality.

## Input formats
This library supports formats: doc, docx, xls, xlsx, ppt, pptx, rtf, odt, png, bmp, jpeg, jp2, jpf, tif.

## Dependency
Download library from releases.
Install it into your maven, gradle or other builder.
Example dependency for maven:
```html
<dependency>
    <groupId>ru.osokin</groupId>
    <artifactId>aspose-converter2pdf</artifactId>
    <version>1.0.1</version>
</dependency>
```

## Using
You can use this code for converting input document to pdf. Empty document constructor is a test mode for Aspose libraries.
```html
PdfFactory pdfFactory = new PdfFactory();
ConvertResult result = pdfFactory.convert(inputDocumentStream);
if (result.success()) {
    byte[] pdfDocument = result.pdfDocument();
}
```
If you have a Aspose license, you can set it. For example:
```html
License license = new AsposeLicense();
license.setTotal(inputLicenseSteam);
PdfFactory pdfFactory = new PdfFactory(license);
```
Also you can look at my tests for all document formats.

## Aspose licenses for Java
You can use any Aspose license: Pdf, Words, Cells, Slides or Total for Java.

If you convert only images then you must use Pdf license.

If you convert only Word documents then you must use Words license.

If you convert only Excel documents then you must use Cells license.

If you convert only PowerPoint presentations then you must use Slides license.

If you convert all of them then you must use Total license.

