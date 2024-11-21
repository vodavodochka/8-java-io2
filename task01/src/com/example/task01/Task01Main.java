package com.example.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:

        /*
        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));
        */
    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {
        String command = String.format("ffprobe -v error -of flat -show_format %s", file.getAbsolutePath());
        Process process = Runtime.getRuntime().exec(command);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String out;
        while ((out = reader.readLine()) != null) {
            if (out.startsWith("format.tags.title")) {
                String[] parts = out.split("=");
                if (parts.length == 2) {
                    return parts[1].replace("\"", "");
                }
            }
        }
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new InterruptedException("Process exited with code " + exitCode);
        }
        return "sound name";
    }
}
