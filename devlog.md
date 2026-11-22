Entry 1 - 11/21

Prior to today, I had very little knowledge of 3D graphics and how to even begin implementing them. I watched part of a YouTube tutorial to get some idea of how a 3D graphics engine should work.

It turns out that each object in a 3D world can be displayed using only triangles, since a triangle is the simplest 2D shape. So, I added classes for points, triangles, and meshes to the project. A triangle is 3 points, and a mesh is multiple triangles.

I also added a matrix class with matrix multiplication, though I haven't implemented Strassen's fast algorithm yet. I tested it in Main.java and it appears to be correct, though I only ran a couple of tests so I'm not 100% sure.

The project seems a lot more doable now, though there's still many unknowns for me, such as how to implement a camera.