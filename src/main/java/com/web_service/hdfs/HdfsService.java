//package com.web_service.hdfs;
//
//import org.apache.commons.io.IOUtils;
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.FSDataInputStream;
//import org.apache.hadoop.fs.FileSystem;
//import org.apache.hadoop.fs.Path;
//
//import java.io.*;
//
//public class HdfsService {
//	public static void readFileFromHDFS() throws IOException {
//        Configuration configuration = new Configuration();
//        configuration.set("fs.defaultFS", "hdfs://10.8.0.1:9870");
//        ///user/csv/test/part-00000-80c9c331-1ffc-4c2c-8f0c-4fe97d0f1124-c000.csv
//        FileSystem fileSystem = FileSystem.get(configuration);
//        //Create a path
//        String fileName = "read_write_hdfs_example.txt";
//        Path hdfsReadPath = new Path("/user/csv/test/part-00000-80c9c331-1ffc-4c2c-8f0c-4fe97d0f1124-c000.csv");
//        //Init input stream
//        FSDataInputStream inputStream = fileSystem.open(hdfsReadPath);
//        //Classical input stream usage
//        System.out.println(inputStream.readChar());
//
//        /*BufferedReader bufferedReader = new BufferedReader(
//                new InputStreamReader(inputStream, StandardCharsets.UTF_8));
//
//        String line = null;
//        while ((line=bufferedReader.readLine())!=null){
//            System.out.println(line);
//        }*/
//
//        inputStream.close();
//        fileSystem.close();
//    }
//}
