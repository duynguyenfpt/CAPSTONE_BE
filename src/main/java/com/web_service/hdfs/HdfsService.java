package com.web_service.hdfs;

import org.apache.hadoop.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HdfsService {
	public String readFileFromHDFS(String path) throws IOException {
		Configuration conf = new Configuration();
		conf.addResource(new Path("/etc/hadoop/conf/hdfs-site.xml"));
		conf.addResource(new Path("/etc/hadoop/conf/core-site.xml"));
		conf.set("hadoop.security.authentication", "kerberos");
		conf.set("fs.defaultFS", "hdfs://127.0.0.1:9000");
		FSDataInputStream in = null;
		// OutputStream o	ut = null;
		try {
			FileSystem fs = FileSystem.get(conf);
			// Input file path
			Path inFile = new Path(path);
			// Check if file exists at the given location
			if (!fs.exists(inFile)) {
				throw new IOException("Input file not found");
			}
			OutputStream os = new ByteArrayOutputStream();
			in = fs.open(inFile);
	        BufferedReader br = new BufferedReader( new InputStreamReader( in, "UTF-8" ) );

//	        String lineRead = br.readLine();
//			System.out.println(lineRead);

//			IOUtils.copyBytes(in, os, 512, false);
//			String content = os.toString();
	        String content = "";
	        String line;
	        int totalLine = 10;
			while ((line = br.readLine()) != null && totalLine >= 0) {
				if(totalLine != 10) line = "\n" + line;
				content = content.concat(line);
				totalLine--;
			}
			os.close();
			in.close();
			
			return content;
		} catch (IOException e) {
			return "";
		} finally {
			IOUtils.closeStream(in);
		}
	}
	
	public void getFile(String path) throws IOException {
		Configuration conf = new Configuration();
		conf.addResource(new Path("/etc/hadoop/conf/hdfs-site.xml"));
		conf.addResource(new Path("/etc/hadoop/conf/core-site.xml"));
		conf.set("hadoop.security.authentication", "kerberos");
		conf.set("fs.defaultFS", "hdfs://127.0.0.1:9000");
		FSDataInputStream in = null;
		// OutputStream out = null;
		try {
			FileSystem fs = FileSystem.get(conf);
			// Input file path
			Path inFile = new Path(path);			
			// Check if file exists at the given location
			if (!fs.exists(inFile)) {
				throw new IOException("Input file not found");
			}
			OutputStream os = new ByteArrayOutputStream();
			in = fs.open(inFile);

			IOUtils.copyBytes(in, os, 512, false);
			String content = os.toString();
			
			System.out.println("1");

			// Check if file exists at the given location
			String fileName = "/home/public/data.txt";
			File targetFile = new File(fileName);
			targetFile.delete();
		    java.nio.file.Path newFilePath = Paths.get(fileName);
			Files.createFile(newFilePath);
			BufferedWriter writer = new BufferedWriter(new FileWriter(targetFile));
			
			writer.write(content);
			writer.flush();
			writer.close();
		} catch (IOException e) {
//			return null;
		} finally {
			IOUtils.closeStream(in);
		}
	}
}
