import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

class Intersection {
    public int name; // name of the vertex
    public boolean seen; // flag to check if the vertex has already been visited
    public List<Street> Adj; // adjacency list; use LinkedList or ArrayList

    Intersection(int n) {
        name = n;
        seen = false;
        Adj = new ArrayList<>();
    }

    @Override
    public String toString() {
        return String.valueOf(name);
    }
}

class Street {
    public Intersection From;
    public Intersection To;

    Street(Intersection u, Intersection v) {
        From = u;
        To = v;
    }


    public Intersection otherEnd(Intersection u) {
        // if the vertex u is the head of the arc, then return the tail else return the head
        if (From == u) {
            return To;
        } else {
            return From;
        }
    }

    @Override
    public String toString() {
        return "[" + From + "," + To + "]";
    }
}

class City implements Iterable<Intersection> {

    public List<Intersection> verts; // array of vertices
    public int numNodes; // number of verices in the graph


    City(int size) {
        numNodes = size;
        verts = new ArrayList<>(size + 1);
        verts.add(0, null);
        // create an array of Vertex objects
        for (int i = 1; i <= size; i++)
            verts.add(i, new Intersection(i));
    }

    void addStreet(int a, int b) {
        Intersection u = verts.get(a);
        Intersection v = verts.get(b);
        Street e = new Street(u, v);
        u.Adj.add(e);
        v.Adj.add(e);
    }


    void addDirectedStreet(int a, int b) {
        Intersection head = verts.get(a);
        Intersection tail = verts.get(b);
        Street e = new Street(head, tail);
        head.Adj.add(e);
    }


    public Iterator<Intersection> iterator() {
        return new IntersectionIterator();
    }

    private class IntersectionIterator implements Iterator<Intersection> {
        private Iterator<Intersection> it;

        private IntersectionIterator() {
            it = verts.iterator();
            it.next();  // Index 0 is not used.  Skip it.
        }


        public boolean hasNext() {
            return it.hasNext();
        }


        public Intersection next() {
            return it.next();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static City readCity(Scanner in) {

        int n = in.nextInt(); // number of intersection in the graph
        int m = in.nextInt(); // number of streets in the graph
        if (n == 0 && m == 0)
            return null;

        City g = new City(n);
        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();
            if (w == 1) {
                g.addDirectedStreet(u, v);
            } else {
                g.addStreet(u, v);
            }
        }

        return g;
    }
}

public class Uva11838 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s1 = new Scanner(System.in);
        while (true) {
            City c1 = City.readCity(s1);
            if (c1 != null)
                checkConnectedness(c1);
            else{
                break;
            }
        }
    }

    private static void checkConnectedness(City c1) {
        int output = 1;
        for (Intersection i : c1) {
            init(c1);
            DFShelper(i);
            if (!checkAllVisited(c1)) {
                output = 0;
                break;
            }
        }
        System.out.println(output);
    }

    private static void init(City c) {
        for (Intersection i : c) {
            i.seen = false;
        }
    }

    private static void DFShelper(Intersection i) {
        for (Street s : i.Adj) {
            Intersection i2 = s.otherEnd(i);
            if (!i2.seen) {
                i2.seen = true;
                DFShelper(i2);
            }
        }
    }

    private static boolean checkAllVisited(City c) {
        for (Intersection i : c) {
            if (!i.seen)
                return false;
        }
        return true;
    }

    private static void printCity(City c1) {
        for (Intersection i : c1) {
            System.out.println(i);
            for (Street s : i.Adj)
                System.out.println(s);
        }
    }
}
