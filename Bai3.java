import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

class Main {
    static class Point implements Comparable<Point> {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point p) {
            return this.x == p.x ? this.y - p.y : this.x - p.x;
        }
    }

    // Tính tích chéo của 3 điểm a, b, c
    static int crossProduct(Point a, Point b, Point c) {
        return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
    }

    // Tính bình phương khoảng cách giữa hai điểm
    static int distanceSquared(Point a, Point b) {
        return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
    }

    // Tìm bao lồi bằng thuật toán Graham Scan
    static List<Point> grahamScan(Point[] points) {
        int n = points.length;
        if (n < 3) throw new IllegalArgumentException("Cần ít nhất 3 điểm để tạo bao lồi!");

        // Tìm điểm thấp nhất (y nhỏ nhất, nếu bằng thì x nhỏ nhất)
        Point pivot = Arrays.stream(points).min(Comparator.comparingInt((Point p) -> p.y).thenComparingInt(p -> p.x)).get();

        // Sắp xếp điểm theo góc cực so với pivot
        Arrays.sort(points, (p1, p2) -> {
            int orientation = crossProduct(pivot, p1, p2);
            if (orientation == 0) return Integer.compare(distanceSquared(pivot, p1), distanceSquared(pivot, p2));
            return -Integer.compare(orientation, 0);
        });

        // Dùng stack để tìm bao lồi
        Stack<Point> hull = new Stack<>();
        for (Point p : points) {
            while (hull.size() >= 2 && crossProduct(hull.get(hull.size() - 2), hull.get(hull.size() - 1), p) <= 0) {
                hull.pop(); // Loại bỏ điểm bên trong
            }
            hull.push(p);
        }

        return new ArrayList<>(hull);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Nhập số lượng trạm phát sóng
        int n = scanner.nextInt();
        Point[] points = new Point[n];

        // Nhập tọa độ các trạm
        for (int i = 0; i < n; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            points[i] = new Point(x, y);
        }

        // Tìm các trạm cảnh báo (nằm trên bao lồi)
        List<Point> hull = grahamScan(points);

        // In ra kết quả
        System.out.println("Các trạm cảnh báo (tọa độ): ");
        for (Point p : hull) {
            System.out.println(p.x + " " + p.y);
        }

        scanner.close();
    }
}