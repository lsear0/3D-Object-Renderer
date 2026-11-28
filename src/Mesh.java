package src;
import java.util.List;

public class Mesh {
    private List<Triangle> list;

    public Mesh() {}

    public Mesh(List<Triangle> list) {
        this.list = list;
    }

    public void add(Triangle t) {
        list.add(t);
    }

    public void remove(Triangle t) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == t) {
                list.remove(t);
                return;
            }
        }
    }

    public List<Triangle> getTriangles() { return list; }
}
