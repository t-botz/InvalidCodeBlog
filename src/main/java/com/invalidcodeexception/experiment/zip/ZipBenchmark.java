package com.invalidcodeexception.experiment.zip;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * User: Tibo
 * Date: 7/02/13
 */
public class ZipBenchmark
{

    private File dirToCompress = new File("/Users/shameemah/Workspaces/Skaffold/out");
    private File fileOutput = new File("/tmp/test.zip");

    public static void main(String[] args) throws IOException
    {
        ZipBenchmark zipBenchmark = new ZipBenchmark();
        zipBenchmark.doJavaUtilZip();
        zipBenchmark.doCommonsCompressZip();
    }


    public void doJavaUtilZip() throws IOException
    {
        long startTime = System.currentTimeMillis();
        FileOutputStream out = new FileOutputStream(fileOutput);
        javaUtilZipDir(dirToCompress, new ZipOutputStream(out));
        out.flush();
        out.close();
        long endTime = System.currentTimeMillis();
        System.out.println("Time :" + (endTime - startTime) + "ms");
        System.out.println("File size : "+ fileOutput.getTotalSpace());
    }

    public void javaUtilZipDir(File dir2zip, ZipOutputStream zos) throws IOException
    {
        byte[] readBuffer = new byte[2156];
        int bytesIn = 0;
        for (File f : dir2zip.listFiles())
        {
            if (f.isDirectory())
            {
                javaUtilZipDir(f, zos);
            }
            else
            {
                FileInputStream fis = new FileInputStream(f);
                ZipEntry anEntry = new ZipEntry(f.getPath());
                zos.putNextEntry(anEntry);
                //now write the content of the file to the ZipOutputStream
                while ((bytesIn = fis.read(readBuffer)) != -1)
                {
                    zos.write(readBuffer, 0, bytesIn);
                }
                //close the Stream
                fis.close();
            }

        }
        zos.finish();
    }

    public void doCommonsCompressZip() throws IOException
    {
        long startTime = System.currentTimeMillis();
        ZipArchiveOutputStream zout = new ZipArchiveOutputStream(fileOutput);
        commonsCompressDir(dirToCompress, zout);
        zout.flush();
        zout.close();
        long endTime = System.currentTimeMillis();
        System.out.println("Time :" + (endTime - startTime) + "ms");
        System.out.println("File size : "+ fileOutput.getTotalSpace());
    }

    public void commonsCompressDir(File dir2zip, ZipArchiveOutputStream zos) throws IOException
    {
        byte[] readBuffer = new byte[2156];
        int bytesIn = 0;
        for (File f : dir2zip.listFiles())
        {
            if (f.isDirectory())
            {
                commonsCompressDir(f, zos);
            }
            else
            {
                FileInputStream fis = new FileInputStream(f);
                ZipArchiveEntry anEntry = new ZipArchiveEntry(f.getPath());
                zos.putArchiveEntry(anEntry);
                //now write the content of the file to the ZipOutputStream
                while ((bytesIn = fis.read(readBuffer)) != -1)
                {
                    zos.write(readBuffer, 0, bytesIn);
                }
                zos.closeArchiveEntry();
                //close the Stream
                fis.close();
            }

        }
    }
}