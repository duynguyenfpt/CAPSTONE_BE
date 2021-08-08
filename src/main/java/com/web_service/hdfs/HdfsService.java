package com.web_service.hdfs;

import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.*;

public class HdfsService {
	public static String readFileFromHDFS() throws IOException {
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
			Path inFile = new Path("/user/csv/test/part-00000-80c9c331-1ffc-4c2c-8f0c-4fe97d0f1124-c000.csv");

			// Check if file exists at the given location
			if (!fs.exists(inFile)) {
				throw new IOException("Input file not found");
			}
			OutputStream os = new ByteArrayOutputStream();
			in = fs.open(inFile);
			IOUtils.copyBytes(in, os, 512, false);
			String content = os.toString();
			os.close();
			in.close();
			
			return content;
		} catch (IOException e) {
			return "";
		} finally {
			IOUtils.closeStream(in);
		}
	}
}
