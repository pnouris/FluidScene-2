import processing.core.PApplet;
import processing.core.PGraphics;

/**
 * Created by dubsta on 28.03.2017.
 */
public class ObstaclePainter{

    // 0 ... not drawing
    // 1 ... adding obstacles
    // 2 ... removing obstacles
    public int draw_mode = 0;
    PGraphics pg;
    private PApplet m;

    float size_paint = 15;
    float size_clear = size_paint * 2.5f;

    float paint_x, paint_y;
    float clear_x, clear_y;

    int shading = 64;

    public ObstaclePainter(PGraphics pg, PApplet applet){
        this.pg = pg;
        this.m = applet;
    }


    public void displayBrush(PGraphics dst){
        if(draw_mode == 1){
            dst.strokeWeight(1);
            dst.stroke(0);
            dst.fill(200,50);
            dst.ellipse(paint_x, paint_y, size_paint, size_paint);
        }
        if(draw_mode == 2){
            dst.strokeWeight(1);
            dst.stroke(200);
            dst.fill(200,100);
            dst.ellipse(clear_x, clear_y, size_clear, size_clear);
        }
    }


}