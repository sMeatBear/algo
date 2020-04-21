package app.basic.nestedclass;

public class InnerClassTest {
    public class Coordinate {
        private int x;
        private int y;
        public Coordinate(int x, int y) {
            this.setX(x);
            this.setY(y);
        }
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
    }

    public static void main(String[] args) {
        Coordinate c = new InnerClassTest().new Coordinate(1, 2);
        System.out.println("x: " + c.getX() + ", y: " + c.getY());
    }
}