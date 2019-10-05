package com.glispa.csvad.utils;

import com.glispa.csvad.model.Ad;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.junit.Test;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CsvGenerator {
    public static final int FILE_COUNT = 10;
    public static final int ROW_PER_FILE = 100;
    public static final String FILE_NAME_PATTERN = "data/ad-list-";

    @Test
    public void generateCsvFiles() throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        for(int i = 1; i <= FILE_COUNT; i++) {


            try (Writer writer = Files.newBufferedWriter(Paths.get(FILE_NAME_PATTERN + i + ".csv"))) {
                StatefulBeanToCsv<Ad> beanToCsv = new StatefulBeanToCsvBuilder(writer)
                        .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                        .build();

                List<Ad> adList = generateAdList(ROW_PER_FILE, i);

                beanToCsv.write(adList);
            }
        }
    }

    //TODO

    private List<Ad> generateAdList(int rowPerFile, int i) {
        return null;
    }


}
