package com.glispa.csvad.utils;

import com.glispa.csvad.model.Ad;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CsvGenerator {
    public static final int FILE_COUNT = 10;
    public static final int LINES_PER_FILE = 100;
    public static final String FILE_NAME_PATTERN = "src/test/resources/data/ad-list-";
    public static final List<String> CAMPAIGN_LIST = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "X", "Y", "Z");

    @Ignore
    @Test
    public void generateCsvFiles() throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        for(int i = 0; i < FILE_COUNT; i++) {
            Path path = Paths.get(FILE_NAME_PATTERN + i + ".csv");
            try {
                Files.createFile(path);
            } catch (FileAlreadyExistsException ex) {
                //do nothing
            }
            try (Writer writer = Files.newBufferedWriter(path)) {
                StatefulBeanToCsv<Ad> beanToCsv = new StatefulBeanToCsvBuilder(writer)
                        .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                        .build();
                List<Ad> adList = generateAdList(LINES_PER_FILE, i);
                beanToCsv.write(adList);
            }
        }
    }
    private List<Ad> generateAdList(int linesPerFile, int fileNumber) {
        List<Ad> resultList = new ArrayList<>();
        Ad ad;
        Random random = new Random();
        for(int i = 0; i < linesPerFile; i++) {
            ad = new Ad();
            ad.setIndex(i + 1);
            ad.setId(String.valueOf(i + 1 + fileNumber * linesPerFile));
            ad.setName(RandomStringUtils.randomAlphabetic(10));
            ad.setDescription(RandomStringUtils.randomAlphabetic(20));
            ad.setCategory(RandomStringUtils.randomAlphabetic(4));
            ad.setCampaign(CAMPAIGN_LIST.get(random.nextInt(CAMPAIGN_LIST.size())));
            ad.setUrl("http://" + RandomStringUtils.randomAlphabetic(5) + ".com");
            ad.setNurl("http://" + RandomStringUtils.randomAlphabetic(5) + ".com");
            ad.setCustomerName(RandomStringUtils.randomAlphabetic(5));
            resultList.add(ad);
        }
        return resultList;
    }
}
