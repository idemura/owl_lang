# The Owl Programming Language

_I am providing code in this repository to you under an open source license._
_Because this is my personal repository, the license you receive to my code _
_is from me and not from my employer (Facebook)_.

## Build
Will be gradle.
To build Owl from command line, Buck build is required (https://buckbuild.com/).
First, download dependencies: `buck fetch --java11-test-mode ...`.

```
./antlr && buck build --java11-test-mode ...
```

I'm restarting this project.

## Compile and Run Owl Program
Same Java class files:
```
// Skeleton:
package id.owl_example;

// Java -> Owl:
// int -> i32
// Integer -> I32

// By default, class is package private, as Java
class Name {
  final var first, last: String
  
  // Constructors:
  new Name() { this("unknown", "unknown"); }
  
  // This syntax means "take member variable first/last, declare parameter with
  // the same name (first/last) of the same type. Assign paramters to that members.
  // You can declare type though explicitly.
  // Type should be less restrictive than var type, like i32 param for i64 member.
  new Name(this.first, this.last) {
    if name.length() <= 0 {
      throw InvalidArgument(""); // Owl doesn't have throws function declaration.
    }
  }
  
  // Property. Underlying variable is this.<name>
  var address: String
    get set;
  var address: String
    get; // Read only
    
  var address: String
    get = this.address + " USA";
  // Body allowed:
  var address: String
    get {
      if this.address.endsWith("USA") {
        return this.address;
      } else {
        return this.address + " USA";
      }
    }
    // For set, placeholder _ is used:
    set {
      this.address = _;
    };
}

public class NameCollection {
  // This means when you import class Names, this is automatically
  // imported name and you access it just as countSpaces.
  free fn countSpaces(s: String): i32 {
    var n = 0;
    for c in s {
      if greeting[i] == ' ' {
        n += 1;
      }
    }
    return n;
  }
  
  // By default, members are private
  static fn sumOf2(a, b: i32): i32 {
    return a + b;
  }
  // We HAVE TO write explicit return type in function,
  // but can have expression body.
  static fn productOf2(a, b: i32): i64 = a: i64 * b;
  // Note that type cast has higher priority, that *
  
  // Static doesn't import automatically
  static fn main(argv: String[]) {
    final var nc = new NameCollection();
  }
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
