# **AsposeConverter2Pdf**
This an easy wrapper for Aspose Java libraries which you must buy.
Convert MS Office documents and images to pdf documents.

## Input formats
This library supports formats: doc, docx, xls, xlsx, ppt, pptx, rtf, odt, png, bmp, jpeg, jp2, jpf, tif.
## Using
You can use this code for converting input document to pdf. False in constructor is a test mode for Aspose.
```html
Converter2Pdf converter = new AsposeConverter2Pdf(false);
boolean result = converter.convert(docInputStream, pdfOutputStream);
```
If you have a Aspose license, you can set it. For example:
```html
converter.setTotalLicense(licenseInputSteam)
```
You can use any Aspose license: Pdf, Words, Cells, Slides or Total for Java.
