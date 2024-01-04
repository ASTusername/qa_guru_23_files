package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.fail;

public class FilesTest {
    private ClassLoader cl = FilesTest.class.getClassLoader();

    @DisplayName("проверка CSV из архива ZIP")
    @Test
    void zipFileParsingCSVTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("my_homework.zip")
        )) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals("cian.csv")) {
                    CSVReader csv = new CSVReader(new InputStreamReader(zis));
                    List<String[]> data = csv.readAll();
                    Assertions.assertEquals(2, data.size());
                    return;
                }
            }
            fail("Отсутствует искомый файл");
        }
    }

    @DisplayName("проверка XLSX из архива ZIP")
    @Test
    void zipFileParsingXLSXTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("my_homework.zip")
        )) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals("cian.xlsx")) {
                    XLS xls = new XLS(zis);
                    String actualValue = xls.excel.getSheetAt(0).getRow(4).getCell(2).getStringCellValue();
                    Assertions.assertTrue(actualValue.contains("Москва"));
                    return;
                }
            }
            fail("Отсутствует искомый файл");
        }
    }

    @DisplayName("проверка PDF из архива ZIP")
    @Test
    void zipFileParsingPDFTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("my_homework.zip")
        )) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals("Resume.pdf")) {
                    PDF pdf = new PDF(zis);
                    Assertions.assertTrue(pdf.text.contains("QA инженер"));
                    return;
                }
            }
            fail("Отсутствует искомый файл");
        }
    }
}
