package cs3318.raytracing;

import java.awt.*;
import java.util.List;
//Josh's Notes
//1. Privated code
//2. provided getters for origin and direction to make them harder to access
//3. 
class Ray {
    private static final float MAX_T = Float.MAX_VALUE; 
    private Vector3D origin;
    private Vector3D direction;
    private  float t;
    private Renderable object;

   public int getOrigin()  
    { 
      return origin; 
    } 

  public int getDirection()  
    { 
      return  direction; 
    } 


    private Ray(Vector3D eye, Vector3D dir) {
        origin = new Vector3D(eye);
        direction = Vector3D.normalize(dir);
    }

    private boolean trace(List<Object> objects) {
        t = MAX_T;
        object = null;
        for (Object objList : objects) {
            Renderable object = (Renderable) objList;
            object.intersect(this);
        }
        return (object != null);
    }

    // The following method is not strictly needed, and most likely
    // adds unnecessary overhead, but I preferred the syntax
    //
    //            ray.Shade(...)
    // to
    //            ray.object.Shade(ray, ...)
    //
    private final Color Shade(List<Object> lights, List<Object> objects, Color bgnd) {
        return object.Shade(this, lights, objects, bgnd);
    }

    @Override
    private String toString() {
        return ("ray origin = "+ getOrigin()+"  direction = "+getDirection()+"  t = "+t);
    }
}
