prebuilt_jar(
  name = 'lib_junit',
  binary_jar = 'lib/junit-4.12.jar',
)

prebuilt_jar(
  name = 'lib_hamcrest_all',
  binary_jar = 'lib/hamcrest-all-1.3.jar',
)

java_library(
  name = 'owl_lang',
  srcs = glob([
    'src/main/owl/lang/parser/*.java',
    'src/main/owl/lang/*.java',
  ]),
)

java_test(
  name = 'owl_lang_test',
  srcs = glob([
    'src/test/owl/lang/*.java',
  ]),
  deps = [
    ':owl_lang',
    ':lib_hamcrest_all',
    ':lib_junit',
  ],
)

java_binary(
  name = 'owl_lang_cli',
  main_class = 'owl.lang.CliMain',
  deps = [
    ':owl_lang',
  ],
)

