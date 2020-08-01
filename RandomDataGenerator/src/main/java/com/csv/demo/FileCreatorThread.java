package com.csv.demo;

import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author apktool
 * @package com.csv.demo
 * @class FileCreatorThread
 * @description TODO
 * @date 2020-07-28 22:43
 */
@NoArgsConstructor
public class FileCreatorThread extends Thread {
    private AtomicInteger startNo;

    private File folderPrefix;

    private Integer rowNum;

    public FileCreatorThread(Runnable target, File folder, Integer startNo, Integer rowNum) {
        super(target);
        this.folderPrefix = folder;
        this.startNo = new AtomicInteger(startNo);
        this.rowNum = rowNum;
    }

    @Override
    public void run() {
        AppRunner runner = new AppRunner(new File(folderPrefix + File.separator + Thread.currentThread().getName()), startNo.get());

        for (int i = startNo.get(); i < startNo.get() + rowNum; i++) {
            try {
                runner
                        .appendKeyToStream()
                        .appendDelimiterToStream(",")
                        .appendTimestampToStream()
                        .appendDelimiterToStream(",")
                        .appendLipsumToStream(10)
                        .appendDelimiterToStream("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        runner.build();
    }
}
