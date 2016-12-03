java_library(
    name = 'owl_lang',
    srcs = glob([
        'src/main/owl/lang/parser/*.java',
        'src/main/owl/lang/*.java',
    ]),
    deps = [
        '//lib:antlr4-runtime',
        '//lib:jcommander',
    ],
)

java_test(
    name = 'owl_lang_test',
    srcs = glob([
        'src/test/owl/lang/*.java',
    ]),
    deps = [
        ':owl_lang',
        '//lib:junit',
    ],
)

java_binary(
    name = 'owl_lang_cli',
    main_class = 'owl.lang.CLI',
    deps = [
        ':owl_lang',
    ],
)
