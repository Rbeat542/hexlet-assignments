package exercise;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.Arrays;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.File;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ExecutionException;

class App {
    private static String path1;
    private static String path2;
    private static String content1;
    private static String content2;


    // BEGIN




    public static CompletableFuture<String> unionFiles(String path1, String path2, String path3) throws ExecutionException, InterruptedException {

        CompletableFuture<String> result1 = CompletableFuture.supplyAsync(() -> {
            Path pathOfFileOne = Paths.get(path1).toAbsolutePath().normalize();
            try {
                content1 = Files.readString(pathOfFileOne);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return content1;
        });

        CompletableFuture<String> result2 = CompletableFuture.supplyAsync(() -> {
            Path pathOfFileOne = Paths.get(path2).toAbsolutePath().normalize();
            try {
                content2 = Files.readString(pathOfFileOne);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return content2;
        });

        return result1.thenCombine(result2, (string1, string2) -> {
            var string3 = string1 + string2;
            System.out.println(string3);
            var path = Path.of(path3);
            try {
                Files.writeString(path, string3);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            return string3;
        }).exceptionally(ex -> {
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            return null;
        });
        // END
    }

    public static void main(String[] args) throws Exception {
        // BEGIN
        String path1 = "src/main/resources/file1.txt";
        String path2 = "src/main/resources/file2.txt";
        String path3 = "src/main/resources/file3.txt";

        unionFiles(path1, path2, path3).get();

        // END
    }
}

