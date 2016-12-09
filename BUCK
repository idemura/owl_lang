java_library(
    name = 'owl_lang',
    srcs = glob([
        'src/main/owl/lang/parser/*.java',
        'src/main/owl/lang/*.java',
    ]),
    deps = [
        '//lib:antlr4-runtime',
        '//lib:guava',
        '//lib:jcommander',
    ],
)

java_library(
    name = 'owl_runtime',
    srcs = glob([
        'src/main/owl/runtime/*.java',
    ]),
    deps = [],
)

java_test(
    name = 'owl_lang_test',
    srcs = glob([
        'src/test/owl/lang/*.java',
    ]),
    deps = [
        ':owl_lang',
        '//lib:guava',
        '//lib:junit',
    ],
)

java_binary(
    name = 'owl_lang_cli',
    manifest_file = 'src/main/resources/META-INF/MANIFEST.MF',
    deps = [
        ':owl_lang',
    ],
)

