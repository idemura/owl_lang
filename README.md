# The Owl Programming Language

_I am providing code in this repository to you under an open source license._
_Because this is my personal repository,_
_the license you receive to my code is from me and not from my employer (Facebook)_.

## Build
To build Owl from command line, Buck build is required (https://buckbuild.com/).
First, download dependencies: `buck fetch --java11-test-mode ...`.

```
./antlr && buck build --java11-test-mode ...
```

## Compile and Run Owl Program
Let's create a sample Owl program and save it in `test.owl`:
```
# Module name should match file name
module test;

main(argv: String[]) {
  var greeting = "hello world";
  var spaces = 0;
  for i in 0, size(greeting) {
    if (greeting[i] == $\_) {
      spaces += 1;
    }
  }
  println(greeting);
  println(spaces);
}
```
Compile it to JVM class file (compiler writes output to owl_out by default):
```
./owl test.owl
```
To compile and run, do:
```
./run test.owl
```
