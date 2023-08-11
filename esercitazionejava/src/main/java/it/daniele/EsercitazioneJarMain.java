package it.daniele;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class EsercitazioneJarMain {
    private static final Logger logger = LogManager.getLogger(EsercitazioneJarMain.class);
    public static void main(String[] args){
        logger.info("applicazione si sta avviando correttamente");
        logger.warn("le seguenti saranno una prova di log");
        logger.debug("questo è un esempio di messaggio in debug");
        logger.error("questo è un esempio di errore");

    }
}
