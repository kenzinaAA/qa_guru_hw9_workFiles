package guru.qa.homework;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.nio.charset.StandardCharsets;

import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipParsingTest {
    private final ClassLoader cl = ZipParsingTest.class.getClassLoader();

    @DisplayName("Проверка наличия xlsx файла в zip архиве и его чтение")
    @Test
    void xlsxFindAndReadInZipFile () throws Exception {
        try (ZipInputStream zip = new ZipInputStream(cl.getResourceAsStream("orgStructure.zip")
        )){
            ZipEntry entry;
            while ((entry = zip.getNextEntry()) != null) {
                if (entry.getName().endsWith(".xlsx")) {
                    XLS xlsx = new XLS(zip);
                    String findValue = xlsx.excel.getSheetAt(0).getRow(3).getCell(6).getStringCellValue();
                    Assertions.assertTrue(findValue.contains("nbuchelnikov@company.ru"));
                }
            }
        }
    }
    @DisplayName("Проверка наличия pdf файла в zip архиве и его чтение")
    @Test
    void pdfFindAndReadInZipFile () throws Exception {
        try (ZipInputStream zip = new ZipInputStream(cl.getResourceAsStream("orgStructure.zip")
        )){
            ZipEntry entry;
            while ((entry = zip.getNextEntry()) != null) {
                if (entry.getName().endsWith(".pdf")) {
                    PDF pdf = new PDF(zip);
                    Assertions.assertEquals("contacts.xls",pdf.title);
                }
            }
        }
    }
    @DisplayName("Проверка наличия csv файла в zip архиве и его чтение")
    @Test
    void csvFindAndReadInZipFile () throws Exception {
        try (ZipInputStream zip = new ZipInputStream(cl.getResourceAsStream("orgStructure.zip")
        )){
            ZipEntry entry;
            while ((entry = zip.getNextEntry()) != null) {
                if (entry.getName().endsWith(".csv")) {
                    List<String[]> data;
                    try (
                            CSVReader csvReader = new CSVReaderBuilder(
                                    new InputStreamReader(zip, StandardCharsets.UTF_8)
                            ).withCSVParser(new CSVParserBuilder().withSeparator(';').build()).build()
                    ) {
                        data = csvReader.readAll();

                        Assertions.assertEquals(11, data.size());
                        Assertions.assertArrayEquals(
                                new String[]{"Идентификатор", "Вышестоящий отдел", "Название", "Адрес"},
                                data.get(0));
                        Assertions.assertArrayEquals(
                                new String[]{"OU003","OU002","Отдел сервисного обслуживания","Екатеринбург, Луначарского 180"},
                                data.get(6)
                        );
                        break;
                    }
                }
            }
        }
    }}

