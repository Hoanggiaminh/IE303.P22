import java.util.Random;
import java.util.Scanner;

public class Bai1 {
    public static double approximateCircleArea(double r, int numPoints) {
        Random random = new Random();
        int insideCircle = 0;
        
        for (int i = 0; i < numPoints; i++) {
            double x = (random.nextDouble() * 2 - 1) * r; // Tọa độ x trong [-r, r]
            double y = (random.nextDouble() * 2 - 1) * r; // Tọa độ y trong [-r, r]
            
            if (x * x + y * y <= r * r) {
                insideCircle++;
            }
        }
        
        double squareArea = (2 * r) * (2 * r); // Diện tích hình vuông chứa hình tròn
        return ((double) insideCircle / numPoints) * squareArea;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập bán kính r: ");
        double r = scanner.nextDouble(); // Nhập bán kính từ bàn phím
        scanner.close();
        
        int numPoints = 1_000_000; // Số điểm ngẫu nhiên
        
        double estimatedArea = approximateCircleArea(r, numPoints);
        System.out.println("Diện tích xấp xỉ của hình tròn bán kính " + r + " là: " + estimatedArea);
    }
}

