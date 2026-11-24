Entry 1 - 11/21

Prior to today, I had very little knowledge of 3D graphics and how to even begin implementing them. I watched part of a YouTube tutorial to get some idea of how a 3D graphics engine should work.

It turns out that each object in a 3D world can be displayed using only triangles, since a triangle is the simplest 2D shape. So, I added classes for points, triangles, and meshes to the project. A triangle is 3 points, and a mesh is multiple triangles.

I also added a matrix class with matrix multiplication, though I haven't implemented Strassen's fast algorithm yet. I tested it in Main.java and it appears to be correct, though I only ran a couple of tests so I'm not 100% sure.

The project seems a lot more doable now, though there's still many unknowns for me, such as how to implement a camera.


Entry 2 - 11/24

Today I worked on implementing Strassen's fast matrix multiplication algorithm. I spent some time learning how the algorithm actually works and how to implement it in Java.

I found that, in order to implement the algorithm, I would need two additional methods for adding and subtracting matrices, and preferably a method for appending two matrices (either lengthwise or widthwise).

I implemented these three methods, and also moved every matrix operation from the Matrix class itself to a new class called MatrixOperator. I think having all the arithmetic in a separate class tidies up the code a bit.

Tomorrow I plan to finish implementing Strassen's algorithm, then I'll use it to render 3D objects.