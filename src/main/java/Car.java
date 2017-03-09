/**
 * Created by dubsta on 07.03.2017.
 */



import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

public class Car{

    PShape thecar;
    float speed;
    float x, y, z;
    PShape wheel1, wheel2, wheel3, wheel4;
    PApplet parent;


    public Car(PApplet parent) {
        this.parent = parent;
        x = 0;
        y = 0;
        z = 0;
        speed = 5;
        thecar = parent.createShape(parent.GROUP);
        PShape topBox = parent.createShape(parent.BOX, 200, 100, 300);
        PShape bottomBox = parent.createShape(parent.BOX, 400, 100, 400);
        PShape exhaust = parent.createShape(parent.BOX,140,10,10);

        wheel1 = createWheelShape(200, 50);
        wheel2 = createWheelShape(200, 50);
        wheel3 = createWheelShape(200, 50);
        wheel4 = createWheelShape(200, 50);

        wheel1.translate(150, 100, 200);
        wheel2.translate(-150, 100, 200);
        wheel3.translate(150, 100, -250);
        wheel4.translate(-150, 100, -250);


        bottomBox.translate(0, 100);
        exhaust.translate(-225,100);
        thecar.addChild(topBox);
        thecar.addChild(bottomBox);
        thecar.addChild(exhaust);
        thecar.addChild(wheel1);
        thecar.addChild(wheel2);
        thecar.addChild(wheel3);
        thecar.addChild(wheel4);


        thecar.translate(parent.width/2, parent.height/2);
    }
    //reset the car to its original position
    void nodrive() {
        x = 0;
        y = 0;
        z = 0;
        thecar.resetMatrix();
        thecar.translate(parent.width/2, parent.height/2);
    }

    //drive along the X axis
    void driveXPos() {
        x += speed;
        parent.pushMatrix();
        parent.translate(x, y, z);
        parent.shape(thecar);
        parent.popMatrix();
    }
    //drive along the X negative axis
    void driveXNeg() {
        x -= speed;
        parent.pushMatrix();
        parent.translate(x, y, z);
        parent.rotateY(parent.radians(180));
        parent.shape(thecar);
        parent.popMatrix();
    }
    //drive along the Z axis
    void driveZPos() {
        z += speed;
        parent.pushMatrix();
        parent.translate(x, y, z);
        parent.rotateY(parent.radians(270));
        parent.shape(thecar);
        parent.popMatrix();
    }
    //drive along the Z negative axis
    void driveZNeg() {
        z -= speed;
        parent.pushMatrix();
        parent.translate(x, y, z);
        parent.rotateY(parent.radians(90));
        parent.shape(thecar);
        parent.popMatrix();
    }

    private PShape createWheelShape(int diameter, int thick) {
        PShape p1 = parent.createShape(parent.GROUP);
        PShape outCircle = parent.createShape(parent.ELLIPSE, 0, 0, diameter, diameter);
        PShape innerCircle = parent.createShape(parent.ELLIPSE, 0, 0, diameter, diameter);
        innerCircle.translate(0, 0, thick);

        PShape strip = parent.createShape();
        strip.beginShape(parent.TRIANGLE_STRIP);
        int sides = 50;
        float angle = (float)360 / sides;
        float half = thick / 2;
        for (int i = 0; i < sides + 1; i++) {
            float x = parent.cos( parent.radians( i * angle ) ) * (diameter/2);
            float y = parent.sin( parent.radians( i * angle ) ) * (diameter/2);
            strip.vertex( x, y, half);
            strip.vertex( x, y, -half);
        }
        strip.endShape(parent.CLOSE);
        strip.translate(0, 0, half);

        p1.addChild(outCircle);
        p1.addChild(innerCircle);
        p1.addChild(strip);
        return p1;
    }

    public PVector getCurrentLocation(){
        return new PVector(x,y,z);
    }
}