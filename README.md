# The Owl Programming Language

_I am providing code in this repository to you under an open source license._
_Because this is my personal repository,_
_the license you receive to my code is from me and not from my employer (Facebook)_.

## Build
Go to `lib` and run `python download_libs.py lib_list`, then from the project root:

```
./antlr && ./build
```

## Compile and Run
Let's create a sample Owl program and save it in `test.owl`:
```
# Module name used in call to java_owl.
module main;

fn main(String[]) {
  var greeting = "hello world";
  println(greeting);
}
```
Compile it to Java (JVM bytecode is planned in the near future):
```
./owl test.owl
```
Run compiled Owl program:
```
# module name comes before the dot, file name - after:
./java_owl main.test
```
