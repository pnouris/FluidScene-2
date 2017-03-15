
import processing.core.PApplet;
import processing.core.PImage;
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
        x = m.width/2;
        y = 600;
        speed = 5;

        this.vase = createShapeTri(vase);
        this.vase.rotateX(m.radians(180));
        this.vase.rotateY(m.radians(90));
        //vase.rotateZ(m.radians(10));

    }

    public PShape getVase() {
        return vase;
    }

    public PVector getCurrentLocation(){
        float offsetX = 20;
        float offsetY = 110;
        float tempY = m.height -y;
        return new PVector(x+offsetX,tempY+offsetY,z);
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

    private PShape createShapeTri(PShape r) {
        PImage tex = m.loadImage("src\\main\\resources\\tex.jpg");
        PShape s = m.createShape();
        s.beginShape(m.TRIANGLES);
        s.noStroke();
        s.texture(tex);
        s.textureMode(m.NORMAL);
        for (int i=100; i<r.getChildCount (); i++) {
            if (r.getChild(i).getVertexCount() ==3) {
                for (int j=0; j<r.getChild (i).getVertexCount(); j++) {
                    PVector p = r.getChild(i).getVertex(j);
                    PVector n = r.getChild(i).getNormal(j);
                    float u = r.getChild(i).getTextureU(j);
                    float v = r.getChild(i).getTextureV(j);
                    s.normal(n.x, n.y, n.z);
                    s.vertex(p.x, p.y, p.z, u, v);
                }
            }
        }
        s.endShape();
        return s;
    }

}
