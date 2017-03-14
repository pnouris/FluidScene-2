
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
        x = 500;
        y = 100;
        speed = 5;
        vase.rotateX(m.radians(180));
        vase.rotateY(m.radians(90));
        //vase.rotateZ(m.radians(10));

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

    public void moveMouse(){
        m.pushMatrix();
        m.translate(m.width/2, m.height/2, 0);
        x = (float) (m.mouseY*0.01);
        y = (float) (m.mouseX*0.01);
        m.rotateX(x);
        m.rotateY(y);
        m.shape(vase, 0, 0);
        m.popMatrix();
    }

    public void displayCentered(){
        m.pushMatrix();
        m.translate(m.width/2, m.height/2, 0);
        m.rotateX((float) 3.54);
        m.rotateY((float) 4.66);
        m.shape(vase);
        m.popMatrix();
    }

}
