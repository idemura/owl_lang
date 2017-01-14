java_library(
    name = 'compiler',
    srcs = glob([
        'src/main/owl/compiler/*.java',
    ]),
    deps = [
        '//lib:antlr4-runtime',
        '//lib:asm',
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
    name = 'compiler_test',
    srcs = glob([
        'src/test/owl/compiler/*.java',
    ]),
    deps = [
        ':compiler',
        '//lib:junit',
    ],
)

java_binary(
    name = 'owl_compiler_cli',
    manifest_file = 'src/main/resources/META-INF/MANIFEST.MF',
    deps = [
        ':compiler',
    ],
)
