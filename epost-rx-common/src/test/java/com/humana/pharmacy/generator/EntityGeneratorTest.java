package com.humana.pharmacy.generator;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EntityGeneratorTest {
    /**
     * The model directory.
     */
    private static final String GENERATE_DIR_MODEL =
            "./src/main/java/com/humana/pharmacy/common/model";
    /**
     * The entity directory.
     */
    private static final String GENERATE_DIR_ENTITY =
            "./src/main/java/com/humana/pharmacy/common/entity";
    /**
     * The generated model classes.
     */
    private static final String[] MODEL_FILES = {
            "CodeValue.java",
            "EScriptMsgAttributes.java",
            "OrderHeader.java",
            "OrderLines.java",
            "Products.java",
            "RuleQueueException.java",
            "Rules.java",
            "WorkflowQueueList.java",
            "WorkflowQueueNames.java"
    };
    /**
     * The generated entity classes.
     */
    private static final String[] ENTITY_FILES = {
            "QCodeValue.java",
            "QEScriptMsgAttributes.java",
            "QOrderHeader.java",
            "QOrderLines.java",
            "QProducts.java",
            "QRuleQueueException.java",
            "QRules.java",
            "QWorkflowQueueList.java",
            "QWorkflowQueueNames.java"
    };

    private final String[] args = {"src/main/resources/db_config.properties"};

    @Test
    public void test_main_argsNull() {
        assertThrows(RuntimeException.class, () -> EntityGenerator.main(null));
    }

    @Test
    public void test_main_argsEmpty() {
        assertThrows(RuntimeException.class, () -> EntityGenerator.main(new String[0]));
    }

    @Test
    public void test_main_argsContainsEmpty() {
        assertThrows(RuntimeException.class, () -> EntityGenerator.main(new String[]{" "}));
    }

    @Test
    public void test_main_argsContainsInvalid1() {
        assertThrows(
                RuntimeException.class,
                () ->
                        EntityGenerator.main(
                                new String[]{"src/test/resources/db_config_invalid1.properties"}));
    }

    @Test
    public void test_main_argsContainsInvalid2() {
        assertThrows(
                RuntimeException.class,
                () ->
                        EntityGenerator.main(
                                new String[]{"src/test/resources/db_config_invalid2.properties"}));
    }

    @Test
    public void test_main_configContainsInvalid1() {
        assertThrows(
                RuntimeException.class, () -> EntityGenerator.main(new String[]{"invalid_\n_file"}));
    }

    @Test
    public void test_main() {
        clearDir(new File(GENERATE_DIR_MODEL));
        clearDir(new File(GENERATE_DIR_ENTITY));

        EntityGenerator.main(args);

        checkFiles(new File(GENERATE_DIR_MODEL), MODEL_FILES);
        checkFiles(new File(GENERATE_DIR_ENTITY), ENTITY_FILES);

//    clearDir(new File(GENERATE_DIR_MODEL));
//    clearDir(new File(GENERATE_DIR_ENTITY));
    }

    /**
     * Check if the files exist.
     *
     * @param dir       the directory
     * @param fileNames the file names
     */
    private void checkFiles(File dir, String[] fileNames) {
        Arrays.stream(fileNames)
                .forEach(
                        fileName -> {
                            try {
                                assertTrue(new File(dir, fileName).exists());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
    }

    /**
     * CLear the directory.
     *
     * @param dir the directory.
     */
    private void clearDir(File dir) {
        try {
            File[] files = dir.listFiles();
            if (files != null && files.length > 0) {
                Arrays.stream(files)
                        .forEach(
                                file -> {
                                    if (!file.isDirectory()) {
                                        file.delete();
                                    }
                                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
