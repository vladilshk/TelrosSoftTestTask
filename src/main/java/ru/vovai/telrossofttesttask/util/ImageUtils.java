package ru.vovai.telrossofttesttask.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ImageUtils {

    public static byte[] compressImage(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4 * 1024];
        while (!deflater.finished()) {
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }
        //TODO: check exception
        try {
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return outputStream.toByteArray();
    }

    public static byte[] decompressImage(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        byte[] tmp = new byte[4 * 1024];
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        try {
            while (!inflater.finished()) {
                int size = inflater.inflate(tmp);
                outputStream.write(tmp, 0, size);
            }

            outputStream.close();
        } catch (Exception e) {
            //TODO: check exception
            throw new RuntimeException(e);
        }
        return outputStream.toByteArray();
    }
}
