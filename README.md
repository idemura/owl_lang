# The Owl Programming Language

_I am providing code in this repository to you under an open source license._
_Because this is my personal repository,_
_the license you receive to my code is from me and not from my employer (Facebook)_.

## Build
Go to `lib` and run `python download_libs.py lib_list`, then from the project root:

```
./antlr && ./build
```

## Compile and Run Owl Program
Let's create a sample Owl program and save it in `test.owl`:
```
# Module name should match file name
module test;

main(String[]) {
  var greeting = "hello world";
  println(greeting);
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
