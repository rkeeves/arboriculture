# arboriculture

A small project nobody asked for! 

But what could this be?! A plane, a bird...or a celebrity quiz app perhaps?

> TLDR: Not a plane. Not a bird. Not a celebrity quiz app. It is a... theoretical nothing-sandwich and some code.

## Overview

At first glance [Interpreter pattern](https://sourcemaking.com/design_patterns/interpreter) seems to be a cool thing.
You might even already did something like this without actually realizing it.
Aka you build a 'syntax tree' like structure, then you traverse it, and do some calculations/side-effects.
The Interpreter pattern locks you into one behavior though.
The data, the actual behavior / side-effect, and the traversal are both coded into the Interpreter classes, 
or are added-in later in weird ways when a new feature is needed.

I think with [Visitor pattern](https://sourcemaking.com/design_patterns/visitor) we can decouple these things a bit.
I mean:
- Data and the basic structure is stored within an immutable tree.
- The actual traversal order and side-effects can be moved to visitors or other semantic units.
- The traversal was implemented like a [top-down parser](https://binaryterms.com/top-down-parsing-in-compiler-design.html).

There are two examples.

### Selenium or Playwright

I could've done some simple arithmetic example, but I decided to spice things up a bit.
I decided on the 'domain' of web browser automation:
- There's a syntax tree which describes a web test story from a high-level perspective.
- There are different visitors. These use either `Selenium` or `Playwirght` to execute the story syntax tree.

The main goal of this example is to highlight how the 'what to do' and 'how to do it' can be separated,
while still being able to express and encode (to a certain degree) 
the semantic structure inherent to 'stories' with types and data structures.

### Generic heterogeneous tree

The another example is more about heterogeneous trees as generic data structures.
This example is geared towards playing around with the type system and mapping between different types,
and also converting the generic tree into simple Collections containers.

The tree is generic: `Tree<NODE_DATA_TYPE, LEAF_DATA_TYPE>`.
Aka all nodes have data of some type attached to them, but the leaves' and the nodes' attached data type differs.
Also, just to make the example a bit more interesting...
The leaves can use multiple types of attachment types.
You can interact with these 'leaf attachment' types via [double dispatch](https://refactoring.guru/design-patterns/visitor-double-dispatch)
in a type-safe manner.
The traversal was implemented like a [bottom-up parser](https://binaryterms.com/bottom-up-parsing-in-compiler-design.html).
