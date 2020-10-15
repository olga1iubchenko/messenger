package com.javamentoringprogram.messenger.junittests.extensions;

import org.junit.jupiter.api.extension.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 *
 */
public class TemporaryDirectoryExtension implements AfterEachCallback, TestInstancePostProcessor, ParameterResolver {

    private final List<TemporaryDirectory> temporaryDirectoryList_;

    public TemporaryDirectoryExtension() {
        temporaryDirectoryList_ = new ArrayList<>();
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        temporaryDirectoryList_.forEach(TemporaryDirectory::close);
        temporaryDirectoryList_.clear();
    }


    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == TemporaryDirectory.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return createTemporaryDirectory();
    }


    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext context) throws Exception {
        Stream.of(testInstance.getClass().getDeclaredFields())
                .filter(field -> field.getType() == TemporaryDirectory.class)
                .forEach(field -> {
                    field.setAccessible(true);
                    TemporaryDirectory t = createTemporaryDirectory();
                    try {
                        field.set(testInstance, t);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    private TemporaryDirectory createTemporaryDirectory() {
        TemporaryDirectory t = new TemporaryDirectory();
        temporaryDirectoryList_.add(t);
        return t;
    }
}