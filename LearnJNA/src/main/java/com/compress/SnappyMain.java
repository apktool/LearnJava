package com.compress;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xerial.snappy.Snappy;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.ResourceBundle;

/**
 * @author li.wengang
 * @date 2018-12-17 13:54
 */
@CommandLine.Command(version = {"Compression/Decompressio 2.0", "Build li.wengang", "(c) 2018"})
public class SnappyMain {
    private static final Logger logger = LoggerFactory.getLogger(SnappyMain.class);
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("local");

    @CommandLine.Option(names = {"-h", "--help"}, description = "help")
    private boolean help = false;

    @CommandLine.Option(names = {"-v", "--version"}, description = "version")
    private boolean version = false;

    @CommandLine.Option(names = {"-c", "--compress"}, description = "Compress File")
    private boolean compress = false;

    @CommandLine.Option(names = {"-d", "--decompress"}, description = "Decompress File")
    private boolean decompress = false;

    @CommandLine.Option(names = {"-s", "--checksum"}, description = "Checksum File")
    private boolean checksum = false;

    @CommandLine.Option(names = {"-a", "--all"}, description = "Compress Files, Decompress Files and Checksum Files")
    private boolean all = false;

    @CommandLine.Parameters(paramLabel = "String", description = "One or more files to archive")
    private String[] fileList = new String[0];

    private static final String errMessage = "The Command is error, Please input again!";

    public static void main(String[] args) {
        SnappyMain app = new SnappyMain();
        CommandLine commandLine = new CommandLine(app);

        try {
            commandLine.parse(args);
        } catch (CommandLine.UnmatchedArgumentException e) {
            commandLine.usage(System.out);
            return;
        }

        if (app.version) {
            commandLine.printVersionHelp(System.out);
        } else if (app.help) {
            commandLine.usage(System.out);
        } else if (app.compress) {
            if (app.fileList.length == 0) {
                logger.info("Compress directory ...");
                operatDirectory("compress");
            } else if (app.fileList.length == 2) {
                logger.info("Compress file...");
                compress(app.fileList);
            } else {
                System.err.println(errMessage);
            }
        } else if (app.decompress) {
            if (app.fileList.length == 0) {
                logger.info("Decompress directory ...");
                operatDirectory("decompress");
            } else if (app.fileList.length == 2) {
                logger.info("Decompress file...");
                decompress(app.fileList);
            } else {
                System.err.println(errMessage);
            }
        } else if (app.checksum) {
            if (app.fileList.length == 0) {
                logger.info("Checksum directory ...");
                operatDirectory("checksum");
            } else if (app.fileList.length == 2) {
                logger.info("Checksum file...");
                checksum(app.fileList);
            } else {
                System.err.println(errMessage);
            }
        }
    }

    private static void compress(String[] args) {
        final File inPathName = new File(args[0]);
        final File outPathName = new File(args[1]);
        try {

            byte[] input = FileUtils.readFileToByteArray(inPathName);
            byte[] compressed = Snappy.compress(input);

            FileUtils.writeByteArrayToFile(outPathName, compressed);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void decompress(String[] args) {
        final File inPathName = new File(args[0]);
        final File outPathName = new File(args[1]);
        try {

            byte[] input = FileUtils.readFileToByteArray(inPathName);
            byte[] uncompressed = Snappy.uncompress(input);

            FileUtils.writeByteArrayToFile(outPathName, uncompressed);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void checksum(String[] args) {
        final File inPathName = new File(args[0]);
        final File outPathName = new File(args[1]);

        boolean isSame = false;

        try {
            isSame = FileUtils.checksumCRC32(inPathName) == FileUtils.checksumCRC32(outPathName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (isSame) {
            logger.info("SAME CONTENTS");
        } else {
            logger.info("DIFFERENT CONTENTS");
        }
    }

    private static void operatDirectory(String cmd) {
        if (cmd.equals("compress")) {
            File inPathName = new File(resourceBundle.getString("original.directory"));
            File outPathName = new File(resourceBundle.getString("compress.directory"));

            Iterator<File> fileIterator = FileUtils.iterateFiles(inPathName, null, true);
            while (fileIterator.hasNext()) {
                File file = fileIterator.next();

                String[] tmp = new String[2];
                tmp[0] = file.getAbsolutePath();
                tmp[1] = file.getAbsolutePath().replace(inPathName.getAbsolutePath(), outPathName.getAbsolutePath());

                compress(tmp);
            }
        } else if (cmd.equals("decompress")) {
            File inPathName = new File(resourceBundle.getString("compress.directory"));
            File outPathName = new File(resourceBundle.getString("decompress.directory"));

            Iterator<File> fileIterator = FileUtils.iterateFiles(inPathName, null, true);
            while (fileIterator.hasNext()) {
                File file = fileIterator.next();

                String[] tmp = new String[2];
                tmp[0] = file.getAbsolutePath();
                tmp[1] = file.getAbsolutePath().replace(inPathName.getAbsolutePath(), outPathName.getAbsolutePath());

                decompress(tmp);
            }
        } else if (cmd.equals("checksum")) {

            File inPathName = new File(resourceBundle.getString("original.directory"));
            File outPathName = new File(resourceBundle.getString("decompress.directory"));

            Iterator<File> fileIterator = FileUtils.iterateFiles(inPathName, null, true);
            while (fileIterator.hasNext()) {
                File file = fileIterator.next();

                String[] tmp = new String[2];
                tmp[0] = file.getAbsolutePath();
                tmp[1] = file.getAbsolutePath().replace(inPathName.getAbsolutePath(), outPathName.getAbsolutePath());

                checksum(tmp);
            }
        }
    }
}
