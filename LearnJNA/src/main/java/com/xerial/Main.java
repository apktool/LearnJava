package com.xerial;

import com.xerial.test.directory.ChecksumFiles;
import com.xerial.test.directory.CompressionFiles;
import com.xerial.test.directory.DecompressionFiles;
import com.xerial.test.file.ChecksumFile;
import com.xerial.test.file.CompressionFile;
import com.xerial.test.file.DecompressionFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

import java.util.ResourceBundle;

/**
 * @author li.wengang
 * @date 2018-12-17 13:54
 */
@CommandLine.Command(version = {"Xerial Compression/Decompressio 2.0", "Build li.wengang", "(c) 2018"})
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("local");
    private static final String errMessage = "The Command is error, Please input again!";
    @CommandLine.Option(names = {"-h", "--help"}, description = "help")
    private boolean help = false;
    @CommandLine.Option(names = {"-v", "--version"}, description = "version")
    private boolean version = false;
    @CommandLine.Option(names = {"-c", "--jna"}, description = "Compress File")
    private boolean compress = false;
    @CommandLine.Option(names = {"-d", "--decompress"}, description = "Decompress File")
    private boolean decompress = false;
    @CommandLine.Option(names = {"-s", "--checksum"}, description = "Checksum File")
    private boolean checksum = false;
    @CommandLine.Option(names = {"-a", "--all"}, description = "Compress Files, Decompress Files and Checksum Files")
    private boolean all = false;
    @CommandLine.Parameters(paramLabel = "String", description = "One or more files to archive")
    private String[] fileList = new String[0];

    public static void main(String[] args) {
        Main app = new Main();
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
                CompressionFiles.main(app.fileList);
            } else if (app.fileList.length == 2) {
                logger.info("Compress file...");
                CompressionFile.main(app.fileList);
            } else {
                System.err.println(errMessage);
            }
        } else if (app.decompress) {
            if (app.fileList.length == 0) {
                logger.info("Decompress directory ...");
                DecompressionFiles.main(app.fileList);
            } else if (app.fileList.length == 2) {
                logger.info("Decompress file...");
                DecompressionFile.main(app.fileList);
            } else {
                System.err.println(errMessage);
            }
        } else if (app.checksum) {
            if (app.fileList.length == 0) {
                logger.info("Checksum directory ...");
                ChecksumFiles.main(app.fileList);
            } else if (app.fileList.length == 2) {
                logger.info("Checksum file...");
                ChecksumFile.main(app.fileList);
            } else {
                System.err.println(errMessage);
            }
        }
    }
}
