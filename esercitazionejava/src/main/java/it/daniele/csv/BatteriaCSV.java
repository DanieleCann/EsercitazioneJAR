package it.daniele.csv;

import it.daniele.EsercitazioneJarMain;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class BatteriaCSV {
    public BatteriaCSV() {
    }
    public void leggiCSV() {
        String mieiBatteristiCSVPath = null;
        String[] INTESTAZIONE = {"nome", "marca"};
        try {
            mieiBatteristiCSVPath = Paths.get(ClassLoader.getSystemResource("Batteria.csv")
                    .toURI()).toString();
        } catch (URISyntaxException e) {
            logger.error("Errore nel trovare nel creare il file");
        }
        Reader in = null;
        try {
            in = new FileReader(mieiBatteristiCSVPath);
            // Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
            Iterable<CSVRecord> records =
                    CSVFormat.DEFAULT.builder().setSkipHeaderRecord(true)
                            .setHeader(INTESTAZIONE).build().parse(in);
            for (CSVRecord record : records) {
                String autore = record.get(INTESTAZIONE[0]);
                logger.info("Nome: " + autore);
                String titolo = record.get(1);
                logger.warn("Marca: " + titolo);
            }
        } catch (IOException e) {
            logger.error("Si è verificato un errore", e);
        }
    }
    private static final Logger logger = LogManager.getLogger(EsercitazioneJarMain.class);
    public void scriviCSV(){
        try {
            String batteriaCSVPath = null;
            try {
                batteriaCSVPath = Paths.get(ClassLoader.getSystemResource("Batteria.csv")
                        .toURI()).toString();
            } catch (URISyntaxException e) {
                logger.error("Errore nel trovare nel creare il file");
            }
            File parent = new File(batteriaCSVPath).getParentFile();
            String csvFile = parent.getAbsolutePath() + File.separator + "batteria.csv";
            File mioCSV = new File(csvFile);
            if(mioCSV.exists()){
                logger.debug("Elimino il vecchio CSV");
                mioCSV.delete();
            }
            boolean fileCreato = mioCSV.createNewFile();
            if(fileCreato){
                logger.debug("File CSV creato correttamente");
            }
            FileWriter out = new FileWriter(mioCSV);
            try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT)) {
                BATTERIE.forEach((author, title) -> {
                    try {
                        printer.printRecord(author, title);
                    } catch (IOException e) {
                        logger.error("Si è verificato un errore nel scrivere i miei autori", e);                    }
                });
            }
        } catch (IOException e) {
            logger.error("Si è verificato un errore", e);
        }
    }
    private Map<String, String> BATTERIE = new HashMap<>() {
        {
            put("Daniele", "Dw");
            put("Camillo", "Tama");
            put("Marco", "Mapex");
        }
    };
}

