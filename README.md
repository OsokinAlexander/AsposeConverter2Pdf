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
```
If you have a Aspose license, you can set it. For example:
```html
License license = new AsposeLicense();
license.setTotal(inputLicenseSteam);
Document document = new Document(license);
```
You can use any Aspose license: Pdf, Words, Cells, Slides or Total for Java.
Also you can look at my tests
