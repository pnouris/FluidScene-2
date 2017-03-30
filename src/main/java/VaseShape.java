
import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

import processing.core.PMatrix3D;


/**
 * Created by dubsta on 07.03.2017.
 */
public class VaseShape {

    private PShape vase;
    public float x, y, z;
    private float speed;
    private PApplet m;
    private MyFluidData fluid;
    public int movement = 0;


    public VaseShape(PShape vase, PApplet applet, MyFluidData fluid) {
        this.vase = vase;
        m = applet;
        x = m.width/2;
        y = m.height/2;
        speed = 5;

        this.vase.rotateX(m.radians(90));
        this.vase.rotateY(m.radians(90));
        //this.vase.rotateZ(m.radians(90));
        this.fluid = fluid;
        this.fluid.px=0;
        this.fluid.py=0;
    }

    public PShape getVase() {
        return vase;
    }

    public PVector getCurrentLocation(){
        float offsetX = 20;
        float offsetY = 110;
        float tempY = m.height -y;
        return new PVector(x+offsetX,tempY+offsetY,z);
        //return new PVector(x+offsetX + m.cos(-m.frameCount) * x, tempY+offsetY + m.sin(-m.frameCount) * y, z);
    }

    public void driveXPos() {
        x += speed;
        m.pushMatrix();
        m.translate(x, y, z);
        m.shape(vase);
        m.popMatrix();

    }


    public void animation(){

        //Set the fluid position
        PVector origPosition = new PVector(150,62,0);

        // Set rotation angles
        float angle = m.radians(movement);
        m.pushMatrix();
        m.translate(m.width/2, m.height/2,  z);
        m.rotateZ(angle);
        m.translate(-250,-250);
        m.shape(vase,m.width/2,m.height/2);
        m.popMatrix();

        // define the matrix which will be used for the transformation
        // this is the same as was use for the vase. But it goes on the opposite direction
        //notice the following matrix is used for rotation around the z axis (clockwise)
        // I constructed this matrix with help from here: http://uk.mathworks.com/help/phased/ref/rotz.html
        // and https://en.wikipedia.org/wiki/Rotation_matrix (for the clockwise bit)
        PMatrix3D mrotate1 = new PMatrix3D(
                m.cos(angle), m.sin(angle),  0,  m.width/2,
                -m.sin(angle), m.cos(angle), 0,  m.height/2,
                0, 0,  1,  0,
                0, 0, 0,  1);

        //now apply the matrix to our point to give us the correct position
        PVector newPos = new PVector();
        mrotate1.mult(origPosition,newPos);
        //and set the values x and y
        this.fluid.px=newPos.x;
        this.fluid.py=newPos.y;

    }

}
