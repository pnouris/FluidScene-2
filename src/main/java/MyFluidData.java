import com.thomasdiewald.pixelflow.java.fluid.DwFluid2D;
import controlP5.ControlP5;
import processing.core.PApplet;

/**
 * Created by dubsta on 09.03.2017.
 */
public class MyFluidData implements DwFluid2D.FluidData {

    PApplet parent;
    private ControlP5 cp5;
    private VaseShape vase;

    public MyFluidData(PApplet parent, ControlP5 cp5, VaseShape vase) {
        this.parent = parent;
        this.cp5 = cp5;
        this.vase = vase;
    }

    // update() is called during the fluid-simulation update step.
    public void update(DwFluid2D fluid) {

        float px, py, vx, vy, radius, vscale, r, g, b, intensity, temperature;

        // add impulse: density + temperature
        intensity = 1.0f;
        px = vase.getCurrentLocation().x;
        py = vase.getCurrentLocation().y;
        radius = 20;
        r = 1.0f;
        g = 0.0f;
        b = 0.0f;
        fluid.addDensity(px, py, radius, r, g, b, intensity);
        fluid.addVelocity(px, -5, radius, 0, -5);

        //if((fluid.simulation_step) % 200 == 0){
        //  temperature = 50f;
        //  fluid.addTemperature(px, py, radius, temperature);
        //}
        temperature = -5;
        //fluid.addTemperature(px, py, radius, temperature);
        //fluid.addVelocity(px, py, radius, 0, -50);


        boolean mouse_input = !cp5.isMouseOver() && parent.mousePressed;

        // add impulse: density + velocity
        if (mouse_input && parent.mouseButton == parent.LEFT) {
            radius = 15;
            vscale = 15;
            px = parent.mouseX;
            py = parent.height - parent.mouseY;
            vx = (parent.mouseX - parent.pmouseX) * +vscale;
            vy = (parent.mouseY - parent.pmouseY) * -vscale;

            fluid.addDensity(px, py, radius, 0.25f, 0.0f, 0.1f, 1.0f);
            fluid.addVelocity(px, py, radius, vx, vy);
        }

    }
}