package com.hongkai.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileUtils {

    public static void copy(String srcPathStr, String desPathStr)
    {
        //获取源文件的名称
        String newFileName = srcPathStr.substring(srcPathStr.lastIndexOf(File.separator)+1); //目标文件地址
        System.out.println("源文件:"+srcPathStr);
        desPathStr = desPathStr + File.separator + newFileName; //源文件地址
        System.out.println("目标文件地址:"+desPathStr);
        try
        {
            FileInputStream fis = new FileInputStream(srcPathStr);//创建输入流对象
            FileOutputStream fos = new FileOutputStream(desPathStr); //创建输出流对象
            byte datas[] = new byte[1024*8];//创建搬运工具
            int len = 0;//创建长度
            while((len = fis.read(datas))!=-1)//循环读取数据
            {
                fos.write(datas,0,len);
            }
            fis.close();//释放资源
            fis.close();//释放资源
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static String copy(File srcFile, String desPathStr)
    {
        //获取源文件的名称
        String newFileName = srcFile.getPath().substring(srcFile.getPath().lastIndexOf(File.separator)+1); //目标文件地址
        System.out.println("源文件:"+srcFile.getPath());
        desPathStr = desPathStr + File.separator + newFileName; //源文件地址
        System.out.println("目标文件地址:"+desPathStr);
        try
        {
            FileInputStream fis = new FileInputStream(srcFile);//创建输入流对象
            FileOutputStream fos = new FileOutputStream(desPathStr); //创建输出流对象
            byte buffer[] = new byte[1024];//创建搬运工具

            while(fis.read(buffer)!=-1)//循环读取数据
            {
                fos.write(buffer);
            }
            fis.close();//释放资源
            fis.close();//释放资源
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return desPathStr;
    }
}
