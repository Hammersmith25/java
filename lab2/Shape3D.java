public abstract class Shape3D {
    public abstract double volume();
    public abstract double surfaceArea();
}


class Cylinder extends Shape3D {
    private double radius, height;
    public Cylinder(double r, double h) {
        this.radius = r;
        this.height = h;
    }
    @Override
    public double volume() {
        return Math.PI * radius * radius * height;
    }

    @Override
    public double surfaceArea() {
        return (2*Math.PI * radius) * (radius + height);
    }

}
class Sphere extends Shape3D {
    private double r;
    public Sphere(double r) {
        this.r = r;
    }
    @Override
    public double volume() {
        return (4.0/3) * (Math.PI*Math.pow(r,3));
    }

    @Override
    public double surfaceArea() {
        return 4*Math.PI * Math.pow(r,2) ;
    }

}
class Cube extends Shape3D {
    private double side;
    public Cube(double s) {
        this.side = s;
    }
    @Override
    public double volume() {
        return Math.pow(side,3);
    }

    @Override
    public double surfaceArea() {
        return 6* side * side;
    }

}
