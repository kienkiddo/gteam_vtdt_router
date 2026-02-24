package vn.gteam.reflect;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ConfigurationBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class GenerateReflectGenerator {
    private static final String BASE_PACKAGE = "vn.gteam";
    private static final String OUTPUT_PATH = "src/main/resources/META-INF/native-image/reflect-config.json";

    public static void main(String[] args) {
        try {
            System.out.println("🔍 Đang quét tất cả class có @MyReflection trong package: " + BASE_PACKAGE);

            // Quét tất cả class annotated
            Set<Class<?>> annotatedClasses = scanAnnotatedClasses(BASE_PACKAGE, MyReflection.class);
            Set<Class<?>> resultClasses = new HashSet<>(annotatedClasses);

            // Với mỗi class annotated → tìm class con
            for (Class<?> parent : annotatedClasses) {
                Set<Class<?>> subtypes = scanSubclassedClasses(BASE_PACKAGE, parent);
                resultClasses.addAll(subtypes);
            }

            if (resultClasses.isEmpty()) {
                System.out.println("⚠️ Không tìm thấy class nào.");
                return;
            }

            generateReflectConfig(resultClasses, OUTPUT_PATH);
            System.out.println("✅ Đã tạo reflect-config.json với " + resultClasses.size() + " class.");
        } catch (Exception e) {
            System.err.println("❌ Lỗi khi tạo reflect-config.json: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static Set<Class<?>> scanAnnotatedClasses(String basePackage, Class<?> annotationClass) {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .forPackages(basePackage)
                .addScanners(Scanners.TypesAnnotated)
        );
        return reflections.getTypesAnnotatedWith((Class<? extends java.lang.annotation.Annotation>) annotationClass);
    }

    private static Set<Class<?>> scanSubclassedClasses(String basePackage, Class<?> superClassOrInterface) {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .forPackages(basePackage)
                .addScanners(Scanners.SubTypes)
        );
        return reflections.getSubTypesOf((Class<Object>) superClassOrInterface);
    }

    private static void generateReflectConfig(Set<Class<?>> classes, String outputPath) throws IOException {
        List<Map<String, Object>> config = new ArrayList<>();

        for (Class<?> clazz : classes) {
            config.add(Map.of(
                    "name", clazz.getCanonicalName(),
                    "allDeclaredConstructors", true,
                    "allDeclaredFields", true,
                    "allDeclaredMethods", true
            ));
        }

        ObjectMapper mapper = new ObjectMapper();
        try (FileWriter writer = new FileWriter(outputPath)) {
            mapper.writerWithDefaultPrettyPrinter().writeValue(writer, config);
        }
    }

}
