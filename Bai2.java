import java.util.Random;

public class Bai2 {
    public static double approximatePi(int numPoints) {
        Random random = new Random();
        int insideCircle = 0;
        
        for (int i = 0; i < numPoints; i++) {
            double x = random.nextDouble() * 2 - 1; // Tọa độ x trong [-1, 1]
            double y = random.nextDouble() * 2 - 1; // Tọa độ y trong [-1, 1]
            
            if (x * x + y * y <= 1) {
                insideCircle++;
            }
        }
        
        return 4.0 * insideCircle / numPoints;
    }
    
    public static void main(String[] args) {
        int numPoints = 1_000_000;
        double estimatedPi = approximatePi(numPoints);
        System.out.println("Giá trị xấp xỉ của π: " + estimatedPi);
    }
}
