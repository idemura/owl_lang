prebuilt_jar(
  name = 'lib_antlr',
  binary_jar = 'lib/antlr4-runtime-4.5.3.jar',
)

prebuilt_jar(
  name = 'lib_junit',
  binary_jar = 'lib/junit-4.12.jar',
)

prebuilt_jar(
  name = 'lib_hamcrest',
  binary_jar = 'lib/hamcrest-all-1.3.jar',
)

java_library(
  name = 'owl_lang',
  srcs = glob([
    'src/main/owl/lang/parser/*.java',
    'src/main/owl/lang/*.java',
  ]),
  deps = [
    ':lib_antlr',
  ],
)

java_test(
  name = 'owl_lang_test',
  srcs = glob([
    'src/test/owl/lang/*.java',
  ]),
  deps = [
    ':owl_lang',
    ':lib_hamcrest',
    ':lib_junit',
  ],
)

java_binary(
  name = 'owl_lang_cli',
  main_class = 'owl.lang.CLI',
  deps = [
    ':owl_lang',
  ],
)
