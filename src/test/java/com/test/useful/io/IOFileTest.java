package com.test.useful.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOFileTest {

	public static void main(String[] args) throws Exception{
		File file01 = new File("fileTest01.txt");
		File file02 = new File("fileTest02.txt");
		file01.createNewFile();
		file02.createNewFile();
		
		FileOutputStream fileOutputStream = new FileOutputStream("fileTest01.txt");
		String str = "这是io测试文件";
		fileOutputStream.write(str.getBytes());
		fileOutputStream.close();
		
		InputStream in = new FileInputStream("fileTest01.txt");
		BufferedInputStream bin = new BufferedInputStream(in);
		
		OutputStream out = new FileOutputStream("fileTest02.txt");
		BufferedOutputStream bout = new BufferedOutputStream(out);
		
		byte[] buffer = new byte[10];
		int len = 0;
		while((len = bin.read(buffer)) != -1) {
			bout.write(buffer,0,len);
			System.out.println(buffer);
		}
		in.close();
		bin.close();
		bout.close();
		out.close();
	}
	
	public void IOTest() throws IOException{
		File file = new File("IOTest.txt");
		// 检测文件是否存在
		boolean exists = file.exists();
		System.out.println(exists);
		System.out.println(file.length());
		// 文件重命名
		file.renameTo(new File("IoTest.txt"));
		File fmk = new File("IoFile");
		// 创建一个文件
		fmk.createNewFile();
		// 删除文件
        fmk.delete();
		// 创建文件夹
		fmk.mkdir();
	}

}
