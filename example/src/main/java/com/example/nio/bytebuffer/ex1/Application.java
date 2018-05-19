package com.example.nio.bytebuffer.ex1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Application {

  public static void main(String[] args) {
    try (FileOutputStream binFile = new FileOutputStream("data.dat");
         FileChannel binChannel = binFile.getChannel()) {

      byte[] outputBytes = "Hello World!".getBytes();
      ByteBuffer buffer = ByteBuffer.wrap(outputBytes);
      int numBytes = binChannel.write(buffer);
      System.out.println("numBytes written was: " + numBytes);

      ByteBuffer intBuffer = ByteBuffer.allocate(Integer.BYTES);
      intBuffer.putInt(245);
      intBuffer.flip();
      numBytes = binChannel.write(intBuffer);
      System.out.println("numBytes written was: " + numBytes);

      intBuffer.flip();
      intBuffer.putInt(-98765);
      intBuffer.flip();
      numBytes = binChannel.write(intBuffer);
      System.out.println("numBytes written was: " + numBytes);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
