package cs3318.raytracing;
//Josh's Notes
//Privtised
//Encapsulated data within sphere (added getters to variables)
//renamed dz, dy and dx to be easier to read/understand
import java.awt.*;
import java.util.List;

// An example "Renderable" object
class Sphere implements Renderable {
    Surface surface;
    Vector3D center;
    float radius;
    float radSqr;

    public Sphere(Surface s, Vector3D c, float r) {
       private surface = s;
       private center = c;
       private radius = r;
       private radSqr = r*r;
    }

public int getSurface()  
    { 
      return surface; 
    } 
public int getCentre()  
    { 
      return centre; 
    } 
public int getRadius()  
    { 
      return radius; 
    } 
public int getRadiusSqr()  
    { 
      return radSqr; 
    } 


    public boolean intersect(Ray ray) {
        float dirx = center.x - ray.origin.x;
        float diry = center.y - ray.origin.y;
        float dirz = center.z - ray.origin.z;
        float v = ray.direction.dot(dx, dy, dz);

        // Do the following quick check to see if there is even a chance
        // that an intersection here might be closer than a previous one
        if (v - getRadius()   > ray.t)
            return false;

        // Test if the ray actually intersects the sphere
        float t = getRadiusSqr() + v*v - dirx*dirx - diry*diry - dirz*dirz;
        if (t < 0)
            return false;

        // Test if the intersection is in the positive
        // ray direction and it is the closest so far
        t = v - ((float) Math.sqrt(t));
        if ((t > ray.t) || (t < 0))
            return false;

        ray.t = t;
        ray.object = this;
        return true;
    }

    public Color Shade(Ray ray, java.util.List<Object> lights, List<Object> objects, Color bgnd) {
        // An object shader doesn't really do too much other than
        // supply a few critical bits of geometric information
        // for a surface shader. It must must compute:
        //
        //   1. the point of intersection (p)
        //   2. a unit-length surface normal (n)
        //   3. a unit-length vector towards the ray's origin (v)
        //
        float px = ray.origin.x + ray.t*ray.direction.x;
        float py = ray.origin.y + ray.t*ray.direction.y;
        float pz = ray.origin.z + ray.t*ray.direction.z;

        Vector3D p = new Vector3D(px, py, pz);
        Vector3D v = new Vector3D(-ray.direction.x, -ray.direction.y, -ray.direction.z);
        Vector3D n = new Vector3D(px - center.x, py - center.y, pz - center.z);
        n.normalize();

        // The illumination model is applied
        // by the surface's Shade() method
        return surface.Shade(p, n, v, lights, objects, bgnd);
    }

    public String toString() {
        return ("sphere "getCentre()  " "+ getRadius());
    }
}
