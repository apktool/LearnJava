package com.hadoop;


import com.hadoop.test.directory.ChecksumFiles;
import com.hadoop.test.file.ChecksumFile;
import com.hadoop.test.file.DecryptionFile;
import com.hadoop.test.file.EncryptionFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

import java.io.IOException;

/**
 * @author li.wengang
 * @date 2018-12-03 16:08
 */
@CommandLine.Command(version = {"Hadoop Encryption/Decryption 1.0", "Build li.wengang", "(c) 2018"})
public class Main1 {
    private static final Logger logger = LoggerFactory.getLogger(Main1.class);
    private static final String errMessage = "The Command is error, Please input again!";
    @CommandLine.Option(names = {"-h", "--help"}, description = "help")
    private boolean help = false;
    @CommandLine.Option(names = {"-v", "--version"}, description = "version")
    private boolean version = false;
    @CommandLine.Option(names = {"-e", "--encryption"}, description = "Encrypt File")
    private boolean encryption = false;
    @CommandLine.Option(names = {"-d", "--decryption"}, description = "Decrypt File")
    private boolean decryption = false;
    @CommandLine.Option(names = {"-s", "--checksum"}, description = "Checksum File")
    private boolean checksum = false;
    @CommandLine.Option(names = {"-a", "--all"}, description = "Encrypt Files, Decrypt Files and Checksum Files")
    private boolean all = false;
    @CommandLine.Parameters(paramLabel = "String", description = "One or more files to archive")
    private String[] fileList = new String[0];

    public static void main(String[] args) throws IOException {
        Main1 app = new Main1();
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
        } else if (app.encryption) {
            if (app.fileList.length == 0) {
                logger.info("Encrypt HDFS directory ...");
//                EncryptionFiles.main(app.fileList);
            } else if (app.fileList.length == 2) {
                logger.info("Encrypt HDFS file...");
                EncryptionFile.main(app.fileList);
            } else {
                System.out.println(errMessage);
            }
        } else if (app.decryption) {
            if (app.fileList.length == 0) {
                logger.info("Decrypt HDFS directory ...");
//                DecryptionFiles.main(app.fileList);
            } else if (app.fileList.length == 2) {
                logger.info("Decrypt HDFS file...");
                DecryptionFile.main(app.fileList);
            } else {
                System.out.println(errMessage);
            }
        } else if (app.checksum) {
            if (app.fileList.length == 0) {
                logger.info("Checksum HDFS directory ...");
                ChecksumFiles.main(app.fileList);
            } else if (app.fileList.length == 2) {
                logger.info("Checksum HDSF file...");
                ChecksumFile.main(app.fileList);
            } else {
                System.out.println(errMessage);
            }
        } else if (app.all) {
//            EncryptionFiles.main(args);
//            DecryptionFiles.main(args);
//            ChecksumFiles.main(args);
        } else {
            System.out.println(errMessage);
            commandLine.usage(System.out);
        }
    }
}
