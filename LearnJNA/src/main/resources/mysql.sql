CREATE TABLE compress_test (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    compress_type ENUM('HARDWARECOMPRESS', 'SOFTWARECOMPRESS') COMMENT '压缩类型',
    file_name VARCHAR(255) COMMENT '文件名',
    file_size_before INT COMMENT '压缩前文件大小',
    file_size_after INT COMMENT '压缩后文件大小',
    compression_ratio FLOAT COMMENT '压缩率',
    compression_speed FLOAT COMMENT '压缩速度',
    start_time TIMESTAMP COMMENT '开始测试时间',
    duration INT COMMENT '持续时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE decompress_test (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    decompress_type ENUM('HARDWAREDECOMPRESS', 'SOFTWAREDECOMPRESS') COMMENT '解压类型',
    file_name VARCHAR(255) COMMENT '文件名',
    file_size_before INT COMMENT '解压前文件大小',
    file_size_after INT COMMENT '解压后文件大小',
    decompression_ratio FLOAT COMMENT '解压率',
    decompression_speed FLOAT COMMENT '解压速度',
    start_time TIMESTAMP COMMENT '开始测试时间',
    duration INT COMMENT '持续时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE checksum_test (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    original_file_name VARCHAR(255) COMMENT '原始文件名',
    decompress_file_name VARCHAR(255) COMMENT '解压后文件名',
    is_same BOOLEAN COMMENT '原始文件是否和解压文件相同',
    start_time TIMESTAMP COMMENT '开始测试时间',
    duration INT COMMENT '持续时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB CHARSET=utf8;