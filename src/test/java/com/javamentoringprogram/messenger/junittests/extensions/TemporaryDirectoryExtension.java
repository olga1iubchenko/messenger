package com.javamentoringprogram.messenger.junittests.extensions;

import lombok.SneakyThrows;
import org.junit.jupiter.api.extension.*;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import static org.mockito.Matchers.any;


public class TemporaryDirectoryExtension implements ParameterResolver, AfterEachCallback {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(Path.class);
    }

    @SneakyThrows
    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)  {
       return extensionContext.getStore(ExtensionContext.Namespace.GLOBAL).getOrComputeIfAbsent("root", key -> {
           try {
               return createTempDir();
           } catch (IOException e) {
               e.printStackTrace();
           }
           return null;
       });
    }

    private Path createTempDir() throws IOException {
       try{
           return Files.createTempDirectory("tmpTestSir");
       }catch(IOException e){
           throw new ParameterResolutionException("couldn't create temp dir", e);
       }

    }

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
             Object root = extensionContext.getStore(any()).get("root", Path.class);
             if(root != null){
                 deleteRecursively((Path) root);
             }
    }

    private void deleteRecursively (Path root) throws IOException {
        Files.walkFileTree(root, new SimpleFileVisitor<Path>(){

            @Override
           public  FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException{
             Files.delete(file);
             return super.visitFile(file, attrs);
        }

              @Override
             public FileVisitResult postVisitDirectory (Path dir, IOException exc) throws IOException{
                Files.delete(dir);
                return super.postVisitDirectory(dir, exc);
              }
    });

    }
}

