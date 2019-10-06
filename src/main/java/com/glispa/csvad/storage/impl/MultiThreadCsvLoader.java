package com.glispa.csvad.storage.impl;

import com.glispa.csvad.model.Ad;
import com.glispa.csvad.storage.AdLoader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
public class MultiThreadCsvLoader implements AdLoader {
    @Value("${csv.dir}")
    private String csvDir;
    @Value("${csv.loader.thread.count}")
    private Integer threadCount;


    @Override
    public List<Ad> load(){
        try {
            ExecutorService executor = Executors.newFixedThreadPool(threadCount);

            List<Future<List<Ad>>> futureList = new ArrayList<>();
            try (Stream<Path> walk = Files.walk(Paths.get(csvDir))) {
                List<Path> filesList = walk.filter(Files::isRegularFile).collect(Collectors.toList());
                for(Path path: filesList) {
                    futureList.add(executor.submit(new CsvReaderWorker(path)));
                }
            }
            return processFutureList(futureList);
        } catch (Exception ex) {
            log.error("Error while loading data from csv files.", ex);
            throw new RuntimeException(ex);
        }
    }

    private List<Ad> processFutureList(List<Future<List<Ad>>> futureList) throws ExecutionException, InterruptedException {
        List<Ad> resultList = new ArrayList<>();
        Iterator<Future<List<Ad>>> iterator;
        Future<List<Ad>> tmp;
        while (!futureList.isEmpty()) {
            iterator = futureList.iterator();
            while (iterator.hasNext()) {
                tmp = iterator.next();
                if (tmp.isDone()) {
                    if(Objects.nonNull(tmp.get())) {
                        resultList.addAll(tmp.get());
                    }
                    iterator.remove();
                }
            }
        }
        return resultList;
    }

    class CsvReaderWorker implements Callable<List<Ad>> {
        private Path csvPath;

        public CsvReaderWorker(Path csvPath) {
            this.csvPath = csvPath;
        }

        @Override
        public List<Ad> call() throws IOException {
            log.info("Start to load data from file '{}'", csvPath.toString());
            Reader reader = Files.newBufferedReader(csvPath);
            CsvToBean<Ad> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Ad.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            List<Ad> result = csvToBean.parse();
            return result;
        }
    }
}
