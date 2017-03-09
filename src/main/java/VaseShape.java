
import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;


/**
 * Created by dubsta on 07.03.2017.
 */
public class VaseShape {

    private PShape vase;
    public float x, y, z;
    private float speed;
    private PApplet m;

    public VaseShape(PShape vase, PApplet applet) {
        this.vase = vase;
        m = applet;
        x = m.width /2;
        y = m.height /2;
        speed = 5;
    }

    public PShape getVase() {
        return vase;
    }

    public PVector getCurrentLocation(){
        return new PVector(x,y,z);
    }

    public void driveXPos() {
        x += speed;
        m.pushMatrix();
        m.translate(x, y, z);
        m.shape(vase);
        m.popMatrix();
    }

}
